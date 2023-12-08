package com.kdt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("kdtgroovy@gmail.com");
        javaMailSender.setPassword("sqyv bwbv byzu qtlt");
        javaMailSender.setPort(465);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
}