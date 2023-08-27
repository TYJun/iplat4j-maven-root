package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.utils.UUID;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 合同履历明细
 * MpContractHistoryDetail
 * @author fangjian
 */
public class MpContractHistoryDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**操作主表ID*/
    private String historyId ;

    /**采购计划ID*/
    private String purchasePlanId ;

    /**合同ID*/
    private String contId ;

    /**合同号*/
    private String contNo ;

    /**物资编码*/
    private String matNum ;

    /**物资名称*/
    private String matName ;

    /**物资分类ID*/
    private String matTypeId ;

    /**物资分类名称*/
    private String matTypeName ;

    /**物资规格*/
    private String matSpec ;

    /**物资型号*/
    private String matModel ;

    /**计量单位*/
    private String unit ;

    /**计量单位名称*/
    private String unitName ;

    /**操作前单价*/
    private Double beforePrice = new Double(0.00);

    /**操作前合同明细数量*/
    private Double beforeNum = new Double(0.00);

    /**操作前合价（含税总价）*/
    private BigDecimal beforeTotalCost = new BigDecimal("0.00");

    /**操作后单价*/
    private Double afterPrice = new Double(0.00);

    /**操作后合同明细数量*/
    private Double afterNum = new Double(0.00);

    /**操作后合价（含税总价）*/
    private BigDecimal afterTotalCost = new BigDecimal("0.00");

    /**数据是否被修改 0=没有 1=有*/
    private Integer hasModify = new Integer(0);

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("historyId");
        eiColumn.setDescName("操作主表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchasePlanId");
        eiColumn.setDescName("采购计划ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contId");
        eiColumn.setDescName("合同ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matNum");
        eiColumn.setDescName("物资编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matName");
        eiColumn.setDescName("物资名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeId");
        eiColumn.setDescName("物资分类ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matSpec");
        eiColumn.setDescName("物资规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matModel");
        eiColumn.setDescName("物资型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName("计量单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beforePrice");
        eiColumn.setDescName("操作前单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beforeNum");
        eiColumn.setDescName("操作前合同明细数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("beforeTotalCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(12);
        eiColumn.setDescName("操作前合价（含税总价）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterPrice");
        eiColumn.setDescName("操作后单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterNum");
        eiColumn.setDescName("操作后合同明细数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("afterTotalCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(12);
        eiColumn.setDescName("操作后合价（含税总价）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hasModify");
        eiColumn.setDescName("是否有被修改");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MpContractHistoryDetail() {
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
     * get the historyId - 操作主表ID
     * @return the historyId
     */
    public String getHistoryId() {
        return this.historyId;
    }

    /**
     * set the historyId - 操作主表ID
     */
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    /**
     * get the purchasePlanId - 采购计划ID
     * @return the purchasePlanId
     */
    public String getPurchasePlanId() {
        return this.purchasePlanId;
    }

    /**
     * set the purchasePlanId - 采购计划ID
     */
    public void setPurchasePlanId(String purchasePlanId) {
        this.purchasePlanId = purchasePlanId;
    }

    /**
     * get the contId - 合同ID
     * @return the contId
     */
    public String getContId() {
        return this.contId;
    }

    /**
     * set the contId - 合同ID
     */
    public void setContId(String contId) {
        this.contId = contId;
    }

    /**
     * get the contNo - 合同号
     * @return the contNo
     */
    public String getContNo() {
        return this.contNo;
    }

    /**
     * set the contNo - 合同号
     */
    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    /**
     * get the matNum - 物资编码
     * @return the matNum
     */
    public String getMatNum() {
        return this.matNum;
    }

    /**
     * set the matNum - 物资编码
     */
    public void setMatNum(String matNum) {
        this.matNum = matNum;
    }

    /**
     * get the matName - 物资名称
     * @return the matName
     */
    public String getMatName() {
        return this.matName;
    }

    /**
     * set the matName - 物资名称
     */
    public void setMatName(String matName) {
        this.matName = matName;
    }

    /**
     * get the matTypeId - 物资分类ID
     * @return the matTypeId
     */
    public String getMatTypeId() {
        return this.matTypeId;
    }

    /**
     * set the matTypeId - 物资分类ID
     */
    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
    }

    /**
     * get the matTypeName - 物资分类名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the matSpec - 物资规格
     * @return the matSpec
     */
    public String getMatSpec() {
        return this.matSpec;
    }

    /**
     * set the matSpec - 物资规格
     */
    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    /**
     * get the matModel - 物资型号
     * @return the matModel
     */
    public String getMatModel() {
        return this.matModel;
    }

    /**
     * set the matModel - 物资型号
     */
    public void setMatModel(String matModel) {
        this.matModel = matModel;
    }

    /**
     * get the unit - 计量单位
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit - 计量单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the unitName - 计量单位名称
     * @return the unitName
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * set the unitName - 计量单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * get the beforePrice - 操作前单价
     * @return the beforePrice
     */
    public Double getBeforePrice() {
        return this.beforePrice;
    }

    /**
     * set the beforePrice - 操作前单价
     */
    public void setBeforePrice(Double beforePrice) {
        this.beforePrice = beforePrice;
    }

    /**
     * get the beforeNum - 操作前合同明细数量
     * @return the beforeNum
     */
    public Double getBeforeNum() {
        return this.beforeNum;
    }

    /**
     * set the beforeNum - 操作前合同明细数量
     */
    public void setBeforeNum(Double beforeNum) {
        this.beforeNum = beforeNum;
    }

    /**
     * get the beforeTotalCost - 操作前合价（含税总价）
     * @return the beforeTotalCost
     */
    public BigDecimal getBeforeTotalCost() {
        return this.beforeTotalCost;
    }

    /**
     * set the beforeTotalCost - 操作前合价（含税总价）
     */
    public void setBeforeTotalCost(BigDecimal beforeTotalCost) {
        this.beforeTotalCost = beforeTotalCost;
    }

    /**
     * get the afterPrice - 操作后单价
     * @return the afterPrice
     */
    public Double getAfterPrice() {
        return this.afterPrice;
    }

    /**
     * set the afterPrice - 操作后单价
     */
    public void setAfterPrice(Double afterPrice) {
        this.afterPrice = afterPrice;
    }

    /**
     * get the afterNum - 操作后合同明细数量
     * @return the afterNum
     */
    public Double getAfterNum() {
        return this.afterNum;
    }

    /**
     * set the afterNum - 操作后合同明细数量
     */
    public void setAfterNum(Double afterNum) {
        this.afterNum = afterNum;
    }

    /**
     * get the afterTotalCost - 操作后合价（含税总价）
     * @return the afterTotalCost
     */
    public BigDecimal getAfterTotalCost() {
        return this.afterTotalCost;
    }

    /**
     * set the afterTotalCost - 操作后合价（含税总价）
     */
    public void setAfterTotalCost(BigDecimal afterTotalCost) {
        this.afterTotalCost = afterTotalCost;
    }

    /**
     * get the hasModify - 数据是否被修改 0=没有 1=有
     * @return the hasModify
     */
    public Integer getHasModify() {
        return this.hasModify;
    }

    /**
     * set the hasModify - 数据是否被修改 0=没有 1=有
     */
    public void setHasModify(Integer hasModify) {
        this.hasModify = hasModify;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setHistoryId(MpUtils.toString(map.get("historyId"), historyId));
        setPurchasePlanId(MpUtils.toString(map.get("purchasePlanId"), purchasePlanId));
        setContId(MpUtils.toString(map.get("contId"), contId));
        setContNo(MpUtils.toString(map.get("contNo"), contNo));
        setMatNum(MpUtils.toString(map.get("matNum"), matNum));
        setMatName(MpUtils.toString(map.get("matName"), matName));
        setMatTypeId(MpUtils.toString(map.get("matTypeId"), matTypeId));
        setMatTypeName(MpUtils.toString(map.get("matTypeName"), matTypeName));
        setMatSpec(MpUtils.toString(map.get("matSpec"), matSpec));
        setMatModel(MpUtils.toString(map.get("matModel"), matModel));
        setUnit(MpUtils.toString(map.get("unit"), unit));
        setUnitName(MpUtils.toString(map.get("unitName"), unitName));
        setBeforePrice(NumberUtils.toDouble(StringUtils.toString(map.get("beforePrice")), beforePrice));
        setBeforeNum(NumberUtils.toDouble(StringUtils.toString(map.get("beforeNum")), beforeNum));
        setBeforeTotalCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("beforeTotalCost")), beforeTotalCost));
        setAfterPrice(NumberUtils.toDouble(StringUtils.toString(map.get("afterPrice")), afterPrice));
        setAfterNum(NumberUtils.toDouble(StringUtils.toString(map.get("afterNum")), afterNum));
        setAfterTotalCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("afterTotalCost")), afterTotalCost));
        setHasModify(NumberUtils.toInteger(StringUtils.toString(map.get("hasModify")), hasModify));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("historyId",StringUtils.toString(historyId, eiMetadata.getMeta("historyId")));
        map.put("purchasePlanId",StringUtils.toString(purchasePlanId, eiMetadata.getMeta("purchasePlanId")));
        map.put("contId",StringUtils.toString(contId, eiMetadata.getMeta("contId")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matTypeId",StringUtils.toString(matTypeId, eiMetadata.getMeta("matTypeId")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("beforePrice",StringUtils.toString(beforePrice, eiMetadata.getMeta("beforePrice")));
        map.put("beforeNum",StringUtils.toString(beforeNum, eiMetadata.getMeta("beforeNum")));
        map.put("beforeTotalCost",StringUtils.toString(beforeTotalCost, eiMetadata.getMeta("beforeTotalCost")));
        map.put("afterPrice",StringUtils.toString(afterPrice, eiMetadata.getMeta("afterPrice")));
        map.put("afterNum",StringUtils.toString(afterNum, eiMetadata.getMeta("afterNum")));
        map.put("afterTotalCost",StringUtils.toString(afterTotalCost, eiMetadata.getMeta("afterTotalCost")));
        map.put("hasModify",StringUtils.toString(hasModify, eiMetadata.getMeta("hasModify")));
        return map;
    }

    /**
     * 构建合同生效时明细履历对象
     * @Title: newEffectInstance
     * @param historyId historyId
     * @return com.baosight.wilp.mp.lj.domain.MpContractHistoryDetail
     * @throws
     **/
    public static MpContractHistoryDetail newEffectInstance(String historyId) {
        MpContractHistoryDetail detail = new MpContractHistoryDetail();
        detail.setHistoryId(historyId);
        detail.setHasModify(1);
        return detail;
    }
}