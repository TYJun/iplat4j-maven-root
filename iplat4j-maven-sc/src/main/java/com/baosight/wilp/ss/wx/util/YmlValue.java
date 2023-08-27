package com.baosight.wilp.ss.wx.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Classname PropertiesValue
 * @Description
 * @Date 2022/9/17 14:50
 * @keke
 */
@Configuration
public class YmlValue {
    @Value("${wework.corp.id:AdOrdmAikeMz}")
    private String pCorpID;
    // 数据及指令回调
    @Value("${wework.callback.data.token:AdOrdmAikeMz}")
    private String pDataToken;
    @Value("${wework.callback.data.encoding-aes-key:HHORCgKFXJV3BWlHAiydr2qID5dGPlf5SIS6icQAehR}")
    private String pDataEncodingAESKey;

    public static String corpID;
    public static String dataToken;
    public static String dataEncodingAESKey;

    @PostConstruct
    public void init() {
        corpID = this.pCorpID;
        dataToken = this.pDataToken;
        dataEncodingAESKey = this.pDataEncodingAESKey;
    }
}
