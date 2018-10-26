package org.com.lzz.test;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.com.lzz.Application;
import org.com.lzz.service.imp.UserServiceImp;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import redis.clients.jedis.Jedis;

import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
//@SpringApplicationConfiguration(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration
public class Test {

//    @Autowired
//    DefaultWebSecurityManager   securityManager;
//
//    @Autowired
//    EhCacheManager  ehCacheManager;
//
//    @Autowired
//    ShiroFilterFactoryBean  shiroFilter;
//
//    @Autowired
//    UserServiceImp userServiceImp;

    @org.junit.Test
    public void testdemo(){
//            System.out.println(testBean.a);
//        Map<String,String> map = shiroFilter.getFilterChainDefinitionMap();
//        System.out.println("-------------------------"+ehCacheManager);
//        System.out.println("-------------------------"+ehCacheManager);
//        System.out.println("-------------------------"+securityManager.getCacheManager());
        System.gc();
    }


}
