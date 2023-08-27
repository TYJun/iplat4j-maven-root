//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.bonawise.smp.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SmpConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    private String commonNodeUrl;
    private String commonNodeUrlWechat;
    private String commonNodeUrlAli;
    private String hessianPattern;
    private String hessianPatternWechat;
    private String hessianPatternAli;
    private String hospitalCode;
    private String hospitalCodeWechat;
    private String hospitalCodeAli;
    private String hospitalName;
    private String canteenNumWork;
    private String canteenNameWork;
    private String canteenNum;
    private String scanHospitalCode;
    private String canteenName;
    private Map<String, String> notifyMap = new HashMap();

    public String getCanteenNameWork() {
        return this.canteenNameWork;
    }

    public void setCanteenNameWork(String canteenNameWork) {
        this.canteenNameWork = canteenNameWork;
    }

    public String getCanteenNumWork() {
        return this.canteenNumWork;
    }

    public void setCanteenNumWork(String canteenNumWork) {
        this.canteenNumWork = canteenNumWork;
    }

    public String getScanHospitalCode() {
        return this.scanHospitalCode;
    }

    public void setScanHospitalCode(String scanHospitalCode) {
        this.scanHospitalCode = scanHospitalCode;
    }

    public String getCanteenNum() {
        return this.canteenNum;
    }

    public void setCanteenNum(String canteenNum) {
        this.canteenNum = canteenNum;
    }

    public String getCanteenName() {
        return this.canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public String getHospitalName() {
        return this.hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getCommonNodeUrl() {
        return this.commonNodeUrl;
    }

    public void setCommonNodeUrl(String commonNodeUrl) {
        this.commonNodeUrl = commonNodeUrl;
    }

    public String getHessianPattern() {
        return this.hessianPattern;
    }

    public void setHessianPattern(String hessianPattern) {
        this.hessianPattern = hessianPattern;
    }

    public String getHospitalCode() {
        return this.hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalCodeWechat() {
        return this.hospitalCodeWechat;
    }

    public void setHospitalCodeWechat(String hospitalCodeWechat) {
        this.hospitalCodeWechat = hospitalCodeWechat;
    }

    public String getHospitalCodeAli() {
        return this.hospitalCodeAli;
    }

    public void setHospitalCodeAli(String hospitalCodeAli) {
        this.hospitalCodeAli = hospitalCodeAli;
    }

    public String getCommonNodeUrlWechat() {
        return this.commonNodeUrlWechat;
    }

    public void setCommonNodeUrlWechat(String commonNodeUrlWechat) {
        this.commonNodeUrlWechat = commonNodeUrlWechat;
    }

    public String getCommonNodeUrlAli() {
        return this.commonNodeUrlAli;
    }

    public void setCommonNodeUrlAli(String commonNodeUrlAli) {
        this.commonNodeUrlAli = commonNodeUrlAli;
    }

    public String getHessianPatternWechat() {
        return this.hessianPatternWechat;
    }

    public void setHessianPatternWechat(String hessianPatternWechat) {
        this.hessianPatternWechat = hessianPatternWechat;
    }

    public String getHessianPatternAli() {
        return this.hessianPatternAli;
    }

    public void setHessianPatternAli(String hessianPatternAli) {
        this.hessianPatternAli = hessianPatternAli;
    }

    public Map<String, String> getNotifyMap() {
        return this.notifyMap;
    }

    public void setNotifyMap(Map<String, String> notifyMap) {
        this.notifyMap = notifyMap;
    }

    public SmpConfig() {
    }

    public SmpConfig(Properties props) {
        if (props != null && !props.isEmpty()) {
            this.commonNodeUrl = props.getProperty("common_node_url");
            this.commonNodeUrlWechat = props.getProperty("common_node_url_wechat");
            this.commonNodeUrlAli = props.getProperty("common_node_url_ali");
            this.hessianPattern = props.getProperty("hessian_pattern");
            this.hessianPatternWechat = props.getProperty("hessian_pattern_wechat");
            this.hessianPatternAli = props.getProperty("hessian_pattern_ali");
            this.hospitalCode = props.getProperty("hospital_code");
            this.hospitalCodeWechat = props.getProperty("hospital_code_wechat");
            this.hospitalCodeAli = props.getProperty("hospital_code_ali");
            this.hospitalName = props.getProperty("hospital_name");
            this.canteenNum = props.getProperty("canteen_num");
            this.canteenName = props.getProperty("canteen_name");
            this.scanHospitalCode = props.getProperty("scan_hospital_code");
            this.canteenNameWork = props.getProperty("canteen_name_work");
            this.canteenNumWork = props.getProperty("canteen_num_work");
            System.out.println("载入配置文件" + this.toString());
        }
    }

    public String toString() {
        return "SmpConfig [commonNodeUrl=" + this.commonNodeUrl + ", commonNodeUrlWechat=" + this.commonNodeUrlWechat + ", commonNodeUrlAli=" + this.commonNodeUrlAli + ", hessianPattern=" + this.hessianPattern + ", hessianPatternWechat=" + this.hessianPatternWechat + ", hessianPatternAli=" + this.hessianPatternAli + ", hospitalCode=" + this.hospitalCode + ", hospitalCodeWechat=" + this.hospitalCodeWechat + ", hospitalCodeAli=" + this.hospitalCodeAli + ", hospitalName=" + this.hospitalName + ", canteenNumWork=" + this.canteenNumWork + ", canteenNameWork=" + this.canteenNameWork + ", canteenNum=" + this.canteenNum + ", scanHospitalCode=" + this.scanHospitalCode + ", canteenName=" + this.canteenName + ", notifyMap=" + this.notifyMap + "]";
    }
}
