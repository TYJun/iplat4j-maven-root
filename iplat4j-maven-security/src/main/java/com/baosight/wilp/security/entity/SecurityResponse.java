package com.baosight.wilp.security.entity;

/**
 * @PackageName com.baosight.wilp.security.entity
 * @ClassName SecurityResponse
 * @Description TODO
 * @Author chunchen2
 * @Date 2023/2/20 14:43
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/20 14:43
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class SecurityResponse {

    private String code;
    private String msg;

    public static SecurityResponse success(String msg){
        SecurityResponse securityResponse = new SecurityResponse();
        securityResponse.code = "200";
        securityResponse.msg = msg;
        return securityResponse;
    }

    public static SecurityResponse failure(String msg){
        SecurityResponse securityResponse = new SecurityResponse();
        securityResponse.code = "-1";
        securityResponse.msg = msg;
        return securityResponse;
    }

    public static SecurityResponse failure(String msg, String code){
        SecurityResponse securityResponse = new SecurityResponse();
        securityResponse.code = code;
        securityResponse.msg = msg;
        return securityResponse;
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
}
