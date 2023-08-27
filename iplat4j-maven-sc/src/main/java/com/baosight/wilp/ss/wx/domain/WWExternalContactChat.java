package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class WWExternalContactChat extends WWContactEvent {
    // 群ID
    @JacksonXmlProperty(localName = "ChatId")
    private String chatId;
    // 变更详情。目前有以下几种：
    //add_member : 成员入群
    //del_member : 成员退群
    //change_owner : 群主变更
    //change_name : 群名变更
    //change_notice : 群公告变更
    @JacksonXmlProperty(localName = "UpdateDetail")
    private String updateDetail;
    // 当是成员入群时有值。表示成员的入群方式
    //0 - 由成员邀请入群（包括直接邀请入群和通过邀请链接入群）
    //3 - 通过扫描群二维码入群
    @JacksonXmlProperty(localName = "JoinScene")
    private int joinScene;
    // 当是成员退群时有值。表示成员的退群方式
    //0 - 自己退群
    //1 - 群主/群管理员移出
    @JacksonXmlProperty(localName = "QuitScene")
    private int quitScene;
    // 当是成员入群或退群时有值。表示成员变更数量
    @JacksonXmlProperty(localName = "MemChangeCnt")
    private int memChangeCnt;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUpdateDetail() {
        return updateDetail;
    }

    public void setUpdateDetail(String updateDetail) {
        this.updateDetail = updateDetail;
    }

    public int getJoinScene() {
        return joinScene;
    }

    public void setJoinScene(int joinScene) {
        this.joinScene = joinScene;
    }

    public int getQuitScene() {
        return quitScene;
    }

    public void setQuitScene(int quitScene) {
        this.quitScene = quitScene;
    }

    public int getMemChangeCnt() {
        return memChangeCnt;
    }

    public void setMemChangeCnt(int memChangeCnt) {
        this.memChangeCnt = memChangeCnt;
    }
}
