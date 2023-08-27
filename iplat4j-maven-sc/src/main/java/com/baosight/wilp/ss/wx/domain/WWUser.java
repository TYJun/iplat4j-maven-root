package com.baosight.wilp.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class WWUser extends WWContactEvent {
    // 成员UserID --》 acct_id
    @JacksonXmlProperty(localName = "UserID")
    private String userID;

    // 新的UserID，变更时推送（userid由系统生成时可更改一次）
    @JacksonXmlProperty(localName = "OpenUserID")
    private String openUserID;

    // 成员名称
    @JacksonXmlProperty(localName = "Name")
    private String name;

    // 成员部门列表，仅返回该应用有查看权限的部门id
    @JacksonXmlProperty(localName = "Department")
    private String department;

    // 主部门
    @JacksonXmlProperty(localName = "MainDepartment")
    private Long mainDeprtmemnt;

    //表示所在部门是否为上级，0-否，1-是，顺序与Department字段的部门逐一对应
    @JacksonXmlProperty(localName = "IsLeaderInDept")
    private String isLeaderInDept;

    // 职位信息。长度为0~64个字节，变更时推送
    @JacksonXmlProperty(localName = "Position")
    private String position;

    // 手机号码，变更时推送
    @JacksonXmlProperty(localName = "Mobile")
    private String mobile;

    // 性别，变更时推送。1表示男性，2表示女性
    @JacksonXmlProperty(localName = "Gender")
    private Integer gender;

    // 邮箱，变更时推送
    @JacksonXmlProperty(localName = "Email")
    private String email;

    //激活状态：1=激活或关注， 2=禁用， 4=未激活（重新启用未激活用户或者退出企业并且取消关注时触发），5=成员退出
    @JacksonXmlProperty(localName = "Status")
    private Integer status;

    //头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可。变更时推送
    @JacksonXmlProperty(localName = "Avatar")
    private String avatar;

    // 成员别名，变更时推送
    @JacksonXmlProperty(localName = "Alias")
    private String alias;

    // 座机，变更时推送
    @JacksonXmlProperty(localName = "Telephone")
    private String telephone;

    // 地址
    @JacksonXmlProperty(localName = "Address")
    private String address;

    // 扩展属性，变更时推送
    @JacksonXmlProperty(localName = "ExtAttr")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<WWExtAttrItem> extAttr;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOpenUserID() {
        return openUserID;
    }

    public void setOpenUserID(String openUserID) {
        this.openUserID = openUserID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getMainDeprtmemnt() {
        return mainDeprtmemnt;
    }

    public void setMainDeprtmemnt(Long mainDeprtmemnt) {
        this.mainDeprtmemnt = mainDeprtmemnt;
    }

    public String getIsLeaderInDept() {
        return isLeaderInDept;
    }

    public void setIsLeaderInDept(String isLeaderInDept) {
        this.isLeaderInDept = isLeaderInDept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<WWExtAttrItem> getExtAttr() {
        return extAttr;
    }

    public void setExtAttr(List<WWExtAttrItem> extAttr) {
        this.extAttr = extAttr;
    }
}
