package com.baosight.wilp.ms.ar.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 15479
 * @title: MSDC01
 * @projectName iplat_v5_monitor
 * @description: 报警配置实体类
 * @date 2021/8/2 13:58
 */
public class MSAR01 extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    /*主键*/
    private String id;
    /*规则代码*/
    private String code;
    /*规则说明*/
    private String description;
    /*计量单位*/
    private String unit;
    /*计量量纲*/
    private String dimension;
    private String dimensionName;
    private String unitName;
    /*低报警*/
    private String lower;
    /*低低报警*/
    private String lowerLower;
    /*高报警*/
    private String upper;
    /*高高报警*/
    private String upperUpper;
    /*创建人*/
    private String createBy;
    /*创建时间*/
    private String createDate;
    /*更新人*/
    private String updateBy;
    /*更新时间*/
    private String updateDate;
    /*备注*/
    private String remarks;
    /*院区标识*/
    private String groupId;

    /*绑定参数*/
    private String tmsCount;

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("code");
        eiColumn.setDescName("规则代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setDescName("规则说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setDescName("计量单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitName");
        eiColumn.setDescName("计量单位名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dimension");
        eiColumn.setDescName("计量量纲");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dimensionName");
        eiColumn.setDescName("计量量纲名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lower");
        eiColumn.setDescName("低报警");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lowerLower");
        eiColumn.setDescName("低低报警");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("upper");
        eiColumn.setDescName("高报警");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("upperUpper");
        eiColumn.setDescName("高高报警");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createBy");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateBy");
        eiColumn.setDescName("更新人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateDate");
        eiColumn.setDescName("更新时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remarks");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupId");
        eiColumn.setDescName("院区标识");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tmsCount");
        eiColumn.setDescName("绑定参数");
        eiMetadata.addMeta(eiColumn);
    }

    public MSAR01() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }

    public String getLowerLower() {
        return lowerLower;
    }

    public void setLowerLower(String lowerLower) {
        this.lowerLower = lowerLower;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getUpperUpper() {
        return upperUpper;
    }

    public void setUpperUpper(String upperUpper) {
        this.upperUpper = upperUpper;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTmsCount() {
        return tmsCount;
    }

    public void setTmsCount(String tmsCount) {
        this.tmsCount = tmsCount;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("code")), code));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setUnitName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitName")), unitName));
        setDimension(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dimension")), dimension));
        setDimensionName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dimensionName")), dimensionName));
        setLower(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lower")), lower));
        setLowerLower(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lowerLower")), lowerLower));
        setUpper(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("upper")), upper));
        setUpperUpper(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("upperUpper")), upperUpper));
        setCreateBy(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createBy")), createBy));
        setCreateDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createDate")), createDate));
        setUpdateBy(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateBy")), updateBy));
        setUpdateDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateDate")), updateDate));
        setRemarks(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remarks")), remarks));
        setGroupId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupId")), groupId));
        setTmsCount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tmsCount")),tmsCount));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("code", StringUtils.toString(code, eiMetadata.getMeta("code")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("unitName", StringUtils.toString(unitName, eiMetadata.getMeta("unitName")));
        map.put("dimension", StringUtils.toString(dimension, eiMetadata.getMeta("dimension")));
        map.put("dimensionName", StringUtils.toString(dimensionName, eiMetadata.getMeta("dimensionName")));
        map.put("lower", StringUtils.toString(lower, eiMetadata.getMeta("lower")));
        map.put("lowerLower", StringUtils.toString(lowerLower, eiMetadata.getMeta("lowerLower")));
        map.put("upper", StringUtils.toString(upper, eiMetadata.getMeta("upper")));
        map.put("upperUpper", StringUtils.toString(upperUpper, eiMetadata.getMeta("upperUpper")));
        map.put("createBy", StringUtils.toString(createBy, eiMetadata.getMeta("createBy")));
        map.put("createDate", StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        map.put("updateBy", StringUtils.toString(updateBy, eiMetadata.getMeta("updateBy")));
        map.put("updateDate", StringUtils.toString(updateDate, eiMetadata.getMeta("updateDate")));
        map.put("remarks", StringUtils.toString(remarks, eiMetadata.getMeta("remarks")));
        map.put("groupId", StringUtils.toString(groupId, eiMetadata.getMeta("groupId")));
        map.put("tmsCount", StringUtils.toString(tmsCount, eiMetadata.getMeta("tmsCount")));
        return map;
    }
}
