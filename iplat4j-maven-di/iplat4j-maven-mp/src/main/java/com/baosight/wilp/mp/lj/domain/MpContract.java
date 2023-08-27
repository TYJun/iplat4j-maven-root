package com.baosight.wilp.mp.lj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.utils.UUID;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* MpContract
* 
*/
public class MpContract extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id ;

    /**对接系统的合同ID**/
    private String dockContId;

    /**合同号*/
    @NotBlank(message = "合同号不能为空")
    private String contNo ;
    /**合同号*/
    @NotBlank(message = "项目号不能为空")
    private String itemNum ;
    /**合同名称*/
    @NotBlank(message = "合同名称不能为空")
    private String contName ;

    /**状态编码*/
    private String statusCode ;

    /**状态编码集合*/
    private List<String> statusCodes ;


    /**状态名称*/
    private String statusName ;

    /**合同分类*/
    @NotBlank(message = "合同分类不能为空")
    private String contClassify ;

    /**合同类型*/
    @NotBlank(message = "合同类型不能为空")
    private String contType ;

    /**供应商编码*/
    private String supplierNum ;

    /**供应商名称*/
    @NotNull(message = "供应商名称不能为空")
    private String supplierName ;

    /**合同金额*/
    @DecimalMin(value="0",message = "合同金额不能为空")
    private BigDecimal contCost = new BigDecimal("0");

    /**合同签订日期*/
    @NotNull(message = "合同签订日期不能为空")
    private Date signDate;

    /**合同生效日期*/
    @NotNull(message = "合同终止日期不能为空")
    private Date validDate;

    /**合同终止日期*/
    private Date overDate;

    /**币种编码*/
    private String currencyCode ;

    /**币种名称*/
    private String currencyName ;

    /**所属（管理）科室编码*/
    private String manageDeptNum ;

    /**所属（管理）科室名称*/
    @NotNull(message = "合同所属部门不能为空")
    private String manageDeptName ;

    /**管理员（负责人）工号*/
    private String managerNum ;

    /**管理员（负责人）名称*/
    @NotNull(message = "合同管理员不能为空")
    private String managerName ;

    /**采购方式编码*/
    private String purchaseWay ;

    /**采购方式名称*/
    private String purchaseWayName ;

    /**付款方式编码*/
    private String payWay ;

    /**付款方式名称*/
    private String payWayName ;

    /**合同期效（年）*/
    private Integer validLimit = new Integer(0);

    /**合同说明*/
    private String remark ;

    /**创建人*/
    private String recCreator ;

    /**创建人姓名*/
    private String recCreatorName ;

    /**创建时间*/
    private Date recCreateTime ;

    /**修改人*/
    private String recRevisor ;

    /**修改时间*/
    private Date recReviseTime ;

    /**账套*/
    private String dataGroupCode ;

    /**归档标记*/
    private String archiveFlag ;

    private String fundingSourceNum ;

    private String fundingSourceName ;
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dockContId");
        eiColumn.setDescName("对接系统的合同ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemNum");
        eiColumn.setDescName("项目号");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("contName");
        eiColumn.setDescName("合同名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contClassify");
        eiColumn.setDescName("合同分类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contType");
        eiColumn.setDescName("合同类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplierName");
        eiColumn.setDescName("供应商名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contCost");
        //eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("合同金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("signDate");
        eiColumn.setDescName("合同签订日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("validDate");
        eiColumn.setDescName("合同生效日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("overDate");
        eiColumn.setDescName("合同终止日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyCode");
        eiColumn.setDescName("币种编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currencyName");
        eiColumn.setDescName("币种名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageDeptNum");
        eiColumn.setDescName("所属（管理）科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageDeptName");
        eiColumn.setDescName("所属（管理）科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerNum");
        eiColumn.setDescName("管理员（负责人）工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerName");
        eiColumn.setDescName("管理员（负责人）名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseWay");
        eiColumn.setDescName("采购方式编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purchaseWayName");
        eiColumn.setDescName("采购方式名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payWay");
        eiColumn.setDescName("付款方式编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payWayName");
        eiColumn.setDescName("付款方式名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("validLimit");
        eiColumn.setDescName("合同期效（年）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("合同说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建人姓名");
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

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingSourceNum");
        eiColumn.setDescName("资金来源编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fundingSourceName");
        eiColumn.setDescName("资金来源名称");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public MpContract() {
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
     * get the dockContId - 对接系统的合同ID
     * @return the dockContId
     */
    public String getDockContId() {
        return this.dockContId;
    }

    /**
     * set the dockContId - 对接系统的合同ID
     */
    public void setDockContId(String dockContId) {
        this.dockContId = dockContId;
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
    /*/
    get the itemNum - 项目号
        @return the itemNum
     */
    public String getItemNum() {
        return this.itemNum;
    }
    /*/
        set the itemNum - 项目号
            @return the itemNum
         */
    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * get the contName - 合同名称
     * @return the contName
     */
    public String getContName() {
        return this.contName;
    }

    /**
     * set the contName - 合同名称
     */
    public void setContName(String contName) {
        this.contName = contName;
    }

    /**
     * get the statusCodes - 状态编码集合
     * @return the statusCodes
     */
    public List<String> getStatusCodes() {
        return this.statusCodes;
    }

    /**
     * set the statusCodes - 状态编码集合
     */
    public void setStatusCodes(List<String> statusCodes) {
        this.statusCodes = statusCodes;
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
     * get the contClassify - 合同分类
     * @return the contClassify
     */
    public String getContClassify() {
        return this.contClassify;
    }

    /**
     * set the contClassify - 合同分类
     */
    public void setContClassify(String contClassify) {
        this.contClassify = contClassify;
    }

    /**
     * get the contType - 合同类型
     * @return the contType
     */
    public String getContType() {
        return this.contType;
    }

    /**
     * set the contType - 合同类型
     */
    public void setContType(String contType) {
        this.contType = contType;
    }

    /**
     * get the supplierNum - 供应商编码
     * @return the supplierNum
     */
    public String getSupplierNum() {
        return this.supplierNum;
    }

    /**
     * set the supplierNum - 供应商编码
     */
    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }

    /**
     * get the supplierName - 供应商名称
     * @return the supplierName
     */
    public String getSupplierName() {
        return this.supplierName;
    }

    /**
     * set the supplierName - 供应商名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * get the contCost - 合同金额
     * @return the contCost
     */
    public BigDecimal getContCost() {
        return this.contCost;
    }

    /**
     * set the contCost - 合同金额
     */
    public void setContCost(BigDecimal contCost) {
        this.contCost = contCost;
    }

    /**
     * get the signDate - 合同签订日期
     * @return the signDate
     */
    public Date getSignDate() {
        return this.signDate;
    }

    /**
     * set the signDate - 合同签订日期
     */
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     * get the validDate - 合同生效日期
     * @return the validDate
     */
    public Date getValidDate() {
        return this.validDate;
    }

    /**
     * set the validDate - 合同生效日期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * get the overDate - 合同终止日期
     * @return the overDate
     */
    public Date getOverDate() {
        return this.overDate;
    }

    /**
     * set the overDate - 合同终止日期
     */
    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }

    /**
     * get the currencyCode - 币种编码
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * set the currencyCode - 币种编码
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * get the currencyName - 币种名称
     * @return the currencyName
     */
    public String getCurrencyName() {
        return this.currencyName;
    }

    /**
     * set the currencyName - 币种名称
     */
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * get the manageDeptNum - 所属（管理）科室编码
     * @return the manageDeptNum
     */
    public String getManageDeptNum() {
        return this.manageDeptNum;
    }

    /**
     * set the manageDeptNum - 所属（管理）科室编码
     */
    public void setManageDeptNum(String manageDeptNum) {
        this.manageDeptNum = manageDeptNum;
    }

    /**
     * get the manageDeptName - 所属（管理）科室名称
     * @return the manageDeptName
     */
    public String getManageDeptName() {
        return this.manageDeptName;
    }

    /**
     * set the manageDeptName - 所属（管理）科室名称
     */
    public void setManageDeptName(String manageDeptName) {
        this.manageDeptName = manageDeptName;
    }

    /**
     * get the managerNum - 管理员（负责人）工号
     * @return the managerNum
     */
    public String getManagerNum() {
        return this.managerNum;
    }

    /**
     * set the managerNum - 管理员（负责人）工号
     */
    public void setManagerNum(String managerNum) {
        this.managerNum = managerNum;
    }

    /**
     * get the managerName - 管理员（负责人）名称
     * @return the managerName
     */
    public String getManagerName() {
        return this.managerName;
    }

    /**
     * set the managerName - 管理员（负责人）名称
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * get the purchaseWay - 采购方式编码
     * @return the purchaseWay
     */
    public String getPurchaseWay() {
        return this.purchaseWay;
    }

    /**
     * set the purchaseWay - 采购方式编码
     */
    public void setPurchaseWay(String purchaseWay) {
        this.purchaseWay = purchaseWay;
    }

    /**
     * get the purchaseWayName - 采购方式名称
     * @return the purchaseWayName
     */
    public String getPurchaseWayName() {
        return this.purchaseWayName;
    }

    /**
     * set the purchaseWayName - 采购方式名称
     */
    public void setPurchaseWayName(String purchaseWayName) {
        this.purchaseWayName = purchaseWayName;
    }

    /**
     * get the payWay - 付款方式编码
     * @return the payWay
     */
    public String getPayWay() {
        return this.payWay;
    }

    /**
     * set the payWay - 付款方式编码
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    /**
     * get the payWayName - 付款方式名称
     * @return the payWayName
     */
    public String getPayWayName() {
        return this.payWayName;
    }

    /**
     * set the payWayName - 付款方式名称
     */
    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    /**
     * get the validLimit - 合同期效（年）
     * @return the validLimit
     */
    public Integer getValidLimit() {
        return this.validLimit;
    }

    /**
     * set the validLimit - 合同期效（年）
     */
    public void setValidLimit(Integer validLimit) {
        this.validLimit = validLimit;
    }

    /**
     * get the remark - 合同说明
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 合同说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreatorName - 创建人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

    /**
     * set the recCreatorName - 创建人姓名
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
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
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

    public String getFundingSourceNum() {
        return this.fundingSourceNum;
    }

    public void setFundingSourceNum(String fundingSourceNum) {
        this.fundingSourceNum = fundingSourceNum;
    }

    public String getFundingSourceName() {
        return this.fundingSourceName;
    }

    public void setFundingSourceName(String fundingSourceName) {
        this.fundingSourceName = fundingSourceName;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(MpUtils.toString(map.get("id"), id));
        setDockContId(MpUtils.toString(map.get("dockContId"), dockContId));
        setItemNum(MpUtils.toString(map.get("itemNum"), itemNum));
        setContNo(MpUtils.toString(map.get("contNo"), contNo));
        setContName(MpUtils.toString(map.get("contName"), contName));
        setStatusCode(MpUtils.toString(map.get("statusCode"), statusCode));
        setStatusName(MpUtils.toString(map.get("statusName"), statusName));
        setContClassify(MpUtils.toString(map.get("contClassify"), contClassify));
        setContType(MpUtils.toString(map.get("contType"), contType));
        setSupplierNum(MpUtils.toString(map.get("supplierNum"), supplierNum));
        setSupplierName(MpUtils.toString(map.get("supplierName"), supplierName));
        setContCost(NumberUtils.toBigDecimal(StringUtils.toString(map.get("contCost")), contCost));
        setSignDate(DateUtils.toDate(StringUtils.toString(map.get("signDate"))));
        setValidDate(DateUtils.toDate(StringUtils.toString(map.get("validDate"))));
        setOverDate(DateUtils.toDate(StringUtils.toString(map.get("overDate"))));
        setCurrencyCode(MpUtils.toString(map.get("currencyCode"), currencyCode));
        setCurrencyName(MpUtils.toString(map.get("currencyName"), currencyName));
        setManageDeptNum(MpUtils.toString(map.get("manageDeptNum"), manageDeptNum));
        setManageDeptName(MpUtils.toString(map.get("manageDeptName"), manageDeptName));
        setManagerNum(MpUtils.toString(map.get("managerNum"), managerNum));
        setManagerName(MpUtils.toString(map.get("managerName"), managerName));
        setPurchaseWay(MpUtils.toString(map.get("purchaseWay"), purchaseWay));
        setPurchaseWayName(MpUtils.toString(map.get("purchaseWayName"), purchaseWayName));
        setPayWay(MpUtils.toString(map.get("payWay"), payWay));
        setPayWayName(MpUtils.toString(map.get("payWayName"), payWayName));
        setValidLimit(NumberUtils.toInteger(StringUtils.toString(map.get("validLimit")), validLimit));
        setRemark(MpUtils.toString(map.get("remark"), remark));
        setRecCreator(MpUtils.toString(map.get("recCreator"), recCreator));
        setRecCreatorName(MpUtils.toString(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(MpUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(MpUtils.toString(map.get("recRevisor"), recRevisor));
        setRecReviseTime(MpUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(MpUtils.toString(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(MpUtils.toString(map.get("archiveFlag"), archiveFlag));
        setFundingSourceNum(MpUtils.toString(map.get("fundingSourceNum"), fundingSourceNum));
        setFundingSourceName(MpUtils.toString(map.get("fundingSourceName"), fundingSourceName));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("dockContId",StringUtils.toString(dockContId, eiMetadata.getMeta("dockContId")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("contName",StringUtils.toString(contName, eiMetadata.getMeta("contName")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("contClassify",StringUtils.toString(contClassify, eiMetadata.getMeta("contClassify")));
        map.put("contType",StringUtils.toString(contType, eiMetadata.getMeta("contType")));
        map.put("supplierNum",StringUtils.toString(supplierNum, eiMetadata.getMeta("supplierNum")));
        map.put("supplierName",StringUtils.toString(supplierName, eiMetadata.getMeta("supplierName")));
        map.put("contCost",StringUtils.toString(contCost, eiMetadata.getMeta("contCost")));
        map.put("signDate",StringUtils.toString(signDate, eiMetadata.getMeta("signDate")));
        map.put("validDate",StringUtils.toString(validDate, eiMetadata.getMeta("validDate")));
        map.put("overDate",StringUtils.toString(overDate, eiMetadata.getMeta("overDate")));
        map.put("currencyCode",StringUtils.toString(currencyCode, eiMetadata.getMeta("currencyCode")));
        map.put("currencyName",StringUtils.toString(currencyName, eiMetadata.getMeta("currencyName")));
        map.put("manageDeptNum",StringUtils.toString(manageDeptNum, eiMetadata.getMeta("manageDeptNum")));
        map.put("manageDeptName",StringUtils.toString(manageDeptName, eiMetadata.getMeta("manageDeptName")));
        map.put("managerNum",StringUtils.toString(managerNum, eiMetadata.getMeta("managerNum")));
        map.put("managerName",StringUtils.toString(managerName, eiMetadata.getMeta("managerName")));
        map.put("purchaseWay",StringUtils.toString(purchaseWay, eiMetadata.getMeta("purchaseWay")));
        map.put("purchaseWayName",StringUtils.toString(purchaseWayName, eiMetadata.getMeta("purchaseWayName")));
        map.put("payWay",StringUtils.toString(payWay, eiMetadata.getMeta("payWay")));
        map.put("payWayName",StringUtils.toString(payWayName, eiMetadata.getMeta("payWayName")));
        map.put("validLimit",StringUtils.toString(validLimit, eiMetadata.getMeta("validLimit")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("fundingSourceNum",StringUtils.toString(fundingSourceNum, eiMetadata.getMeta("fundingSourceNum")));
        map.put("fundingSourceName",StringUtils.toString(fundingSourceName, eiMetadata.getMeta("fundingSourceName")));
        map.put("itemNum",StringUtils.toString(itemNum, eiMetadata.getMeta("itemNum")));
        return map;
    }

    /**
     * 构建合同对象
     * @Title: getStatusInstance
     * @param id id
     * @param statusCode statusCode
     * @return com.baosight.wilp.mp.lj.domain.MpContract
     * @throws
     **/
    public static MpContract getStatusInstance(String id, String statusCode) {
        MpContract contract = new MpContract();
        contract.setId(id);
        contract.setStatusCode(statusCode);
        contract.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.contract.Status", contract.getStatusCode()));
        contract.setRecRevisor(UserSession.getLoginName());
        contract.setRecReviseTime(new Date());
        return contract;
    }
}