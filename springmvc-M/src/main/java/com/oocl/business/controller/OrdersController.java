package com.oocl.business.controller;


import com.oocl.business.handler.BusinessSocket;
import com.oocl.business.model.Orders;
import com.oocl.business.model.PageBean;
import com.oocl.business.model.PageInfo;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.view.OrdersRequest;
import com.oocl.business.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/orders")
@CrossOrigin(origins = "*", maxAge = 100000)
public class OrdersController {

    @Resource(name = "ordersService")
    private OrdersService ordersService;


    @PostMapping()
    @ResponseBody
    public RespondResult addOders(OrdersRequest orders){

        RespondResult resp = ordersService.addOders(orders);

        return resp;
    }

    @PutMapping()
    @ResponseBody
    public RespondResult updateOders(@RequestBody OrdersRequest orders){

        RespondResult resp = ordersService.updateMsg(orders);

        return resp;
    }



    @ResponseBody
    @GetMapping()
    public PageBean<Orders> queryOrders(OrdersRequest orders, PageInfo pageInfo){
        PageBean<Orders> page = ordersService.listOrders(orders,pageInfo);
        return page;
    }

    @ResponseBody
    @GetMapping("/msg/{msg}/user/{userId}")
    public String receive(@PathVariable String msg,@PathVariable String userId){
        BusinessSocket.sendMessageToUsers(msg,userId);
        return "success";
    }
}
