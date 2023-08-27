package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WWExternalContactUser extends WWContactEvent {
    // 企业服务人员的UserID
    @JacksonXmlProperty(localName = "UserID")
    private String userID;
    // 外部联系人的userid，注意不是企业成员的帐号
    @JacksonXmlProperty(localName = "ExternalUserID")
    private String externalUserID;
    // 添加此用户的「联系我」方式配置的state参数，可用于识别添加此用户的渠道
    @JacksonXmlProperty(localName = "State")
    private String state;
    // 欢迎语code，可用于发送欢迎语
    @JacksonXmlProperty(localName = "WelcomeCode")
    private String welcomeCode;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getExternalUserID() {
        return externalUserID;
    }

    public void setExternalUserID(String externalUserID) {
        this.externalUserID = externalUserID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWelcomeCode() {
        return welcomeCode;
    }

    public void setWelcomeCode(String welcomeCode) {
        this.welcomeCode = welcomeCode;
    }
}
