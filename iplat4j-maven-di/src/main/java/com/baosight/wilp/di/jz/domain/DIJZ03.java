package com.baosight.wilp.di.jz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class DIJZ03 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String id = " ";
    private String configureNum = " ";		/* 分组编码*/
    private String configureName = " ";		/* 分组名称*/
    private String configureTime = " ";		/* 所需时间*/
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

        eiColumn = new EiColumn("configureNum");
        eiColumn.setDescName("分组编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configureName");
        eiColumn.setDescName("分组名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("configureTime");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DIJZ03() {
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
    public String getConfigureNum() {
        return this.configureNum;
    }

    /**
     * set the groupNum - 分组编码
     */
    public void setConfigureNum(String configureNum) {
        this.configureNum = configureNum;
    }

    /**
     * get the groupName - 分组名称
     * @return the groupName
     */
    public String getConfigureName() {
        return this.configureName;
    }

    /**
     * set the groupName - 分组名称
     */
    public void setConfigureName(String configureName) {
        this.configureName = configureName;
    }

    /**
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getConfigureTime() {
        return this.configureTime;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setConfigureTime(String configureTime) {
        this.configureTime = configureTime;
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
        setConfigureNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configureNum")), configureNum));
        setConfigureName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configureName")), configureName));
        setConfigureTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("configureTime")), configureTime));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("configureNum",StringUtils.toString(configureNum, eiMetadata.getMeta("configureNum")));
        map.put("configureName",StringUtils.toString(configureName, eiMetadata.getMeta("configureName")));
        map.put("configureTime",StringUtils.toString(configureTime, eiMetadata.getMeta("configureTime")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        return map;
    }
}
