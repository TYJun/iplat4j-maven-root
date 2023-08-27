package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WWSuite extends WWReceive {
    // Ticket内容，最长为512字节
    @JacksonXmlProperty(localName = "SuiteTicket")
    private String SuiteTicket;

    public String getSuiteTicket() {
        return SuiteTicket;
    }

    public void setSuiteTicket(String suiteTicket) {
        SuiteTicket = suiteTicket;
    }
}
