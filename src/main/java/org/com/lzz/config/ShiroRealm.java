package org.com.lzz.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.logging.Logger;

/**
 * shiro身份验证类
 */
public class ShiroRealm extends AuthorizingRealm {
    /*
        授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //这里可以通过principals对象获取到登录之后的用户对象信息，从中拿到权限
        System.out.println("something");
        return null;
    }

    /*
        权限认证    这个方法就是进行验证是否登录了 里面写判断前端传递过来的数据（用户密码或者token之类的）  每次前面写的登录filter都会在这之前
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken User = new UsernamePasswordToken("tomcat","123");
        //中间代码进行用户信息的认证判断是否已登录  未登录应该跑出异常后前端进行跳转到登录页面
        //已登录则把对于的ceadentials放在下面的simpleInfo中
        //SimpleAuthenticationInfo第一个参数放置用户信息，里面包括权限集合这个用户信息可以在进行完登录请求之后直接放在缓存中
        //第二个参数则是与之前login中的token参数的第二个值相同，不然抛出401也就是本次请求结束
        //第三个参数表示当前cealm的代理对象名字
//        if(true)
//        throw new IncorrectCredentialsException("无效的认证信息");
        System.out.println("token getName:::::::::"+getName());
        return new SimpleAuthenticationInfo(User, "123456", getName());
    }
}
