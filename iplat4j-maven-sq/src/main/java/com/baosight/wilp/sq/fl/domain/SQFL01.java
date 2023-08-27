/**
* Generate time : 2021-07-26 16:49:00
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.fl.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SqPersonnelGroup
* 
*/
public class SQFL01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 人员组id*/
    private String perGroupNo = " ";		/* 人员组编号*/
    private String perGroupName = " ";		/* 人员组名称*/
    private String workNo = " ";		/* 人员编号*/
    private String name = " ";		/* 人员名称*/
    private String deptNum = " ";		/* 科室编号*/
    private String deptName = " ";		/* 科室名称*/
    private String createtime = " ";		/* 创建时间*/
    private String updatetime = " ";		/* 修改时间*/
    private String groupId = " ";   /* 分组id*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("人员组id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("perGroupNo");
        eiColumn.setDescName("人员组编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("perGroupName");
        eiColumn.setDescName("人员组名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("人员编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("人员名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatetime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("groupId");
        eiColumn.setDescName("分组id");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SQFL01() {
        initMetaData();
    }

    /**
     * get the id - 人员组id
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 人员组id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the perGroupNo - 人员组编号
     * @return the perGroupNo
     */
    public String getPerGroupNo() {
        return this.perGroupNo;
    }

    /**
     * set the perGroupNo - 人员组编号
     */
    public void setPerGroupNo(String perGroupNo) {
        this.perGroupNo = perGroupNo;
    }

    /**
     * get the perGroupName - 人员组名称
     * @return the perGroupName
     */
    public String getPerGroupName() {
        return this.perGroupName;
    }

    /**
     * set the perGroupName - 人员组名称
     */
    public void setPerGroupName(String perGroupName) {
        this.perGroupName = perGroupName;
    }

    /**
     * get the workNo - 人员编号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 人员编号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
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
     * get the deptNum - 科室编号
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编号
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
     * get the createtime - 创建时间
     * @return the createtime
     */
    public String getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime - 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * get the updatetime - 修改时间
     * @return the updatetime
     */
    public String getUpdatetime() {
        return this.updatetime;
    }

    /**
     * set the updatetime - 修改时间
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 将Map中的值存入实体
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setPerGroupNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("perGroupNo")), perGroupNo));
        setPerGroupName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("perGroupName")), perGroupName));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setCreatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createtime")), createtime));
        setUpdatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatetime")), updatetime));
        setGroupId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupId")), groupId));
    }

    /**
     * 将值塞到Map中
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("perGroupNo",StringUtils.toString(perGroupNo, eiMetadata.getMeta("perGroupNo")));
        map.put("perGroupName",StringUtils.toString(perGroupName, eiMetadata.getMeta("perGroupName")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("updatetime",StringUtils.toString(updatetime, eiMetadata.getMeta("updatetime")));
        map.put("groupId",StringUtils.toString(groupId, eiMetadata.getMeta("groupId")));
        return map;
    }
}