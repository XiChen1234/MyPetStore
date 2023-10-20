package com.example.mypetstoreserver.controller;

import com.example.mypetstoreserver.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private EmailUtil emailUtil;
    @GetMapping("/hello")
    public String hello() {
//        emailUtil.sendEmail("1234", "2585245562@qq.com");
        return "Hello World";
    }
}
