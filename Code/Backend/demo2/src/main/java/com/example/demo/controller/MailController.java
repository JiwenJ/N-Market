package com.example.demo.controller;

import com.example.demo.mapper.GoodMapper;
import com.example.demo.service.MailService;
//import com.example.demo.service.MimeMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/Mail")
public class MailController {

    @Autowired
    private MailService mailService;
    @RequestMapping("/Send")
    public String Send(HttpServletRequest request)
    {
        String price=request.getParameter("price");
        String name=request.getParameter("name");
        String to=request.getParameter("sell_email");
        String from=request.getParameter("buy_email");
        String statement=request.getParameter("statement");
        String time = request.getParameter("time");
        String subject = "N-Market 预约通知";
        String sell_content = "尊敬的客户您好:\n\t您的商品" + name + "已被预约，买家承诺购买，预约时间段为"+time+"\n\t买家联系方式为"+from + "，请双方在预约时间前尽快联系，以免影响双方信用。" ;
        String buy_content = "尊敬的客户您好，，您选择的商品" + name + "成功预约，您承诺在原则上购买此商品，预约时间段为"+time+"卖家联系方式为"+to + "，请双方在预约时间前尽快联系，以免影响双方信用。";
        try{
            mailService.sendSimpleMail(to,subject,sell_content);
            mailService.sendSimpleMail(from,subject,buy_content);
            return "YES";
        }catch (Exception e){
            return "NO";
        }
    }
    @RequestMapping("/SendHtml")
    public String SendHtml(HttpServletRequest request)
    {
        System.out.println("-------EMAIL--------");
        String buy=request.getParameter("buyemail");
        String sell=request.getParameter("sellemail");
        String name=request.getParameter("name");
        String time=request.getParameter("time");
        String price=request.getParameter("price");
        System.out.println("here");
        try{
            mailService.sendMimeMail(name,time,buy,sell,price,true);
            mailService.sendMimeMail(name,time,buy,sell,price,false);
            return "YES";
        }catch (Exception e){
            return "NO";
        }
    }
    @RequestMapping("/Test")
    public String Test(@RequestParam("to") String to,@RequestParam("subject") String subject,@RequestParam("content") String content)
    {
        try{
            mailService.sendSimpleMail(to,subject,content);
            return "YES";
        }catch (Exception e){
            return "NO";
        }
    }



}


