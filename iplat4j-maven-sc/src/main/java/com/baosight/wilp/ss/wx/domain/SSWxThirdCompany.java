package com.baosight.wilp.ss.wx.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

/**
 * 企业微信三方应用授权公司(ScWxThirdCompany)实体类
 *
 * @author liu
 * @since 2022-10-27 15:03:56
 */
public class SSWxThirdCompany extends DaoEPBase {

    private static final long serialVersionUID = 1L;



    private String id = " ";

    /**
     * 企业id
     */
    private String corpId = " ";

    /**
     * 企业加密id
     */
    private String corpEncryptId = " ";

    /**
     * 企业名称
     */
    private String corpName = " ";

    /**
     * 企业全称
     */
    private String corpFullName = " ";

    /**
     * 企业类型
     */
    private String subjectType = " ";

    /**
     * 企业认证到期时间
     */
    private String verifiedEndTime = " ";

    /**
     * 授权应用id
     */
    private String agentId = " ";

    /**
     * 账户状态，-1为删除，禁用为0 启用为1
     */
    private Integer status = new Integer(0);

    /**
     * 创建时间
     */
    private String addtime = " ";

    /**
     * 修改时间
     */
    private String modtime = " ";

    /**
     * 变动时间
     */
    private String rectime = " ";

    /**
     * 应用id 可以不写
     */
    private Integer appId = new Integer(0);

    /**
     * 应用密钥
     */
    private String agentSecret = " ";

    /**
     * 审批流程引擎模板id
     */
    private String agentToken = " ";

    /**
     * encoding_aes_key
     */
    private String agentEncodingAesKey = " ";

    /**
     * 审批流程引擎模板id
     */
    private String approvalTemplateId = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("corpId");
        eiColumn.setDescName("企业id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("corpEncryptId");
        eiColumn.setDescName("企业加密id");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("corpName");
        eiColumn.setDescName("企业名称");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("corpFullName");
        eiColumn.setDescName("企业全称");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("subjectType");
        eiColumn.setDescName("企业类型");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("verifiedEndTime");
        eiColumn.setDescName("企业认证到期时间");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("agentId");
        eiColumn.setDescName("授权应用id");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("status");
        eiColumn.setDescName("账户状态，-1为删除，禁用为0 启用为1");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("addtime");
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("modtime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("rectime");
        eiColumn.setDescName("变动时间");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("appId");
        eiColumn.setDescName("应用id 可以不写");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("agentSecret");
        eiColumn.setDescName("应用密钥");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("agentToken");
        eiColumn.setDescName("审批流程引擎模板id");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("agentEncodingAesKey");
        eiColumn.setDescName("encoding_aes_key");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("approvalTemplateId");
        eiColumn.setDescName("审批流程引擎模板id");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public SSWxThirdCompany() {
        initMetaData();
    }

    /**
     * get the id -
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * set the id -
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the corpId - 企业id
     * @return the corpId
     */
    public String getCorpId() {
        return corpId;
    }
    /**
     * set the corpId - 企业id
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }


    public String getCorpEncryptId() {
        return corpEncryptId;
    }

    public void setCorpEncryptId(String corpEncryptId) {
        this.corpEncryptId = corpEncryptId;
    }

    /**
     * get the corpName - 企业名称
     * @return the corpName
     */
    public String getCorpName() {
        return corpName;
    }
    /**
     * set the corpName - 企业名称
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * get the corpFullName - 企业全称
     * @return the corpFullName
     */
    public String getCorpFullName() {
        return corpFullName;
    }
    /**
     * set the corpFullName - 企业全称
     */
    public void setCorpFullName(String corpFullName) {
        this.corpFullName = corpFullName;
    }

    /**
     * get the subjectType - 企业类型
     * @return the subjectType
     */
    public String getSubjectType() {
        return subjectType;
    }
    /**
     * set the subjectType - 企业类型
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * get the verifiedEndTime - 企业认证到期时间
     * @return the verifiedEndTime
     */
    public String getVerifiedEndTime() {
        return verifiedEndTime;
    }
    /**
     * set the verifiedEndTime - 企业认证到期时间
     */
    public void setVerifiedEndTime(String verifiedEndTime) {
        this.verifiedEndTime = verifiedEndTime;
    }

    /**
     * get the agentId - 授权应用id
     * @return the agentId
     */
    public String getAgentId() {
        return agentId;
    }
    /**
     * set the agentId - 授权应用id
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    /**
     * get the status - 账户状态，-1为删除，禁用为0 启用为1
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * set the status - 账户状态，-1为删除，禁用为0 启用为1
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get the addtime - 创建时间
     * @return the addtime
     */
    public String getAddtime() {
        return addtime;
    }
    /**
     * set the addtime - 创建时间
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * get the modtime - 修改时间
     * @return the modtime
     */
    public String getModtime() {
        return modtime;
    }
    /**
     * set the modtime - 修改时间
     */
    public void setModtime(String modtime) {
        this.modtime = modtime;
    }

    /**
     * get the rectime - 变动时间
     * @return the rectime
     */
    public String getRectime() {
        return rectime;
    }
    /**
     * set the rectime - 变动时间
     */
    public void setRectime(String rectime) {
        this.rectime = rectime;
    }

    /**
     * get the appId - 应用id 可以不写
     * @return the appId
     */
    public Integer getAppId() {
        return appId;
    }
    /**
     * set the appId - 应用id 可以不写
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * get the agentSecret - 应用密钥
     * @return the agentSecret
     */
    public String getAgentSecret() {
        return agentSecret;
    }
    /**
     * set the agentSecret - 应用密钥
     */
    public void setAgentSecret(String agentSecret) {
        this.agentSecret = agentSecret;
    }

    /**
     * get the agentToken - 审批流程引擎模板id
     * @return the agentToken
     */
    public String getAgentToken() {
        return agentToken;
    }
    /**
     * set the agentToken - 审批流程引擎模板id
     */
    public void setAgentToken(String agentToken) {
        this.agentToken = agentToken;
    }

    /**
     * get the agentEncodingAesKey - encoding_aes_key
     * @return the agentEncodingAesKey
     */
    public String getAgentEncodingAesKey() {
        return agentEncodingAesKey;
    }
    /**
     * set the agentEncodingAesKey - encoding_aes_key
     */
    public void setAgentEncodingAesKey(String agentEncodingAesKey) {
        this.agentEncodingAesKey = agentEncodingAesKey;
    }

    /**
     * get the approvalTemplateId - 审批流程引擎模板id
     * @return the approvalTemplateId
     */
    public String getApprovalTemplateId() {
        return approvalTemplateId;
    }
    /**
     * set the approvalTemplateId - 审批流程引擎模板id
     */
    public void setApprovalTemplateId(String approvalTemplateId) {
        this.approvalTemplateId = approvalTemplateId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setCorpId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("corpId")), corpId));
        setCorpEncryptId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("corpEncryptId")), corpEncryptId));
        setCorpName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("corpName")), corpName));
        setCorpFullName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("corpFullName")), corpFullName));
        setSubjectType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("subjectType")), subjectType));
        setVerifiedEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("verifiedEndTime")), verifiedEndTime));
        setAgentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("agentId")), agentId));
        setStatus(NumberUtils.toInteger(StringUtils.toString(map.get("status")), status));
        setAddtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("addtime")), addtime));
        setModtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modtime")), modtime));
        setRectime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rectime")), rectime));
        setAppId(NumberUtils.toInteger(StringUtils.toString(map.get("appId")), appId));
        setAgentSecret(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("agentSecret")), agentSecret));
        setAgentToken(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("agentToken")), agentToken));
        setAgentEncodingAesKey(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("agentEncodingAesKey")), agentEncodingAesKey));
        setApprovalTemplateId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("approvalTemplateId")), approvalTemplateId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id",StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("corpId",StringUtils.toString(corpId, eiMetadata.getMeta("corpId")));
        map.put("corpEncryptId",StringUtils.toString(corpEncryptId, eiMetadata.getMeta("corpEncryptId")));
        map.put("corpName",StringUtils.toString(corpName, eiMetadata.getMeta("corpName")));
        map.put("corpFullName",StringUtils.toString(corpFullName, eiMetadata.getMeta("corpFullName")));
        map.put("subjectType",StringUtils.toString(subjectType, eiMetadata.getMeta("subjectType")));
        map.put("verifiedEndTime",StringUtils.toString(verifiedEndTime, eiMetadata.getMeta("verifiedEndTime")));
        map.put("agentId",StringUtils.toString(agentId, eiMetadata.getMeta("agentId")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("addtime",StringUtils.toString(addtime, eiMetadata.getMeta("addtime")));
        map.put("modtime",StringUtils.toString(modtime, eiMetadata.getMeta("modtime")));
        map.put("rectime",StringUtils.toString(rectime, eiMetadata.getMeta("rectime")));
        map.put("appId",StringUtils.toString(appId, eiMetadata.getMeta("appId")));
        map.put("agentSecret",StringUtils.toString(agentSecret, eiMetadata.getMeta("agentSecret")));
        map.put("agentToken",StringUtils.toString(agentToken, eiMetadata.getMeta("agentToken")));
        map.put("agentEncodingAesKey",StringUtils.toString(agentEncodingAesKey, eiMetadata.getMeta("agentEncodingAesKey")));
        map.put("approvalTemplateId",StringUtils.toString(approvalTemplateId, eiMetadata.getMeta("approvalTemplateId")));
        return map;
    }



}

