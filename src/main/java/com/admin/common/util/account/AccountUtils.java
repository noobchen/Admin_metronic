package com.admin.common.util.account;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-13
 * Time: 下午3:13
 * To change this template use File | Settings | File Templates.
 */
public class AccountUtils {
    private static AtomicLong atomicLong = new AtomicLong(0);
    private static final String PREFIX = "A";

    private static String getNextIndex() {
        long i = atomicLong.getAndIncrement();

        String str = "00" + i;
        return str.substring(str.length() - 3);
    }

    public static String getCustomerAccount() {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
        sb.append(format.format(new Date()));
        sb.append(getNextIndex());
        return sb.toString();
    }
}
