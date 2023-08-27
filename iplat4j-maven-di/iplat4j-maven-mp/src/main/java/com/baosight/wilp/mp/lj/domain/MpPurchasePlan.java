package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购计划实体
 * MpPurchasePlan
 * @author fangjian
 */
public class MpPurchasePlan extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**来源单据ID(需求汇总ID/补库计划ID)*/
    private String resourceId ;

    /**采购计划单号*/
    private String purchaseNo ;

    /**状态编码*/
    private String statusCode ;

    /**状态名称*/
    private String statusName ;

    /**采购物资总数*/
    private Double purchaseNum = new Double(0.00);

    /**总金额*/
    private BigDecimal purchaseCost = new BigDecimal("0.00");

    /**专业科室编码*/
    private String deptNum ;

    /**专业科室编码*/
    private String deptName ;

    /**备注*/
    private String remark="" ;

    /**采购计划描述**/
    private String purchaseRemark;

    /**创建（申请）人*/
    private String recCreator ;

    /**创建（申请）人姓名*/
    private String recCreatorName ;

    /**创建时间*/
    private Date recCreateTime ;

    /**创建时间*/
    private String recCreateTimeStr ;

    /**修改人*/
    private String recRevisor ;

    /**修改时间*/
    private Date recReviseTime ;

    /**账套*/
    private String dataGroupCode ;

    /**归档标记*/
    private String archiveFlag ;

    /**采购计划明细集合**/
    private List<MpPurchasePlanDetail> details;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("resourceId");
        eiColumn.setDescName("来源单据ID(需求汇总ID/补库计划ID)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseNo");
        eiColumn.setDescName("采购计划单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseNum");
        eiColumn.setDescName("采购物资总数");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("专业科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("专业科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseRemark");
        eiColumn.setDescName("采购计划描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建（申请）人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建（申请）人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTimeStr");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public MpPurchasePlan() {
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
     * get the resourceId - 来源单据ID(需求汇总ID/补库计划ID)
     * @return the resourceId
     */
    public String getResourceId() {
        return this.resourceId;
    }

    /**
     * set the resourceId - 来源单据ID(需求汇总ID/补库计划ID)
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * get the purchaseNo - 采购计划单号
     * @return the purchaseNo
     */
    public String getPurchaseNo() {
        return this.purchaseNo;
    }

    /**
     * set the purchaseNo - 采购计划单号
     */
    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    /**
     * get the statusCode - 状态编码
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态编码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the statusName - 状态名称
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

    /**
     * set the statusName - 状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * get the purchaseNum - 采购物资总数
     * @return the purchaseNum
     */
    public Double getPurchaseNum() {
        return this.purchaseNum;
    }

    /**
     * set the purchaseNum - 采购物资总数
     */
    public void setPurchaseNum(Double purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    /**
     * get the purchaseCost - 总金额
     * @return the purchaseCost
     */
    public BigDecimal getPurchaseCost() {
        return this.purchaseCost;
    }

    /**
     * set the purchaseCost - 总金额
     */
    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    /**
     * get the deptNum - 专业科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 专业科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 专业科室编码
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 专业科室编码
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the purchaseRemark - 采购计划描述
     * @return the purchaseRemark
     */
    public String getPurchaseRemark() {
        return this.purchaseRemark;
    }

    /**
     * set the purchaseRemark - 采购计划描述
     */
    public void setPurchaseRemark(String purchaseRemark) {
        this.purchaseRemark = purchaseRemark;
    }

    /**
     * get the recCreator - 创建（申请）人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建（申请）人
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreatorName - 创建（申请）人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

    /**
     * set the recCreatorName - 创建（申请）人姓名
     */
    public void setRecCreatorName(String recCreatorName) {
        this.recCreatorName = recCreatorName;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建(汇总)时间
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
        if(this.recCreateTime != null) {
            setRecCreateTimeStr(DateUtils.toDateTimeStr19(this.recCreateTime));
        }
    }

    /**
     * get the recCreateTimeStr - 创建(汇总)时间
     * @return the recCreateTimeStr
     */
    public String getRecCreateTimeStr() {
        return this.recCreateTimeStr;
    }

    /**
     * set the recCreateTimeStr - 创建(汇总)时间
     */
    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Date getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Date recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    public List<MpPurchasePlanDetail> getDetails() {
        return details;
    }

    public void setDetails(List<MpPurchasePlanDetail> details) {
        this.details = details;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setResourceId(MpUtils.toString(map.get("resourceId"), resourceId));
        setPurchaseNo(MpUtils.toString(map.get("purchaseNo"), purchaseNo));
        setStatusCode(MpUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(MpUtils.toString(map.get("statusName"), statusName));
        setPurchaseNum(NumberUtils.toDouble(StringUtils.toString(map.get("purchaseNum")), purchaseNum));
        setPurchaseCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("purchaseCost")), purchaseCost));
        setDeptNum(MpUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(MpUtils.toString(map.get("deptName"), deptName));
        setRemark(MpUtils.toString(map.get("remark"), remark));
        setPurchaseRemark(MpUtils.toString(map.get("purchaseRemark"), purchaseRemark));
        setRecCreator(MpUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(MpUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(MpUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(MpUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(MpUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(MpUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(MpUtils.toString(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("resourceId",StringUtils.toString(resourceId, eiMetadata.getMeta("resourceId")));
        map.put("purchaseNo",StringUtils.toString(purchaseNo, eiMetadata.getMeta("purchaseNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("purchaseNum",StringUtils.toString(purchaseNum, eiMetadata.getMeta("purchaseNum")));
        map.put("purchaseCost",StringUtils.toString(purchaseCost, eiMetadata.getMeta("purchaseCost")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("purchaseRemark",StringUtils.toString(purchaseRemark, eiMetadata.getMeta("purchaseRemark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }

    public static MpPurchasePlan getStatusInstant(String id, String statusCode) {
        MpPurchasePlan plan = new MpPurchasePlan();
        plan.setId(id);
        plan.setStatusCode(statusCode);
        plan.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.purchase.planStatus", statusCode));
        plan.setRecReviseTime(new Date());
        return plan;
    }

}