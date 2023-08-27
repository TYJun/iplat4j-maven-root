package com.baosight.wilp.entity;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;

/**
 * @PackageName com.baosight.wilp.entity
 * @ClassName UserEntity
 * @Description 医信签保存的用户实体
 * @Author chunchen2
 * @Date 2023/3/2 14:47
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/3/2 14:47
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class UserEntity extends DaoEPBase {

    public UserEntity(){
        initMetaData();
    }

    // 用户类型
    private String userType;

    // 机构编号
    private String orgId;

    // 用户工号
    private String userId;

    // 用户姓名
    private String userName;

    // 身份证号
    private String userIdcard;

    // 电话号码
    private String userPhone;

    // 实名认证状态
    private String realNameStatus;

    // 科室名称
    private String deptName;

    // 手写签字图片
    private String signatureImg;

    // 状态
    private String status;

    public EiBlockMeta getEiBlockMeta(){
        return eiMetadata;
    }

    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("userType");
        eiColumn.setDescName("用户类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("orgId");
        eiColumn.setDescName("机构编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userId");
        eiColumn.setDescName("用户工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setDescName("用户姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userIdcard");
        eiColumn.setDescName("身份证号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userPhone");
        eiColumn.setDescName("手机号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("realNameStatus");
        eiColumn.setDescName("实名认证状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deptName");
        eiColumn.setDescName("科室名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("signatureImg");
        eiColumn.setDescName("手写签名图片");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);
    }



    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRealNameStatus() {
        return realNameStatus;
    }

    public void setRealNameStatus(String realNameStatus) {
        this.realNameStatus = realNameStatus;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSignatureImg() {
        return signatureImg;
    }

    public void setSignatureImg(String signatureImg) {
        this.signatureImg = signatureImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
