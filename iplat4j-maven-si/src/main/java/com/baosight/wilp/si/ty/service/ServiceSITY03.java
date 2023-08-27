package com.baosight.wilp.si.ty.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;

import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: ServiceSITY03
 * @Package com.baosight.wilp.si.ty.service
 * @date: 2023年03月08日 16:43
 */
public class ServiceSITY03 extends ServiceBase {

    public EiInfo checkAuthStatusByUserId(EiInfo inInfo) {
        String auth = "{\n" +
                "\"authEndTime\":\"2023-03-06 11:17:19\",\n" +
                "\"authKEY\":\"d2cf7b411d384634a6a08eba854ea7b2\",\n" +
                "\"transactionId\":\"ku6gg96jrz82waed\",\n" +
                "\"userId\":\"bonatest\",\n" +
                "\"userName\":\"陈春\"\n" +
                "}";
        Map resultMap = JSON.parseObject(auth, Map.class);
        //inInfo.set("auth", resultMap);
        inInfo.setStatus(-201);
        inInfo.setMsg("用户未授权");
        return inInfo;
    }

    public EiInfo signData(EiInfo inInfo) {
        String data = "{\n" +
                "\"certDN\":\"C=CN,ST=广东省,L=梅州市,O=梅州市人民医院,OU=测试科室,CN=陈春\",\n" +
                "\"certEndTime\":\"2024-02-0914:18:15\",\n" +
                "\"certIssuer\":\"HLJCA_SM2_SUB\",\n" +
                "\"certSN\":\"22655084221553741825026397011048823365742787\",\n" +
                "\"certStartTime\":\"2023-02-0914:18:15\",\n" +
                "\"fileCode\":\"202303-056c4e90c14946e9a31ccc11034df8272\",\n" +
                "\"fileName\":\"测试文件-20230306112221\",\n" +
                "\"signCert\":\"MIICpDCCAkqgAwIBAgIWImVQhCIVU3QYJQJjlwEQS=\",\n" +
                "\"signTime\":\"2023-03-0611:39:19\",\n" +
                "\"signatureImg\":\"iVBORw0KGgoAAAANSUhEUgAAArwAAAEOCAYYII=\",\n" +
                "\"signedData\":\"MEUCIFlm4qRhUfeG3CKR3t/7bUH5G3D5xHklR78CqO4MCcJiAiEA2N/bdXQl8HBpdexIBxYwvWiTpHvERnA0e79IZPJCkUM=\",\n" +
                "\"timestamp\":\"MIIEsjAVAgEAMBAMDk9wZXJhdGlvbiBPa2F5MIIElwYJKoZIhvcNAQU\",\n" +
                "\"transactionId\":\"b7fw6qqv3ou25yka\",\n" +
                "\"userId\":\"bonatest\"\n" +
                "}";
        Map resultMap = JSON.parseObject(data, Map.class);
        inInfo.set("result", resultMap);
        return inInfo;
    }

    public EiInfo scanOAuth(EiInfo inInfo) {
       String result = "{\n" +
               "            \"data\": {\n" +
               "                \"transactionId\": \"ku6gg96jrz82waed\",\n" +
               "                        \"oauthMPCode\": \"iVBORw0KGgoAAAANSUhEUgAAARgAA5CYII=\",\n" +
               "                        \"oauthWindowURL\": \"http://199.168.200.79:8091/h5/authwindow/index.html?t=ku6gg96jrz82waed\"\n" +
               "            },\n" +
               "            \"message\": \"success\",\n" +
               "                    \"status\": \"0\"\n" +
               "        }";
        Map resultMap = JSON.parseObject(result, Map.class);
        inInfo.set("result", resultMap);
        return inInfo;
    }

    public EiInfo checkScanStatus(EiInfo inInfo) {
        String data = "{\n" +
                "\"data\":{\n" +
                "\"oauthStatus\":\"1\",\n" +
                "\"userId\":\"bonatest\",\n" +
                "\"userName\":\"陈春\",\n" +
                "\"certDN\":\"C=CN,ST=广东省,L=梅州市,O=梅州市人民医院,OU=测试科室,CN=陈春\",\n" +
                "\"certSN\":\"22655084221553741825026397011048823365742787\",\n" +
                "\"certStartTime\":\"2023-02-0914:18:15\",\n" +
                "\"certEndTime\":\"2024-02-0914:18:15\",\n" +
                "\"authKEY\":\"d2cf7b411d384634a6a08eba854ea7b2\",\n" +
                "\"authTime\":\"30\",\n" +
                "\"expireTime\":\"412\",\n" +
                "\"authStartTime\":\"2023-03-0610:47:19\",\n" +
                "\"authEndTime\":\"2023-03-0611:17:19\",\n" +
                "\"idcard\":\"341181199502050811\",\n" +
                "\"transactionId\":\"ku6gg96jrz82waed\",\n" +
                "\"userPhone\":\"15056021503\",\n" +
                "\"officeName\":\"测试\",\n" +
                "\"authType\":\"1\",\n" +
                "\"oauthMethod\":\"3\",\n" +
                "\"optUserId\":\"bonatest\",\n" +
                "\"signatureImg\":\"iVBORw0KGgoAAAANSUhEUgAAArwAAAEOCAYAAAYII=\",\n" +
                "\"officeQyId\":\"\"\n" +
                "},\n" +
                "\"message\":\"success\",\n" +
                "\"status\":\"0\"\n" +
                "}";
        Map resultMap = JSON.parseObject(data, Map.class);
        inInfo.set("result", resultMap);
        return inInfo;
    }


}
