/**
* Generate time : 2021-05-05 10:58:05
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.im.jz.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiScheme
* 
*/
public class ImScheme extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String schemeid = " ";		/* 基准的ID号,UUID*/
    private String schemecode = " ";		/* 基准代码*/
    private String schemename = " ";		/* 基准名称*/
    private String departid = " ";		/* 责任部门,引用自tbmbd01(id)*/
    private String managerid = " ";		/* 责任人,引用自tbmbd02(id)*/
    private String managerphone = " ";		/* 责任人联系电话*/
    private String activetime = " ";		/* 有效时间*/
    private String jobcontent = " ";		/* 作业说明*/
    private String memo = " ";		/* 备注*/
    private String machineid = " ";		/* 设备ID号*/
    private Timestamp createtime ;		/* 基准创建时间*/
    private String createman = " ";		/* 登记人*/
    private Timestamp modifytime ;		/* 修改(确认)时间*/
    private String modifyman = " ";		/* 修改(确认)人*/
    private Integer status = new Integer(0);		/* 状态,0=未生效，1=生效，-1=失效*/
    private String smssend = " ";		/* 短信提醒*/
    private String timeoutremindertime = " ";		/* 超时提前提醒时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("schemeid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("基准的ID号,UUID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schemecode");
        eiColumn.setDescName("基准代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schemename");
        eiColumn.setDescName("基准名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departid");
        eiColumn.setDescName("责任部门,引用自tbmbd01(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerid");
        eiColumn.setDescName("责任人,引用自tbmbd02(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerphone");
        eiColumn.setDescName("责任人联系电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("activetime");
        eiColumn.setDescName("有效时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jobcontent");
        eiColumn.setDescName("作业说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineid");
        eiColumn.setDescName("设备ID号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName("基准创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createman");
        eiColumn.setDescName("登记人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifytime");
        eiColumn.setDescName("修改(确认)时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyman");
        eiColumn.setDescName("修改(确认)人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态,0=未生效，1=生效，-1=失效");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smssend");
        eiColumn.setDescName("短信提醒");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("timeoutremindertime");
        eiColumn.setDescName("超时提前提醒时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ImScheme() {
        initMetaData();
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
     * get the schemecode - 基准代码
     * @return the schemecode
     */
    public String getSchemecode() {
        return this.schemecode;
    }

    /**
     * set the schemecode - 基准代码
     */
    public void setSchemecode(String schemecode) {
        this.schemecode = schemecode;
    }

    /**
     * get the schemename - 基准名称
     * @return the schemename
     */
    public String getSchemename() {
        return this.schemename;
    }

    /**
     * set the schemename - 基准名称
     */
    public void setSchemename(String schemename) {
        this.schemename = schemename;
    }

    /**
     * get the departid - 责任部门,引用自tbmbd01(id)
     * @return the departid
     */
    public String getDepartid() {
        return this.departid;
    }

    /**
     * set the departid - 责任部门,引用自tbmbd01(id)
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }

    /**
     * get the managerid - 责任人,引用自tbmbd02(id)
     * @return the managerid
     */
    public String getManagerid() {
        return this.managerid;
    }

    /**
     * set the managerid - 责任人,引用自tbmbd02(id)
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    /**
     * get the managerphone - 责任人联系电话
     * @return the managerphone
     */
    public String getManagerphone() {
        return this.managerphone;
    }

    /**
     * set the managerphone - 责任人联系电话
     */
    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }

    /**
     * get the activetime - 有效时间
     * @return the activetime
     */
    public String getActivetime() {
        return this.activetime;
    }

    /**
     * set the activetime - 有效时间
     */
    public void setActivetime(String activetime) {
        this.activetime = activetime;
    }

    /**
     * get the jobcontent - 作业说明
     * @return the jobcontent
     */
    public String getJobcontent() {
        return this.jobcontent;
    }

    /**
     * set the jobcontent - 作业说明
     */
    public void setJobcontent(String jobcontent) {
        this.jobcontent = jobcontent;
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
     * get the machineid - 设备ID号
     * @return the machineid
     */
    public String getMachineid() {
        return this.machineid;
    }

    /**
     * set the machineid - 设备ID号
     */
    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    /**
     * get the createtime - 基准创建时间
     * @return the createtime
     */
    public Timestamp getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime - 基准创建时间
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * get the createman - 登记人
     * @return the createman
     */
    public String getCreateman() {
        return this.createman;
    }

    /**
     * set the createman - 登记人
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * get the modifytime - 修改(确认)时间
     * @return the modifytime
     */
    public Timestamp getModifytime() {
        return this.modifytime;
    }

    /**
     * set the modifytime - 修改(确认)时间
     */
    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * get the modifyman - 修改(确认)人
     * @return the modifyman
     */
    public String getModifyman() {
        return this.modifyman;
    }

    /**
     * set the modifyman - 修改(确认)人
     */
    public void setModifyman(String modifyman) {
        this.modifyman = modifyman;
    }

    /**
     * get the status - 状态,0=未生效，1=生效，-1=失效
     * @return the status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态,0=未生效，1=生效，-1=失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get the smssend - 短信提醒
     * @return the smssend
     */
    public String getSmssend() {
        return this.smssend;
    }

    /**
     * set the smssend - 短信提醒
     */
    public void setSmssend(String smssend) {
        this.smssend = smssend;
    }

    /**
     * get the timeoutremindertime - 超时提前提醒时间
     * @return the timeoutremindertime
     */
    public String getTimeoutremindertime() {
        return this.timeoutremindertime;
    }

    /**
     * set the timeoutremindertime - 超时提前提醒时间
     */
    public void setTimeoutremindertime(String timeoutremindertime) {
        this.timeoutremindertime = timeoutremindertime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setSchemeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemeid")), schemeid));
        setSchemecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemecode")), schemecode));
        setSchemename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemename")), schemename));
        setDepartid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departid")), departid));
        setManagerid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerid")), managerid));
        setManagerphone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerphone")), managerphone));
        setActivetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("activetime")), activetime));
        setJobcontent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jobcontent")), jobcontent));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setMachineid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineid")), machineid));
        setCreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("createtime"))));
        setCreateman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createman")), createman));
        setModifytime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifytime"))));
        setModifyman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyman")), modifyman));
        setStatus(NumberUtils.toInteger(StringUtils.toString(map.get("status")), status));
        setSmssend(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smssend")), smssend));
        setTimeoutremindertime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("timeoutremindertime")), timeoutremindertime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("schemeid",StringUtils.toString(schemeid, eiMetadata.getMeta("schemeid")));
        map.put("schemecode",StringUtils.toString(schemecode, eiMetadata.getMeta("schemecode")));
        map.put("schemename",StringUtils.toString(schemename, eiMetadata.getMeta("schemename")));
        map.put("departid",StringUtils.toString(departid, eiMetadata.getMeta("departid")));
        map.put("managerid",StringUtils.toString(managerid, eiMetadata.getMeta("managerid")));
        map.put("managerphone",StringUtils.toString(managerphone, eiMetadata.getMeta("managerphone")));
        map.put("activetime",StringUtils.toString(activetime, eiMetadata.getMeta("activetime")));
        map.put("jobcontent",StringUtils.toString(jobcontent, eiMetadata.getMeta("jobcontent")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("machineid",StringUtils.toString(machineid, eiMetadata.getMeta("machineid")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("createman",StringUtils.toString(createman, eiMetadata.getMeta("createman")));
        map.put("modifytime",StringUtils.toString(modifytime, eiMetadata.getMeta("modifytime")));
        map.put("modifyman",StringUtils.toString(modifyman, eiMetadata.getMeta("modifyman")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("smssend",StringUtils.toString(smssend, eiMetadata.getMeta("smssend")));
        map.put("timeoutremindertime",StringUtils.toString(timeoutremindertime, eiMetadata.getMeta("timeoutremindertime")));
        return map;
    }
}