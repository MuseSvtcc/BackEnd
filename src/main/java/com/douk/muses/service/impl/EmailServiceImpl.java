package com.douk.muses.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class EmailServiceImpl {
    @Autowired
    JavaMailSenderImpl mail;


    public  String EmailCode(String email){
        Random random=new Random();
        int i = random.nextInt(89999) + 10000;

        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("欢迎注册！！！");
        message.setText("您的验证码为："+i);
        message.setTo(email);
        message.setFrom("muse<2172941774@qq.com>");
        try {
            mail.send(message);
        }catch (MailException e){
            e.printStackTrace();
            return "code error";
        }
        return String.valueOf(i);
    }


    public String EmailGet(String email) {
        Random random=new Random();
        int i = random.nextInt(89999) + 10000;

        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("找回密码！！！");
        message.setText("您的验证码为："+i);
        message.setTo(email);
        message.setFrom("muse<2172941774@qq.com>");
        try {
            mail.send(message);
        }catch (MailException e){
            e.printStackTrace();
            return "code error";
        }
        return String.valueOf(i);
    }

    public String EmailLogin(String email) {
        Random random=new Random();
        int i = random.nextInt(89999) + 10000;

        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("欢迎登录！！！");
        message.setText("您的验证码为："+i);
        message.setTo(email);
        message.setFrom("muse<2172941774@qq.com>");
        try {
            mail.send(message);
        }catch (MailException e){
            e.printStackTrace();
            return "code error";
        }
        return String.valueOf(i);
    }
}
