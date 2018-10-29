package org.com.lzz.test;

import redis.clients.jedis.Jedis;

public class mainTest {

    public static void main(String args[]){
        System.out.println("asd");
        Jedis j = new Jedis("locahost",6379);
        j.auth("123456");
        System.out.println(j.set("hello","123"));
        System.out.println(j.get("hello"));
    }
}
