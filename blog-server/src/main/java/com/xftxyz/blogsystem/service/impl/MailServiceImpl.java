package com.xftxyz.blogsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xftxyz.blogsystem.controller.utils.BlogException;
import com.xftxyz.blogsystem.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    String SENDER;

    @Autowired
    JavaMailSender mailSender;

    /**
     * 发送普通邮件
     *
     * @param to      收件人
     * @param subject 主题（标题）
     * @param content 内容
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new BlogException("邮件发送失败");
        }
    }
}
