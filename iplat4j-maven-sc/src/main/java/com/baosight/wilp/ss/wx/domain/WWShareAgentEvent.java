package com.baosight.wilp.ss.wx.domain;


public class WWShareAgentEvent extends WWReceive {
    // 第三方应用appid
    private String AppId;
    // 上级企业corpid
    private String CorpId;
    // 上级企业应用id
    private String AgentId;

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }
}
