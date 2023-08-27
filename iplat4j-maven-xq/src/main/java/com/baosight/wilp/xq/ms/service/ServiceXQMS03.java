package com.baosight.wilp.xq.ms.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.wilp.authentication.service.TokenService;
import com.baosight.wilp.entity.ErrorTips;
import com.baosight.wilp.entity.ResTemplateUtils;
import com.baosight.wilp.entity.URLConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.xq.ms.service
 * @ClassName ServiceXQMS03
 * @Description 文件和查验接口
 * @Author chunchen2
 * @Date 2023/2/28 17:08
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/28 17:08
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceXQMS03 extends ServiceEPBase {

    @Autowired
    TokenService tokenService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * @Title getSignImageByFileCode
     * @Author chunchen2
     * @Description // 根据文件编号，获取对应的签名图片
     * @Date 15:23 2023/3/1
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo getSignImageByFileCode(EiInfo eiInfo) {

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

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.DOWNLOAD_SIGN_IMAGE +
                "?accessToken=" + accessToken + "&fileCode=" + fileCode;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.getForJSON(restTemplate, requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.SIGN_IMAGE_DOWNLOAD_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_IMAGE_DOWNLOAD_EXCEPTION_TIPS);
            return out;
        }

        String signatureImage = null;
        // 尝试获取响应里面的参数，判断是否只能调用，不在前端判断
        try {
            String status = response.getString("status");
            if(!"0".equalsIgnoreCase(status)) { // 未成功调用
                out.setStatus(Integer.parseInt(status));
                out.setMsg(response.getString("message"));
                return out;
            }

            signatureImage = response.getJSONObject("data").getString("signatureImage");
            System.out.println("signatureImage: " + signatureImage);
        } catch (Exception e) {
            out.setStatus(ErrorTips.SIGN_IMAGE_DOWNLOAD_RESPONSE_EXCEPTION_CODE);
            out.setMsg(ErrorTips.SIGN_IMAGE_DOWNLOAD_RESPONSE_EXCEPTION_TIPS);
            return out;
        }

        out.setStatus(ErrorTips.SUCCESS_CODE);
        out.setMsg(ErrorTips.SUCCESS_TIPS);
        out.set("data", signatureImage);
        return out;
    }

    /**
     * @Title verifySignData
     * @Author chunchen2
     * @Description // 验证数据是否已经签名，根据fileCode 校验
     *              isHash:0
     *              isBackSignatureImg:0
     * @Date 13:37 2023/3/6
     * @param eiInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo verifySignData(EiInfo eiInfo) {
        EiInfo out = new EiInfo();

        // 校验文件业务名称fileCode，必填
        String fileCode = eiInfo.getString("fileCode");
        if(StringUtils.isEmpty(fileCode)) {
            out.setStatus(ErrorTips.FILE_CODE_NOT_NULL_CODE);
            out.setMsg(ErrorTips.FILE_CODE_NOT_NULL_TIPS);
            return out;
        }

        // isHash 签名时，原文是否经过摘要运算(默认为:0)
        String isHash = eiInfo.getString("isHash");

        // isBackSignatureImg: 0:不返回;   1:返回
        String isBackSignatureImg = eiInfo.getString("isBackSignatureImg");

        // 校验访问令牌
        String accessToken = tokenService.getAccessToken();
        if(null == accessToken) {
            out.setStatus(ErrorTips.ACCESS_TOKEN_NOT_NULL_CODE);
            out.setMsg(ErrorTips.ACCESS_TOKEN_NOT_NULL_TIPS);
            return out;
        }

        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN +  URLConstants.VERIFY_DATA +
                "?accessToken=" + accessToken;

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl,
                    buildVerifyDataParams(fileCode, isHash, isBackSignatureImg));
        } catch (Exception e) {
            e.printStackTrace();
            out.setStatus(ErrorTips.VERIFY_DATA_EXCEPTION_CODE);
            out.setMsg(ErrorTips.VERIFY_DATA_EXCEPTION_TIPS);
            return out;
        }

        String status = response.getString("status");
        String message = response.getString("message");
        Object data = response.get("data");

        out.setStatus(Integer.parseInt(status));
        out.setMsg(message);
        out.set("data", data);

        return out;
    }

    public Map<String, Object> buildVerifyDataParams(String fileCode, String isHash, String isBackSignatureImg){

        Map<String, Object> params = new HashMap<>();
//        params.put("signedData", "");
//        params.put("data", "");
//        params.put("cert", "");
        params.put("fileCode", fileCode);
        params.put("isHash", "0");
        if(null != isHash){
            params.put("isHash", isHash);
        }
        params.put("isBackSignatureImg", "0");
        if(null != isBackSignatureImg){
            params.put("isBackSignatureImg", isBackSignatureImg);
        }

        return params;
    }

}
