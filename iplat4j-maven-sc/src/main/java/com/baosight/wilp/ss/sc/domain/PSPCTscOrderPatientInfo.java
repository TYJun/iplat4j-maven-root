package com.baosight.wilp.ss.sc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PSPCTscOrderPatientInfo extends DaoEPBase {
    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String FILE_NUMBER = " ";		/* 患者档案号*/
    private String VISITING_SERIAL_NUMBER = " ";		/* 就诊流水号*/
    private String PATIENT_NAME = " ";                /* 患者姓名 */
    private String CURR_DEP_NAME = " ";		/* 当前科室*/
    private String WARD_BED_NUMBER = " ";    /* 床号 */
    private String TELEPHONE_NUMBER = " ";		/* 联系电话*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("FILE_NUMBER");
        eiColumn.setDescName("订单号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("VISITING_SERIAL_NUMBER");
        eiColumn.setDescName("微信ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("PATIENT_NAME");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("CURR_DEP_NAME");
        eiColumn.setDescName("用户电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("WARD_BED_NUMBER");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("TELEPHONE_NUMBER");
        eiColumn.setDescName("病区名称");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public PSPCTscOrderPatientInfo() {
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
     * get the billNo - 订单号
     * @return the billNo
     */
    public String getFILE_NUMBER() {
        return this.FILE_NUMBER;
    }

    /**
     * set the billNo - 订单号
     */
    public void setFILE_NUMBER(String FILE_NUMBER) {
        this.FILE_NUMBER = FILE_NUMBER;
    }

    /**
     * get the openId - 微信ID
     * @return the openId
     */
    public String getVISITING_SERIAL_NUMBER() {
        return this.VISITING_SERIAL_NUMBER;
    }

    /**
     * set the openId - 微信ID
     */
    public void setVISITING_SERIAL_NUMBER(String VISITING_SERIAL_NUMBER) {
        this.VISITING_SERIAL_NUMBER = VISITING_SERIAL_NUMBER;
    }

    /**
     * get the userName
     * @return the userName
     */
    public String getPATIENT_NAME() {
        return this.PATIENT_NAME;
    }

    /**
     * set the userName
     */
    public void setPATIENT_NAME(String PATIENT_NAME) {
        this.PATIENT_NAME = PATIENT_NAME;
    }

    /**
     * get the userTelNumber - 用户电话
     * @return the userTelNumber
     */
    public String getCURR_DEP_NAME() {
        return this.CURR_DEP_NAME;
    }

    /**
     * set the userTelNumber - 用户电话
     */
    public void setCURR_DEP_NAME(String CURR_DEP_NAME) {
        this.CURR_DEP_NAME = CURR_DEP_NAME;
    }

    /**
     * get the deptNum
     * @return the deptNum
     */
    public String getWARD_BED_NUMBER() {
        return this.WARD_BED_NUMBER;
    }

    /**
     * set the deptNum
     */
    public void setWARD_BED_NUMBER(String WARD_BED_NUMBER) {
        this.WARD_BED_NUMBER = WARD_BED_NUMBER;
    }

    /**
     * get the deptName - 病区名称
     * @return the deptName
     */
    public String getTELEPHONE_NUMBER() {
        return this.TELEPHONE_NUMBER;
    }

    /**
     * set the deptName - 病区名称
     */
    public void setTELEPHONE_NUMBER(String TELEPHONE_NUMBER) {
        this.TELEPHONE_NUMBER = TELEPHONE_NUMBER;
    }



    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setFILE_NUMBER(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("FILE_NUMBER")), FILE_NUMBER));
        setVISITING_SERIAL_NUMBER(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("VISITING_SERIAL_NUMBER")), VISITING_SERIAL_NUMBER));
        setPATIENT_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("PATIENT_NAME")), PATIENT_NAME));
        setCURR_DEP_NAME(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("CURR_DEP_NAME")), CURR_DEP_NAME));
        setWARD_BED_NUMBER(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("WARD_BED_NUMBER")), WARD_BED_NUMBER));
        setTELEPHONE_NUMBER(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("TELEPHONE_NUMBER")), TELEPHONE_NUMBER));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("FILE_NUMBER",StringUtils.toString(FILE_NUMBER, eiMetadata.getMeta("FILE_NUMBER")));
        map.put("VISITING_SERIAL_NUMBER",StringUtils.toString(VISITING_SERIAL_NUMBER, eiMetadata.getMeta("VISITING_SERIAL_NUMBER")));
        map.put("PATIENT_NAME",StringUtils.toString(PATIENT_NAME, eiMetadata.getMeta("PATIENT_NAME")));
        map.put("CURR_DEP_NAME",StringUtils.toString(CURR_DEP_NAME, eiMetadata.getMeta("CURR_DEP_NAME")));
        map.put("WARD_BED_NUMBER",StringUtils.toString(WARD_BED_NUMBER, eiMetadata.getMeta("WARD_BED_NUMBER")));
        map.put("TELEPHONE_NUMBER",StringUtils.toString(TELEPHONE_NUMBER, eiMetadata.getMeta("TELEPHONE_NUMBER")));
        return map;
    }
}
