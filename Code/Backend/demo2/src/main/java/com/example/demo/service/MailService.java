package com.example.demo.service;

public interface MailService {
    public void sendSimpleMail(String to, String subject, String content);
    public void sendMimeMail(String good, String time, String buymail,String sellmail,String price,boolean status);
}