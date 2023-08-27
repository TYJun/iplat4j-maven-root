package com.baosight.wilp.ac.jk.util;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.Map;

/**
 * @PackageName com.bonawise.smp.bankABC.util
 * @ClassName ABCHttpClient
 * @Description TODO
 * @Author rck
 * @Date 2021/3/5 9:31
 * @Version 1.0
 * @History 修改记录1
 * <author> rck
 * <time> 2021/3/5 9:31
 * <version>
 */
public class ABCHttpClient {

    public static String sendHttpPost(String url, Map<String, Object> pMap){
        HttpClient client = new HttpClient();
        PostMethod httpPost = new PostMethod(url);
        String result = null;
        for(Map.Entry<String, Object> entry :pMap.entrySet()){
            httpPost.addParameter(entry.getKey(),entry.getValue().toString());
        }
        try {
            httpPost.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
            client.executeMethod(httpPost);
            result = new String(httpPost.getResponseBody(),"GBK");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
            ((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
        }
        return result;
    }
}
