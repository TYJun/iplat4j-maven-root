package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WWTag extends WWContactEvent {
    // 标签Id
    @JacksonXmlProperty(localName = "TagId")
    private int TagId;
    // 标签中新增的成员userid列表，用逗号分隔
    @JacksonXmlProperty(localName = "AddUserItems")
    private String AddUserItems;
    // 标签中删除的成员userid列表，用逗号分隔
    @JacksonXmlProperty(localName = "DelUserItems")
    private String DelUserItems;
    // 标签中新增的部门id列表，用逗号分隔
    @JacksonXmlProperty(localName = "AddPartyItems")
    private String AddPartyItems;
    // 标签中删除的部门id列表，用逗号分隔
    @JacksonXmlProperty(localName = "DelPartyItems")
    private String DelPartyItems;

    public int getTagId() {
        return TagId;
    }

    public void setTagId(int tagId) {
        TagId = tagId;
    }

    public String getAddUserItems() {
        return AddUserItems;
    }

    public void setAddUserItems(String addUserItems) {
        AddUserItems = addUserItems;
    }

    public String getDelUserItems() {
        return DelUserItems;
    }

    public void setDelUserItems(String delUserItems) {
        DelUserItems = delUserItems;
    }

    public String getAddPartyItems() {
        return AddPartyItems;
    }

    public void setAddPartyItems(String addPartyItems) {
        AddPartyItems = addPartyItems;
    }

    public String getDelPartyItems() {
        return DelPartyItems;
    }

    public void setDelPartyItems(String delPartyItems) {
        DelPartyItems = delPartyItems;
    }
}
