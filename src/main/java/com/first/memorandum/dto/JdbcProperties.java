package com.first.memorandum.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jdbc参数
 */
@ConfigurationProperties(prefix = "spring.datasource")
public class JdbcProperties {

    private String url;

    private String driverClassName;

    private String userName;

    private String password;

    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
