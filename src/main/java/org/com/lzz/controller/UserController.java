package org.com.lzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloworld(){
        return "hello world";
    }

    @RequestMapping("/User/hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("进来了");
        return "index";
    }

}
