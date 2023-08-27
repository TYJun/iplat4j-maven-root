package com.baosight.wilp.ps.pc.domain;


import java.io.Serializable;

/**
 * (苏大xml配置对应的实体类)
 *
 * @Title:BillingRefund
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class BillingRefund implements Serializable {
    private static final long serialVersionUID = 1L;

    //RetCode
    private String RetCode;
    //RetType
    private String RetType = "1";
    //RetCont
    private String RetCont;
    //RecordFlow
    private String RecordFlow;
    //FeeSn
    private String FeeSn;
    //HospitalCode
    private String HospitalCode;
    //InoutFlag
    private String InoutFlag;
    //IPID
    private String IPID;
    //OPID
    private String OPID;
    //EPID
    private String EPID;
    //PID
    private String PID;
    //RegisterSn
    private String RegisterSn;
    //PatientName
    private String PatientName;
    //MedicalInsuranceType
    private String MedicalInsuranceType;
    //SocialCardNo
    private String SocialCardNo;
    //CertNo
    private String CertNo;
    //OrderSn
    private String OrderSn;
    //BillingRefFlag
    private String BillingRefFlag;
    //ChargeType
    private String ChargeType;
    //AddChargeCode
    private String AddChargeCode;
    //AddChargeAmout
    private String AddChargeAmout;
    //AdmissionTime
    private String AdmissionTime;
    //DischargeTime时间
    private String DischargeTime;
    //ChargeDate日期
    private String ChargeDate;
    //FeeTypeCode金额类型编码
    private String FeeTypeCode;
    //FeeTypeName金额类型名称
    private String FeeTypeName;
    //金额
    private String Fee;
    //FeeUnit
    private String FeeUnit;
    //AddFee
    private String AddFee;
    //AddFeeUnit
    private String AddFeeUnit;
    //TotalFee
    private String TotalFee;
    //TotalFeeUnit
    private String TotalFeeUnit;
    //OperatorID
    private String OperatorID;
    //Operator
    private String Operator;

    public String getRetCode() {
        return RetCode;
    }

    public void setRetCode(String retCode) {
        RetCode = retCode;
    }

    public String getRetType() {
        return RetType;
    }

    public void setRetType(String retType) {
        RetType = retType;
    }

    public String getRetCont() {
        return RetCont;
    }

    public void setRetCont(String retCont) {
        RetCont = retCont;
    }

    public String getRecordFlow() {
        return RecordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        RecordFlow = recordFlow;
    }

    public String getFeeSn() {
        return FeeSn;
    }

    public void setFeeSn(String feeSn) {
        FeeSn = feeSn;
    }

    public String getHospitalCode() {
        return HospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        HospitalCode = hospitalCode;
    }

    public String getInoutFlag() {
        return InoutFlag;
    }

    public void setInoutFlag(String inoutFlag) {
        InoutFlag = inoutFlag;
    }

    public String getIPID() {
        return IPID;
    }

    public void setIPID(String IPID) {
        this.IPID = IPID;
    }

    public String getOPID() {
        return OPID;
    }

    public void setOPID(String OPID) {
        this.OPID = OPID;
    }

    public String getEPID() {
        return EPID;
    }

    public void setEPID(String EPID) {
        this.EPID = EPID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getRegisterSn() {
        return RegisterSn;
    }

    public void setRegisterSn(String registerSn) {
        RegisterSn = registerSn;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getMedicalInsuranceType() {
        return MedicalInsuranceType;
    }

    public void setMedicalInsuranceType(String medicalInsuranceType) {
        MedicalInsuranceType = medicalInsuranceType;
    }

    public String getSocialCardNo() {
        return SocialCardNo;
    }

    public void setSocialCardNo(String socialCardNo) {
        SocialCardNo = socialCardNo;
    }

    public String getCertNo() {
        return CertNo;
    }

    public void setCertNo(String certNo) {
        CertNo = certNo;
    }

    public String getOrderSn() {
        return OrderSn;
    }

    public void setOrderSn(String orderSn) {
        OrderSn = orderSn;
    }

    public String getBillingRefFlag() {
        return BillingRefFlag;
    }

    public void setBillingRefFlag(String billingRefFlag) {
        BillingRefFlag = billingRefFlag;
    }

    public String getChargeType() {
        return ChargeType;
    }

    public void setChargeType(String chargeType) {
        ChargeType = chargeType;
    }

    public String getAddChargeCode() {
        return AddChargeCode;
    }

    public void setAddChargeCode(String addChargeCode) {
        AddChargeCode = addChargeCode;
    }

    public String getAddChargeAmout() {
        return AddChargeAmout;
    }

    public void setAddChargeAmout(String addChargeAmout) {
        AddChargeAmout = addChargeAmout;
    }

    public String getAdmissionTime() {
        return AdmissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        AdmissionTime = admissionTime;
    }

    public String getDischargeTime() {
        return DischargeTime;
    }

    public void setDischargeTime(String dischargeTime) {
        DischargeTime = dischargeTime;
    }

    public String getChargeDate() {
        return ChargeDate;
    }

    public void setChargeDate(String chargeDate) {
        ChargeDate = chargeDate;
    }

    public String getFeeTypeCode() {
        return FeeTypeCode;
    }

    public void setFeeTypeCode(String feeTypeCode) {
        FeeTypeCode = feeTypeCode;
    }

    public String getFeeTypeName() {
        return FeeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        FeeTypeName = feeTypeName;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
    }

    public String getFeeUnit() {
        return FeeUnit;
    }

    public void setFeeUnit(String feeUnit) {
        FeeUnit = feeUnit;
    }

    public String getAddFee() {
        return AddFee;
    }

    public void setAddFee(String addFee) {
        AddFee = addFee;
    }

    public String getAddFeeUnit() {
        return AddFeeUnit;
    }

    public void setAddFeeUnit(String addFeeUnit) {
        AddFeeUnit = addFeeUnit;
    }

    public String getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(String totalFee) {
        TotalFee = totalFee;
    }

    public String getTotalFeeUnit() {
        return TotalFeeUnit;
    }

    public void setTotalFeeUnit(String totalFeeUnit) {
        TotalFeeUnit = totalFeeUnit;
    }

    public String getOperatorID() {
        return OperatorID;
    }

    public void setOperatorID(String operatorID) {
        OperatorID = operatorID;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    @Override
    public String toString() {
        return "BillingRefund{" +
                "RecordFlow='" + RecordFlow + '\'' +
                ", FeeSn='" + FeeSn + '\'' +
                ", HospitalCode='" + HospitalCode + '\'' +
                ", InoutFlag='" + InoutFlag + '\'' +
                ", IPID='" + IPID + '\'' +
                ", OPID='" + OPID + '\'' +
                ", EPID='" + EPID + '\'' +
                ", PID='" + PID + '\'' +
                ", RegisterSn='" + RegisterSn + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", MedicalInsuranceType='" + MedicalInsuranceType + '\'' +
                ", SocialCardNo='" + SocialCardNo + '\'' +
                ", CertNo='" + CertNo + '\'' +
                ", OrderSn='" + OrderSn + '\'' +
                ", BillingRefFlag='" + BillingRefFlag + '\'' +
                ", ChargeType='" + ChargeType + '\'' +
                ", AddChargeCode='" + AddChargeCode + '\'' +
                ", AddChargeAmout='" + AddChargeAmout + '\'' +
                ", AdmissionTime='" + AdmissionTime + '\'' +
                ", DischargeTime='" + DischargeTime + '\'' +
                ", ChargeDate='" + ChargeDate + '\'' +
                ", FeeTypeCode='" + FeeTypeCode + '\'' +
                ", FeeTypeName='" + FeeTypeName + '\'' +
                ", Fee='" + Fee + '\'' +
                ", FeeUnit='" + FeeUnit + '\'' +
                ", AddFee='" + AddFee + '\'' +
                ", AddFeeUnit='" + AddFeeUnit + '\'' +
                ", TotalFee='" + TotalFee + '\'' +
                ", TotalFeeUnit='" + TotalFeeUnit + '\'' +
                ", OperatorID='" + OperatorID + '\'' +
                ", Operator='" + Operator + '\'' +
                '}';
    }
}
