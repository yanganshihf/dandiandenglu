package com.example.demo.vo;

/**
 * SsoSession
 *
 * @date: 2019-10-07
 * @author: chy
 */
public class SsoSession {

    /**
     * 签名
     */
    private String sign;

    /**
     * 用户以及用户权限信息
     */
    private SsoUserInfo content;

    /**
     * 时间戳
     */
    private String timespan;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public SsoUserInfo getContent() {
        return content;
    }

    public void setContent(SsoUserInfo content) {
        this.content = content;
    }

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(String timespan) {
        this.timespan = timespan;
    }
}
