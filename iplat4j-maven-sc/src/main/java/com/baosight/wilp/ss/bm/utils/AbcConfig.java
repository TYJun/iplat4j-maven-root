package com.baosight.wilp.ss.bm.utils;

import java.util.Properties;

/**
 * @PackageName com.baosight.wilp.ss.bm.utils
 * @ClassName ABCPayGlobalConfig
 * @Description TODO
 * @Author rck
 * @Date 2021/6/10 10:48
 * @Version 1.0
 * @History 修改记录1
 * <author> rck
 * <time> 2021/6/10 10:48
 * <version>
 */
public class AbcConfig {
    //merchantNum
    private String merchantNum;
    //returnUrl
    private String returnUrl;
    //hospitalName医院
    private String hospitalName;
    //payMentUrl链接地址
    private String payMentUrl;
    //appid
    private String appid;
    //pay
    private String pay;
    //posPay
    private String posPay;
    //refund
    private String refund;
    //publicKey公钥
    private String publicKey;
    //privateKey私钥
    private String privateKey;

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPayMentUrl() {
        return payMentUrl;
    }

    public void setPayMentUrl(String payMentUrl) {
        this.payMentUrl = payMentUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getPosPay() {
        return posPay;
    }

    public void setPosPay(String posPay) {
        this.posPay = posPay;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Todo(构造方法)
     *
     * @Title: AbcConfig
     * @author xiajunqing
     * @date: 2022/2/21 10:23
     * @Param
     * @return:
     */
    public AbcConfig(Properties props){
        if (null == props || props.isEmpty())
            return;
        //设置配置的属性 merchantNum
        this.merchantNum = props.getProperty("merchantNum");
        //设置配置的属性 returnUrl
        this.returnUrl = props.getProperty("returnUrl");
        //设置配置的属性 payMentUrl
        this.payMentUrl = props.getProperty("payMentUrl");
        //设置配置的属性 hospital_name
        this.hospitalName = props.getProperty("hospital_name");
        //设置配置的属性 appid
        this.appid = props.getProperty("appid");
        //设置配置的属性 pay
        this.pay = props.getProperty("pay");
        //设置配置的属性 posPay
        this.posPay = props.getProperty("posPay");
        //设置配置的属性 refund
        this.refund = props.getProperty("refund");
        //设置配置的属性 public_key
        this.publicKey = props.getProperty("public_key");
        //设置配置的属性 private_key
        this.privateKey = props.getProperty("private_key");
    }
}
