/**
* Generate time : 2022-03-21 17:22:39
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cp.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CpBillDeal
* 
*/
public class CpBillDeal extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String creator = " ";		/* 创建人工号*/
    private String createName = " ";		/* 创建人名字*/
    private String createTime=" ";		/* 创建日期*/
    private String dealType = " ";		/* 处理类型，立即解决，立即上报，转接部门*/
    private String dealReason = " ";		/* 处理原因*/
    private String dealDesc = " ";		/* 处理描述*/
    private String dealDept = " ";		/* 处理部门*/
    private String dealWorkNo = " ";		/* 处理人工号*/
    private String dealWorkName = " ";		/* 处理名字*/
    private String dealDate = " ";		/* 处理日期*/
    private String dealFinishDate = " ";		/* 处理完成日期*/
    private String billNo = " ";		/* 处理的投诉单号*/
    private String statusCode = " ";		/* 状态01有效02无效*/
    private String dealStatusCode = " ";		/* 处理状态*/

    private String idendify = " ";

    private String estimateDealDate = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createName");
        eiColumn.setDescName("创建人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealType");
        eiColumn.setDescName("处理类型，立即解决，立即上报，转接部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealReason");
        eiColumn.setDescName("处理原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealDesc");
        eiColumn.setDescName("处理描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealDept");
        eiColumn.setDescName("处理部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealWorkNo");
        eiColumn.setDescName("处理人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealWorkName");
        eiColumn.setDescName("处理名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealDate");
        eiColumn.setDescName("处理日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealFinishDate");
        eiColumn.setDescName("处理完成日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("处理的投诉单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态01有效02无效");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dealStatusCode");
        eiColumn.setDescName("处理状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("idendify");
        eiColumn.setDescName("识别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("estimateDealDate");
        eiColumn.setDescName("预计处理时间");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public CpBillDeal() {
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
     * get the creator - 创建人工号
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 创建人工号
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createName - 创建人名字
     * @return the createName
     */
    public String getCreateName() {
        return this.createName;
    }

    /**
     * set the createName - 创建人名字
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * get the createTime - 创建日期
     * @return the createTime
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建日期
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get the dealType - 处理类型，立即解决，立即上报，转接部门
     * @return the dealType
     */
    public String getDealType() {
        return this.dealType;
    }

    /**
     * set the dealType - 处理类型，立即解决，立即上报，转接部门
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    /**
     * get the dealReason - 处理原因
     * @return the dealReason
     */
    public String getDealReason() {
        return this.dealReason;
    }

    /**
     * set the dealReason - 处理原因
     */
    public void setDealReason(String dealReason) {
        this.dealReason = dealReason;
    }

    /**
     * get the dealDesc - 处理描述
     * @return the dealDesc
     */
    public String getDealDesc() {
        return this.dealDesc;
    }

    /**
     * set the dealDesc - 处理描述
     */
    public void setDealDesc(String dealDesc) {
        this.dealDesc = dealDesc;
    }

    /**
     * get the dealDept - 处理部门
     * @return the dealDept
     */
    public String getDealDept() {
        return this.dealDept;
    }

    /**
     * set the dealDept - 处理部门
     */
    public void setDealDept(String dealDept) {
        this.dealDept = dealDept;
    }

    /**
     * get the dealWorkNo - 处理人工号
     * @return the dealWorkNo
     */
    public String getDealWorkNo() {
        return this.dealWorkNo;
    }

    /**
     * set the dealWorkNo - 处理人工号
     */
    public void setDealWorkNo(String dealWorkNo) {
        this.dealWorkNo = dealWorkNo;
    }

    /**
     * get the dealWorkName - 处理名字
     * @return the dealWorkName
     */
    public String getDealWorkName() {
        return this.dealWorkName;
    }

    /**
     * set the dealWorkName - 处理名字
     */
    public void setDealWorkName(String dealWorkName) {
        this.dealWorkName = dealWorkName;
    }

    /**
     * get the dealDate - 处理日期
     * @return the dealDate
     */
    public String getDealDate() {
        return this.dealDate;
    }

    /**
     * set the dealDate - 处理日期
     */
    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    /**
     * get the dealFinishDate - 处理完成日期
     * @return the dealFinishDate
     */
    public String getDealFinishDate() {
        return this.dealFinishDate;
    }

    /**
     * set the dealFinishDate - 处理完成日期
     */
    public void setDealFinishDate(String dealFinishDate) {
        this.dealFinishDate = dealFinishDate;
    }

    /**
     * get the billNo - 处理的投诉单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 处理的投诉单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the statusCode - 状态01有效02无效
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态01有效02无效
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the dealStatusCode - 处理状态
     * @return the dealStatusCode
     */
    public String getDealStatusCode() {
        return this.dealStatusCode;
    }

    /**
     * set the dealStatusCode - 处理状态
     */
    public void setDealStatusCode(String dealStatusCode) {
        this.dealStatusCode = dealStatusCode;
    }



    public String getIdendify() {
        return idendify;
    }

    public void setIdendify(String idendify) {
        this.idendify = idendify;
    }

    public String getEstimateDealDate() {
        return estimateDealDate;
    }

    public void setEstimateDealDate(String estimateDealDate) {
        this.estimateDealDate = estimateDealDate;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createName")), createName));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")),createTime));
        setDealType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealType")), dealType));
        setDealReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealReason")), dealReason));
        setDealDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealDesc")), dealDesc));
        setDealDept(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealDept")), dealDept));
        setDealWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealWorkNo")), dealWorkNo));
        setDealWorkName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealWorkName")), dealWorkName));
        setDealDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealDate")), dealDate));
        setDealFinishDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealFinishDate")), dealFinishDate));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setDealStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dealStatusCode")), dealStatusCode));
        setIdendify(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("idendify")),idendify));
        setEstimateDealDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("estimateDealDate")),estimateDealDate));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createName",StringUtils.toString(createName, eiMetadata.getMeta("createName")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("dealType",StringUtils.toString(dealType, eiMetadata.getMeta("dealType")));
        map.put("dealReason",StringUtils.toString(dealReason, eiMetadata.getMeta("dealReason")));
        map.put("dealDesc",StringUtils.toString(dealDesc, eiMetadata.getMeta("dealDesc")));
        map.put("dealDept",StringUtils.toString(dealDept, eiMetadata.getMeta("dealDept")));
        map.put("dealWorkNo",StringUtils.toString(dealWorkNo, eiMetadata.getMeta("dealWorkNo")));
        map.put("dealWorkName",StringUtils.toString(dealWorkName, eiMetadata.getMeta("dealWorkName")));
        map.put("dealDate",StringUtils.toString(dealDate, eiMetadata.getMeta("dealDate")));
        map.put("dealFinishDate",StringUtils.toString(dealFinishDate, eiMetadata.getMeta("dealFinishDate")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("dealStatusCode",StringUtils.toString(dealStatusCode, eiMetadata.getMeta("dealStatusCode")));
        map.put("idendify",StringUtils.toString(idendify, eiMetadata.getMeta("idendify")));
        map.put("estimateDealDate",StringUtils.toString(estimateDealDate, eiMetadata.getMeta("estimateDealDate")));
        return map;
    }
}