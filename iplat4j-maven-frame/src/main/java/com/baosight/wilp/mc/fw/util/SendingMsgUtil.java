package com.baosight.wilp.mc.fw.util;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;

import java.util.*;

/**
 * 短信发送工具类
 *  作者：hcd
 */
public class SendingMsgUtil {

	private static final Logger logger = Logger.getLogger(SendingMsgUtil.class);
	/**
	 * 1.先读取配置文件参数，启动时加载 hospital:医院 priority:优先级
	 */
	private static String hospital = PlatApplicationContext.getProperty("hospital");
	private static String priority = PlatApplicationContext.getProperty("priority");
	private static String pushMsgUrl = PlatApplicationContext.getProperty("pushMsgUrl");

	/**
	 * 2.封装pushMsg方法
	 * 
	 * @param phone
	 * @param msg
	 * @param module
	 * @param
	 */
	public static String sendingMsg(String phone, String msg, String module) {
		// 获取参数电话号码、短信信息、模块
		String uuid = UUID.randomUUID().toString();
		String bill = UUID.randomUUID().toString();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("hospital", hospital);
		map.add("id", uuid);
		map.add("phones", phone);
		map.add("module", module);
		map.add("bill", bill);
		map.add("priority", priority);
		map.add("sendcontent", msg);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(pushMsgUrl, request, String.class);
		// 获取服务器返回信息,保存到map中
		int code = response.getStatusCodeValue();

		if (code == 200) {
			code = 1;
		} else {
			code = 0;
		}

		return String.valueOf(code);
	}

	public static String editMessage(String message, List<String> list) {

		int count = verificationStr(message);
		// 验证传入的参数和模板的变量是否匹配
		if (count / 2 == list.size()) {
			String regex = "#.*?#";
			for (int i = 0; i < list.size(); i++) {
				message = message.replaceFirst(regex, list.get(i));
			}
			return message;
		} else {
			return "-1";
		}
	}

	public static int verificationStr(String str) {

		char searchChar = '#';
		int count = 0;
		char[] charArray = str.toCharArray();
		for (char item : charArray) {
			if (item == searchChar) {
				count++;
			}
		}
		return count;
	}

	public static List<Map<String, Object>> obtainParam(String str) {

		String[] strs = str.split("#");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < strs.length; i++) {

			if (i % 2 != 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("paramName", "#" + strs[i] + "#");
				map.put("paramValue", "");
				list.add(map);
			}
		}
		return list;
	}

}
