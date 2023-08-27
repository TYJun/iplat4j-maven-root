package com.baosight.wilp.ms.common.domain;

import java.io.Serializable;

/**
 * @description: 数据推送接收接口
 * @author: panlingfeng
 * @createDate: 2021/8/5 2:42 下午
 */
public class RtDTO implements Serializable, Cloneable {
    private String bsn; //网关设备SN
    private String bnm; //网关设备名称
    private String tnm; //标签名称路径
    private String tds; //标签描述路径
    private Integer hid; //全局句柄，可在点表里获取,该参数为动态参数，请慎用。
    private Integer iid; //网关设备内部的唯一编号,每次工程配置发生变化，会导致该句柄值变化，如果使用该参数做索引，请注意和点表配合使用.
    private String tss; //时间换算后的整秒数
    private String tsm; //对应的毫秒数
    private Integer vt; //数据类型1-int 2-double 3-string
    private Double val; //数据
    private Integer vq; //质量戳 0-好数据,1-坏数据，255/256-初始

    // VO
    private String grade; //报警等级
    private String valText; //自定义实时值显示

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getBnm() {
        return bnm;
    }

    public void setBnm(String bnm) {
        this.bnm = bnm;
    }

    public String getTnm() {
        return tnm;
    }

    public void setTnm(String tnm) {
        this.tnm = tnm;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getTss() {
        return tss;
    }

    public void setTss(String tss) {
        this.tss = tss;
    }

    public String getTsm() {
        return tsm;
    }

    public void setTsm(String tsm) {
        this.tsm = tsm;
    }

    public Integer getVt() {
        return vt;
    }

    public void setVt(Integer vt) {
        this.vt = vt;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public Integer getVq() {
        return vq;
    }

    public void setVq(Integer vq) {
        this.vq = vq;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getValText() {
        return valText;
    }

    public void setValText(String valText) {
        this.valText = valText;
    }

    @Override
    public RtDTO clone() {
        RtDTO rtDTO = null;
        try {
            rtDTO = (RtDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return rtDTO;
    }
}
