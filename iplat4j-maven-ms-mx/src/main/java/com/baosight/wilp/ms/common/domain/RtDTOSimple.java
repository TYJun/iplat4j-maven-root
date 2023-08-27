package com.baosight.wilp.ms.common.domain;

import java.io.Serializable;

/**
 * @description: 数据推送接收接口包装
 * @author: panlingfeng
 * @createDate: 2021/8/5 2:42 下午
 */
public class RtDTOSimple implements Serializable, Cloneable {

    private String bsn; //网关设备SN
    private String bnm; //网关设备名称
    private String tnm; //标签名称路径
    private String tss; //时间换算后的整秒数
    private String tsm; //对应的毫秒数
    private Integer vt; //数据类型1-int 2-double 3-string
    private Object val; //数据
    private Integer vq; //质量戳 0-好数据,1-坏数据，255/256-初始

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

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public Integer getVq() {
        return vq;
    }

    public void setVq(Integer vq) {
        this.vq = vq;
    }
}
