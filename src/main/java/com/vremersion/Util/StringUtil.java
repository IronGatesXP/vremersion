package com.vremersion.Util;

/**
 * @author XP
 * @date 2018/5/14 15:26
 */
public class StringUtil {
    public static boolean isNotEmpty(String str){
        if(str != null && str.trim().length() != 0){
            return true;
        }
        return false;
    }
}
