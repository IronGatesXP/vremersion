package com.vremersion.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author XP
 * @date 2018/5/8 16:00
 */
public class SecurityUtil {
    public static String md5(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        return new BigInteger(1,md.digest()).toString(16);
    }
}
