package com.example.demo.property;


/**
 * @date: 2019-09-30
 * @author: chy
 */
public class Sso {

    private String loginUrl;

    private String validUrl;

    private String logoutUrl;

    private String getAuthUrl;

    private String tokenName;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getValidUrl() {
        return validUrl;
    }

    public void setValidUrl(String validUrl) {
        this.validUrl = validUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getGetAuthUrl() {
        return getAuthUrl;
    }

    public void setGetAuthUrl(String getAuthUrl) {
        this.getAuthUrl = getAuthUrl;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
