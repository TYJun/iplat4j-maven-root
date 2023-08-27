/**
* Generate time : 2021-06-02 15:39:40
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.yc.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import java.math.BigDecimal;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiTask
* 
*/
public class DIYC01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    
    private String itemID = " ";		/* 任务实绩的ID号*/
    private String taskid = " ";		/* 任务实绩的ID号*/
    private String taskCode = " ";		/* 作务实绩的代码*/
    private String departid = " ";		/* 责任部门,引用自tbmdb02(id)*/
    private String deptName = " ";		/* 责任部门,引用自tbmdb02(id)*/
    private String managerid = " ";		/* 责任人,引用自tbmdb02(id)*/
    private BigDecimal activetime = new BigDecimal("0");		/* 有效时间*/
    private String jobContent = " ";		/* 作业说明*/
    private String memo = " ";		/* 备注*/
    private String schemeid = " ";		/* 基准的ID号,UUID,可以为NULL*/
    private String machineid = " ";		/* 设备ID号，UUID*/
    private Timestamp reccreatetime ;		/* 定时任务生成时间*/
    private Timestamp createtime ;		/* 执行时间*/
    private Timestamp lastwritetime ;		/* 最后登记时间*/
    private String lastwriteman = " ";		/* 最后登记人*/
    private Timestamp finishTime ;		/* 完工时间*/
    private String finishMan = " ";		/* 完成人*/
    private Timestamp invalidtime ;		/* 作废时间*/
    private String invalidman = " ";		/* 作废人*/
    private Integer status = new Integer(0);		/* 0=未生效，1=生效，-1=失效，2=完成，3=关闭*/
    private String exceptionflag = " ";		/* -1异常，1正常*/
    private String exceptionStatus = " ";		
    private String exceptionResult = " ";		
    private String jitemName = " ";		
    private String errorContent = " ";		
    private String writeValue=" ";  /*巡检结果记录*/
    private String taskNameType = " ";		/* 完整路径*/

    private String processType = " ";



    private String solutionType = " ";

    private String schemeID = " ";
    

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("taskid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("任务实绩的ID号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskCode");
        eiColumn.setDescName("作务实绩的代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departid");
        eiColumn.setDescName("责任部门,引用自tbmdb02(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("责任部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managerid");
        eiColumn.setDescName("责任人,引用自tbmdb02(id)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("activetime");
        eiColumn.setType("N");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("有效时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jobContent");
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

        eiColumn = new EiColumn("finishTime");
        eiColumn.setDescName("完工时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishMan");
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
        
        eiColumn = new EiColumn("exceptionStatus");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("exceptionResult");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn= new EiColumn("writeValue");
        eiColumn.setDescName("结果记录");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("jitemName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("errorContent");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemID");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskNameType");
        eiColumn.setDescName("任务名称");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("processType");
        eiColumn.setDescName("处理方式");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("solutionType");
        eiColumn.setDescName("解决方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("schemeID");
        eiColumn.setDescName("解决方式");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DIYC01() {
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
    public String getTaskCode() {
        return this.taskCode;
    }

    /**
     * set the taskcode - 作务实绩的代码
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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
     * get the departid - 责任部门,引用自tbmdb02(id)
     * @return the departid
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the departid - 责任部门,引用自tbmdb02(id)
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the managerid - 责任人,引用自tbmdb02(id)
     * @return the managerid
     */
    public String getManagerid() {
        return this.managerid;
    }

    /**
     * set the managerid - 责任人,引用自tbmdb02(id)
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid;
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
    public String getJobContent() {
        return this.jobContent;
    }

    /**
     * set the jobcontent - 作业说明
     */
    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
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
    public Timestamp getFinishTime() {
        return this.finishTime;
    }

    /**
     * set the finishtime - 完工时间
     */
    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * get the finishman - 完成人
     * @return the finishman
     */
    public String getFinishMan() {
        return this.finishMan;
    }

    /**
     * set the finishman - 完成人
     */
    public void setFinishMan(String finishMan) {
        this.finishMan = finishMan;
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

    public String getExceptionStatus() {
        return exceptionStatus;
    }

    public void setExceptionStatus(String exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }
    
    public String getExceptionResult() {
        return exceptionResult;
    }

    public void setExceptionResult(String exceptionResult) {
        this.exceptionResult = exceptionResult;
    }
    
    public String getWriteValue() {
		return writeValue;
	}

	public void setWriteValue(String writeValue) {
		this.writeValue = writeValue;
	}

	public String getJitemName() {
        return jitemName;
    }

    public void setJitemName(String jitemName) {
        this.jitemName = jitemName;
    }
    
    public String getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getTaskNameType() {
        return taskNameType;
    }

    public void setTaskNameType(String taskNameType) {
        this.taskNameType = taskNameType;
    }


    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(String solutionType) {
        this.solutionType = solutionType;
    }

    public String getSchemeID() {
        return schemeID;
    }

    public void setSchemeID(String schemeID) {
        this.schemeID = schemeID;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setTaskid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskid")), taskid));
        setTaskCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskCode")), taskCode));
        setDepartid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departid")), departid));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setManagerid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managerid")), managerid));
        setActivetime(NumberUtils.toBigDecimal(StringUtils.toString(map.get("activetime")), activetime));
        setJobContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jobContent")), jobContent));
        setMemo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memo")), memo));
        setSchemeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemeid")), schemeid));
        setMachineid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("machineid")), machineid));
        setReccreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("reccreatetime"))));
        setCreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("createtime"))));
        setLastwritetime(DateUtils.toTimestamp(StringUtils.toString(map.get("lastwritetime"))));
        setLastwriteman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastwriteman")), lastwriteman));
        setFinishTime(DateUtils.toTimestamp(StringUtils.toString(map.get("finishTime"))));
        setFinishMan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishMan")), finishMan));
        setInvalidtime(DateUtils.toTimestamp(StringUtils.toString(map.get("invalidtime"))));
        setInvalidman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("invalidman")), invalidman));
        setStatus(NumberUtils.toInteger(StringUtils.toString(map.get("status")), status));
        setExceptionflag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exceptionflag")), exceptionflag));
        setExceptionStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exceptionStatus")), exceptionStatus));
        setExceptionResult(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exceptionResult")), exceptionResult));
        setWriteValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("writeValue")), writeValue));
        setJitemName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jitemName")), jitemName));
        setErrorContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("errorContent")), errorContent));
        setItemID(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemID")), itemID));
        setTaskNameType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskNameType")), taskNameType));
        setProcessType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processType")),processType));
        setSolutionType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solutionType")),solutionType));
        setSchemeID(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("schemeID")),schemeID));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("taskid",StringUtils.toString(taskid, eiMetadata.getMeta("taskid")));
        map.put("taskCode",StringUtils.toString(taskCode, eiMetadata.getMeta("taskCode")));
        map.put("departid",StringUtils.toString(departid, eiMetadata.getMeta("departid")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("managerid",StringUtils.toString(managerid, eiMetadata.getMeta("managerid")));
        map.put("activetime",StringUtils.toString(activetime, eiMetadata.getMeta("activetime")));
        map.put("jobContent",StringUtils.toString(jobContent, eiMetadata.getMeta("jobContent")));
        map.put("memo",StringUtils.toString(memo, eiMetadata.getMeta("memo")));
        map.put("schemeid",StringUtils.toString(schemeid, eiMetadata.getMeta("schemeid")));
        map.put("machineid",StringUtils.toString(machineid, eiMetadata.getMeta("machineid")));
        map.put("reccreatetime",StringUtils.toString(reccreatetime, eiMetadata.getMeta("reccreatetime")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("lastwritetime",StringUtils.toString(lastwritetime, eiMetadata.getMeta("lastwritetime")));
        map.put("lastwriteman",StringUtils.toString(lastwriteman, eiMetadata.getMeta("lastwriteman")));
        map.put("finishTime",Date(StringUtils.toString(finishTime, eiMetadata.getMeta("finishTime"))));
        map.put("finishMan",StringUtils.toString(finishMan, eiMetadata.getMeta("finishMan")));
        map.put("invalidtime",StringUtils.toString(invalidtime, eiMetadata.getMeta("invalidtime")));
        map.put("invalidman",StringUtils.toString(invalidman, eiMetadata.getMeta("invalidman")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("exceptionflag",StringUtils.toString(exceptionflag, eiMetadata.getMeta("exceptionflag")));
        map.put("exceptionStatus",StringUtils.toString(exceptionStatus, eiMetadata.getMeta("exceptionStatus")));
        map.put("exceptionResult",StringUtils.toString(exceptionResult, eiMetadata.getMeta("exceptionResult")));
        map.put("writeValue",StringUtils.toString(writeValue, eiMetadata.getMeta("writeValue")));
        map.put("jitemName",StringUtils.toString(jitemName, eiMetadata.getMeta("jitemName")));
        map.put("errorContent",StringUtils.toString(errorContent, eiMetadata.getMeta("errorContent")));
        map.put("itemID",StringUtils.toString(itemID, eiMetadata.getMeta("itemID")));
        map.put("taskNameType",StringUtils.toString(taskNameType, eiMetadata.getMeta("taskNameType")));
        map.put("processType",StringUtils.toString(processType,eiMetadata.getMeta("processType")));
        map.put("solutionType",StringUtils.toString(solutionType,eiMetadata.getMeta("solutionType")));
        map.put("schemeID",StringUtils.toString(schemeID,eiMetadata.getMeta("schemeID")));
        return map;
    }


    public String Date(String date){
        String data = "";
        if(null == date){
        }else {
            Date d = null;
            try {
                d = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            data = df.format(d);
        }
        return data;
    }
}