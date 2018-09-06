package org.com.lzz.test;

import org.com.lzz.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class testBean {
    public static String a;
    @Value("${spring.adad}")
    public void setA(String a) {
        testBean.a = a;
    }
}
