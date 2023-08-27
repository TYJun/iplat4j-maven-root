package com.baosight.wilp.ac.jk.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpClientUtil：通过httpClient访问
 * */
public class HttpClientUtil implements Serializable {

	private static final Logger logger = Logger.getLogger(HttpClientUtil.class);

	private static final long serialVersionUID = 1L;

	private static RequestConfig requestConfig = null;

	private static int timeout = 2000;

	public static int getTimeout() {
		return timeout;
	}

	public static void setTimeout(int timeout) {
		HttpClientUtil.timeout = timeout;
		requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
	}

	static {
		// 设置默认超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
	}

	
	/**
	 * @desc 获取客户端IP
	 * @param request
	 * @return	String
	 */
	public static String getClinetIp(HttpServletRequest request) {
		String IP = request.getHeader("X-Real-IP");
		if (StringUtils.isEmpty(IP)) {
			IP = request.getLocalAddr();
		}
		return IP;
	}
	
	public static String getLocalIp() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		String IP = request.getHeader("X-Real-IP");
		if (StringUtils.isEmpty(IP)) {
			IP = request.getLocalAddr();
		}
		return IP;
	}
	/**
	 * doPost
	 * 
	 * @param         url:访问路径
	 * @param headers : post请求header信息
	 * @param param   : 请求参数
	 * @param timeout : 连接超时时间
	 */
	public static com.baosight.wilp.ac.we.utils.RespResult doPost(String url, Map<String, String> headers, Map<String, String> param,
																  int timeout) {
		com.baosight.wilp.ac.we.utils.RespResult result = new com.baosight.wilp.ac.we.utils.RespResult("201", "操作失败！");
		System.out.println("doPost:" + url);
		// 设置请求和传输超时时间
		setTimeout(timeout);
		// HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			// httpPost
			HttpPost httpPost = new HttpPost(url);
			// 设置默认header,json类型
			httpPost.setHeader("Content-type", "application/json");
			// 设置请求头参数
			if (headers != null && !headers.isEmpty()) {
				for (String key : headers.keySet()) {
					httpPost.setHeader(key, headers.get(key));
				}
			}
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
			JSONObject jsonResult = JSONObject.parseObject(returnValue);
			result = new com.baosight.wilp.ac.we.utils.RespResult(jsonResult.getString("respCode"), jsonResult.getString("respMsg"));
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
		return result;
	}

	/**
	 * doPost
	 * 
	 * @param         url:访问路径
	 * @param param   : 请求参数
	 * @param timeout : 连接超时时间
	 */
	public static com.baosight.wilp.ac.we.utils.RespResult doPost(String url, Map<String, String> param, int timeout) {
		com.baosight.wilp.ac.we.utils.RespResult result = null;
		System.out.println("doPost:" + url);
		// 设置请求和传输超时时间
		setTimeout(timeout);
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
			JSONObject jsonResult = JSONObject.parseObject(returnValue);
			result = new com.baosight.wilp.ac.we.utils.RespResult(jsonResult.getString("respCode"), jsonResult.getString("respMsg"));
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
		return result;
	}

	/**
	 * doPost
	 * 
	 * @param       url:访问路径
	 * @param param : 请求参数
	 */
	public static com.baosight.wilp.ac.we.utils.RespResult doPost(String url, Map<String, String> param) {
		com.baosight.wilp.ac.we.utils.RespResult result = null;
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
			JSONObject jsonResult = JSONObject.parseObject(returnValue);
			result = new com.baosight.wilp.ac.we.utils.RespResult(jsonResult.getString("respCode"), jsonResult.getString("respMsg"));
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
		return result;
	}
	public static void main(String[] args) {
//		String url = "http://v5meal.yyhq365.cn/v5meal/service/S_PS_PC_ALI_01";
//		Map<String,String> param = new HashMap<String, String>();
//		param.put("billNo", "BH2021041300918");
//		ResponseResult doPost = HttpClientUtil.doPost(url, param);
//		System.out.println(doPost.getRespCode()+doPost.getRespMsg());
		System.out.println(getLocalIp());
	}
}
