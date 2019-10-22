package com.example.demo.controller;

import com.example.demo.service.StandardSsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权控制器
 *
 * @date: 2019-10-07
 * @author: chy
 */
@Controller
public class AccountController {

    @Autowired
    private StandardSsoService standardSsoService;

    /**
     * 接收授权
     *
     * @param token
     * @param domain
     * @param expiresAt
     * @param callback
     * @param response
     */
    @RequestMapping(value = "/Account/Receive")
    public void receive(String token, String domain, String expiresAt, String callback, HttpServletResponse response) {
        try {
            standardSsoService.save(response, token, domain, expiresAt);
            response.getWriter().print(callback + "('SUCCESS')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * 退出登录
     *
     * @param request
     */
    @RequestMapping(value = "/account/logout")
    @ResponseBody
    public void Logout(HttpServletRequest request, HttpServletResponse response) {
        standardSsoService.logout(request, response);
    }
}
