/**
* Generate time : 2021-02-20 17:36:11
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
 * 合同费用表实体类
 * 
 * @Title: ContractCost.java
 * @ClassName: ContractCost
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:42:31
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractCost extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevsior = " ";		
    private String recReviseTime = " ";		
    private String archiveFlag = " ";		/* 归档标记*/
    private String id = " ";		/* 主键*/
    private String contCostNum = " ";		/* 合同费用编码*/
    private String contCostName = " ";		/* 合同费用名称*/
    private String remark = " ";		/* 备注*/
    private String flag = "0";		
    private Integer checknum = new Integer(0);		/* 选择次数*/

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

        eiColumn = new EiColumn("contCostNum");
        eiColumn.setDescName("合同费用编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contCostName");
        eiColumn.setDescName("合同费用名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("flag");
        eiColumn.setDescName("标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checknum");
        eiColumn.setDescName("选择次数");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ContractCost() {
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
     * get the flag 
     * @return the flag
     */
    public String getFlag() {
        return this.flag;
    }

    /**
     * set the flag 
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * get the checknum - 选择次数
     * @return the checknum
     */
    public Integer getChecknum() {
        return this.checknum;
    }

    /**
     * set the checknum - 选择次数
     */
    public void setChecknum(Integer checknum) {
        this.checknum = checknum;
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
        setContCostNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contCostNum")), contCostNum));
        setContCostName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contCostName")), contCostName));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
        setChecknum(NumberUtils.toInteger(StringUtils.toString(map.get("checknum")), checknum));
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
        map.put("contCostNum",StringUtils.toString(contCostNum, eiMetadata.getMeta("contCostNum")));
        map.put("contCostName",StringUtils.toString(contCostName, eiMetadata.getMeta("contCostName")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("flag",StringUtils.toString(flag, eiMetadata.getMeta("flag")));
        map.put("checknum",StringUtils.toString(checknum, eiMetadata.getMeta("checknum")));
        return map;
    }
}