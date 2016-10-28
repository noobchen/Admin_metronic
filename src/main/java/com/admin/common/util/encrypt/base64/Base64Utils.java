package com.admin.common.util.encrypt.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-8-10
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */
public class Base64Utils {
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}
