/**
* Generate time : 2021-05-20 11:17:25
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
* SSBMCppb01
* sc_menu_sche 
* sc_canteen_liaison_conf
* sc_menu
* sc_order_type
*/
public class SSBMCppb01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String menuNum = " ";		/* 菜品编码*/
    private String menuName = " ";		/* 菜品名称*/
    private BigDecimal menuPrice = new BigDecimal("0");		/* 菜品单价*/
    private BigDecimal priceAfterDiscount = new BigDecimal("0");		/* 折后价格*/
    private Integer surplus = new Integer(100);		/* 剩余数量*/
    private String mealTypeName = " ";		/* 餐类名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuNum");
        eiColumn.setDescName("菜品编码");
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

        eiColumn = new EiColumn("priceAfterDiscount");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("折后价格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surplus");
        eiColumn.setDescName("剩余数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mealTypeName");
        eiColumn.setDescName("餐类名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMCppb01() {
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setPriceAfterDiscount(NumberUtils.toBigDecimal(StringUtils.toString(map.get("priceAfterDiscount")), priceAfterDiscount));
        setSurplus(NumberUtils.toInteger(StringUtils.toString(map.get("surplus")), surplus));
        setMealTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mealTypeName")), mealTypeName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("priceAfterDiscount",StringUtils.toString(priceAfterDiscount, eiMetadata.getMeta("priceAfterDiscount")));
        map.put("surplus",StringUtils.toString(surplus, eiMetadata.getMeta("surplus")));
        map.put("mealTypeName",StringUtils.toString(mealTypeName, eiMetadata.getMeta("mealTypeName")));
        return map;
    }
}