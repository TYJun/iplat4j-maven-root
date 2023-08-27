package com.baosight.wilp.ms.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: 配置文件映射对象
 * @author: panlingfeng
 * @createDate: 2021/8/9 5:46 下午
 */
@Component
@PropertySource("classpath:influx-db.properties")
@ConfigurationProperties(prefix = "influx")
public class InfluxDBConfig {
    private String url; //地址
    private String username; //用户名
    private String password; //密码
    private String database; //数据库
    private String defaultPolicy; //默认数据保持策略

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDefaultPolicy() {
        return defaultPolicy;
    }

    public void setDefaultPolicy(String defaultPolicy) {
        this.defaultPolicy = defaultPolicy;
    }
}
