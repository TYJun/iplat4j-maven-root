package com.baosight.wilp.mp.pz.domain;

import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 *
 * @author liu
 * @since 2023-04-26 13:44:22
 */
public class MpPurchaseTypeConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;
    

    /**
     * 主键
     */        
    private String id = " ";

    /**
     * 采购年份
     */        
    private String purchaseYear = " ";

    /**
     * 采购类别
     */        
    private String purchaseType = " ";

    /**
     * 采购类别名称
     */        
    private String purchaseTypeName = " ";

    /**
     * 总金额
     */        
    private BigDecimal purchaseCost = new BigDecimal("0.00");

    /**
     * 账套
     */        
    private String dataGroupCode = " ";

    /**
     * 创建（申请）人
     */        
    private String recCreator = " ";

    /**
     * 创建时间
     */        
    private String recCreateTime;

    /**
     * 修改人
     */        
    private String recRevisor = " ";

    /**
     * 修改时间
     */        
    private String recReviseTime;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;
        
        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);   
        
        
        eiColumn = new EiColumn("purchaseYear");
        eiColumn.setDescName("采购年份");
        eiMetadata.addMeta(eiColumn);   
        
        
        eiColumn = new EiColumn("purchaseType");
        eiColumn.setDescName("采购类别");
        eiMetadata.addMeta(eiColumn);   
        
        
        eiColumn = new EiColumn("purchaseTypeName");
        eiColumn.setDescName("采购类别名称");
        eiMetadata.addMeta(eiColumn);   
        
        
        eiColumn = new EiColumn("purchaseCost");
        eiColumn.setDescName("总金额");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiMetadata.addMeta(eiColumn);   
        
        
        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);   
        
        
        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建（申请）人");
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
    public MpPurchaseTypeConfig() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the purchaseYear - 采购年份
     * @return the purchaseYear
     */
    public String getPurchaseYear() {
        return purchaseYear;
    }
    /**
     * set the purchaseYear - 采购年份
     */
    public void setPurchaseYear(String purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    /**
     * get the purchaseType - 采购类别
     * @return the purchaseType
     */
    public String getPurchaseType() {
        return purchaseType;
    }
    /**
     * set the purchaseType - 采购类别
     */
    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    /**
     * get the purchaseTypeName - 采购类别名称
     * @return the purchaseTypeName
     */
    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }
    /**
     * set the purchaseTypeName - 采购类别名称
     */
    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    /**
     * get the purchaseCost - 总金额
     * @return the purchaseCost
     */
    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }
    /**
     * set the purchaseCost - 总金额
     */
    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return dataGroupCode;
    }
    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the recCreator - 创建（申请）人
     * @return the recCreator
     */
    public String getRecCreator() {
        return recCreator;
    }
    /**
     * set the recCreator - 创建（申请）人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return recCreateTime;
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
        return recRevisor;
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
        return recReviseTime;
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
            setPurchaseYear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseYear")), purchaseYear));
            setPurchaseType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseType")), purchaseType));
            setPurchaseTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purchaseTypeName")), purchaseTypeName));
            setPurchaseCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("purchaseCost")), purchaseCost));
            setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
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
            map.put("purchaseYear",StringUtils.toString(purchaseYear, eiMetadata.getMeta("purchaseYear")));
            map.put("purchaseType",StringUtils.toString(purchaseType, eiMetadata.getMeta("purchaseType")));
            map.put("purchaseTypeName",StringUtils.toString(purchaseTypeName, eiMetadata.getMeta("purchaseTypeName")));
            map.put("purchaseCost",StringUtils.toString(purchaseCost, eiMetadata.getMeta("purchaseCost")));
            map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
            map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
            map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
            map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
            map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }

}

