package com.baosight.wilp.mc.fw.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.UUID;

/**
 * 个推发送工具类
 * 作者：hcd
 * 
 */
public class PushMsgUtil {

	private static final Logger logger = Logger.getLogger(PushMsgUtil.class);
	/**
	 * 1.先读取配置文件参数，启动时加载 hospital:医院 priority:优先级
	 */
	private static String pushAppUrl = PlatApplicationContext.getProperty("pushAppUrl");

	public static String httpPost(String url, String data, String token) throws Exception {

		logger.info("HttpClientUtil    url: " + url);

		InputStream instream = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer("");
		HttpClient httpClient = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		try {

			httpPost.setRequestHeader("Content-Type", "application/json");
			if (token != null) {
				httpPost.setRequestHeader("token", token);// 7b5fd8cd04f76f7258fa0e8dc466a31919916c34a0734223d8c52b6102c1d294
			}

			RequestEntity requestEntity = new StringRequestEntity(data, "application/json", "UTF-8");
			httpPost.setRequestEntity(requestEntity);
			httpClient.executeMethod(httpPost);

			if (httpPost.getStatusCode() == HttpStatus.SC_OK) {
				Header headers[] = httpPost.getResponseHeaders();
				for (Header header : headers) {
					logger.info(header.getName() + "||" + header.getValue());
				}
				instream = httpPost.getResponseBodyAsStream();
				if (instream == null) {
					logger.error("Result is NULL. URL:" + url);
				}

				in = new BufferedReader(new InputStreamReader(instream, "UTF-8"));

				String line = null;
				while ((line = in.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}

			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("HttpClientUtil-Exception: ", e);
			}
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}

			httpPost.releaseConnection();
		}
		logger.info("结束调用：HttpClientUtil的httpPostWithJSON方法" + " [HttpStatus：" + httpPost.getStatusCode() + "]");

		return sb.toString();
	}

	/**
	 * 封装方法
	 *  作者：hcd
	 * @param
	 */
	public static String obtainToken(String appkey, String appId, String mastersecret) {

		String token = null;

		// appkey = "E6KuUsqrCB8hqlkr67nfs4";
		// mastersecret = "ricakhP98c5vyBUlKihxV4";
		// appId = "aLBzaBGask6JeQ1wQH5mi1";

//		String url = "https://restapi.getui.com/v2" + "/" + appId + "/" + "auth";
		String url = pushAppUrl + "/v2" + "/" + appId + "/" + "auth";

		long timestamp = Calendar.getInstance().getTimeInMillis();

		String sign = getSHA256(appkey + timestamp + mastersecret);

		JSONObject m = new JSONObject();
		m.put("sign", sign);
		m.put("timestamp", timestamp);
		m.put("appkey", appkey);

		try {
			String result = httpPost(url, m.toJSONString(), null);
			System.out.println(" ############################################### result=" + result);
			JSONObject resultJson = JSON.parseObject(result);

			String code = resultJson.get("code").toString();

			if ("0".equals(code)) {
				token = JSON.parseObject(resultJson.get("data").toString()).get("token").toString();
			} else {
				token = "获取token失败,请重新获取!";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return token;
	}

	/**
	 *  作者：hcd
	 * @param msg
	 * 
	 * @param
	 */
	public static String pushMsg(String msg, JSONArray cid, String appId, String token, String url) {
		String code = null; 
		String pushMsgUrl = pushAppUrl + "/v2" + "/" + appId + "/" + "push/single/cid";
//		String pushMsgUrl = "https://restapi.getui.com" + "/v2" + "/" + appId + "/" + "push/single/cid";
		for (Object o : cid) {
			
			JSONObject aa = new JSONObject();
			aa.put("default", 1);
			aa.put("ios", 4);
			aa.put("st", 3);
			aa.put("hw", 3);
			aa.put("xm", 3);
			aa.put("vv", 3);
			aa.put("mz", 3);
			aa.put("op", 3);
			
			JSONObject bb = new JSONObject();
			bb.put("strategy", aa);
			bb.put("ttl", 3600000);
			
			JSONObject a = new JSONObject();
			a.put("title", "通知");
			a.put("body", msg);
			if (url == null || "".equals(url)) {
				a.put("click_type", "none");
				// a.put("url", "a.com");
			} else {
				a.put("click_type", "url");
				a.put("url", url);// https://docs.getui.com/getui/server/rest_v2/push/
			}

			JSONObject b = new JSONObject();
			b.put("notification", a);

			JSONObject c = new JSONObject();
			c.put("push_message", b);
			
			c.put("settings", bb);
			
			JSONObject notification = new JSONObject();
			notification.put("title", "通知");
			notification.put("body", msg);
			notification.put("click_type", "url");
			notification.put("url", "https://qiye.163.com/");
//			notification.put("url", "www.baidu.com");
			
			JSONObject ups = new JSONObject();
			ups.put("notification", notification);
			
			JSONObject android = new JSONObject();
			android.put("ups", ups);
			
			JSONObject alert = new JSONObject();
			alert.put("title", "通知");
			alert.put("body", msg);
			
			
			JSONObject aps = new JSONObject();
			aps.put("alert", alert);
			aps.put("content-available", 0);
			
			JSONObject ios = new JSONObject();
			ios.put("type", "notify");
			ios.put("payload", "自定义消息");
			ios.put("aps", aps);
			ios.put("auto_badge", "+1");
			
			JSONObject push_channel = new JSONObject();
			push_channel.put("ios", ios);
			push_channel.put("android", android);
			
			c.put("push_channel", push_channel);

			JSONObject d = new JSONObject();
			JSONArray objects = new JSONArray();
			objects.add(o);
			d.put("cid", objects); // 88efe08d04d297b4579e614699650437
			c.put("audience", d);

			c.put("request_id", UUID.randomUUID().toString().replace("-", "").toLowerCase());

			System.out.println(c);
			
			logger.info("消息格式: " + c);

			try {
				String result = httpPost(pushMsgUrl, c.toJSONString(), token);
				
				logger.info("result: " + result);

				JSONObject resultJson = JSON.parseObject(result);
				code = resultJson.get("code").toString();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "推送消息失败!"+e.getMessage();
			}
		}

		return code;
	}

	public static void main(String[] args) {
		
		
		String appkey = "0G3NRzze8G7hPTHxfrwVM5";
		String mastersecret = "F8pcYEEpCd8vInSkxbU2N8";
		String appId = "cTOY4xXHzu7fzy7cC3J7k3";
		
//		String token = obtainToken( appkey,  appId,  mastersecret);
		
		String token = "c8fd2335b0dd69c075949f120b10f46303f3e4c818a9a3ec3211366ffdf87e22";
		
		JSONArray cid = new JSONArray();
//		cid.add("120bebe91741cd400095b1e6e0df75d6");
//		cid.add("37402578bc91d629c993484df57664ad");
		cid.add("d16d9ea22f9cbf99ef1bff1a3db78a1a");
		
		String result = pushMsg("测试1111111", cid, appId, token,"");


	

		System.out.println(result);
	}

	/**
	 * 利用java原生的类实现SHA256加密
	 *
	 * @param str 参数拼接的字符串
	 * @return
	 */
	public static String getSHA256(String str) {
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodeStr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}

	/**
	 * 将byte转为16进制
	 *
	 * @param bytes
	 * @return
	 */
	private static String byte2Hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		String temp = null;
		for (byte aByte : bytes) {
			temp = Integer.toHexString(aByte & 0xFF);
			if (temp.length() == 1) {
				// 1得到一位的进行补0操作
				sb.append("0");
			}
			sb.append(temp);
		}
		return sb.toString();
	}

}
