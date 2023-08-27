package com.baosight.wilp.ms.pl.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dell
 * @title: MSPL0201
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021-08-2313:50
 */
public class MSPL0201 extends DaoEPBase {
    private static final long serialVersionUID = 1L;
    private String groupEname;
    private String recRevisor;
    private String groupType;
    private String recCreator;
    private String sortIndex;
    private String recReviseTime;
    private String archiveFlag;
    private String groupId;
    private String recCreateTime;
    private String groupCname;

    public MSPL0201() {
        initMetaData();
    }

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("groupId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupCname");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortIndex");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupType");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupEname");
        eiMetadata.addMeta(eiColumn);
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupEname() {
        return groupEname;
    }

    public void setGroupEname(String groupEnam) {
        this.groupEname = groupEnam;
    }

    public String getRecRevisor() {
        return recRevisor;
    }

    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    public String getRecCreator() {
        return recCreator;
    }

    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getRecReviseTime() {
        return recReviseTime;
    }

    @Override
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    public String getArchiveFlag() {
        return archiveFlag;
    }

    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRecCreateTime() {
        return recCreateTime;
    }

    @Override
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    public String getGroupCname() {
        return groupCname;
    }

    public void setGroupCname(String groupCname) {
        this.groupCname = groupCname;
    }

    public void fromMap(Map map) {

        setGroupId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupId")), groupId));
        setGroupCname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupCname")), groupCname));
        setGroupType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupType")), groupType));
        setGroupEname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupEname")), groupEname));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setSortIndex(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sortIndex")), sortIndex));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
    }

    public Map toMap() {
        Map map = new HashMap();
        map.put("groupId", StringUtils.toString(groupId, eiMetadata.getMeta("groupId")));
        map.put("groupCname", StringUtils.toString(groupCname, eiMetadata.getMeta("groupCname")));
        map.put("groupType", StringUtils.toString(groupType, eiMetadata.getMeta("groupType")));
        map.put("groupEname", StringUtils.toString(groupEname, eiMetadata.getMeta("groupEname")));
        map.put("recRevisor", StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recCreator", StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("sortIndex", StringUtils.toString(sortIndex, eiMetadata.getMeta("sortIndex")));
        map.put("archiveFlag", StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recReviseTime", StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        return map;
    }
}
