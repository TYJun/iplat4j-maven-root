/**
* Generate time : 2021-04-06 15:54:34
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 合同主表付款实体类
 * 
 * @Title: ContractBillPayment.java
 * @ClassName: ContractBillPayment
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:41:50
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractBillPayment extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevsior = " ";		
    private String recReviseTime = " ";		
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		
    private String contNo = " ";		/* 合同号*/
    private String subNo = " ";		
    private Integer lastTime = new Integer(0);		/* 距离上次付款时间*/
    private String payRate = " ";		/* 付款比例*/
    private Double payAmount = new Double(0.00);		/* 付款金额*/
    private String remark = " ";		/* 备注*/
    private String payFlag = "0";		/* 付款标记*/
    private String msgStatus = "0";
    private String planPaymentTime = " ";
    private String no = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevsior");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("subNo");
        eiColumn.setDescName("子项号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastTime");
        eiColumn.setDescName("距离上次付款时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payRate");
        eiColumn.setDescName("付款比例");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payAmount");
        eiColumn.setDescName("付款金额");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payFlag");
        eiColumn.setDescName("付款标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("msgStatus");
        eiColumn.setDescName("消息状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planPaymentTime");
        eiColumn.setDescName("预计付款时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("no");
        eiColumn.setDescName("编号");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public ContractBillPayment() {
        initMetaData();
    }

    /**
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevsior 
     * @return the recRevsior
     */
    public String getRecRevsior() {
        return this.recRevsior;
    }

    /**
     * set the recRevsior 
     */
    public void setRecRevsior(String recRevsior) {
        this.recRevsior = recRevsior;
    }

    /**
     * get the recReviseTime 
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime 
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
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
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
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
     * get the subNo 
     * @return the subNo
     */
    public String getSubNo() {
        return this.subNo;
    }

    /**
     * set the subNo 
     */
    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    /**
     * get the lastTime - 距离上次付款时间
     * @return the lastTime
     */
    public Integer getLastTime() {
        return this.lastTime;
    }

    /**
     * set the lastTime - 距离上次付款时间
     */
    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * get the payRate - 付款比例
     * @return the payRate
     */
    public String getPayRate() {
        return this.payRate;
    }

    /**
     * set the payRate - 付款比例
     */
    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    /**
     * get the payAmount - 付款金额
     * @return the payAmount
     */
    public Double getPayAmount() {
        return this.payAmount;
    }

    /**
     * set the payAmount - 付款金额
     */
    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
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
     * get the payFlag - 付款标记
     * @return the payFlag
     */
    public String getPayFlag() {
        return this.payFlag;
    }

    /**
     * set the payFlag - 付款标记
     */
    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    /**
     * get the msgStatus 
     * @return the msgStatus
     */
    public String getMsgStatus() {
        return this.msgStatus;
    }

    /**
     * set the msgStatus 
     */
    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getPlanPaymentTime() {
        return planPaymentTime;
    }

    public void setPlanPaymentTime(String planPaymentTime) {
        this.planPaymentTime = planPaymentTime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevsior(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevsior")), recRevsior));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setSubNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("subNo")), subNo));
        setLastTime(NumberUtils.toInteger(StringUtils.toString(map.get("lastTime")), lastTime));
        setPayRate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payRate")), payRate));
        setPayAmount(NumberUtils.toDouble(StringUtils.toString(map.get("payAmount")), payAmount));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setPayFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payFlag")), payFlag));
        setMsgStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("msgStatus")), msgStatus));
        setPlanPaymentTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planPaymentTime")), planPaymentTime));
        setNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("no")), no));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevsior",StringUtils.toString(recRevsior, eiMetadata.getMeta("recRevsior")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("subNo",StringUtils.toString(subNo, eiMetadata.getMeta("subNo")));
        map.put("lastTime",StringUtils.toString(lastTime, eiMetadata.getMeta("lastTime")));
        map.put("payRate",StringUtils.toString(payRate, eiMetadata.getMeta("payRate")));
        map.put("payAmount",StringUtils.toString(payAmount, eiMetadata.getMeta("payAmount")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("payFlag",StringUtils.toString(payFlag, eiMetadata.getMeta("payFlag")));
        map.put("msgStatus",StringUtils.toString(msgStatus, eiMetadata.getMeta("msgStatus")));
        map.put("planPaymentTime",StringUtils.toString(planPaymentTime, eiMetadata.getMeta("planPaymentTime")));
        map.put("no",StringUtils.toString(no, eiMetadata.getMeta("no")));
        return map;
    }
}