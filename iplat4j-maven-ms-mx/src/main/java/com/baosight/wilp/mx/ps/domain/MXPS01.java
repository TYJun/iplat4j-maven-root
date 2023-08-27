package com.baosight.wilp.mx.ps.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ms.pl.domain.MSPL01;

import java.util.Map;

/**
 * 供配电系统
 * @author: panlingfeng
 * @createDate: 2021/8/11 6:56 下午
 */
public class MXPS01 extends MSPL01 {

    private String scope; //正常值范围
    private String tagName; //表情名称
    private String lowerLower; //低低报警
    private String upperUpper; //高高报警
    private String classifyId; //区域编号
    private String classifyName; //区域名称

    public void initMetaData() {
        super.initMetaData();
        EiColumn eiColumn;
        eiColumn = new EiColumn("scope");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("正常值范围");
        eiMetadata.addMeta(eiColumn);
        eiColumn = new EiColumn("tagName");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("参数名称");
        eiMetadata.addMeta(eiColumn);
        eiColumn = new EiColumn("lowerLower");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("低低报警");
        eiMetadata.addMeta(eiColumn);
        eiColumn = new EiColumn("upperUpper");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("高高报警");
        eiMetadata.addMeta(eiColumn);
        eiColumn = new EiColumn("classifyId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("区域编号");
        eiMetadata.addMeta(eiColumn);
        eiColumn = new EiColumn("classifyName");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("区域");
        eiMetadata.addMeta(eiColumn);
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getLowerLower() {
        return lowerLower;
    }

    public void setLowerLower(String lowerLower) {
        this.lowerLower = lowerLower;
    }

    public String getUpperUpper() {
        return upperUpper;
    }

    public void setUpperUpper(String upperUpper) {
        this.upperUpper = upperUpper;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public void fromMap(Map map) {
        super.fromMap(map);
        setScope(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scope")), scope));
        setTagName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tagName")), tagName));
        setLowerLower(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lowerLower")), lowerLower));
        setUpperUpper(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("upperUpper")), upperUpper));
        setClassifyId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyId")), classifyId));
        setClassifyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyName")), classifyName));
    }

    public Map toMap() {
        Map map = super.toMap();
        map.put("scope", StringUtils.toString(scope, eiMetadata.getMeta("scope")));
        map.put("tagName", StringUtils.toString(tagName, eiMetadata.getMeta("tagName")));
        map.put("lowerLower", StringUtils.toString(lowerLower, eiMetadata.getMeta("lowerLower")));
        map.put("upperUpper", StringUtils.toString(upperUpper, eiMetadata.getMeta("upperUpper")));
        map.put("classifyId", StringUtils.toString(classifyId, eiMetadata.getMeta("classifyId")));
        map.put("classifyName", StringUtils.toString(classifyName, eiMetadata.getMeta("classifyName")));
        return map;
    }
}
