package com.baosight.wilp.sq.common;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 生成编码工具类
 *
 * @Title: UtilCode.java
 * @ClassName: UtilCode
 * @Package：com.baosight.wilp.sq.common
 * @author: zhangjiaqian
 * @date: 2021年7月19日 下午4:11:08
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class UtilCode {


	/**
	 * 注入dao
	 */
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");

	/**
	 * 生成主题编号
	 *
	 * @return
	 * @Title: dangerCode
	 * @return: String
	 */
	public static String dangerCode() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String format = df.format(new Date());
		String replace = format.replace("-", "");
		String substring = replace.substring(2, replace.length());
		//查询当天是否生成过编号
		List query = dao.query("SQBZ01.dangerCodeCount", "bz" + substring);
		String count = null;
		if (query.isEmpty()) {
			count = "0";
		}
		count = query.get(0).toString();
		Integer valueOf = Integer.valueOf(count);
		Integer par = valueOf + 1;
		String string = par.toString();
		String code = "bz" + substring + string;
		return code;
	}

	/**
	 * 生成项目编号
	 *
	 * @return
	 * @Title: dangerCode
	 * @return: String
	 */
	public static String projectCode() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String format = df.format(new Date());
		String replace = format.replace("-", "");
		String substring = replace.substring(2, replace.length());
		//查询当天是否生成过编号
		List query = dao.query("SQBZ02.projectCodeCount", "xm" + substring);
		String count = null;
		if (query.isEmpty()) {
			count = "0";
		}
		count = query.get(0).toString();
		Integer valueOf = Integer.valueOf(count);
		Integer par = valueOf + 1;
		String string = par.toString();
		String code = "xm" + substring + string;
		return code;
	}


	/**
	 * 生成标准编号
	 *
	 * @return
	 * @Title: dangerCode
	 * @return: String
	 */
	public static String projectInstanceCode() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String format = df.format(new Date());
		String replace = format.replace("-", "");
		String substring = replace.substring(2, replace.length());
		//查询当天是否生成过编号
		List query = dao.query("SQBZ03.projectInstanceCodeCount", "jcbz" + substring);
		String count = null;
		if (query.isEmpty()) {
			count = "0";
		}
		count = query.get(0).toString();
		Integer valueOf = Integer.valueOf(count);
		Integer par = valueOf + 1;
		String string = par.toString();
		String code = "jcbz" + substring + string;
		return code;
	}


	/**
	 * 生成问卷编号
	 *
	 * @return
	 * @Title: dangerCode
	 * @return: String
	 */
	public static String manageCode() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String format = df.format(new Date());
		String replace = format.replace("-", "");
		//查询当天是否生成过编号
		List<Map<String, Object>> batchNoList = dao.query("SQWJ01.queryBatchNoByDate", replace);
		//
		if (CollectionUtils.isNotEmpty(batchNoList)) {
			String batchNo = (String) batchNoList.get(0).get("batchNo");
			String substring = batchNo.substring(9);
			return replace + "-" + String.format("%03d", Integer.valueOf(substring) + 1);
		} else {
			return replace + "-" + "001";
		}
	}


}
