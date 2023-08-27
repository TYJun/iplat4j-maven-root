/**
* Generate time : 2021-04-06 16:18:53
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 合同主表费用实体类
 * 
 * @Title: ContractBillCost.java
 * @ClassName: ContractBillCost
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:41:16
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractBillCost extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevsior = " ";		
    private String recReviseTime = " ";		
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String contNo = " ";		/* 合同号*/
    private String subNo = " ";		
    private String contCostNum = " ";		/* 合同费用编码*/
    private String contCostName = " ";		/* 合同费用名称*/
    private Double costAmount = new Double(0.00);		/* 费用总计*/
    private String payTime = " ";		/* 合同付款时间*/
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

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("subNo");
        eiColumn.setDescName("子项号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contCostNum");
        eiColumn.setDescName("合同费用编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contCostName");
        eiColumn.setDescName("合同费用名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("costAmount");
        eiColumn.setDescName("费用总计");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payTime");
        eiColumn.setDescName("合同付款时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ContractBillCost() {
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
     * get the id - 主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the contNo - 合同号
     * @return the contNo
     */
    public String getContNo() {
        return this.contNo;
    }

    /**
     * set the contNo - 合同号
     */
    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    /**
     * get the subNo 
     * @return the subNo
     */
    public String getSubNo() {
        return this.subNo;
    }

    /**
     * set the subNo 
     */
    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    /**
     * get the contCostNum - 合同费用编码
     * @return the contCostNum
     */
    public String getContCostNum() {
        return this.contCostNum;
    }

    /**
     * set the contCostNum - 合同费用编码
     */
    public void setContCostNum(String contCostNum) {
        this.contCostNum = contCostNum;
    }

    /**
     * get the contCostName - 合同费用名称
     * @return the contCostName
     */
    public String getContCostName() {
        return this.contCostName;
    }

    /**
     * set the contCostName - 合同费用名称
     */
    public void setContCostName(String contCostName) {
        this.contCostName = contCostName;
    }

    /**
     * get the costAmount - 费用总计
     * @return the costAmount
     */
    public Double getCostAmount() {
        return this.costAmount;
    }

    /**
     * set the costAmount - 费用总计
     */
    public void setCostAmount(Double costAmount) {
        this.costAmount = costAmount;
    }

    /**
     * get the payTime - 合同付款时间
     * @return the payTime
     */
    public String getPayTime() {
        return this.payTime;
    }

    /**
     * set the payTime - 合同付款时间
     */
    public void setPayTime(String payTime) {
        this.payTime = payTime;
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
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setSubNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("subNo")), subNo));
        setContCostNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contCostNum")), contCostNum));
        setContCostName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contCostName")), contCostName));
        setCostAmount(NumberUtils.toDouble(StringUtils.toString(map.get("costAmount")), costAmount));
        setPayTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payTime")), payTime));
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
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("subNo",StringUtils.toString(subNo, eiMetadata.getMeta("subNo")));
        map.put("contCostNum",StringUtils.toString(contCostNum, eiMetadata.getMeta("contCostNum")));
        map.put("contCostName",StringUtils.toString(contCostName, eiMetadata.getMeta("contCostName")));
        map.put("costAmount",StringUtils.toString(costAmount, eiMetadata.getMeta("costAmount")));
        map.put("payTime",StringUtils.toString(payTime, eiMetadata.getMeta("payTime")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }
}