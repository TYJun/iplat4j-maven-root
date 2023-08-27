/**
* Generate time : 2021-06-16 14:29:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.ss.bm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
* SSBMSCGL01
* sc_sender_dept
*/
public class SSBMSCGL01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String workName = " ";		
    private String workNo = " ";		/* 工号*/
    private String deptNum = " ";		/* 部门编码*/
    private String deptName = " ";		/* 部门名称*/
    private Timestamp createTime ;		
    private String datagroupCode = " ";		
    private String datagroupTreecode = " ";		/* 根账套*/
    private String deptType = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("部门编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("部门名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupTreecode");
        eiColumn.setDescName("根账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SSBMSCGL01() {
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
     * get the workName 
     * @return the workName
     */
    public String getWorkName() {
        return this.workName;
    }

    /**
     * set the workName 
     */
    public void setWorkName(String workName) {
        this.workName = workName;
    }

    /**
     * get the workNo - 工号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 工号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the deptNum - 部门编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 部门编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 部门名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the createTime 
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime 
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the datagroupCode 
     * @return the datagroupCode
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the datagroupCode 
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
     * get the deptType 
     * @return the deptType
     */
    public String getDeptType() {
        return this.deptType;
    }

    /**
     * set the deptType 
     */
    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setWorkName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workName")), workName));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
        setDatagroupTreecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupTreecode")), datagroupTreecode));
        setDeptType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptType")), deptType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("workName",StringUtils.toString(workName, eiMetadata.getMeta("workName")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        map.put("datagroupTreecode",StringUtils.toString(datagroupTreecode, eiMetadata.getMeta("datagroupTreecode")));
        map.put("deptType",StringUtils.toString(deptType, eiMetadata.getMeta("deptType")));
        return map;
    }
}