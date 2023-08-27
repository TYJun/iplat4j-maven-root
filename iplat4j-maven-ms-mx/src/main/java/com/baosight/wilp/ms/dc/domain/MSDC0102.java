package com.baosight.wilp.ms.dc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dell
 * @title: MSDC0102
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021-08-1310:39
 */
public class MSDC0102 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String Reid =" ";                     //主键
    private String RetDCid = " ";               //依赖主键
    private String ReName = " ";               //依赖主键
    private String ReTag = " ";               //依赖主键



    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("Reid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("所属设备主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("RetDCid");
        eiColumn.setDescName("关联参数id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ReName");
        eiColumn.setDescName("参数类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ReTag");
        eiColumn.setDescName("参数项Tag");
        eiMetadata.addMeta(eiColumn);

    }
    /**
     * the constructor
     */
    public MSDC0102() {
        initMetaData();
    }


    public String getReid() {
        return Reid;
    }

    public void setReid(String reid) {
        Reid = reid;
    }

    public String getRetDCid() {
        return RetDCid;
    }

    public void setRetDCid(String retDCid) {
        RetDCid = retDCid;
    }

    public String getReName() {
        return ReName;
    }

    public void setReName(String reName) {
        ReName = reName;
    }

    public String getReTag() {
        return ReTag;
    }

    public void setReTag(String reTag) {
        ReTag = reTag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setReid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("Reid")), Reid));
        setRetDCid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("RetDCid")),RetDCid));
        setReName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ReName")), ReName));
        setReTag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ReTag")),ReTag));
    }
    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("Reid",StringUtils.toString(ReTag, eiMetadata.getMeta("ReTag")));
        map.put("RetDCid",StringUtils.toString(RetDCid, eiMetadata.getMeta("RetDCid")));
        map.put("ReName",StringUtils.toString(ReTag, eiMetadata.getMeta("ReName")));
        map.put("ReTag",StringUtils.toString(RetDCid, eiMetadata.getMeta("ReTag")));
        return map;
    }
}
