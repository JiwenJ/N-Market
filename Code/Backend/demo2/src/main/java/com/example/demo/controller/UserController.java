package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.Good;
import com.example.demo.entity.User;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodMapper goodMapper;
    @RequestMapping("getUserById")
    public User getUserById(@RequestParam Map<String, String> map){
        System.out.println("----getUserById-----");
        System.out.println(map.get("id"));
        int id = Integer.parseInt(map.get("id"));
        return userMapper.selectById(id);
    }

    @RequestMapping("/insert")
    public String insert(@RequestParam("username") String name,@RequestParam("password") String passwd) {
        User user = new User();
        user.setName(name);
        user.setPassword(passwd);
        userMapper.insert(user);
        System.out.println(user);
        return "OK";
    }
    @RequestMapping("/updatepasswd")
    public String updatepasswd(@RequestParam("username") String name,@RequestParam("password") String passwd) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name",name).set("password", passwd);
        userMapper.update(null, updateWrapper);
        return "OK";
    }
    @RequestMapping("/updatemail")
    public String updatemail(@RequestParam("username") String name,@RequestParam("mail") String mail) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name",name).set("email", mail);
        userMapper.update(null, updateWrapper);
        return "OK";
    }


    @RequestMapping("/delete")
    public Integer delete(@RequestParam("username") String name,@RequestParam("password") String passwd) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("password",passwd);
        return userMapper.deleteByMap(map);
    }
    @RequestMapping("/findall")
    public List<User> findall(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
        return users;
    }

    @RequestMapping("/select")
    public void selectById(){
        User user = userMapper.selectById(7);
        System.out.println(user);

    }


    @RequestMapping("/getOrderList")
    public List<Good> getOrderList (@RequestParam("uid") int uid)
    {
        System.out.println(uid);
        QueryWrapper<Good> goodQueryWrapper = new QueryWrapper<>();
        goodQueryWrapper.eq("uid",uid);
        List<Good> goodList = goodMapper.selectList(goodQueryWrapper);
        System.out.println(goodList);
        return goodList;

    };

    @RequestMapping("/insertOrder")
    public void insertOrder(@RequestBody Good order)
    {
        Good tmp = order;
        goodMapper.insert(tmp);
        System.out.println(tmp);
        System.out.println(order);
    };
    @RequestMapping("/infoUpdate")
    public String infoUpdate(@RequestParam Map<String, String> params)
    {
        System.out.println("----infoUpdate----");
        try{
            User user = new User();
            user.setId(Integer.parseInt(params.get("id")));
            user.setName(params.get("name"));
            user.setPassword(params.get("password"));
            user.setAge(Integer.valueOf(params.get("age")));
            user.setEmail(params.get("email"));
            user.setGender(params.get("gender"));
            user.setPersonal(params.get("personal"));
            user.setPhone(params.get("phone"));
            user.setSchool(params.get("school"));
            userMapper.updateById(user);
            return "success";
        }
        catch (Exception e)
        {
            return "failure";
        }
    };


}
