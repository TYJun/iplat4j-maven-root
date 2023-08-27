package com.baosight.wilp.mp.pz.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资采购专业科室与物资分类关联实体
 * MpMatTypeConfig
 * @author fangjian
 */
@SuppressWarnings("all")
public class MpMatTypeConfig extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /** 主键*/
    private String id = " ";

    /** 科室编码*/
    private String deptNum = " ";

    /** 科室名称*/
    private String deptName = " ";

    /** 物资分类ID*/
    private String matTypeId = " ";

    /** 物资分类编码*/
    private String matTypeNum = " ";

    /** 物资分类名称*/
    private String matTypeName = " ";

    /** 备注*/
    private String remark = " ";

    /** 创建（申请）人*/
    private String recCreator = " ";

    /** 创建时间*/
    private Date recCreateTime ;

    /** 创建时间*/
    private String recCreateTimeStr ;

    /** 修改人*/
    private String recRevisor = " ";

    /** 修改时间*/
    private Date recReviseTime ;

    /** 修改时间*/
    private String recReviseTimeStr ;

    /** 账套*/
    private String dataGroupCode = " ";

    /** 归档标记*/
    private String archiveFlag = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeId");
        eiColumn.setDescName("物资分类ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeNum");
        eiColumn.setDescName("物资分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建（申请）人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTimeStr");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTimeStr");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("账套");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public MpMatTypeConfig() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the matTypeId - 物资分类ID
     * @return the matTypeId
     */
    public String getMatTypeId() {
        return this.matTypeId;
    }

    /**
     * set the matTypeId - 物资分类ID
     */
    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
    }

    /**
     * get the matTypeNum - 物资分类编码
     * @return the matTypeNum
     */
    public String getMatTypeNum() {
        return this.matTypeNum;
    }

    /**
     * set the matTypeNum - 物资分类编码
     */
    public void setMatTypeNum(String matTypeNum) {
        this.matTypeNum = matTypeNum;
    }

    /**
     * get the matTypeName - 物资分类名称
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the recCreator - 创建（申请）人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建（申请）人
     */
    @Override
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
        if(org.apache.commons.lang3.StringUtils.isBlank(this.recCreateTimeStr)) {
            this.setRecCreateTimeStr(DateUtils.toDateTimeStr19(this.recCreateTime));
        }
    }

    /**
     * get the recCreateTimeStr - 创建时间
     * @return the recCreateTimeStr
     */
    public String getRecCreateTimeStr() {
        return this.recCreateTimeStr;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
        if(org.apache.commons.lang3.StringUtils.isNotBlank(this.recCreateTimeStr)) {
            this.setRecCreateTime(DateUtils.toDateTime(this.recCreateTimeStr));
        }
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public Date getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(Date recReviseTime) {
        this.recReviseTime = recReviseTime;
        if(org.apache.commons.lang3.StringUtils.isBlank(this.recReviseTimeStr)) {
            this.setRecReviseTimeStr(DateUtils.toDateTimeStr19(this.recReviseTime));
        }
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTimeStr() {
        return this.recReviseTimeStr;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTimeStr(String recReviseTimeStr) {
        this.recReviseTimeStr = recReviseTimeStr;
        if(org.apache.commons.lang3.StringUtils.isNotBlank(this.recReviseTimeStr)) {
            this.setRecReviseTime(DateUtils.toDateTime(this.recReviseTimeStr));
        }
    }

    /**
     * get the dataGroupCode - 账套
     * @return the dataGroupCode
     */
    public String getDataGroupCode() {
        return this.dataGroupCode;
    }

    /**
     * set the dataGroupCode - 账套
     */
    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    @Override
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setMatTypeId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeId")), matTypeId));
        setMatTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeNum")), matTypeNum));
        setMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeName")), matTypeName));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("matTypeId",StringUtils.toString(matTypeId, eiMetadata.getMeta("matTypeId")));
        map.put("matTypeNum",StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTimeStr, eiMetadata.getMeta("recReviseTimeStr")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }
}