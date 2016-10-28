package com.admin.common.permission.util;

import com.admin.common.util.encrypt.base64.Base64Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-28
 * Time: 下午8:39
 * To change this template use File | Settings | File Templates.
 */
public class UrlEncryption {
    private static final String prefix = "Ads32^%";
    private static final String suffix = "&*23YH";

    public static String encrypt(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        try {
            return Base64Utils.encryptBASE64((prefix + url + suffix).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decoding(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        try {
            String string = new String(Base64Utils.decryptBASE64(url));
            if (StringUtils.startsWith(string, prefix) && StringUtils.endsWith(string, suffix)) {
                int startIndex = prefix.length();
                int endIndex = StringUtils.indexOf(string, suffix);

                return string.substring(startIndex, endIndex);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取账号
     *
     * @param params,加密参数串
     * @return
     */
    public static String getAccount(String params) {
        String sourceParams = decoding(params);

        if (StringUtils.isEmpty(sourceParams)) {
            return null;
        } else {
            StringTokenizer sz = new StringTokenizer(sourceParams, "&");
            while (sz.hasMoreTokens()) {
                String param = sz.nextToken();
                String[] s = param.split("=");
                String key = s[0];
                if (StringUtils.equals("account", key)) {
                    return s[1];
                }
            }
            return null;
        }
    }

    /**
     * 获取账户类型
     *
     * @param params 加密参数串
     * @return 0为管理员，1为cp,2为sp，3为渠道
     */
    public static String getAccountType(String params) {
        String sourceParams = decoding(params);

        if (StringUtils.isEmpty(sourceParams)) {
            return null;
        } else {
            StringTokenizer sz = new StringTokenizer(sourceParams, "&");
            while (sz.hasMoreTokens()) {
                String param = sz.nextToken();
                String[] s = param.split("=");
                String key = s[0];
                if (StringUtils.equals("accountType", key)) {
                    return s[1];
                }
            }
            return null;
        }
    }

    /**
     * 获取账户类型编号
     *
     * @param params 加密参数串
     * @return
     */
    public static String getAccountTypeId(String params) {
        String sourceParams = decoding(params);

        if (StringUtils.isEmpty(sourceParams)) {
            return null;
        } else {
            StringTokenizer sz = new StringTokenizer(sourceParams, "&");
            while (sz.hasMoreTokens()) {
                String param = sz.nextToken();
                String[] s = param.split("=");
                String key = s[0];
                if (StringUtils.equals("accountTypeId", key)) {
                    if (s.length == 1) {
                        return null;
                    } else {
                        return s[1];
                    }
                }
            }
            return null;
        }
    }

    public static String getR(String params) {
        String sourceParams = decoding(params);

        if (StringUtils.isEmpty(sourceParams)) {
            return null;
        } else {
            StringTokenizer sz = new StringTokenizer(sourceParams, "&");
            while (sz.hasMoreTokens()) {
                String param = sz.nextToken();
                String[] s = param.split("=");
                String key = s[0];
                if (StringUtils.equals("r", key)) {
                    if (s.length == 1) {
                        return null;
                    } else {
                        return s[1];
                    }
                }
            }
            return null;
        }
    }

    /**
     * 获取权限
     *
     * @param params 加密参数串
     * @return QUERY, ADD, EDIT, DELETE
     */
    public static List<String> getOperateTypes(String params) {
        String sourceParams = decoding(params);

        if (StringUtils.isEmpty(sourceParams)) {
            return null;
        } else {
            StringTokenizer sz = new StringTokenizer(sourceParams, "&");
            List<String> operateTypes = new ArrayList<String>();
            while (sz.hasMoreTokens()) {
                String param = sz.nextToken();
                String[] s = param.split("=");
                String key = s[0];

                if (StringUtils.equals("operateType", key)) {
                    operateTypes.add(s[1]);
                }
            }
            return operateTypes;
        }
    }

    /**
     * 检查参数
     *
     * @param param
     * @return
     */
    public static boolean check(String param) {
        try {
            String string = new String(Base64Utils.decryptBASE64(param));
            if (StringUtils.startsWith(string, prefix) && StringUtils.endsWith(string, suffix)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static String getBackUrl(String params) {
        String sourceParams = decoding(params);

        if (StringUtils.isEmpty(sourceParams)) {
            return null;
        } else {
            StringTokenizer sz = new StringTokenizer(sourceParams, "&");
            while (sz.hasMoreTokens()) {
                String param = sz.nextToken();
                String[] s = param.split("=");
                String key = s[0];
                if (StringUtils.equals("backUrl", key)) {
                    if (s.length == 1) {
                        return null;
                    } else {
                        return s[1];
                    }
                }
            }
            return null;
        }
    }

    public static boolean checkUser(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        try {
            String string = new String(Base64Utils.decryptBASE64(content));
            if (StringUtils.startsWith(string, prefix) && StringUtils.endsWith(string, suffix)) {
                int startIndex = prefix.length();
                int endIndex = StringUtils.indexOf(string, suffix);

                String result = string.substring(startIndex, endIndex);
                if (StringUtils.equals(result, "true")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
