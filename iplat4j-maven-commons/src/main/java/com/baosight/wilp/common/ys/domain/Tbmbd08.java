/**
* Generate time : 2021-01-27 16:18:37
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* Tbmbd08
* 
*/
public class Tbmbd08 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String achiveFlag = " ";		/* 归档标记*/
    private String matNum = " ";		/* 物资编码*/
    private String matName = " ";		/* 物资名称*/
    private String matClassTypeNum = " ";		/* 物资大类编码*/
    private String matClassTypeName = " ";		/* 物资大类名称*/
    private String surpNum = " ";		/* 供应商编码*/
    private Double price = new Double(0);		/* 最近订购单价*/
    private String matTypeNum = " ";		/* 物资分类编码*/
    private String matTypeName = " ";		/* 物资分类名称*/
    private String matModel = " ";		/* 物资型号*/
    private String matSpec = " ";		/* 物资规格*/
    private String unit = " ";		/* 计量单位编码*/
    private String unitName = " ";		/* 计量单位名称*/
    private String parentId = " ";		/* 上级ID*/
    private Double rate = new Double(0);		/* 系数*/
    private String picNum = " ";		/* 图号*/
    private String matMaker = " ";		/* 制造商*/
    private Double magnification = new Double(0);		/* 倍率*/
    private String barCode = " ";		/* 条形码*/
    private String jpMatName = " ";		/* 物资名简拼*/
    private String materialtype = " ";		
    private String remark = " ";		/* 备注*/
    private String stopFlag = " ";		
    private String typeFlag = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("achiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matNum");
        eiColumn.setDescName("物资编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matName");
        eiColumn.setDescName("物资名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matClassTypeNum");
        eiColumn.setDescName("物资大类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matClassTypeName");
        eiColumn.setDescName("物资大类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surpNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("price");
        eiColumn.setDescName("最近订购单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeNum");
        eiColumn.setDescName("物资分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matModel");
        eiColumn.setDescName("物资型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matSpec");
        eiColumn.setDescName("物资规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName("计量单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("上级ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rate");
        eiColumn.setDescName("系数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("picNum");
        eiColumn.setDescName("图号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matMaker");
        eiColumn.setDescName("制造商");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("magnification");
        eiColumn.setDescName("倍率");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("barCode");
        eiColumn.setDescName("条形码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jpMatName");
        eiColumn.setDescName("物资名简拼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("materialtype");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stopFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tbmbd08() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the achiveFlag - 归档标记
     * @return the achiveFlag
     */
    public String getAchiveFlag() {
        return this.achiveFlag;
    }

    /**
     * set the achiveFlag - 归档标记
     */
    public void setAchiveFlag(String achiveFlag) {
        this.achiveFlag = achiveFlag;
    }

    /**
     * get the matNum - 物资编码
     * @return the matNum
     */
    public String getMatNum() {
        return this.matNum;
    }

    /**
     * set the matNum - 物资编码
     */
    public void setMatNum(String matNum) {
        this.matNum = matNum;
    }

    /**
     * get the matName - 物资名称
     * @return the matName
     */
    public String getMatName() {
        return this.matName;
    }

    /**
     * set the matName - 物资名称
     */
    public void setMatName(String matName) {
        this.matName = matName;
    }

    /**
     * get the matClassTypeNum - 物资大类编码
     * @return the matClassTypeNum
     */
    public String getMatClassTypeNum() {
        return this.matClassTypeNum;
    }

    /**
     * set the matClassTypeNum - 物资大类编码
     */
    public void setMatClassTypeNum(String matClassTypeNum) {
        this.matClassTypeNum = matClassTypeNum;
    }

    /**
     * get the matClassTypeName - 物资大类名称
     * @return the matClassTypeName
     */
    public String getMatClassTypeName() {
        return this.matClassTypeName;
    }

    /**
     * set the matClassTypeName - 物资大类名称
     */
    public void setMatClassTypeName(String matClassTypeName) {
        this.matClassTypeName = matClassTypeName;
    }

    /**
     * get the surpNum - 供应商编码
     * @return the surpNum
     */
    public String getSurpNum() {
        return this.surpNum;
    }

    /**
     * set the surpNum - 供应商编码
     */
    public void setSurpNum(String surpNum) {
        this.surpNum = surpNum;
    }

    /**
     * get the price - 最近订购单价
     * @return the price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * set the price - 最近订购单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * get the matTypeNum - 物资分类编码
     * @return the matTypeNum
     */
    public String getMatTypeNum() {
        return this.matTypeNum;
    }

    /**
     * set the matTypeNum - 物资分类编码
     */
    public void setMatTypeNum(String matTypeNum) {
        this.matTypeNum = matTypeNum;
    }

    /**
     * get the matTypeName - 物资分类名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the matModel - 物资型号
     * @return the matModel
     */
    public String getMatModel() {
        return this.matModel;
    }

    /**
     * set the matModel - 物资型号
     */
    public void setMatModel(String matModel) {
        this.matModel = matModel;
    }

    /**
     * get the matSpec - 物资规格
     * @return the matSpec
     */
    public String getMatSpec() {
        return this.matSpec;
    }

    /**
     * set the matSpec - 物资规格
     */
    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    /**
     * get the unit - 计量单位编码
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit - 计量单位编码
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the unitName - 计量单位名称
     * @return the unitName
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * set the unitName - 计量单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * get the parentId - 上级ID
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId - 上级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the rate - 系数
     * @return the rate
     */
    public Double getRate() {
        return this.rate;
    }

    /**
     * set the rate - 系数
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**
     * get the picNum - 图号
     * @return the picNum
     */
    public String getPicNum() {
        return this.picNum;
    }

    /**
     * set the picNum - 图号
     */
    public void setPicNum(String picNum) {
        this.picNum = picNum;
    }

    /**
     * get the matMaker - 制造商
     * @return the matMaker
     */
    public String getMatMaker() {
        return this.matMaker;
    }

    /**
     * set the matMaker - 制造商
     */
    public void setMatMaker(String matMaker) {
        this.matMaker = matMaker;
    }

    /**
     * get the magnification - 倍率
     * @return the magnification
     */
    public Double getMagnification() {
        return this.magnification;
    }

    /**
     * set the magnification - 倍率
     */
    public void setMagnification(Double magnification) {
        this.magnification = magnification;
    }

    /**
     * get the barCode - 条形码
     * @return the barCode
     */
    public String getBarCode() {
        return this.barCode;
    }

    /**
     * set the barCode - 条形码
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    /**
     * get the jpMatName - 物资名简拼
     * @return the jpMatName
     */
    public String getJpMatName() {
        return this.jpMatName;
    }

    /**
     * set the jpMatName - 物资名简拼
     */
    public void setJpMatName(String jpMatName) {
        this.jpMatName = jpMatName;
    }

    /**
     * get the materialtype 
     * @return the materialtype
     */
    public String getMaterialtype() {
        return this.materialtype;
    }

    /**
     * set the materialtype 
     */
    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the stopFlag 
     * @return the stopFlag
     */
    public String getStopFlag() {
        return this.stopFlag;
    }

    /**
     * set the stopFlag 
     */
    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    /**
     * get the typeFlag 
     * @return the typeFlag
     */
    public String getTypeFlag() {
        return this.typeFlag;
    }

    /**
     * set the typeFlag 
     */
    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setAchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("achiveFlag")), achiveFlag));
        setMatNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matNum")), matNum));
        setMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matName")), matName));
        setMatClassTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matClassTypeNum")), matClassTypeNum));
        setMatClassTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matClassTypeName")), matClassTypeName));
        setSurpNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("surpNum")), surpNum));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setMatTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeNum")), matTypeNum));
        setMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeName")), matTypeName));
        setMatModel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matModel")), matModel));
        setMatSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matSpec")), matSpec));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setRate(NumberUtils.toDouble(StringUtils.toString(map.get("rate")), rate));
        setPicNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("picNum")), picNum));
        setMatMaker(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matMaker")), matMaker));
        setMagnification(NumberUtils.toDouble(StringUtils.toString(map.get("magnification")), magnification));
        setBarCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("barCode")), barCode));
        setJpMatName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jpMatName")), jpMatName));
        setMaterialtype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("materialtype")), materialtype));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setStopFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stopFlag")), stopFlag));
        setTypeFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeFlag")), typeFlag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("achiveFlag",StringUtils.toString(achiveFlag, eiMetadata.getMeta("achiveFlag")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matClassTypeNum",StringUtils.toString(matClassTypeNum, eiMetadata.getMeta("matClassTypeNum")));
        map.put("matClassTypeName",StringUtils.toString(matClassTypeName, eiMetadata.getMeta("matClassTypeName")));
        map.put("surpNum",StringUtils.toString(surpNum, eiMetadata.getMeta("surpNum")));
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("matTypeNum",StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("rate",StringUtils.toString(rate, eiMetadata.getMeta("rate")));
        map.put("picNum",StringUtils.toString(picNum, eiMetadata.getMeta("picNum")));
        map.put("matMaker",StringUtils.toString(matMaker, eiMetadata.getMeta("matMaker")));
        map.put("magnification",StringUtils.toString(magnification, eiMetadata.getMeta("magnification")));
        map.put("barCode",StringUtils.toString(barCode, eiMetadata.getMeta("barCode")));
        map.put("jpMatName",StringUtils.toString(jpMatName, eiMetadata.getMeta("jpMatName")));
        map.put("materialtype",StringUtils.toString(materialtype, eiMetadata.getMeta("materialtype")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("stopFlag",StringUtils.toString(stopFlag, eiMetadata.getMeta("stopFlag")));
        map.put("typeFlag",StringUtils.toString(typeFlag, eiMetadata.getMeta("typeFlag")));
        return map;
    }
}