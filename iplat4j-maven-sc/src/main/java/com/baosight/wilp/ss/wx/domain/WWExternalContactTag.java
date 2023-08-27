package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WWExternalContactTag extends WWContactEvent {
    @JacksonXmlProperty(localName = "Id")
    private String id;
    @JacksonXmlProperty(localName = "TagType")
    private String tagType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
