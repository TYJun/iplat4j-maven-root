package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 需求计划汇总实体
 * MpRequireCollect
 * @author fangjian
 */
public class MpRequireCollect extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id;

    /** 需求计划汇总单号*/
    private String collectNo;

    /** 01=年度，02=月度, 03=临时，04=混合*/
    private String collectType = "04";

    /** 状态编码*/
    private String statusCode;

    /** 状态名称*/
    private String statusName;

    /** 物资汇总总数*/
    private Double collectNum = new Double(0.00);

    /** 汇总总金额*/
    private BigDecimal collectCost = new BigDecimal("0.00");

    /**科室编码*/
    private String deptNum;

    /**科室名称*/
    private String deptName;

    /** 创建（汇总）人*/
    private String recCreator;

    /** 创建（汇总）人姓名*/
    private String recCreatorName;

    /** 创建(汇总)时间*/
    private Date recCreateTime;

    /** 创建(汇总)时间*/
    private String recCreateTimeStr;

    /** 修改人*/
    private String recRevisor;

    /** 修改时间*/
    private Date recReviseTime;

    /** 账套*/
    private String dataGroupCode;

    /** 归档标记*/
    private String archiveFlag;

    /**采购类别0-分散类采购， 1-集中性采购**/
    private String purchaseType;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("collectNo");
        eiColumn.setDescName("需求计划汇总单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("collectType");
        eiColumn.setDescName("0=年度，1=临时");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("collectNum");
        eiColumn.setDescName("物资汇总总数");
        eiColumn.setScaleLength(2);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("collectCost");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("汇总总金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建（汇总）人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建（汇总）人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建(汇总)时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTimeStr");
        eiColumn.setDescName("创建(汇总)时间字符串");
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

        eiColumn = new EiColumn("purchaseType");
        eiColumn.setDescName("采购类别0-分散类采购， 1-集中性采购");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MpRequireCollect() {
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
     * get the collectNo - 需求计划汇总单号
     * @return the collectNo
     */
    public String getCollectNo() {
        return this.collectNo;
    }

    /**
     * set the collectNo - 需求计划汇总单号
     */
    public void setCollectNo(String collectNo) {
        this.collectNo = collectNo;
    }

    /**
     * get the collectType - 0=年度，1=临时
     * @return the collectType
     */
    public String getCollectType() {
        return this.collectType;
    }

    /**
     * set the collectType - 0=年度，1=临时
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
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
     * get the collectNum - 物资汇总总数
     * @return the collectNum
     */
    public Double getCollectNum() {
        return this.collectNum;
    }

    /**
     * set the collectNum - 物资汇总总数
     */
    public void setCollectNum(Double collectNum) {
        this.collectNum = collectNum;
    }

    /**
     * get the collectCost - 汇总总金额
     * @return the collectCost
     */
    public BigDecimal getCollectCost() {
        return this.collectCost;
    }

    /**
     * set the collectCost - 汇总总金额
     */
    public void setCollectCost(BigDecimal collectCost) {
        this.collectCost = collectCost;
    }

    /**
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the deptName - 科室名称
     * @return the deptName
     */
    public String getDeptName() { return this.deptName;}

    /**
     * get the recCreator - 创建（汇总）人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建（汇总）人
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreatorName - 创建（汇总）人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

    /**
     * set the recCreatorName - 创建（汇总）人姓名
     */
    public void setRecCreatorName(String recCreatorName) {
        this.recCreatorName = recCreatorName;
    }

    /**
     * get the recCreateTime - 创建(汇总)时间
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

    /**
     * get the purchaseType - 采购类别0-分散类采购， 1-集中性采购
     * @return the purchaseType
     */
    public String getPurchaseType() {
        return purchaseType;
    }

    /**
     * set the purchaseType - 采购类别0-分散类采购， 1-集中性采购
     */
    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setCollectNo(MpUtils.toString(map.get("collectNo"), collectNo));
        setCollectType(MpUtils.toString(map.get("collectType"), collectType));
        setStatusCode(MpUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(MpUtils.toString(map.get("statusName"), statusName));
        setCollectNum(NumberUtils.toDouble(StringUtils.toString(map.get("collectNum")), collectNum));
        setCollectCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("collectCost")), collectCost));
        setDeptNum(MpUtils.toString(map.get("deptNum"), deptNum));
        setDeptName(MpUtils.toString(map.get("deptName"), deptName));
        setRecCreator(MpUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(MpUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(MpUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(MpUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(MpUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(MpUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(MpUtils.toString(map.get("archiveFlag"), archiveFlag));
        setPurchaseType(MpUtils.toString(map.get("purchaseType"), purchaseType));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("collectNo",StringUtils.toString(collectNo, eiMetadata.getMeta("collectNo")));
        map.put("collectType",StringUtils.toString(collectType, eiMetadata.getMeta("collectType")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("collectNum",StringUtils.toString(collectNum, eiMetadata.getMeta("collectNum")));
        map.put("collectCost",StringUtils.toString(collectCost, eiMetadata.getMeta("collectCost")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("purchaseType",StringUtils.toString(purchaseType, eiMetadata.getMeta("purchaseType")));
        return map;
    }

    /**
     * 构建需求汇总对象
     * @Title: getStatusInstance
     * @param collectId collectId
     * @param statusCode statusCode
     * @return com.baosight.wilp.mp.lj.domain.MpRequireCollect
     * @throws
     **/
    public static MpRequireCollect getStatusInstance(String collectId, String statusCode) {
        MpRequireCollect collect = new MpRequireCollect();
        collect.setId(collectId);
        collect.setStatusCode(statusCode);
        collect.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.require.collectStatus", collect.getStatusCode()));
        collect.setRecReviseTime(new Date());
        collect.setRecRevisor(UserSession.getLoginName());
        return collect;
    }

}