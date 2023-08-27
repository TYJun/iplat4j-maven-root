package com.baosight.wilp.ac.jk.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpPostUtils：通过httpPost访问
 * */
public class HttpPostUtils implements Serializable {

	private static final Logger logger = Logger.getLogger(HttpPostUtils.class);

	private static final long serialVersionUID = 1L;

	private static RequestConfig requestConfig = null;
	
	private static int timeout = 2000;

	public static int getTimeout() {
		return timeout;
	}

	public static void setTimeout(int timeout) {
		HttpPostUtils.timeout = timeout;
		requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
	}

	static {
		// 设置默认超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
	}
	
	/**
	 * doPost
	 * 
	 * @param       url:访问路径
	 * @param param : 请求参数
	 */
	public static JSONObject doPost(String url, Map<String, String> param) {
		JSONObject jsonResult= null;
		System.out.println("[timeout:" + timeout + "]" + " doPost : " + url);
		// HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			// httpPost
			HttpPost httpPost = new HttpPost(url);
			// 设置默认header,json类型
			httpPost.setHeader("Content-type", "application/json");
			// 参数
			if (param == null) {
				param = new HashMap<String, String>();
			}
			System.out.println("param : " + JSONObject.toJSONString(param));
			// httpPost使用SONString传参
			StringEntity requestEntity = new StringEntity(JSONObject.toJSONString(param), "utf-8");
			requestEntity.setContentEncoding("UTF-8");
			httpPost.setEntity(requestEntity);
			// HttpPost执行
			String returnValue = httpClient.execute(httpPost, responseHandler);
			System.out.println("PostReturn : " + returnValue);
			// 转换成jsonObject
			jsonResult = JSONObject.parseObject(returnValue);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("post请求提交失败:" + url, e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonResult;
	}

	public static void main(String[] args) {
		String url = "http://localhost:8084/iplat/meal";
		Map<String,String> param = new HashMap<String, String>();
		param.put("userName", "测试员");
		param.put("deptCode", "10001");
		param.put("idCardNo", "321251199901010001");
		param.put("op", "init");
		param.put("cardNum", "A900BD99");
		param.put("cardType", "work");
		param.put("methodName", "work");
		param.put("className", "work");
		JSONObject doPost = HttpPostUtils.doPost(url, param);
		System.out.println(doPost);
	}
}
