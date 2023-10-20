package com.example.mypetstoreserver.util;


import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Date;


@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;


    private static final String USERNAME = "xichen.public@foxmail.com";

    @Resource
    private TemplateEngine templateEngine;

    /**
     * 发送thymeleaf模板构建的验证码邮件
     * @param to 接收邮箱
     * @return 发送成功返回true，失败返回false
     */
    public boolean sendMail(String to) {
        // 生成验证码
        String captcha = CaptchaUtil.getCode();

        // 上下文对象
        Context context = new Context();
        context.setVariable("captcha", Arrays.asList(captcha.split(""))); // 分割验证码

        String process = templateEngine.process("email.html", context);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("【MyPetStore官方】验证码");
            helper.setFrom(USERNAME);
            helper.setTo(to);
            helper.setSentDate(new Date());
            helper.setText(process, true); // 第二个参数为true表示消息内容类型为HTML
            javaMailSender.send(message);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
