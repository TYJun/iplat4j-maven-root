/**
* Generate time : 2022-03-21 17:22:39
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cp.domain;

import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* CpBillReturn
* 
*/
public class CpBillReturn extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String creator = " ";		/* 创建人工号*/
    private String createName = " ";		/* 创建人名字*/
    private Timestamp createTime ;		/* 创建时间*/
    private String returnWorkNo = " ";		/* 回访人工号*/
    private String returnWorkName = " ";		/* 回访人名字*/
    private String returnDate = " ";		/* 回访日期*/
    private String returnDesc = " ";		/* 回访内容描述*/
    private String billNo = " ";		/* 回访的投诉单号*/
    private String statusCode = " ";		/* 状态*/
    private String returnStatusCode = " ";		/* 回访状态*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createName");
        eiColumn.setDescName("创建人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("returnWorkNo");
        eiColumn.setDescName("回访人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("returnWorkName");
        eiColumn.setDescName("回访人名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("returnDate");
        eiColumn.setDescName("回访日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("returnDesc");
        eiColumn.setDescName("回访内容描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("billNo");
        eiColumn.setDescName("回访的投诉单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("returnStatusCode");
        eiColumn.setDescName("回访状态");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public CpBillReturn() {
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
     * get the creator - 创建人工号
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 创建人工号
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createName - 创建人名字
     * @return the createName
     */
    public String getCreateName() {
        return this.createName;
    }

    /**
     * set the createName - 创建人名字
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * get the createTime - 创建时间
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * set the createTime - 创建时间
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * get the returnWorkNo - 回访人工号
     * @return the returnWorkNo
     */
    public String getReturnWorkNo() {
        return this.returnWorkNo;
    }

    /**
     * set the returnWorkNo - 回访人工号
     */
    public void setReturnWorkNo(String returnWorkNo) {
        this.returnWorkNo = returnWorkNo;
    }

    /**
     * get the returnWorkName - 回访人名字
     * @return the returnWorkName
     */
    public String getReturnWorkName() {
        return this.returnWorkName;
    }

    /**
     * set the returnWorkName - 回访人名字
     */
    public void setReturnWorkName(String returnWorkName) {
        this.returnWorkName = returnWorkName;
    }

    /**
     * get the returnDate - 回访日期
     * @return the returnDate
     */
    public String getReturnDate() {
        return this.returnDate;
    }

    /**
     * set the returnDate - 回访日期
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * get the returnDesc - 回访内容描述
     * @return the returnDesc
     */
    public String getReturnDesc() {
        return this.returnDesc;
    }

    /**
     * set the returnDesc - 回访内容描述
     */
    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    /**
     * get the billNo - 回访的投诉单号
     * @return the billNo
     */
    public String getBillNo() {
        return this.billNo;
    }

    /**
     * set the billNo - 回访的投诉单号
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * get the statusCode - 状态
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode - 状态
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the returnStatusCode - 回访状态
     * @return the returnStatusCode
     */
    public String getReturnStatusCode() {
        return this.returnStatusCode;
    }

    /**
     * set the returnStatusCode - 回访状态
     */
    public void setReturnStatusCode(String returnStatusCode) {
        this.returnStatusCode = returnStatusCode;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createName")), createName));
        setCreateTime(DateUtils.toTimestamp(StringUtils.toString(map.get("createTime"))));
        setReturnWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("returnWorkNo")), returnWorkNo));
        setReturnWorkName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("returnWorkName")), returnWorkName));
        setReturnDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("returnDate")), returnDate));
        setReturnDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("returnDesc")), returnDesc));
        setBillNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("billNo")), billNo));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setReturnStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("returnStatusCode")), returnStatusCode));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("creator",StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createName",StringUtils.toString(createName, eiMetadata.getMeta("createName")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("returnWorkNo",StringUtils.toString(returnWorkNo, eiMetadata.getMeta("returnWorkNo")));
        map.put("returnWorkName",StringUtils.toString(returnWorkName, eiMetadata.getMeta("returnWorkName")));
        map.put("returnDate",StringUtils.toString(returnDate, eiMetadata.getMeta("returnDate")));
        map.put("returnDesc",StringUtils.toString(returnDesc, eiMetadata.getMeta("returnDesc")));
        map.put("billNo",StringUtils.toString(billNo, eiMetadata.getMeta("billNo")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("returnStatusCode",StringUtils.toString(returnStatusCode, eiMetadata.getMeta("returnStatusCode")));
        return map;
    }
}