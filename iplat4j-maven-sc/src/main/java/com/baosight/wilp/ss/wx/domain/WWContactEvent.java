package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WWContactEvent extends WWReceive {
    // 授权企业的CorpID
    @JacksonXmlProperty(localName = "AuthCorpId")
    private String AuthCorpId;
    // 事件类型
    @JacksonXmlProperty(localName = "ChangeType")
    private String ChangeType;

    public String getAuthCorpId() {
        return AuthCorpId;
    }

    public void setAuthCorpId(String authCorpId) {
        AuthCorpId = authCorpId;
    }

    public String getChangeType() {
        return ChangeType;
    }

    public void setChangeType(String changeType) {
        ChangeType = changeType;
    }
}
