package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@TableName("good")
public class Good {
    @TableId(type = IdType.AUTO)
    private int id;
    private int uid;
    private int status;
    private String name;
//    private String picurl;
    private String picurl1;
    private String picurl2;
    private String picurl3;
    private String comment;
    private String price;
    private String time;
    private String email;
//    private String price;

    private int category;


}
