/**
* Generate time : 2021-02-09 9:14:56
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CarriageUser
* 
*/
public class CarriageUser extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 运送人员主键（UUID）*/
    private String workNo = " ";		/* 人员工号*/
    private String name = " ";		/* 人员姓名*/
    private String statusCode = " ";		/* 人员状态*/
    private String roleCode = " ";		/* 角色编码*/
    private String phone = " ";		/* 联系方式*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("运送人员主键（UUID）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("人员工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("人员姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("人员状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roleCode");
        eiColumn.setDescName("角色编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setDescName("联系方式");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CarriageUser() {
        initMetaData();
    }

    /**
     * get the id - 运送人员主键（UUID）
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 运送人员主键（UUID）
     */
    public void setId(String id) {
        this.id = id;
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
     * get the name - 人员姓名
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 人员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the statusCode - 人员状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 人员状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the roleCode - 角色编码
     * @return the roleCode
     */
    public String getRoleCode() {
        return this.roleCode;
    }

    /**
     * set the roleCode - 角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * get the phone - 联系方式
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone - 联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setRoleCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roleCode")), roleCode));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("roleCode",StringUtils.toString(roleCode, eiMetadata.getMeta("roleCode")));
        map.put("phone",StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        return map;
    }
}