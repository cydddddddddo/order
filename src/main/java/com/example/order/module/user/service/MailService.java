package com.example.order.module.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
/**
     * @author chencj
     * @date 2020/7/1
     */
@Component
public class MailService {

	 @Autowired
     JavaMailSender javaMailSender;
	 public void sendSimpleMail(String from,String to,String cc,String subject,String content) {
		 SimpleMailMessage msg=new SimpleMailMessage();
		 msg.setFrom("点餐平台<321831788@qq.com>");
		 msg.setTo(to);
		 msg.setSubject(subject);
		 msg.setText(content);
		 javaMailSender.send(msg);
	 }
}
