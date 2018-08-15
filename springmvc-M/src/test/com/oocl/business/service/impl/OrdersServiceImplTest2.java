package com.oocl.business.service.impl;

import com.oocl.business.service.OrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrdersServiceImplTest2 {


    @Resource(name = "ordersService")
    private OrdersService ordersService;


    @Test
    public void getOrdersById() {
    }

    @Test
    public void listOrders() {
    }

    @Test
    public void grade() {
        ordersService.grade("8a5e9d0b64f92b900164f92b96700000",4.5);
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void updateMsg() {
    }

    @Test
    public void addOders() {
    }
}