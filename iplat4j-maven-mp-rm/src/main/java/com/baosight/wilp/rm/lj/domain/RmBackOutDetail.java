package com.baosight.wilp.rm.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.rm.common.RmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 物资退库申请明细实体
 * RmBackOutDetail
 * @author fangjian
 */
public class RmBackOutDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 物资退库申请表ID*/
    private String backOutId;

    /**领用单号**/
    private String claimNo;

    /** 物资编码*/
    private String matNum;

    /** 物资名称*/
    private String matName;

    /** 物资分类ID*/
    private String matTypeId;

    /** 物资分类名称*/
    private String matTypeName;

    /** 物资规格*/
    private String matSpec;

    /** 物资型号*/
    private String matModel;

    /** 计量单位*/
    private String unit;

    /** 计量单位*/
    private String unitName ;

    /** 单价*/
    private Double price = new Double(0.00);

    /** 退库数量*/
    private Double num = new Double(0.00);

    /** 已退库数量*/
    private Double outNum = new Double(0.00);

    /** 本次退库数量*/
    private Double curOutNum = new Double(0.00);

    /** 实际领用数量*/
    private Double actualClaimNum = new Double(0.00);

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

        eiColumn = new EiColumn("backOutId");
        eiColumn.setDescName("物资退库申请表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("claimNo");
        eiColumn.setDescName("物资领用单号");
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
        eiColumn.setDescName("退库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outNum");
        eiColumn.setDescName("已退库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("curOutNum");
        eiColumn.setDescName("本次退库数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualClaimNum");
        eiColumn.setDescName("实际领用数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pictureUri");
        eiColumn.setDescName("图片地址");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public RmBackOutDetail() {
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
     * get the claimNo - 物资领用单号
     * @return the claimNo
     */
    public String getClaimNo() {
        return this.claimNo;
    }

    /**
     * set the claimNo - 物资领用单号
     */
    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    /**
     * get the backOutId - 物资退库申请表ID
     * @return the backOutId
     */
    public String getBackOutId() {
        return this.backOutId;
    }

    /**
     * set the backOutId - 物资退库申请表ID
     */
    public void setBackOutId(String backOutId) {
        this.backOutId = backOutId;
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
     * get the num - 退库数量
     * @return the num
     */
    public Double getNum() {
        return this.num;
    }

    /**
     * set the num - 退库数量
     */
    public void setNum(Double num) {
        this.num = num;
    }

    /**
     * get the outNum - 已退库数量
     * @return the outNum
     */
    public Double getOutNum() {
        return this.outNum;
    }

    /**
     * set the outNum - 已退库数量
     */
    public void setOutNum(Double outNum) {
        this.outNum = outNum;
    }

    /**
     * get the curOutNum - 本次退库数量
     * @return the curOutNum
     */
    public Double getCurOutNum() {
        return this.curOutNum;
    }

    /**
     * set the curOutNum - 本次退库数量
     */
    public void setCurOutNum(Double curOutNum) {
        this.curOutNum = curOutNum;
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
     * get the actualClaimNum - 实际领用数量
     * @return the actualClaimNum
     */
    public Double getActualClaimNum() {
        return this.actualClaimNum;
    }

    /**
     * set the actualClaimNum - 实际领用数量
     */
    public void setActualClaimNum(Double actualClaimNum) {
        this.actualClaimNum = actualClaimNum;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(RmUtils.toString(map.get("id"), id));
        setBackOutId(RmUtils.toString(map.get("backOutId"), backOutId));
        setClaimNo(RmUtils.toString(map.get("claimNo"), claimNo));
        setMatNum(RmUtils.toString(map.get("matNum"), matNum));
        setMatName(RmUtils.toString(map.get("matName"), matName));
        setMatTypeId(RmUtils.toString(map.get("matTypeId"), matTypeId));
        setMatTypeName(RmUtils.toString(map.get("matTypeName"), matTypeName));
        setMatSpec(RmUtils.toString(map.get("matSpec"), matSpec));
        setMatModel(RmUtils.toString(map.get("matModel"), matModel));
        setUnit(RmUtils.toString(map.get("unit"), unit));
        setUnitName(RmUtils.toString(map.get("unitName"), unitName));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setNum(NumberUtils.toDouble(StringUtils.toString(map.get("num")), num));
        setOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("outNum")), outNum));
        setCurOutNum(NumberUtils.toDouble(StringUtils.toString(map.get("curOutNum")), curOutNum));
        setPictureUri(RmUtils.toString(map.get("pictureUri"), pictureUri));
        setActualClaimNum(NumberUtils.toDouble(StringUtils.toString(map.get("actualClaimNum")), actualClaimNum));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("backOutId",StringUtils.toString(backOutId, eiMetadata.getMeta("backOutId")));
        map.put("claimNo",StringUtils.toString(claimNo, eiMetadata.getMeta("claimNo")));
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
        map.put("outNum",StringUtils.toString(outNum, eiMetadata.getMeta("outNum")));
        map.put("curOutNum",StringUtils.toString(curOutNum, eiMetadata.getMeta("curOutNum")));
        map.put("pictureUri",StringUtils.toString(pictureUri, eiMetadata.getMeta("pictureUri")));
        map.put("actualClaimNum",StringUtils.toString(actualClaimNum, eiMetadata.getMeta("actualClaimNum")));
        return map;
    }
}