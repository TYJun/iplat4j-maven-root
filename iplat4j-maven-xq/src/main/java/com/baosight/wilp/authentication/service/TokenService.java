package com.baosight.wilp.authentication.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.wilp.entity.ResTemplateUtils;
import com.baosight.wilp.entity.URLConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackageName com.baosight.wilp.authentication.service
 * @ClassName TokenService
 * @Description 获取令牌
 * @Author chunchen2
 * @Date 2023/2/22 16:20
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/22 16:20
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
@Component
public class TokenService implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    RestTemplate restTemplate;

    // 缓存token 令牌对象的map
    private Map<String, JSONObject> tokenCache =new ConcurrentHashMap<>();
    /**
     * 缓存时间：一小时55分钟, token实际 有效时间是2小时
     */
    private static final long CACHE_TTL = 60 * 55 * 2 * 1000;


    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化完毕，首先获取一下token,放入缓存
        getAccessToken();
    }

    /**
     * @Title getAccessToken
     * @Author chunchen2
     * @Description // 获取医信签的访问令牌
     * @Date 17:25 2023/2/22
     * @param
     * @return java.lang.String
     * @throws
     **/
    public String getAccessToken() {
        String appid = URLConstants.APP_ID;
        String appkey = URLConstants.APP_KEY;

        // 获取token 接口的url
        String requestUrl = URLConstants.MEDI_SIGN_DOMAIN + URLConstants.GET_TOKEN;

        String tokenKey =appid + "$$" +  appkey;
        String accessToken = getFromCache("access_token", tokenKey);
        if(null != accessToken) {
            return accessToken;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("appid", appid);
        params.put("appkey", appkey);

        JSONObject response = null;
        try {
            response = ResTemplateUtils.postForJson(restTemplate, requestUrl, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            accessToken = response.getJSONObject("data").getString("accessToken");
        } catch (Exception e) {
            logger.error("获取token异常：" + response);
        }

        if(null != accessToken) {
            // 存入缓存
            putToCache("access_token", accessToken, tokenKey);
        }

        return accessToken;
    }

    private void putToCache(String field, String value, String tokenKey) {
        JSONObject fieldObj = new JSONObject(2);
        fieldObj.put(field, value);
        fieldObj.put("begin_time", System.currentTimeMillis());

        tokenCache.put(tokenKey, fieldObj);
    }

    private String getFromCache(String field, String tokenKey){
        JSONObject jsonObject = tokenCache.get(tokenKey);

        if(jsonObject != null && System.currentTimeMillis() - jsonObject.getLong("begin_time") <= CACHE_TTL){
            return jsonObject.getString(field);
        }
        return null;
    }
}
