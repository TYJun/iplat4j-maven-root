package com.baosight.wilp.pr.ap.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 安全信息实体
 * 
 * @Title: SaftyInformation.java
 * @ClassName: SaftyInformation
 * @Package：com.baosight.zdyyaq.aq.ap.domain
 * @author: zhangjiaqian
 * @date: 2021年5月11日 上午10:14:17
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class SaftyInformation implements Serializable{

    
    
    /**
     * @Fields serialVersionUID : 序列化
     */
    private static final long serialVersionUID = -7146697871245952435L;


    
    /**
     * id
     */
    private String id;
    
    
    
    /**
     * 问题池关联id
     */
    private String dangerId;
    
    
    
    /**
     * 创建人
     */
    private String createMan;
    
    
    
    /**
     * 创建时间
     */
    private String createTime;
    
    
    
    /**
     * 问题编号
     */
    private String dangerCode;
    
    
    
    /**
     * 问题地点
     */
    private String dangerWhere;
    
    
    
    /**
     * 问题类型
     */
    private String localTypeName;
    
    
    
    /**
     * 问题类型编码
     */
    private String localTypeCode;
    
    
    
    /**
     * 问题描述
     */
    private String dangerContent;
    
    
    
    /**
     * 要求整改时间
     */
    private String requiredTime;
    
    
    
    /**
     * 整改实绩
     */
    private String content;
    
    
    /**
     * 状态编码
     */
    private String statusCode;
    
    
    
    /**
     * 整改前图片
     */
    private List<Pic> pic;
    

    /**
     * 整改后图片
     */
    private List<ResultPic> resultPic;
    
    
    
    /**
     * 整改信息id
     */
    private String dangerResultId;
    
    
    
    /**
     * 日志信息
     */
    private String rejectReason;
    
    /**
     * 日志信息
     */
    private String rejectReason2;

    
    
    /**
     * 整改要求
     */
    private String requireDesc;
    
    

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getDangerId() {
        return dangerId;
    }


    public void setDangerId(String dangerId) {
        this.dangerId = dangerId;
    }


    public String getCreateMan() {
        return createMan;
    }


    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }


    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getDangerCode() {
        return dangerCode;
    }


    public void setDangerCode(String dangerCode) {
        this.dangerCode = dangerCode;
    }


    public String getDangerWhere() {
        return dangerWhere;
    }


    public void setDangerWhere(String dangerWhere) {
        this.dangerWhere = dangerWhere;
    }


    public String getLocalTypeName() {
        return localTypeName;
    }


    public void setLocalTypeName(String localTypeName) {
        this.localTypeName = localTypeName;
    }


    public String getLocalTypeCode() {
        return localTypeCode;
    }


    public void setLocalTypeCode(String localTypeCode) {
        this.localTypeCode = localTypeCode;
    }


    public String getDangerContent() {
        return dangerContent;
    }


    public void setDangerContent(String dangerContent) {
        this.dangerContent = dangerContent;
    }


    public String getRequiredTime() {
        return requiredTime;
    }


    public void setRequiredTime(String requiredTime) {
        this.requiredTime = requiredTime;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public List<Pic> getPic() {
        return pic;
    }


    public void setPic(List<Pic> pic) {
        this.pic = pic;
    }


    public List<ResultPic> getResultPic() {
        return resultPic;
    }


    public void setResultPic(List<ResultPic> resultPic) {
        this.resultPic = resultPic;
    }
    

    public String getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    
    public String getDangerResultId() {
        return dangerResultId;
    }


    public void setDangerResultId(String dangerResultId) {
        this.dangerResultId = dangerResultId;
    }


    public String getRejectReason() {
        return rejectReason;
    }


    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }


    public String getRequireDesc() {
        return requireDesc;
    }


    public void setRequireDesc(String requireDesc) {
        this.requireDesc = requireDesc;
    }


    public String getRejectReason2() {
        return rejectReason2;
    }


    public void setRejectReason2(String rejectReason2) {
        this.rejectReason2 = rejectReason2;
    }


    @Override
    public String toString() {
        return "SaftyInformation [id=" + id + ", dangerId=" + dangerId + ", createMan=" + createMan + ", createTime="
            + createTime + ", dangerCode=" + dangerCode + ", dangerWhere=" + dangerWhere + ", localTypeName="
            + localTypeName + ", localTypeCode=" + localTypeCode + ", dangerContent=" + dangerContent
            + ", requiredTime=" + requiredTime + ", content=" + content + ", statusCode=" + statusCode + ", pic=" + pic
            + ", resultPic=" + resultPic + ", dangerResultId=" + dangerResultId + ", rejectReason=" + rejectReason
            + ", rejectReason2=" + rejectReason2 + ", requireDesc=" + requireDesc + "]";
    }



}
