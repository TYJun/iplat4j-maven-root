package com.baosight.wilp.xq.ms.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.wilp.authentication.service.TokenService;
import com.baosight.wilp.cache.AuthKeyCacheManage;
import com.baosight.wilp.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.*;

/**
 * @PackageName com.baosight.wilp.xq.ms.service
 * @ClassName ServiceXQMS01
 * @Description 登录身份授权
 *              用户扫码授权、（根据用户id/transcationId）获取授权状态、根据用户id/transcationId）取消用户授权
 * @Author chunchen2
 * @Date 2023/2/22 15:43
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/22 15:43
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceXQMS01 extends ServiceEPBase {

    @Autowired
    TokenService tokenService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * @Title checkAuthByUserId
     * @Author chunchen2
     * @Description // 根据用户的工号，检查指定的用户签名状态
     * @Date 15:46 2023/2/22
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo checkAuthStatusByUserId(EiInfo eiInfo) {
        EiInfo out = new EiInfo();
        String userId = eiInfo.getString("userId");

        if(StringUtils.isEmpty(userId)){
            out.setStatus(ErrorTips.USER_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.USER_ID_NOT_NULL_TIPS);
            return out;
        }

        // 1. 根据用户工号，查询系统缓存里面是否已经有未过期的授权密钥
        List<AuthKeyEitity> authKeyLists = AuthKeyCacheManage.get(userId);
        if(null != authKeyLists && authKeyLists.size() > 0) {
            out.setStatus(ErrorTips.SUCCESS_CODE);
            out.setMsg(ErrorTips.SUCCESS_TIPS);

            AuthKeyEitity authKeyEitity = authKeyLists.get(0);
            out.set("auth", authKeyEitity);

            return out;
        }

        // 2. 未查到授权情况，尝试调用医信签的接口查询是否有授权
        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken){
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.GET_OAUTH_BY_USERID +
                "?accessToken=" + accessToken;

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.GET_USER_OAUTH_STATUS_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_USER_OAUTH_STATUS_EXCEPTION_TIPS);
            return out;
        }

        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String status = response.getString("status");
            Object data = response.get("data");

            if(!"0".equalsIgnoreCase(status)) { // 调用异常
                out.setStatus(Integer.parseInt(status));
                out.setMsg(response.getString("message"));
                return out;
            }

            if("0".equalsIgnoreCase(status) && null == data) { // 查询成功，无记录
                out.setStatus(ErrorTips.USER_UNAUTHORIZED_CODE);
                out.setMsg(ErrorTips.USER_UNAUTHORIZED_TIPS);
                return out;
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.GET_USER_OAUTH_STATUS_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_USER_OAUTH_STATUS_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        // 缓存临时授权密钥到我们的系统
        Object data = response.get("data");
        String userName = null;
        String authKEY =null;
        String authEndTime =null;
        String transactionId =null;
        if(data instanceof LinkedHashMap) {
            LinkedHashMap item =(LinkedHashMap) data;
            userName = String.valueOf(item.get("userName"));
            authKEY = String.valueOf(item.get("authKEY"));
            authEndTime = String.valueOf(item.get("authEndTime"));
            transactionId = String.valueOf(item.get("transactionId"));
        } else if(data instanceof ArrayList){
            ArrayList<LinkedHashMap<String, String>> tempLists = (ArrayList<LinkedHashMap<String, String>>) data;
            if(tempLists.size() == 0) { // 没有授权的记录
                out.setStatus(ErrorTips.USER_UNAUTHORIZED_CODE);
                out.setMsg(ErrorTips.USER_UNAUTHORIZED_TIPS);
                return out;
            }
            LinkedHashMap<String, String> jsonObject = (tempLists).get(0);

            userName = jsonObject.get("userName");
            authKEY = jsonObject.get(("authKEY"));
            authEndTime = jsonObject.get("authEndTime");
            transactionId = jsonObject.get("transactionId");
        }

        if(null == userName || null == authKEY || null == authEndTime || null == transactionId) {
            System.out.println("userName: " + userName + ",authKEY: " + authKEY + ",authEndTime: " + authEndTime +
                    ",transactionId: " + transactionId + " --> " + data);
            out.setStatus(ErrorTips.GET_USER_OAUTH_STATUS_RESPONSE_EXCEPTION_CODE);
            out.setMsg("解析->" + ErrorTips.GET_USER_OAUTH_STATUS_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        AuthKeyEitity authKeyEitity = new AuthKeyEitity(userId, userName, authKEY, authEndTime, transactionId);
        AuthKeyCacheManage.put(userId, authKeyEitity);

        out.set("auth", authKeyEitity);
        out.setStatus(ErrorTips.SUCCESS_CODE);
        out.setMsg(ErrorTips.SUCCESS_TIPS);
        return out;
    }

    /**
     * @Title scanOAuth
     * @Author chunchen2
     * @Description // 用户扫码身份授权
     * @Date 17:59 2023/2/22
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo scanOAuth(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验用户工号，必填
        String userId = eiInfo.getString("userId");
        String oauthMethod = eiInfo.getString("oauthMethod");
        String redirectURL = eiInfo.getString("redirectURL");
        if(StringUtils.isEmpty(userId)) {
            out.setStatus(ErrorTips.USER_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.USER_ID_NOT_NULL_TIPS);
            return out;
        }

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken){
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        // 构建调用医信签扫码授权接口的请求参数
        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.SCAN_AUTH +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl,  buildOAuthParams(userId, oauthMethod, redirectURL));
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.SCAN_OAUTH_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SCAN_OAUTH_EXCEPTION_TIPS);
            return out;
        }

        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String transctionId = response.getJSONObject("data").getString("transactionId");

            System.out.println("transctionId: " + transctionId);
        } catch (Exception e) {
            out.setStatus(ErrorTips.SCAN_OAUTH_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SCAN_OAUTH_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        out.set("result", response);
        return out;
    }

    /**
     * @Title checkScanStatus
     * @Author chunchen2
     * @Description // 根据用户的事务id，查询用户扫码授权的状态
     * @Date 14:44 2023/2/27
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo checkScanStatus(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验身份授权事务id，必填
        String transactionId = eiInfo.getString("transactionId");
        if(StringUtils.isEmpty(transactionId)) {
            out.setStatus(ErrorTips.TRANSACTION_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.TRANSACTION_ID_NOT_NULL_TIPS);
            return out;
        }

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken){
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }


        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.GET_OAUTH_STATUS +
                "?accessToken=" + accessToken;


        Map<String, Object> params = new HashMap<>();
        params.put("transactionId", transactionId);

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.GET_OAUTH_STATUS_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_OAUTH_STATUS_EXCEPTION_TIPS);
            return out;
        }

        // 尝试获取响应里面的参数，判断是否能正确的获取到返回的数据
        try {
            String authKEY = response.getJSONObject("data").getString("authKEY");
        } catch (Exception e) {
            out.setStatus(ErrorTips.GET_OAUTH_STATUS_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_OAUTH_STATUS_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        if("0".equalsIgnoreCase(response.getString("status")) &&
                "1".equalsIgnoreCase(response.getJSONObject("data").getString("oauthStatus"))) { // 已授权
            // 授权成功，开始缓存临时授权key信息
            JSONObject data = response.getJSONObject("data");
            String userId = data.getString("userId");
            String userName = data.getString("userName");
            String authKEY = data.getString("authKEY");
            String authEndTime = data.getString("authEndTime");
            AuthKeyEitity authKeyEitity = new AuthKeyEitity(userId, userName, authKEY, authEndTime, transactionId);
            AuthKeyCacheManage.put(userId, authKeyEitity);
        }

        out.set("result", response);
        return out;
    }

    /**
     * @Title cancelUserOauthByTransactionId
     * @Author chunchen2
     * @Description // 根据事务id取消用户授权
     * @Date 15:06 2023/2/27
     * @param
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo cancelUserOauthByTransactionId(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验身份授权事务id，必填
        String transactionId = eiInfo.getString("transactionId");
        if(StringUtils.isEmpty(transactionId)) {
            out.setStatus(ErrorTips.TRANSACTION_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.TRANSACTION_ID_NOT_NULL_TIPS);
            return out;
        }

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken){
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.CANCLE_AUTH_BY_TRANSACTIONID +
                "?accessToken=" + accessToken;


        Map<String, Object> params = new HashMap<>();
        params.put("transactionId", transactionId);

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.CANCEL_USER_OAUTH_BY_TRANSACTIONID_EXCEPTION_CODE);
            out.setMsg(ErrorTips.CANCEL_USER_OAUTH_BY_TRANSACTION_EXCEPTION_TIPS);
            return out;
        }

        String status = response.getString("status");
        String message = response.getString("message");

        out.setStatus(Integer.parseInt(status));
        out.setMsg(message);
        return out;
    }

    /**
     * @Title cancelUserAllOauthByUserId
     * @Author chunchen2
     * @Description // 根据用户的工号取消用户的所有授权
     * @Date 15:07 2023/2/27
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo cancelUserAllOauthByUserId(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验用户Id，必填
        String userId = eiInfo.getString("userId");
        if(StringUtils.isEmpty(userId)) {
            out.setStatus(ErrorTips.USER_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.USER_ID_NOT_NULL_TIPS);
            return out;
        }

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken){
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.CANCLE_AUTH_BY_USERID +
                "?accessToken=" + accessToken;

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.CANCEL_USER_OAUTH_BY_USERID_EXCEPTION_CODE);
            out.setMsg(ErrorTips.CANCEL_USER_OAUTH_BY_USERID_EXCEPTION_TIPS);
            return out;
        }

        String status = response.getString("status");
        String message = response.getString("message");

        if("0".equalsIgnoreCase(status)) { // 去除缓存里面无效的用户授权记录
            AuthKeyCacheManage.remove(userId);
        }

        out.setStatus(Integer.parseInt(status));
        out.setMsg(message);
        return out;
    }



    private Map<String, Object> buildOAuthParams(String userId, String oauthMethod, String redirectURL) {
        Map<String, Object> params = new HashMap<>(8);
        params.put("userId", userId);
        params.put("oauthMethod",  StringUtils.isBlank(oauthMethod) ? "3" : oauthMethod);
        //params.put("callbackURL", "http://dgrytest.yyhq365.cn/app_receive.do?msg");
        //params.put("redirectURL", "http://v5-stable-test.yyhq365.cn/iplat_v5_stable_test/login.jsp?id=2");
        if(StringUtils.isNotBlank(redirectURL)) {
            params.put("redirectURL", redirectURL);
        }
        params.put("imageType", "png");
        //params.put("isBackSignatureImg", "1");
        return params;
    }

}
