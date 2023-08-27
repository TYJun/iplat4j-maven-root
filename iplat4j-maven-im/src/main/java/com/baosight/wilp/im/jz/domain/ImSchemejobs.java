/**
* Generate time : 2021-05-07 17:20:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.jz.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiSchemejobs
* 
*/
public class ImSchemejobs extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer jobid = new Integer(0);		/* 执行人员记录的ID号*/
    private String schemeid = " ";		/* 基准的ID号,UUID*/
    private String jobdepartid = " ";		/* 执行部门引用自tbmbd01(id)*/
    private String jobmanid = " ";		/* 执行人引用自tbmbd02(id)*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("jobid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("执行人员记录的ID号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schemeid");
        eiColumn.setDescName("基准的ID号,UUID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jobdepartid");
        eiColumn.setDescName("执行部门引用自tbmbd01(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jobmanid");
        eiColumn.setDescName("执行人引用自tbmbd02(id)");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImSchemejobs() {
        initMetaData();
    }

    /**
     * get the jobid - 执行人员记录的ID号
     * @return the jobid
     */
    public Integer getJobid() {
        return this.jobid;
    }

    /**
     * set the jobid - 执行人员记录的ID号
     */
    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    /**
     * get the schemeid - 基准的ID号,UUID
     * @return the schemeid
     */
    public String getSchemeid() {
        return this.schemeid;
    }

    /**
     * set the schemeid - 基准的ID号,UUID
     */
    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    /**
     * get the jobdepartid - 执行部门引用自tbmbd01(id)
     * @return the jobdepartid
     */
    public String getJobdepartid() {
        return this.jobdepartid;
    }

    /**
     * set the jobdepartid - 执行部门引用自tbmbd01(id)
     */
    public void setJobdepartid(String jobdepartid) {
        this.jobdepartid = jobdepartid;
    }

    /**
     * get the jobmanid - 执行人引用自tbmbd02(id)
     * @return the jobmanid
     */
    public String getJobmanid() {
        return this.jobmanid;
    }

    /**
     * set the jobmanid - 执行人引用自tbmbd02(id)
     */
    public void setJobmanid(String jobmanid) {
        this.jobmanid = jobmanid;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setJobid(NumberUtils.toInteger(StringUtils.toString(map.get("jobid")), jobid));
        setSchemeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemeid")), schemeid));
        setJobdepartid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jobdepartid")), jobdepartid));
        setJobmanid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jobmanid")), jobmanid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("jobid",StringUtils.toString(jobid, eiMetadata.getMeta("jobid")));
        map.put("schemeid",StringUtils.toString(schemeid, eiMetadata.getMeta("schemeid")));
        map.put("jobdepartid",StringUtils.toString(jobdepartid, eiMetadata.getMeta("jobdepartid")));
        map.put("jobmanid",StringUtils.toString(jobmanid, eiMetadata.getMeta("jobmanid")));
        return map;
    }
}