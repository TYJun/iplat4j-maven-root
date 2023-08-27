/**
* Generate time : 2021-03-30 18:37:38
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
 * 合同主表联系人实体类
 * 
 * @Title: ContractBillLinkman.java
 * @ClassName: ContractBillLinkman
 * @Package：com.baosight.wilp.cm.domain
 * @author: zhaow
 * @date: 2021年8月30日 下午2:41:43
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ContractBillLinkman extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		
    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevisor = " ";		
    private String recReviseTime = " ";		
    private String contNo = " ";		/* 合同号*/
    private String subNo = " ";		/* 子项号*/
    private String workNo = " ";   /* 工号*/
    private String name = " ";		/* 联系人*/
    private String number = " ";		/* 联系电话*/
    private String deptNum = " ";		/* 科室编码*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contNo");
        eiColumn.setDescName("合同号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("subNo");
        eiColumn.setDescName("子项号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("联系人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("number");
        eiColumn.setDescName("联系电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public ContractBillLinkman() {
        initMetaData();
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
     * get the subNo - 子项号
     * @return the subNo
     */
    public String getSubNo() {
        return this.subNo;
    }

    /**
     * set the subNo - 子项号
     */
    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }
    
    /**
     * get the subNo - 子项号
     * @return the subNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the subNo - 子项号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the name - 联系人
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 联系人
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the number - 联系电话
     * @return the number
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * set the number - 联系电话
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * get the deptNum - 科室编码
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编码
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setContNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contNo")), contNo));
        setSubNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("subNo")), subNo));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("number")), number));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("contNo",StringUtils.toString(contNo, eiMetadata.getMeta("contNo")));
        map.put("subNo",StringUtils.toString(subNo, eiMetadata.getMeta("subNo")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("number",StringUtils.toString(number, eiMetadata.getMeta("number")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        return map;
    }
}