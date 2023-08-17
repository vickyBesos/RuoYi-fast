package com.ruoyi.project.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class Test {

    @GetMapping("validate")
    public String validate(){
        return "测试";
    }
}
