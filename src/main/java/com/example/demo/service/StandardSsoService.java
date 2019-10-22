package com.example.demo.service;

import com.example.demo.vo.SsoSession;
import com.example.demo.vo.SsoUserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SSO服务
 *
 * @date: 2019-09-30
 * @author: chy
 */
public interface StandardSsoService {

    /**
     * 获取用户授权信息
     *
     * @param request 请求信息
     * @return
     */
    SsoSession getAuthByToken(HttpServletRequest request);

    /**
     * 校验登录信息
     *
     * @param token    访问令牌
     * @param appId    应用Id
     * @param timespan 时间戳
     * @param sign     签名信息
     * @return 是否有效
     */
    boolean valid(String token, String appId, String timespan, String sign);

    /**
     * 计算签名
     *
     * @param userAuth 用户以及用户权限信息
     * @param time     时间戳
     * @return 签名值
     */
    String sign(SsoUserInfo userAuth, String time);

    /**
     * 退出登录
     *
     * @param request 请求信息
     */
    void logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 保存访问令牌
     *
     * @param response  响应信息
     * @param token     访问令牌
     * @param domain    域
     * @param expiresAt 过期时间
     */
    void save(HttpServletResponse response, String token, String domain, String expiresAt);
}
