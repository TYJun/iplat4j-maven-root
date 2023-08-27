/**
* Generate time : 2021-08-17 17:42:46
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.si.wh.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* SiWarehouse ： 仓库实体
* 
*/
@SuppressWarnings("all")
public class SiWarehouse extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String recCreator = " ";		/* 记录创建责任者*/
    private String recCreateTime = " ";		/* 记录创建时间*/
    private String recRevisor = " ";		/* 记录修改责任者*/
    private String recReviseTime = " ";		/* 记录修改时间*/
    private String dataGroupCode = " ";		/* 院区（账套）*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String wareHouseNo = " ";		/* 仓库号*/
    private String wareHouseName = " ";		/* 仓库名称*/
    private String freezeFlag = " ";		/* 冻结标记*/
    private String wareHouseType = " ";		/* 仓库类型*/
    private String priceType = " ";		/* 计价方式*/
    private String workNo = " ";		/* 仓库管理员（未用）*/
    private String workName = " ";		/* 仓库管理员（未用）*/
    private Integer managementType = new Integer(1);		/* 管理类型（未用）*/
    private String matTypeNum = " ";		/* 物资分类编码（未用）*/
    private String matTypeName = " ";		/* 物资分类名称（未用）*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("记录创建责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("记录创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("记录修改责任者");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("记录修改时间");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataGroupCode");
        eiColumn.setDescName("院区（账套）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseNo");
        eiColumn.setDescName("仓库号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseName");
        eiColumn.setDescName("仓库名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("freezeFlag");
        eiColumn.setDescName("冻结标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wareHouseType");
        eiColumn.setDescName("仓库类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("priceType");
        eiColumn.setDescName("计价方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("仓库管理员（未用）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workName");
        eiColumn.setDescName("仓库管理员（未用）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managementType");
        eiColumn.setDescName("管理类型（未用）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeNum");
        eiColumn.setDescName("物资分类编码（未用）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("matTypeName");
        eiColumn.setDescName("物资分类名称（未用）");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SiWarehouse() {
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
     * get the recCreator - 记录创建责任者
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 记录创建责任者
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 记录创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 记录创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 记录修改责任者
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 记录修改责任者
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 记录修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 记录修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }
    
    /**
     * get the dataGroupCode - 院区（账套）
     * @return the recReviseTime
     */
    public String getDataGroupCode() {
		return dataGroupCode;
	}

    /**
     * set the dataGroupCode - 院区（账套）
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
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the wareHouseNo - 仓库号
     * @return the wareHouseNo
     */
    public String getWareHouseNo() {
        return this.wareHouseNo;
    }

    /**
     * set the wareHouseNo - 仓库号
     */
    public void setWareHouseNo(String wareHouseNo) {
        this.wareHouseNo = wareHouseNo;
    }

    /**
     * get the wareHouseName - 仓库名称
     * @return the wareHouseName
     */
    public String getWareHouseName() {
        return this.wareHouseName;
    }

    /**
     * set the wareHouseName - 仓库名称
     */
    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    /**
     * get the freezeFlag - 冻结标记
     * @return the freezeFlag
     */
    public String getFreezeFlag() {
        return this.freezeFlag;
    }

    /**
     * set the freezeFlag - 冻结标记
     */
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag;
    }

    /**
     * get the wareHouseType - 仓库类型
     * @return the wareHouseType
     */
    public String getWareHouseType() {
        return this.wareHouseType;
    }

    /**
     * set the wareHouseType - 仓库类型
     */
    public void setWareHouseType(String wareHouseType) {
        this.wareHouseType = wareHouseType;
    }

    /**
     * get the priceType - 计价方式
     * @return the priceType
     */
    public String getPriceType() {
        return this.priceType;
    }

    /**
     * set the priceType - 计价方式
     */
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    /**
     * get the workNo - 仓库管理员（未用）
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 仓库管理员（未用）
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the workName - 仓库管理员（未用）
     * @return the workName
     */
    public String getWorkName() {
        return this.workName;
    }

    /**
     * set the workName - 仓库管理员（未用）
     */
    public void setWorkName(String workName) {
        this.workName = workName;
    }

    /**
     * get the managementType - 管理类型（未用）
     * @return the managementType
     */
    public Integer getManagementType() {
        return this.managementType;
    }

    /**
     * set the managementType - 管理类型（未用）
     */
    public void setManagementType(Integer managementType) {
        this.managementType = managementType;
    }

    /**
     * get the matTypeNum - 物资分类编码（未用）
     * @return the matTypeNum
     */
    public String getMatTypeNum() {
        return this.matTypeNum;
    }

    /**
     * set the matTypeNum - 物资分类编码（未用）
     */
    public void setMatTypeNum(String matTypeNum) {
        this.matTypeNum = matTypeNum;
    }

    /**
     * get the matTypeName - 物资分类名称（未用）
     * @return the matTypeName
     */
    public String getMatTypeName() {
        return this.matTypeName;
    }

    /**
     * set the matTypeName - 物资分类名称（未用）
     */
    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setDataGroupCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataGroupCode")), dataGroupCode));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setWareHouseNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseNo")), wareHouseNo));
        setWareHouseName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseName")), wareHouseName));
        setFreezeFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("freezeFlag")), freezeFlag));
        setWareHouseType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wareHouseType")), wareHouseType));
        setPriceType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("priceType")), priceType));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setWorkName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workName")), workName));
        setManagementType(NumberUtils.toInteger(StringUtils.toString(map.get("managementType")), managementType));
        setMatTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeNum")), matTypeNum));
        setMatTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matTypeName")), matTypeName));
    }

    /**
     * set the value to Map
     */
	public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("wareHouseNo",StringUtils.toString(wareHouseNo, eiMetadata.getMeta("wareHouseNo")));
        map.put("wareHouseName",StringUtils.toString(wareHouseName, eiMetadata.getMeta("wareHouseName")));
        map.put("freezeFlag",StringUtils.toString(freezeFlag, eiMetadata.getMeta("freezeFlag")));
        map.put("wareHouseType",StringUtils.toString(wareHouseType, eiMetadata.getMeta("wareHouseType")));
        map.put("priceType",StringUtils.toString(priceType, eiMetadata.getMeta("priceType")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("workName",StringUtils.toString(workName, eiMetadata.getMeta("workName")));
        map.put("managementType",StringUtils.toString(managementType, eiMetadata.getMeta("managementType")));
        map.put("matTypeNum",StringUtils.toString(matTypeNum, eiMetadata.getMeta("matTypeNum")));
        map.put("matTypeName",StringUtils.toString(matTypeName, eiMetadata.getMeta("matTypeName")));
        return map;
    }
}