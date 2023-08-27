/**
* Generate time : 2021-06-03 14:13:18
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTcardConsumeRecordInfo 卡片消费记录实体类
* 
*/
public class PSPCTcardConsumeRecordInfo extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String consumeBillId = " ";		/* 消费品编码*/
    private String billNo = " ";		
    private String canteenNum = " ";		
    private String canteenName = " ";		
    private String cardCode = " ";		/* 卡片编码*/
    private String patientCode = " ";		/* 病人住院号/员工号*/
    private String consumeDeviceCode = " ";		/* 消费设备编码*/
    private BigDecimal consumeMoney = new BigDecimal("0");		/* 消费金额*/
    private BigDecimal backMoney = new BigDecimal("0");		/* 退款金额*/
    private String consumeType = " ";		/* 0/1,消费/退款*/
    private String consumeTime = " ";		/* 消费时间*/
    private String backTime = " ";		/* 退卡时间*/
    private String billType = " ";		/* bh/病患 yw/医务*/
    private String statusCode = " ";		/* 00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败/90-付款处理中/91-退款处理中/99-返回明确的错误信息时，终结消息发送*/
    private String message = " ";		
    private String branchno = " ";		/* 00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败-/90付款处理中/91退款处理中*/
    private String operid = " ";		
    private String flag = " ";		/* 01代表未更新订单表，99代表更新订单表，98代表工作流提交失败手动修改*/
    private String activitiType = " ";		/* 工作流类型标记*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeBillId");
        eiColumn.setDescName("消费品编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenNum");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canteenName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cardCode");
        eiColumn.setDescName("卡片编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("patientCode");
        eiColumn.setDescName("病人住院号/员工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeDeviceCode");
        eiColumn.setDescName("消费设备编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeMoney");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("消费金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("backMoney");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(8);
        eiColumn.setDescName("退款金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeType");
        eiColumn.setDescName("0/1,消费/退款");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("consumeTime");
        eiColumn.setDescName("消费时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("backTime");
        eiColumn.setDescName("退卡时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billType");
        eiColumn.setDescName("bh/病患 yw/医务");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败/90-付款处理中/91-退款处理中/99-返回明确的错误信息时，终结消息发送");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("message");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("branchno");
        eiColumn.setDescName("00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败-/90付款处理中/91退款处理中");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("flag");
        eiColumn.setDescName("01代表未更新订单表，99代表更新订单表，98代表工作流提交失败手动修改");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("activitiType");
        eiColumn.setDescName("工作流类型标记");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTcardConsumeRecordInfo() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the consumeBillId - 消费品编码
     * @return the consumeBillId
     */
    public String getConsumeBillId() {
        return this.consumeBillId;
    }

    /**
     * set the consumeBillId - 消费品编码
     */
    public void setConsumeBillId(String consumeBillId) {
        this.consumeBillId = consumeBillId;
    }

    /**
     * get the billNo 
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo 
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the canteenNum 
     * @return the canteenNum
     */
    public String getCanteenNum() {
        return this.canteenNum;
    }

    /**
     * set the canteenNum 
     */
    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    /**
     * get the canteenName 
     * @return the canteenName
     */
    public String getCanteenName() {
        return this.canteenName;
    }

    /**
     * set the canteenName 
     */
    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    /**
     * get the cardCode - 卡片编码
     * @return the cardCode
     */
    public String getCardCode() {
        return this.cardCode;
    }

    /**
     * set the cardCode - 卡片编码
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    /**
     * get the patientCode - 病人住院号/员工号
     * @return the patientCode
     */
    public String getPatientCode() {
        return this.patientCode;
    }

    /**
     * set the patientCode - 病人住院号/员工号
     */
    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    /**
     * get the consumeDeviceCode - 消费设备编码
     * @return the consumeDeviceCode
     */
    public String getConsumeDeviceCode() {
        return this.consumeDeviceCode;
    }

    /**
     * set the consumeDeviceCode - 消费设备编码
     */
    public void setConsumeDeviceCode(String consumeDeviceCode) {
        this.consumeDeviceCode = consumeDeviceCode;
    }

    /**
     * get the consumeMoney - 消费金额
     * @return the consumeMoney
     */
    public BigDecimal getConsumeMoney() {
        return this.consumeMoney;
    }

    /**
     * set the consumeMoney - 消费金额
     */
    public void setConsumeMoney(BigDecimal consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    /**
     * get the backMoney - 退款金额
     * @return the backMoney
     */
    public BigDecimal getBackMoney() {
        return this.backMoney;
    }

    /**
     * set the backMoney - 退款金额
     */
    public void setBackMoney(BigDecimal backMoney) {
        this.backMoney = backMoney;
    }

    /**
     * get the consumeType - 0/1,消费/退款
     * @return the consumeType
     */
    public String getConsumeType() {
        return this.consumeType;
    }

    /**
     * set the consumeType - 0/1,消费/退款
     */
    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    /**
     * get the consumeTime - 消费时间
     * @return the consumeTime
     */
    public String getConsumeTime() {
        return this.consumeTime;
    }

    /**
     * set the consumeTime - 消费时间
     */
    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

    /**
     * get the backTime - 退卡时间
     * @return the backTime
     */
    public String getBackTime() {
        return this.backTime;
    }

    /**
     * set the backTime - 退卡时间
     */
    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    /**
     * get the billType - bh/病患 yw/医务
     * @return the billType
     */
    public String getBillType() {
        return this.billType;
    }

    /**
     * set the billType - bh/病患 yw/医务
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * get the statusCode - 00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败/90-付款处理中/91-退款处理中/99-返回明确的错误信息时，终结消息发送
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败/90-付款处理中/91-退款处理中/99-返回明确的错误信息时，终结消息发送
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the message 
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * set the message 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * get the branchno - 00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败-/90付款处理中/91退款处理中
     * @return the branchno
     */
    public String getBranchno() {
        return this.branchno;
    }

    /**
     * set the branchno - 00-待付款/ 01-付款成功/02-付款失败/03-待退款/04-退款成功/05-退款失败-/90付款处理中/91退款处理中
     */
    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }

    /**
     * get the operid 
     * @return the operid
     */
    public String getOperid() {
        return this.operid;
    }

    /**
     * set the operid 
     */
    public void setOperid(String operid) {
        this.operid = operid;
    }

    /**
     * get the flag - 01代表未更新订单表，99代表更新订单表，98代表工作流提交失败手动修改
     * @return the flag
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * set the flag - 01代表未更新订单表，99代表更新订单表，98代表工作流提交失败手动修改
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * get the activitiType - 工作流类型标记
     * @return the activitiType
     */
    public String getActivitiType() {
        return this.activitiType;
    }

    /**
     * set the activitiType - 工作流类型标记
     */
    public void setActivitiType(String activitiType) {
        this.activitiType = activitiType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setConsumeBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeBillId")), consumeBillId));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setCanteenNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenNum")), canteenNum));
        setCanteenName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canteenName")), canteenName));
        setCardCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cardCode")), cardCode));
        setPatientCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("patientCode")), patientCode));
        setConsumeDeviceCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeDeviceCode")), consumeDeviceCode));
        setConsumeMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("consumeMoney")), consumeMoney));
        setBackMoney(NumberUtils.toBigDecimal(StringUtils.toString(map.get("backMoney")), backMoney));
        setConsumeType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeType")), consumeType));
        setConsumeTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("consumeTime")), consumeTime));
        setBackTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("backTime")), backTime));
        setBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billType")), billType));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setMessage(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("message")), message));
        setBranchno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("branchno")), branchno));
        setOperid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operid")), operid));
        setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
        setActivitiType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("activitiType")), activitiType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("consumeBillId",StringUtils.toString(consumeBillId, eiMetadata.getMeta("consumeBillId")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("canteenNum",StringUtils.toString(canteenNum, eiMetadata.getMeta("canteenNum")));
        map.put("canteenName",StringUtils.toString(canteenName, eiMetadata.getMeta("canteenName")));
        map.put("cardCode",StringUtils.toString(cardCode, eiMetadata.getMeta("cardCode")));
        map.put("patientCode",StringUtils.toString(patientCode, eiMetadata.getMeta("patientCode")));
        map.put("consumeDeviceCode",StringUtils.toString(consumeDeviceCode, eiMetadata.getMeta("consumeDeviceCode")));
        map.put("consumeMoney",StringUtils.toString(consumeMoney, eiMetadata.getMeta("consumeMoney")));
        map.put("backMoney",StringUtils.toString(backMoney, eiMetadata.getMeta("backMoney")));
        map.put("consumeType",StringUtils.toString(consumeType, eiMetadata.getMeta("consumeType")));
        map.put("consumeTime",StringUtils.toString(consumeTime, eiMetadata.getMeta("consumeTime")));
        map.put("backTime",StringUtils.toString(backTime, eiMetadata.getMeta("backTime")));
        map.put("billType",StringUtils.toString(billType, eiMetadata.getMeta("billType")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("message",StringUtils.toString(message, eiMetadata.getMeta("message")));
        map.put("branchno",StringUtils.toString(branchno, eiMetadata.getMeta("branchno")));
        map.put("operid",StringUtils.toString(operid, eiMetadata.getMeta("operid")));
        map.put("flag",StringUtils.toString(flag, eiMetadata.getMeta("flag")));
        map.put("activitiType",StringUtils.toString(activitiType, eiMetadata.getMeta("activitiType")));
        return map;
    }
}