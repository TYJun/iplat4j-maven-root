package com.baosight.wilp.ms.common.domain;


import java.io.Serializable;

/**
 * @description: 采集数据属性类
 * @author: panlingfeng
 * @createDate: 2021/8/5 5:09 下午
 */
public class AttrDTO implements Serializable {
    private String name;
    private String desc;
    private Integer vt;
    private Integer tss;
    private Integer tsm;
    private String val;

    public AttrDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getVt() {
        return vt;
    }

    public void setVt(Integer vt) {
        this.vt = vt;
    }

    public Integer getTss() {
        return tss;
    }

    public void setTss(Integer tss) {
        this.tss = tss;
    }

    public Integer getTsm() {
        return tsm;
    }

    public void setTsm(Integer tsm) {
        this.tsm = tsm;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
