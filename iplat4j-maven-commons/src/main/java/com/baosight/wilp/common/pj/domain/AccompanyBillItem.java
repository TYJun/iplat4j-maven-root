/**
* Generate time : 2021-03-23 14:36:26
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.common.pj.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* AccompanyBillItem
* 
*/
public class AccompanyBillItem extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String accompanyId = " ";		/* 陪检主键*/
    private String acceptNo = " ";		/* 受理人*/
    private String acceptTime = " ";		/* 受理时间*/
    private String receiptNo = " ";		/* 接单人*/
    private String receiptTime = " ";		/* 接单时间*/
    private String accompanyNo = " ";		/* 陪检人*/
    private String accompanyTime = " ";		/* 陪检时间*/
    private String finishNo = " ";		/* 完工人*/
    private String finishTime = " ";		/* 完工时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyId");
        eiColumn.setDescName("陪检主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("acceptNo");
        eiColumn.setDescName("受理人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("acceptTime");
        eiColumn.setDescName("受理时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("receiptNo");
        eiColumn.setDescName("接单人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("receiptTime");
        eiColumn.setDescName("接单时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyNo");
        eiColumn.setDescName("陪检人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accompanyTime");
        eiColumn.setDescName("陪检时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishNo");
        eiColumn.setDescName("完工人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishTime");
        eiColumn.setDescName("完工时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public AccompanyBillItem() {
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
     * get the accompanyId - 陪检主键
     * @return the accompanyId
     */
    public String getAccompanyId() {
        return this.accompanyId;
    }

    /**
     * set the accompanyId - 陪检主键
     */
    public void setAccompanyId(String accompanyId) {
        this.accompanyId = accompanyId;
    }

    /**
     * get the acceptNo - 受理人
     * @return the acceptNo
     */
    public String getAcceptNo() {
        return this.acceptNo;
    }

    /**
     * set the acceptNo - 受理人
     */
    public void setAcceptNo(String acceptNo) {
        this.acceptNo = acceptNo;
    }

    /**
     * get the acceptTime - 受理时间
     * @return the acceptTime
     */
    public String getAcceptTime() {
        return this.acceptTime;
    }

    /**
     * set the acceptTime - 受理时间
     */
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * get the receiptNo - 接单人
     * @return the receiptNo
     */
    public String getReceiptNo() {
        return this.receiptNo;
    }

    /**
     * set the receiptNo - 接单人
     */
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * get the receiptTime - 接单时间
     * @return the receiptTime
     */
    public String getReceiptTime() {
        return this.receiptTime;
    }

    /**
     * set the receiptTime - 接单时间
     */
    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
    }

    /**
     * get the accompanyNo - 陪检人
     * @return the accompanyNo
     */
    public String getAccompanyNo() {
        return this.accompanyNo;
    }

    /**
     * set the accompanyNo - 陪检人
     */
    public void setAccompanyNo(String accompanyNo) {
        this.accompanyNo = accompanyNo;
    }

    /**
     * get the accompanyTime - 陪检时间
     * @return the accompanyTime
     */
    public String getAccompanyTime() {
        return this.accompanyTime;
    }

    /**
     * set the accompanyTime - 陪检时间
     */
    public void setAccompanyTime(String accompanyTime) {
        this.accompanyTime = accompanyTime;
    }

    /**
     * get the finishNo - 完工人
     * @return the finishNo
     */
    public String getFinishNo() {
        return this.finishNo;
    }

    /**
     * set the finishNo - 完工人
     */
    public void setFinishNo(String finishNo) {
        this.finishNo = finishNo;
    }

    /**
     * get the finishTime - 完工时间
     * @return the finishTime
     */
    public String getFinishTime() {
        return this.finishTime;
    }

    /**
     * set the finishTime - 完工时间
     */
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setAccompanyId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyId")), accompanyId));
        setAcceptNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("acceptNo")), acceptNo));
        setAcceptTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("acceptTime")), acceptTime));
        setReceiptNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("receiptNo")), receiptNo));
        setReceiptTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("receiptTime")), receiptTime));
        setAccompanyNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyNo")), accompanyNo));
        setAccompanyTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accompanyTime")), accompanyTime));
        setFinishNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishNo")), finishNo));
        setFinishTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishTime")), finishTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("accompanyId",StringUtils.toString(accompanyId, eiMetadata.getMeta("accompanyId")));
        map.put("acceptNo",StringUtils.toString(acceptNo, eiMetadata.getMeta("acceptNo")));
        map.put("acceptTime",StringUtils.toString(acceptTime, eiMetadata.getMeta("acceptTime")));
        map.put("receiptNo",StringUtils.toString(receiptNo, eiMetadata.getMeta("receiptNo")));
        map.put("receiptTime",StringUtils.toString(receiptTime, eiMetadata.getMeta("receiptTime")));
        map.put("accompanyNo",StringUtils.toString(accompanyNo, eiMetadata.getMeta("accompanyNo")));
        map.put("accompanyTime",StringUtils.toString(accompanyTime, eiMetadata.getMeta("accompanyTime")));
        map.put("finishNo",StringUtils.toString(finishNo, eiMetadata.getMeta("finishNo")));
        map.put("finishTime",StringUtils.toString(finishTime, eiMetadata.getMeta("finishTime")));
        return map;
    }
}