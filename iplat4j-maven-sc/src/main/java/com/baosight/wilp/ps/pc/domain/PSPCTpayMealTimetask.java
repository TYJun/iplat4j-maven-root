/**
* Generate time : 2021-06-01 18:33:57
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ps.pc.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* PSPCTalipayMealTimetask 在线支付流转记录实体类
* 
*/
public class PSPCTpayMealTimetask extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String createTime = " ";		/* 创建时间*/
    private String hospitalCode = " ";		/* 食堂编码*/
    private String billId = " ";		/* 订单ID*/
    private String billNo = " ";		/* 订单单号*/
    private String billType = " ";		/* 订单类型*/
    private String billStatus = " ";		/* 订单状态*/
    private String creator = " ";		/* 创建人*/
    private String activitiTime = " ";		/* 工作流提交时间*/
    private String msg = " ";		/* 提交工作流返回信息*/
    private String flag = " ";		/* 状态标记 /01--待提交工作流/99--提交成功/98--提交失败，手动走流程*/
    private String payType = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hospitalCode");
        eiColumn.setDescName("食堂编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billId");
        eiColumn.setDescName("订单ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("订单单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billType");
        eiColumn.setDescName("订单类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billStatus");
        eiColumn.setDescName("订单状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("activitiTime");
        eiColumn.setDescName("工作流提交时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("msg");
        eiColumn.setDescName("提交工作流返回信息");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("flag");
        eiColumn.setDescName("状态标记 /01--待提交工作流/99--提交成功/98--提交失败，手动走流程");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PSPCTpayMealTimetask() {
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
     * get the createTime - 创建时间
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the hospitalCode - 食堂编码
     * @return the hospitalCode
     */
    public String getHospitalCode() {
        return this.hospitalCode;
    }

    /**
     * set the hospitalCode - 食堂编码
     */
    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    /**
     * get the billId - 订单ID
     * @return the billId
     */
    public String getBillId() {
        return this.billId;
    }

    /**
     * set the billId - 订单ID
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * get the billNo - 订单单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 订单单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the billType - 订单类型
     * @return the billType
     */
    public String getBillType() {
        return this.billType;
    }

    /**
     * set the billType - 订单类型
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * get the billStatus - 订单状态
     * @return the billStatus
     */
    public String getBillStatus() {
        return this.billStatus;
    }

    /**
     * set the billStatus - 订单状态
     */
    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * get the creator - 创建人
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the activitiTime - 工作流提交时间
     * @return the activitiTime
     */
    public String getActivitiTime() {
        return this.activitiTime;
    }

    /**
     * set the activitiTime - 工作流提交时间
     */
    public void setActivitiTime(String activitiTime) {
        this.activitiTime = activitiTime;
    }

    /**
     * get the msg - 提交工作流返回信息
     * @return the msg
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * set the msg - 提交工作流返回信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * get the flag - 状态标记 /01--待提交工作流/99--提交成功/98--提交失败，手动走流程
     * @return the flag
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * set the flag - 状态标记 /01--待提交工作流/99--提交成功/98--提交失败，手动走流程
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * get the payType 
     * @return the payType
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * set the payType 
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setHospitalCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hospitalCode")), hospitalCode));
        setBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billId")), billId));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setBillType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billType")), billType));
        setBillStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billStatus")), billStatus));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setActivitiTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("activitiTime")), activitiTime));
        setMsg(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("msg")), msg));
        setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
        setPayType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("hospitalCode",StringUtils.toString(hospitalCode, eiMetadata.getMeta("hospitalCode")));
        map.put("billId",StringUtils.toString(billId, eiMetadata.getMeta("billId")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("billType",StringUtils.toString(billType, eiMetadata.getMeta("billType")));
        map.put("billStatus",StringUtils.toString(billStatus, eiMetadata.getMeta("billStatus")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("activitiTime",StringUtils.toString(activitiTime, eiMetadata.getMeta("activitiTime")));
        map.put("msg",StringUtils.toString(msg, eiMetadata.getMeta("msg")));
        map.put("flag",StringUtils.toString(flag, eiMetadata.getMeta("flag")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        return map;
    }
}