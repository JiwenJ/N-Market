package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String email;
    private Integer age;
    private String name;
    private String school;
    private String gender;
    private String personal;
    private String phone;
    private String password;

}
