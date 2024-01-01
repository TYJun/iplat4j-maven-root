package com.baosight.wilp.xq.ms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.wilp.authentication.service.TokenService;
import com.baosight.wilp.cache.AuthKeyCacheManage;
import com.baosight.wilp.entity.*;
import com.baosight.wilp.util.MedicalSignBase64Utils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @PackageName com.baosight.wilp.xq.ms.service
 * @ClassName ServiceXQMS02
 * @Description 数据电子签名服务
 * @Author chunchen2
 * @Date 2023/2/27 15:34
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/27 15:34
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceXQMS02 extends ServiceEPBase {

    @Autowired
    TokenService tokenService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * @Title signData
     * @Author chunchen2
     * @Description // 单条数据电子签名接口
     * @Date 15:42 2023/2/27
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo signData(EiInfo eiInfo) {

        EiInfo out = new EiInfo();

        // 校验用户工号，必填
        String userId = eiInfo.getString("userId");
        if(StringUtils.isEmpty(userId)) {
            out.setStatus(ErrorTips.USER_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.USER_ID_NOT_NULL_TIPS);
            return out;
        }

        // 校验身份授权事务id，必填
        String transactionId = eiInfo.getString("transactionId");
        if(StringUtils.isEmpty(transactionId)) {
            out.setStatus(ErrorTips.TRANSACTION_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.TRANSACTION_ID_NOT_NULL_TIPS);
            return out;
        }

        // 校验文件业务名称，必填
        String fileName = eiInfo.getString("fileName");
        if(StringUtils.isEmpty(fileName)) {
            out.setStatus(ErrorTips.FILE_NAME_NOT_NULL_CODE);
            out.setMsg(ErrorTips.FILE_NAME_NOT_NULL_TIPS);
            return out;
        }

        // 大数据要传入isHash=1
        String isHash = eiInfo.getString("isHash");

        // 等待签名的数据原文不能为空
        String data = eiInfo.getString("data");
        data = MedicalSignBase64Utils.StringToBase64(data);
        /** 测试构造data 数据 start
        Map<String, String> signMap = new HashMap<>();
        signMap.put("fileName", "梅州市人民医院固定资产报废申请表");
        signMap.put("资产名称", "外衣");
        signMap.put("数量", "2");
        signMap.put("使用科室", "消毒供应科");
        signMap.put("资产编码", "C0000176");
        signMap.put("规格", "超大号XL");
        signMap.put("启用日期", "2006-01-12");
        signMap.put("资产原值（元）", "380.00");
        signMap.put("资产净值（元）", "0.00");
        signMap.put("已用年限", "18");
        signMap.put("厂家产品编、序号", "编号001");
        signMap.put("申请报废原因", "测试暂无");
        signMap.put("申请报废科室负责人签名时间", "2023-03-01");
        data = JSON.toJSONString(signMap);
         测试构造data 数据 end **/

        if(StringUtils.isEmpty(data)) {
            out.setStatus(ErrorTips.SIGN_DATA_NOT_NULL_CODE);
            out.setMsg(ErrorTips.SIGN_DATA_NOT_NULL_TIPS);
            return out;
        }

        // 校验临时授权Key
        String authKEY = eiInfo.getString("authKEY");
        if(StringUtils.isEmpty(authKEY)) {
            // 前台未传入， 根据工号从缓存中尝试获取临时Key
            List<AuthKeyEitity> authKeyEitities = AuthKeyCacheManage.get(userId);
            if(null != authKeyEitities && authKeyEitities.size() > 0) {
                authKEY = authKeyEitities.get(0).getAuthKEY();
            }

            if(StringUtils.isEmpty(authKEY)) {
                out.setStatus(ErrorTips.AUTH_KEY_NOT_NULL_CODE);
                out.setMsg(ErrorTips.AUTH_KEY_NOT_NULL_TIPS);
                return out;
            }
        }

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        Map<String, Object> params = buildSignDataParams(userId, transactionId, authKEY, fileName, data, isHash);

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.SIGN_DATA +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.SIGN_DATA_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_DATA_EXCEPTION_TIPS);
            return out;
        }

        SignDataItem signDataItem = null;
        // 尝试获取响应里面的参数，判断是否能正确的获取到返回的数据
        try {
            signDataItem = JSON.parseObject(response.getJSONObject("data").toJSONString(), SignDataItem.class);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.SIGN_DATA_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_DATA_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        signDataItem.setUserId(userId);
        // signDataItem.setData(data); 原始数据不存
        signDataItem.setTransactionId(transactionId);
        //signDataItem.setAuthKEY(authKEY);

        // 签名结果存入数据库  signDataItem
        // 数据库存储字段 fileName userId transactionId  fileCode signCert timestamp

        Map<String, Object> insertParam = new HashMap<>();
        try {
            User user = UserSession.getUser();
            String username = user.getUsername();
            insertParam.put("projectSchema", PlatApplicationContext.getProperty("projectSchema"));
            insertParam.put("signDataItem", signDataItem);
            insertParam.put("username", username);
            insertParam.put("createTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            dao.insert("XQMS02.insertSignDataRecords", insertParam);
        } catch (Exception e) {
            e.printStackTrace(); // 打印一下原始数据
            System.out.println("签名保存数据库结果异常数据：" + insertParam);
            out.setStatus(ErrorTips.SIGN_DATA_RESULT_SAVE_DB_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_DATA_RESULT_SAVE_DB_EXCEPTION_TIPS);
            return out;
        }

        out.set("result", signDataItem);
        out.setStatus(ErrorTips.SUCCESS_CODE);
        out.setMsg(ErrorTips.SUCCESS_TIPS);

        return out;
    }

    /**
     * @Title multiSignData
     * @Author chunchen2
     * @Description // 批量数据签名
     * @Date 16:17 2023/2/28
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo multiSignData(EiInfo eiInfo) {

        EiInfo out = new EiInfo();

        // 校验用户工号，必填
        String userId = eiInfo.getString("userId");
        if(StringUtils.isEmpty(userId)) {
            out.setStatus(ErrorTips.USER_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.USER_ID_NOT_NULL_TIPS);
            return out;
        }

        // 校验身份授权事务id，必填
        String transactionId = eiInfo.getString("transactionId");
        if(StringUtils.isEmpty(transactionId)) {
            out.setStatus(ErrorTips.TRANSACTION_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.TRANSACTION_ID_NOT_NULL_TIPS);
            return out;
        }

        /** 批量签名的数据原文不能为空
        Map<String, String> data1 = new HashMap<>();
        data1.put("fileName", "测试数据0003");
        data1.put("data", "测试内容1:785723912;姓名:张三;性别:男;就诊时间:2020-3-5;结果:健康");

        Map<String, String> data2 = new HashMap<>();
        data2.put("fileName", "测试数据0004");
        data2.put("data", "测试内容2:785723912;姓名:李四;性别:男;就诊时间:2024-3-5;结果:住院");

        List<Map<String, String>> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
         **/

        Object dataList = eiInfo.get("dataList");
        //dataList = list;
        if(null == dataList) {
            out.setStatus(ErrorTips.MULTI_SIGN_DATA_NOT_NULL_CODE);
            out.setMsg(ErrorTips.MULTI_SIGN_DATA_NOT_NULL_TIPS);
            return out;
        }

        // 校验临时授权Key
        String authKEY = eiInfo.getString("authKEY");
        if(StringUtils.isEmpty(authKEY)) {
            // 前台未传入， 根据工号从缓存中尝试获取临时Key
            List<AuthKeyEitity> authKeyEitities = AuthKeyCacheManage.get(userId);
            if(null != authKeyEitities && authKeyEitities.size() > 0) {
                authKEY = authKeyEitities.get(0).getAuthKEY();
            }

            if(StringUtils.isEmpty(authKEY)) {
                out.setStatus(ErrorTips.AUTH_KEY_NOT_NULL_CODE);
                out.setMsg(ErrorTips.AUTH_KEY_NOT_NULL_TIPS);
                return out;
            }
        }

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        // 大数据要传入isHash=1
        String isHash = eiInfo.getString("isHash");

        Map<String, Object> params = buildMultiSignDataParams(userId, transactionId, authKEY, dataList, isHash);

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.MULTI_SIGN_DATA +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.SIGN_DATA_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_DATA_EXCEPTION_TIPS);
            return out;
        }

        List<SignDataItem> signDataItemLists = null;
        // 尝试获取响应里面的参数，判断是否能正确的获取到返回的数据
        try {
            signDataItemLists = JSON.parseArray(response.getJSONArray("data").toJSONString(),
                    SignDataItem.class);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.SIGN_DATA_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_DATA_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        for(SignDataItem signDataItem: signDataItemLists){
            signDataItem.setUserId(userId);
            //signDataItem.setData(data);
            signDataItem.setTransactionId(transactionId);
            signDataItem.setAuthKEY(authKEY);
        }

        // 签名结果存入数据库  signDataItem
        Map<String, Object> insertParam = new HashMap<>();
        try {
            User user = UserSession.getUser();
            String username = user.getUsername();
            insertParam.put("projectSchema", PlatApplicationContext.getProperty("projectSchema"));
            insertParam.put("signDataItemLists", signDataItemLists);
            insertParam.put("username", username);
            insertParam.put("createTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            dao.insert("XQMS02.insertMultiSignDataRecords", insertParam);
        } catch (Exception e) {
            e.printStackTrace(); // 打印一下原始数据
            System.out.println("签名保存数据库结果异常数据：" + insertParam);
            out.setStatus(ErrorTips.SIGN_DATA_RESULT_SAVE_DB_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_DATA_RESULT_SAVE_DB_EXCEPTION_TIPS);
            return out;
        }

        out.set("data", signDataItemLists);
        out.setStatus(ErrorTips.SUCCESS_CODE);
        out.setMsg(ErrorTips.SUCCESS_TIPS);

        return out;

    }


    /**
     * @Title getSignStatus
     * @Author chunchen2
     * @Description // 根据文件的 fileCode 查询数据签名状态。
     * @Date 15:15 2023/3/1
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getSignStatus(EiInfo eiInfo) {

        EiInfo out = new EiInfo();

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        // 校验文件业务名称，必填
        String fileCode = eiInfo.getString("fileCode");
        if(StringUtils.isEmpty(fileCode)) {
            out.setStatus(ErrorTips.FILE_CODE_NOT_NULL_CODE);
            out.setMsg(ErrorTips.FILE_CODE_NOT_NULL_TIPS);
            return out;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("fileCode", fileCode);

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.GET_SIGN_STATUS +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.GET_SIGN_STATUS_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_SIGN_STATUS_EXCEPTION_TIPS);
            return out;
        }

        // 尝试获取响应里面的参数，判断是否能正确的获取到返回的数据
        JSONObject data = null;
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 返回值异常
                out.setStatus(Integer.parseInt(status));
                out.setMsg(response.getString("message"));
                return out;
            }

            data = response.getJSONObject("data");
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.GET_SIGN_STATUS_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_SIGN_STATUS_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        out.setStatus(ErrorTips.SUCCESS_CODE);
        out.setMsg(ErrorTips.SUCCESS_TIPS);
        out.set("data", data);
        return out;
    }

    /**
     * @Title getSignByTransactionId
     * @Author chunchen2
     * @Description // 根据 transactionId 获取 签名的文件信息
     * @Date 17:26 2023/3/1
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getSignByTransactionId(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        // 校验身份授权事务id，必填
        String transactionId = eiInfo.getString("transactionId");
        if(StringUtils.isEmpty(transactionId)) {
            out.setStatus(ErrorTips.TRANSACTION_ID_NOT_NULL_CODE);
            out.setMsg(ErrorTips.TRANSACTION_ID_NOT_NULL_TIPS);
            return out;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("transactionId", transactionId);

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.GET_SIGN_BY_TRANSACTIONID +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.GET_SIGN_BY_TRANSACTION_ID_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_SIGN_BY_TRANSACTION_ID_EXCEPTION_TIPS);
            return out;
        }

        // 尝试获取响应里面的参数，判断是否能正确的获取到返回的数据
        JSONObject data = null;
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 返回值异常
                out.setStatus(Integer.parseInt(status));
                out.setMsg(response.getString("message"));
                return out;
            }

            data = response.getJSONObject("data");
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.GET_SIGN_BY_TRANSACTION_ID_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.GET_SIGN_BY_TRANSACTION_ID_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        out.setStatus(ErrorTips.SUCCESS_CODE);
        out.setMsg(ErrorTips.SUCCESS_TIPS);
        out.set("data", data);
        return out;
    }

    /**
     * @Title cancelSignByFileCode
     * @Author chunchen2
     * @Description // 根据文件编号，撤销已经发起签名流程的业务，业务流程状态变成已取消
     * @Date 10:51 2023/3/2
     * @param
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo cancelSignByFileCode(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        // 校验文件业务名称，必填
        String fileCode = eiInfo.getString("fileCode");
        if(StringUtils.isEmpty(fileCode)) {
            out.setStatus(ErrorTips.FILE_CODE_NOT_NULL_CODE);
            out.setMsg(ErrorTips.FILE_CODE_NOT_NULL_TIPS);
            return out;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("fileCode", fileCode);

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.CANCEL_SIGN +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.CANCEL_SIGN_EXCEPTION_CODE);
            out.setMsg(ErrorTips.CANCEL_SIGN_EXCEPTION_TIPS);
            return out;
        }

        String status = response.getString("status");
        String message = response.getString("message");

        out.setStatus(Integer.parseInt(status));
        out.setMsg(message);
        return out;
    }

    /**
     * @Title delSignByFileCode
     * @Author chunchen2
     * @Description // 根据fileCode 删除签名
     * @Date 16:11 2023/3/6
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo delSignByFileCode(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        // 校验文件业务名称，必填
        String fileCode = eiInfo.getString("fileCode");
        if(StringUtils.isEmpty(fileCode)) {
            out.setStatus(ErrorTips.FILE_CODE_NOT_NULL_CODE);
            out.setMsg(ErrorTips.FILE_CODE_NOT_NULL_TIPS);
            return out;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("fileCode", fileCode);

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.DELETE_SIGN +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.CANCEL_SIGN_EXCEPTION_CODE);
            out.setMsg(ErrorTips.CANCEL_SIGN_EXCEPTION_TIPS);
            return out;
        }

        String status = response.getString("status");
        String message = response.getString("message");

        out.setStatus(Integer.parseInt(status));
        out.setMsg(message);
        return out;
    }

    private Map<String, Object> buildSignDataParams(String userId, String transactionId, String authKEY,
                                                    String fileName, String data, String isHash) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("transactionId", transactionId);
        params.put("authKEY", authKEY);
        params.put("fileName", fileName);
        params.put("data", data);
        params.put("isHash", "0"); // 0:原文签署; 1:原文摘要签署 数据大，改用1
        if(null != isHash) {
            params.put("isHash", isHash);
        }
        params.put("timestamp", "1"); // 0:不保存时间戳;   1:保存时间戳
        params.put("isBackTimestamp", "1"); // 0:不返回; 1返回;
        params.put("isBackSignatureImg", "1"); // 0:不返回;   1:返回
        params.put("isBackSignCert", "1"); // 0:不返回;   1:返回
        return params;
    }

    private Map<String, Object> buildMultiSignDataParams(String userId, String transactionId, String authKEY,
                                                   Object dataList, String isHash) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("transactionId", transactionId);
        params.put("authKEY", authKEY);
        params.put("dataList", dataList);
        params.put("isHash", "0"); // 0:原文签署; 1:原文摘要签署 数据大，改用1
        if(null != isHash){
            params.put("isHash", isHash);
        }
        params.put("timestamp", "1"); // 0:不保存时间戳;   1:保存时间戳
        params.put("isBackTimestamp", "1"); // 0:不返回; 1返回;
        params.put("isBackSignatureImg", "1"); // 0:不返回;   1:返回
        params.put("isBackSignCert", "1"); // 0:不返回;   1:返回
        return params;
    }

}
