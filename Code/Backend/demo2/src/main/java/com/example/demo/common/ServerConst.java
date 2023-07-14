package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ServerConst {
    private String address = "http://localhost:8081/";
    public String getAddress()
    {
        return address;
    }
}
