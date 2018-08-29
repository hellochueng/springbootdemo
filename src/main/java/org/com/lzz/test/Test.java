package org.com.lzz.test;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.com.lzz.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringApplicationConfiguration(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class Test {

    @Autowired
    DefaultWebSecurityManager   securityManager;

    @Autowired
    EhCacheManager  ehCacheManager;

    @org.junit.Test
    public void testdemo(){
        System.out.println("-------------------------"+ehCacheManager);
        System.out.println("-------------------------"+securityManager.getCacheManager());
    }
}
