package com.baosight.wilp.mx.ps.domain;

import java.io.Serializable;

/**
 * websocket 客户端传来的数据模型
 *
 * @author: panlingfeng
 * @createDate: 2021/8/16 3:14 下午
 */
public class Tag implements Serializable {

    private String name; //标签
    private Double lower; //低报警
    private Double upper; //高报警
    private Double lowerLower; //低低报警
    private Double upperUpper; //高高报警
    private String areaId; //区域编号
    private String area; //区域
    private String device; //设备
    private String label; //标签别名
    private String classify; //分类

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLower() {
        return lower;
    }

    public void setLower(Double lower) {
        this.lower = lower;
    }

    public Double getUpper() {
        return upper;
    }

    public void setUpper(Double upper) {
        this.upper = upper;
    }

    public Double getLowerLower() {
        return lowerLower;
    }

    public void setLowerLower(Double lowerLower) {
        this.lowerLower = lowerLower;
    }

    public Double getUpperUpper() {
        return upperUpper;
    }

    public void setUpperUpper(Double upperUpper) {
        this.upperUpper = upperUpper;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
