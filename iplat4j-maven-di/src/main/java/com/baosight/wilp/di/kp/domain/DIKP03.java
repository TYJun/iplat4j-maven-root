/**
* Generate time : 2021-04-15 16:01:13
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.kp.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* IMKP03
* 
*/
public class DIKP03 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String idd = " ";		
    private String moduleId = " ";		/* 项目所属分类ID，对于module_base_classfy表的id字段*/
    private String code = " ";		/* 项目编码，规则：所属模块CODE-00/01/02(保养项目/一般巡检项目/常规巡检项目)-0001/9999(序列号)*/
    private String content = " ";		/* 项目内容*/
    private String projectDesc = " ";		/* 项目描述*/
    private String referencevalue = " ";		/* 参数值*/
    private String memo = " ";		/* 备注*/
    private String xunjianAbnormal = " ";		
    private String actualvalueunit = " ";		/* 实际值单位*/
    private String needphoto = " ";		/* 是否需要拍照*/
    private String typecode = " ";		
    private Integer point = new Integer(5);		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("idd");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("moduleId");
        eiColumn.setDescName("项目所属分类ID，对于module_base_classfy表的id字段");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("code");
        eiColumn.setDescName("项目编码，规则：所属模块CODE-00/01/02(保养项目/一般巡检项目/常规巡检项目)-0001/9999(序列号)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("content");
        eiColumn.setDescName("项目内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectDesc");
        eiColumn.setDescName("项目描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("referencevalue");
        eiColumn.setDescName("参数值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("xunjianAbnormal");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualvalueunit");
        eiColumn.setDescName("实际值单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needphoto");
        eiColumn.setDescName("是否需要拍照");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("point");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DIKP03() {
        initMetaData();
    }

    /**
     * get the id 
     * @return the id
     */
    public String getIdd() {
        return this.idd;
    }

    /**
     * set the id 
     */
    public void setIdd(String idd) {
        this.idd = idd;
    }

    /**
     * get the moduleId - 项目所属分类ID，对于module_base_classfy表的id字段
     * @return the moduleId
     */
    public String getModuleId() {
        return this.moduleId;
    }

    /**
     * set the moduleId - 项目所属分类ID，对于module_base_classfy表的id字段
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * get the code - 项目编码，规则：所属模块CODE-00/01/02(保养项目/一般巡检项目/常规巡检项目)-0001/9999(序列号)
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * set the code - 项目编码，规则：所属模块CODE-00/01/02(保养项目/一般巡检项目/常规巡检项目)-0001/9999(序列号)
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * get the content - 项目内容
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * set the content - 项目内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get the projectDesc - 项目描述
     * @return the projectDesc
     */
    public String getProjectDesc() {
        return this.projectDesc;
    }

    /**
     * set the projectDesc - 项目描述
     */
    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    /**
     * get the referencevalue - 参数值
     * @return the referencevalue
     */
    public String getReferencevalue() {
        return this.referencevalue;
    }

    /**
     * set the referencevalue - 参数值
     */
    public void setReferencevalue(String referencevalue) {
        this.referencevalue = referencevalue;
    }

    /**
     * get the memo - 备注
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * set the memo - 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * get the xunjianAbnormal 
     * @return the xunjianAbnormal
     */
    public String getXunjianAbnormal() {
        return this.xunjianAbnormal;
    }

    /**
     * set the xunjianAbnormal 
     */
    public void setXunjianAbnormal(String xunjianAbnormal) {
        this.xunjianAbnormal = xunjianAbnormal;
    }

    /**
     * get the actualvalueunit - 实际值单位
     * @return the actualvalueunit
     */
    public String getActualvalueunit() {
        return this.actualvalueunit;
    }

    /**
     * set the actualvalueunit - 实际值单位
     */
    public void setActualvalueunit(String actualvalueunit) {
        this.actualvalueunit = actualvalueunit;
    }

    /**
     * get the needphoto - 是否需要拍照
     * @return the needphoto
     */
    public String getNeedphoto() {
        return this.needphoto;
    }

    /**
     * set the needphoto - 是否需要拍照
     */
    public void setNeedphoto(String needphoto) {
        this.needphoto = needphoto;
    }

    /**
     * get the typecode 
     * @return the typecode
     */
    public String getTypecode() {
        return this.typecode;
    }

    /**
     * set the typecode 
     */
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    /**
     * get the point 
     * @return the point
     */
    public Integer getPoint() {
        return this.point;
    }

    /**
     * set the point 
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setIdd(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("idd")), idd));
        setModuleId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("moduleId")), moduleId));
        setCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("code")), code));
        setContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("content")), content));
        setProjectDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectDesc")), projectDesc));
        setReferencevalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("referencevalue")), referencevalue));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setXunjianAbnormal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("xunjianAbnormal")), xunjianAbnormal));
        setActualvalueunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualvalueunit")), actualvalueunit));
        setNeedphoto(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needphoto")), needphoto));
        setTypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typecode")), typecode));
        setPoint(NumberUtils.toInteger(StringUtils.toString(map.get("point")), point));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("idd",StringUtils.toString(idd, eiMetadata.getMeta("idd")));
        map.put("moduleId",StringUtils.toString(moduleId, eiMetadata.getMeta("moduleId")));
        map.put("code",StringUtils.toString(code, eiMetadata.getMeta("code")));
        map.put("content",StringUtils.toString(content, eiMetadata.getMeta("content")));
        map.put("projectDesc",StringUtils.toString(projectDesc, eiMetadata.getMeta("projectDesc")));
        map.put("referencevalue",StringUtils.toString(referencevalue, eiMetadata.getMeta("referencevalue")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("xunjianAbnormal",StringUtils.toString(xunjianAbnormal, eiMetadata.getMeta("xunjianAbnormal")));
        map.put("actualvalueunit",StringUtils.toString(actualvalueunit, eiMetadata.getMeta("actualvalueunit")));
        map.put("needphoto",StringUtils.toString(needphoto, eiMetadata.getMeta("needphoto")));
        map.put("typecode",StringUtils.toString(typecode, eiMetadata.getMeta("typecode")));
        map.put("point",StringUtils.toString(point, eiMetadata.getMeta("point")));
        return map;
    }
}