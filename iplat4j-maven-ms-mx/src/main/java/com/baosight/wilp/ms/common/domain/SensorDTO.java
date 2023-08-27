package com.baosight.wilp.ms.common.domain;


import java.io.Serializable;

/**
 * @description: 采集数据头属性类
 * @author: panlingfeng
 * @createDate: 2021/8/5 5:10 下午
 */
public class SensorDTO implements Serializable {
    private String name; //标签名称
    private Integer handle; //标签symgrid句柄
    private Integer iid; //标签symlink网关句柄
    private String desc; //标签名称
    private Integer dom; //采集变量类型， 1 模拟量、 2 数字量、 3 信号量、 4 累积量
    private Integer hsf;

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getDom() {
        return dom;
    }

    public void setDom(Integer dom) {
        this.dom = dom;
    }

    public Integer getHsf() {
        return hsf;
    }

    public void setHsf(Integer hsf) {
        this.hsf = hsf;
    }
}
