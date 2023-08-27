package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.mp.common.MpUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
* MpInvoiceDetail
* 发票明细表
*/
public class MpInvoiceDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**发票ID*/
    private String invoiceId;

    /**合同号*/
    private String contNo ;

    /**采购订单ID*/
    private String orderId ;

    /**订单号*/
    private String orderNo ;

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

    /**订单数量*/
    private Double num = new Double(0.00);

    /**订单金额*/
    private BigDecimal orderCost = new BigDecimal("0.00");

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

        eiColumn = new EiColumn("invoiceId");
        eiColumn.setDescName("发票ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderId");
        eiColumn.setDescName("采购订单ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderNo");
        eiColumn.setDescName("订单号");
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
        eiColumn.setDescName("单价");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("num");
        eiColumn.setDescName("订单数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orderCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pictureUri");
        eiColumn.setDescName("图片地址");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MpInvoiceDetail() {
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
     * get the invoiceId - 发票ID
     * @return the invoiceId
     */
    public String getInvoiceId() {
        return this.invoiceId;
    }

    /**
     * set the invoiceId - 发票ID
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * get the orderId - 采购订单ID
     * @return the orderId
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * set the orderId - 采购订单ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * get the orderNo - 订单号
     * @return the orderNo
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * set the orderNo - 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * get the num - 订单数量
     * @return the num
     */
    public Double getNum() {
        return this.num;
    }

    /**
     * set the num - 订单数量
     */
    public void setNum(Double num) {
        this.num = num;
        setOrderCost(NumberUtils.toBigDecimal(this.num).multiply(NumberUtils.toBigDecimal(this.price)).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * get the orderCost - 总金额
     * @return the orderCost
     */
    public BigDecimal getOrderCost() {
        return this.orderCost;
    }

    /**
     * set the orderCost - 总金额
     */
    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
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
        setInvoiceId(MpUtils.toString(map.get("invoiceId"), invoiceId));
        setContNo(MpUtils.toString(map.get("contNo"), contNo));
        setOrderId(MpUtils.toString(map.get("orderId"), orderId));
        setOrderNo(MpUtils.toString(map.get("orderNo"), orderNo));
        setMatNum(MpUtils.toString(map.get("matNum"), matNum));
        setMatName(MpUtils.toString(map.get("matName"), matName));
        setMatTypeId(MpUtils.toString(map.get("matTypeId"), matTypeId));
        setMatTypeName(MpUtils.toString(map.get("matTypeName"), matTypeName));
        setMatSpec(MpUtils.toString(map.get("matSpec"), matSpec));
        setMatModel(MpUtils.toString(map.get("matModel"), matModel));
        setUnit(MpUtils.toString(map.get("unit"), unit));
        setUnitName(MpUtils.toString(map.get("unitName"), unitName));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setNum(NumberUtils.toDouble(StringUtils.toString(map.get("num")), num));
        setOrderCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("orderCost")), orderCost));
        setPictureUri(MpUtils.toString(map.get("pictureUri"), pictureUri));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("invoiceId",StringUtils.toString(invoiceId, eiMetadata.getMeta("invoiceId")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("orderId",StringUtils.toString(orderId, eiMetadata.getMeta("orderId")));
        map.put("orderNo",StringUtils.toString(orderNo, eiMetadata.getMeta("orderNo")));
        map.put("matNum",StringUtils.toString(matNum, eiMetadata.getMeta("matNum")));
        map.put("matName",StringUtils.toString(matName, eiMetadata.getMeta("matName")));
        map.put("matTypeId",StringUtils.toString(matTypeId, eiMetadata.getMeta("matTypeId")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("matSpec",StringUtils.toString(matSpec, eiMetadata.getMeta("matSpec")));
        map.put("matModel",StringUtils.toString(matModel, eiMetadata.getMeta("matModel")));
        map.put("unit",StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName",StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("num",StringUtils.toString(num, eiMetadata.getMeta("num")));
        map.put("orderCost",StringUtils.toString(orderCost, eiMetadata.getMeta("orderCost")));
        map.put("pictureUri",StringUtils.toString(pictureUri, eiMetadata.getMeta("pictureUri")));
        return map;
    }
}