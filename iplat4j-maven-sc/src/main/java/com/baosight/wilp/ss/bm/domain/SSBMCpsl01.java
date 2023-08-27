/**
* Generate time : 2021-05-19 15:21:56
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
* SSBMCpsl01
* sc_menu_sche
* sc_canteen_liaison_conf
* sc_menu
* sc_order_time
*/
public class SSBMCpsl01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String menuName = " ";		/* 菜品名称*/
    private BigDecimal menuPrice = new BigDecimal("0");		/* 菜品单价*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String canteenName = " ";		/* 食堂名称*/
    private String mealName = " ";		/* 餐次名称*/
    private String menuDesc = " ";		/* 菜品备注*/
    private String mealDate = " ";		/* 菜品日期*/
    private Integer num = new Integer(100);		/* 份量*/
    private Integer surplus = new Integer(100);		/* 剩余数量*/
    private BigDecimal priceAfterDiscount = new BigDecimal("0");		/* 折后价格*/
    private String operatecode = "meal";		
    private String operatename = "普餐";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuName");
        eiColumn.setDescName("菜品名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("菜品单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealName");
        eiColumn.setDescName("餐次名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuDesc");
        eiColumn.setDescName("菜品备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealDate");
        eiColumn.setDescName("菜品日期");
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

        eiColumn = new EiColumn("operatecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatename");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMCpsl01() {
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
     * get the menuName - 菜品名称
     * @return the menuName
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * set the menuName - 菜品名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * get the menuPrice - 菜品单价
     * @return the menuPrice
     */
    public BigDecimal getMenuPrice() {
        return this.menuPrice;
    }

    /**
     * set the menuPrice - 菜品单价
     */
    public void setMenuPrice(BigDecimal menuPrice) {
        this.menuPrice = menuPrice;
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
     * get the menuDesc - 菜品备注
     * @return the menuDesc
     */
    public String getMenuDesc() {
        return this.menuDesc;
    }

    /**
     * set the menuDesc - 菜品备注
     */
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
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
     * get the operatecode 
     * @return the operatecode
     */
    public String getOperatecode() {
        return this.operatecode;
    }

    /**
     * set the operatecode 
     */
    public void setOperatecode(String operatecode) {
        this.operatecode = operatecode;
    }

    /**
     * get the operatename 
     * @return the operatename
     */
    public String getOperatename() {
        return this.operatename;
    }

    /**
     * set the operatename 
     */
    public void setOperatename(String operatename) {
        this.operatename = operatename;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setMealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealName")), mealName));
        setMenuDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuDesc")), menuDesc));
        setMealDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealDate")), mealDate));
        setNum(NumberUtils.toInteger(StringUtils.toString(map.get("num")), num));
        setSurplus(NumberUtils.toInteger(StringUtils.toString(map.get("surplus")), surplus));
        setPriceAfterDiscount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("priceAfterDiscount")), priceAfterDiscount));
        setOperatecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatecode")), operatecode));
        setOperatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatename")), operatename));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("mealName",StringUtils.toString(mealName, eiMetadata.getMeta("mealName")));
        map.put("menuDesc",StringUtils.toString(menuDesc, eiMetadata.getMeta("menuDesc")));
        map.put("mealDate",StringUtils.toString(mealDate, eiMetadata.getMeta("mealDate")));
        map.put("num",StringUtils.toString(num, eiMetadata.getMeta("num")));
        map.put("surplus",StringUtils.toString(surplus, eiMetadata.getMeta("surplus")));
        map.put("priceAfterDiscount",StringUtils.toString(priceAfterDiscount, eiMetadata.getMeta("priceAfterDiscount")));
        map.put("operatecode",StringUtils.toString(operatecode, eiMetadata.getMeta("operatecode")));
        map.put("operatename",StringUtils.toString(operatename, eiMetadata.getMeta("operatename")));
        return map;
    }
}