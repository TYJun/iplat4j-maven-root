package com.baosight.wilp.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.entity
 * @ClassName ResTemplateUtils
 * @Description 封装一下restTemplate请求
 * @Author chunchen2
 * @Date 2023/2/22 16:52
 * @Version V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2023/2/22 16:52
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ResTemplateUtils {

    /**
     * @Title postForJson
     * @Author chunchen2
     * @Description // 请求参数、返回值类型是JSON对象
     * @Date 16:58 2023/2/22
     * @param restTemplate
     * @param requestUrl
     * @param params
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     **/
    public static JSONObject postForJson(RestTemplate restTemplate, String requestUrl,
                                         Map<String, Object> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(JSON.toJSONString(params),
                headers);

        return restTemplate.postForObject(requestUrl, httpEntity, JSONObject.class);
    }

    /**
     * @Title getForJSON
     * @Author chunchen2
     * @Description // get方式获取数据
     * @Date 16:08 2023/3/1
     * @param restTemplate
     * @param requestUrl
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     **/
    public static JSONObject getForJSON(RestTemplate restTemplate, String requestUrl){
        return restTemplate.getForObject(requestUrl, JSONObject.class);
    }

}
