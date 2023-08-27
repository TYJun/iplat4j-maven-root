package com.baosight.wilp.ps.sc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PSSC01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";
    private String needValidate = " ";
    private String workNo = " ";
    private String contactTel = " ";
    private String deptName = " ";
    private String deptCode = " ";		/* 说明*/
    private String datagroupCode = " ";
    private String name = " ";
    private String roleCodes = " ";
    private String roleNames = " ";
    private String paramdesc3 = " ";
    private String paramvalue3 = " ";
    private String paramdesc4 = " ";
    private String paramvalue4 = " ";


    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needValidate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contactTel");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptCode");
        eiColumn.setDescName("说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datagroupCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roleCodes");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("roleNames");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc3");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue3");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramdesc4");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("paramvalue4");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);




    }

    /**
     * the constructor
     */
    public PSSC01() {
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
     * get the typecode
     * @return the typecode
     */
    public String getNeedValidate() {
        return this.needValidate;
    }

    /**
     * set the typecode
     */
    public void setNeedValidate(String needValidate) {
        this.needValidate = needValidate;
    }

    /**
     * get the typename
     * @return the typename
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the typename
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the typepid
     * @return the typepid
     */
    public String getContactTel() {
        return this.contactTel;
    }

    /**
     * set the typepid
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    /**
     * get the typegroupid
     * @return the typegroupid
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the typegroupid
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the typemessage - 说明
     * @return the typemessage
     */
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * set the typemessage - 说明
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * get the paramdesc1
     * @return the paramdesc1
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the paramdesc1
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the paramvalue1
     * @return the paramvalue1
     */
    public String getRoleCodes() {
        return this.roleCodes;
    }

    /**
     * set the paramvalue1
     */
    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }

    /**
     * get the paramdesc2
     * @return the paramdesc2
     */
    public String getRoleNames() {
        return this.roleNames;
    }

    /**
     * set the paramdesc2
     */
    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    /**
     * get the paramvalue2
     * @return the paramvalue2
     */
    public String getDatagroupCode() {
        return this.datagroupCode;
    }

    /**
     * set the paramvalue2
     */
    public void setDatagroupCode(String datagroupCode) {
        this.datagroupCode = datagroupCode;
    }

    /**
     * get the paramdesc3
     * @return the paramdesc3
     */
    public String getParamdesc3() {
        return this.paramdesc3;
    }

    /**
     * set the paramdesc3
     */
    public void setParamdesc3(String paramdesc3) {
        this.paramdesc3 = paramdesc3;
    }

    /**
     * get the paramvalue3
     * @return the paramvalue3
     */
    public String getParamvalue3() {
        return this.paramvalue3;
    }

    /**
     * set the paramvalue3
     */
    public void setParamvalue3(String paramvalue3) {
        this.paramvalue3 = paramvalue3;
    }

    /**
     * get the paramdesc4
     * @return the paramdesc4
     */
    public String getParamdesc4() {
        return this.paramdesc4;
    }

    /**
     * set the paramdesc4
     */
    public void setParamdesc4(String paramdesc4) {
        this.paramdesc4 = paramdesc4;
    }

    /**
     * get the paramvalue4
     * @return the paramvalue4
     */
    public String getParamvalue4() {
        return this.paramvalue4;
    }

    /**
     * set the paramvalue4
     */
    public void setParamvalue4(String paramvalue4) {
        this.paramvalue4 = paramvalue4;
    }



    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setNeedValidate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needValidate")), needValidate));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setContactTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contactTel")), contactTel));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setDeptCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptCode")), deptCode));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setDatagroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datagroupCode")), datagroupCode));
        setRoleCodes(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roleCodes")), roleCodes));
        setRoleNames(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("roleNames")), roleNames));
        setParamdesc3(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramdesc3")), paramdesc3));
        setParamvalue3(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramvalue3")), paramvalue3));
        setParamdesc4(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramdesc4")), paramdesc4));
        setParamvalue4(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramvalue4")), paramvalue4));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("needValidate",StringUtils.toString(needValidate, eiMetadata.getMeta("needValidate")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("contactTel",StringUtils.toString(contactTel, eiMetadata.getMeta("contactTel")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("deptCode",StringUtils.toString(deptCode, eiMetadata.getMeta("deptCode")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("datagroupCode",StringUtils.toString(datagroupCode, eiMetadata.getMeta("datagroupCode")));
        map.put("roleCodes",StringUtils.toString(roleCodes, eiMetadata.getMeta("roleCodes")));
        map.put("roleNames",StringUtils.toString(roleNames, eiMetadata.getMeta("roleNames")));
        map.put("paramdesc3",StringUtils.toString(paramdesc3, eiMetadata.getMeta("paramdesc3")));
        map.put("paramvalue3",StringUtils.toString(paramvalue3, eiMetadata.getMeta("paramvalue3")));
        map.put("paramdesc4",StringUtils.toString(paramdesc4, eiMetadata.getMeta("paramdesc4")));
        map.put("paramvalue4",StringUtils.toString(paramvalue4, eiMetadata.getMeta("paramvalue4")));
        return map;
    }
}
