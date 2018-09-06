package org.com.lzz.test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class AESCrptography {
	private static byte[] _key1 = { 0x12, 0x34, 0x56, 0x78, (byte)0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF, 0x12, 0x34, 0x56, 0x78, (byte)0x90, (byte)0xAB, (byte)0xCC, (byte)0xEF };
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String content="zhongming";
		String key="zhongmin.cn_wine";

		System.out.println("加密前："+byteToHexString(content.getBytes()));
		byte[] encrypted=AES_CBC_Encrypt(content.getBytes(), key.getBytes(), _key1);

		System.out.println("加密后："+byteToHexString(encrypted));
		byte[] decrypted=AES_CBC_Decrypt(encrypted, key.getBytes(), _key1);
		System.out.println("解密后："+byteToHexString(decrypted));
	}
	
	public static byte[] AES_CBC_Encrypt(byte[] content, byte[] keyBytes, byte[] iv){
		
		try{
			KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(keyBytes));
			SecretKey key=keyGenerator.generateKey();
			Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			byte[] result=cipher.doFinal(content);
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("exception:"+e.toString());
		} 
		return null;
	}
	
	public static byte[] AES_CBC_Decrypt(byte[] content, byte[] keyBytes, byte[] iv){
		
		try{
			KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(keyBytes));//key长可设为128，192，256位，这里只能设为128
			SecretKey key=keyGenerator.generateKey();
			Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			byte[] result=cipher.doFinal(content);
			return result;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("exception:"+e.toString());
		} 
		return null;
	}
		
	public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

}