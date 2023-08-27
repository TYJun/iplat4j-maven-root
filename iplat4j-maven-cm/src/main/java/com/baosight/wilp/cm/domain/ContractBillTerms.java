/**
* Generate time : 2021-03-30 18:27:53
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 
 * 合同主表条款实体类
 * 
 * @Title: ContractBillTerms.java
 * @ClassName: ContractBillTerms
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:42:12
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractBillTerms extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevsior = " ";		
    private String recReviseTime = " ";		
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String contNo = " ";		/* 合同号*/
    private String subNo = " ";		
    private String contTermNum = " ";		/* 合同条款编码*/
    private String contTermName = " ";		/* 合同条款名称*/
    private String content = " ";		
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

        eiColumn = new EiColumn("contTermNum");
        eiColumn.setDescName("合同条款编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contTermName");
        eiColumn.setDescName("合同条款名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("content");
        eiColumn.setDescName("内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ContractBillTerms() {
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
     * get the contTermNum - 合同条款编码
     * @return the contTermNum
     */
    public String getContTermNum() {
        return this.contTermNum;
    }

    /**
     * set the contTermNum - 合同条款编码
     */
    public void setContTermNum(String contTermNum) {
        this.contTermNum = contTermNum;
    }

    /**
     * get the contTermName - 合同条款名称
     * @return the contTermName
     */
    public String getContTermName() {
        return this.contTermName;
    }

    /**
     * set the contTermName - 合同条款名称
     */
    public void setContTermName(String contTermName) {
        this.contTermName = contTermName;
    }

    /**
     * get the content 
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * set the content 
     */
    public void setContent(String content) {
        this.content = content;
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
        setContTermNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contTermNum")), contTermNum));
        setContTermName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contTermName")), contTermName));
        setContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("content")), content));
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
        map.put("contTermNum",StringUtils.toString(contTermNum, eiMetadata.getMeta("contTermNum")));
        map.put("contTermName",StringUtils.toString(contTermName, eiMetadata.getMeta("contTermName")));
        map.put("content",StringUtils.toString(content, eiMetadata.getMeta("content")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }
}