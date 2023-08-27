/**
* Generate time : 2021-04-25 18:49:16
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

import org.springframework.format.annotation.DateTimeFormat;

import com.baosight.iplat4j.core.util.StringUtils;

/**
* TSaftyDangerResult
* 
*/
public class PRGL0302 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer dangerresultid = new Integer(0);		/* 主键*/
    private Integer dangerid = new Integer(0);		/* 所属的隐患*/
    private String manageman = " ";		/* 负责人姓名*/
    private String managephone = " ";		/* 负责人电话*/
    private Integer finishtime = new Integer(0);		/* 完成时间*/
    private String contentdesc = " ";		/* 整改内容*/
    private Integer resultstatus = new Integer(0);		/* 总体状态*/
    private Integer auditstatus = new Integer(0);		/* 审核状态*/
    private String auditcontent = " ";		/* 审核内容*/
    private String writeman = " ";		/* 登记人*/
    private String writeip = " ";		/* 登记IP*/
    private Timestamp writetime ;		
    private String auditman = " ";		/* 审核人*/
    private String auditip = " ";		/* 审核IP*/
    private Timestamp audittime ;		
    private String globalid = " ";		
    private String auditfromid = " ";		
    private String dangerclassfullname = " ";		/* 问题分类名称*/
    private String dangerclasstypecode = " ";		/* 问题分类类型的代码*/
    private String dangerclasstypename = " ";		/* 问题分类类型的名称*/
    private String localtypecode = " ";		/* 本院问题分类的代码*/
    private String time = " ";		/* 时间*/
    private String name = " ";		/* 姓名*/
    private String contentType = " ";		/* 状态*/
    private String param = " ";		/* 参数*/

    
    
    
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("dangerresultid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerid");
        eiColumn.setDescName("所属的隐患");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("manageman");
        eiColumn.setDescName("负责人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("managephone");
        eiColumn.setDescName("负责人电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finishtime");
        eiColumn.setDescName("完成时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contentdesc");
        eiColumn.setDescName("整改内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("resultstatus");
        eiColumn.setDescName("总体状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditstatus");
        eiColumn.setDescName("审核状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditcontent");
        eiColumn.setDescName("审核内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("writeman");
        eiColumn.setDescName("登记人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("writeip");
        eiColumn.setDescName("登记IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("writetime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditman");
        eiColumn.setDescName("审核人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditip");
        eiColumn.setDescName("审核IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("audittime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("globalid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditfromid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclassfullname");
        eiColumn.setDescName("问题分类名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclasstypecode");
        eiColumn.setDescName("问题分类类型的代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerclasstypename");
        eiColumn.setDescName("问题分类类型的名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("localtypecode");
        eiColumn.setDescName("本院问题分类的代码");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("time");
        eiColumn.setDescName("时间");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("name");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("contentType");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("param");
        eiColumn.setDescName("参数");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public PRGL0302() {
        initMetaData();
    }

    /**
     * get the dangerresultid - 主键
     * @return the dangerresultid
     */
    public Integer getDangerresultid() {
        return this.dangerresultid;
    }

    /**
     * set the dangerresultid - 主键
     */
    public void setDangerresultid(Integer dangerresultid) {
        this.dangerresultid = dangerresultid;
    }

    /**
     * get the dangerid - 所属的隐患
     * @return the dangerid
     */
    public Integer getDangerid() {
        return this.dangerid;
    }

    /**
     * set the dangerid - 所属的隐患
     */
    public void setDangerid(Integer dangerid) {
        this.dangerid = dangerid;
    }

    /**
     * get the manageman - 负责人姓名
     * @return the manageman
     */
    public String getManageman() {
        return this.manageman;
    }

    /**
     * set the manageman - 负责人姓名
     */
    public void setManageman(String manageman) {
        this.manageman = manageman;
    }

    /**
     * get the managephone - 负责人电话
     * @return the managephone
     */
    public String getManagephone() {
        return this.managephone;
    }

    /**
     * set the managephone - 负责人电话
     */
    public void setManagephone(String managephone) {
        this.managephone = managephone;
    }

    /**
     * get the finishtime - 完成时间
     * @return the finishtime
     */
    public Integer getFinishtime() {
        return this.finishtime;
    }

    /**
     * set the finishtime - 完成时间
     */
    public void setFinishtime(Integer finishtime) {
        this.finishtime = finishtime;
    }

    /**
     * get the contentdesc - 整改内容
     * @return the contentdesc
     */
    public String getContentdesc() {
        return this.contentdesc;
    }

    /**
     * set the contentdesc - 整改内容
     */
    public void setContentdesc(String contentdesc) {
        this.contentdesc = contentdesc;
    }

    /**
     * get the resultstatus - 总体状态
     * @return the resultstatus
     */
    public Integer getResultstatus() {
        return this.resultstatus;
    }

    /**
     * set the resultstatus - 总体状态
     */
    public void setResultstatus(Integer resultstatus) {
        this.resultstatus = resultstatus;
    }

    /**
     * get the auditstatus - 审核状态
     * @return the auditstatus
     */
    public Integer getAuditstatus() {
        return this.auditstatus;
    }

    /**
     * set the auditstatus - 审核状态
     */
    public void setAuditstatus(Integer auditstatus) {
        this.auditstatus = auditstatus;
    }

    /**
     * get the auditcontent - 审核内容
     * @return the auditcontent
     */
    public String getAuditcontent() {
        return this.auditcontent;
    }

    /**
     * set the auditcontent - 审核内容
     */
    public void setAuditcontent(String auditcontent) {
        this.auditcontent = auditcontent;
    }

    /**
     * get the writeman - 登记人
     * @return the writeman
     */
    public String getWriteman() {
        return this.writeman;
    }

    /**
     * set the writeman - 登记人
     */
    public void setWriteman(String writeman) {
        this.writeman = writeman;
    }

    /**
     * get the writeip - 登记IP
     * @return the writeip
     */
    public String getWriteip() {
        return this.writeip;
    }

    /**
     * set the writeip - 登记IP
     */
    public void setWriteip(String writeip) {
        this.writeip = writeip;
    }

    /**
     * get the writetime 
     * @return the writetime
     */
    public Timestamp getWritetime() {
        return this.writetime;
    }

    /**
     * set the writetime 
     */
    public void setWritetime(Timestamp writetime) {
        this.writetime = writetime;
    }

    /**
     * get the auditman - 审核人
     * @return the auditman
     */
    public String getAuditman() {
        return this.auditman;
    }

    /**
     * set the auditman - 审核人
     */
    public void setAuditman(String auditman) {
        this.auditman = auditman;
    }

    /**
     * get the auditip - 审核IP
     * @return the auditip
     */
    public String getAuditip() {
        return this.auditip;
    }

    /**
     * set the auditip - 审核IP
     */
    public void setAuditip(String auditip) {
        this.auditip = auditip;
    }

    /**
     * get the audittime 
     * @return the audittime
     */
    public Timestamp getAudittime() {
        return this.audittime;
    }

    /**
     * set the audittime 
     */
    public void setAudittime(Timestamp audittime) {
        this.audittime = audittime;
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
     * get the auditfromid 
     * @return the auditfromid
     */
    public String getAuditfromid() {
        return this.auditfromid;
    }

    /**
     * set the auditfromid 
     */
    public void setAuditfromid(String auditfromid) {
        this.auditfromid = auditfromid;
    }

    /**
     * get the dangerclassfullname - 问题分类名称
     * @return the dangerclassfullname
     */
    public String getDangerclassfullname() {
        return this.dangerclassfullname;
    }

    /**
     * set the dangerclassfullname - 问题分类名称
     */
    public void setDangerclassfullname(String dangerclassfullname) {
        this.dangerclassfullname = dangerclassfullname;
    }

    /**
     * get the dangerclasstypecode - 问题分类类型的代码
     * @return the dangerclasstypecode
     */
    public String getDangerclasstypecode() {
        return this.dangerclasstypecode;
    }

    /**
     * set the dangerclasstypecode - 问题分类类型的代码
     */
    public void setDangerclasstypecode(String dangerclasstypecode) {
        this.dangerclasstypecode = dangerclasstypecode;
    }

    /**
     * get the dangerclasstypename - 问题分类类型的名称
     * @return the dangerclasstypename
     */
    public String getDangerclasstypename() {
        return this.dangerclasstypename;
    }

    /**
     * set the dangerclasstypename - 问题分类类型的名称
     */
    public void setDangerclasstypename(String dangerclasstypename) {
        this.dangerclasstypename = dangerclasstypename;
    }

    /**
     * get the localtypecode - 本院问题分类的代码
     * @return the localtypecode
     */
    public String getLocaltypecode() {
        return this.localtypecode;
    }

    /**
     * set the localtypecode - 本院问题分类的代码
     */
    public void setLocaltypecode(String localtypecode) {
        this.localtypecode = localtypecode;
    }

    
    
    
    
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDangerresultid(NumberUtils.toInteger(StringUtils.toString(map.get("dangerresultid")), dangerresultid));
        setDangerid(NumberUtils.toInteger(StringUtils.toString(map.get("dangerid")), dangerid));
        setManageman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manageman")), manageman));
        setManagephone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managephone")), managephone));
        setFinishtime(NumberUtils.toInteger(StringUtils.toString(map.get("finishtime")), finishtime));
        setContentdesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contentdesc")), contentdesc));
        setResultstatus(NumberUtils.toInteger(StringUtils.toString(map.get("resultstatus")), resultstatus));
        setAuditstatus(NumberUtils.toInteger(StringUtils.toString(map.get("auditstatus")), auditstatus));
        setAuditcontent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditcontent")), auditcontent));
        setWriteman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("writeman")), writeman));
        setWriteip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("writeip")), writeip));
        setWritetime(DateUtils.toTimestamp(StringUtils.toString(map.get("writetime"))));
        setAuditman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditman")), auditman));
        setAuditip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditip")), auditip));
        setAudittime(DateUtils.toTimestamp(StringUtils.toString(map.get("audittime"))));
        setGlobalid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("globalid")), globalid));
        setAuditfromid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditfromid")), auditfromid));
        setDangerclassfullname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclassfullname")), dangerclassfullname));
        setDangerclasstypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclasstypecode")), dangerclasstypecode));
        setDangerclasstypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclasstypename")), dangerclasstypename));
        setLocaltypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("localtypecode")), localtypecode));
        setTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("time")), time));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setContentType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contentType")), contentType));
        setParam(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("param")), param));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("dangerresultid",StringUtils.toString(dangerresultid, eiMetadata.getMeta("dangerresultid")));
        map.put("dangerid",StringUtils.toString(dangerid, eiMetadata.getMeta("dangerid")));
        map.put("manageman",StringUtils.toString(manageman, eiMetadata.getMeta("manageman")));
        map.put("managephone",StringUtils.toString(managephone, eiMetadata.getMeta("managephone")));
        map.put("finishtime",StringUtils.toString(finishtime, eiMetadata.getMeta("finishtime")));
        map.put("contentdesc",StringUtils.toString(contentdesc, eiMetadata.getMeta("contentdesc")));
        map.put("resultstatus",StringUtils.toString(resultstatus, eiMetadata.getMeta("resultstatus")));
        map.put("auditstatus",StringUtils.toString(auditstatus, eiMetadata.getMeta("auditstatus")));
        map.put("auditcontent",StringUtils.toString(auditcontent, eiMetadata.getMeta("auditcontent")));
        map.put("writeman",StringUtils.toString(writeman, eiMetadata.getMeta("writeman")));
        map.put("writeip",StringUtils.toString(writeip, eiMetadata.getMeta("writeip")));
        map.put("writetime",StringUtils.toString(writetime, eiMetadata.getMeta("writetime")));
        map.put("auditman",StringUtils.toString(auditman, eiMetadata.getMeta("auditman")));
        map.put("auditip",StringUtils.toString(auditip, eiMetadata.getMeta("auditip")));
        map.put("audittime",StringUtils.toString(audittime, eiMetadata.getMeta("audittime")));
        map.put("globalid",StringUtils.toString(globalid, eiMetadata.getMeta("globalid")));
        map.put("auditfromid",StringUtils.toString(auditfromid, eiMetadata.getMeta("auditfromid")));
        map.put("dangerclassfullname",StringUtils.toString(dangerclassfullname, eiMetadata.getMeta("dangerclassfullname")));
        map.put("dangerclasstypecode",StringUtils.toString(dangerclasstypecode, eiMetadata.getMeta("dangerclasstypecode")));
        map.put("dangerclasstypename",StringUtils.toString(dangerclasstypename, eiMetadata.getMeta("dangerclasstypename")));
        map.put("localtypecode",StringUtils.toString(localtypecode, eiMetadata.getMeta("localtypecode")));
        map.put("time",StringUtils.toString(time, eiMetadata.getMeta("time")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("contentType",StringUtils.toString(contentType, eiMetadata.getMeta("contentType")));
        map.put("param",StringUtils.toString(param, eiMetadata.getMeta("param")));
        return map;
    }
}