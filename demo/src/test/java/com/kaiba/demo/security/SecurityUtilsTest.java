package com.kaiba.demo.security;

import org.junit.Test;

/**
 * Created by kaiba on 2016/9/8.
 */
public class SecurityUtilsTest {

    @Test
    public void testEncryptWithAES(){

    }

    @Test
    public void testDecodeWithAES(){

    }

    public static void main(String[] args){
        String content = "test";
        String password = "12345678";
        //加密
        System.out.println("加密前：" + content);
        byte[] encryptResult = SecurityUtils.encryptWithAES(password.getBytes(),content.getBytes());
        //解密
        byte[] decryptResult = SecurityUtils.decodeWithAES(password.getBytes(),encryptResult);
        System.out.println("解密后：" + new String(decryptResult));
    }
}
