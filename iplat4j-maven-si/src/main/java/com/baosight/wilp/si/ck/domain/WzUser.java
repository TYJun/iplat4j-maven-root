/**
* Generate time : 2023-04-17 10:30:26
* Version : 6.0.731.201809200158
*/
package com.baosight.wilp.si.ck.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.si.common.SiUtils;

import java.util.HashMap;
import java.util.Map;

/**
* WzUser
* 人员对应科室表
*/
public class WzUser extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id;

    /**工号*/
    private String workNo;

    /**姓名*/
    private String name;

    /**性别*/
    private String gender;

    /**科室编码*/
    private String deptCode;

    /**所属科室ID*/
    private String deptId;

    /**所属科室*/
    private String deptName;

    /**邮箱*/
    private String mailbox;

    /**身份证号*/
    private String idNo;

    /**员工照片*/
    private String picUrl;

    /**类型*/
    private String type;

    /**是否是服务类型*/
    private String isService;

    /**状态*/
    private String status;

    /**出处*/
    private String source;

    /**CID*/
    private String cid;

    /**注销*/
    private String cancellation;

    /**创建人*/
    private String recCreater;

    /**创建时间*/
    private String recCreateTime;

    /**修改人*/
    private String recRevisor;

    /**修改时间*/
    private String recReviseTime;

    /**补贴金额0是未使用1是已使用*/
    private String subsidy = "0";

    /**平台科室id*/
    private String bizId;


    private String zao;
    private String wu;
    private String wan;
    private String post;

    /**业务科室职务*/
    private String vocationalPost;

    /**负责人*/
    private String leader;

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setDescName("主键");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workNo");
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gender");
        eiColumn.setDescName("性别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptCode");
        eiColumn.setDescName("科室编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptId");
        eiColumn.setDescName("所属科室ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("所属科室");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mailbox");
        eiColumn.setDescName("邮箱");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("idNo");
        eiColumn.setDescName("身份证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("picUrl");
        eiColumn.setDescName("员工照片");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setDescName("类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isService");
        eiColumn.setDescName("是否是服务类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("source");
        eiColumn.setDescName("出处");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cid");
        eiColumn.setDescName("CID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cancellation");
        eiColumn.setDescName("注销");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreater");
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

        eiColumn = new EiColumn("subsidy");
        eiColumn.setDescName("补贴金额0是未使用1是已使用");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("bizId");
        eiColumn.setDescName("平台科室id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("zao");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wu");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("wan");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("post");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("vocationalPost");
        eiColumn.setDescName("业务科室职务");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("leader");
        eiColumn.setDescName("负责人");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public WzUser() {
        initMetaData();
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
     * get the workNo - 工号
     * @return the workNo
     */
    public String getWorkNo() {
        return this.workNo;
    }

    /**
     * set the workNo - 工号
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /**
     * get the name - 姓名
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name - 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the gender - 性别
     * @return the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * set the gender - 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * get the deptCode - 科室编码
     * @return the deptCode
     */
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * set the deptCode - 科室编码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * get the deptId - 所属科室ID
     * @return the deptId
     */
    public String getDeptId() {
        return this.deptId;
    }

    /**
     * set the deptId - 所属科室ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * get the deptName - 所属科室
     * @return the deptName
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * set the deptName - 所属科室
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * get the mailbox - 邮箱
     * @return the mailbox
     */
    public String getMailbox() {
        return this.mailbox;
    }

    /**
     * set the mailbox - 邮箱
     */
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    /**
     * get the idNo - 身份证号
     * @return the idNo
     */
    public String getIdNo() {
        return this.idNo;
    }

    /**
     * set the idNo - 身份证号
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * get the picUrl - 员工照片
     * @return the picUrl
     */
    public String getPicUrl() {
        return this.picUrl;
    }

    /**
     * set the picUrl - 员工照片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * get the type - 类型
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * set the type - 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get the isService - 是否是服务类型
     * @return the isService
     */
    public String getIsService() {
        return this.isService;
    }

    /**
     * set the isService - 是否是服务类型
     */
    public void setIsService(String isService) {
        this.isService = isService;
    }

    /**
     * get the status - 状态
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the source - 出处
     * @return the source
     */
    public String getSource() {
        return this.source;
    }

    /**
     * set the source - 出处
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * get the cid - CID
     * @return the cid
     */
    public String getCid() {
        return this.cid;
    }

    /**
     * set the cid - CID
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * get the cancellation - 注销
     * @return the cancellation
     */
    public String getCancellation() {
        return this.cancellation;
    }

    /**
     * set the cancellation - 注销
     */
    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    /**
     * get the recCreater - 创建人
     * @return the recCreater
     */
    public String getRecCreater() {
        return this.recCreater;
    }

    /**
     * set the recCreater - 创建人
     */
    public void setRecCreater(String recCreater) {
        this.recCreater = recCreater;
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
    @Override
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    @Override
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    @Override
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the subsidy - 补贴金额0是未使用1是已使用
     * @return the subsidy
     */
    public String getSubsidy() {
        return this.subsidy;
    }

    /**
     * set the subsidy - 补贴金额0是未使用1是已使用
     */
    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    /**
     * get the bizId - 平台科室id
     * @return the bizId
     */
    public String getBizId() {
        return this.bizId;
    }

    /**
     * set the bizId - 平台科室id
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * get the zao 
     * @return the zao
     */
    public String getZao() {
        return this.zao;
    }

    /**
     * set the zao 
     */
    public void setZao(String zao) {
        this.zao = zao;
    }

    /**
     * get the wu 
     * @return the wu
     */
    public String getWu() {
        return this.wu;
    }

    /**
     * set the wu 
     */
    public void setWu(String wu) {
        this.wu = wu;
    }

    /**
     * get the wan 
     * @return the wan
     */
    public String getWan() {
        return this.wan;
    }

    /**
     * set the wan 
     */
    public void setWan(String wan) {
        this.wan = wan;
    }

    /**
     * get the post 
     * @return the post
     */
    public String getPost() {
        return this.post;
    }

    /**
     * set the post 
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * get the vocationalPost - 业务科室职务
     * @return the vocationalPost
     */
    public String getVocationalPost() {
        return this.vocationalPost;
    }

    /**
     * set the vocationalPost - 业务科室职务
     */
    public void setVocationalPost(String vocationalPost) {
        this.vocationalPost = vocationalPost;
    }

    /**
     * get the leader - 负责人
     * @return the leader
     */
    public String getLeader() {
        return this.leader;
    }

    /**
     * set the leader - 负责人
     */
    public void setLeader(String leader) {
        this.leader = leader;
    }

    /**
     * get the value from Map
     */
    @Override
    public void fromMap(Map map) {

        setId(SiUtils.isEmpty(map.get("id"), id));
        setWorkNo(SiUtils.isEmpty(map.get("workNo"), workNo));
        setName(SiUtils.isEmpty(map.get("name"), name));
        setGender(SiUtils.isEmpty(map.get("gender"), gender));
        setDeptCode(SiUtils.isEmpty(map.get("deptCode"), deptCode));
        setDeptId(SiUtils.isEmpty(map.get("deptId"), deptId));
        setDeptName(SiUtils.isEmpty(map.get("deptName"), deptName));
        setMailbox(SiUtils.isEmpty(map.get("mailbox"), mailbox));
        setIdNo(SiUtils.isEmpty(map.get("idNo"), idNo));
        setPicUrl(SiUtils.isEmpty(map.get("picUrl"), picUrl));
        setType(SiUtils.isEmpty(map.get("type"), type));
        setIsService(SiUtils.isEmpty(map.get("isService"), isService));
        setStatus(SiUtils.isEmpty(map.get("status"), status));
        setSource(SiUtils.isEmpty(map.get("source"), source));
        setCid(SiUtils.isEmpty(map.get("cid"), cid));
        setCancellation(SiUtils.isEmpty(map.get("cancellation"), cancellation));
        setRecCreater(SiUtils.isEmpty(map.get("recCreater"), recCreater));
        setRecCreateTime(SiUtils.isEmpty(map.get("recCreateTime"), recCreateTime));
        setRecRevisor(SiUtils.isEmpty(map.get("recRevisor"), recRevisor));
        setRecReviseTime(SiUtils.isEmpty(map.get("recReviseTime"), recReviseTime));
        setSubsidy(SiUtils.isEmpty(map.get("subsidy"), subsidy));
        setBizId(SiUtils.isEmpty(map.get("bizId"), bizId));
        setZao(SiUtils.isEmpty(map.get("zao"), zao));
        setWu(SiUtils.isEmpty(map.get("wu"), wu));
        setWan(SiUtils.isEmpty(map.get("wan"), wan));
        setPost(SiUtils.isEmpty(map.get("post"), post));
        setVocationalPost(SiUtils.isEmpty(map.get("vocationalPost"), vocationalPost));
        setLeader(SiUtils.isEmpty(map.get("leader"), leader));
    }

    /**
     * set the value to Map
     */
    @Override
    public Map toMap() {
        Map map = new HashMap(32);
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("workNo",StringUtils.toString(workNo, eiMetadata.getMeta("workNo")));
        map.put("name",StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("gender",StringUtils.toString(gender, eiMetadata.getMeta("gender")));
        map.put("deptCode",StringUtils.toString(deptCode, eiMetadata.getMeta("deptCode")));
        map.put("deptId",StringUtils.toString(deptId, eiMetadata.getMeta("deptId")));
        map.put("deptName",StringUtils.toString(deptName, eiMetadata.getMeta("deptName")));
        map.put("mailbox",StringUtils.toString(mailbox, eiMetadata.getMeta("mailbox")));
        map.put("idNo",StringUtils.toString(idNo, eiMetadata.getMeta("idNo")));
        map.put("picUrl",StringUtils.toString(picUrl, eiMetadata.getMeta("picUrl")));
        map.put("type",StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("isService",StringUtils.toString(isService, eiMetadata.getMeta("isService")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("source",StringUtils.toString(source, eiMetadata.getMeta("source")));
        map.put("cid",StringUtils.toString(cid, eiMetadata.getMeta("cid")));
        map.put("cancellation",StringUtils.toString(cancellation, eiMetadata.getMeta("cancellation")));
        map.put("recCreater",StringUtils.toString(recCreater, eiMetadata.getMeta("recCreater")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("subsidy",StringUtils.toString(subsidy, eiMetadata.getMeta("subsidy")));
        map.put("bizId",StringUtils.toString(bizId, eiMetadata.getMeta("bizId")));
        map.put("zao",StringUtils.toString(zao, eiMetadata.getMeta("zao")));
        map.put("wu",StringUtils.toString(wu, eiMetadata.getMeta("wu")));
        map.put("wan",StringUtils.toString(wan, eiMetadata.getMeta("wan")));
        map.put("post",StringUtils.toString(post, eiMetadata.getMeta("post")));
        map.put("vocationalPost",StringUtils.toString(vocationalPost, eiMetadata.getMeta("vocationalPost")));
        map.put("leader",StringUtils.toString(leader, eiMetadata.getMeta("leader")));
        return map;
    }

    /**
     * 将基础档案人员科室转换WzUser
     * @Title: newInstance
     * @param map map
     * @return com.baosight.wilp.si.ck.domain.WzUser
     * @throws
     **/
    public static WzUser newInstance(Map map) {
        WzUser user = new WzUser();
        user.fromMap(map);
        user.setDeptCode(SiUtils.isEmpty(map.get("deptNum")));
        return user;
    }
}