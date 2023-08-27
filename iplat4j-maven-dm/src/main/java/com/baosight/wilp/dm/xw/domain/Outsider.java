/**
* Generate time : 2022-03-28 16:05:34
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.dm.xw.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DormsMan
* 
*/
public class Outsider extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String manNo = " ";		/* 工号*/
    private String manName = " ";		/* 姓名*/
    private String sex = " ";		/* 性别*/
    private Integer age = new Integer(0);		/* 员工年龄*/
    private String phone = " ";		/* 电话*/
    private String note = " ";		/* 居住地*/
    private String recCreator = " ";		/* 创建人工号*/
    private String recCreateName = " ";		/* 创建人姓名*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人工号*/
    private String recReviseName = " ";		/* 修改人姓名*/
    private String recReviseTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manName");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sex");
        eiColumn.setDescName("性别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("age");
        eiColumn.setDescName("员工年龄");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setDescName("电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("note");
        eiColumn.setDescName("当前居住地");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateName");
        eiColumn.setDescName("创建人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseName");
        eiColumn.setDescName("修改人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public Outsider() {
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
     * get the manNo - 工号
     * @return the manNo
     */
    public String getManNo() {
        return this.manNo;
    }

    /**
     * set the manNo - 工号
     */
    public void setManNo(String manNo) {
        this.manNo = manNo;
    }

    /**
     * get the manName - 姓名
     * @return the manName
     */
    public String getManName() {
        return this.manName;
    }

    /**
     * set the manName - 姓名
     */
    public void setManName(String manName) {
        this.manName = manName;
    }

    /**
     * get the sex - 性别
     * @return the sex
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * set the sex - 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * get the age - 员工年龄
     * @return the age
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * set the age - 员工年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * get the phone - 电话
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone - 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the phone - 居住地
     * @return the phone
     */
    public String getNote() {
        return this.note;
    }

    /**
     * set the phone - 居住地
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * get the recCreator - 创建人工号
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人工号
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateName - 创建人姓名
     * @return the recCreateName
     */
    public String getRecCreateName() {
        return this.recCreateName;
    }

    /**
     * set the recCreateName - 创建人姓名
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
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
     * get the recRevisor - 修改人工号
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人工号
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseName - 修改人姓名
     * @return the recReviseName
     */
    public String getRecReviseName() {
        return this.recReviseName;
    }

    /**
     * set the recReviseName - 修改人姓名
     */
    public void setRecReviseName(String recReviseName) {
        this.recReviseName = recReviseName;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manNo")), manNo));
        setManName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manName")), manName));
        setSex(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sex")), sex));
        setAge(NumberUtils.toInteger(StringUtils.toString(map.get("age")), age));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setNote(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("note")), note));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateName")), recCreateName));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseName")), recReviseName));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("manNo",StringUtils.toString(manNo, eiMetadata.getMeta("manNo")));
        map.put("manName",StringUtils.toString(manName, eiMetadata.getMeta("manName")));
        map.put("sex",StringUtils.toString(sex, eiMetadata.getMeta("sex")));
        map.put("age",StringUtils.toString(age, eiMetadata.getMeta("age")));
        map.put("phone",StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("note",StringUtils.toString(phone, eiMetadata.getMeta("note")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateName",StringUtils.toString(recCreateName, eiMetadata.getMeta("recCreateName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseName",StringUtils.toString(recReviseName, eiMetadata.getMeta("recReviseName")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}