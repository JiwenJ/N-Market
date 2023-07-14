package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Good;
import com.example.demo.entity.OrderList;
import com.example.demo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/del")
    public void getOrderList(@RequestParam Map<String, String> params) {
        System.out.println("------del------");
        System.out.println(params.get("oid"));
        int uid = Integer.parseInt(params.get("oid"));
        orderMapper.deleteById(uid);
    }
    @RequestMapping("/getAll")
    public List<OrderList> get() {
        List<OrderList> orders = orderMapper.selectList(null);
        System.out.println(orders);
        return orders;
    }

    @RequestMapping("/getByuid")
    public List<OrderList> getByuid(@RequestParam Map<String, String> params){
        System.out.println("----getAll-----");
        int uid = Integer.parseInt(params.get("uid"));
        System.out.println(uid);
        QueryWrapper<OrderList> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("uid",uid);
        List<OrderList> orderList = orderMapper.selectList(orderQueryWrapper);
        System.out.println(orderList);
        return orderList;
    }

    @RequestMapping("/insert")
    public OrderList insert(@RequestParam Map<String, String> params) {
        System.out.println("------INSERT------");
        OrderList order = new OrderList();
        System.out.println(params);

        int uid = Integer.parseInt(params.get("uid"));
        int gid = Integer.parseInt(params.get("gid"));
        int status = Integer.parseInt(params.get("status"));
        String goodname=params.get("name");
        String price=params.get("price");
        String time = params.get("time");
        System.out.println(uid);

        order.setName(goodname);
        order.setUid(uid);
        order.setTime(time);
        order.setPrice(price);
        order.setGid(gid);
        order.setStatus(status);

        System.out.println(order);
        orderMapper.insert(order);
        return order;
    }
}
