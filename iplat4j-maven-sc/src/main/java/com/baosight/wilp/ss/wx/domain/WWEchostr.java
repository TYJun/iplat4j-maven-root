package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

@JacksonXmlRootElement(localName = "xml")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WWEchostr implements Serializable {
    // 第三方应用的SuiteId或者企业的corpId
    @JacksonXmlProperty(localName = "ToUserName")
    private String ToUserName;
    // 待解密数据
    @JacksonXmlProperty(localName = "Encrypt")
    private String Encrypt;
    // 应用id
    @JacksonXmlProperty(localName = "AgentID")
    private Long AgentID;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getEncrypt() {
        return Encrypt;
    }

    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }

    public Long getAgentID() {
        return AgentID;
    }

    public void setAgentID(Long agentID) {
        AgentID = agentID;
    }
}
