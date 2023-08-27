/**
* Generate time : 2022-05-24 0:35:13
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ScMealStaffing
* 
*/
public class SSBM01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String typeName = " ";		/* 员工类型*/
    private String typeCode = " ";		/* 员工类型编码*/
    private String mealTimeName = " ";		/* 餐次名称*/
    private String mealTimeCode = " ";		/* 餐次编码*/
    private String discountAmount = " ";		/* 优惠金额*/
    private String menuName = " ";
    private Timestamp createTime ;		/* 创建时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeName");
        eiColumn.setDescName("员工类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeCode");
        eiColumn.setDescName("员工类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealTimeName");
        eiColumn.setDescName("餐次名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealTimeCode");
        eiColumn.setDescName("餐次编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("discountAmount");
        eiColumn.setDescName("优惠金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuName");
        eiColumn.setDescName("优惠金额");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBM01() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the typeName - 员工类型
     * @return the typeName
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * set the typeName - 员工类型
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * get the typeCode - 员工类型编码
     * @return the typeCode
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * set the typeCode - 员工类型编码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * get the mealTimeName - 餐次名称
     * @return the mealTimeName
     */
    public String getMealTimeName() {
        return this.mealTimeName;
    }

    /**
     * set the mealTimeName - 餐次名称
     */
    public void setMealTimeName(String mealTimeName) {
        this.mealTimeName = mealTimeName;
    }

    /**
     * get the mealTimeCode - 餐次编码
     * @return the mealTimeCode
     */
    public String getMealTimeCode() {
        return this.mealTimeCode;
    }

    /**
     * set the mealTimeCode - 餐次编码
     */
    public void setMealTimeCode(String mealTimeCode) {
        this.mealTimeCode = mealTimeCode;
    }

    /**
     * get the discountAmount - 优惠金额
     * @return the discountAmount
     */
    public String getDiscountAmount() {
        return this.discountAmount;
    }

    /**
     * set the discountAmount - 优惠金额
     */
    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the discountAmount - 优惠金额
     * @return the discountAmount
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * set the discountAmount - 优惠金额
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeName")), typeName));
        setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
        setMealTimeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealTimeName")), mealTimeName));
        setMealTimeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealTimeCode")), mealTimeCode));
        setDiscountAmount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("discountAmount")), discountAmount));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("typeName",StringUtils.toString(typeName, eiMetadata.getMeta("typeName")));
        map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
        map.put("mealTimeName",StringUtils.toString(mealTimeName, eiMetadata.getMeta("mealTimeName")));
        map.put("mealTimeCode",StringUtils.toString(mealTimeCode, eiMetadata.getMeta("mealTimeCode")));
        map.put("discountAmount",StringUtils.toString(discountAmount, eiMetadata.getMeta("discountAmount")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        return map;
    }
}