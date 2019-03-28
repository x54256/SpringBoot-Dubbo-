package com.dist.base.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 项目地址工具【仅适用于tomcat启动】
 *
 * @author yinxp@dist.com.cn
 * @date 2018/9/7
 */
public abstract class ContextPathUtil {


    /**
     * 获取上下文物理路径
     * @param relativePath
     * @return
     */
    public static String getContextPath(String relativePath,HttpServletRequest request){

        if (null == request) {
            return null;
        }
        String path = request.getServletContext().getRealPath(relativePath);
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    /**
     * 获取请求的父级URL
     * @param request
     * @return
     */
    public static  String getBaseURL(HttpServletRequest request) {

        if (null == request) {
            throw new RuntimeException("请求的request为空");
        }
        // 忽略默认端口80和443
        String port = ((80 ==  request.getServerPort() || 443 ==  request.getServerPort())? "" : (":" + request.getServerPort()));
        // 在反向代理服务器（如nginx配置了X-Forwarded-Scheme或者X-Forwarded-Proto），为了获取https
        String scheme = request.getHeader("X-Forwarded-Scheme");
        if(StringUtils.isEmpty(scheme)) {
            scheme = request.getHeader("X-Forwarded-Proto");
            if(StringUtils.isEmpty(scheme)) {
                return request.getScheme() + "://" + request.getServerName() + port + request.getContextPath();
            }
        }
        return scheme + "://" + request.getServerName() + port + request.getContextPath();
    }
}
