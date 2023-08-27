/**
* Generate time : 2021-05-07 23:35:56
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.di.jz.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.DateUtils;
import java.sql.Timestamp;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DiTaskitem
* 
*/
public class DiTaskitem extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private Integer itemid = new Integer(0);		/* 主键*/
    private String taskid = " ";		/* 任务实绩的ID号*/
    private String jitemname = " ";		/* 作业项目*/
    private String itemdesc = " ";		/* 作业说明*/
    private String referencevalue = " ";		/* 参考值*/
    private String errorcontent = " ";		/* 异常故障描述*/
    private String writevalue = " ";		/* 巡检录入值*/
    private Integer successflag = new Integer(0);		/* 状态,-1代表异常，2停用，1代表正常*/
    private Timestamp writetime ;		/* 最后登记时间*/
    private String writeman = " ";		/* 最后登记人*/
    private String writeip = " ";		/* 最后登记IP*/
    private Integer sortno = new Integer(0);		/* 排序说明*/
    private String itemidXmid = " ";		
    private String exceptionStatus = " ";		/* 异常状态,0--待处理,1--已处理*/
    private String exceptionResult = " ";		/* 异常处理结果*/
    private String actualvalueunit = " ";		/* 巡检实际值单位*/
    private String needphoto = " ";		/* 是否需要拍照*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("itemid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taskid");
        eiColumn.setDescName("任务实绩的ID号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jitemname");
        eiColumn.setDescName("作业项目");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemdesc");
        eiColumn.setDescName("作业说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("referencevalue");
        eiColumn.setDescName("参考值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("errorcontent");
        eiColumn.setDescName("异常故障描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("writevalue");
        eiColumn.setDescName("巡检录入值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("successflag");
        eiColumn.setDescName("状态,-1代表异常，2停用，1代表正常");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("writetime");
        eiColumn.setDescName("最后登记时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("writeman");
        eiColumn.setDescName("最后登记人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("writeip");
        eiColumn.setDescName("最后登记IP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortno");
        eiColumn.setDescName("排序说明");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemidXmid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("exceptionStatus");
        eiColumn.setDescName("异常状态,0--待处理,1--已处理");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("exceptionResult");
        eiColumn.setDescName("异常处理结果");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("actualvalueunit");
        eiColumn.setDescName("巡检实际值单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needphoto");
        eiColumn.setDescName("是否需要拍照");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DiTaskitem() {
        initMetaData();
    }

    /**
     * get the itemid - 主键
     * @return the itemid
     */
    public Integer getItemid() {
        return this.itemid;
    }

    /**
     * set the itemid - 主键
     */
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
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
     * get the jitemname - 作业项目
     * @return the jitemname
     */
    public String getJitemname() {
        return this.jitemname;
    }

    /**
     * set the jitemname - 作业项目
     */
    public void setJitemname(String jitemname) {
        this.jitemname = jitemname;
    }

    /**
     * get the itemdesc - 作业说明
     * @return the itemdesc
     */
    public String getItemdesc() {
        return this.itemdesc;
    }

    /**
     * set the itemdesc - 作业说明
     */
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    /**
     * get the referencevalue - 参考值
     * @return the referencevalue
     */
    public String getReferencevalue() {
        return this.referencevalue;
    }

    /**
     * set the referencevalue - 参考值
     */
    public void setReferencevalue(String referencevalue) {
        this.referencevalue = referencevalue;
    }

    /**
     * get the errorcontent - 异常故障描述
     * @return the errorcontent
     */
    public String getErrorcontent() {
        return this.errorcontent;
    }

    /**
     * set the errorcontent - 异常故障描述
     */
    public void setErrorcontent(String errorcontent) {
        this.errorcontent = errorcontent;
    }

    /**
     * get the writevalue - 巡检录入值
     * @return the writevalue
     */
    public String getWritevalue() {
        return this.writevalue;
    }

    /**
     * set the writevalue - 巡检录入值
     */
    public void setWritevalue(String writevalue) {
        this.writevalue = writevalue;
    }

    /**
     * get the successflag - 状态,-1代表异常，2停用，1代表正常
     * @return the successflag
     */
    public Integer getSuccessflag() {
        return this.successflag;
    }

    /**
     * set the successflag - 状态,-1代表异常，2停用，1代表正常
     */
    public void setSuccessflag(Integer successflag) {
        this.successflag = successflag;
    }

    /**
     * get the writetime - 最后登记时间
     * @return the writetime
     */
    public Timestamp getWritetime() {
        return this.writetime;
    }

    /**
     * set the writetime - 最后登记时间
     */
    public void setWritetime(Timestamp writetime) {
        this.writetime = writetime;
    }

    /**
     * get the writeman - 最后登记人
     * @return the writeman
     */
    public String getWriteman() {
        return this.writeman;
    }

    /**
     * set the writeman - 最后登记人
     */
    public void setWriteman(String writeman) {
        this.writeman = writeman;
    }

    /**
     * get the writeip - 最后登记IP
     * @return the writeip
     */
    public String getWriteip() {
        return this.writeip;
    }

    /**
     * set the writeip - 最后登记IP
     */
    public void setWriteip(String writeip) {
        this.writeip = writeip;
    }

    /**
     * get the sortno - 排序说明
     * @return the sortno
     */
    public Integer getSortno() {
        return this.sortno;
    }

    /**
     * set the sortno - 排序说明
     */
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    /**
     * get the itemidXmid 
     * @return the itemidXmid
     */
    public String getItemidXmid() {
        return this.itemidXmid;
    }

    /**
     * set the itemidXmid 
     */
    public void setItemidXmid(String itemidXmid) {
        this.itemidXmid = itemidXmid;
    }

    /**
     * get the exceptionStatus - 异常状态,0--待处理,1--已处理
     * @return the exceptionStatus
     */
    public String getExceptionStatus() {
        return this.exceptionStatus;
    }

    /**
     * set the exceptionStatus - 异常状态,0--待处理,1--已处理
     */
    public void setExceptionStatus(String exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    /**
     * get the exceptionResult - 异常处理结果
     * @return the exceptionResult
     */
    public String getExceptionResult() {
        return this.exceptionResult;
    }

    /**
     * set the exceptionResult - 异常处理结果
     */
    public void setExceptionResult(String exceptionResult) {
        this.exceptionResult = exceptionResult;
    }

    /**
     * get the actualvalueunit - 巡检实际值单位
     * @return the actualvalueunit
     */
    public String getActualvalueunit() {
        return this.actualvalueunit;
    }

    /**
     * set the actualvalueunit - 巡检实际值单位
     */
    public void setActualvalueunit(String actualvalueunit) {
        this.actualvalueunit = actualvalueunit;
    }

    /**
     * get the needphoto - 是否需要拍照
     * @return the needphoto
     */
    public String getNeedphoto() {
        return this.needphoto;
    }

    /**
     * set the needphoto - 是否需要拍照
     */
    public void setNeedphoto(String needphoto) {
        this.needphoto = needphoto;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setItemid(NumberUtils.toInteger(StringUtils.toString(map.get("itemid")), itemid));
        setTaskid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taskid")), taskid));
        setJitemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jitemname")), jitemname));
        setItemdesc(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemdesc")), itemdesc));
        setReferencevalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("referencevalue")), referencevalue));
        setErrorcontent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("errorcontent")), errorcontent));
        setWritevalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("writevalue")), writevalue));
        setSuccessflag(NumberUtils.toInteger(StringUtils.toString(map.get("successflag")), successflag));
        setWritetime(DateUtils.toTimestamp(StringUtils.toString(map.get("writetime"))));
        setWriteman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("writeman")), writeman));
        setWriteip(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("writeip")), writeip));
        setSortno(NumberUtils.toInteger(StringUtils.toString(map.get("sortno")), sortno));
        setItemidXmid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemidXmid")), itemidXmid));
        setExceptionStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exceptionStatus")), exceptionStatus));
        setExceptionResult(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exceptionResult")), exceptionResult));
        setActualvalueunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("actualvalueunit")), actualvalueunit));
        setNeedphoto(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needphoto")), needphoto));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap();
        map.put("itemid",StringUtils.toString(itemid, eiMetadata.getMeta("itemid")));
        map.put("taskid",StringUtils.toString(taskid, eiMetadata.getMeta("taskid")));
        map.put("jitemname",StringUtils.toString(jitemname, eiMetadata.getMeta("jitemname")));
        map.put("itemdesc",StringUtils.toString(itemdesc, eiMetadata.getMeta("itemdesc")));
        map.put("referencevalue",StringUtils.toString(referencevalue, eiMetadata.getMeta("referencevalue")));
        map.put("errorcontent",StringUtils.toString(errorcontent, eiMetadata.getMeta("errorcontent")));
        map.put("writevalue",StringUtils.toString(writevalue, eiMetadata.getMeta("writevalue")));
        map.put("successflag",StringUtils.toString(successflag, eiMetadata.getMeta("successflag")));
        map.put("writetime",StringUtils.toString(writetime, eiMetadata.getMeta("writetime")));
        map.put("writeman",StringUtils.toString(writeman, eiMetadata.getMeta("writeman")));
        map.put("writeip",StringUtils.toString(writeip, eiMetadata.getMeta("writeip")));
        map.put("sortno",StringUtils.toString(sortno, eiMetadata.getMeta("sortno")));
        map.put("itemidXmid",StringUtils.toString(itemidXmid, eiMetadata.getMeta("itemidXmid")));
        map.put("exceptionStatus",StringUtils.toString(exceptionStatus, eiMetadata.getMeta("exceptionStatus")));
        map.put("exceptionResult",StringUtils.toString(exceptionResult, eiMetadata.getMeta("exceptionResult")));
        map.put("actualvalueunit",StringUtils.toString(actualvalueunit, eiMetadata.getMeta("actualvalueunit")));
        map.put("needphoto",StringUtils.toString(needphoto, eiMetadata.getMeta("needphoto")));
        return map;
    }
}