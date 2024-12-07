package com.einstein.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
public class IpUtils {

    public static final String UNKNOWN = "unknown";
    public static final String IPV4_LOCAL = "127.0.0.1";
    public static final String IPV6_LOCAL = "0:0:0:0:0:0:0:1";

    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (IPV6_LOCAL.equals(ip)) {
            return IPV4_LOCAL;
        }
        return ip;
    }
}
