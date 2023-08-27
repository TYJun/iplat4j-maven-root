package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;


@JacksonXmlRootElement(localName = "xml")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WWReceive implements Serializable {
    /********************************************三方应用*****************************************/
    // 第三方应用的SuiteId
    @JacksonXmlProperty(localName = "SuiteId")
    private String SuiteId;
    // 推送消息类型
    @JacksonXmlProperty(localName = "InfoType")
    private String InfoType;
    // 时间戳
    @JacksonXmlProperty(localName = "TimeStamp")
    private Long TimeStamp;

    @JacksonXmlProperty(localName = "SuiteTicket")
    private String SuiteTicket;

    /********************************************代开发应用*****************************************/
    // 企业id
    @JacksonXmlProperty(localName = "ToUserName")
    private String ToUserName;
    // 员工id
    @JacksonXmlProperty(localName = "FromUserName")
    private String FromUserName;
    // 创建时间
    @JacksonXmlProperty(localName = "CreateTime")
    private Long CreateTime;
    // 消息类型
    @JacksonXmlProperty(localName = "MsgType")
    private String MsgType;
    // 应用id
    @JacksonXmlProperty(localName = "AgentID")
    private String AgentID;
    // 事件类型
    @JacksonXmlProperty(localName = "Event")
    private String Event;

    public String getSuiteId() {
        return SuiteId;
    }

    public void setSuiteId(String suiteId) {
        SuiteId = suiteId;
    }

    public String getInfoType() {
        return InfoType;
    }

    public void setInfoType(String infoType) {
        InfoType = infoType;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getAgentID() {
        return AgentID;
    }

    public void setAgentID(String agentID) {
        AgentID = agentID;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getSuiteTicket() {
        return SuiteTicket;
    }

    public void setSuiteTicket(String suiteTicket) {
        SuiteTicket = suiteTicket;
    }
}
