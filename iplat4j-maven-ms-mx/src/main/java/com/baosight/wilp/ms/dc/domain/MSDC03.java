package com.baosight.wilp.ms.dc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 孔帅博
 * 1、参数区域封装类
 */
public class MSDC03 extends DaoEPBase {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 父节节点编号
     */
    private String parentId;

    /**
     * 分类编号
     */
    private String classifyCode;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 树类型
     */
    private String classifyType;

    /**
     * 排序
     */
    private String classifySort;

    /**
     * 院区标识
     */
    private String groupId;

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("父节节点编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyCode");
        eiColumn.setDescName("分类编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyName");
        eiColumn.setDescName("分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyType");
        eiColumn.setDescName("树类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifySort");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupId");
        eiColumn.setDescName("院区标识");
        eiMetadata.addMeta(eiColumn);
    }

    public MSDC03() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(String classifyType) {
        this.classifyType = classifyType;
    }

    public String getClassifySort() {
        return classifySort;
    }

    public void setClassifySort(String classifySort) {
        this.classifySort = classifySort;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setClassifyCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyCode")), classifyCode));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
        setClassifyType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyType")), classifyType));
        setClassifySort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifySort")), classifySort));
        setGroupId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupId")), groupId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("parentId", StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("classifyCode", StringUtils.toString(classifyCode, eiMetadata.getMeta("classifyCode")));
        map.put("classifyName", StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        map.put("classifyType", StringUtils.toString(classifyType, eiMetadata.getMeta("classifyType")));
        map.put("classifySort", StringUtils.toString(classifySort, eiMetadata.getMeta("classifySort")));
        map.put("groupId", StringUtils.toString(groupId, eiMetadata.getMeta("groupId")));
        return map;
    }
}
