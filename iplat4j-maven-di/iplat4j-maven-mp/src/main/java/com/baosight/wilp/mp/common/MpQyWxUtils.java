package com.baosight.wilp.mp.common;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: MpQyWxUtils
 * @Package com.baosight.wilp.mp.common
 * @date: 2023年07月17日 14:48
 */
public class MpQyWxUtils {

    /**企业微信： 企业ID */
    private static final String corpId = PlatApplicationContext.getProperty("work_wx_appid");

    /**企业微信： 企业密钥 */
    private static final String corpSecret = "64SL4XvUw1n1ALfeHwLc_Yj9OCi3xUNuObo-TOCYQ2U";

    /**企业微信： 地址 */
    private static final String baseUrl = PlatApplicationContext.getProperty("work_wx_api_url");

    /**accessToken缓存对象**/
    private static Map<String, Object> tokenMap = new HashMap<>(4);

    /**restTemplate模板对象**/
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取accessToken
     * @Title: getAccessToken
     * @return java.lang.String
     * @throws
     **/
    public static String getAccessToken(){
        if(!accessTokenIsEffect()) {
            tokenMap.put("accessToken", queryAccessToken());
            tokenMap.put("startTime", System.currentTimeMillis());
        }
        return MpUtils.toString(tokenMap.get("accessToken"));
    }

    /**
     * 获取accessToken
     * @Title: queryAccessToken
     * @return java.lang.String
     * @throws
     **/
    public static String queryAccessToken(){
        String accessToken = null;
        try{
            JSONObject response = restTemplate.getForObject(baseUrl + "gettoken?corpid="+ corpId + "&corpsecret=" + corpSecret, JSONObject.class);
            if(response.getInteger("errcode") != 0) {
                System.out.println("获取企业微信accessToken失败："+ response.getString("errmsg"));
            }
            accessToken = response.getString("access_token");
        } catch (Exception e){
            System.out.println("获取企业微信accessToken接口调用失败");
            e.printStackTrace();
        }
        return accessToken;
    }

    /**
     * 获取userId
     * @Title: getUserId
     * @param code code
     * @return java.lang.String
     * @throws
     **/
    public static String getUserId(String code) {
        String userId = null;
        try{
            JSONObject response = restTemplate.getForObject(baseUrl + "auth/getuserinfo?access_token="
                    + getAccessToken() + "&code=" + code, JSONObject.class);
            if(response.getInteger("errcode") != 0) {
                System.out.println("获取企业微信userId失败："+ response.getString("errmsg"));
            }
            userId = response.getString("userid");
        } catch (Exception e){
            System.out.println("获取企业微信userId接口调用失败");
            e.printStackTrace();
        }
        return userId;
    }

    /**
     * 判断accessToken是否有效
     * @Title: accessTokenIsEffect
     * @return boolean
     * @throws
     **/
    private static boolean accessTokenIsEffect() {
        long expiry = 5400000L;
        if(tokenMap.isEmpty()) {
            return false;
        }
        if(StringUtils.isBlank(MpUtils.toString(tokenMap.get("accessToken")))) {
            return false;
        }
        long startTime = NumberUtils.toLong(tokenMap.get("startTime"));
        long currentTime = System.currentTimeMillis();
        return startTime + expiry >= currentTime;
    }

}
