/**
* Generate time : 2022-05-18 13:29:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.df.common.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DfCleaningExecutor
* 
*/
public class DfCleaningExecutor extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String cleanId = " ";		/* 保洁主键*/
    private String cleanNo = " ";		/* 保洁单号*/
    private String workNo = " ";		/* 工号*/
    private String name = " ";		/* 姓名*/
    private String deptNum = " ";		/* 科室编码*/
    private String deptName = " ";		/* 科室名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cleanId");
        eiColumn.setDescName("保洁主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cleanNo");
        eiColumn.setDescName("保洁单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DfCleaningExecutor() {
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
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the cleanId - 保洁主键
     * @return the cleanId
     */
    public String getCleanId() {
        return this.cleanId;
    }

    /**
     * set the cleanId - 保洁主键
     */
    public void setCleanId(String cleanId) {
        this.cleanId = cleanId;
    }

    /**
     * get the cleanNo - 保洁单号
     * @return the cleanNo
     */
    public String getCleanNo() {
        return this.cleanNo;
    }

    /**
     * set the cleanNo - 保洁单号
     */
    public void setCleanNo(String cleanNo) {
        this.cleanNo = cleanNo;
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
     * get the name - 姓名
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        //setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")),id));
        setCleanId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanId")), cleanId));
        setCleanNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanNo")), cleanNo));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        //map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("cleanId",StringUtils.toString(cleanId, eiMetadata.getMeta("cleanId")));
        map.put("cleanNo",StringUtils.toString(cleanNo, eiMetadata.getMeta("cleanNo")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        return map;
    }
}