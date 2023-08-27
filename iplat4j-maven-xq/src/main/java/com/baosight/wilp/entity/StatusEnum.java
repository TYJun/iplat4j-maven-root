package com.baosight.wilp.entity;

/**
 * @Title
 * @Author chunchen2
 * @Description // 医信签 状态编码
 * @Date 16:32 2023/2/22
 * @return
 * @throws
 **/
public enum StatusEnum {

    SUCCESS("0", "操作成功"),
    FAILURE("-1", "调用医信签接口失败"),
    OPSUCCESS("20000", "操作成功"),
    SERVEREXCEPTION("20001", "服务器异常"),
    LOGINFAILURE("20002", "登录操作失败"),
    ACCESSDENIED("20003", "无权限访问"),
    PARAMSERROR("20004", "请求参数错误"),
    NORESOURCE("20005", "请求资源不存在"),
    MOBILEOCCUPY("20006", "手机号已被占用"),
    TOKENEXPIRED("405", "app未经授权（token失效）"),
    NOTREALNAMEAUTH("20008", "用户未实名"),
    NOTSETPWD("20009", "未设置密码"),
    NOTCOLLECSIGN("20010", "未采集签字"),
    PWDERROR("20011", "密码错误"),
    REPEATOP("20012", "重复操作"),
    FILESIGNED("20013", "文件已签署"),
    FILECANCLESIGN("20014", "文件已取消签署"),
    FILEEXPIRED("20015", "文件已过期"),
    FILENOTSIGN("20016", "文件未签署"),
    NOTSPECIFYOPERATOR("20017", "非指定操作人"),
    SECURITYKEYERROR("20018", "安全密钥错误"),
    MEDICALCERTFICATEINSUFFICIENT("20019", "可用医护人员证书授权数不足"),
    PATIENTSIGNINSUFFICIENT("20020", "可用患者电子签名授权数不足"),
    TEMPAUTHERROR("20025", "临时授权密钥过期"),
    KEYWORDSIGNFAILURE("20026", "关键字签署失败"),
    POINTSIGNERROR("20027", "坐标签署失败"),
    USERNOEXISTS("20030", "用户编号已存在"),
    APPLYCERTFAILURE("20031", "证书申请失败"),
    BRUSHFACEINSUFFICIENT("20032", "可用刷脸数量不足"),
    IDCARDEXCEPTION("20033", "身份证号码异常"),
    USERNOTEXISTS("20034", "用户不存在"),
    SYSTEMBUY("21000", "系统繁忙");

    private String code;
    private String msg;

    StatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsgByCode(String code) {
        for(StatusEnum statusEnum : StatusEnum.values()) {
            if(statusEnum.getCode() == code){
                return statusEnum.getMsg();
            }
        }
        return "未知状态编码：" + code;
    }

}
