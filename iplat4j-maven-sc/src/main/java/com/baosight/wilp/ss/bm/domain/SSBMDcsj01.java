/**
* Generate time : 2021-05-24 20:36:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SSBMDCSJ01
* sc_canteen_times
*/
public class SSBMDcsj01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String canteenNum = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private String mealNum = " ";		/* 餐次编码*/
    private String mealName = " ";		/* 餐次名称*/
    private String todayOrderTime = " ";		/* 当天订餐时间*/
    private String tomorrowOrderTime = " ";		/* 明天订餐时间*/
    private Integer advanceTime = new Integer(0);		/* 送餐时间提前量，分钟为单位*/
    private String sendTime = " ";		/* 送达时间以逗号分割，如 07:00,08:00,09:00*/
    private String todayMealTypeCode = " ";		
    private String remark = " ";		/* 备注*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName("餐次编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealName");
        eiColumn.setDescName("餐次名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("todayOrderTime");
        eiColumn.setDescName("当天订餐时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tomorrowOrderTime");
        eiColumn.setDescName("明天订餐时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("advanceTime");
        eiColumn.setDescName("送餐时间提前量，分钟为单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sendTime");
        eiColumn.setDescName("送达时间以逗号分割，如 07:00,08:00,09:00");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("todayMealTypeCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMDcsj01() {
        initMetaData();
    }


    public SSBMDcsj01(String mealTime, String sendTime) {
    	super();
		this.mealNum = mealTime;
		this.sendTime = sendTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
     * get the canteenNum - 食堂编码
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum - 食堂编码
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    /**
     * get the canteenName - 食堂名称
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName - 食堂名称
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    /**
     * get the mealNum - 餐次编码
     * @return the mealNum
     */
    public String getMealNum() {
        return this.mealNum;
    }

    /**
     * set the mealNum - 餐次编码
     */
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }

    /**
     * get the mealName - 餐次名称
     * @return the mealName
     */
    public String getMealName() {
        return this.mealName;
    }

    /**
     * set the mealName - 餐次名称
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    /**
     * get the todayOrderTime - 当天订餐时间
     * @return the todayOrderTime
     */
    public String getTodayOrderTime() {
        return this.todayOrderTime;
    }

    /**
     * set the todayOrderTime - 当天订餐时间
     */
    public void setTodayOrderTime(String todayOrderTime) {
        this.todayOrderTime = todayOrderTime;
    }

    /**
     * get the tomorrowOrderTime - 明天订餐时间
     * @return the tomorrowOrderTime
     */
    public String getTomorrowOrderTime() {
        return this.tomorrowOrderTime;
    }

    /**
     * set the tomorrowOrderTime - 明天订餐时间
     */
    public void setTomorrowOrderTime(String tomorrowOrderTime) {
        this.tomorrowOrderTime = tomorrowOrderTime;
    }

    /**
     * get the advanceTime - 送餐时间提前量，分钟为单位
     * @return the advanceTime
     */
    public Integer getAdvanceTime() {
        return this.advanceTime;
    }

    /**
     * set the advanceTime - 送餐时间提前量，分钟为单位
     */
    public void setAdvanceTime(Integer advanceTime) {
        this.advanceTime = advanceTime;
    }

    /**
     * get the sendTime - 送达时间以逗号分割，如 07:00,08:00,09:00
     * @return the sendTime
     */
    public String getSendTime() {
        return this.sendTime;
    }

    /**
     * set the sendTime - 送达时间以逗号分割，如 07:00,08:00,09:00
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * get the todayMealTypeCode 
     * @return the todayMealTypeCode
     */
    public String getTodayMealTypeCode() {
        return this.todayMealTypeCode;
    }

    /**
     * set the todayMealTypeCode 
     */
    public void setTodayMealTypeCode(String todayMealTypeCode) {
        this.todayMealTypeCode = todayMealTypeCode;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setMealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealName")), mealName));
        setTodayOrderTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("todayOrderTime")), todayOrderTime));
        setTomorrowOrderTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tomorrowOrderTime")), tomorrowOrderTime));
        setAdvanceTime(NumberUtils.toInteger(StringUtils.toString(map.get("advanceTime")), advanceTime));
        setSendTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sendTime")), sendTime));
        setTodayMealTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("todayMealTypeCode")), todayMealTypeCode));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("mealName",StringUtils.toString(mealName, eiMetadata.getMeta("mealName")));
        map.put("todayOrderTime",StringUtils.toString(todayOrderTime, eiMetadata.getMeta("todayOrderTime")));
        map.put("tomorrowOrderTime",StringUtils.toString(tomorrowOrderTime, eiMetadata.getMeta("tomorrowOrderTime")));
        map.put("advanceTime",StringUtils.toString(advanceTime, eiMetadata.getMeta("advanceTime")));
        map.put("sendTime",StringUtils.toString(sendTime, eiMetadata.getMeta("sendTime")));
        map.put("todayMealTypeCode",StringUtils.toString(todayMealTypeCode, eiMetadata.getMeta("todayMealTypeCode")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }
}