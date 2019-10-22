package com.example.demo.config;

import com.example.demo.property.Application;
import com.example.demo.property.ConnectionStrings;
import com.example.demo.property.Logging;
import com.example.demo.property.Sso;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Sso鉴权配置
 *
 * @date: 2019-09-30
 * @author: chy
 */
@Configuration
@Component
@ConfigurationProperties(prefix = "sso-config")
public class SsoConfig {

    private ConnectionStrings connectionStrings;

    private Logging logging;

    private Application application;

    private String allowedHosts;

    private Sso sso;

    public ConnectionStrings getConnectionStrings() {
        return connectionStrings;
    }

    public void setConnectionStrings(ConnectionStrings connectionStrings) {
        this.connectionStrings = connectionStrings;
    }

    public Logging getLogging() {
        return logging;
    }

    public void setLogging(Logging logging) {
        this.logging = logging;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getAllowedHosts() {
        return allowedHosts;
    }

    public void setAllowedHosts(String allowedHosts) {
        this.allowedHosts = allowedHosts;
    }

    public Sso getSso() {
        return sso;
    }

    public void setSso(Sso sso) {
        this.sso = sso;
    }
}


