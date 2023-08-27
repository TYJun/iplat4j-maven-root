package com.baosight.wilp.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 日志工具类.
 * <p>
 * </p>
 *
 * @Title ArchivesLogUtil.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ArchivesLogUtil {
	private final static ObjectMapper objectMapper = new ObjectMapper();

	private ArchivesLogUtil() {

	}

	public static ObjectMapper getInstance() {
		return objectMapper;
	}

	/**
	 * javaBean、列表数组转换为json字符串
	 */
	public static String obj2json(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * json 转JavaBean
	 */

	public static <T> T json2pojo(String jsonString, Class<T> clazz) throws Exception {
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		return objectMapper.readValue(jsonString, clazz);
	}

	/**
	 * json字符串转换为map
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.readValue(jsonString, Map.class);
	}
}
