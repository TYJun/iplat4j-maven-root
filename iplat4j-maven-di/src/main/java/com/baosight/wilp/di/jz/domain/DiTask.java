/**
* Generate time : 2021-05-07 23:35:56
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.jz.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiTask
* 
*/
public class DiTask extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String taskid = " ";		/* 任务实绩的ID号*/
    private String taskcode = " ";		/* 作务实绩的代码*/
    private String departid = " ";		/* 责任部门,引用自tbmdb02(id)*/
    private String mangerid = " ";		/* 责任人,引用自tbmdb02(id)*/
    private BigDecimal activetime = new BigDecimal("0");		/* 有效时间*/
    private String jobcontent = " ";		/* 作业说明*/
    private String memo = " ";		/* 备注*/
    private String schemeid = " ";		/* 基准的ID号,UUID,可以为NULL*/
    private String machineid = " ";		/* 设备ID号，UUID*/
    private Timestamp reccreatetime ;		/* 定时任务生成时间*/
    private Timestamp createtime ;		/* 执行时间*/
    private Timestamp lastwritetime ;		/* 最后登记时间*/
    private String lastwriteman = " ";		/* 最后登记人*/
    private Timestamp finishtime ;		/* 完工时间*/
    private String finishman = " ";		/* 完成人*/
    private Timestamp invalidtime ;		/* 作废时间*/
    private String invalidman = " ";		/* 作废人*/
    private Integer status = new Integer(0);		/* 0=未生效，1=生效，-1=失效，2=完成，3=关闭*/
    private String exceptionflag = " ";		/* -1异常，1正常*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("taskid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("任务实绩的ID号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskcode");
        eiColumn.setDescName("作务实绩的代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departid");
        eiColumn.setDescName("责任部门,引用自tbmdb02(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mangerid");
        eiColumn.setDescName("责任人,引用自tbmdb02(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("activetime");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("有效时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jobcontent");
        eiColumn.setDescName("作业说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memo");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schemeid");
        eiColumn.setDescName("基准的ID号,UUID,可以为NULL");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("machineid");
        eiColumn.setDescName("设备ID号，UUID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reccreatetime");
        eiColumn.setDescName("定时任务生成时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName("执行时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastwritetime");
        eiColumn.setDescName("最后登记时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastwriteman");
        eiColumn.setDescName("最后登记人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishtime");
        eiColumn.setDescName("完工时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishman");
        eiColumn.setDescName("完成人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invalidtime");
        eiColumn.setDescName("作废时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("invalidman");
        eiColumn.setDescName("作废人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("0=未生效，1=生效，-1=失效，2=完成，3=关闭");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("exceptionflag");
        eiColumn.setDescName("-1异常，1正常");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DiTask() {
        initMetaData();
    }

    /**
     * get the taskid - 任务实绩的ID号
     * @return the taskid
     */
    public String getTaskid() {
        return this.taskid;
    }

    /**
     * set the taskid - 任务实绩的ID号
     */
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    /**
     * get the taskcode - 作务实绩的代码
     * @return the taskcode
     */
    public String getTaskcode() {
        return this.taskcode;
    }

    /**
     * set the taskcode - 作务实绩的代码
     */
    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode;
    }

    /**
     * get the departid - 责任部门,引用自tbmdb02(id)
     * @return the departid
     */
    public String getDepartid() {
        return this.departid;
    }

    /**
     * set the departid - 责任部门,引用自tbmdb02(id)
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }

    /**
     * get the mangerid - 责任人,引用自tbmdb02(id)
     * @return the mangerid
     */
    public String getMangerid() {
        return this.mangerid;
    }

    /**
     * set the mangerid - 责任人,引用自tbmdb02(id)
     */
    public void setMangerid(String mangerid) {
        this.mangerid = mangerid;
    }

    /**
     * get the activetime - 有效时间
     * @return the activetime
     */
    public BigDecimal getActivetime() {
        return this.activetime;
    }

    /**
     * set the activetime - 有效时间
     */
    public void setActivetime(BigDecimal activetime) {
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
     * get the schemeid - 基准的ID号,UUID,可以为NULL
     * @return the schemeid
     */
    public String getSchemeid() {
        return this.schemeid;
    }

    /**
     * set the schemeid - 基准的ID号,UUID,可以为NULL
     */
    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    /**
     * get the machineid - 设备ID号，UUID
     * @return the machineid
     */
    public String getMachineid() {
        return this.machineid;
    }

    /**
     * set the machineid - 设备ID号，UUID
     */
    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    /**
     * get the reccreatetime - 定时任务生成时间
     * @return the reccreatetime
     */
    public Timestamp getReccreatetime() {
        return this.reccreatetime;
    }

    /**
     * set the reccreatetime - 定时任务生成时间
     */
    public void setReccreatetime(Timestamp reccreatetime) {
        this.reccreatetime = reccreatetime;
    }

    /**
     * get the createtime - 执行时间
     * @return the createtime
     */
    public Timestamp getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime - 执行时间
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * get the lastwritetime - 最后登记时间
     * @return the lastwritetime
     */
    public Timestamp getLastwritetime() {
        return this.lastwritetime;
    }

    /**
     * set the lastwritetime - 最后登记时间
     */
    public void setLastwritetime(Timestamp lastwritetime) {
        this.lastwritetime = lastwritetime;
    }

    /**
     * get the lastwriteman - 最后登记人
     * @return the lastwriteman
     */
    public String getLastwriteman() {
        return this.lastwriteman;
    }

    /**
     * set the lastwriteman - 最后登记人
     */
    public void setLastwriteman(String lastwriteman) {
        this.lastwriteman = lastwriteman;
    }

    /**
     * get the finishtime - 完工时间
     * @return the finishtime
     */
    public Timestamp getFinishtime() {
        return this.finishtime;
    }

    /**
     * set the finishtime - 完工时间
     */
    public void setFinishtime(Timestamp finishtime) {
        this.finishtime = finishtime;
    }

    /**
     * get the finishman - 完成人
     * @return the finishman
     */
    public String getFinishman() {
        return this.finishman;
    }

    /**
     * set the finishman - 完成人
     */
    public void setFinishman(String finishman) {
        this.finishman = finishman;
    }

    /**
     * get the invalidtime - 作废时间
     * @return the invalidtime
     */
    public Timestamp getInvalidtime() {
        return this.invalidtime;
    }

    /**
     * set the invalidtime - 作废时间
     */
    public void setInvalidtime(Timestamp invalidtime) {
        this.invalidtime = invalidtime;
    }

    /**
     * get the invalidman - 作废人
     * @return the invalidman
     */
    public String getInvalidman() {
        return this.invalidman;
    }

    /**
     * set the invalidman - 作废人
     */
    public void setInvalidman(String invalidman) {
        this.invalidman = invalidman;
    }

    /**
     * get the status - 0=未生效，1=生效，-1=失效，2=完成，3=关闭
     * @return the status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * set the status - 0=未生效，1=生效，-1=失效，2=完成，3=关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get the exceptionflag - -1异常，1正常
     * @return the exceptionflag
     */
    public String getExceptionflag() {
        return this.exceptionflag;
    }

    /**
     * set the exceptionflag - -1异常，1正常
     */
    public void setExceptionflag(String exceptionflag) {
        this.exceptionflag = exceptionflag;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setTaskid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskid")), taskid));
        setTaskcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskcode")), taskcode));
        setDepartid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departid")), departid));
        setMangerid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mangerid")), mangerid));
        setActivetime(NumberUtils.toBigDecimal(StringUtils.toString(map.get("activetime")), activetime));
        setJobcontent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jobcontent")), jobcontent));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setSchemeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemeid")), schemeid));
        setMachineid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineid")), machineid));
        setReccreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("reccreatetime"))));
        setCreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("createtime"))));
        setLastwritetime(DateUtils.toTimestamp(StringUtils.toString(map.get("lastwritetime"))));
        setLastwriteman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastwriteman")), lastwriteman));
        setFinishtime(DateUtils.toTimestamp(StringUtils.toString(map.get("finishtime"))));
        setFinishman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishman")), finishman));
        setInvalidtime(DateUtils.toTimestamp(StringUtils.toString(map.get("invalidtime"))));
        setInvalidman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invalidman")), invalidman));
        setStatus(NumberUtils.toInteger(StringUtils.toString(map.get("status")), status));
        setExceptionflag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exceptionflag")), exceptionflag));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("taskid",StringUtils.toString(taskid, eiMetadata.getMeta("taskid")));
        map.put("taskcode",StringUtils.toString(taskcode, eiMetadata.getMeta("taskcode")));
        map.put("departid",StringUtils.toString(departid, eiMetadata.getMeta("departid")));
        map.put("mangerid",StringUtils.toString(mangerid, eiMetadata.getMeta("mangerid")));
        map.put("activetime",StringUtils.toString(activetime, eiMetadata.getMeta("activetime")));
        map.put("jobcontent",StringUtils.toString(jobcontent, eiMetadata.getMeta("jobcontent")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("schemeid",StringUtils.toString(schemeid, eiMetadata.getMeta("schemeid")));
        map.put("machineid",StringUtils.toString(machineid, eiMetadata.getMeta("machineid")));
        map.put("reccreatetime",StringUtils.toString(reccreatetime, eiMetadata.getMeta("reccreatetime")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("lastwritetime",StringUtils.toString(lastwritetime, eiMetadata.getMeta("lastwritetime")));
        map.put("lastwriteman",StringUtils.toString(lastwriteman, eiMetadata.getMeta("lastwriteman")));
        map.put("finishtime",StringUtils.toString(finishtime, eiMetadata.getMeta("finishtime")));
        map.put("finishman",StringUtils.toString(finishman, eiMetadata.getMeta("finishman")));
        map.put("invalidtime",StringUtils.toString(invalidtime, eiMetadata.getMeta("invalidtime")));
        map.put("invalidman",StringUtils.toString(invalidman, eiMetadata.getMeta("invalidman")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("exceptionflag",StringUtils.toString(exceptionflag, eiMetadata.getMeta("exceptionflag")));
        return map;
    }
}