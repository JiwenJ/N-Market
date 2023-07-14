package com.example.demo.controller;
import cn.dev33.satoken.annotation.SaCheckBasic;
import cn.dev33.satoken.annotation.SaCheckLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @SaCheckLogin
    @RequestMapping("checkLogin")
    public SaResult checkLogin() {
        return SaResult.ok();
    }


    //----------------------------------------------------------------------
    // 用户登入，输入用户名密码，返回用户个人信息字典 OK
//    @RequestMapping  ("login")@RequestParam(name="username",required = false)
//    @RequestParam(name="password",required = false)
    @RequestMapping("login")
    public Map<String, Object> doLogin(  String username,  String password){
        System.out.println(username);
        System.out.println(password);
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name",username);
        map.put("password",password);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        User user = userMapper.selectOne(wrapper);
        System.out.println("用户尝试登入");
        if(user == null){
            res.put("state","failure");
            res.put("result",null);
            System.out.println("用户登入失败，暂未注册");
            return res;
//            System.out.println("in");
//            StpUtil.login(100);
//            str = "---------ok----------";
//            System.out.println(str);
//            System.out.println("用户登入成功");
//            return StpUtil.getTokenValue();
        }
        System.out.println("用户登入成功");
        res.put("state","success");
        res.put("result",user);
        return res;
    }
//----------------------------------------------------------------------

    @PostMapping ("test")
    public String test() {
        return "123";

    }

    @PostMapping ("isLogin")
    public String isLogin(){
        System.out.println("here--------");
        return "当前会话是否登录："+StpUtil.isLogin();
    }

    @RequestMapping("getLoginId")
    public String getLoginId(){
        System.out.println("--------here--------");
        return "当前会话账号ID："+StpUtil.getLoginIdDefaultNull();
    }


    //token 的信息
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        System.out.println("here--------");
        return SaResult.data("当前token信息："+StpUtil.getTokenInfo());

    }

    // 测试注销  ---- http://localhost:8081/user/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok("注销成功");
    }

    @RequestMapping("disable")
    public String disable(){
        StpUtil.disable(10001, 30);
        return "已被封禁30S";
    }
}
