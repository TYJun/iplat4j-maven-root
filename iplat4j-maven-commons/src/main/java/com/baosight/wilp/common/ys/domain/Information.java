package com.baosight.wilp.common.ys.domain;

import java.io.Serializable;

import com.baosight.iplat4j.core.data.DaoEPBase;

/**
 * 
 * app登录基础信息实体类
 * 
 * @Title: Information.java
 * @ClassName: Information
 * @Package：com.baosight.wilp.nb.ap.domain
 * @author: zhangjiaqian
 * @date: 2021年2月26日 上午11:04:27
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class Information extends DaoEPBase implements Serializable{

    /**
     * @Fields serialVersionUID : 序列化
     */
    private static final long serialVersionUID = 1504784562400585470L;
    
    /**
     * 登录人角色编码
     */
    private String role;
    
    
    /**
     * 登录人工号
     */
    private String workNo;
    

    /**
     * 登陆人角色名
     */
    private String roleName;
    
    
    /**
     * 登录人电话
     */
    private String telPhone;
    
    
    /**
     * 登陆人所属科室
     */
    private String deptName;
    
    
    /**
     * 登录人所属科室编码
     */
    private String deptCode;
    
    
    /**
     * 登陆人姓名
     */
    private String name;

    
    /**
     * 登录人所属账套
     */
    private String dataGroupCode;

    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getWorkNo() {
        return workNo;
    }


    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getTelPhone() {
        return telPhone;
    }


    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }


    public String getDeptName() {
        return deptName;
    }


    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public String getDeptCode() {
        return deptCode;
    }


    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDataGroupCode() {
        return dataGroupCode;
    }


    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

}
