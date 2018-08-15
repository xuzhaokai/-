package com.oocl.business.service.impl;

import com.oocl.business.model.Items;
import com.oocl.business.model.Menu;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.view.OrdersRequest;
import com.oocl.business.service.OrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrdersServiceImplTest {

    @Resource(name = "ordersService")
    private OrdersService ordersService;

    @Test
    public void getOrdersById() {
    }

    @Test
    public void listOrders() {
    }

    @Test
    public void updateOders() {

//
//        OrdersRequest req = new OrdersRequest();
//
//        req.setId("8a5e9d0b64f92b900164f92b96700000");
//        req.setAddress("beijing");
//        req.setMobile("13250062001");
//        req.setStatus(0);

        ordersService.grade("8a5e9d0b64f92b900164f92b96700000",4.5);
//        ordersService.updateOders(req);

    }



    @Test
    public void addOders() {

        OrdersRequest req = new OrdersRequest();

        Menu m1 = new Menu();
        m1.setId("8a5e9d1f64f57faa0164f580bbcf0000");
        m1.setName("红烧猪扒");
        Menu m2 = new Menu();
        m2.setId("8a5e9d1f64f8bbb50164f8f37b210001");
        m2.setName("cccc11");

        List<Items> itemsList = new ArrayList<Items>();
        Items items1=new Items();
        items1.setTotalPrice(52.21);
        items1.setCount(20);
        items1.setUnitPrice(10);
        items1.setMenu(m1);
        items1.setMenuName(m1.getName());
        itemsList.add(items1);

        Items items2=new Items();
        items2.setTotalPrice(52.21);
        items2.setCount(20);
        items2.setUnitPrice(10);
        items2.setMenu(m2);
        items2.setMenuName(m2.getName());
        itemsList.add(items2);

        req.setAddress("zha");
        req.setConsignee("bernie");
        req.setMobile("13231251254");
        req.setItems(itemsList);
        req.setStore_id("123");
        req.setTotalPrice(522.23);
        req.setCustomer_id("888");


        RespondResult result =  ordersService.addOders(req);



    }

    @Test
    public void grade() {
        ordersService.grade("8a5e9d0b64f92b900164f92b96700000",4.5);
    }
}