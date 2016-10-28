package com.admin.common.util.net;

import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-10-9
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
public class IpUtils {
    public static List<String> getLocalIp() {
        List<String> ipList = new ArrayList<String>();
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                System.out.println("DisplayName:" + ni.getDisplayName());
                System.out.println("Name:" + ni.getName());
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ipList.add(ips.nextElement().getHostAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipList;
    }

    public static boolean isLocalHost(String requestIp) {
        List<String> ipList = getLocalIp();
        for (String ip : ipList) {
            if (StringUtils.equals(ip, requestIp)) {
                return true;
            }
        }
        return false;
    }

}
