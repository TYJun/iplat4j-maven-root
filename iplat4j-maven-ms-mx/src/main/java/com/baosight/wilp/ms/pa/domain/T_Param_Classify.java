package com.baosight.wilp.ms.pa.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wzy
 * @title: T_Param_Classify
 * @projectName iplat_v5_monitor
 * @description: TODO
 * @date 2021/8/99:20
 */
public class T_Param_Classify extends DaoEPBase {
    private String id = "";                   //主键  id
    private String parent_id = "";            //父节节点编号
    private String classify_code = "";        //分类编号
    private String classify_name = "";        //分类名称
    private char classify_type = '\0';        //树类型
    private int classify_sort = 0;            //排序
    private String group_id = "";             //院区标识


    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parent_id");
        eiColumn.setDescName("父节节点编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classify_code");
        eiColumn.setDescName("分类编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classify_name");
        eiColumn.setDescName("分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classify_type");
        eiColumn.setDescName("树类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classify_sort");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("group_id");
        eiColumn.setDescName("院区标识");
        eiMetadata.addMeta(eiColumn);
    }

    public T_Param_Classify() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getClassify_code() {
        return classify_code;
    }

    public void setClassify_code(String classify_code) {
        this.classify_code = classify_code;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public char getClassify_type() {
        return classify_type;
    }

    public void setClassify_type(char classify_type) {
        this.classify_type = classify_type;
    }

    public int getClassify_sort() {
        return classify_sort;
    }

    public void setClassify_sort(int classify_sort) {
        this.classify_sort = classify_sort;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setParent_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parent_id")), parent_id));
        setClassify_code(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classify_code")), classify_code));
        setClassify_name(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classify_name")), classify_name));
        setGroup_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("group_id")), group_id));
    }


    /**
     * 把所有字段全部放进map  然后页面映射
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("parent_id", StringUtils.toString(parent_id, eiMetadata.getMeta("parent_id")));
        map.put("classify_code", StringUtils.toString(classify_code, eiMetadata.getMeta("classify_code")));
        map.put("classify_name", StringUtils.toString(classify_name, eiMetadata.getMeta("classify_name")));
        map.put("group_id", StringUtils.toString(group_id, eiMetadata.getMeta("group_id")));
        return map;
    }
}
