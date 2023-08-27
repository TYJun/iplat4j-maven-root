/**
* Generate time : 2021-04-21 16:31:30
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
 * 
 * PRGL03
 * 
 * @Title: PRGL03.java
 * @ClassName: PRGL03
 * @Package：com.baosight.wilp.pr.gl.domain
 * @author: zhangjiaqian
 * @date: 2021年10月25日 下午2:19:26
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class PRGL03 extends DaoEPBase {

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
    private String writetime ;		
    private String auditman = " ";		/* 审核人*/
    private String auditip = " ";		/* 审核IP*/
    private Timestamp audittime ;		
    private String globalid = " ";		
    private String auditfromid = " ";		
    private String dangerclassfullname = " ";		/* 问题分类名称*/
    private String dangerclasstypecode = " ";		/* 问题分类类型的代码*/
    private String dangerclasstypename = " ";		/* 问题分类类型的名称*/
    private String localtypecode = " ";		/* 本院问题分类的代码*/
    private String typeName = " ";
    private String typeCode = " ";
    private String statusCode = " ";
    private String dangerCode = "";
    private String dangerWhere = "";
    private String realname = "";
    private String createTime = "";
    private String requireDesc = "";
    private String paramValue1 = "";
    private String requiredTime = "";
    private String id = "";
    
    
    
    
    
    

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

        eiColumn = new EiColumn("typeName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("typeCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dangerCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dangerWhere");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("realname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("createTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("requireDesc");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("paramValue1");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("requiredTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("statusCode");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("id");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public PRGL03() {
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
    public String getWritetime() {
        return this.writetime;
    }

    /**
     * set the writetime 
     */
    public void setWritetime(String writetime) {
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDangerCode() {
        return dangerCode;
    }

    public void setDangerCode(String dangerCode) {
        this.dangerCode = dangerCode;
    }

    public String getDangerWhere() {
        return dangerWhere;
    }

    public void setDangerWhere(String dangerWhere) {
        this.dangerWhere = dangerWhere;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRequireDesc() {
        return requireDesc;
    }

    public void setRequireDesc(String requireDesc) {
        this.requireDesc = requireDesc;
    }

    public String getParamValue1() {
        return paramValue1;
    }

    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1;
    }

    public String getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(String requiredTime) {
        this.requiredTime = requiredTime;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        setWritetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("writetime")),writetime));
        setAuditman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditman")), auditman));
        setAuditip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditip")), auditip));
        setAudittime(DateUtils.toTimestamp(StringUtils.toString(map.get("audittime"))));
        setGlobalid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("globalid")), globalid));
        setAuditfromid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditfromid")), auditfromid));
        setDangerclassfullname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclassfullname")), dangerclassfullname));
        setDangerclasstypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclasstypecode")), dangerclasstypecode));
        setDangerclasstypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerclasstypename")), dangerclasstypename));
        setLocaltypecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("localtypecode")), localtypecode));
        setTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeName")), typeName));
        setTypeCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeCode")), typeCode));
        setStatusCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("statusCode")), statusCode));
        setDangerCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerCode")), dangerCode));
        setDangerWhere(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerWhere")), dangerWhere));
        setRealname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("realname")), realname));
        setCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createTime")), createTime));
        setRequireDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requireDesc")), requireDesc));
        setParamValue1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramValue1")), paramValue1));
        setRequiredTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requiredTime")), requiredTime));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
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
        map.put("typeName",StringUtils.toString(typeName, eiMetadata.getMeta("typeName")));
        map.put("typeCode",StringUtils.toString(typeCode, eiMetadata.getMeta("typeCode")));
        map.put("statusCode",StringUtils.toString(statusCode, eiMetadata.getMeta("statusCode")));
        map.put("dangerCode",StringUtils.toString(dangerCode, eiMetadata.getMeta("dangerCode")));
        map.put("dangerWhere",StringUtils.toString(dangerWhere, eiMetadata.getMeta("dangerWhere")));
        map.put("realname",StringUtils.toString(realname, eiMetadata.getMeta("realname")));
        map.put("createTime",StringUtils.toString(createTime, eiMetadata.getMeta("createTime")));
        map.put("requireDesc",StringUtils.toString(requireDesc, eiMetadata.getMeta("requireDesc")));
        map.put("paramValue1",StringUtils.toString(paramValue1, eiMetadata.getMeta("paramValue1")));
        map.put("requiredTime",StringUtils.toString(requiredTime, eiMetadata.getMeta("requiredTime")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        return map;
    }
}