/**
* Generate time : 2021-03-05 15:30:33
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
* SHCL01
* sc_order_type
*/
public class SSBMCpfl01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String mealTypeNum = " ";		/* 餐类编码*/
    private String mealTypeName = " ";		/* 餐类名称*/
    private String spuerMealTypeNum = " ";		/* 上次餐类编码*/
    private String spuerMealTypeName = " ";		/* 上级餐类名称*/
    private String memo = " ";		
    private String recCreateTime = " ";		/* 创建时间*/
    private String recCreator = " ";		/* 创建人*/
    private String statusCode = " ";		
    private Integer sortNo = new Integer(0);		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealTypeNum");
        eiColumn.setDescName("餐类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealTypeName");
        eiColumn.setDescName("餐类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spuerMealTypeNum");
        eiColumn.setDescName("上次餐类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spuerMealTypeName");
        eiColumn.setDescName("上级餐类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMCpfl01() {
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
     * get the mealTypeNum - 餐类编码
     * @return the mealTypeNum
     */
    public String getMealTypeNum() {
        return this.mealTypeNum;
    }

    /**
     * set the mealTypeNum - 餐类编码
     */
    public void setMealTypeNum(String mealTypeNum) {
        this.mealTypeNum = mealTypeNum;
    }

    /**
     * get the mealTypeName - 餐类名称
     * @return the mealTypeName
     */
    public String getMealTypeName() {
        return this.mealTypeName;
    }

    /**
     * set the mealTypeName - 餐类名称
     */
    public void setMealTypeName(String mealTypeName) {
        this.mealTypeName = mealTypeName;
    }

    /**
     * get the spuerMealTypeNum - 上次餐类编码
     * @return the spuerMealTypeNum
     */
    public String getSpuerMealTypeNum() {
        return this.spuerMealTypeNum;
    }

    /**
     * set the spuerMealTypeNum - 上次餐类编码
     */
    public void setSpuerMealTypeNum(String spuerMealTypeNum) {
        this.spuerMealTypeNum = spuerMealTypeNum;
    }

    /**
     * get the spuerMealTypeName - 上级餐类名称
     * @return the spuerMealTypeName
     */
    public String getSpuerMealTypeName() {
        return this.spuerMealTypeName;
    }

    /**
     * set the spuerMealTypeName - 上级餐类名称
     */
    public void setSpuerMealTypeName(String spuerMealTypeName) {
        this.spuerMealTypeName = spuerMealTypeName;
    }

    /**
     * get the memo 
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo 
     */
    public void setMemo(String memo) {
        this.memo = memo;
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
     * get the statusCode 
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode 
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the sortNo 
     * @return the sortNo
     */
    public Integer getSortNo() {
        return this.sortNo;
    }

    /**
     * set the sortNo 
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMealTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealTypeNum")), mealTypeNum));
        setMealTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealTypeName")), mealTypeName));
        setSpuerMealTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spuerMealTypeNum")), spuerMealTypeNum));
        setSpuerMealTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spuerMealTypeName")), spuerMealTypeName));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setSortNo(NumberUtils.toInteger(StringUtils.toString(map.get("sortNo")), sortNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("mealTypeNum",StringUtils.toString(mealTypeNum, eiMetadata.getMeta("mealTypeNum")));
        map.put("mealTypeName",StringUtils.toString(mealTypeName, eiMetadata.getMeta("mealTypeName")));
        map.put("spuerMealTypeNum",StringUtils.toString(spuerMealTypeNum, eiMetadata.getMeta("spuerMealTypeNum")));
        map.put("spuerMealTypeName",StringUtils.toString(spuerMealTypeName, eiMetadata.getMeta("spuerMealTypeName")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("sortNo",StringUtils.toString(sortNo, eiMetadata.getMeta("sortNo")));
        return map;
    }
}