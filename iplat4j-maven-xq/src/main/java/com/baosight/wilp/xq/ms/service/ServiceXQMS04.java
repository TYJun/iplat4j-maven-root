package com.baosight.wilp.xq.ms.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.wilp.authentication.service.TokenService;
import com.baosight.wilp.entity.ErrorTips;
import com.baosight.wilp.entity.ResTemplateUtils;
import com.baosight.wilp.entity.URLConstants;
import com.baosight.wilp.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @PackageName com.baosight.wilp.xq.ms.service
 * @ClassName ServiceXQMS04
 * @Description 用户管理服务
 *              导入用户、更改用户、查询用户信息、用户职称设置、删除用户、冻结用户、解冻用户
 * @Author chunchen2
 * @Date 2023/3/2 11:32
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/3/2 11:32
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceXQMS04 extends ServiceEPBase {

    @Autowired
    TokenService tokenService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo out = new EiInfo();

        // 初始化，返回一个空的对象，不渲染数据
        UserEntity userEntity = new UserEntity();
        EiBlock resultBlock = new EiBlock("result");
        resultBlock.setBlockMeta(userEntity.getEiBlockMeta());
        out.addBlock(resultBlock);
        return out;
    }



    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo out = new EiInfo();

        EiInfo userInfo = getUserInfo(inInfo);

        if(userInfo.getStatus() != 0 ) { // 调用失败直接返回
            return userInfo;
        }

        List<Map<String, String>> retLists = new ArrayList<>();
        Map<String, String> result1 = (Map<String, String>) userInfo.get("result");

        String signatureImg = result1.get("signatureImg");
        if(StringUtils.isNotEmpty(signatureImg)){
            result1.put("signatureImgStr", "data:image/png;base64," + signatureImg);
        } else {
            result1.put("signatureImgStr", "");
        }

        retLists.add(result1);

        UserEntity userEntity = new UserEntity();
        EiBlock resultBlock = new EiBlock("result");
        resultBlock.setBlockMeta(userEntity.getEiBlockMeta());
        resultBlock.setRows(retLists);
        Map<String, Object> rAttr = new HashMap<>();
        rAttr.put("count", retLists.size());
        resultBlock.setAttr(rAttr);
        out.addBlock(resultBlock);

        return out;
    }

    /**
     * @Title importUser
     * @Author chunchen2
     * @Description // 导入单个用户信息到医信签里面
     * @Date 13:18 2023/3/2
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo importUser(EiInfo eiInfo){
        EiInfo out = new EiInfo();

        Map<String, String> userInfo = (HashMap) eiInfo.get("userInfo");

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        List<Map<String, String>> userInfoLists = new ArrayList<>();
        userInfoLists.add(userInfo);

        Map<String, Object> params = new HashMap<>();
        params.put("userInfo", userInfoLists);

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.IMPORT_USER +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(-301);
            out.setMsg("导入用户调用异常！");
            return out;
        }

        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 未成功调用
                out.setStatus(Integer.parseInt(status));
                out.setMsg(response.getString("message"));
                return out;
            }
            String message = response.getString("message");

            out.setMsg(message);
            out.setStatus(Integer.parseInt(status));
            out.set("result", response.get("data"));
        } catch (Exception e) {
            out.setStatus(-302);
            out.setMsg("导入用户返回值解析异常！");
            return out;
        }

        return out;
    }

    /**
     * @Title delUser
     * @Author chunchen2
     * @Description // 删除用户
     * @Date 3:37 2023/3/5
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo delUser(EiInfo eiInfo){
        EiInfo out = new EiInfo();

        String userId = eiInfo.getString("userId");

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.DEL_USER_INFO +
                "/" + userId + "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.getForJSON(restTemplate, requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(-301);
            out.setMsg("删除用户调用异常！");
            return out;
        }

        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 未成功调用
                out.setStatus(-1);
                out.setMsg(response.getString("message"));
                return out;
            }
            String message = response.getString("message");

            out.setMsg(message);
            out.setStatus(Integer.parseInt(status));
            out.set("result", response.get("data"));
        } catch (Exception e) {
            out.setStatus(-302);
            out.setMsg("导入用户返回值解析异常！");
            return out;
        }

        return out;
    }

    /**
     * @Title freezeUser
     * @Author chunchen2
     * @Description // 冻结用户
     * @Date 3:58 2023/3/5
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo freezeUser(EiInfo eiInfo){
        EiInfo out = new EiInfo();

        String userId = eiInfo.getString("userId");

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.FREEZE_USER +
                "/" + userId + "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.getForJSON(restTemplate, requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(-301);
            out.setMsg("冻结用户调用异常！");
            return out;
        }

        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 未成功调用
                out.setStatus(-1);
                out.setMsg(response.getString("message"));
                return out;
            }
            String message = response.getString("message");

            out.setMsg(message);
            out.setStatus(Integer.parseInt(status));
            out.set("result", response.get("data"));
        } catch (Exception e) {
            out.setStatus(-302);
            out.setMsg("导入用户返回值解析异常！");
            return out;
        }

        return out;
    }

    /**
     * @Title unFreezeUser
     * @Author chunchen2
     * @Description // 解冻用户
     * @Date 3:59 2023/3/5
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo unFreezeUser(EiInfo eiInfo){
        EiInfo out = new EiInfo();

        String userId = eiInfo.getString("userId");

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.UN_FREEZE_USER +
                "/" + userId + "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.getForJSON(restTemplate, requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(-301);
            out.setMsg("冻结用户调用异常！");
            return out;
        }

        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 未成功调用
                out.setStatus(-1);
                out.setMsg(response.getString("message"));
                return out;
            }
            String message = response.getString("message");

            out.setMsg(message);
            out.setStatus(Integer.parseInt(status));
            out.set("result", response.get("data"));
        } catch (Exception e) {
            out.setStatus(-302);
            out.setMsg("导入用户返回值解析异常！");
            return out;
        }

        return out;
    }

    /**
     * @Title getUserInfo
     * @Author chunchen2
     * @Description // 获取用户
     * @Date 15:59 2023/3/2
     * @param
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getUserInfo(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        Map<String, String> inquMaps = eiInfo.getRow("inqu_status", 0);
        String userId = inquMaps.get("userId");
        String userIdcard = inquMaps.get("userIdcard");
        String userPhone = inquMaps.get("userPhone");

        Map<String, Object> params = new HashMap<>();
        if(StringUtils.isNotEmpty(userId)) {
            params.put("userId", userId);
        }

        if(StringUtils.isNotEmpty(userIdcard)) {
            params.put("userIdcard", userIdcard);
        }

        if(StringUtils.isNotEmpty(userPhone)) {
            params.put("userPhone", userPhone);
        }

        params.put("isBackSignCert", "0");
        if(params.size() <= 1) {
            out.setStatus(ErrorTips.PARAMS_NOT_NULL_CODE);
            out.setMsg(ErrorTips.PARAMS_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.GET_USER_INFO +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(-303);
            out.setMsg("获取用户信息接口调用异常！");
            return out;
        }

        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 未成功调用
                out.setStatus(-1);
                out.setMsg(response.getString("message"));
                return out;
            }
        } catch (Exception e) {
            out.setStatus(-304);
            out.setMsg("查询用户接口返回值解析异常！");
            return out;
        }

        Map<String, String> data = (HashMap)response.get("data");
        data = buildUserInfoOut(data);

        out.setStatus(ErrorTips.SUCCESS_CODE);
        out.setMsg(ErrorTips.SUCCESS_TIPS);
        out.set("result", data);

        return out;
    }

    public Map<String, String> buildUserInfoOut(Map<String, String> data){
        String realNameStatus = data.get("realNameStatus");
        String isAuthValid = data.get("isAuthValid");
        String status = data.get("status");

        // 0为已实名认证，-1为未认证
        if(StringUtils.isNotEmpty(realNameStatus)) {
            if("0".equalsIgnoreCase(realNameStatus)){
                realNameStatus = "已实名认证";
            } else if("-1".equalsIgnoreCase(realNameStatus)){
                realNameStatus = "未认证";
            }
            data.put("realNameStatus", realNameStatus);
        }

        // 0: 不存在;  1: 存在
        if(StringUtils.isNotEmpty(isAuthValid)) {
            if("0".equalsIgnoreCase(isAuthValid)){
                isAuthValid = "不存在有效临时授权密钥";
            } else if("1".equalsIgnoreCase(isAuthValid)){
                isAuthValid = "存在有效临时授权密钥";
            }
            data.put("isAuthValid", isAuthValid);
        }

        // -1冻结; 0:停用; 1:正常;
        if(StringUtils.isNotEmpty(status)) {
            if("0".equalsIgnoreCase(status)) {
                status = "停用";
            } else if("1".equalsIgnoreCase(status)) {
                status = "正常";
            } else if("-1".equalsIgnoreCase(status)) {
                status = "冻结";
            }
            data.put("status", status);
        }

        return data;
    }


}
