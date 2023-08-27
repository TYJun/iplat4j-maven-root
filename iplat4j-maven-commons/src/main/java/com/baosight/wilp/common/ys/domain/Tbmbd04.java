/**
* Generate time : 2021-01-22 20:18:09
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.ys.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* Tbmbd04
* 
*/
public class Tbmbd04 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String itemNum = " ";		/* 项目编码*/
    private String itemName = " ";		/* 项目名称*/
    private String itemTypeNum = " ";		/* 项目分类编码*/
    private Double whour = new Double(0);		/* 工时*/
    private Double price = new Double(0);		/* 单价*/
    private String suprNum = " ";		/* 供应商编码*/
    private String unitNum = " ";		/* 单位编码*/
    private String patrolFlag = " ";		/* 巡检任务*/
    private String serviceType = " ";		/* 服务类别*/
    private String mainteFlag = " ";		/* 维修事件*/
    private String parentId = " ";		/* 上级ID*/
    private String archiveFlag = " ";		
    private String recCreateTime = " ";		
    private String recCreator = " ";		
    private String recReviseTime = " ";		
    private String typeFlag = " ";		
    private String recRevisor = " ";		
    private String projectClass = " ";		/* 维修设备*/
    private String independentFlag = " ";		/* 独立任务标志*/
    private String jpItemName = " ";		/* 维修事项名简拼*/
    private String solution = " ";		/* 处理方案*/
    private String wgroupNum = " ";		/* 服务科室*/
    private String stopFlag = " ";		
    private String worktype = " ";		/* 员工工种*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemNum");
        eiColumn.setDescName("项目编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemName");
        eiColumn.setDescName("项目名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemTypeNum");
        eiColumn.setDescName("项目分类编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("whour");
        eiColumn.setDescName("工时");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("price");
        eiColumn.setDescName("单价");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("suprNum");
        eiColumn.setDescName("供应商编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unitNum");
        eiColumn.setDescName("单位编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("patrolFlag");
        eiColumn.setDescName("巡检任务");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("serviceType");
        eiColumn.setDescName("服务类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mainteFlag");
        eiColumn.setDescName("维修事件");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setDescName("上级ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectClass");
        eiColumn.setDescName("维修设备");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("independentFlag");
        eiColumn.setDescName("独立任务标志");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jpItemName");
        eiColumn.setDescName("维修事项名简拼");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solution");
        eiColumn.setDescName("处理方案");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wgroupNum");
        eiColumn.setDescName("服务科室");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("stopFlag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("worktype");
        eiColumn.setDescName("员工工种");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tbmbd04() {
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
     * get the itemNum - 项目编码
     * @return the itemNum
     */
    public String getItemNum() {
        return this.itemNum;
    }

    /**
     * set the itemNum - 项目编码
     */
    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * get the itemName - 项目名称
     * @return the itemName
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * set the itemName - 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * get the itemTypeNum - 项目分类编码
     * @return the itemTypeNum
     */
    public String getItemTypeNum() {
        return this.itemTypeNum;
    }

    /**
     * set the itemTypeNum - 项目分类编码
     */
    public void setItemTypeNum(String itemTypeNum) {
        this.itemTypeNum = itemTypeNum;
    }

    /**
     * get the whour - 工时
     * @return the whour
     */
    public Double getWhour() {
        return this.whour;
    }

    /**
     * set the whour - 工时
     */
    public void setWhour(Double whour) {
        this.whour = whour;
    }

    /**
     * get the price - 单价
     * @return the price
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * set the price - 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * get the suprNum - 供应商编码
     * @return the suprNum
     */
    public String getSuprNum() {
        return this.suprNum;
    }

    /**
     * set the suprNum - 供应商编码
     */
    public void setSuprNum(String suprNum) {
        this.suprNum = suprNum;
    }

    /**
     * get the unitNum - 单位编码
     * @return the unitNum
     */
    public String getUnitNum() {
        return this.unitNum;
    }

    /**
     * set the unitNum - 单位编码
     */
    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    /**
     * get the patrolFlag - 巡检任务
     * @return the patrolFlag
     */
    public String getPatrolFlag() {
        return this.patrolFlag;
    }

    /**
     * set the patrolFlag - 巡检任务
     */
    public void setPatrolFlag(String patrolFlag) {
        this.patrolFlag = patrolFlag;
    }

    /**
     * get the serviceType - 服务类别
     * @return the serviceType
     */
    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * set the serviceType - 服务类别
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * get the mainteFlag - 维修事件
     * @return the mainteFlag
     */
    public String getMainteFlag() {
        return this.mainteFlag;
    }

    /**
     * set the mainteFlag - 维修事件
     */
    public void setMainteFlag(String mainteFlag) {
        this.mainteFlag = mainteFlag;
    }

    /**
     * get the parentId - 上级ID
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId - 上级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the archiveFlag 
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag 
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recReviseTime 
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime 
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the typeFlag 
     * @return the typeFlag
     */
    public String getTypeFlag() {
        return this.typeFlag;
    }

    /**
     * set the typeFlag 
     */
    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    /**
     * get the recRevisor 
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor 
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the projectClass - 维修设备
     * @return the projectClass
     */
    public String getProjectClass() {
        return this.projectClass;
    }

    /**
     * set the projectClass - 维修设备
     */
    public void setProjectClass(String projectClass) {
        this.projectClass = projectClass;
    }

    /**
     * get the independentFlag - 独立任务标志
     * @return the independentFlag
     */
    public String getIndependentFlag() {
        return this.independentFlag;
    }

    /**
     * set the independentFlag - 独立任务标志
     */
    public void setIndependentFlag(String independentFlag) {
        this.independentFlag = independentFlag;
    }

    /**
     * get the jpItemName - 维修事项名简拼
     * @return the jpItemName
     */
    public String getJpItemName() {
        return this.jpItemName;
    }

    /**
     * set the jpItemName - 维修事项名简拼
     */
    public void setJpItemName(String jpItemName) {
        this.jpItemName = jpItemName;
    }

    /**
     * get the solution - 处理方案
     * @return the solution
     */
    public String getSolution() {
        return this.solution;
    }

    /**
     * set the solution - 处理方案
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * get the wgroupNum - 服务科室
     * @return the wgroupNum
     */
    public String getWgroupNum() {
        return this.wgroupNum;
    }

    /**
     * set the wgroupNum - 服务科室
     */
    public void setWgroupNum(String wgroupNum) {
        this.wgroupNum = wgroupNum;
    }

    /**
     * get the stopFlag 
     * @return the stopFlag
     */
    public String getStopFlag() {
        return this.stopFlag;
    }

    /**
     * set the stopFlag 
     */
    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    /**
     * get the worktype - 员工工种
     * @return the worktype
     */
    public String getWorktype() {
        return this.worktype;
    }

    /**
     * set the worktype - 员工工种
     */
    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setItemNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemNum")), itemNum));
        setItemName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemName")), itemName));
        setItemTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemTypeNum")), itemTypeNum));
        setWhour(NumberUtils.toDouble(StringUtils.toString(map.get("whour")), whour));
        setPrice(NumberUtils.toDouble(StringUtils.toString(map.get("price")), price));
        setSuprNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("suprNum")), suprNum));
        setUnitNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unitNum")), unitNum));
        setPatrolFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("patrolFlag")), patrolFlag));
        setServiceType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("serviceType")), serviceType));
        setMainteFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mainteFlag")), mainteFlag));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setTypeFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeFlag")), typeFlag));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setProjectClass(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("projectClass")), projectClass));
        setIndependentFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("independentFlag")), independentFlag));
        setJpItemName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jpItemName")), jpItemName));
        setSolution(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solution")), solution));
        setWgroupNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("wgroupNum")), wgroupNum));
        setStopFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("stopFlag")), stopFlag));
        setWorktype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("worktype")), worktype));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("itemNum",StringUtils.toString(itemNum, eiMetadata.getMeta("itemNum")));
        map.put("itemName",StringUtils.toString(itemName, eiMetadata.getMeta("itemName")));
        map.put("itemTypeNum",StringUtils.toString(itemTypeNum, eiMetadata.getMeta("itemTypeNum")));
        map.put("whour",StringUtils.toString(whour, eiMetadata.getMeta("whour")));
        map.put("price",StringUtils.toString(price, eiMetadata.getMeta("price")));
        map.put("suprNum",StringUtils.toString(suprNum, eiMetadata.getMeta("suprNum")));
        map.put("unitNum",StringUtils.toString(unitNum, eiMetadata.getMeta("unitNum")));
        map.put("patrolFlag",StringUtils.toString(patrolFlag, eiMetadata.getMeta("patrolFlag")));
        map.put("serviceType",StringUtils.toString(serviceType, eiMetadata.getMeta("serviceType")));
        map.put("mainteFlag",StringUtils.toString(mainteFlag, eiMetadata.getMeta("mainteFlag")));
        map.put("parentId",StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("typeFlag",StringUtils.toString(typeFlag, eiMetadata.getMeta("typeFlag")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("projectClass",StringUtils.toString(projectClass, eiMetadata.getMeta("projectClass")));
        map.put("independentFlag",StringUtils.toString(independentFlag, eiMetadata.getMeta("independentFlag")));
        map.put("jpItemName",StringUtils.toString(jpItemName, eiMetadata.getMeta("jpItemName")));
        map.put("solution",StringUtils.toString(solution, eiMetadata.getMeta("solution")));
        map.put("wgroupNum",StringUtils.toString(wgroupNum, eiMetadata.getMeta("wgroupNum")));
        map.put("stopFlag",StringUtils.toString(stopFlag, eiMetadata.getMeta("stopFlag")));
        map.put("worktype",StringUtils.toString(worktype, eiMetadata.getMeta("worktype")));
        return map;
    }
}