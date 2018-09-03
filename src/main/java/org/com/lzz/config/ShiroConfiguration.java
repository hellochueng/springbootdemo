package org.com.lzz.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    shiro的配置文件  总体包括生命周期   /    身份验证    /   数据缓存    /   管理对象     /  过滤器
 */
@Configuration
public class ShiroConfiguration {


    /*
        shiro生命周期的配置
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
    //指定加密方式方式，也可以在这里加入缓存，当用户超过五次登陆错误就锁定该用户禁止不断尝试登陆
    //解决每次启动项目报错（用户密码不能为明文错误）
//    @Bean(name = "hashedCredentialsMatcher")
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(1024);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }

    /*
        身份验证配置  ShiroRealm为自己编写的权限控制类
     */
    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
//        配置是否限制登录错误数
//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /*
        shiro的缓存配置  缓存存放一下用户的登录信息，token等，也可以自己继承这个类 编写 如缓存在redis中
     */
    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    /*
        shiro的管理对象配置
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(ehCacheManager());//用户授权/认证信息Cache, 采用EhCache 缓存
        return securityManager;
    }

    /*
        shiro的url过滤器配置  对不同的url进行配置不同的权限    anon表示可以匿名访问    authc表示需要登录这个会进行身份权限验证
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager  securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置对于请求的filter
//        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/login");
//        filters.put("logout", logoutFilter);
//        shiroFilterFactoryBean.setFilters(filters);


        Map<String, Filter> filters = new HashMap<>();
        filters.put("login", new SignatureAuthcFilter());
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
        filterChainDefinitionManager.put("/order", "login,perms[order]");//anon 可以理解为不拦截
        filterChainDefinitionManager.put("/statistic/**",  "anon");//静态资源不拦截
//        filterChainDefinitionManager.put("/user/**", "authc,roles[user]");
        filterChainDefinitionManager.put("/shop", "anon");
//        filterChainDefinitionManager.put("/admin/**", "authc,roles[admin]");
//        filterChainDefinitionManager.put("/hello", "authc,roles[admin]");//anon 可以理解为不拦截
//        filterChainDefinitionManager.put("/logout", "logout");                  //登出配置 shiro自己实现了登出功能
        //所有的资源都要进过这个拦截
        // authc表示要进行的请求,roles【****】表示需要的角色才可以进行请求
        //在配置这个拦截时，可以配置一个filter配合登录一块验证 所有要过这个请求的都加上一个登录验证
        //一个继承AccessControlFilter的filter    其中配上前端传过来的用户名密码或者token之类的东西进行验证这个用户是否已经登录了，登录了就可以进行接下来的请求了，否则不然
        filterChainDefinitionManager.put("/**",  "login");//其他资源全部拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        return shiroFilterFactoryBean;
    }

    /*
        开启请求权限拦截注解模式
     */

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * 不要使用 DefaultAdvisorAutoProxyCreator 会出现二次代理的问题，这里不详述
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * cookie对象;
     * 记住密码实现起来也是比较简单的，主要看下是如何实现的。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
}
