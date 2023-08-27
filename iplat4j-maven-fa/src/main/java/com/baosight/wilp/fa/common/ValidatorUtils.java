package com.baosight.wilp.fa.common;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年11月24日 16:39
 */
public class ValidatorUtils {

	private static Validator validator;

	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public static EiInfo validatorEntity(Map<String,?> map, Class<DaoEPBase> clazz,Class<?>... groups){
		if (clazz == null){
			return errorInfo("无法校验错误");
		}
		try {
			DaoEPBase instance = clazz.newInstance();
			instance.fromMap(map);
			return validateEntity(instance, groups);
		} catch (Exception e) {
			return errorInfo("无法校验错误");
		}
	}

	/**
	 * 校验实体参数对象
	 * @Title: validateEntity
	 * @param object object 待校验对象
	 * @param groups groups 待校验的组
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo validateEntity(Object object, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			for(ConstraintViolation<Object> constraint:  constraintViolations){
				msg.append(constraint.getMessage()).append("<br>");
			}
			return errorInfo(msg.toString());
		}
		return new EiInfo();
	}

	/**
	 * 构建错误EiInfo
	 * @Title: errorInfo
	 * @param msg msg : 校验错误消息
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo errorInfo(String msg) {
		return errorInfo(null, msg);
	}

	/**
	 * 构建错误EiInfo
	 * @Title: errorInfo
	 * @param inInfo inInfo : 参数EiInfo
	 * @param msg msg : 校验错误消息
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo errorInfo(EiInfo inInfo, String msg) {
		if(inInfo == null) {
			inInfo = new EiInfo();
		}
		inInfo.setStatus(-1);
		inInfo.setMsg(msg);
		return inInfo;
	}

	/**
	 * 将对象转成String
	 * @Title: toString
	 * @param obj obj : 需要转换的数据
	 * @param defaultValue defaultValue : 默认值
	 * @return java.lang.String
	 * @throws
	 **/
	public static String toString(Object obj, String defaultValue) {
		if (obj == null) {
			return defaultValue;
		}
		if (StringUtils.isBlank(obj.toString())) {
			return defaultValue;
		}
		return obj.toString();
	}

	/**
	 * 将String转成String
	 * @author zhaowei
	 * @date 2022/12/26 9:37
	 * @param str
	 * @param defaultString
	 * @return java.lang.String
	 */
	public static String defaultIfEmpty(String str, String defaultString) {
		if (str == null) {
			return "";
		} else {
			return str.trim().length() == 0 ? "" : str;
		}
	}

	/**
	 * 将字符串转成date
	 * @Title: toDate
	 * @param dateStr dateStr : 时间字符串
	 * @return java.util.Date
	 * @throws
	 **/
	public static Date toDateTime(String dateStr) {
		if(StringUtils.isBlank(dateStr)) {
			return null;
		}
		return DateUtils.toDateTime(dateStr);
	}

	/**
	 * 将字符串转成date
	 * @Title: toDate
	 * @param dateStr dateStr : 时间字符串
	 * @return java.util.Date
	 * @throws
	 **/
	public static Date toDate(String dateStr) {
		if(StringUtils.isBlank(dateStr)) {
			return null;
		}
		return DateUtils.toDate(dateStr);
	}
}
