package com.baosight.wilp.entity;

/**
 * @PackageName com.baosight.wilp.entity
 * @ClassName SignDataItem
 * @Description 单个签名对象的返回值对象
 * @Author chunchen2
 * @Date 2023/2/28 14:28
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/28 14:28
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class SignDataItem {

    // 工号
    private String userId;

    // 事务id
    private String transactionId;

    // 临时授权Key
    private String authKEY;

    // 要签名数据对应的文件名
    private String fileName;

    // 原始签名数据
    private String data;

    // 签署编号（此结果需要保存，保证业务系统和医信签系统的签署数据可以关联)
    private String fileCode;

    // 签名后的数据（String/Base64），核心法律效力证据，建议保存在应用系统的数据库中。（此结果需要保存）
    private String signedData;

    // 签名时间戳中的法定时间
    private String signTime;

    // 证书序列号
    private String certSN;

    // 证书主题
    private String certDN;

    //证书生效日期
    private String certStartTime;

    // 证书终止日期
    private String certEndTime;

    // 证书颁发机构
    private String certIssuer;

    // 手写签字图片（默认PNG格式，base64）
    private String signatureImg;

    // 数字签名证书（X.509/Base64）（此结果需要保存）
    private String signCert;

    // 原文摘要值（isHash参数为1时返回此值）
    private String hash;

    // 时间戳数据包（Base64）（此结果需要保存）
    private String timestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAuthKEY() {
        return authKEY;
    }

    public void setAuthKEY(String authKEY) {
        this.authKEY = authKEY;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getSignedData() {
        return signedData;
    }

    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getCertSN() {
        return certSN;
    }

    public void setCertSN(String certSN) {
        this.certSN = certSN;
    }

    public String getCertDN() {
        return certDN;
    }

    public void setCertDN(String certDN) {
        this.certDN = certDN;
    }

    public String getCertStartTime() {
        return certStartTime;
    }

    public void setCertStartTime(String certStartTime) {
        this.certStartTime = certStartTime;
    }

    public String getCertEndTime() {
        return certEndTime;
    }

    public void setCertEndTime(String certEndTime) {
        this.certEndTime = certEndTime;
    }

    public String getCertIssuer() {
        return certIssuer;
    }

    public void setCertIssuer(String certIssuer) {
        this.certIssuer = certIssuer;
    }

    public String getSignatureImg() {
        return signatureImg;
    }

    public void setSignatureImg(String signatureImg) {
        this.signatureImg = signatureImg;
    }

    public String getSignCert() {
        return signCert;
    }

    public void setSignCert(String signCert) {
        this.signCert = signCert;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
