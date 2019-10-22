package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.config.SsoConfig;
import com.example.demo.service.StandardSsoService;
import com.example.demo.utils.CookieUtil;
import com.example.demo.utils.HttpUtil;
import com.example.demo.utils.Md5Util;
import com.example.demo.vo.MessageBack;
import com.example.demo.vo.SsoSession;
import com.example.demo.vo.SsoUserInfo;
import com.example.demo.vo.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * SSO服务
 *
 * @date: 2019-09-30
 * @author: chy
 */
@Service
public class StandardSsoServiceImpl implements StandardSsoService {

    @Autowired
    private SsoConfig ssoConfig;

    @Override
    public SsoSession getAuthByToken(HttpServletRequest request) {
        String appId = ssoConfig.getApplication().getAppId();
        String cookieName = ssoConfig.getSso().getTokenName();
        SsoSession session = null;
        // 获取Cookie
        String token = CookieUtil.getCookieValue(request, cookieName);
        if (token != null && token != "") {
            String url = ssoConfig.getSso().getGetAuthUrl();
            String query = String.format("appid=%s&token=%s", appId, token);
            String result = HttpUtil.sendGet(url, query);
            SsoSession auth = JSON.parseObject(result, SsoSession.class);
            if (auth != null) {
                //计算签名
                String sign = sign(auth.getContent(), auth.getTimespan());
                //接收到的数据未被篡改
                if (sign.equals(auth.getSign())) {
                    session = auth;
                }
            }
        }
        return session;
    }

    @Override
    public boolean valid(String token, String appId, String timespan, String sign) {
        String url = ssoConfig.getSso().getValidUrl();
        TreeMap<String, String> param = new TreeMap<>();
        param.put("token", token);
        param.put("appid", appId);
        param.put("sign", sign);
        param.put("timespan", timespan);

        String paramStr = HttpUtil.createFormToString(param);
        String result = HttpUtil.post(url, paramStr, "x-www-form-urlencoded");
        MessageBack back = JSON.parseObject(result, MessageBack.class);
        return back != null && back.getCode() == 0;
    }

    @Override
    public String sign(SsoUserInfo userAuth, String time) {
        TreeMap<String, String> param = new TreeMap<>();
        param.put("Id", userAuth.getId().toString());
        param.put("Gender", userAuth.getGender().toString());
        param.put("Phone", userAuth.getPhone());
        param.put("RealName", userAuth.getRealName());
        param.put("Tel", userAuth.getTel());
        param.put("UserName", userAuth.getUserName());
        param.put("Email", userAuth.getEmail());
        param.put("UserLv", userAuth.getUserLv().toString());
        param.put("ProvinceName", userAuth.getProvinceName());
        param.put("CityName", userAuth.getCityName());
        param.put("RegionName", userAuth.getRegionName());
        param.put("XZCode", userAuth.getxZCode().toString());
        param.put("UserStatus", userAuth.getUserStatus().toString());
        param.put("UserAppAuth", userAuth.getUserAppAuth().toString());
        param.put("UserServiceAuth", userAuth.getUserServiceAuth().toString());
        param.put("CompanyName", userAuth.getCompanyName());
        param.put("UserType", userAuth.getUserType().toString());

        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> item : param.entrySet()) {
            stringBuffer.append(String.format("%s=%s", item.getKey(), item.getValue()));
        }
        stringBuffer.append(time);
        String text = stringBuffer.toString();
        return Md5Util.md5(text);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String url = ssoConfig.getSso().getLogoutUrl();
        String cookieName = ssoConfig.getSso().getTokenName();
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie != null && cookieName.equals(cookie.getName())) {
                    token = cookie.getValue();
                    // 删除Cookie
                    cookie.setMaxAge(-1);
                    response.addCookie(cookie);
                }
            }
        }
        UserManager.CurrentUser = null;
        if (token != null && token != "") {
            String query = "token=" + token;
            HttpUtil.sendGet(url, query);
        }
    }
    
    @Override
    public void save(HttpServletResponse response, String token, String domain, String expiresAt) {//
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(expiresAt);
            String time = date.toString();
            String format = String.format("SSOTOKEN=%s;domain=%s;path=/;expires=%s",token,domain,time);
            System.out.println("format : "+format);
            response.setHeader("Set-Cookie",format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
