package com.baosight.wilp.ms.pl.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 关联中间表
 */
public class MSPL0202 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String id = " ";                     //主键
    private String usergroupid = " ";           //依赖主键
    private String tparamid = " ";
    private String groupCname = " ";
    private String groupId = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("usergroupid");
        eiColumn.setDescName("依赖主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tparamid");  //关联tparm表id
        eiColumn.setDescName("param关联字段");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupCname");  //关联tparm表id
        eiColumn.setDescName("分组名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupId");  //关联tparm表id
        eiColumn.setDescName("分组ID");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public MSPL0202() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsergroupid() {
        return usergroupid;
    }

    public void setUsergroupid(String usergroupid) {
        this.usergroupid = usergroupid;
    }

    public String getTparamid() {
        return tparamid;
    }

    public void setTparamid(String tparamid) {
        this.tparamid = tparamid;
    }

    public String getGroupCname() {
        return groupCname;
    }

    public void setGroupCname(String groupCname) {
        this.groupCname = groupCname;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setUsergroupid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("usergroupid")), usergroupid));
        setGroupCname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupCname")), groupCname));
        setTparamid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tparamid")), tparamid));
        setGroupId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupId")), groupId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("usergroupid", StringUtils.toString(usergroupid, eiMetadata.getMeta("usergroupid")));
        map.put("groupCname", StringUtils.toString(groupCname, eiMetadata.getMeta("groupCname")));
        map.put("groupId", StringUtils.toString(groupId, eiMetadata.getMeta("groupId")));
        map.put("tparamid", StringUtils.toString(tparamid, eiMetadata.getMeta("tparamid")));
        return map;
    }
}
