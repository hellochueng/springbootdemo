package org.com.lzz.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.com.lzz.domain.User;
import org.com.lzz.service.UserService;
import org.com.lzz.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

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

    @RequestMapping("/user/{id}")
    @ResponseBody
    public  String getUserById(@PathVariable("id")String id, HttpServletRequest request){
        String host = request.getRemoteHost();
        Integer port = request.getRemotePort();
        CommonUtils.println("host:"+host+"\nport:"+port+"");
        return userService.getUserById(id).toString();
    }

    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public  String deleteuser(@PathVariable("id")String id){
        return userService.deleteUserById(id);
    }

    @RequestMapping(value="/test",method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody List<User> user){
        System.out.println(user);
        return "a";
    }
}
