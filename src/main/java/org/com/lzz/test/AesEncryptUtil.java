package org.com.lzz.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
/**
 * Created by zhangxiaobei on 2017/1/16.
 */
 
public class AesEncryptUtil {
    public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return new BASE64Encoder().encode(bytes);
    }

    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = new BASE64Decoder().decodeBuffer(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }
    public static void main(String args[]){
        //YwB5BNV763UWGuDPFgpvkQ==
        //OE7K444+29BzQ+FVG3KKMQ==
        //R2rptbgzE/c9eeL3gfo0Ig==

        //b54P9UV58NvohscCcBT6Dg==
        //H+dRYbeRKHR0eaqTuKeyPg==
        //本地
        //yo5T+iDFYwL3lJcp4q/wVw==
        //中民java
        //j3xALNTw3m62vDXi5o7R7Q==
        try {
            System.out.println(aesEncrypt("zhongming","zhongmin.cn_wine"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(new BASE64Encoder().encode(aesCbcPkcs5PaddingEncrypt("zhongming".getBytes(),"zhongmin.cn_wine")));
//        System.out.println(new BASE64Encoder().encode((aesCbcNoPaddingDecrypt("j3xALNTw3m62vDXi5o7R7Q==".getBytes(),"zhongmin.cn_wine"))));
    }
 


}
