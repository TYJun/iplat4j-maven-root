/**
* Generate time : 2021-04-27 11:25:49
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.pr.gl.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* TSaftyDanger
* 
*/
public class PRGL04 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer dangerid = new Integer(0);		/*  主键*/
    private String dangercode = " ";		/*  隐患代码 */
    private String dnagername = " ";		/*  隐患标题*/
    private String dangerwhere = " ";		/*  隐患位置*/
    private String dangerlevel = " ";		/*  隐患等级*/
    private String requiredesc = " ";		/*  整改要求*/
    private Integer dangerclassid = new Integer(0);		/*  隐患类型*/
    private String taskclass = " ";		/*  计划类型(表名)*/
    private String taskcode = " ";		/*  计划的代码*/
    private String contentdesc = " ";		/*  隐患的内容描*/
    private String requiredtime = "";		/*  要求完成整改的时间*/
    private String hospitalid = " ";		/*  相关医院*/
    private String dangername = " ";		/*  隐患类型*/
    private String departname = " ";		/*  部门*/
    private String taskdepartname = " ";		/*  责任部门*/
    private Integer status = new Integer(0);		/*  隐患情况*/
    private Integer status2 = new Integer(0);		/*  整改情况*/
    private Timestamp createtime ;		/*  创建时间*/
    private String createman = " ";		/*  创建人 */
    private String createip = " ";		/*  创建IP*/
    private Timestamp modifytime ;		/*  修改时间*/
    private String modifyman = " ";		/*  修改人*/
    private String modifyip = " ";		/*  修改IP*/
    private Timestamp audittime ;		/*  审核时间*/
    private String auditman = " ";		/*  审核人*/
    private String auditip = " ";		/*  审核IP*/
    private String globalid = " ";		
    private String addfromid = " ";		
    private String taskdepartid = " ";		
    private Integer dangertype = new Integer(0);		
    private String frombillid = " ";		
    private String answerman = " ";		/* 任务接受人*/
    private String answerip = " ";		/* 任务接受IP*/
    private Timestamp answertime ;		/* 任务接受时间*/
    private Integer answertype = new Integer(0);		/* 接收状态*/
    private Long addid = new Long(0);		/* 移动端增加的ID号*/
    private Timestamp bakcreatetime ;		
    private Integer bakrequriedtime = new Integer(0);		
    private String fromsouceclass = " ";		
    private String fromsouceobject = " ";		
    private String dangerclassfullname = " ";		
    private String dangerclassname = " ";		
    private String previousdangerglobalid = " ";		
    private String activitikey = " ";		
    private String dispatchman = " ";		
    private Integer selfflag = new Integer(0);		
    private String id = " ";		
    private String currentDealer = " ";		
    private String statusCode = " ";		
    private String processInstId = " ";		
    private String recRevisor = " ";		
    private String recReviseTime = " ";		
    private Timestamp createDate ;		
    private String creatorId = " ";		
    private String creatorName = " ";		
    private String creatorPhone = " ";		
    private Timestamp updateDate ;		
    private String source = " ";		/* source*/
    private String updatorId = " ";		
    private String updatorName = " ";		
    private String operateType = " ";		
    private String dangerclasstypecode = " ";		
    private String dangerclasstypename = " ";		
    private String createtype = " ";		/* 登记来源：PC/PAD/APP*/
    private String localtypecode = " ";		
    private Timestamp warningtime ;		/* 超期时间*/
    private Timestamp warning2time ;		/* 超期时间,7天通知院长的*/
    private String rejectstatus = "0";		/* 驳回字段：0--正常；1--被驳回;2--驳回之后重新指定部门*/
    private String acceptType = " ";		
    private String msgstatus = " ";		
    private String typeName = " ";		
    private String rejectReason = " ";		
    private String realName = " ";		
    private String logsTime = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("dangerid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" 主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangercode");
        eiColumn.setDescName(" 隐患代码 ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dnagername");
        eiColumn.setDescName(" 隐患标题");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerwhere");
        eiColumn.setDescName(" 隐患位置");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerlevel");
        eiColumn.setDescName(" 隐患等级");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("requiredesc");
        eiColumn.setDescName(" 整改要求");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclassid");
        eiColumn.setDescName(" 隐患类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskclass");
        eiColumn.setDescName(" 计划类型(表名)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskcode");
        eiColumn.setDescName(" 计划的代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contentdesc");
        eiColumn.setDescName(" 隐患的内容描");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("requiredtime");
        eiColumn.setDescName(" 要求完成整改的时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hospitalid");
        eiColumn.setDescName(" 相关医院");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangername");
        eiColumn.setDescName(" 隐患类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departname");
        eiColumn.setDescName(" 部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskdepartname");
        eiColumn.setDescName(" 责任部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName(" 隐患情况");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status2");
        eiColumn.setDescName(" 整改情况");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName(" 创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createman");
        eiColumn.setDescName(" 创建人 ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createip");
        eiColumn.setDescName(" 创建IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifytime");
        eiColumn.setDescName(" 修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyman");
        eiColumn.setDescName(" 修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifyip");
        eiColumn.setDescName(" 修改IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("audittime");
        eiColumn.setDescName(" 审核时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditman");
        eiColumn.setDescName(" 审核人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditip");
        eiColumn.setDescName(" 审核IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("globalid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("addfromid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskdepartid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangertype");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("frombillid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("answerman");
        eiColumn.setDescName("任务接受人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("answerip");
        eiColumn.setDescName("任务接受IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("answertime");
        eiColumn.setDescName("任务接受时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("answertype");
        eiColumn.setDescName("接收状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("addid");
        eiColumn.setDescName("移动端增加的ID号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bakcreatetime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bakrequriedtime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fromsouceclass");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fromsouceobject");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclassfullname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclassname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("previousdangerglobalid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("activitikey");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dispatchman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("selfflag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("currentDealer");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processInstId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createDate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creatorPhone");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateDate");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("source");
        eiColumn.setDescName("source");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorId");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatorName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operateType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclasstypecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclasstypename");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtype");
        eiColumn.setDescName("登记来源：PC/PAD/APP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("localtypecode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("warningtime");
        eiColumn.setDescName("超期时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("warning2time");
        eiColumn.setDescName("超期时间,7天通知院长的");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectstatus");
        eiColumn.setDescName("驳回字段：0--正常；1--被驳回;2--驳回之后重新指定部门");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("acceptType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("msgstatus");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("typeName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rejectReason");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("realName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("logsTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PRGL04() {
        initMetaData();
    }

    /**
     * get the dangerid -  主键
     * @return the dangerid
     */
    public Integer getDangerid() {
        return this.dangerid;
    }

    /**
     * set the dangerid -  主键
     */
    public void setDangerid(Integer dangerid) {
        this.dangerid = dangerid;
    }

    /**
     * get the dangercode -  隐患代码 
     * @return the dangercode
     */
    public String getDangercode() {
        return this.dangercode;
    }

    /**
     * set the dangercode -  隐患代码 
     */
    public void setDangercode(String dangercode) {
        this.dangercode = dangercode;
    }

    /**
     * get the dnagername -  隐患标题
     * @return the dnagername
     */
    public String getDnagername() {
        return this.dnagername;
    }

    /**
     * set the dnagername -  隐患标题
     */
    public void setDnagername(String dnagername) {
        this.dnagername = dnagername;
    }

    /**
     * get the dangerwhere -  隐患位置
     * @return the dangerwhere
     */
    public String getDangerwhere() {
        return this.dangerwhere;
    }

    /**
     * set the dangerwhere -  隐患位置
     */
    public void setDangerwhere(String dangerwhere) {
        this.dangerwhere = dangerwhere;
    }

    /**
     * get the dangerlevel -  隐患等级
     * @return the dangerlevel
     */
    public String getDangerlevel() {
        return this.dangerlevel;
    }

    /**
     * set the dangerlevel -  隐患等级
     */
    public void setDangerlevel(String dangerlevel) {
        this.dangerlevel = dangerlevel;
    }

    /**
     * get the requiredesc -  整改要求
     * @return the requiredesc
     */
    public String getRequiredesc() {
        return this.requiredesc;
    }

    /**
     * set the requiredesc -  整改要求
     */
    public void setRequiredesc(String requiredesc) {
        this.requiredesc = requiredesc;
    }

    /**
     * get the dangerclassid -  隐患类型
     * @return the dangerclassid
     */
    public Integer getDangerclassid() {
        return this.dangerclassid;
    }

    /**
     * set the dangerclassid -  隐患类型
     */
    public void setDangerclassid(Integer dangerclassid) {
        this.dangerclassid = dangerclassid;
    }

    /**
     * get the taskclass -  计划类型(表名)
     * @return the taskclass
     */
    public String getTaskclass() {
        return this.taskclass;
    }

    /**
     * set the taskclass -  计划类型(表名)
     */
    public void setTaskclass(String taskclass) {
        this.taskclass = taskclass;
    }

    /**
     * get the taskcode -  计划的代码
     * @return the taskcode
     */
    public String getTaskcode() {
        return this.taskcode;
    }

    /**
     * set the taskcode -  计划的代码
     */
    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode;
    }

    /**
     * get the contentdesc -  隐患的内容描
     * @return the contentdesc
     */
    public String getContentdesc() {
        return this.contentdesc;
    }

    /**
     * set the contentdesc -  隐患的内容描
     */
    public void setContentdesc(String contentdesc) {
        this.contentdesc = contentdesc;
    }

    /**
     * get the requiredtime -  要求完成整改的时间
     * @return the requiredtime
     */
    public String getRequiredtime() {
        return this.requiredtime;
    }

    /**
     * set the requiredtime -  要求完成整改的时间
     */
    public void setRequiredtime(String requiredtime) {
        this.requiredtime = requiredtime;
    }

    /**
     * get the hospitalid -  相关医院
     * @return the hospitalid
     */
    public String getHospitalid() {
        return this.hospitalid;
    }

    /**
     * set the hospitalid -  相关医院
     */
    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }

    /**
     * get the dangername -  隐患类型
     * @return the dangername
     */
    public String getDangername() {
        return this.dangername;
    }

    /**
     * set the dangername -  隐患类型
     */
    public void setDangername(String dangername) {
        this.dangername = dangername;
    }

    /**
     * get the departname -  部门
     * @return the departname
     */
    public String getDepartname() {
        return this.departname;
    }

    /**
     * set the departname -  部门
     */
    public void setDepartname(String departname) {
        this.departname = departname;
    }

    /**
     * get the taskdepartname -  责任部门
     * @return the taskdepartname
     */
    public String getTaskdepartname() {
        return this.taskdepartname;
    }

    /**
     * set the taskdepartname -  责任部门
     */
    public void setTaskdepartname(String taskdepartname) {
        this.taskdepartname = taskdepartname;
    }

    /**
     * get the status -  隐患情况
     * @return the status
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * set the status -  隐患情况
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get the status2 -  整改情况
     * @return the status2
     */
    public Integer getStatus2() {
        return this.status2;
    }

    /**
     * set the status2 -  整改情况
     */
    public void setStatus2(Integer status2) {
        this.status2 = status2;
    }

    /**
     * get the createtime -  创建时间
     * @return the createtime
     */
    public Timestamp getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime -  创建时间
     */
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    /**
     * get the createman -  创建人 
     * @return the createman
     */
    public String getCreateman() {
        return this.createman;
    }

    /**
     * set the createman -  创建人 
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * get the createip -  创建IP
     * @return the createip
     */
    public String getCreateip() {
        return this.createip;
    }

    /**
     * set the createip -  创建IP
     */
    public void setCreateip(String createip) {
        this.createip = createip;
    }

    /**
     * get the modifytime -  修改时间
     * @return the modifytime
     */
    public Timestamp getModifytime() {
        return this.modifytime;
    }

    /**
     * set the modifytime -  修改时间
     */
    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * get the modifyman -  修改人
     * @return the modifyman
     */
    public String getModifyman() {
        return this.modifyman;
    }

    /**
     * set the modifyman -  修改人
     */
    public void setModifyman(String modifyman) {
        this.modifyman = modifyman;
    }

    /**
     * get the modifyip -  修改IP
     * @return the modifyip
     */
    public String getModifyip() {
        return this.modifyip;
    }

    /**
     * set the modifyip -  修改IP
     */
    public void setModifyip(String modifyip) {
        this.modifyip = modifyip;
    }

    /**
     * get the audittime -  审核时间
     * @return the audittime
     */
    public Timestamp getAudittime() {
        return this.audittime;
    }

    /**
     * set the audittime -  审核时间
     */
    public void setAudittime(Timestamp audittime) {
        this.audittime = audittime;
    }

    /**
     * get the auditman -  审核人
     * @return the auditman
     */
    public String getAuditman() {
        return this.auditman;
    }

    /**
     * set the auditman -  审核人
     */
    public void setAuditman(String auditman) {
        this.auditman = auditman;
    }

    /**
     * get the auditip -  审核IP
     * @return the auditip
     */
    public String getAuditip() {
        return this.auditip;
    }

    /**
     * set the auditip -  审核IP
     */
    public void setAuditip(String auditip) {
        this.auditip = auditip;
    }

    /**
     * get the globalid 
     * @return the globalid
     */
    public String getGlobalid() {
        return this.globalid;
    }

    /**
     * set the globalid 
     */
    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

    /**
     * get the addfromid 
     * @return the addfromid
     */
    public String getAddfromid() {
        return this.addfromid;
    }

    /**
     * set the addfromid 
     */
    public void setAddfromid(String addfromid) {
        this.addfromid = addfromid;
    }

    /**
     * get the taskdepartid 
     * @return the taskdepartid
     */
    public String getTaskdepartid() {
        return this.taskdepartid;
    }

    /**
     * set the taskdepartid 
     */
    public void setTaskdepartid(String taskdepartid) {
        this.taskdepartid = taskdepartid;
    }

    /**
     * get the dangertype 
     * @return the dangertype
     */
    public Integer getDangertype() {
        return this.dangertype;
    }

    /**
     * set the dangertype 
     */
    public void setDangertype(Integer dangertype) {
        this.dangertype = dangertype;
    }

    /**
     * get the frombillid 
     * @return the frombillid
     */
    public String getFrombillid() {
        return this.frombillid;
    }

    /**
     * set the frombillid 
     */
    public void setFrombillid(String frombillid) {
        this.frombillid = frombillid;
    }

    /**
     * get the answerman - 任务接受人
     * @return the answerman
     */
    public String getAnswerman() {
        return this.answerman;
    }

    /**
     * set the answerman - 任务接受人
     */
    public void setAnswerman(String answerman) {
        this.answerman = answerman;
    }

    /**
     * get the answerip - 任务接受IP
     * @return the answerip
     */
    public String getAnswerip() {
        return this.answerip;
    }

    /**
     * set the answerip - 任务接受IP
     */
    public void setAnswerip(String answerip) {
        this.answerip = answerip;
    }

    /**
     * get the answertime - 任务接受时间
     * @return the answertime
     */
    public Timestamp getAnswertime() {
        return this.answertime;
    }

    /**
     * set the answertime - 任务接受时间
     */
    public void setAnswertime(Timestamp answertime) {
        this.answertime = answertime;
    }

    /**
     * get the answertype - 接收状态
     * @return the answertype
     */
    public Integer getAnswertype() {
        return this.answertype;
    }

    /**
     * set the answertype - 接收状态
     */
    public void setAnswertype(Integer answertype) {
        this.answertype = answertype;
    }

    /**
     * get the addid - 移动端增加的ID号
     * @return the addid
     */
    public Long getAddid() {
        return this.addid;
    }

    /**
     * set the addid - 移动端增加的ID号
     */
    public void setAddid(Long addid) {
        this.addid = addid;
    }

    /**
     * get the bakcreatetime 
     * @return the bakcreatetime
     */
    public Timestamp getBakcreatetime() {
        return this.bakcreatetime;
    }

    /**
     * set the bakcreatetime 
     */
    public void setBakcreatetime(Timestamp bakcreatetime) {
        this.bakcreatetime = bakcreatetime;
    }

    /**
     * get the bakrequriedtime 
     * @return the bakrequriedtime
     */
    public Integer getBakrequriedtime() {
        return this.bakrequriedtime;
    }

    /**
     * set the bakrequriedtime 
     */
    public void setBakrequriedtime(Integer bakrequriedtime) {
        this.bakrequriedtime = bakrequriedtime;
    }

    /**
     * get the fromsouceclass 
     * @return the fromsouceclass
     */
    public String getFromsouceclass() {
        return this.fromsouceclass;
    }

    /**
     * set the fromsouceclass 
     */
    public void setFromsouceclass(String fromsouceclass) {
        this.fromsouceclass = fromsouceclass;
    }

    /**
     * get the fromsouceobject 
     * @return the fromsouceobject
     */
    public String getFromsouceobject() {
        return this.fromsouceobject;
    }

    /**
     * set the fromsouceobject 
     */
    public void setFromsouceobject(String fromsouceobject) {
        this.fromsouceobject = fromsouceobject;
    }

    /**
     * get the dangerclassfullname 
     * @return the dangerclassfullname
     */
    public String getDangerclassfullname() {
        return this.dangerclassfullname;
    }

    /**
     * set the dangerclassfullname 
     */
    public void setDangerclassfullname(String dangerclassfullname) {
        this.dangerclassfullname = dangerclassfullname;
    }

    /**
     * get the dangerclassname 
     * @return the dangerclassname
     */
    public String getDangerclassname() {
        return this.dangerclassname;
    }

    /**
     * set the dangerclassname 
     */
    public void setDangerclassname(String dangerclassname) {
        this.dangerclassname = dangerclassname;
    }

    /**
     * get the previousdangerglobalid 
     * @return the previousdangerglobalid
     */
    public String getPreviousdangerglobalid() {
        return this.previousdangerglobalid;
    }

    /**
     * set the previousdangerglobalid 
     */
    public void setPreviousdangerglobalid(String previousdangerglobalid) {
        this.previousdangerglobalid = previousdangerglobalid;
    }

    /**
     * get the activitikey 
     * @return the activitikey
     */
    public String getActivitikey() {
        return this.activitikey;
    }

    /**
     * set the activitikey 
     */
    public void setActivitikey(String activitikey) {
        this.activitikey = activitikey;
    }

    /**
     * get the dispatchman 
     * @return the dispatchman
     */
    public String getDispatchman() {
        return this.dispatchman;
    }

    /**
     * set the dispatchman 
     */
    public void setDispatchman(String dispatchman) {
        this.dispatchman = dispatchman;
    }

    /**
     * get the selfflag 
     * @return the selfflag
     */
    public Integer getSelfflag() {
        return this.selfflag;
    }

    /**
     * set the selfflag 
     */
    public void setSelfflag(Integer selfflag) {
        this.selfflag = selfflag;
    }

    /**
     * get the id 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the currentDealer 
     * @return the currentDealer
     */
    public String getCurrentDealer() {
        return this.currentDealer;
    }

    /**
     * set the currentDealer 
     */
    public void setCurrentDealer(String currentDealer) {
        this.currentDealer = currentDealer;
    }

    /**
     * get the statusCode 
     * @return the statusCode
     */
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * set the statusCode 
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * get the processInstId 
     * @return the processInstId
     */
    public String getProcessInstId() {
        return this.processInstId;
    }

    /**
     * set the processInstId 
     */
    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
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
     * get the createDate 
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /**
     * set the createDate 
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * get the creatorId 
     * @return the creatorId
     */
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * set the creatorId 
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * get the creatorName 
     * @return the creatorName
     */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * set the creatorName 
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * get the creatorPhone 
     * @return the creatorPhone
     */
    public String getCreatorPhone() {
        return this.creatorPhone;
    }

    /**
     * set the creatorPhone 
     */
    public void setCreatorPhone(String creatorPhone) {
        this.creatorPhone = creatorPhone;
    }

    /**
     * get the updateDate 
     * @return the updateDate
     */
    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    /**
     * set the updateDate 
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * get the source - source
     * @return the source
     */
    public String getSource() {
        return this.source;
    }

    /**
     * set the source - source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * get the updatorId 
     * @return the updatorId
     */
    public String getUpdatorId() {
        return this.updatorId;
    }

    /**
     * set the updatorId 
     */
    public void setUpdatorId(String updatorId) {
        this.updatorId = updatorId;
    }

    /**
     * get the updatorName 
     * @return the updatorName
     */
    public String getUpdatorName() {
        return this.updatorName;
    }

    /**
     * set the updatorName 
     */
    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }

    /**
     * get the operateType 
     * @return the operateType
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * set the operateType 
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * get the dangerclasstypecode 
     * @return the dangerclasstypecode
     */
    public String getDangerclasstypecode() {
        return this.dangerclasstypecode;
    }

    /**
     * set the dangerclasstypecode 
     */
    public void setDangerclasstypecode(String dangerclasstypecode) {
        this.dangerclasstypecode = dangerclasstypecode;
    }

    /**
     * get the dangerclasstypename 
     * @return the dangerclasstypename
     */
    public String getDangerclasstypename() {
        return this.dangerclasstypename;
    }

    /**
     * set the dangerclasstypename 
     */
    public void setDangerclasstypename(String dangerclasstypename) {
        this.dangerclasstypename = dangerclasstypename;
    }

    /**
     * get the createtype - 登记来源：PC/PAD/APP
     * @return the createtype
     */
    public String getCreatetype() {
        return this.createtype;
    }

    /**
     * set the createtype - 登记来源：PC/PAD/APP
     */
    public void setCreatetype(String createtype) {
        this.createtype = createtype;
    }

    /**
     * get the localtypecode 
     * @return the localtypecode
     */
    public String getLocaltypecode() {
        return this.localtypecode;
    }

    /**
     * set the localtypecode 
     */
    public void setLocaltypecode(String localtypecode) {
        this.localtypecode = localtypecode;
    }

    /**
     * get the warningtime - 超期时间
     * @return the warningtime
     */
    public Timestamp getWarningtime() {
        return this.warningtime;
    }

    /**
     * set the warningtime - 超期时间
     */
    public void setWarningtime(Timestamp warningtime) {
        this.warningtime = warningtime;
    }

    /**
     * get the warning2time - 超期时间,7天通知院长的
     * @return the warning2time
     */
    public Timestamp getWarning2time() {
        return this.warning2time;
    }

    /**
     * set the warning2time - 超期时间,7天通知院长的
     */
    public void setWarning2time(Timestamp warning2time) {
        this.warning2time = warning2time;
    }

    /**
     * get the rejectstatus - 驳回字段：0--正常；1--被驳回;2--驳回之后重新指定部门
     * @return the rejectstatus
     */
    public String getRejectstatus() {
        return this.rejectstatus;
    }

    /**
     * set the rejectstatus - 驳回字段：0--正常；1--被驳回;2--驳回之后重新指定部门
     */
    public void setRejectstatus(String rejectstatus) {
        this.rejectstatus = rejectstatus;
    }

    /**
     * get the acceptType 
     * @return the acceptType
     */
    public String getAcceptType() {
        return this.acceptType;
    }

    /**
     * set the acceptType 
     */
    public void setAcceptType(String acceptType) {
        this.acceptType = acceptType;
    }

    /**
     * get the msgstatus 
     * @return the msgstatus
     */
    public String getMsgstatus() {
        return this.msgstatus;
    }

    /**
     * set the msgstatus 
     */
    public void setMsgstatus(String msgstatus) {
        this.msgstatus = msgstatus;
    }
    
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLogsTime() {
        return logsTime;
    }

    public void setLogsTime(String logsTime) {
        this.logsTime = logsTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDangerid(NumberUtils.toInteger(StringUtils.toString(map.get("dangerid")), dangerid));
        setDangercode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangercode")), dangercode));
        setDnagername(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dnagername")), dnagername));
        setDangerwhere(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerwhere")), dangerwhere));
        setDangerlevel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerlevel")), dangerlevel));
        setRequiredesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requiredesc")), requiredesc));
        setDangerclassid(NumberUtils.toInteger(StringUtils.toString(map.get("dangerclassid")), dangerclassid));
        setTaskclass(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskclass")), taskclass));
        setTaskcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskcode")), taskcode));
        setContentdesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contentdesc")), contentdesc));
        setRequiredtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requiredtime")), requiredtime));
        setHospitalid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hospitalid")), hospitalid));
        setDangername(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangername")), dangername));
        setDepartname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departname")), departname));
        setTaskdepartname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskdepartname")), taskdepartname));
        setStatus(NumberUtils.toInteger(StringUtils.toString(map.get("status")), status));
        setStatus2(NumberUtils.toInteger(StringUtils.toString(map.get("status2")), status2));
        setCreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("createtime"))));
        setCreateman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createman")), createman));
        setCreateip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createip")), createip));
        setModifytime(DateUtils.toTimestamp(StringUtils.toString(map.get("modifytime"))));
        setModifyman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyman")), modifyman));
        setModifyip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifyip")), modifyip));
        setAudittime(DateUtils.toTimestamp(StringUtils.toString(map.get("audittime"))));
        setAuditman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditman")), auditman));
        setAuditip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditip")), auditip));
        setGlobalid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("globalid")), globalid));
        setAddfromid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("addfromid")), addfromid));
        setTaskdepartid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskdepartid")), taskdepartid));
        setDangertype(NumberUtils.toInteger(StringUtils.toString(map.get("dangertype")), dangertype));
        setFrombillid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("frombillid")), frombillid));
        setAnswerman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("answerman")), answerman));
        setAnswerip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("answerip")), answerip));
        setAnswertime(DateUtils.toTimestamp(StringUtils.toString(map.get("answertime"))));
        setAnswertype(NumberUtils.toInteger(StringUtils.toString(map.get("answertype")), answertype));
        setAddid(NumberUtils.toLong(StringUtils.toString(map.get("addid")), addid));
        setBakcreatetime(DateUtils.toTimestamp(StringUtils.toString(map.get("bakcreatetime"))));
        setBakrequriedtime(NumberUtils.toInteger(StringUtils.toString(map.get("bakrequriedtime")), bakrequriedtime));
        setFromsouceclass(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fromsouceclass")), fromsouceclass));
        setFromsouceobject(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fromsouceobject")), fromsouceobject));
        setDangerclassfullname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclassfullname")), dangerclassfullname));
        setDangerclassname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclassname")), dangerclassname));
        setPreviousdangerglobalid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("previousdangerglobalid")), previousdangerglobalid));
        setActivitikey(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("activitikey")), activitikey));
        setDispatchman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dispatchman")), dispatchman));
        setSelfflag(NumberUtils.toInteger(StringUtils.toString(map.get("selfflag")), selfflag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCurrentDealer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("currentDealer")), currentDealer));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setProcessInstId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processInstId")), processInstId));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setCreateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("createDate"))));
        setCreatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorId")), creatorId));
        setCreatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorName")), creatorName));
        setCreatorPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creatorPhone")), creatorPhone));
        setUpdateDate(DateUtils.toTimestamp(StringUtils.toString(map.get("updateDate"))));
        setSource(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("source")), source));
        setUpdatorId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorId")), updatorId));
        setUpdatorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatorName")), updatorName));
        setOperateType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operateType")), operateType));
        setDangerclasstypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclasstypecode")), dangerclasstypecode));
        setDangerclasstypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclasstypename")), dangerclasstypename));
        setCreatetype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createtype")), createtype));
        setLocaltypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("localtypecode")), localtypecode));
        setWarningtime(DateUtils.toTimestamp(StringUtils.toString(map.get("warningtime"))));
        setWarning2time(DateUtils.toTimestamp(StringUtils.toString(map.get("warning2time"))));
        setRejectstatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectstatus")), rejectstatus));
        setAcceptType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("acceptType")), acceptType));
        setMsgstatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("msgstatus")), msgstatus));
        setTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeName")), typeName));
        setRejectReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rejectReason")), rejectReason));
        setRealName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("realName")), realName));
        setLogsTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("logsTime")), logsTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("dangerid",StringUtils.toString(dangerid, eiMetadata.getMeta("dangerid")));
        map.put("dangercode",StringUtils.toString(dangercode, eiMetadata.getMeta("dangercode")));
        map.put("dnagername",StringUtils.toString(dnagername, eiMetadata.getMeta("dnagername")));
        map.put("dangerwhere",StringUtils.toString(dangerwhere, eiMetadata.getMeta("dangerwhere")));
        map.put("dangerlevel",StringUtils.toString(dangerlevel, eiMetadata.getMeta("dangerlevel")));
        map.put("requiredesc",StringUtils.toString(requiredesc, eiMetadata.getMeta("requiredesc")));
        map.put("dangerclassid",StringUtils.toString(dangerclassid, eiMetadata.getMeta("dangerclassid")));
        map.put("taskclass",StringUtils.toString(taskclass, eiMetadata.getMeta("taskclass")));
        map.put("taskcode",StringUtils.toString(taskcode, eiMetadata.getMeta("taskcode")));
        map.put("contentdesc",StringUtils.toString(contentdesc, eiMetadata.getMeta("contentdesc")));
        map.put("requiredtime",StringUtils.toString(requiredtime, eiMetadata.getMeta("requiredtime")));
        map.put("hospitalid",StringUtils.toString(hospitalid, eiMetadata.getMeta("hospitalid")));
        map.put("dangername",StringUtils.toString(dangername, eiMetadata.getMeta("dangername")));
        map.put("departname",StringUtils.toString(departname, eiMetadata.getMeta("departname")));
        map.put("taskdepartname",StringUtils.toString(taskdepartname, eiMetadata.getMeta("taskdepartname")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("status2",StringUtils.toString(status2, eiMetadata.getMeta("status2")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("createman",StringUtils.toString(createman, eiMetadata.getMeta("createman")));
        map.put("createip",StringUtils.toString(createip, eiMetadata.getMeta("createip")));
        map.put("modifytime",StringUtils.toString(modifytime, eiMetadata.getMeta("modifytime")));
        map.put("modifyman",StringUtils.toString(modifyman, eiMetadata.getMeta("modifyman")));
        map.put("modifyip",StringUtils.toString(modifyip, eiMetadata.getMeta("modifyip")));
        map.put("audittime",StringUtils.toString(audittime, eiMetadata.getMeta("audittime")));
        map.put("auditman",StringUtils.toString(auditman, eiMetadata.getMeta("auditman")));
        map.put("auditip",StringUtils.toString(auditip, eiMetadata.getMeta("auditip")));
        map.put("globalid",StringUtils.toString(globalid, eiMetadata.getMeta("globalid")));
        map.put("addfromid",StringUtils.toString(addfromid, eiMetadata.getMeta("addfromid")));
        map.put("taskdepartid",StringUtils.toString(taskdepartid, eiMetadata.getMeta("taskdepartid")));
        map.put("dangertype",StringUtils.toString(dangertype, eiMetadata.getMeta("dangertype")));
        map.put("frombillid",StringUtils.toString(frombillid, eiMetadata.getMeta("frombillid")));
        map.put("answerman",StringUtils.toString(answerman, eiMetadata.getMeta("answerman")));
        map.put("answerip",StringUtils.toString(answerip, eiMetadata.getMeta("answerip")));
        map.put("answertime",StringUtils.toString(answertime, eiMetadata.getMeta("answertime")));
        map.put("answertype",StringUtils.toString(answertype, eiMetadata.getMeta("answertype")));
        map.put("addid",StringUtils.toString(addid, eiMetadata.getMeta("addid")));
        map.put("bakcreatetime",StringUtils.toString(bakcreatetime, eiMetadata.getMeta("bakcreatetime")));
        map.put("bakrequriedtime",StringUtils.toString(bakrequriedtime, eiMetadata.getMeta("bakrequriedtime")));
        map.put("fromsouceclass",StringUtils.toString(fromsouceclass, eiMetadata.getMeta("fromsouceclass")));
        map.put("fromsouceobject",StringUtils.toString(fromsouceobject, eiMetadata.getMeta("fromsouceobject")));
        map.put("dangerclassfullname",StringUtils.toString(dangerclassfullname, eiMetadata.getMeta("dangerclassfullname")));
        map.put("dangerclassname",StringUtils.toString(dangerclassname, eiMetadata.getMeta("dangerclassname")));
        map.put("previousdangerglobalid",StringUtils.toString(previousdangerglobalid, eiMetadata.getMeta("previousdangerglobalid")));
        map.put("activitikey",StringUtils.toString(activitikey, eiMetadata.getMeta("activitikey")));
        map.put("dispatchman",StringUtils.toString(dispatchman, eiMetadata.getMeta("dispatchman")));
        map.put("selfflag",StringUtils.toString(selfflag, eiMetadata.getMeta("selfflag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("currentDealer",StringUtils.toString(currentDealer, eiMetadata.getMeta("currentDealer")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("processInstId",StringUtils.toString(processInstId, eiMetadata.getMeta("processInstId")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("createDate",StringUtils.toString(createDate, eiMetadata.getMeta("createDate")));
        map.put("creatorId",StringUtils.toString(creatorId, eiMetadata.getMeta("creatorId")));
        map.put("creatorName",StringUtils.toString(creatorName, eiMetadata.getMeta("creatorName")));
        map.put("creatorPhone",StringUtils.toString(creatorPhone, eiMetadata.getMeta("creatorPhone")));
        map.put("updateDate",StringUtils.toString(updateDate, eiMetadata.getMeta("updateDate")));
        map.put("source",StringUtils.toString(source, eiMetadata.getMeta("source")));
        map.put("updatorId",StringUtils.toString(updatorId, eiMetadata.getMeta("updatorId")));
        map.put("updatorName",StringUtils.toString(updatorName, eiMetadata.getMeta("updatorName")));
        map.put("operateType",StringUtils.toString(operateType, eiMetadata.getMeta("operateType")));
        map.put("dangerclasstypecode",StringUtils.toString(dangerclasstypecode, eiMetadata.getMeta("dangerclasstypecode")));
        map.put("dangerclasstypename",StringUtils.toString(dangerclasstypename, eiMetadata.getMeta("dangerclasstypename")));
        map.put("createtype",StringUtils.toString(createtype, eiMetadata.getMeta("createtype")));
        map.put("localtypecode",StringUtils.toString(localtypecode, eiMetadata.getMeta("localtypecode")));
        map.put("warningtime",StringUtils.toString(warningtime, eiMetadata.getMeta("warningtime")));
        map.put("warning2time",StringUtils.toString(warning2time, eiMetadata.getMeta("warning2time")));
        map.put("rejectstatus",StringUtils.toString(rejectstatus, eiMetadata.getMeta("rejectstatus")));
        map.put("acceptType",StringUtils.toString(acceptType, eiMetadata.getMeta("acceptType")));
        map.put("msgstatus",StringUtils.toString(msgstatus, eiMetadata.getMeta("msgstatus")));
        map.put("typeName",StringUtils.toString(typeName, eiMetadata.getMeta("typeName")));
        map.put("rejectReason",StringUtils.toString(rejectReason, eiMetadata.getMeta("rejectReason")));
        map.put("realName",StringUtils.toString(realName, eiMetadata.getMeta("realName")));
        map.put("logsTime",StringUtils.toString(logsTime, eiMetadata.getMeta("logsTime")));
        return map;
    }
}