package com.baosight.wilp.ac.su.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  新增供应商子页面.
 *  <p>页面初始化方法, 新增供应商, 根据供应商ID更新供应商信息.</p>
 *
 * @Title ServiceACSU0101.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACSU0101 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();

    /**
     * 	页面初始化方法
	 * 	作者：jzm
     * 	入参：EiInfo
     * 	出参：EiInfo
     * 	处理：直接返回入参
     */
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
     * 	新增供应商
	 * 	作者：jzm
     *	入参：	供应商名称 supplierName, 供应商类型 supplierType,联系人 contacts, 联系电话 contactNumber
	 *			联系地址 contactAddress 联系邮箱contactEmail  zipCode 邮编  legalRepresentative 法人代表
	 *			工商注册号 icrNo  经营范围business_scope   供应商简称abbreviation   HRP代码 hrp_code
     *	出参：inInfo（返回新增结果）
     * 	处理：
     * 	1.从EiInfo中读取入参
     * 	2.检查供应商名称是否重复
     * 	3.生成主键，供应商编码，创建时间，创建人
	 * 	4.写入数据库
     * 	5.返回操作结果
     */
	@Override
	@ArchivesLog(model = "AC", sign = "新增供应商")
	public EiInfo insert(EiInfo inInfo) {
		/**
		 * 1.从EiInfo中读取入参
		 */
		Map map = inInfo.getAttr();
		map.put("projectSchema", projectSchema);

		/**
		 * 	2.检查供应商名称是否重复
		 */
		EiInfo out = new EiInfo();
		// 检查供应商名称是否重复
		int count = super.count("ACSU0101.isExistSupplierName", map);
		if (count > 0){
			out.setStatus(1);
			out.setMsg("该供应商名称已存在");
			return out;
		}

		/**
		 * 	3.生成主键，供应商编码，创建时间，创建人
		 */
		String supplierCode;
		// 先查询出当前最新的 rec_create_time 取出该记录的spotNum
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("projectSchema", projectSchema);
		List<Map<String,String>> list = dao.query("ACSU0101.queryLastSuppNum",map1);

		if(CollectionUtils.isEmpty(list)) { //如果没有记录则生成一号SU00001
			supplierCode = "SU00001";
		}else {
			supplierCode = genSuppNum(list.get(0).get("suppNum")); //生成供应商编号
		}

		String id = UUID.randomUUID().toString(); //主键
		String recCreateTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());	 // 创建时间
		String recCreator = UserSession.getUser().getUsername();// 创建人

		map.put("id", id);
		map.put("supplierCode", supplierCode);
		map.put("recCreateTime", recCreateTime);
		map.put("recCreator", recCreator);
		map.put("status", "1");

		/**
		 * 	4.写入数据库
		 */
		dao.insert("ACSU0101.insert",map);

		/**
		 * 	5.返回操作结果
		 */
		out.setStatus(0);
		out.setMsg("新增成功");
		return out;
	}

	/**
	 * 	按照 SU00000 方式生成科室编号
	 * 	作者：jzm
	 * 	入参：lastSuppNum 上一次最后生成的部门编号
	 * 	出参：下一个供应商编号
	 * 	处理：取后五位转整型然后加一，转字符串并在前面拼接前缀返回。
	 */
	public static String genSuppNum(String lastSuppNum) {
		return
		 "SU" + String.format("%05d", Integer.parseInt(lastSuppNum.substring(2))+1);
	}

}
