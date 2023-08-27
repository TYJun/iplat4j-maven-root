package com.baosight.wilp.ms.dc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 15479
 * @title: MSDC01
 * @projectName iplat_v5_monitor
 * @description: 设备配置实体类
 * @date 2021/8/2 13:58
 */
public class MSDC01 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String DC_id =" ";                     //uuid、非空
    private String code = " ";                  //设备代码
    private String name = " ";                  //设备名称
    private String type = " ";                  //设备大类
    private String weight = " ";                //权重
    private String t_area_classify_id = " ";    //所属设备区域分类主键
    private String group_id =  " ";             //院区标识
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("DC_id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("DC_id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("code");
        eiColumn.setDescName("设备代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("设备名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setDescName("设备大类");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("weight");
        eiColumn.setDescName("权重");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("t_area_classify_id");
        eiColumn.setDescName("所属设备区域分类主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("group_id");
        eiColumn.setDescName("院区标识");
        eiMetadata.addMeta(eiColumn);

    }
    /**
     * the constructor
     */
    public MSDC01() {
        initMetaData();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setT_area_classify_id(String t_area_classify_id) {
        this.t_area_classify_id = t_area_classify_id;
    }

    public String getT_area_classify_id() {
        return t_area_classify_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_id() {
        return group_id;
    }
    public String getDC_id() {
        return DC_id;
    }

    public void setDC_id(String DC_id) {
        this.DC_id = DC_id;
    }
    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setDC_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("DC_id")), DC_id));
        setCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("code")), code));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
        setWeight(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("weight")), weight));
        setT_area_classify_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("t_area_classify_id")), t_area_classify_id));
        setGroup_id(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("group_id")), group_id));
    }
    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("DC_id",StringUtils.toString(DC_id, eiMetadata.getMeta("DC_id")));
        map.put("code",StringUtils.toString(code, eiMetadata.getMeta("code")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("type",StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("weight",StringUtils.toString(weight, eiMetadata.getMeta("weight")));
        map.put("t_area_classify_id",StringUtils.toString(t_area_classify_id, eiMetadata.getMeta("t_area_classify_id")));
        map.put("group_id",StringUtils.toString(group_id, eiMetadata.getMeta("group_id")));
        return map;
    }

}
