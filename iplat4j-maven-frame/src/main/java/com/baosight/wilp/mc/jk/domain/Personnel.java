/**
* Generate time : 2022-06-17 1:02:28
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.mc.jk.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* ScAddress
* 
*/
public class Personnel extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";
    private String STAFF_CODE = " ";    //工号
    private String STAFF_NAME = " ";    //姓名
    private String ID_NUMBER = " ";    //身份证
    private String GENDER_CODE = " ";    //性别编码
    private String TELEPHONE = " ";    //电话
    private String DEFAULT_ADMIN_DEP_CODE = " ";    //科室编码(管理区域)
    private String DEFAULT_ADMIN_DEP_NAME = " ";    //科室名称(管理区域)

    private String DEFAULT_DEP_CODE = " ";    //科室编码
    private String DEFAULT_DEP_NAME = " ";    //科室名称


    private String DEFAULT_BIZ_ID = " ";    //bizid
    private String RCRT_NAME = " ";    //人员类型名称
    private String RCRT_CODE;    //人员类型编码           dept_id
    private String dept_id;    //科室id
    private String WEIXIN_WORK_ACCOUNT;    //微信登录号码

    private String STAFF_INFO;
    private String OCCUPATION_NAME;

    private String ACT_FROM;
    private String ACT_TO;
    private String STATUS_CODE;
    private String STATUS_NAME;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("STAFF_CODE");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("STAFF_NAME");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ID_NUMBER");
        eiColumn.setDescName("身份证");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("GENDER_CODE");
        eiColumn.setDescName("性别编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("TELEPHONE");
        eiColumn.setDescName("电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("DEFAULT_ADMIN_DEP_CODE");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("DEFAULT_ADMIN_DEP_NAME");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("DEFAULT_DEP_CODE");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("DEFAULT_DEP_NAME");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("DEFAULT_BIZ_ID");
        eiColumn.setDescName("bizid");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("RCRT_NAME");
        eiColumn.setDescName("人员类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("RCRT_CODE");
        eiColumn.setDescName("人员类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dept_id");
        eiColumn.setDescName("科室id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("WEIXIN_WORK_ACCOUNT");
        eiColumn.setDescName("微信登录号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("STAFF_INFO");
        eiColumn.setDescName("微信登录号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("OCCUPATION_NAME");
        eiColumn.setDescName("微信登录号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ACT_FROM");
        eiColumn.setDescName("微信登录号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ACT_TO");
        eiColumn.setDescName("微信登录号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("STATUS_CODE");
        eiColumn.setDescName("微信登录号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("STATUS_NAME");
        eiColumn.setDescName("微信登录号码");
        eiMetadata.addMeta(eiColumn);



    }

    /**
     * the constructor
     */
    public Personnel() {
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

    public String getSTAFF_CODE() {
        return this.STAFF_CODE;
    }
    public void setSTAFF_CODE(String STAFF_CODE) {
        this.STAFF_CODE = STAFF_CODE;
    }
    public String getSTAFF_NAME() {
        return this.STAFF_NAME;
    }
    public void setSTAFF_NAME(String STAFF_NAME) {
        this.STAFF_NAME = STAFF_NAME;
    }
    public String getID_NUMBER() {
        return this.ID_NUMBER;
    }
    public void setID_NUMBER(String ID_NUMBER) {
        this.ID_NUMBER = ID_NUMBER;
    }
    public String getGENDER_CODE() {
        return this.GENDER_CODE;
    }
    public void setGENDER_CODE(String GENDER_CODE) {
        this.GENDER_CODE = GENDER_CODE;
    }
    public String getTELEPHONE() {
        return this.TELEPHONE;
    }
    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }
    public String getDEFAULT_ADMIN_DEP_CODE() {
        return this.DEFAULT_ADMIN_DEP_CODE;
    }
    public void setDEFAULT_ADMIN_DEP_CODE(String DEFAULT_ADMIN_DEP_CODE) {
        this.DEFAULT_ADMIN_DEP_CODE = DEFAULT_ADMIN_DEP_CODE;
    }
    public String getDEFAULT_ADMIN_DEP_NAME() {
        return this.DEFAULT_ADMIN_DEP_NAME ;
    }
    public void setDEFAULT_ADMIN_DEP_NAME(String DEFAULT_ADMIN_DEP_NAME ) {
        this.DEFAULT_ADMIN_DEP_NAME  = DEFAULT_ADMIN_DEP_NAME ;
    }

    public String getDEFAULT_DEP_CODE() {
        return this.DEFAULT_DEP_CODE;
    }
    public void setDEFAULT_DEP_CODE(String DEFAULT_DEP_CODE) {
        this.DEFAULT_DEP_CODE = DEFAULT_DEP_CODE;
    }
    public String getDEFAULT_DEP_NAME() {
        return this.DEFAULT_DEP_NAME ;
    }
    public void setDEFAULT_DEP_NAME(String DEFAULT_DEP_NAME ) {
        this.DEFAULT_DEP_NAME  = DEFAULT_DEP_NAME ;
    }

    public String getDEFAULT_BIZ_ID() {
        return this.DEFAULT_BIZ_ID;
    }
    public void setDEFAULT_BIZ_ID(String DEFAULT_BIZ_ID) {
        this.id = DEFAULT_BIZ_ID;
    }
    public String getRCRT_NAME() {
        return this.RCRT_NAME;
    }
    public void setRCRT_NAME(String RCRT_NAME) {
        this.RCRT_NAME = RCRT_NAME;
    }
    public String getRCRT_CODE() {
        return this.RCRT_CODE;
    }
    public void setRCRT_CODE(String RCRT_CODE) {
        this.RCRT_CODE = RCRT_CODE;
    }

    public String getDept_id() {
        return this.dept_id;
    }
    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getWEIXIN_WORK_ACCOUNT() {
        return this.WEIXIN_WORK_ACCOUNT;
    }
    public void setWEIXIN_WORK_ACCOUNT(String WEIXIN_WORK_ACCOUNT) {
        this.WEIXIN_WORK_ACCOUNT = WEIXIN_WORK_ACCOUNT;
    }

    public String getSTAFF_INFO() {
        return this.STAFF_INFO;
    }
    public void setSTAFF_INFO(String STAFF_INFO) {
        this.STAFF_INFO = STAFF_INFO;
    }

    public String getOCCUPATION_NAME() {
        return this.OCCUPATION_NAME;
    }
    public void setOCCUPATION_NAME(String OCCUPATION_NAME) {
        this.OCCUPATION_NAME = OCCUPATION_NAME;
    }

    public String getACT_FROM() {
        return this.ACT_FROM;
    }
    public void setACT_FROM(String ACT_FROM) {
        this.ACT_FROM = ACT_FROM;
    }

    public String getACT_TO() {
        return this.ACT_TO;
    }
    public void setACT_TO(String ACT_TO) {
        this.ACT_TO = ACT_TO;
    }

    public String getSTATUS_CODE() {
        return this.STATUS_CODE;
    }
    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getSTATUS_NAME() {
        return this.STATUS_NAME;
    }
    public void setSTATUS_NAME(String STATUS_NAME) {
        this.STATUS_NAME = STATUS_NAME;
    }


    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setSTAFF_CODE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("STAFF_CODE")), STAFF_CODE));
        setSTAFF_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("STAFF_NAME")), STAFF_NAME));
        setID_NUMBER(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ID_NUMBER")), ID_NUMBER));
        setGENDER_CODE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("GENDER_CODE")), GENDER_CODE));
        setTELEPHONE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("TELEPHONE")), TELEPHONE));
        setDEFAULT_ADMIN_DEP_CODE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DEFAULT_ADMIN_DEP_CODE")), DEFAULT_ADMIN_DEP_CODE));
        setDEFAULT_ADMIN_DEP_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DEFAULT_ADMIN_DEP_NAME")), DEFAULT_ADMIN_DEP_NAME));
        setDEFAULT_DEP_CODE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DEFAULT_DEP_CODE")), DEFAULT_DEP_CODE));
        setDEFAULT_DEP_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DEFAULT_DEP_NAME")), DEFAULT_DEP_NAME));

        setDEFAULT_BIZ_ID(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DEFAULT_BIZ_ID")), DEFAULT_BIZ_ID));
        setRCRT_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("RCRT_NAME")), RCRT_NAME));
        setRCRT_CODE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("RCRT_CODE")), RCRT_CODE));
        setDept_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dept_id")), dept_id));
        setWEIXIN_WORK_ACCOUNT(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("WEIXIN_WORK_ACCOUNT")), WEIXIN_WORK_ACCOUNT));
        setSTAFF_INFO(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("STAFF_INFO")), STAFF_INFO));
        setOCCUPATION_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("OCCUPATION_NAME")), OCCUPATION_NAME));
        setACT_FROM(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ACT_FROM")), ACT_FROM));
        setACT_TO(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ACT_TO")), ACT_TO));
        setSTATUS_CODE(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("STATUS_CODE")), STATUS_CODE));
        setSTATUS_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("STATUS_NAME")), STATUS_NAME));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("STAFF_CODE",StringUtils.toString(STAFF_CODE, eiMetadata.getMeta("STAFF_CODE")));
        map.put("STAFF_NAME",StringUtils.toString(STAFF_NAME, eiMetadata.getMeta("STAFF_NAME")));
        map.put("ID_NUMBER",StringUtils.toString(ID_NUMBER, eiMetadata.getMeta("ID_NUMBER")));
        map.put("GENDER_CODE",StringUtils.toString(GENDER_CODE, eiMetadata.getMeta("GENDER_CODE")));
        map.put("TELEPHONE",StringUtils.toString(TELEPHONE, eiMetadata.getMeta("TELEPHONE")));
        map.put("DEFAULT_ADMIN_DEP_CODE",StringUtils.toString(DEFAULT_ADMIN_DEP_CODE, eiMetadata.getMeta("DEFAULT_ADMIN_DEP_CODE")));
        map.put("DEFAULT_ADMIN_DEP_NAME",StringUtils.toString(DEFAULT_ADMIN_DEP_NAME, eiMetadata.getMeta("DEFAULT_ADMIN_DEP_NAME")));
        map.put("DEFAULT_DEP_CODE",StringUtils.toString(DEFAULT_DEP_CODE, eiMetadata.getMeta("DEFAULT_DEP_CODE")));
        map.put("DEFAULT_DEP_NAME",StringUtils.toString(DEFAULT_DEP_NAME, eiMetadata.getMeta("DEFAULT_DEP_NAME")));
        map.put("DEFAULT_BIZ_ID",StringUtils.toString(DEFAULT_BIZ_ID, eiMetadata.getMeta("DEFAULT_BIZ_ID")));
        map.put("RCRT_NAME",StringUtils.toString(RCRT_NAME, eiMetadata.getMeta("RCRT_NAME")));
        map.put("RCRT_CODE",StringUtils.toString(RCRT_CODE, eiMetadata.getMeta("RCRT_CODE")));
        map.put("dept_id",StringUtils.toString(dept_id, eiMetadata.getMeta("dept_id")));
        map.put("WEIXIN_WORK_ACCOUNT",StringUtils.toString(WEIXIN_WORK_ACCOUNT, eiMetadata.getMeta("WEIXIN_WORK_ACCOUNT")));
        map.put("STAFF_INFO",StringUtils.toString(STAFF_INFO, eiMetadata.getMeta("STAFF_INFO")));
        map.put("OCCUPATION_NAME",StringUtils.toString(OCCUPATION_NAME, eiMetadata.getMeta("OCCUPATION_NAME")));
        map.put("ACT_FROM",StringUtils.toString(ACT_FROM, eiMetadata.getMeta("ACT_FROM")));
        map.put("ACT_TO",StringUtils.toString(ACT_TO, eiMetadata.getMeta("ACT_TO")));
        map.put("STATUS_CODE",StringUtils.toString(STATUS_CODE, eiMetadata.getMeta("STATUS_CODE")));
        map.put("STATUS_NAME",StringUtils.toString(STATUS_NAME, eiMetadata.getMeta("STATUS_NAME")));
        return map;
    }
}