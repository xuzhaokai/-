package com.oocl.business.dao.impl;

import com.oocl.business.dao.OrdersDao;
import com.oocl.business.model.Menu;
import com.oocl.business.model.Orders;
import com.oocl.business.model.PageInfo;
import com.oocl.business.model.view.OrdersRequest;
import com.oocl.business.util.CalendarUtil;
import com.oocl.business.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


@Repository("ordersDao")
public class OrdersDaoImpl implements OrdersDao {

    @PersistenceContext(name = "em")
    private EntityManager em;

    public Orders queryOrdersById(String ordersId) {

        Orders orders = em.find(Orders.class,ordersId);
        return orders;
    }

    public List<Orders> queryOrdersList(Map<String,Object> condition, PageInfo pageInfo) {
        StringBuilder builder = new StringBuilder("select ord from Orders ord where 1=1 ");

        String ordersId = (String) condition.get("ordersId");
        String customerId = (String) condition.get("customerId");
        String storeId = (String) condition.get("storeId");
        Integer status = (Integer) condition.get("status");
        String createdAt = (String) condition.get("createdAt");

        if (!StringUtils.isBlank(ordersId)){
            builder.append(" and  ord.id='"+ordersId+"'");
        }
        if (!StringUtils.isBlank(createdAt)){
            builder.append(" and  ord.createdAt Like '%"+createdAt+"%'");
        }
        if(!StringUtils.isBlank(customerId)){
            builder.append(" and  ord.customer.id='"+customerId+"'");
        }
        if (!StringUtils.isBlank(storeId)){
            builder.append(" and  ord.store.id='"+storeId+"'");
        }

        if (status!=null){
            builder.append(" and  ord.status="+status);
        }

        builder.append(" order by ord."+pageInfo.getOrder()+" "+pageInfo.getDescOrAsc());
        System.out.print("sql:"+builder.toString());

        List<Orders> ordersList = (List<Orders>) PageUtil.getPageResult(em,
                builder.toString(),null,pageInfo.getPage(), pageInfo.getSize());
        return ordersList;
    }

    public Orders updateGrade(String ordersId,double grade) {

        Orders m = em.find(Orders.class, ordersId);
        m.setGrade(grade);
        em.persist(m);
        return m;

    }

    public Orders updateMsg(OrdersRequest orders) {
        Orders m = em.find(Orders.class, orders.getId());
        m.setUpdateMsd(orders);
        em.persist(m);
        return m;
    }

    public Integer queryOrdersCount(Map<String,Object> condition) {
        StringBuilder builder = new StringBuilder("select count(*) from Orders ord where 1=1 ");

        String ordersId = (String) condition.get("ordersId");
        String customerId = (String) condition.get("customerId");
        String storeId = (String) condition.get("storeId");
        Integer status = (Integer) condition.get("status");
        String createdAt = (String) condition.get("createdAt");

        if (!StringUtils.isBlank(ordersId)){
            builder.append(" and  ord.id='"+ordersId+"'");
        }
        if (!StringUtils.isBlank(createdAt)){
            builder.append(" and  ord.createdAt Like '%"+createdAt+"%'");
        }
        if(!StringUtils.isBlank(customerId)){
            builder.append(" and  ord.customer.id='"+customerId+"'");
        }
        if (!StringUtils.isBlank(storeId)){
            builder.append(" and  ord.store.id='"+storeId+"'");
        }

        if (status!=null){
            builder.append(" and  ord.status="+status);
        }



        Query query = em.createQuery(builder.toString());
        Long count = (Long) query.getResultList().get(0);
        return count.intValue();
    }

    public Orders updateStatus(String ordersId,Integer status) {

        Orders m = em.find(Orders.class, ordersId);
        m.setStatus(status);
        return m;

    }



    public Orders addOders(Orders orders) {

        em.persist(orders);
        return orders;
    }
}
