package com.baosight.wilp.ms.common.domain;

import java.io.Serializable;

/**
 * @description: 采集数据事件类
 * @author: panlingfeng
 * @createDate: 2021/8/5 5:56 下午
 */
public class EventDTO implements Serializable {
    private String sn; //网关设备SN
    private String nm; //网关设备名称
    private String dsc; //网关设备描述
    private Integer dom; //采集变量类型， 1 模拟量、 2 数字量、 3 信号量、 4 累积量
    private String ytag; //该记录关联的标签变量
    private String ts; //时间戳
    private Integer type; //事件类型，0 系统事件、1 实时报警、2 逻辑报警、3 IO操作响应、4 外挂模块自定义（比如脚本）、100 短信发送记录
    private Integer level; //级别
    private String oname; //原始采集变量名称
    private String odesc; //原始采集变量描述
    private String content; //事件描述文本
    private Integer avt; //报警数据类型 1-int,2-double
    private String avx; //报警值
    private Integer scd; //报警类型,用dom字段区分模拟量还是开关量
    private String szs; //报警源站
    private String szz; //报警原因

    public EventDTO() {
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public Integer getDom() {
        return dom;
    }

    public void setDom(Integer dom) {
        this.dom = dom;
    }

    public String getYtag() {
        return ytag;
    }

    public void setYtag(String ytag) {
        this.ytag = ytag;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOdesc() {
        return odesc;
    }

    public void setOdesc(String odesc) {
        this.odesc = odesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAvt() {
        return avt;
    }

    public void setAvt(Integer avt) {
        this.avt = avt;
    }

    public String getAvx() {
        return avx;
    }

    public void setAvx(String avx) {
        this.avx = avx;
    }

    public Integer getScd() {
        return scd;
    }

    public void setScd(Integer scd) {
        this.scd = scd;
    }

    public String getSzs() {
        return szs;
    }

    public void setSzs(String szs) {
        this.szs = szs;
    }

    public String getSzz() {
        return szz;
    }

    public void setSzz(String szz) {
        this.szz = szz;
    }
}
