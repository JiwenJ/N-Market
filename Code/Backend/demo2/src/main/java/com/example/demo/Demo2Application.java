package com.example.demo;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@MapperScan("com.example.demo.mapper")
@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        System.out.println("hello world");
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
        SpringApplication.run(Demo2Application.class, args);
    }

}
