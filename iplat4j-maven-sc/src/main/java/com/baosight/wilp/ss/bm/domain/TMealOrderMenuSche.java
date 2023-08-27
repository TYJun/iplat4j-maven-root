/**
* Generate time : 2021-05-24 15:25:48
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
* TMealOrderMenuSche
* 
*/
public class TMealOrderMenuSche extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String menuNum = " ";		/* 菜品编码*/
    private String mealNum = " ";		/* 餐次编码*/
    private String mealTypeNum = " ";		/* 餐类编码*/
    private String mealDate = " ";		/* 菜品日期*/
    private String statusCode = " ";		/* 状态编码[启用(1)/停用(0)]*/
    private String canteenNum = " ";		/* 食堂编码*/
    private Integer num = new Integer(100);		/* 份量*/
    private Integer surplus = new Integer(100);		/* 剩余数量*/
    private BigDecimal priceAfterDiscount = new BigDecimal("0");		/* 折后价格*/
    private String operateCode = "meal";		
    private String operateName = "普餐";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNum");
        eiColumn.setDescName("菜品编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealNum");
        eiColumn.setDescName("餐次编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealTypeNum");
        eiColumn.setDescName("餐类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealDate");
        eiColumn.setDescName("菜品日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码[启用(1)/停用(0)]");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("num");
        eiColumn.setDescName("份量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surplus");
        eiColumn.setDescName("剩余数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("priceAfterDiscount");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("折后价格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public TMealOrderMenuSche() {
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
     * get the menuNum - 菜品编码
     * @return the menuNum
     */
    public String getMenuNum() {
        return this.menuNum;
    }

    /**
     * set the menuNum - 菜品编码
     */
    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
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
     * get the mealDate - 菜品日期
     * @return the mealDate
     */
    public String getMealDate() {
        return this.mealDate;
    }

    /**
     * set the mealDate - 菜品日期
     */
    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }

    /**
     * get the statusCode - 状态编码[启用(1)/停用(0)]
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态编码[启用(1)/停用(0)]
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
     * get the num - 份量
     * @return the num
     */
    public Integer getNum() {
        return this.num;
    }

    /**
     * set the num - 份量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * get the surplus - 剩余数量
     * @return the surplus
     */
    public Integer getSurplus() {
        return this.surplus;
    }

    /**
     * set the surplus - 剩余数量
     */
    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    /**
     * get the priceAfterDiscount - 折后价格
     * @return the priceAfterDiscount
     */
    public BigDecimal getPriceAfterDiscount() {
        return this.priceAfterDiscount;
    }

    /**
     * set the priceAfterDiscount - 折后价格
     */
    public void setPriceAfterDiscount(BigDecimal priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    /**
     * get the operateCode 
     * @return the operateCode
     */
    public String getOperateCode() {
        return this.operateCode;
    }

    /**
     * set the operateCode 
     */
    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    /**
     * get the operateName 
     * @return the operateName
     */
    public String getOperateName() {
        return this.operateName;
    }

    /**
     * set the operateName 
     */
    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMealNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealNum")), mealNum));
        setMealTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealTypeNum")), mealTypeNum));
        setMealDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealDate")), mealDate));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setNum(NumberUtils.toInteger(StringUtils.toString(map.get("num")), num));
        setSurplus(NumberUtils.toInteger(StringUtils.toString(map.get("surplus")), surplus));
        setPriceAfterDiscount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("priceAfterDiscount")), priceAfterDiscount));
        setOperateCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateCode")), operateCode));
        setOperateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateName")), operateName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("mealNum",StringUtils.toString(mealNum, eiMetadata.getMeta("mealNum")));
        map.put("mealTypeNum",StringUtils.toString(mealTypeNum, eiMetadata.getMeta("mealTypeNum")));
        map.put("mealDate",StringUtils.toString(mealDate, eiMetadata.getMeta("mealDate")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("num",StringUtils.toString(num, eiMetadata.getMeta("num")));
        map.put("surplus",StringUtils.toString(surplus, eiMetadata.getMeta("surplus")));
        map.put("priceAfterDiscount",StringUtils.toString(priceAfterDiscount, eiMetadata.getMeta("priceAfterDiscount")));
        map.put("operateCode",StringUtils.toString(operateCode, eiMetadata.getMeta("operateCode")));
        map.put("operateName",StringUtils.toString(operateName, eiMetadata.getMeta("operateName")));
        return map;
    }
}