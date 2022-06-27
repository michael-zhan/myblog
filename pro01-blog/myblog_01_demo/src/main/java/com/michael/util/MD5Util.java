package com.michael.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMd5(String s) throws NoSuchAlgorithmException {
        //获得MD5加密算法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //对明文进行加密，得到每1字节
        byte[] digest = md5.digest(s.getBytes());
        //定义StringBuilder对得到的密文每一个字节拼接
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:
                digest) {
            //1个字节转换为2位16进制，与ff进行与运算会把负数变正数，不然会出现高位ffffffff
            String s1=Integer.toHexString(b&0xff);
            //对于不足2位的1个字节前面进行0填充
            if(s1.length()==1){
                stringBuilder.append("0");
            }
            //拼接
            stringBuilder.append(s1);
        }
        return stringBuilder.toString();
    }
}
