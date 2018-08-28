package org.com.lzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloworld(){
        return "hello world";
    }
}
