package com.baosight.wilp.si.bf.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.util.Date;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.SiUtils;

/**
 * 物资报废主表
 * SiScrap
 * @author fangjian
 */
public class SiScrap extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id;

    /**报废单号*/
    private String scrapNo;

    /**报废日期*/
    private Date scrapDate;

    /**报废原因*/
    private String scrapReason;

    /** 状态编码*/
    private String statusCode;

    /** 状态名称*/
    private String statusName;

    /**创建（申请）人*/
    private String recCreator;

    /**创建（申请）人姓名*/
    private String recCreatorName;

    /**创建时间*/
    private Date recCreateTime ;

    /**创建时间*/
    private String recCreateTimeStr ;

    /**修改人*/
    private String recRevisor;

    /**修改时间*/
    private Date recReviseTime ;

    /**账套*/
    private String dataGroupCode;

    /**归档标记*/
    private String archiveFlag;

    /**上个状态**/
    private String lastStatus;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapNo");
        eiColumn.setDescName("报废单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapDate");
        eiColumn.setDescName("报废日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scrapReason");
        eiColumn.setDescName("报废原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusName");
        eiColumn.setDescName("状态名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建（申请）人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorName");
        eiColumn.setDescName("创建（申请）人姓名");
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
    public SiScrap() {
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
     * get the scrapNo - 报废单号
     * @return the scrapNo
     */
    public String getScrapNo() {
        return this.scrapNo;
    }

    /**
     * set the scrapNo - 报废单号
     */
    public void setScrapNo(String scrapNo) {
        this.scrapNo = scrapNo;
    }

    /**
     * get the scrapDate - 报废日期
     * @return the scrapDate
     */
    public Date getScrapDate() {
        return this.scrapDate;
    }

    /**
     * set the scrapDate - 报废日期
     */
    public void setScrapDate(Date scrapDate) {
        this.scrapDate = scrapDate;
    }

    /**
     * get the scrapReason - 报废原因
     * @return the scrapReason
     */
    public String getScrapReason() {
        return this.scrapReason;
    }

    /**
     * set the scrapReason - 报废原因
     */
    public void setScrapReason(String scrapReason) {
        this.scrapReason = scrapReason;
    }

    /**
     * get the statusCode - 状态编码
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态编码
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the statusName - 状态名称
     * @return the statusName
     */
    public String getStatusName() {
        return this.statusName;
    }

    /**
     * set the statusName - 状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
     * get the recCreatorName - 创建（申请）人姓名
     * @return the recCreatorName
     */
    public String getRecCreatorName() {
        return this.recCreatorName;
    }

    /**
     * set the recCreatorName - 创建（申请）人姓名
     */
    public void setRecCreatorName(String recCreatorName) {
        this.recCreatorName = recCreatorName;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public Date getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public void setRecCreateTime(Date recCreateTime) {
        this.recCreateTime = recCreateTime;
        if(this.recCreateTime != null) {
            setRecCreateTimeStr(DateUtils.toDateTimeStr19(this.recCreateTime));
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
     * set the recCreateTimeStr - 创建时间
     */
    public void setRecCreateTimeStr(String recCreateTimeStr) {
        this.recCreateTimeStr = recCreateTimeStr;
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

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {
        setId(SiUtils.isEmpty(map.get("id"), id));
        setScrapNo(SiUtils.isEmpty(map.get("scrapNo"), scrapNo));
        setScrapDate(DateUtils.toDate(StringUtils.toString(map.get("scrapDate"))));
        setScrapReason(SiUtils.isEmpty(map.get("scrapReason"), scrapReason));
        setStatusCode(SiUtils.isEmpty(map.get("statusCode"), statusCode));
        setStatusName(SiUtils.isEmpty(map.get("statusName"), statusName));
        setRecCreator(SiUtils.isEmpty(map.get("recCreator"), recCreator));
        setRecCreatorName(SiUtils.isEmpty(map.get("recCreatorName"), recCreatorName));
        setRecCreateTime(SiUtils.toDate(StringUtils.toString(map.get("recCreateTime"))));
        setRecRevisor(SiUtils.isEmpty(map.get("recRevisor"), recRevisor));
        setRecReviseTime(SiUtils.toDate(StringUtils.toString(map.get("recReviseTime"))));
        setDataGroupCode(SiUtils.isEmpty(map.get("dataGroupCode"), dataGroupCode));
        setArchiveFlag(SiUtils.isEmpty(map.get("archiveFlag"), archiveFlag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(16);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("scrapNo",StringUtils.toString(scrapNo, eiMetadata.getMeta("scrapNo")));
        map.put("scrapDate",StringUtils.toString(scrapDate, eiMetadata.getMeta("scrapDate")));
        map.put("scrapReason",StringUtils.toString(scrapReason, eiMetadata.getMeta("scrapReason")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("statusName",StringUtils.toString(statusName, eiMetadata.getMeta("statusName")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreatorName",StringUtils.toString(recCreatorName, eiMetadata.getMeta("recCreatorName")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recCreateTimeStr",StringUtils.toString(recCreateTimeStr, eiMetadata.getMeta("recCreateTimeStr")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("dataGroupCode",StringUtils.toString(dataGroupCode, eiMetadata.getMeta("dataGroupCode")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }

    /**
     * 获取报废实体
     * @Title: getInstance
     * @param id id
     * @param statusCode statusCode
     * @param lastStatus lastStatus
     * @return com.baosight.wilp.si.bf.domain.SiScrap
     * @throws
     **/
    public static SiScrap getInstance(String id, String statusCode, String lastStatus) {
        SiScrap siScrap = new SiScrap();
        siScrap.setId(id);
        siScrap.setStatusCode(statusCode);
        siScrap.setStatusName(CommonUtils.getDataCodeItemName("wilp.si.scrapStatus", statusCode));
        siScrap.setLastStatus(lastStatus);
        return siScrap;
    }
}