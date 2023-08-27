/**
* Generate time : 2021-07-15 17:26:50
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.sb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfSpecialDevice
* 
*/
public class DFSB01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 设备ID*/
    private String machineCode = " ";		/* 设备编码*/
    private String machineName = " ";		/* 设备名称*/
    private String machineTypeId = " ";		/* 设备分类ID*/
    private String models = " ";		/* 规格型号*/
    private String statusCode = " ";		/* 状态代码（0=新建 1=启用，-1=停用）*/
    private String fixedId = " ";		/* 安装地点id*/
    private String fixedPlace = " ";		/* 安装地点*/
    private String innerMachineCode = " ";		/* 内部设备编码*/
    private String documentNo = " ";		/* 档案号*/
    private String workMedia = " ";		/* 工作介质*/
    private String useCertNo = " ";		/* 使用证编号*/
    private String useArea = " ";		/* 使用范围*/
    private String registerCode = " ";		/* 注册代码*/
    private String registerDate = " ";		/* 注册登记日期*/
    private String outFactoryDate = " ";		/* 出厂日期*/
    private String fixedTime = " ";		/* 安装日期*/
    private String useTime = " ";		/* 使用日期*/
    private String nonuseDate = " ";		/* 停用日期*/
    private String managerDeptId = " ";		/* 管理科室ID*/
    private String managerDeptName = " ";		/* 管理科室名称*/
    private String managerManId = " ";		/* 管理员ID*/
    private String managerManName = " ";		/* 管理员名称*/
    private String chargeUserId = " ";		/* 负责人ID*/
    private String chargeUserName = " ";		/* 负责人名称*/
    private String useDeaprtId = " ";		/* 使用科室ID*/
    private String useDeaprtName = " ";		/* 使用科室名称*/
    private String relatedDevice = " ";		/* 关联设备*/
    private String useFor = " ";		/* 用途*/
    private String memo = " ";		/* 备注*/
    private String needScan = " ";		/* 是否扫二维码（N=否，Y=是）*/
    private String manufacturerName = " ";		/* 制造单位*/
    private String manufacturerCertno = " ";		/* 制造单位资格证号*/
    private String fixedUnit = " ";		/* 安装单位*/
    private String fixedCertno = " ";		/* 安装单位资格证号*/
    private String maintUnit = " ";		/* 维保单位*/
    private String maintCertno = " ";		/* 维保单位资格证号*/
    private String checkUnit = " ";		/* 检验单位*/
    private String checkCertno = " ";		/* 检验单位资格证号*/
    private String thisCheckDate = " ";		/* 本次年度检验日*/
    private String thisFinishDate = " ";		/* 本次年度检验完工日*/
    private String nextCheckDate = " ";		/* 下次年度检验日*/
    private Integer annualinspcycle = new Integer(0);		/* 周期(月)-年检*/
    private String thisExpiredDate = " ";		/* 本次到期检验日*/
    private String thisChexpiredDate = " ";		/* 本次到期检验完工日*/
    private String nextExpiredDate = " ";		/* 下次到期检验日*/
    private Integer regularinspcycle = new Integer(0);		/* 周期(月)-定检*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String dataGroupCode = " ";		/* 账套*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("设备ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineCode");
        eiColumn.setDescName("设备编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineName");
        eiColumn.setDescName("设备名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineTypeId");
        eiColumn.setDescName("设备分类ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("models");
        eiColumn.setDescName("规格型号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态代码（0=新建 1=启用，-1=停用）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fixedId");
        eiColumn.setDescName("安装地点id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fixedPlace");
        eiColumn.setDescName("安装地点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("innerMachineCode");
        eiColumn.setDescName("内部设备编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("documentNo");
        eiColumn.setDescName("档案号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workMedia");
        eiColumn.setDescName("工作介质");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("useCertNo");
        eiColumn.setDescName("使用证编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("useArea");
        eiColumn.setDescName("使用范围");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("registerCode");
        eiColumn.setDescName("注册代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("registerDate");
        eiColumn.setDescName("注册登记日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outFactoryDate");
        eiColumn.setDescName("出厂日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fixedTime");
        eiColumn.setDescName("安装日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("useTime");
        eiColumn.setDescName("使用日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nonuseDate");
        eiColumn.setDescName("停用日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerDeptId");
        eiColumn.setDescName("管理科室ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerDeptName");
        eiColumn.setDescName("管理科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerManId");
        eiColumn.setDescName("管理员ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerManName");
        eiColumn.setDescName("管理员名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("chargeUserId");
        eiColumn.setDescName("负责人ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("chargeUserName");
        eiColumn.setDescName("负责人名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("useDeaprtId");
        eiColumn.setDescName("使用科室ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("useDeaprtName");
        eiColumn.setDescName("使用科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("relatedDevice");
        eiColumn.setDescName("关联设备");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("useFor");
        eiColumn.setDescName("用途");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needScan");
        eiColumn.setDescName("是否扫二维码（N=否，Y=是）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manufacturerName");
        eiColumn.setDescName("制造单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manufacturerCertno");
        eiColumn.setDescName("制造单位资格证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fixedUnit");
        eiColumn.setDescName("安装单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fixedCertno");
        eiColumn.setDescName("安装单位资格证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("maintUnit");
        eiColumn.setDescName("维保单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("maintCertno");
        eiColumn.setDescName("维保单位资格证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkUnit");
        eiColumn.setDescName("检验单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkCertno");
        eiColumn.setDescName("检验单位资格证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisCheckDate");
        eiColumn.setDescName("本次年度检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisFinishDate");
        eiColumn.setDescName("本次年度检验完工日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nextCheckDate");
        eiColumn.setDescName("下次年度检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("annualinspcycle");
        eiColumn.setDescName("周期(月)-年检");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisExpiredDate");
        eiColumn.setDescName("本次到期检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisChexpiredDate");
        eiColumn.setDescName("本次到期检验完工日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nextExpiredDate");
        eiColumn.setDescName("下次到期检验日");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("regularinspcycle");
        eiColumn.setDescName("周期(月)-定检");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
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


    }

    /**
     * the constructor
     */
    public DFSB01() {
        initMetaData();
    }

    /**
     * get the id - 设备ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 设备ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the machineCode - 设备编码
     * @return the machineCode
     */
    public String getMachineCode() {
        return this.machineCode;
    }

    /**
     * set the machineCode - 设备编码
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * get the machineName - 设备名称
     * @return the machineName
     */
    public String getMachineName() {
        return this.machineName;
    }

    /**
     * set the machineName - 设备名称
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    /**
     * get the machineTypeId - 设备分类ID
     * @return the machineTypeId
     */
    public String getMachineTypeId() {
        return this.machineTypeId;
    }

    /**
     * set the machineTypeId - 设备分类ID
     */
    public void setMachineTypeId(String machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    /**
     * get the models - 规格型号
     * @return the models
     */
    public String getModels() {
        return this.models;
    }

    /**
     * set the models - 规格型号
     */
    public void setModels(String models) {
        this.models = models;
    }

    /**
     * get the statusCode - 状态代码（0=新建 1=启用，-1=停用）
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态代码（0=新建 1=启用，-1=停用）
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the fixedId - 安装地点id
     * @return the fixedId
     */
    public String getFixedId() {
        return this.fixedId;
    }

    /**
     * set the fixedId - 安装地点id
     */
    public void setFixedId(String fixedId) {
        this.fixedId = fixedId;
    }

    /**
     * get the fixedPlace - 安装地点
     * @return the fixedPlace
     */
    public String getFixedPlace() {
        return this.fixedPlace;
    }

    /**
     * set the fixedPlace - 安装地点
     */
    public void setFixedPlace(String fixedPlace) {
        this.fixedPlace = fixedPlace;
    }

    /**
     * get the innerMachineCode - 内部设备编码
     * @return the innerMachineCode
     */
    public String getInnerMachineCode() {
        return this.innerMachineCode;
    }

    /**
     * set the innerMachineCode - 内部设备编码
     */
    public void setInnerMachineCode(String innerMachineCode) {
        this.innerMachineCode = innerMachineCode;
    }

    /**
     * get the documentNo - 档案号
     * @return the documentNo
     */
    public String getDocumentNo() {
        return this.documentNo;
    }

    /**
     * set the documentNo - 档案号
     */
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    /**
     * get the workMedia - 工作介质
     * @return the workMedia
     */
    public String getWorkMedia() {
        return this.workMedia;
    }

    /**
     * set the workMedia - 工作介质
     */
    public void setWorkMedia(String workMedia) {
        this.workMedia = workMedia;
    }

    /**
     * get the useCertNo - 使用证编号
     * @return the useCertNo
     */
    public String getUseCertNo() {
        return this.useCertNo;
    }

    /**
     * set the useCertNo - 使用证编号
     */
    public void setUseCertNo(String useCertNo) {
        this.useCertNo = useCertNo;
    }

    /**
     * get the useArea - 使用范围
     * @return the useArea
     */
    public String getUseArea() {
        return this.useArea;
    }

    /**
     * set the useArea - 使用范围
     */
    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    /**
     * get the registerCode - 注册代码
     * @return the registerCode
     */
    public String getRegisterCode() {
        return this.registerCode;
    }

    /**
     * set the registerCode - 注册代码
     */
    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    /**
     * get the registerDate - 注册登记日期
     * @return the registerDate
     */
    public String getRegisterDate() {
        return this.registerDate;
    }

    /**
     * set the registerDate - 注册登记日期
     */
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * get the outFactoryDate - 出厂日期
     * @return the outFactoryDate
     */
    public String getOutFactoryDate() {
        return this.outFactoryDate;
    }

    /**
     * set the outFactoryDate - 出厂日期
     */
    public void setOutFactoryDate(String outFactoryDate) {
        this.outFactoryDate = outFactoryDate;
    }

    /**
     * get the fixedTime - 安装日期
     * @return the fixedTime
     */
    public String getFixedTime() {
        return this.fixedTime;
    }

    /**
     * set the fixedTime - 安装日期
     */
    public void setFixedTime(String fixedTime) {
        this.fixedTime = fixedTime;
    }

    /**
     * get the useTime - 使用日期
     * @return the useTime
     */
    public String getUseTime() {
        return this.useTime;
    }

    /**
     * set the useTime - 使用日期
     */
    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    /**
     * get the nonuseDate - 停用日期
     * @return the nonuseDate
     */
    public String getNonuseDate() {
        return this.nonuseDate;
    }

    /**
     * set the nonuseDate - 停用日期
     */
    public void setNonuseDate(String nonuseDate) {
        this.nonuseDate = nonuseDate;
    }

    /**
     * get the managerDeptId - 管理科室ID
     * @return the managerDeptId
     */
    public String getManagerDeptId() {
        return this.managerDeptId;
    }

    /**
     * set the managerDeptId - 管理科室ID
     */
    public void setManagerDeptId(String managerDeptId) {
        this.managerDeptId = managerDeptId;
    }

    /**
     * get the managerDeptName - 管理科室名称
     * @return the managerDeptName
     */
    public String getManagerDeptName() {
        return this.managerDeptName;
    }

    /**
     * set the managerDeptName - 管理科室名称
     */
    public void setManagerDeptName(String managerDeptName) {
        this.managerDeptName = managerDeptName;
    }

    /**
     * get the managerManId - 管理员ID
     * @return the managerManId
     */
    public String getManagerManId() {
        return this.managerManId;
    }

    /**
     * set the managerManId - 管理员ID
     */
    public void setManagerManId(String managerManId) {
        this.managerManId = managerManId;
    }

    /**
     * get the managerManName - 管理员名称
     * @return the managerManName
     */
    public String getManagerManName() {
        return this.managerManName;
    }

    /**
     * set the managerManName - 管理员名称
     */
    public void setManagerManName(String managerManName) {
        this.managerManName = managerManName;
    }

    /**
     * get the chargeUserId - 负责人ID
     * @return the chargeUserId
     */
    public String getChargeUserId() {
        return this.chargeUserId;
    }

    /**
     * set the chargeUserId - 负责人ID
     */
    public void setChargeUserId(String chargeUserId) {
        this.chargeUserId = chargeUserId;
    }

    /**
     * get the chargeUserName - 负责人名称
     * @return the chargeUserName
     */
    public String getChargeUserName() {
        return this.chargeUserName;
    }

    /**
     * set the chargeUserName - 负责人名称
     */
    public void setChargeUserName(String chargeUserName) {
        this.chargeUserName = chargeUserName;
    }

    /**
     * get the useDeaprtId - 使用科室ID
     * @return the useDeaprtId
     */
    public String getUseDeaprtId() {
        return this.useDeaprtId;
    }

    /**
     * set the useDeaprtId - 使用科室ID
     */
    public void setUseDeaprtId(String useDeaprtId) {
        this.useDeaprtId = useDeaprtId;
    }

    /**
     * get the useDeaprtName - 使用科室名称
     * @return the useDeaprtName
     */
    public String getUseDeaprtName() {
        return this.useDeaprtName;
    }

    /**
     * set the useDeaprtName - 使用科室名称
     */
    public void setUseDeaprtName(String useDeaprtName) {
        this.useDeaprtName = useDeaprtName;
    }

    /**
     * get the relatedDevice - 关联设备
     * @return the relatedDevice
     */
    public String getRelatedDevice() {
        return this.relatedDevice;
    }

    /**
     * set the relatedDevice - 关联设备
     */
    public void setRelatedDevice(String relatedDevice) {
        this.relatedDevice = relatedDevice;
    }

    /**
     * get the useFor - 用途
     * @return the useFor
     */
    public String getUseFor() {
        return this.useFor;
    }

    /**
     * set the useFor - 用途
     */
    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the needScan - 是否扫二维码（N=否，Y=是）
     * @return the needScan
     */
    public String getNeedScan() {
        return this.needScan;
    }

    /**
     * set the needScan - 是否扫二维码（N=否，Y=是）
     */
    public void setNeedScan(String needScan) {
        this.needScan = needScan;
    }

    /**
     * get the manufacturerName - 制造单位
     * @return the manufacturerName
     */
    public String getManufacturerName() {
        return this.manufacturerName;
    }

    /**
     * set the manufacturerName - 制造单位
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * get the manufacturerCertno - 制造单位资格证号
     * @return the manufacturerCertno
     */
    public String getManufacturerCertno() {
        return this.manufacturerCertno;
    }

    /**
     * set the manufacturerCertno - 制造单位资格证号
     */
    public void setManufacturerCertno(String manufacturerCertno) {
        this.manufacturerCertno = manufacturerCertno;
    }

    /**
     * get the fixedUnit - 安装单位
     * @return the fixedUnit
     */
    public String getFixedUnit() {
        return this.fixedUnit;
    }

    /**
     * set the fixedUnit - 安装单位
     */
    public void setFixedUnit(String fixedUnit) {
        this.fixedUnit = fixedUnit;
    }

    /**
     * get the fixedCertno - 安装单位资格证号
     * @return the fixedCertno
     */
    public String getFixedCertno() {
        return this.fixedCertno;
    }

    /**
     * set the fixedCertno - 安装单位资格证号
     */
    public void setFixedCertno(String fixedCertno) {
        this.fixedCertno = fixedCertno;
    }

    /**
     * get the maintUnit - 维保单位
     * @return the maintUnit
     */
    public String getMaintUnit() {
        return this.maintUnit;
    }

    /**
     * set the maintUnit - 维保单位
     */
    public void setMaintUnit(String maintUnit) {
        this.maintUnit = maintUnit;
    }

    /**
     * get the maintCertno - 维保单位资格证号
     * @return the maintCertno
     */
    public String getMaintCertno() {
        return this.maintCertno;
    }

    /**
     * set the maintCertno - 维保单位资格证号
     */
    public void setMaintCertno(String maintCertno) {
        this.maintCertno = maintCertno;
    }

    /**
     * get the checkUnit - 检验单位
     * @return the checkUnit
     */
    public String getCheckUnit() {
        return this.checkUnit;
    }

    /**
     * set the checkUnit - 检验单位
     */
    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    /**
     * get the checkCertno - 检验单位资格证号
     * @return the checkCertno
     */
    public String getCheckCertno() {
        return this.checkCertno;
    }

    /**
     * set the checkCertno - 检验单位资格证号
     */
    public void setCheckCertno(String checkCertno) {
        this.checkCertno = checkCertno;
    }

    /**
     * get the thisCheckDate - 本次年度检验日
     * @return the thisCheckDate
     */
    public String getThisCheckDate() {
        return this.thisCheckDate;
    }

    /**
     * set the thisCheckDate - 本次年度检验日
     */
    public void setThisCheckDate(String thisCheckDate) {
        this.thisCheckDate = thisCheckDate;
    }

    /**
     * get the thisFinishDate - 本次年度检验完工日
     * @return the thisFinishDate
     */
    public String getThisFinishDate() {
        return this.thisFinishDate;
    }

    /**
     * set the thisFinishDate - 本次年度检验完工日
     */
    public void setThisFinishDate(String thisFinishDate) {
        this.thisFinishDate = thisFinishDate;
    }

    /**
     * get the nextCheckDate - 下次年度检验日
     * @return the nextCheckDate
     */
    public String getNextCheckDate() {
        return this.nextCheckDate;
    }

    /**
     * set the nextCheckDate - 下次年度检验日
     */
    public void setNextCheckDate(String nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    /**
     * get the annualinspcycle - 周期(月)-年检
     * @return the annualinspcycle
     */
    public Integer getAnnualinspcycle() {
        return this.annualinspcycle;
    }

    /**
     * set the annualinspcycle - 周期(月)-年检
     */
    public void setAnnualinspcycle(Integer annualinspcycle) {
        this.annualinspcycle = annualinspcycle;
    }

    /**
     * get the thisExpiredDate - 本次到期检验日
     * @return the thisExpiredDate
     */
    public String getThisExpiredDate() {
        return this.thisExpiredDate;
    }

    /**
     * set the thisExpiredDate - 本次到期检验日
     */
    public void setThisExpiredDate(String thisExpiredDate) {
        this.thisExpiredDate = thisExpiredDate;
    }

    /**
     * get the thisChexpiredDate - 本次到期检验完工日
     * @return the thisChexpiredDate
     */
    public String getThisChexpiredDate() {
        return this.thisChexpiredDate;
    }

    /**
     * set the thisChexpiredDate - 本次到期检验完工日
     */
    public void setThisChexpiredDate(String thisChexpiredDate) {
        this.thisChexpiredDate = thisChexpiredDate;
    }

    /**
     * get the nextExpiredDate - 下次到期检验日
     * @return the nextExpiredDate
     */
    public String getNextExpiredDate() {
        return this.nextExpiredDate;
    }

    /**
     * set the nextExpiredDate - 下次到期检验日
     */
    public void setNextExpiredDate(String nextExpiredDate) {
        this.nextExpiredDate = nextExpiredDate;
    }

    /**
     * get the regularinspcycle - 周期(月)-定检
     * @return the regularinspcycle
     */
    public Integer getRegularinspcycle() {
        return this.regularinspcycle;
    }

    /**
     * set the regularinspcycle - 周期(月)-定检
     */
    public void setRegularinspcycle(Integer regularinspcycle) {
        this.regularinspcycle = regularinspcycle;
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
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
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
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineCode")), machineCode));
        setMachineName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineName")), machineName));
        setMachineTypeId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineTypeId")), machineTypeId));
        setModels(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("models")), models));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setFixedId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedId")), fixedId));
        setFixedPlace(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedPlace")), fixedPlace));
        setInnerMachineCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("innerMachineCode")), innerMachineCode));
        setDocumentNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("documentNo")), documentNo));
        setWorkMedia(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workMedia")), workMedia));
        setUseCertNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("useCertNo")), useCertNo));
        setUseArea(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("useArea")), useArea));
        setRegisterCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("registerCode")), registerCode));
        setRegisterDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("registerDate")), registerDate));
        setOutFactoryDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outFactoryDate")), outFactoryDate));
        setFixedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedTime")), fixedTime));
        setUseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("useTime")), useTime));
        setNonuseDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nonuseDate")), nonuseDate));
        setManagerDeptId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerDeptId")), managerDeptId));
        setManagerDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerDeptName")), managerDeptName));
        setManagerManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerManId")), managerManId));
        setManagerManName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerManName")), managerManName));
        setChargeUserId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("chargeUserId")), chargeUserId));
        setChargeUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("chargeUserName")), chargeUserName));
        setUseDeaprtId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("useDeaprtId")), useDeaprtId));
        setUseDeaprtName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("useDeaprtName")), useDeaprtName));
        setRelatedDevice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("relatedDevice")), relatedDevice));
        setUseFor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("useFor")), useFor));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setNeedScan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needScan")), needScan));
        setManufacturerName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturerName")), manufacturerName));
        setManufacturerCertno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manufacturerCertno")), manufacturerCertno));
        setFixedUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedUnit")), fixedUnit));
        setFixedCertno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fixedCertno")), fixedCertno));
        setMaintUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("maintUnit")), maintUnit));
        setMaintCertno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("maintCertno")), maintCertno));
        setCheckUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkUnit")), checkUnit));
        setCheckCertno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkCertno")), checkCertno));
        setThisCheckDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thisCheckDate")), thisCheckDate));
        setThisFinishDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thisFinishDate")), thisFinishDate));
        setNextCheckDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nextCheckDate")), nextCheckDate));
        setAnnualinspcycle(NumberUtils.toInteger(StringUtils.toString(map.get("annualinspcycle")), annualinspcycle));
        setThisExpiredDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thisExpiredDate")), thisExpiredDate));
        setThisChexpiredDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thisChexpiredDate")), thisChexpiredDate));
        setNextExpiredDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nextExpiredDate")), nextExpiredDate));
        setRegularinspcycle(NumberUtils.toInteger(StringUtils.toString(map.get("regularinspcycle")), regularinspcycle));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("machineCode",StringUtils.toString(machineCode, eiMetadata.getMeta("machineCode")));
        map.put("machineName",StringUtils.toString(machineName, eiMetadata.getMeta("machineName")));
        map.put("machineTypeId",StringUtils.toString(machineTypeId, eiMetadata.getMeta("machineTypeId")));
        map.put("models",StringUtils.toString(models, eiMetadata.getMeta("models")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("fixedId",StringUtils.toString(fixedId, eiMetadata.getMeta("fixedId")));
        map.put("fixedPlace",StringUtils.toString(fixedPlace, eiMetadata.getMeta("fixedPlace")));
        map.put("innerMachineCode",StringUtils.toString(innerMachineCode, eiMetadata.getMeta("innerMachineCode")));
        map.put("documentNo",StringUtils.toString(documentNo, eiMetadata.getMeta("documentNo")));
        map.put("workMedia",StringUtils.toString(workMedia, eiMetadata.getMeta("workMedia")));
        map.put("useCertNo",StringUtils.toString(useCertNo, eiMetadata.getMeta("useCertNo")));
        map.put("useArea",StringUtils.toString(useArea, eiMetadata.getMeta("useArea")));
        map.put("registerCode",StringUtils.toString(registerCode, eiMetadata.getMeta("registerCode")));
        map.put("registerDate",StringUtils.toString(registerDate, eiMetadata.getMeta("registerDate")));
        map.put("outFactoryDate",StringUtils.toString(outFactoryDate, eiMetadata.getMeta("outFactoryDate")));
        map.put("fixedTime",StringUtils.toString(fixedTime, eiMetadata.getMeta("fixedTime")));
        map.put("useTime",StringUtils.toString(useTime, eiMetadata.getMeta("useTime")));
        map.put("nonuseDate",StringUtils.toString(nonuseDate, eiMetadata.getMeta("nonuseDate")));
        map.put("managerDeptId",StringUtils.toString(managerDeptId, eiMetadata.getMeta("managerDeptId")));
        map.put("managerDeptName",StringUtils.toString(managerDeptName, eiMetadata.getMeta("managerDeptName")));
        map.put("managerManId",StringUtils.toString(managerManId, eiMetadata.getMeta("managerManId")));
        map.put("managerManName",StringUtils.toString(managerManName, eiMetadata.getMeta("managerManName")));
        map.put("chargeUserId",StringUtils.toString(chargeUserId, eiMetadata.getMeta("chargeUserId")));
        map.put("chargeUserName",StringUtils.toString(chargeUserName, eiMetadata.getMeta("chargeUserName")));
        map.put("useDeaprtId",StringUtils.toString(useDeaprtId, eiMetadata.getMeta("useDeaprtId")));
        map.put("useDeaprtName",StringUtils.toString(useDeaprtName, eiMetadata.getMeta("useDeaprtName")));
        map.put("relatedDevice",StringUtils.toString(relatedDevice, eiMetadata.getMeta("relatedDevice")));
        map.put("useFor",StringUtils.toString(useFor, eiMetadata.getMeta("useFor")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("needScan",StringUtils.toString(needScan, eiMetadata.getMeta("needScan")));
        map.put("manufacturerName",StringUtils.toString(manufacturerName, eiMetadata.getMeta("manufacturerName")));
        map.put("manufacturerCertno",StringUtils.toString(manufacturerCertno, eiMetadata.getMeta("manufacturerCertno")));
        map.put("fixedUnit",StringUtils.toString(fixedUnit, eiMetadata.getMeta("fixedUnit")));
        map.put("fixedCertno",StringUtils.toString(fixedCertno, eiMetadata.getMeta("fixedCertno")));
        map.put("maintUnit",StringUtils.toString(maintUnit, eiMetadata.getMeta("maintUnit")));
        map.put("maintCertno",StringUtils.toString(maintCertno, eiMetadata.getMeta("maintCertno")));
        map.put("checkUnit",StringUtils.toString(checkUnit, eiMetadata.getMeta("checkUnit")));
        map.put("checkCertno",StringUtils.toString(checkCertno, eiMetadata.getMeta("checkCertno")));
        map.put("thisCheckDate",StringUtils.toString(thisCheckDate, eiMetadata.getMeta("thisCheckDate")));
        map.put("thisFinishDate",StringUtils.toString(thisFinishDate, eiMetadata.getMeta("thisFinishDate")));
        map.put("nextCheckDate",StringUtils.toString(nextCheckDate, eiMetadata.getMeta("nextCheckDate")));
        map.put("annualinspcycle",StringUtils.toString(annualinspcycle, eiMetadata.getMeta("annualinspcycle")));
        map.put("thisExpiredDate",StringUtils.toString(thisExpiredDate, eiMetadata.getMeta("thisExpiredDate")));
        map.put("thisChexpiredDate",StringUtils.toString(thisChexpiredDate, eiMetadata.getMeta("thisChexpiredDate")));
        map.put("nextExpiredDate",StringUtils.toString(nextExpiredDate, eiMetadata.getMeta("nextExpiredDate")));
        map.put("regularinspcycle",StringUtils.toString(regularinspcycle, eiMetadata.getMeta("regularinspcycle")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        return map;
    }
}