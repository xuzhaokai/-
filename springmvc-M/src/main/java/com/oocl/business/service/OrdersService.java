package com.oocl.business.service;

import com.oocl.business.model.Orders;
import com.oocl.business.model.PageBean;
import com.oocl.business.model.PageInfo;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.view.OrdersRequest;

public interface OrdersService {

    RespondResult getOrdersById(String ordersId);

    /**
    *@Description 分页查找订单
    *@param
    *@return
    */
    PageBean listOrders(OrdersRequest orders, PageInfo pageInfo);


    /**
    *@Description  修改订单状态
    *@param
    *@return
    */
    RespondResult grade(String ordersId,double grade);

    RespondResult updateStatus(String ordersId,int status);


    /**
     *@Description  修改订单地址
     *@param
     *@return
     */
    RespondResult updateMsg(OrdersRequest orders);


    /**
     *@Description  添加订单
     *@param
     *@return
     */
    RespondResult addOders(OrdersRequest orders);
}
