/**
* Generate time : 2022-01-13 18:06:17
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.cm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* CmContractBillNode
* 
*/
public class CmContractBillNode extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 合同节点明细主键*/
    private String contPk = " ";		/* 合同节点主键*/
    private String nodeAutoNo = " ";		/* 节点编码*/
    private String nodeName = " ";		/* 节点名称*/
    private String nodeRemark = " ";		/* 节点备注*/
    private String no = " ";		/* 序号*/
    private String nodeTime = " ";		/* 节点时间*/
    private String remark = " ";		/* 备注*/
    private String recCreatorNo = " ";		/* 创建人工号*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("合同节点明细主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("contPk");
        eiColumn.setDescName("合同主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeAutoNo");
        eiColumn.setDescName("节点编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeName");
        eiColumn.setDescName("节点名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeRemark");
        eiColumn.setDescName("节点备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("no");
        eiColumn.setDescName("序号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nodeTime");
        eiColumn.setDescName("节点时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreatorNo");
        eiColumn.setDescName("创建人工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public CmContractBillNode() {
        initMetaData();
    }

    /**
     * get the id - 合同节点明细主键
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 合同节点明细主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the nodePk - 合同节点主键
     * @return the nodePk
     */
    public String getContPk() {
        return this.contPk;
    }

    /**
     * set the nodePk - 合同节点主键
     */
    public void setContPk(String contPk) {
        this.contPk = contPk;
    }

    /**
     * get the nodeAutoNo - 节点编码
     * @return the nodeAutoNo
     */
    public String getNodeAutoNo() {
        return this.nodeAutoNo;
    }

    /**
     * set the nodeAutoNo - 节点编码
     */
    public void setNodeAutoNo(String nodeAutoNo) {
        this.nodeAutoNo = nodeAutoNo;
    }

    /**
     * get the nodeName - 节点名称
     * @return the nodeName
     */
    public String getNodeName() {
        return this.nodeName;
    }

    /**
     * set the nodeName - 节点名称
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * get the nodeRemark - 节点备注
     * @return the nodeRemark
     */
    public String getNodeRemark() {
        return this.nodeRemark;
    }

    /**
     * set the nodeRemark - 节点备注
     */
    public void setNodeRemark(String nodeRemark) {
        this.nodeRemark = nodeRemark;
    }

    /**
     * get the no - 序号
     * @return the no
     */
    public String getNo() {
        return this.no;
    }

    /**
     * set the no - 序号
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * get the nodeTime - 节点时间
     * @return the nodeTime
     */
    public String getNodeTime() {
        return this.nodeTime;
    }

    /**
     * set the nodeTime - 节点时间
     */
    public void setNodeTime(String nodeTime) {
        this.nodeTime = nodeTime;
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
     * get the recCreatorNo - 创建人工号
     * @return the recCreatorNo
     */
    public String getRecCreatorNo() {
        return this.recCreatorNo;
    }

    /**
     * set the recCreatorNo - 创建人工号
     */
    public void setRecCreatorNo(String recCreatorNo) {
        this.recCreatorNo = recCreatorNo;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setContPk(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contPk")), contPk));
        setNodeAutoNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeAutoNo")), nodeAutoNo));
        setNodeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeName")), nodeName));
        setNodeRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeRemark")), nodeRemark));
        setNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("no")), no));
        setNodeTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nodeTime")), nodeTime));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setRecCreatorNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreatorNo")), recCreatorNo));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("contPk",StringUtils.toString(contPk, eiMetadata.getMeta("contPk")));
        map.put("nodeAutoNo",StringUtils.toString(nodeAutoNo, eiMetadata.getMeta("nodeAutoNo")));
        map.put("nodeName",StringUtils.toString(nodeName, eiMetadata.getMeta("nodeName")));
        map.put("nodeRemark",StringUtils.toString(nodeRemark, eiMetadata.getMeta("nodeRemark")));
        map.put("no",StringUtils.toString(no, eiMetadata.getMeta("no")));
        map.put("nodeTime",StringUtils.toString(nodeTime, eiMetadata.getMeta("nodeTime")));
        map.put("remark",StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("recCreatorNo",StringUtils.toString(recCreatorNo, eiMetadata.getMeta("recCreatorNo")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        return map;
    }
}