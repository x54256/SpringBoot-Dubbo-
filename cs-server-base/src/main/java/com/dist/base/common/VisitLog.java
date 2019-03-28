package com.dist.base.common;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 记录访问日志
 *
 * @author yinxp@dist.com.cn
 */
@Slf4j
public abstract class VisitLog {

    /**
     * 记录请求日志
     *
     * @param request
     */
    public static void record(HttpServletRequest request) {
        StringBuilder visitLog = new StringBuilder();
        visitLog.append("当前用户：");
        visitLog.append(getIpAddress(request));
        visitLog.append("；正在访问：");
        visitLog.append(request.getRequestURL());
        log.info(visitLog.toString());
    }

    /**
     * 计算客户端ip
     *
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


    /**
     * 打印request对象
     *
     * @param request
     */
    public static void printRequest(HttpServletRequest request) {
        System.out.println("Method=" + request.getMethod());
        System.out.println("RequestURI" + request.getRequestURI());
        System.out.println("ServletPath" + request.getServletPath());
        System.out.println("CharacterEncoding" + request.getCharacterEncoding());
        System.out.println("---------parameter-------------");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String element = parameterNames.nextElement();
            System.out.println(element + "=" + request.getParameter(element));
        }
        System.out.println("---------header-------------");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            System.out.println(element + "=" + request.getHeader(element));
        }
    }
}
