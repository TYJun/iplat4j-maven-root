/**
* Generate time : 2022-10-04 12:17:39
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.mp.common.MpUtils;

/**
* MpContractDetail
* 合同明细对象
*/
public class MpContractDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

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

    /** 计量单位*/
    private String unitName ;

    /**单价*/
    private Double price = new Double(0.00);

    /**不含税单价，预留*/
    private Double noTaxPrice = new Double(0);

    /**合同明细数量*/
    private Double num = new Double(0.00);

    /**合价（含税总价）*/
    private BigDecimal totalCost = new BigDecimal("0");

    /**合价（不含税总价），预留\r */
    private BigDecimal noTaxCost = new BigDecimal("0");

    /**税率，预留*/
    private Double taxRate = new Double(0);

    /**可抵扣税率，预留*/
    private Double deductTaxRate = new Double(0);

    /**剩余可采购数量*/
    private Double surplusNum = new Double(0);

    /**剩余可采购金额*/
    private BigDecimal surplusCost = new BigDecimal("0");

    /**总订单数量*/
    private Double orderNum = new Double(0);

    /**总订单金额*/
    private BigDecimal orderCost = new BigDecimal("0");

    /**已开票数量*/
    private Double billedNum = new Double(0);

    /**总开票金额*/
    private BigDecimal billedCost = new BigDecimal("0");

    /**已入库数量*/
    private Double enterNum = new Double(0);

    /**总入库金额*/
    private BigDecimal enterCost = new BigDecimal("0");

    /**已付款数量*/
    private Double payNum = new Double(0);

    /**已付款金额*/
    private BigDecimal payCost = new BigDecimal("0");

    /**图片地址**/
    private String pictureUri;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
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
        eiColumn.setDescName("计量单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("price");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("noTaxPrice");
        eiColumn.setDescName("不含税单价，预留");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("num");
        eiColumn.setDescName("合同明细数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(12);
        eiColumn.setDescName("合价（含税总价）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("noTaxCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("合价（不含税总价），预留\r ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taxRate");
        eiColumn.setDescName("税率，预留");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deductTaxRate");
        eiColumn.setDescName("可抵扣税率，预留");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surplusNum");
        eiColumn.setDescName("剩余可采购数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("surplusCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("剩余可采购金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNum");
        eiColumn.setDescName("总订单数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总订单金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billedNum");
        eiColumn.setDescName("已开票数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billedCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总开票金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterNum");
        eiColumn.setDescName("已入库数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("enterCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总入库金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payNum");
        eiColumn.setDescName("已付款数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总付款金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pictureUri");
        eiColumn.setDescName("图片地址");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MpContractDetail() {
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
    public void setUnitName(String unitName) { this.unitName = unitName; }

    /**
     * get the price - 单价
     * @return the price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * set the price - 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * get the noTaxPrice - 不含税单价，预留
     * @return the noTaxPrice
     */
    public Double getNoTaxPrice() {
        return this.noTaxPrice;
    }

    /**
     * set the noTaxPrice - 不含税单价，预留
     */
    public void setNoTaxPrice(Double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    /**
     * get the num - 合同明细数量
     * @return the num
     */
    public Double getNum() {
        return this.num;
    }

    /**
     * set the num - 合同明细数量
     */
    public void setNum(Double num) {
        this.num = num;
    }

    /**
     * get the totalCost - 合价（含税总价）
     * @return the totalCost
     */
    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    /**
     * set the totalCost - 合价（含税总价）
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * set the totalCost - 计算合价（含税总价）
     */
    public void calTotalCost(Double price, Double num) {
        BigDecimal b_price = NumberUtils.toBigDecimal(price, BigDecimal.ZERO);
        BigDecimal b_num = NumberUtils.toBigDecimal(num, BigDecimal.ZERO);
        this.totalCost = b_price.multiply(b_num).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * get the noTaxCost - 合价（不含税总价），预留\r 
     * @return the noTaxCost
     */
    public BigDecimal getNoTaxCost() {
        return this.noTaxCost;
    }

    /**
     * set the noTaxCost - 合价（不含税总价），预留\r 
     */
    public void setNoTaxCost(BigDecimal noTaxCost) {
        this.noTaxCost = noTaxCost;
    }

    /**
     * get the taxRate - 税率，预留
     * @return the taxRate
     */
    public Double getTaxRate() {
        return this.taxRate;
    }

    /**
     * set the taxRate - 税率，预留
     */
    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * get the deductTaxRate - 可抵扣税率，预留
     * @return the deductTaxRate
     */
    public Double getDeductTaxRate() {
        return this.deductTaxRate;
    }

    /**
     * set the deductTaxRate - 可抵扣税率，预留
     */
    public void setDeductTaxRate(Double deductTaxRate) {
        this.deductTaxRate = deductTaxRate;
    }

    /**
     * get the surplusNum - 剩余可采购数量
     * @return the surplusNum
     */
    public Double getSurplusNum() {
        return this.surplusNum;
    }

    /**
     * set the surplusNum - 剩余可采购数量
     */
    public void setSurplusNum(Double surplusNum) {
        this.surplusNum = surplusNum;
    }

    /**
     * get the surplusCost - 剩余可采购金额
     * @return the surplusCost
     */
    public BigDecimal getSurplusCost() {
        return this.surplusCost;
    }

    /**
     * set the surplusCost - 剩余可采购金额
     */
    public void setSurplusCost(BigDecimal surplusCost) {
        this.surplusCost = surplusCost;
    }

    /**
     * get the orderNum - 总订单数量
     * @return the orderNum
     */
    public Double getOrderNum() {
        return this.orderNum;
    }

    /**
     * set the orderNum - 总订单数量
     */
    public void setOrderNum(Double orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * get the orderCost - 总订单金额
     * @return the orderCost
     */
    public BigDecimal getOrderCost() {
        return this.orderCost;
    }

    /**
     * set the orderCost - 总订单金额
     */
    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    /**
     * get the billedNum - 已开票数量
     * @return the billedNum
     */
    public Double getBilledNum() {
        return this.billedNum;
    }

    /**
     * set the billedNum - 已开票数量
     */
    public void setBilledNum(Double billedNum) {
        this.billedNum = billedNum;
    }

    /**
     * get the billedCost - 总开票金额
     * @return the billedCost
     */
    public BigDecimal getBilledCost() {
        return this.billedCost;
    }

    /**
     * set the billedCost - 总开票金额
     */
    public void setBilledCost(BigDecimal billedCost) {
        this.billedCost = billedCost;
    }

    /**
     * get the enterNum - 已入库数量
     * @return the enterNum
     */
    public Double getEnterNum() {
        return this.enterNum;
    }

    /**
     * set the enterNum - 已入库数量
     */
    public void setEnterNum(Double enterNum) {
        this.enterNum = enterNum;
    }

    /**
     * get the enterCost - 总入库金额
     * @return the enterCost
     */
    public BigDecimal getEnterCost() {
        return this.enterCost;
    }

    /**
     * set the enterCost - 总入库金额
     */
    public void setEnterCost(BigDecimal enterCost) {
        this.enterCost = enterCost;
    }

    /**
     * get the payNum - 已付款数量
     * @return the payNum
     */
    public Double getPayNum() {
        return this.payNum;
    }

    /**
     * set the payNum - 已付款数量
     */
    public void setPayNum(Double payNum) {
        this.payNum = payNum;
    }

    /**
     * get the payCost - 总付款金额
     * @return the payCost
     */
    public BigDecimal getPayCost() {
        return this.payCost;
    }

    /**
     * set the payCost - 总付款金额
     */
    public void setPayCost(BigDecimal payCost) {
        this.payCost = payCost;
    }

    /**
     * get the pictureUri - 图片地址
     * @return the pictureUri
     */
    public String getPictureUri() { return pictureUri; }

    /**
     * set the pictureUri - 图片地址
     */
    public void setPictureUri(String pictureUri) { this.pictureUri = pictureUri; }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
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
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setNoTaxPrice(NumberUtils.toDouble(StringUtils.toString(map.get("noTaxPrice")), noTaxPrice));
        setNum(NumberUtils.toDouble(StringUtils.toString(map.get("num")), num));
        setTotalCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("totalCost")), totalCost));
        setNoTaxCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("noTaxCost")), noTaxCost));
        setTaxRate(NumberUtils.toDouble(StringUtils.toString(map.get("taxRate")), taxRate));
        setDeductTaxRate(NumberUtils.toDouble(StringUtils.toString(map.get("deductTaxRate")), deductTaxRate));
        setSurplusNum(NumberUtils.toDouble(StringUtils.toString(map.get("surplusNum")), surplusNum));
        setSurplusCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("surplusCost")), surplusCost));
        setOrderNum(NumberUtils.toDouble(StringUtils.toString(map.get("orderNum")), orderNum));
        setOrderCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("orderCost")), orderCost));
        setBilledNum(NumberUtils.toDouble(StringUtils.toString(map.get("billedNum")), billedNum));
        setBilledCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("billedCost")), billedCost));
        setEnterNum(NumberUtils.toDouble(StringUtils.toString(map.get("enterNum")), enterNum));
        setEnterCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("enterCost")), enterCost));
        setPayNum(NumberUtils.toDouble(StringUtils.toString(map.get("payNum")), payNum));
        setPayCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("payCost")), payCost));
        setPictureUri(MpUtils.toString(map.get("pictureUri"), pictureUri));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
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
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("noTaxPrice",StringUtils.toString(noTaxPrice, eiMetadata.getMeta("noTaxPrice")));
        map.put("num",StringUtils.toString(num, eiMetadata.getMeta("num")));
        map.put("totalCost",StringUtils.toString(totalCost, eiMetadata.getMeta("totalCost")));
        map.put("noTaxCost",StringUtils.toString(noTaxCost, eiMetadata.getMeta("noTaxCost")));
        map.put("taxRate",StringUtils.toString(taxRate, eiMetadata.getMeta("taxRate")));
        map.put("deductTaxRate",StringUtils.toString(deductTaxRate, eiMetadata.getMeta("deductTaxRate")));
        map.put("surplusNum",StringUtils.toString(surplusNum, eiMetadata.getMeta("surplusNum")));
        map.put("surplusCost",StringUtils.toString(surplusCost, eiMetadata.getMeta("surplusCost")));
        map.put("orderNum",StringUtils.toString(orderNum, eiMetadata.getMeta("orderNum")));
        map.put("orderCost",StringUtils.toString(orderCost, eiMetadata.getMeta("orderCost")));
        map.put("billedNum",StringUtils.toString(billedNum, eiMetadata.getMeta("billedNum")));
        map.put("billedCost",StringUtils.toString(billedCost, eiMetadata.getMeta("billedCost")));
        map.put("enterNum",StringUtils.toString(enterNum, eiMetadata.getMeta("enterNum")));
        map.put("enterCost",StringUtils.toString(enterCost, eiMetadata.getMeta("enterCost")));
        map.put("payNum",StringUtils.toString(payNum, eiMetadata.getMeta("payNum")));
        map.put("payCost",StringUtils.toString(payCost, eiMetadata.getMeta("payCost")));
        map.put("pictureUri",StringUtils.toString(pictureUri, eiMetadata.getMeta("pictureUri")));
        return map;
    }
}