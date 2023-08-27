/**
* Generate time : 2023-07-17 16:53:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.vi.common.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* ViVistingInfoDetail
* 
*/
public class ViVistingInfoDetail extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer id = new Integer(0);		/* 主键*/
    private String ntervieweeContent = "";		/* 具体的到访事由，内容说明*/
    private String guestName = "";		/* 访客姓名*/
    private String guestPhone = "";		/* 访客电话*/
    private String guestEmail = "";		/* 访客Email*/
    private String guestOrgName = "";		/* 访客的工作单位*/
    private String vehicleNumber = "";		/* 车牌号*/
    private String vehicleTypeCode = "";		/* 车型*/
    private Integer batNo = new Integer(0);		/* 批次号*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ntervieweeContent");
        eiColumn.setDescName("具体的到访事由，内容说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("guestName");
        eiColumn.setDescName("访客姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("guestPhone");
        eiColumn.setDescName("访客电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("guestEmail");
        eiColumn.setDescName("访客Email");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("guestOrgName");
        eiColumn.setDescName("访客的工作单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vehicleNumber");
        eiColumn.setDescName("车牌号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vehicleTypeCode");
        eiColumn.setDescName("车型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("batNo");
        eiColumn.setDescName("批次号");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ViVistingInfoDetail() {
        initMetaData();
    }

    /**
     * get the id - 主键
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get the ntervieweeContent - 具体的到访事由，内容说明
     * @return the ntervieweeContent
     */
    public String getNtervieweeContent() {
        return this.ntervieweeContent;
    }

    /**
     * set the ntervieweeContent - 具体的到访事由，内容说明
     */
    public void setNtervieweeContent(String ntervieweeContent) {
        this.ntervieweeContent = ntervieweeContent;
    }

    /**
     * get the guestName - 访客姓名
     * @return the guestName
     */
    public String getGuestName() {
        return this.guestName;
    }

    /**
     * set the guestName - 访客姓名
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * get the guestPhone - 访客电话
     * @return the guestPhone
     */
    public String getGuestPhone() {
        return this.guestPhone;
    }

    /**
     * set the guestPhone - 访客电话
     */
    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    /**
     * get the guestEmail - 访客Email
     * @return the guestEmail
     */
    public String getGuestEmail() {
        return this.guestEmail;
    }

    /**
     * set the guestEmail - 访客Email
     */
    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    /**
     * get the guestOrgName - 访客的工作单位
     * @return the guestOrgName
     */
    public String getGuestOrgName() {
        return this.guestOrgName;
    }

    /**
     * set the guestOrgName - 访客的工作单位
     */
    public void setGuestOrgName(String guestOrgName) {
        this.guestOrgName = guestOrgName;
    }

    /**
     * get the vehicleNumber - 车牌号
     * @return the vehicleNumber
     */
    public String getVehicleNumber() {
        return this.vehicleNumber;
    }

    /**
     * set the vehicleNumber - 车牌号
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * get the vehicleTypeCode - 车型
     * @return the vehicleTypeCode
     */
    public String getVehicleTypeCode() {
        return this.vehicleTypeCode;
    }

    /**
     * set the vehicleTypeCode - 车型
     */
    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    /**
     * get the batNo - 批次号
     * @return the batNo
     */
    public Integer getBatNo() {
        return this.batNo;
    }

    /**
     * set the batNo - 批次号
     */
    public void setBatNo(Integer batNo) {
        this.batNo = batNo;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(NumberUtils.toInteger(StringUtils.toString(map.get("id")), id));
        setNtervieweeContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ntervieweeContent")), ntervieweeContent));
        setGuestName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("guestName")), guestName));
        setGuestPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("guestPhone")), guestPhone));
        setGuestEmail(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("guestEmail")), guestEmail));
        setGuestOrgName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("guestOrgName")), guestOrgName));
        setVehicleNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("vehicleNumber")), vehicleNumber));
        setVehicleTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("vehicleTypeCode")), vehicleTypeCode));
        setBatNo(NumberUtils.toInteger(StringUtils.toString(map.get("batNo")), batNo));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("ntervieweeContent",StringUtils.toString(ntervieweeContent, eiMetadata.getMeta("ntervieweeContent")));
        map.put("guestName",StringUtils.toString(guestName, eiMetadata.getMeta("guestName")));
        map.put("guestPhone",StringUtils.toString(guestPhone, eiMetadata.getMeta("guestPhone")));
        map.put("guestEmail",StringUtils.toString(guestEmail, eiMetadata.getMeta("guestEmail")));
        map.put("guestOrgName",StringUtils.toString(guestOrgName, eiMetadata.getMeta("guestOrgName")));
        map.put("vehicleNumber",StringUtils.toString(vehicleNumber, eiMetadata.getMeta("vehicleNumber")));
        map.put("vehicleTypeCode",StringUtils.toString(vehicleTypeCode, eiMetadata.getMeta("vehicleTypeCode")));
        map.put("batNo",StringUtils.toString(batNo, eiMetadata.getMeta("batNo")));
        return map;
    }
}