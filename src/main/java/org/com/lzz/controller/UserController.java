package org.com.lzz.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/order")
    @ResponseBody
    public String order(){
        return "order";
    }

    @RequestMapping("/admin")
    @ResponseBody
//    @RequiresRoles("admin")
    @RequiresPermissions("admin")
    public String hello(){
        return "admin";
    }

    @RequestMapping("/shop")
    @ResponseBody
    public String shop(){
        return "shopping";
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("进来了");
        return "index";
    }
}
