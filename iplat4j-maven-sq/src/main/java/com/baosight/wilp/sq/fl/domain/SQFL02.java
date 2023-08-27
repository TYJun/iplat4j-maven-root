/**
* Generate time : 2021-07-26 16:49:00
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.sq.fl.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* SqPersonnelGroup
* 
*/
public class SQFL02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 人员组id*/
    private String perGroupNo = " ";		/* 人员组编号*/
    private String perGroupName = " ";		/* 人员组名称*/
    private String workNo = " ";		/* 人员编号*/
    private String name = " ";		/* 人员名称*/
    private String deptNum = " ";		/* 科室编号*/
    private String deptName = " ";		/* 科室名称*/
    private String createtime = " ";		/* 创建时间*/
    private String post = " ";		/* 修改时间*/
    private String userId = " ";   /* 分组id*/
    private String deptPost = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("人员组id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("perGroupNo");
        eiColumn.setDescName("人员组编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("perGroupName");
        eiColumn.setDescName("人员组名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("人员编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("人员名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptNum");
        eiColumn.setDescName("科室编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("post");
        eiColumn.setDescName("职务");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userId");
        eiColumn.setDescName("企业微信登录号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptPost");
        eiColumn.setDescName("企业微信登录号");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public SQFL02() {
        initMetaData();
    }

    /**
     * get the id - 人员组id
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 人员组id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the perGroupNo - 人员组编号
     * @return the perGroupNo
     */
    public String getPerGroupNo() {
        return this.perGroupNo;
    }

    /**
     * set the perGroupNo - 人员组编号
     */
    public void setPerGroupNo(String perGroupNo) {
        this.perGroupNo = perGroupNo;
    }

    /**
     * get the perGroupName - 人员组名称
     * @return the perGroupName
     */
    public String getPerGroupName() {
        return this.perGroupName;
    }

    /**
     * set the perGroupName - 人员组名称
     */
    public void setPerGroupName(String perGroupName) {
        this.perGroupName = perGroupName;
    }

    /**
     * get the workNo - 人员编号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 人员编号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the name - 人员名称
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 人员名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the deptNum - 科室编号
     * @return the deptNum
     */
    public String getDeptNum() {
        return this.deptNum;
    }

    /**
     * set the deptNum - 科室编号
     */
    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    /**
     * get the deptName - 科室名称
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 科室名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the createtime - 创建时间
     * @return the createtime
     */
    public String getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime - 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * get the updatetime - 修改时间
     * @return the updatetime
     */
    public String getPost() {
        return this.post;
    }

    /**
     * set the updatetime - 修改时间
     */
    public void setPost(String post) {
        this.post = post;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptPost() {
        return deptPost;
    }

    public void setDeptPost(String deptPost) {
        this.deptPost = deptPost;
    }

    /**
     * 将Map中的值存入实体
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setPerGroupNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("perGroupNo")), perGroupNo));
        setPerGroupName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("perGroupName")), perGroupName));
        setWorkNo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workNo")), workNo));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setDeptNum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptNum")), deptNum));
        setDeptName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptName")), deptName));
        setCreatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createtime")), createtime));
        setPost(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("post")), post));
        setUserId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userId")), userId));
        setDeptPost(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deptPost")), deptPost));
    }

    /**
     * 将值塞到Map中
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("perGroupNo",StringUtils.toString(perGroupNo, eiMetadata.getMeta("perGroupNo")));
        map.put("perGroupName",StringUtils.toString(perGroupName, eiMetadata.getMeta("perGroupName")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("deptNum",StringUtils.toString(deptNum, eiMetadata.getMeta("deptNum")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("post",StringUtils.toString(post, eiMetadata.getMeta("post")));
        map.put("userId",StringUtils.toString(userId, eiMetadata.getMeta("userId")));
        map.put("deptPost",StringUtils.toString(deptPost, eiMetadata.getMeta("deptPost")));
        return map;
    }
}