/**
* Generate time : 2022-03-18 12:44:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.hr.pz.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* HrJobChangeMan
* 
*/
public class HrJobChangeMan extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键ID*/
    private String billId = " ";		/* 人员调派申请单ID*/
    private String manId = " ";		/* 人员信息表ID*/
    private String phone = " ";		/* 人员信息表phone*/
    private String statusCode = " ";		/* 状态*/
    private String name = " ";		/* 人员名称*/
    private String workNo = " ";		/* 人员工号*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billId");
        eiColumn.setDescName("人员调派申请单ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manId");
        eiColumn.setDescName("人员信息表ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setDescName("人员信息表phone");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("人员名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("人员工号");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public HrJobChangeMan() {
        initMetaData();
    }

    /**
     * get the id - 主键ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the billId - 人员调派申请单ID
     * @return the billId
     */
    public String getBillId() {
        return this.billId;
    }

    /**
     * set the billId - 人员调派申请单ID
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * get the manId - 人员信息表ID
     * @return the manId
     */
    public String getManId() {
        return this.manId;
    }

    /**
     * set the manId - 人员信息表ID
     */
    public void setManId(String manId) {
        this.manId = manId;
    }

    /**
     * get the phone - 人员信息表phone
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone - 人员信息表phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the statusCode - 状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the name - 人员名称
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 人员名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the workNo - 人员工号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 人员工号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setBillId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billId")), billId));
        setManId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manId")), manId));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("billId",StringUtils.toString(billId, eiMetadata.getMeta("billId")));
        map.put("manId",StringUtils.toString(manId, eiMetadata.getMeta("manId")));
        map.put("phone",StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        return map;
    }
}