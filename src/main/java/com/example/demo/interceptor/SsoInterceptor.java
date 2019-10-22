package com.example.demo.interceptor;

import com.example.demo.annotation.SsoAuth;
import com.example.demo.config.SsoConfig;
import com.example.demo.service.StandardSsoService;
import com.example.demo.utils.CookieUtil;
import com.example.demo.vo.SsoSession;
import com.example.demo.vo.UserManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**
 * 自定义拦截器
 */
public class SsoInterceptor implements HandlerInterceptor {

    private SsoConfig ssoConfig;

    private StandardSsoService standardSsoService;

    public SsoInterceptor(SsoConfig ssoConfig, StandardSsoService standardSsoService) {
        this.ssoConfig = ssoConfig;
        this.standardSsoService = standardSsoService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 权限校验
        String returnUrl =  request.getRequestURL().toString();
        if (request.getQueryString() != null && request.getQueryString() != "") {
            returnUrl = returnUrl + "?" + request.getQueryString();
        }

        returnUrl = URLEncoder.encode(returnUrl, "UTF-8");
        String appId = ssoConfig.getApplication().getAppId();
        String loginUrl = ssoConfig.getSso().getLoginUrl();
        String receiveUrl = URLEncoder.encode(ssoConfig.getApplication().getReceiveUrl(), "UTF-8");
        String ssoUrl = String.format("%s?appid=%s&returnUrl=%s&receiveUrl=%s",
                loginUrl, appId, returnUrl, receiveUrl);
        try {
            //验证是否有该注解
//            SsoAuth ssoAuth = method.getAnnotation(SsoAuth.class);
//            if (ssoAuth != null) {
                // 获取Cookie
                String token = CookieUtil.getCookieValue(request, ssoConfig.getSso().getTokenName());
                if (token != null && token != "") {
                    // 获取Token存储用户信息
                    if (UserManager.CurrentUser != null) {
                        //生成时间戳
                        String time = System.currentTimeMillis() + "";
                        //计算签名
                        String sign = standardSsoService.sign(UserManager.CurrentUser, time);
                        //校验登陆信息是否有效
                        boolean success = standardSsoService.valid(token, appId, time, sign);
                        //校验未通过
                        if (!success) {
                            UserManager.CurrentUser = null;
                            response.sendRedirect(ssoUrl);
                            return false;
                        }
                    } else {
                        SsoSession session = standardSsoService.getAuthByToken(request);
                        //获取成功且用户数据有效
                        if (session != null && session.getContent() != null) {
                            UserManager.CurrentUser = session.getContent();
                        } else {
                            response.sendRedirect(ssoUrl);
                            return false;
                        }
                    }
                } else {
                    // 跳转登录
                    response.sendRedirect(ssoUrl);
                    return false;
                }
//        }
        } catch (Exception ex) {
            response.sendRedirect(ssoUrl);
            return false;
        }
        return true;
    }


}
