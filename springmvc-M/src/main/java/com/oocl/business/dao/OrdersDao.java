package com.oocl.business.dao;

import com.oocl.business.model.Orders;
import com.oocl.business.model.PageInfo;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.view.OrdersRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Map;

public interface OrdersDao{

    Orders queryOrdersById(String ordersId);

    List<Orders> queryOrdersList(Map<String,Object> condition, PageInfo pageInfo);

    Orders addOders(Orders orders);

    Orders updateStatus(String ordersId,Integer status);

    Orders updateGrade(String ordersId,double grade);

    Orders updateMsg(OrdersRequest orders);

    Integer queryOrdersCount(Map<String,Object> condition);
}
