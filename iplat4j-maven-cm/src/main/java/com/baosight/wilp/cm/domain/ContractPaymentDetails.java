/**
* Generate time : 2021-03-30 17:45:14
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 合同协议详情表实体类
 * 
 * @Title: ContractPaymentDetails.java
 * @ClassName: ContractPaymentDetails
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:42:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractPaymentDetails extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevsior = " ";		
    private String recReviseTime = " ";		
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		
    private String payTypeNum = " ";		/* 付款类型编码*/
    private Integer no = new Integer(0);		/* 序号*/
    private Integer lastTime = new Integer(0);		/* 距离上次付款时间*/
    private String payRate = " ";		/* 付款比例*/
    private String remark = " ";		/* 备注*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevsior");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payTypeNum");
        eiColumn.setDescName("付款类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("no");
        eiColumn.setDescName("序号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lastTime");
        eiColumn.setDescName("距离上次付款时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payRate");
        eiColumn.setDescName("付款比例");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ContractPaymentDetails() {
        initMetaData();
    }

    /**
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevsior 
     * @return the recRevsior
     */
    public String getRecRevsior() {
        return this.recRevsior;
    }

    /**
     * set the recRevsior 
     */
    public void setRecRevsior(String recRevsior) {
        this.recRevsior = recRevsior;
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
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
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
     * get the payTypeNum - 付款类型编码
     * @return the payTypeNum
     */
    public String getPayTypeNum() {
        return this.payTypeNum;
    }

    /**
     * set the payTypeNum - 付款类型编码
     */
    public void setPayTypeNum(String payTypeNum) {
        this.payTypeNum = payTypeNum;
    }

    /**
     * get the no - 序号
     * @return the no
     */
    public Integer getNo() {
        return this.no;
    }

    /**
     * set the no - 序号
     */
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * get the lastTime - 距离上次付款时间
     * @return the lastTime
     */
    public Integer getLastTime() {
        return this.lastTime;
    }

    /**
     * set the lastTime - 距离上次付款时间
     */
    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * get the payRate - 付款比例
     * @return the payRate
     */
    public String getPayRate() {
        return this.payRate;
    }

    /**
     * set the payRate - 付款比例
     */
    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevsior(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevsior")), recRevsior));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setPayTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payTypeNum")), payTypeNum));
        setNo(NumberUtils.toInteger(StringUtils.toString(map.get("no")), no));
        setLastTime(NumberUtils.toInteger(StringUtils.toString(map.get("lastTime")), lastTime));
        setPayRate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payRate")), payRate));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevsior",StringUtils.toString(recRevsior, eiMetadata.getMeta("recRevsior")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("payTypeNum",StringUtils.toString(payTypeNum, eiMetadata.getMeta("payTypeNum")));
        map.put("no",StringUtils.toString(no, eiMetadata.getMeta("no")));
        map.put("lastTime",StringUtils.toString(lastTime, eiMetadata.getMeta("lastTime")));
        map.put("payRate",StringUtils.toString(payRate, eiMetadata.getMeta("payRate")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }
}