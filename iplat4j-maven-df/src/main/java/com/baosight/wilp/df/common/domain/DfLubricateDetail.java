/**
* Generate time : 2022-05-20 16:36:09
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfLubricateDetail
* 
*/
public class DfLubricateDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String lubricateId = " ";		/* 润滑ID*/
    private String lubricateSpot = " ";		/* 润滑位置*/
    private String lubricateNo = " ";		/* 润滑单号*/
    private String fillOil = " ";		/* 注油量*/
    private String wasteOil = " ";		/* 废油排量*/
    private String oilType = " ";		/* 油脂类型*/
    private String oilWaterContent = " ";		/* 油脂含水量*/
    private String oilAcidContent = " ";		/* 油脂含酸量*/
    private String eachElementContent = " ";		/* 各元素含量*/
    private String nextOilTime = " ";		/* 下次软化油检测时间*/
    private String sortNo = " ";		/* 排序说明*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lubricateId");
        eiColumn.setDescName("润滑ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lubricateSpot");
        eiColumn.setDescName("润滑位置");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lubricateNo");
        eiColumn.setDescName("润滑单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fillOil");
        eiColumn.setDescName("注油量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wasteOil");
        eiColumn.setDescName("废油排量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("oilType");
        eiColumn.setDescName("油脂类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("oilWaterContent");
        eiColumn.setDescName("油脂含水量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("oilAcidContent");
        eiColumn.setDescName("油脂含酸量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("eachElementContent");
        eiColumn.setDescName("各元素含量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nextOilTime");
        eiColumn.setDescName("下次软化油检测时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortNo");
        eiColumn.setDescName("排序说明");
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


    }

    /**
     * the constructor
     */
    public DfLubricateDetail() {
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
     * get the lubricateId - 润滑ID
     * @return the lubricateId
     */
    public String getLubricateId() {
        return this.lubricateId;
    }

    /**
     * set the lubricateId - 润滑ID
     */
    public void setLubricateId(String lubricateId) {
        this.lubricateId = lubricateId;
    }

    /**
     * get the lubricateSpot - 润滑位置
     * @return the lubricateSpot
     */
    public String getLubricateSpot() {
        return this.lubricateSpot;
    }

    /**
     * set the lubricateSpot - 润滑位置
     */
    public void setLubricateSpot(String lubricateSpot) {
        this.lubricateSpot = lubricateSpot;
    }

    /**
     * get the lubricateNo - 润滑单号
     * @return the lubricateNo
     */
    public String getLubricateNo() {
        return this.lubricateNo;
    }

    /**
     * set the lubricateNo - 润滑单号
     */
    public void setLubricateNo(String lubricateNo) {
        this.lubricateNo = lubricateNo;
    }

    /**
     * get the fillOil - 注油量
     * @return the fillOil
     */
    public String getFillOil() {
        return this.fillOil;
    }

    /**
     * set the fillOil - 注油量
     */
    public void setFillOil(String fillOil) {
        this.fillOil = fillOil;
    }

    /**
     * get the wasteOil - 废油排量
     * @return the wasteOil
     */
    public String getWasteOil() {
        return this.wasteOil;
    }

    /**
     * set the wasteOil - 废油排量
     */
    public void setWasteOil(String wasteOil) {
        this.wasteOil = wasteOil;
    }

    /**
     * get the oilType - 油脂类型
     * @return the oilType
     */
    public String getOilType() {
        return this.oilType;
    }

    /**
     * set the oilType - 油脂类型
     */
    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    /**
     * get the oilWaterContent - 油脂含水量
     * @return the oilWaterContent
     */
    public String getOilWaterContent() {
        return this.oilWaterContent;
    }

    /**
     * set the oilWaterContent - 油脂含水量
     */
    public void setOilWaterContent(String oilWaterContent) {
        this.oilWaterContent = oilWaterContent;
    }

    /**
     * get the oilAcidContent - 油脂含酸量
     * @return the oilAcidContent
     */
    public String getOilAcidContent() {
        return this.oilAcidContent;
    }

    /**
     * set the oilAcidContent - 油脂含酸量
     */
    public void setOilAcidContent(String oilAcidContent) {
        this.oilAcidContent = oilAcidContent;
    }

    /**
     * get the eachElementContent - 各元素含量
     * @return the eachElementContent
     */
    public String getEachElementContent() {
        return this.eachElementContent;
    }

    /**
     * set the eachElementContent - 各元素含量
     */
    public void setEachElementContent(String eachElementContent) {
        this.eachElementContent = eachElementContent;
    }

    /**
     * get the nextOilTime - 下次软化油检测时间
     * @return the nextOilTime
     */
    public String getNextOilTime() {
        return this.nextOilTime;
    }

    /**
     * set the nextOilTime - 下次软化油检测时间
     */
    public void setNextOilTime(String nextOilTime) {
        this.nextOilTime = nextOilTime;
    }

    /**
     * get the sortNo - 排序说明
     * @return the sortNo
     */
    public String getSortNo() {
        return this.sortNo;
    }

    /**
     * set the sortNo - 排序说明
     */
    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setLubricateId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lubricateId")), lubricateId));
        setLubricateSpot(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lubricateSpot")), lubricateSpot));
        setLubricateNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lubricateNo")), lubricateNo));
        setFillOil(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fillOil")), fillOil));
        setWasteOil(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wasteOil")), wasteOil));
        setOilType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("oilType")), oilType));
        setOilWaterContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("oilWaterContent")), oilWaterContent));
        setOilAcidContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("oilAcidContent")), oilAcidContent));
        setEachElementContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("eachElementContent")), eachElementContent));
        setNextOilTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nextOilTime")), nextOilTime));
        setSortNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sortNo")), sortNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("lubricateId",StringUtils.toString(lubricateId, eiMetadata.getMeta("lubricateId")));
        map.put("lubricateSpot",StringUtils.toString(lubricateSpot, eiMetadata.getMeta("lubricateSpot")));
        map.put("lubricateNo",StringUtils.toString(lubricateNo, eiMetadata.getMeta("lubricateNo")));
        map.put("fillOil",StringUtils.toString(fillOil, eiMetadata.getMeta("fillOil")));
        map.put("wasteOil",StringUtils.toString(wasteOil, eiMetadata.getMeta("wasteOil")));
        map.put("oilType",StringUtils.toString(oilType, eiMetadata.getMeta("oilType")));
        map.put("oilWaterContent",StringUtils.toString(oilWaterContent, eiMetadata.getMeta("oilWaterContent")));
        map.put("oilAcidContent",StringUtils.toString(oilAcidContent, eiMetadata.getMeta("oilAcidContent")));
        map.put("eachElementContent",StringUtils.toString(eachElementContent, eiMetadata.getMeta("eachElementContent")));
        map.put("nextOilTime",StringUtils.toString(nextOilTime, eiMetadata.getMeta("nextOilTime")));
        map.put("sortNo",StringUtils.toString(sortNo, eiMetadata.getMeta("sortNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}