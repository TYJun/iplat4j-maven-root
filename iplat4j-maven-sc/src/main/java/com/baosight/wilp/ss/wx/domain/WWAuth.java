package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WWAuth extends WWReceive {
    // 授权的auth_code,最长为512字节。用于获取企业的永久授权码。
    @JacksonXmlProperty(localName = "AuthCode")
    private String AuthCode;
    // authCode有效期，10分钟
    private final int ExpiresIn = 10 * 60;
    // 授权方的corpid
    @JacksonXmlProperty(localName = "AuthCorpId")
    private String AuthCorpId;

    public String getAuthCode() {
        return AuthCode;
    }

    public void setAuthCode(String authCode) {
        AuthCode = authCode;
    }

    public int getExpiresIn() {
        return ExpiresIn;
    }

    public String getAuthCorpId() {
        return AuthCorpId;
    }

    public void setAuthCorpId(String authCorpId) {
        AuthCorpId = authCorpId;
    }
}
