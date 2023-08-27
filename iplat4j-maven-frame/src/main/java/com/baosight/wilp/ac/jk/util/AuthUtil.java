package com.baosight.wilp.ac.jk.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AuthUtil {

    public static JSONObject doGetJson(String url) throws Exception, IOException {
        JSONObject jsonObject=null;
        //初始化httpClient
        DefaultHttpClient client=new DefaultHttpClient();
        //用Get方式进行提交
        HttpGet httpGet=new HttpGet(url);
        //发送请求
        HttpResponse response= client.execute(httpGet);
        //获取数据
        HttpEntity entity=response.getEntity();
        //格式转换
        if (entity!=null) {
            String result= EntityUtils.toString(entity,"UTF-8");
            jsonObject=JSONObject.fromObject(result);
        }
        //释放链接
        httpGet.releaseConnection();
        return jsonObject;
    }
}
