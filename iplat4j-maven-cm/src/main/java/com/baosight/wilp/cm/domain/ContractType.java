/**
* Generate time : 2021-03-01 16:57:49
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 合同类型表实体类
 * 
 * @Title: ContractType.java
 * @ClassName: ContractType
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:43:15
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractType extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 主键*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevsior = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String contTypeNum = " ";		/* 合同类型编码*/
    private String contTypeName = " ";		/* 合同类型名称*/
    private String payType = " ";		/* 收付方向*/
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

        eiColumn = new EiColumn("contTypeNum");
        eiColumn.setDescName("合同类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contTypeName");
        eiColumn.setDescName("合同类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("payType");
        eiColumn.setDescName("收付方向");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ContractType() {
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
     * get the contTypeNum - 合同类型编码
     * @return the contTypeNum
     */
    public String getContTypeNum() {
        return this.contTypeNum;
    }

    /**
     * set the contTypeNum - 合同类型编码
     */
    public void setContTypeNum(String contTypeNum) {
        this.contTypeNum = contTypeNum;
    }

    /**
     * get the contTypeName - 合同类型名称
     * @return the contTypeName
     */
    public String getContTypeName() {
        return this.contTypeName;
    }

    /**
     * set the contTypeName - 合同类型名称
     */
    public void setContTypeName(String contTypeName) {
        this.contTypeName = contTypeName;
    }

    /**
     * get the payType - 收付方向
     * @return the payType
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * set the payType - 收付方向
     */
    public void setPayType(String payType) {
        this.payType = payType;
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
        setContTypeNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contTypeNum")), contTypeNum));
        setContTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contTypeName")), contTypeName));
        setPayType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("payType")), payType));
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
        map.put("contTypeNum",StringUtils.toString(contTypeNum, eiMetadata.getMeta("contTypeNum")));
        map.put("contTypeName",StringUtils.toString(contTypeName, eiMetadata.getMeta("contTypeName")));
        map.put("payType",StringUtils.toString(payType, eiMetadata.getMeta("payType")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }
}