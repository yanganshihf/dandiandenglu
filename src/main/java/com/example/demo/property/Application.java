package com.example.demo.property;


/**
 * @date: 2019-09-30
 * @author: chy
 */
public class Application {

    private String appId;

    private String appName;

    private String logType;

    private String esLogUrl;

    private String receiveUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getEsLogUrl() {
        return esLogUrl;
    }

    public void setEsLogUrl(String esLogUrl) {
        this.esLogUrl = esLogUrl;
    }

    public String getReceiveUrl() {
        return receiveUrl;
    }

    public void setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
    }
}
