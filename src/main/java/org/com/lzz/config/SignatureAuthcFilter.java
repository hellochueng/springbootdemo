package org.com.lzz.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.format.Base64Format;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.com.lzz.test.Base64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

public class SignatureAuthcFilter extends AccessControlFilter {

    Logger logger = LoggerFactory.getLogger(SignatureAuthcFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        String authToken, nonce, signature;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();

//        authToken = httpRequest.getHeader(SecurityConstants.HEADER_AUTH_TOKEN);
//        nonce = httpRequest.getHeader(SecurityConstants.HEADER_NONCE);
//        signature = httpRequest.getHeader(SecurityConstants.HEADER_SIGNATURE);
//
//        SignatureToken signatureToken = new SignatureToken(authToken, nonce, signature);
        try {
            System.out.println("token getName:::::::::"+new BASE64Encoder().encode("123456".getBytes()));
            SecurityUtils.getSubject().login(new UsernamePasswordToken("tom",new BASE64Encoder().encode("123456".getBytes()),true,httpRequest.getRemoteHost()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            onLoginFail(response);
            return false;
        }
        System.out.println("loginfilter ----  through");
//            ThreadLocalPrincipalHolder.setPrincipal((Principal) SecurityUtils.getSubject().getPrincipal());
        return true;
    }

    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("unicode");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("{\"error\":1, \"message\":\"无效的认证信息\"}");
    }

}