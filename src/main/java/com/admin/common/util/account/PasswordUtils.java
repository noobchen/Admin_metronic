package com.admin.common.util.account;

import com.admin.common.util.encrypt.md5.Md5Utils;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-24
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class PasswordUtils {
    private static final String PREFIX = "83Yhds:><";
    private static final String SUFFIX = "k%$sdj628";

    public static String decode(String sourcePassword) {
        return Md5Utils.getMD5((PREFIX + sourcePassword + SUFFIX).getBytes());
    }

    public static void main(String[] args){
        System.out.println(PasswordUtils.decode("123456"));
    }
}
