package com.ccc.sys.io.commons.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <a>Title:WebUtils</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 19:05
 * @Version 1.0.0
 */
public class WebUtils {

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequset() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        return getRequset().getSession();
    }
}
