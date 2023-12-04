package rm.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.rm.common.RmUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 需求计划明细表
 * RmRequirePlanDetail
 * @author fangjian
 */
public class RmRequirePlanDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 需求计划ID*/
    private String planId;

    /** 需求计划单号*/
    private String planNo;

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

    /** 计划数量*/
    private Double num = new Double(0.00);

    /** 金额*/
    private BigDecimal cost = new BigDecimal("0.00");

    /** 是否已分配*/
    private Integer hasCollect = new Integer(0);

    /**图片地址**/
    private String pictureUri;

    /**采购科室编码**/
    private String purchaseDeptNum;

    /**采购科室名称**/
    private String purchaseDeptName;

    /**需求科室名称**/
    private String deptName;

    /**电子签名**/
    private String signature;

    /**分配时间**/
    private String allotTime;

    /**分配人工号**/
    private String allotManNo;

    /**分配人姓名**/
    private String allotManName;

    /**退回原因**/
    private String backReason;

    /**创建时间**/
    private String recCreateTimeStr;

    /**是否已生成采购计划标记**/
    private String status = "0";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planId");
        eiColumn.setDescName("计划ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planNo");
        eiColumn.setDescName("计划单号");
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
        eiColumn.setDescName("计划数量");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hasCollect");
        eiColumn.setDescName("是否已汇总");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pictureUri");
        eiColumn.setDescName("图片地址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseDeptNum");
        eiColumn.setDescName("采购科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseDeptName");
        eiColumn.setDescName("采购科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("需求科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("signature");
        eiColumn.setDescName("电子签名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("allotTime");
        eiColumn.setDescName("分配时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("allotManNo");
        eiColumn.setDescName("分配人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("allotManName");
        eiColumn.setDescName("分配人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("backReason");
        eiColumn.setDescName("退回原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("是否已生成采购计划标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTimeStr");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public RmRequirePlanDetail() {
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
     * get the planId - 年度计划ID
     * @return the planId
     */
    public String getPlanId() {
        return this.planId;
    }

    /**
     * set the planId - 年度计划ID
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * set the planNo - 年度计划单号
     */
    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    /**
     * get the planNo - 年度计划单号
     * @return the planNo
     */
    public String getPlanNo() {
        return this.planNo;
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
        setCost(this.num, this.price);
    }

    /**
     * get the num - 计划数量
     * @return the num
     */
    public Double getNum() {
        return this.num;
    }

    /**
     * set the num - 计划数量
     */
    public void setNum(Double num) {
        this.num = num;
        setCost(this.num, this.price);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(Double num, Double price) {
        BigDecimal b_num = new BigDecimal(RmUtils.toString(num, "0"));
        BigDecimal b_price = new BigDecimal(RmUtils.toString(price, "0"));
        this.cost = b_num.multiply(b_price).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * get the hasCollect - 是否已汇总
     * @return the hasCollect
     */
    public Integer getHasCollect() {
        return this.hasCollect;
    }

    /**
     * set the hasCollect - 是否已汇总
     */
    public void setHasCollect(Integer hasCollect) {
        this.hasCollect = hasCollect;
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

    public String getPurchaseDeptNum() {
        return purchaseDeptNum;
    }

    public void setPurchaseDeptNum(String purchaseDeptNum) {
        this.purchaseDeptNum = purchaseDeptNum;
    }

    public String getPurchaseDeptName() {
        return purchaseDeptName;
    }

    public void setPurchaseDeptName(String purchaseDeptName) {
        this.purchaseDeptName = purchaseDeptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAllotTime() {
        return allotTime;
    }

    public void setAllotTime(String allotTime) {
        this.allotTime = allotTime;
    }

    public String getAllotManNo() {
        return allotManNo;
    }

    public void setAllotManNo(String allotManNo) {
        this.allotManNo = allotManNo;
    }

    public String getAllotManName() {
        return allotManName;
    }

    public void setAllotManName(String allotManName) {
        this.allotManName = allotManName;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecCreateTimeStr() {
        return recCreateTimeStr;
    }

    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(RmUtils.toString(map.get("id"), id));
        setPlanId(RmUtils.toString(map.get("planId"), planId));
        setPlanNo(RmUtils.toString(map.get("planNo"), planNo));
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
        setHasCollect(NumberUtils.toInteger(StringUtils.toString(map.get("hasCollect")), hasCollect));
        setPictureUri(RmUtils.toString(map.get("pictureUri"), pictureUri));
        setPurchaseDeptNum(RmUtils.toString(map.get("purchaseDeptNum"), purchaseDeptNum));
        setPurchaseDeptName(RmUtils.toString(map.get("purchaseDeptName"), purchaseDeptName));
        setDeptName(RmUtils.toString(map.get("deptName"), deptName));
        setSignature(RmUtils.toString(map.get("signature"), signature));
        setAllotTime(RmUtils.toString(map.get("allotTime"), allotTime));
        setAllotManNo(RmUtils.toString(map.get("allotManNo"), allotManNo));
        setAllotManName(RmUtils.toString(map.get("allotManName"), allotManName));
        setBackReason(RmUtils.toString(map.get("backReason"), backReason));
        setStatus(RmUtils.toString(map.get("status"), status));
        setRecCreateTimeStr(RmUtils.toString(map.get("recCreateTimeStr"), recCreateTimeStr));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("planId",StringUtils.toString(planId, eiMetadata.getMeta("planId")));
        map.put("planNo",StringUtils.toString(planNo, eiMetadata.getMeta("planNo")));
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
        map.put("hasCollect",StringUtils.toString(hasCollect, eiMetadata.getMeta("hasCollect")));
        map.put("pictureUri",StringUtils.toString(pictureUri, eiMetadata.getMeta("pictureUri")));
        map.put("purchaseDeptNum",StringUtils.toString(purchaseDeptNum, eiMetadata.getMeta("purchaseDeptNum")));
        map.put("purchaseDeptName",StringUtils.toString(purchaseDeptName, eiMetadata.getMeta("purchaseDeptName")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("signature",StringUtils.toString(signature, eiMetadata.getMeta("signature")));
        map.put("allotTime",StringUtils.toString(allotTime, eiMetadata.getMeta("allotTime")));
        map.put("allotManNo",StringUtils.toString(allotManNo, eiMetadata.getMeta("allotManNo")));
        map.put("allotManName",StringUtils.toString(allotManName, eiMetadata.getMeta("allotManName")));
        map.put("backReason",StringUtils.toString(backReason, eiMetadata.getMeta("backReason")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        return map;
    }
}
