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
@TableName("orderlist")
public class OrderList {
    @TableId(type = IdType.AUTO)
    private int oid;
    private int status;
    private String name;

    private String price;
    private String time;


    private int uid;

    private int gid;


}
