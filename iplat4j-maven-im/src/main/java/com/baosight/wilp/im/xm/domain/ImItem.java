/**
* Generate time : 2021-05-03 10:19:20
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.xm.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiItem
* 
*/
public class ImItem extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String code = " ";		/* 项目编码，规则：所属模块CODE-00/01/02(保养项目/一般巡检项目/常规巡检项目)-0001/9999(序列号)*/
    private String content = " ";		/* 项目内容*/
    private String projectDesc = " ";		/* 项目描述*/
    private String referencevalue = " ";		/* 参数值*/
    private String memo = " ";		/* 备注*/
    private String xunjianAbnormal = " ";		
    private String actualvalueunit = " ";		/* 实际值单位*/
    private String needphoto = " ";		/* Y是 N否*/
    private String abnormalNo = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
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
        eiColumn.setDescName("Y是 N否");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalNo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImItem() {
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
     * get the needphoto - Y是 N否
     * @return the needphoto
     */
    public String getNeedphoto() {
        return this.needphoto;
    }

    /**
     * set the needphoto - Y是 N否
     */
    public void setNeedphoto(String needphoto) {
        this.needphoto = needphoto;
    }

    /**
     * get the abnormalNo 
     * @return the abnormalNo
     */
    public String getAbnormalNo() {
        return this.abnormalNo;
    }

    /**
     * set the abnormalNo 
     */
    public void setAbnormalNo(String abnormalNo) {
        this.abnormalNo = abnormalNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("code")), code));
        setContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("content")), content));
        setProjectDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectDesc")), projectDesc));
        setReferencevalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("referencevalue")), referencevalue));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setXunjianAbnormal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("xunjianAbnormal")), xunjianAbnormal));
        setActualvalueunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualvalueunit")), actualvalueunit));
        setNeedphoto(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needphoto")), needphoto));
        setAbnormalNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalNo")), abnormalNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("code",StringUtils.toString(code, eiMetadata.getMeta("code")));
        map.put("content",StringUtils.toString(content, eiMetadata.getMeta("content")));
        map.put("projectDesc",StringUtils.toString(projectDesc, eiMetadata.getMeta("projectDesc")));
        map.put("referencevalue",StringUtils.toString(referencevalue, eiMetadata.getMeta("referencevalue")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("xunjianAbnormal",StringUtils.toString(xunjianAbnormal, eiMetadata.getMeta("xunjianAbnormal")));
        map.put("actualvalueunit",StringUtils.toString(actualvalueunit, eiMetadata.getMeta("actualvalueunit")));
        map.put("needphoto",StringUtils.toString(needphoto, eiMetadata.getMeta("needphoto")));
        map.put("abnormalNo",StringUtils.toString(abnormalNo, eiMetadata.getMeta("abnormalNo")));
        return map;
    }
}