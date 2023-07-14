package com.example.demo.service.Impl;

import freemarker.template.Configuration;
import com.example.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

@Component
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;
    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
    public String buildContent(String good, String time, String mail, String price, boolean status) {
        //加载邮件html模
        InputStream inputStream = null;
        Resource resource = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        if (status) {
            resource = new ClassPathResource("mail4seller.ftl");
        } else {
            resource = new ClassPathResource("mail4buyer.ftl");
        }
        try {
            inputStream = resource.getInputStream();
            fileReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            logger.info("发送邮件读取模板失败{1}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), good, time, mail, price);
    }
    public void sendMimeMail(String good, String time, String buymail,String sellmail,String price,boolean status) {
        MimeMessage message = mailSender.createMimeMessage();
        if (status)
        {
            try {
                //邮箱发送内容组成
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setSubject("N-Market预约提醒");
                helper.setText(buildContent(good,time,buymail,price,status), true);
                helper.setTo(sellmail);
                helper.setFrom(from);
                mailSender.send(message);
            } catch (MessagingException e) {
                logger.info("error");
            }
        }
        else {
            try {

                //邮箱发送内容组成
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setSubject("N-Market预约提醒");
                helper.setText(buildContent(good,time,sellmail,price,status), true);
                helper.setTo(buymail);
                helper.setFrom(from);
                mailSender.send(message);
            } catch (MessagingException e) {
                logger.info("error");
            }
        }

        }


}