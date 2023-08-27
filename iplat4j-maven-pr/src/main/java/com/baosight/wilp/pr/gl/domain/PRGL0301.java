/**
* Generate time : 2021-04-22 14:39:36
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
* TSaftyDangerResult
* 
*/
public class PRGL0301 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer dangerresultid = new Integer(0);
    private String dangerid = "";		/* 所属的隐患*/
    private String id = "";		/* 主键*/
    private String manageman = " ";		/* 负责人姓名*/
    private String managephone = " ";		/* 负责人电话*/
    private String finishtime = "";		/* 完成时间*/
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
    private String dangerWhere = " ";		
    private String paramValue1 = " ";		
    private String requiredTime = " ";		
    private String requireDesc = " ";		
    private String fileName = " ";		
    private String filePic = " ";		
    private String contentdesc2 = " ";		
    private String rectifyfileName = " ";		
    private String rectifyfilePic = " ";		
    private String fileID = " ";		
    private String storagePath = " ";		
    
    
    
    

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

        eiColumn = new EiColumn("id");
        eiColumn.setDescName("主键");
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
        
        eiColumn = new EiColumn("dangerWhere");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("paramValue1");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("requiredTime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("requireDesc");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("fileName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("filePic");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("contentdesc2");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("rectifyfileName");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("rectifyfilePic");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("fileID");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("storagePath");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public PRGL0301() {
        initMetaData();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String getDangerid() {
        return this.dangerid;
    }

    /**
     * set the dangerid - 所属的隐患
     */
    public void setDangerid(String dangerid) {
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
    public String getFinishtime() {
        return this.finishtime;
    }

    /**
     * set the finishtime - 完成时间
     */
    public void setFinishtime(String finishtime) {
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
    
    public String getDangerWhere() {
        return dangerWhere;
    }

    public void setDangerWhere(String dangerWhere) {
        this.dangerWhere = dangerWhere;
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

    public String getRequireDesc() {
        return requireDesc;
    }

    public void setRequireDesc(String requireDesc) {
        this.requireDesc = requireDesc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFilePic() {
        return filePic;
    }

    public void setFilePic(String filePic) {
        this.filePic = filePic;
    }
    
    public String getContentdesc2() {
        return contentdesc2;
    }

    public void setContentdesc2(String contentdesc2) {
        this.contentdesc2 = contentdesc2;
    }
    
    public String getRectifyfileName() {
        return rectifyfileName;
    }

    public void setRectifyfileName(String rectifyfileName) {
        this.rectifyfileName = rectifyfileName;
    }

    public String getRectifyfilePic() {
        return rectifyfilePic;
    }

    public void setRectifyfilePic(String rectifyfilePic) {
        this.rectifyfilePic = rectifyfilePic;
    }
    
    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDangerresultid(NumberUtils.toInteger(StringUtils.toString(map.get("dangerresultid")), dangerresultid));
        setDangerid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerid")), dangerid));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setManageman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manageman")), manageman));
        setManagephone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("managephone")), managephone));
        setFinishtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finishtime")), finishtime));
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
        setDangerWhere(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerWhere")), dangerWhere));
        setParamValue1(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("paramValue1")), paramValue1));
        setRequiredTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requiredTime")), requiredTime));
        setRequireDesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("requireDesc")), requireDesc));
        setFileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileName")), fileName));
        setFilePic(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filePic")), filePic));
        setContentdesc2(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contentdesc2")), contentdesc2));
        setRectifyfileName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rectifyfileName")), rectifyfileName));
        setRectifyfilePic(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rectifyfilePic")), rectifyfilePic));
        setFileID(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileID")), fileID));
        setStoragePath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("storagePath")), storagePath));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("dangerresultid",StringUtils.toString(dangerresultid, eiMetadata.getMeta("dangerresultid")));
        map.put("dangerid",StringUtils.toString(dangerid, eiMetadata.getMeta("dangerid")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
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
        map.put("dangerWhere",StringUtils.toString(dangerWhere, eiMetadata.getMeta("dangerWhere")));
        map.put("paramValue1",StringUtils.toString(paramValue1, eiMetadata.getMeta("paramValue1")));
        map.put("requiredTime",StringUtils.toString(requiredTime, eiMetadata.getMeta("requiredTime")));
        map.put("requireDesc",StringUtils.toString(requireDesc, eiMetadata.getMeta("requireDesc")));
        map.put("fileName",StringUtils.toString(fileName, eiMetadata.getMeta("fileName")));
        map.put("filePic",StringUtils.toString(filePic, eiMetadata.getMeta("filePic")));
        map.put("contentdesc2",StringUtils.toString(contentdesc2, eiMetadata.getMeta("contentdesc2")));
        map.put("rectifyfileName",StringUtils.toString(rectifyfileName, eiMetadata.getMeta("rectifyfileName")));
        map.put("rectifyfilePic",StringUtils.toString(rectifyfilePic, eiMetadata.getMeta("rectifyfilePic")));
        map.put("fileID",StringUtils.toString(fileID, eiMetadata.getMeta("fileID")));
        map.put("storagePath",StringUtils.toString(storagePath, eiMetadata.getMeta("storagePath")));
        return map;
    }
}