/**
* Generate time : 2021-03-05 15:32:26
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
* SHCX01
* sc_menu
*/
public class SSBMCpxx01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String menuNum = " ";		/* 菜品编码*/
    private String menuName = " ";		/* 菜品名称*/
    private String suitGroup = " ";		/* 适宜人群*/
    private String notSuitGroup = " ";		/* 不适宜人群*/
    private String menuDesc = " ";		/* 菜品备注*/
    private BigDecimal menuPrice = new BigDecimal("0");		/* 菜品单价*/
    private String menuPicUrl = " ";		/* 菜品图片地址*/
    private String statusCode = " ";		/* 状态编码[启用(1)/停用(0)]*/
    private Integer evalLevel = new Integer(3);		/* 菜品评价等级*/
    private String canteenNum = " ";		/* 食堂编码*/
    private String menuTypeNum = " ";		/* 菜品类型编码*/
    private String menuTypeName = " ";		/* 菜品类型名称*/
    private String descption = " ";		/* 菜品描述*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recCreator = " ";		/* 创建人*/
    private String nutrition = " ";		/* 营养价值*/
    private String heat = " ";		/* 热量*/
    private String comboRuleValue = " ";		/* 套餐类型*/
    private String operateCode = " ";		
    private String operateName = " ";		
    private Integer menuSupply = new Integer(0);		/* 菜品供应量*/
    private String jpMenuName = " ";		/* 菜品名称简拼*/
    private BigDecimal menuFee = new BigDecimal(0.00);		/* 打包费*/
    private String imgStr = " ";		/* 菜品名称简拼*/

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

        eiColumn = new EiColumn("menuName");
        eiColumn.setDescName("菜品名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("suitGroup");
        eiColumn.setDescName("适宜人群");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("notSuitGroup");
        eiColumn.setDescName("不适宜人群");
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

        eiColumn = new EiColumn("menuPicUrl");
        eiColumn.setDescName("菜品图片地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码[启用(1)/停用(0)]");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("evalLevel");
        eiColumn.setDescName("菜品评价等级");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTypeNum");
        eiColumn.setDescName("菜品类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuTypeName");
        eiColumn.setDescName("菜品类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("descption");
        eiColumn.setDescName("菜品描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nutrition");
        eiColumn.setDescName("营养价值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("heat");
        eiColumn.setDescName("热量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("comboRuleValue");
        eiColumn.setDescName("套餐类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("imgStr");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuSupply");
        eiColumn.setDescName("菜品供应量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jpMenuName");
        eiColumn.setDescName("菜品名称简拼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("menuFee");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("打包费");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMCpxx01() {
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
     * get the menuPicUrl - 菜品图片地址
     * @return the menuPicUrl
     */
    public String getMenuPicUrl() {
        return this.menuPicUrl;
    }

    /**
     * set the menuPicUrl - 菜品图片地址
     */
    public void setMenuPicUrl(String menuPicUrl) {
        this.menuPicUrl = menuPicUrl;
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
     * get the evalLevel - 菜品评价等级
     * @return the evalLevel
     */
    public Integer getEvalLevel() {
        return this.evalLevel;
    }

    /**
     * set the evalLevel - 菜品评价等级
     */
    public void setEvalLevel(Integer evalLevel) {
        this.evalLevel = evalLevel;
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
     * get the descption - 菜品描述
     * @return the descption
     */
    public String getDescption() {
        return this.descption;
    }

    /**
     * set the descption - 菜品描述
     */
    public void setDescption(String descption) {
        this.descption = descption;
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
     * get the nutrition - 营养价值
     * @return the nutrition
     */
    public String getNutrition() {
        return this.nutrition;
    }

    /**
     * set the nutrition - 营养价值
     */
    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * get the heat - 热量
     * @return the heat
     */
    public String getHeat() {
        return this.heat;
    }

    /**
     * set the heat - 热量
     */
    public void setHeat(String heat) {
        this.heat = heat;
    }

    /**
     * get the comboRuleValue - 套餐类型
     * @return the comboRuleValue
     */
    public String getComboRuleValue() {
        return this.comboRuleValue;
    }

    /**
     * set the comboRuleValue - 套餐类型
     */
    public void setComboRuleValue(String comboRuleValue) {
        this.comboRuleValue = comboRuleValue;
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
     * get the jpMenuName - 菜品名称简拼
     * @return the jpMenuName
     */
    public String getJpMenuName() {
        return this.jpMenuName;
    }

    /**
     * set the jpMenuName - 菜品名称简拼
     */
    public void setJpMenuName(String jpMenuName) {
        this.jpMenuName = jpMenuName;
    }

    /**
     * get the menuFee - 打包费
     * @return the menuFee
     */
    public BigDecimal getMenuFee() {
        return this.menuFee;
    }

    /**
     * set the menuFee - 打包费
     */
    public void setMenuFee(BigDecimal menuFee) {
        this.menuFee = menuFee;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMenuNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuNum")), menuNum));
        setMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuName")), menuName));
        setSuitGroup(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("suitGroup")), suitGroup));
        setNotSuitGroup(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("notSuitGroup")), notSuitGroup));
        setMenuDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuDesc")), menuDesc));
        setMenuPrice(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuPrice")), menuPrice));
        setMenuPicUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuPicUrl")), menuPicUrl));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setEvalLevel(NumberUtils.toInteger(StringUtils.toString(map.get("evalLevel")), evalLevel));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setMenuTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuTypeNum")), menuTypeNum));
        setMenuTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("menuTypeName")), menuTypeName));
        setDescption(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("descption")), descption));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setNutrition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nutrition")), nutrition));
        setHeat(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("heat")), heat));
        setComboRuleValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("comboRuleValue")), comboRuleValue));
        setOperateCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateCode")), operateCode));
        setOperateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateName")), operateName));
        setMenuSupply(NumberUtils.toInteger(StringUtils.toString(map.get("menuSupply")), menuSupply));
        setJpMenuName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jpMenuName")), jpMenuName));
        setMenuFee(NumberUtils.toBigDecimal(StringUtils.toString(map.get("menuFee")), menuFee));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("menuNum",StringUtils.toString(menuNum, eiMetadata.getMeta("menuNum")));
        map.put("menuName",StringUtils.toString(menuName, eiMetadata.getMeta("menuName")));
        map.put("suitGroup",StringUtils.toString(suitGroup, eiMetadata.getMeta("suitGroup")));
        map.put("notSuitGroup",StringUtils.toString(notSuitGroup, eiMetadata.getMeta("notSuitGroup")));
        map.put("menuDesc",StringUtils.toString(menuDesc, eiMetadata.getMeta("menuDesc")));
        map.put("menuPrice",StringUtils.toString(menuPrice, eiMetadata.getMeta("menuPrice")));
        map.put("menuPicUrl",StringUtils.toString(menuPicUrl, eiMetadata.getMeta("menuPicUrl")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("evalLevel",StringUtils.toString(evalLevel, eiMetadata.getMeta("evalLevel")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("menuTypeNum",StringUtils.toString(menuTypeNum, eiMetadata.getMeta("menuTypeNum")));
        map.put("menuTypeName",StringUtils.toString(menuTypeName, eiMetadata.getMeta("menuTypeName")));
        map.put("descption",StringUtils.toString(descption, eiMetadata.getMeta("descption")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("nutrition",StringUtils.toString(nutrition, eiMetadata.getMeta("nutrition")));
        map.put("heat",StringUtils.toString(heat, eiMetadata.getMeta("heat")));
        map.put("comboRuleValue",StringUtils.toString(comboRuleValue, eiMetadata.getMeta("comboRuleValue")));
        map.put("operateCode",StringUtils.toString(operateCode, eiMetadata.getMeta("operateCode")));
        map.put("operateName",StringUtils.toString(operateName, eiMetadata.getMeta("operateName")));
        map.put("menuSupply",StringUtils.toString(menuSupply, eiMetadata.getMeta("menuSupply")));
        map.put("jpMenuName",StringUtils.toString(jpMenuName, eiMetadata.getMeta("jpMenuName")));
        map.put("menuFee",StringUtils.toString(menuFee, eiMetadata.getMeta("menuFee")));
        return map;
    }
}