package com.oocl.business.dao.impl;

import com.oocl.business.dao.OrdersDao;
import com.oocl.business.model.Orders;
import com.oocl.business.model.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrdersDaoImplTest {
    @Resource(name = "ordersDao")
    private OrdersDao ordersDao;

    @Test
    public void queryOrdersById() {
    }

    @Test
    public void queryOrdersList() {

        Orders orders = new Orders();
        orders.setId("123");
        orders.setStatus(null);
        Map<String,Object> map  =new HashMap<String, Object>(); // ""   "   "  null
        map.put("storeId",orders.getId());
//        map.put("status",2);
        map.put("createdAt","2018-08-01");
//        map.put("ordersId","8a5e9d0b64fa94130164fa941e360090");
        PageInfo pageInfo = new PageInfo();
        //pageInfo.setOrder("id");
        pageInfo.setOrder("createdAt");
        pageInfo.setDescOrAsc("Desc");
        pageInfo.setPage(1);
        pageInfo.setSize(10);
       List<Orders> list =  ordersDao.queryOrdersList(map,pageInfo);
       System.out.println(list);


    }

    @Test
    public void updateGrade() {
    }

    @Test
    public void updateMsg() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void addOders() {
    }
}