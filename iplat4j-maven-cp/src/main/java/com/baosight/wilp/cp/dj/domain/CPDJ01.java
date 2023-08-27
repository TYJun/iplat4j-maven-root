package com.baosight.wilp.cp.dj.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class CPDJ01 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String id = " ";
    private String groupNum = " ";		/* 分组编码*/
    private String groupName = " ";		/* 分组名称*/
    private String deptNum = " ";		/* 科室编码*/
    private String deptName = " ";		/* 科室名称*/
    private Timestamp createTime ;		/* 创建时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupNum");
        eiColumn.setDescName("分组编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupName");
        eiColumn.setDescName("分组名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CPDJ01() {
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
     * get the groupNum - 分组编码
     * @return the groupNum
     */
    public String getGroupNum() {
        return this.groupNum;
    }

    /**
     * set the groupNum - 分组编码
     */
    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    /**
     * get the groupName - 分组名称
     * @return the groupName
     */
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * set the groupName - 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setGroupNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupNum")), groupNum));
        setGroupName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupName")), groupName));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("groupNum",StringUtils.toString(groupNum, eiMetadata.getMeta("groupNum")));
        map.put("groupName",StringUtils.toString(groupName, eiMetadata.getMeta("groupName")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        return map;
    }
}
