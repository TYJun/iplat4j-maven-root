package com.baosight.wilp.ac.jk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ac.jk.util.HttpClientToInterface;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.io.IOException;

public class ServiceACJK01 extends ServiceBase {
//    @ResponseBody
//    public static String getLYMsg(HttpServletRequest request) throws Exception{
//        final String url = "http://199.168.200.136/esbsvc.asmx/EsbApi?msg=<Message id=\"284\" code=\"S0010\" name=\"ProviderInfoQuery\" appid=\"\"><REQUEST><UNIQUE_ID></UNIQUE_ID><STAFF_CODE></STAFF_CODE><UPDATE_DATE>2021-03-28</UPDATE_DATE></REQUEST></Message>";
//        X509TrustManager tm = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        };
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        // 初始化SSL上下文
//        sslContext.init(null, new TrustManager[] { tm }, null);
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
//        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).setMaxConnTotal(50)
//                .setMaxConnPerRoute(50).setDefaultRequestConfig(RequestConfig.custom()
//                        .setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build())
//                .build();
//        HttpPost httppost = new HttpPost(url);
//        try {
////            httppost.addHeader("x-hw-id","7097d427-b2be-4448-9b24-a836fd1eaa6a");
////            httppost.addHeader("x-hw-appkey","465193735a659c5800c9a200f8bcbb99abb20e8119f5684afab2334082211c87");
//            //  logger.info("httpsPost ===**********===>>> " + EntityUtils.toString(httppost.getEntity()));
//            HttpResponse response = httpclient.execute(httppost);
//            String strResult = "";
//            if (response.getStatusLine().getStatusCode() == 200) {
//                strResult = EntityUtils.toString(response.getEntity());
//                JSONArray data = JSON.parseArray(strResult);
//                String st = data.get(0).toString();
//                return st;
//            } else {
//                //   logger.info("Error Response:" + response.getStatusLine().toString());
//                return "Error Response: " + response.getStatusLine().toString();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "post failure :caused by-->" + e.getMessage().toString();
//        }finally {
//            if(null != httpclient){
//                try {
//                    httpclient.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
public EiInfo getLYMsg(EiInfo inInfo){
    HttpClientToInterface.doGet("http://199.168.200.136/esbsvc.asmx/EsbApi?msg=<Message id=\"284\" code=\"S0010\" name=\"ProviderInfoQuery\" appid=\"\"><REQUEST><UNIQUE_ID></UNIQUE_ID><STAFF_CODE></STAFF_CODE><UPDATE_DATE>2021-03-28</UPDATE_DATE></REQUEST></Message>","UTF-8");
    EiInfo out = new EiInfo();
    out.setMsg("200");
    return out;
}
}
