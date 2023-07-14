package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/info")
    public List<HashMap> getinfo(){
        int num = 8;
        List<HashMap> list =new ArrayList<>();
        List<String> firstNameList =new ArrayList<String>(Arrays.asList("大学化学","大学化学","大学化学","大学化学","大学化学","大学化学","大学化学"));
        for (int i = 1; i <= num; i++) {
            HashMap<String, String> dict = new HashMap<>();
            String serverconst = "http://172.28.187.198:8081/goodsitem";
            System.out.println(serverconst);
            String pic_name =  serverconst + firstNameList.get(i) + Integer.toString(i)+".png";
            dict.put("images",pic_name);
            dict.put("name",firstNameList.get(i));
            dict.put("price","100");
            list.add(dict);
        }
        return list;

    }

}
