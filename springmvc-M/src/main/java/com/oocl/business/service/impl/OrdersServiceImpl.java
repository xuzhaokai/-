package com.oocl.business.service.impl;

import com.oocl.business.compontment.JerseyClient;
import com.oocl.business.dao.ItemsDao;
import com.oocl.business.dao.MenuDao;
import com.oocl.business.dao.OrdersDao;
import com.oocl.business.dao.StoreDao;
import com.oocl.business.enums.OdersStatusEnum;
import com.oocl.business.enums.StatusEnum;
import com.oocl.business.model.*;
import com.oocl.business.model.view.OrdersRequest;
import com.oocl.business.service.OrdersService;
import com.oocl.business.util.CalendarUtil;
import com.oocl.business.util.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service("ordersService")
@Transactional()
public class OrdersServiceImpl implements OrdersService {

    @Resource(name = "ordersDao")
    private OrdersDao ordersDao;

    @Resource(name = "menuDao")
    private MenuDao menuDao;

    @Resource(name = "itemsDao")
    private ItemsDao itemsDao;

    @Resource(name = "storeDao")
    private StoreDao storeDao;

    @Resource(name = "jerseyClient")
    private JerseyClient client;



    public RespondResult getOrdersById(String ordersId) {
        RespondResult resp = new RespondResult();

        Orders orders = ordersDao.queryOrdersById(ordersId);

        if(orders!=null){
            resp.setStatusCode(OdersStatusEnum.FORBIDDEN.getCode());
            resp.setMsg(OdersStatusEnum.FORBIDDEN.getMsg());
        }else{
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("orders",orders);
            resp.setDatas(data);
        }

        return resp;
    }

    public PageBean<Orders> listOrders(OrdersRequest orders,PageInfo pageInfo) {

        //查询条件
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("ordersId",orders.getId());
        condition.put("customerId",orders.getCustomer_id());
        condition.put("storeId",orders.getStore_id());
        condition.put("status",orders.getStatus());
        condition.put("createdAt",orders.getCreatedAt());


        List<Orders> ordersList = ordersDao.queryOrdersList(condition,pageInfo);

        //查询日志总记录数
        Integer logsCount = ordersDao.queryOrdersCount(condition);

        return new PageBean<Orders>(ordersList, logsCount, pageInfo.getSize());

    }

    /**
    *@Description 修改评分
    *@param
    *@return
    */
    public RespondResult grade(String ordersId, double grade) {
        RespondResult resp = new RespondResult();
        Map<String,Object> datas = new HashMap<String, Object>();

        Orders orders =  ordersDao.updateGrade(ordersId,grade);

        if(orders!=null) {
            datas.put("orders", orders);
            resp.setDatas(datas);
            resp.setStatusCode(OdersStatusEnum.OK.getCode());
            resp.setMsg(OdersStatusEnum.OK.getMsg());
        }else{
            resp.setStatusCode(OdersStatusEnum.FORBIDDEN.getCode());
            resp.setMsg(OdersStatusEnum.FORBIDDEN.getMsg());
        }
        return resp;
    }


    /**
    *@Description 更新订单状态
    *@param
    *@return
    */
    public RespondResult updateStatus(String ordersId,int newStatus) {

        RespondResult resp = new RespondResult();

        Orders oldOrders = ordersDao.queryOrdersById(ordersId);
        Orders updateOrders = new Orders();

        int oldStatus = oldOrders.getStatus();


        if(oldStatus == StatusEnum.ORDERS_STATUS_WAITING.getValue()){
            //原来状态是等待接单4
            /** 接单.   改为已接单0*/
            if(newStatus == StatusEnum.ORDERS_STATUS_HAVE.getValue()){
                oldOrders.setStatus(newStatus);
                updateOrders = ordersDao.updateStatus(ordersId,newStatus);
            }

            /** 拒单.   改为被拒3*/
            else if(newStatus == StatusEnum.ORDERS_STATUS_REFUSE.getValue()){
                oldOrders.setStatus(newStatus);
                updateOrders = ordersDao.updateStatus(ordersId,newStatus);
            }

        }else if(oldStatus==StatusEnum.ORDERS_STATUS_HAVE.getValue()){
            //原来状态是已接单0
            /** 配送    改为配送中1*/
            if(newStatus == StatusEnum.ORDERS_STATUS_DELIVERING.getValue()){
                oldOrders.setStatus(newStatus);
                updateOrders = ordersDao.updateStatus(ordersId,newStatus);
            }

        }else if(oldStatus==StatusEnum.ORDERS_STATUS_DELIVERING.getValue()){
            //原来状态是配送中1
            /** 送至用户  改为到达指定地点2*/
            if(newStatus == StatusEnum.ORDERS_STATUS_ARRIVE.getValue()){
                oldOrders.setStatus(newStatus);
                updateOrders = ordersDao.updateStatus(ordersId,newStatus);
            }

        }else if(oldStatus==StatusEnum.ORDERS_STATUS_DELIVERING.getValue()){
            //原来状态到达指定地点2
            /** 完成订单   改为完成订单6*/
            if(newStatus == StatusEnum.ORDERS_STATUS_FINISH.getValue()){
                oldOrders.setStatus(newStatus);
                updateOrders = ordersDao.updateStatus(ordersId,newStatus);
            }
        }else{
            //不合法操作
            resp.setStatusCode(OdersStatusEnum.FORBIDDEN.getCode());
            resp.setMsg(OdersStatusEnum.FORBIDDEN.getMsg());
        }


        if(updateOrders == null){
            resp.setStatusCode(OdersStatusEnum.FORBIDDEN.getCode());
            resp.setMsg(OdersStatusEnum.FORBIDDEN.getMsg());
        }else{
            resp.setStatusCode(OdersStatusEnum.OK.getCode());
            resp.setMsg(OdersStatusEnum.OK.getMsg());
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("orders",updateOrders);
            resp.setDatas(map);
        }

        return resp;
    }

    /**
    *@Description 更新收货信息
    *@param
    *@return
    */
    public RespondResult updateMsg(OrdersRequest orders) {
        RespondResult resp = new RespondResult();
        Map<String,Object> datas = new HashMap<String, Object>();

        Orders newOrders =  ordersDao.updateMsg(orders);

        if(newOrders!=null) {
            int status = newOrders.getStatus();
            String msg="";
            if(status==StatusEnum.ORDERS_STATUS_ARRIVE.getValue()){
                msg = "confirm";
            }else if(status==StatusEnum.ORDERS_STATUS_HAVE.getValue()){
                msg = "accept";
            }else if(status==StatusEnum.ORDERS_STATUS_REFUSE.getValue()){
                msg = "refuse";
            }

            //触发web socket
            String CustomerUrl  = PropertiesUtil.getProperty("customer.socket.url");
            CustomerUrl = CustomerUrl.replace("MSG", msg);
            CustomerUrl = CustomerUrl.replace("USER",orders.getCustomer_id() );
            String str = client.sendHttp(CustomerUrl);


            datas.put("orders", newOrders);
            resp.setDatas(datas);
            resp.setStatusCode(OdersStatusEnum.OK.getCode());
            resp.setMsg(OdersStatusEnum.OK.getMsg());
        }else {
            resp.setStatusCode(OdersStatusEnum.FORBIDDEN.getCode());
            resp.setMsg(OdersStatusEnum.FORBIDDEN.getMsg());
        }
        return resp;
    }


    public RespondResult addOders(OrdersRequest orders) {

        RespondResult resp = new RespondResult();
        Map<String,Object> map = new HashMap<String, Object>();
        Orders newOrders = new Orders();
        Double orderAmount = new Double(0);


        List<Items> itemsList = new ArrayList<Items>();
        //1. 查询商品（数量, 价格）
        for (Items orderItems: orders.getItems()) {
            Menu MenuInfo = menuDao.find(orderItems.getMenu().getId());

            if (MenuInfo == null) {
                resp.setStatusCode(OdersStatusEnum.FORBIDDEN.getCode());
                resp.setMsg(OdersStatusEnum.FORBIDDEN.getMsg());
            }

            //2 计算订单总价
            orderAmount += orderItems.getTotalPrice();

            //3 订单详情入库
            orderItems.setMenu(MenuInfo);
            orderItems.setOrders(newOrders);
            // itemsList.add(itemsDao.add(orderItems));
            itemsList.add(orderItems);

        }

        //3. 写入订单
        newOrders.setItems(itemsList);//订单详情
        //TODO
//        newOrders.setCustomer();//顾客
        Store store = storeDao.listOne(orders.getStore_id());
        newOrders.setStore(store);//店铺
        newOrders.setStoreName(store.getName());
        newOrders.setAddress(orders.getAddress());
        newOrders.setConsignee(orders.getConsignee());
        newOrders.setMobile(orders.getMobile());
        newOrders.setCreatedAt(CalendarUtil.getCurrentDateTime());
        newOrders.setRemark(orders.getRemark());//备注
        newOrders.setTotalPrice(orderAmount);
        newOrders.setStatus(StatusEnum.ORDERS_STATUS_WAITING.getValue());//状态为等待接单

        Orders returnOrd = ordersDao.addOders(newOrders);


        //4. 扣库存
        //TODO

        if(returnOrd!=null){//成功入库
            map.put("orders",returnOrd);
            resp.setStatusCode(OdersStatusEnum.OK.getCode());
            resp.setMsg(OdersStatusEnum.OK.getMsg());
        }else{
            resp.setStatusCode(OdersStatusEnum.FORBIDDEN.getCode());
            resp.setMsg(OdersStatusEnum.FORBIDDEN.getMsg());
        }

        return resp;
    }
}
