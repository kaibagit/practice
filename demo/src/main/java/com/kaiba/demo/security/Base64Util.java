package com.kaiba.demo.security;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by luliru on 2017/2/21.
 */
public class Base64Util {

    public static String encodeStr(String plainText){
        byte[] b=plainText.getBytes();
        Base64 base64=new Base64();
        b=base64.encode(b);
        String s=new String(b);
        return s;
    }

    public static String decodeStr(String encodeStr){
        byte[] b=encodeStr.getBytes();
        Base64 base64=new Base64();
        b=base64.decode(b);
        String s=new String(b);
        return s;
    }
}
