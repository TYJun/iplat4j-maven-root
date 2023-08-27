/**
* Generate time : 2021-06-15 9:16:47
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SSBMQrcodeDept
* sc_qrcode_dept 
*/
public class SSBMBqgl01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String recCreateTime ;		/* 创建时间*/
    private String recCreator = " ";		/* 创建人*/
    private String recReviseTime ;		/* 修改时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String deptName = " ";		/* 科室名称*/
    private String deptCode = " ";		/* 科室编码*/
    private String typeCode = " ";		/* 业务类型(zg/职工;bh/病患)*/
    private String status = " ";		/* 状态*/
    private String datagroupCode = " ";		/* 账套*/
    private String datagroupName = " ";		/* 账套*/
    private String datagroupTreecode = " ";		/* 根账套*/
    private String quoteAddress = "0";		/* 是否被address引用; 0/1未引用/已引用*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptCode");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeCode");
        eiColumn.setDescName("业务类型(zg/职工;bh/病患)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("datagroupName");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupTreecode");
        eiColumn.setDescName("根账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("quoteAddress");
        eiColumn.setDescName("是否被address引用; 0/1未引用/已引用");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMBqgl01() {
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
     * get the deptName - 科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the deptCode - 科室编码
     * @return the deptCode
     */
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * set the deptCode - 科室编码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * get the typeCode - 业务类型(zg/职工;bh/病患)
     * @return the typeCode
     */
    public String getTypeCode() {
        return this.typeCode;
    }

    /**
     * set the typeCode - 业务类型(zg/职工;bh/病患)
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * get the status - 状态
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    
    public String getDatagroupName() {
        return datagroupName;
    }

    public void setDatagroupName(String datagroupName) {
        this.datagroupName = datagroupName;
    }

    /**
     * get the datagroupCode - 账套
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode - 账套
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the datagroupTreecode - 根账套
     * @return the datagroupTreecode
     */
    public String getDatagroupTreecode() {
        return this.datagroupTreecode;
    }

    /**
     * set the datagroupTreecode - 根账套
     */
    public void setDatagroupTreecode(String datagroupTreecode) {
        this.datagroupTreecode = datagroupTreecode;
    }

    /**
     * get the quoteAddress - 是否被address引用; 0/1未引用/已引用
     * @return the quoteAddress
     */
    public String getQuoteAddress() {
        return this.quoteAddress;
    }

    /**
     * set the quoteAddress - 是否被address引用; 0/1未引用/已引用
     */
    public void setQuoteAddress(String quoteAddress) {
        this.quoteAddress = quoteAddress;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")),recCreateTime));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")),recReviseTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setDeptCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptCode")), deptCode));
        setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
        setDatagroupName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupName")), datagroupName));
        setDatagroupTreecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupTreecode")), datagroupTreecode));
        setQuoteAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("quoteAddress")), quoteAddress));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("deptCode",StringUtils.toString(deptCode, eiMetadata.getMeta("deptCode")));
        map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        map.put("datagroupName",StringUtils.toString(datagroupName, eiMetadata.getMeta("datagroupName")));
        map.put("datagroupTreecode",StringUtils.toString(datagroupTreecode, eiMetadata.getMeta("datagroupTreecode")));
        map.put("quoteAddress",StringUtils.toString(quoteAddress, eiMetadata.getMeta("quoteAddress")));
        return map;
    }
}