/**
* Generate time : 2021-05-24 10:10:20
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
* SSBMCppb02
* sc_menu
* sc_canteen_liaison_conf
*/
public class SSBMCppb02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String menuNum = " ";		/* 菜品编码*/
    private String menuName = " ";		/* 菜品名称*/
    private String menuDesc = " ";		/* 菜品备注*/
    private BigDecimal menuPrice = new BigDecimal("0");		/* 菜品单价*/
    private String menuTypeNum = " ";		/* 菜品类型编码*/
    private String menuTypeName = " ";		/* 菜品类型名称*/
    private String suitGroup = " ";		/* 适宜人群*/
    private String notSuitGroup = " ";		/* 不适宜人群*/
    private String canteenName = " ";		/* 食堂名称*/
    private Integer menuSupply = new Integer(0);		/* 菜品供应量*/

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

        eiColumn = new EiColumn("menuDesc");
        eiColumn.setDescName("菜品备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuPrice");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("菜品单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTypeNum");
        eiColumn.setDescName("菜品类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTypeName");
        eiColumn.setDescName("菜品类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("suitGroup");
        eiColumn.setDescName("适宜人群");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("notSuitGroup");
        eiColumn.setDescName("不适宜人群");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName("食堂名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuSupply");
        eiColumn.setDescName("菜品供应量");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMCppb02() {
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
     * get the menuTypeNum - 菜品类型编码
     * @return the menuTypeNum
     */
    public String getMenuTypeNum() {
        return this.menuTypeNum;
    }

    /**
     * set the menuTypeNum - 菜品类型编码
     */
    public void setMenuTypeNum(String menuTypeNum) {
        this.menuTypeNum = menuTypeNum;
    }

    /**
     * get the menuTypeName - 菜品类型名称
     * @return the menuTypeName
     */
    public String getMenuTypeName() {
        return this.menuTypeName;
    }

    /**
     * set the menuTypeName - 菜品类型名称
     */
    public void setMenuTypeName(String menuTypeName) {
        this.menuTypeName = menuTypeName;
    }

    /**
     * get the suitGroup - 适宜人群
     * @return the suitGroup
     */
    public String getSuitGroup() {
        return this.suitGroup;
    }

    /**
     * set the suitGroup - 适宜人群
     */
    public void setSuitGroup(String suitGroup) {
        this.suitGroup = suitGroup;
    }

    /**
     * get the notSuitGroup - 不适宜人群
     * @return the notSuitGroup
     */
    public String getNotSuitGroup() {
        return this.notSuitGroup;
    }

    /**
     * set the notSuitGroup - 不适宜人群
     */
    public void setNotSuitGroup(String notSuitGroup) {
        this.notSuitGroup = notSuitGroup;
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
     * get the menuSupply - 菜品供应量
     * @return the menuSupply
     */
    public Integer getMenuSupply() {
        return this.menuSupply;
    }

    /**
     * set the menuSupply - 菜品供应量
     */
    public void setMenuSupply(Integer menuSupply) {
        this.menuSupply = menuSupply;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setMenuDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuDesc")), menuDesc));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setMenuTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuTypeNum")), menuTypeNum));
        setMenuTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuTypeName")), menuTypeName));
        setSuitGroup(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("suitGroup")), suitGroup));
        setNotSuitGroup(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("notSuitGroup")), notSuitGroup));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setMenuSupply(NumberUtils.toInteger(StringUtils.toString(map.get("menuSupply")), menuSupply));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("menuDesc",StringUtils.toString(menuDesc, eiMetadata.getMeta("menuDesc")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("menuTypeNum",StringUtils.toString(menuTypeNum, eiMetadata.getMeta("menuTypeNum")));
        map.put("menuTypeName",StringUtils.toString(menuTypeName, eiMetadata.getMeta("menuTypeName")));
        map.put("suitGroup",StringUtils.toString(suitGroup, eiMetadata.getMeta("suitGroup")));
        map.put("notSuitGroup",StringUtils.toString(notSuitGroup, eiMetadata.getMeta("notSuitGroup")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("menuSupply",StringUtils.toString(menuSupply, eiMetadata.getMeta("menuSupply")));
        return map;
    }
}