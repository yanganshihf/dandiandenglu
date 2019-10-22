package com.example.demo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @date: 2019-10-07
 * @author: chy
 */
public class CookieUtil {

    /**
     * 获取Cookie
     *
     * @param request    请求
     * @param cookieName Cookie名称
     * @return Cookie对象
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie != null && cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 获取Cookie
     *
     * @param request    请求
     * @param cookieName Cookie名称
     * @return Cookie对象
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie != null && cookie.getValue() != null && cookie.getValue() != "") {
            return cookie.getValue();
        }
        return null;
    }
}
