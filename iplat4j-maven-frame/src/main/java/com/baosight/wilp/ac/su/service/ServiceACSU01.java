package com.baosight.wilp.ac.su.service;

import com.baosight.iplat4j.common.ed.domain.TEDCM01;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  供应商档案信息管理.
 *  <p>页面初始化方法, 查询方法,ACGM0103.jsp 的 EFPopupInput后台查询方法, 启用供应商, 停用供应商, 从 excel 导入数据.</p>
 *
 * @Title ServiceACDE01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACSU01 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 	页面初始化方法
	 * 	作者：jzm
     * 	入参：EiInfo
     * 	出参：EiInfo
     * 	处理：调用query()方法
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 	查询功能
	 * 	作者：jzm
	 * 	入参：供应商编号"supplierCode", 供应商名称"supplierName", 联系地址"contactAddress"	 联系电话 contactNumber  状态status
	 * 	出参：EiInfo
	 * 	处理：
	 * 	1.处理入参 设置正确的status入参值
	 * 	2.从数据库中查出符合条件的供应商集合
	 * 	3.查询出count
	 * 	4.封装到eiinfo中返回
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.处理入参 设置正确的status入参值
		 */
		String[] param = {"supplierCode","supplierName", "contactAddress", "contactNumber","status"};
        List<String> plist = Arrays.asList(param);
        Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
        // 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "1");

		/**
		 * 	2.从数据库中查出符合条件的供应商集合
		 */
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> deptList = dao.query("ACSU01.querySupplierList",map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));

		/**
		 * 	3.查询出count
		 */
		int count = super.count("ACSU01.querySupplierListCount",map);


		/**
		 * 	4.封装到eiinfo中返回
		 */
		if(!CollectionUtils.isEmpty(deptList)){
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}

	}

	/**
	 * ACGM0103.jsp 中的 EFPopupInput 后台查询方法
	 * 作者：jzm
	 * 入参: EiInfo（inqu_status Block 中的查询条件）
	 * 出参：EiInfo（result block中）
	 * 处理：
	 * 	1.处理入参，置正确的状态位
	 * 	2.从数据库中查询符合条件的供应商集合
	 * 	3.查询符合条件的count
	 * 	4.将结果封装在eiinfo中的result block中返回
	 */
	public EiInfo querySupplier(EiInfo inInfo) {
		/**
		 * 1.处理入参，置正确的状态位
		 */
		Map<String, Object> map = PrUtils.buildParamter(inInfo, "inqu_status", "result");
		map.putIfAbsent("status", "1");
		map.put("projectSchema", projectSchema);

		/**
		 * 	2.从数据库中查询符合条件的供应商集合
		 */
		List<Map<String, Object>> deptList = dao.query("ACSU01.querySupplierList",map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));

		/**
		 * 3.查询符合条件的count
		 */
		int count = super.count("ACSU01.querySupplierListCount",map);

		/**
		 * 4.将结果封装在eiinfo中的result block中返回
		 */
		if(!CollectionUtils.isEmpty(deptList)){
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}

	}


	/**
	 * 启用供应商
	 * 作者：jzm
	 * 入参: EiInfo（attr中的 list  中的查询条件）
	 * 出参：EiInfo（result block中）
	 * 处理：
	 * 1.从EiInfo中读入入参
	 * 2.执行数据库更新操作，完成启用（即置status标志位为1）
	 * 3.返回操作结果
	 */
	public EiInfo startUsing(EiInfo eiInfo){
		/**
		 *  1.从EiInfo中读入入参
		 */
		List<String> list = (List<String>)eiInfo.get("list");
		HashMap<String, Object> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("list", list);

		/**
		 *  2.执行数据库更新操作，完成启用（即置status标志位为1）
		 */
		dao.update("ACSU01.startUsing", map);

		/**
		 * 3.返回操作结果
		 */
		eiInfo.setStatus(0);
		eiInfo.setMsg("启用成功");
		return eiInfo;
	}

	/**
	 * 停用供应商
	 * 作者：jzm
	 * 入参: EiInfo（attr中的 list  中的查询条件）
	 * 出参：EiInfo（result block中）
	 * 处理：
	 * 1.从EiInfo中读入入参
	 * 2.执行数据库更新操作，完成启用（即置status标志位为0）
	 * 3.返回操作结果
	 */
	public EiInfo stopUsing(EiInfo inInfo) {
		/**
		 * 1.从EiInfo中读入入参
		 */
		List<String> list = (List<String>)inInfo.get("list");

		/**
		 *  2.执行数据库更新操作，完成启用（即置status标志位为0）
		 */
		HashMap<String, Object> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("list", list);
		dao.update("ACSU01.stopUsing", map);

		/**
		 *  3.返回操作结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("停用成功!");
		return inInfo;
	}


	/**
	 * 从 excel 导入数据（ExcelACSUController调用此方法）
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo（导入结果，如果有失败的记录，则会将失败的记录和失败原因返回）
	 *
	 * // 保证供应商名称不能重复
	 * // 验证供应商类型来自小代码
	 * // 先从数据库中拉出现有的供应商名称集合
	 *
	 *
	 * 处理：
	 * 1.从数据库中拉出现有的供应商名称集合
	 * 2，获取供应商类型集合 放入 typeSet中 用于验证
	 * 3.验证：非空，长度，供应商名称重复
	 * 4，将正确的数据写入数据库
	 * 5.返回操作结果（如果有错误数据包含错误数据一并返回）
	 */
	public EiInfo importSuppFromExcel(EiInfo eiInfo){
		/**
		 *  1.从数据库中拉出现有的供应商名称集合
		 */
		HashMap<String, String> mp = new HashMap<>();
		mp.put("projectSchema", projectSchema);
		List<String> dbSupplierNameList = dao.query("ACSU01.queryAllSupplierName", mp);
		HashSet<String> dbSupplierNameSet = new HashSet<>(dbSupplierNameList);	// db 内供应商名称集合
		HashSet<String> checkSet= new HashSet<>();	// excel 内供应商名称验重复集合

		/**
		 * 2，获取供应商类型集合 放入 typeSet中 用于验证
		 */
		EiInfo ei = new EiInfo();
		ei.set(EiConstant.serviceId,"S_ED_36");
		ei.set("codesetCode","wilp.ac.su.type");
		EiInfo out =XServiceManager.call(ei);
		ArrayList<TEDCM01> result = (ArrayList<TEDCM01>) out.getAttr().get("result");
		Set<String> typeSet = new HashSet<>(); // 供应商类型集合
		result.forEach(e-> typeSet.add(e.getItemCode()));

		List<Map<String,String>> inList = (List<Map<String,String>>)eiInfo.get("list");
		List<Map<String, String>> okList = new ArrayList<>();
		List<Map<String, String>> errorList = new ArrayList<>();

		// 生成供应商编号 标志位
		boolean supCodeGenByDB = true;
		String globalSupCode = null;

		/**
		 *  3.验证：非空，长度，供应商名称重复
		 */
		for (Map<String, String> map : inList) {

			// 非空验证
			StringBuilder error = new StringBuilder();
			if (StringUtils.isEmpty(map.get("supplierName"))) {
				error.append("供应商名称为空  ");
			}
			if (StringUtils.isEmpty(map.get("supplierType"))){
				error.append("供应商类型为空  ");
			}

			// 判断字段长度是否超出
			if (map.get("supplierName").length() > 100 ){
				error.append("供应商名称长度超过100 ");
			}
			if (map.get("supplierType").length() > 2 ){
				error.append("供应商类型长度超过2 ");
			}
			if (map.get("contacts").length() > 50 ){
				error.append("联系人长度超过50  ");
			}
			if (map.get("contactNumber").length() > 20 ){
				error.append("联系电话长度超过20  ");
			}
			if (map.get("contactAddress").length() > 200 ){
				error.append("联系地址长度超过200  ");
			}
			if (map.get("contactEmail").length() > 36 ){
				error.append("联系邮箱长度超过36 ");
			}
			if (map.get("zipCode").length() > 255 ){
				error.append("邮编长度超过255  ");
			}
			if (map.get("legalRepresentative").length() > 36 ){
				error.append("法人代表长度超过36 ");
			}
			if (map.get("icrNo").length() > 100 ){
				error.append("工商注册号长度超过100 ");
			}
			if (map.get("businessScope").length() > 500 ){
				error.append("经营范围长度超过500 ");
			}
			if (map.get("abbreviation").length() > 36 ){
				error.append("供应商简称长度超过36 ");
			}
			if (map.get("hrpCode").length() > 50 ){
				error.append("hrp编码长度超过50 ");
			}

			if ( !typeSet.contains(map.get("supplierType")) ){
				error.append("供应商类型应填写 ").append(typeSet).append(" 中的一个  ");
			}

			// 验证是否重复
			if(dbSupplierNameSet.contains(map.get("supplierName")) || checkSet.contains(map.get("supplierName"))){
				error.append("供应商名称重复  ");
			}


			checkSet.add(map.get("supplierName"));
			// 判断是否存在错误
			if (error.length() == 0){
				// 无错误，则生成必要信息 然后存入okList
				String supplierCode;
				// 先查询出当前最新的 rec_create_time 取出该记录的matNum
				// 由于 事务 导致 导入时每次查询的结果都是相同的
				// 设置一个标志位 仅仅 第一次进入时查询数据库最 后一个合法编码 后入全部走自增方法
				if (supCodeGenByDB) {
					map.put("projectSchema",projectSchema);
					List<Map<String,String>> list = dao.query("ACSU0101.queryLastSuppNum",map);
					if(CollectionUtils.isEmpty(list)) { //如果没有记录则生成一号SU00001
						supplierCode = "SU00001";
					}else {
						supplierCode = ServiceACSU0101.genSuppNum(list.get(0).get("suppNum")); //生成物编码
					}
					globalSupCode = supplierCode;
					supCodeGenByDB = false;
				}else {
					supplierCode = ServiceACSU0101.genSuppNum(globalSupCode);
					globalSupCode = supplierCode;
				}
				String id = UUID.randomUUID().toString();
				String recCreateTime = sdf.format(new Date());
				String recCreator = UserSession.getLoginName();
				map.put("id", id);
				map.put("status", "1"); // 默认启用
				map.put("supplierCode", supplierCode);
				map.put("recCreator", recCreator);
				map.put("recCreateTime", recCreateTime);
				// 存入okList
				okList.add(map);

			}else {
				// 有错误 添加错误字段 存入errorList
				map.put("error", error.toString());
				errorList.add(map);
			}

		}

		/**
		 * 4，将正确的数据写入数据库
		 */
		// 如果 okList 不为空 则插入数据库
		if (CollectionUtils.isNotEmpty(okList)){
			// 批量导入okList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", okList);
			map1.put("projectSchema", projectSchema);

			dao.insert("ACSU01.importSuppFromExcel",map1);

		}

		/**
		 * 5.返回操作结果（如果有错误数据包含错误数据一并返回）
		 */
		EiInfo outInfo = new EiInfo();
		if (!errorList.isEmpty()){
			outInfo.setStatus(1);
			HashMap<String, Object> map = new HashMap<>();
			map.put("list", errorList);
			outInfo.setAttr(map);
			return outInfo;
		}
		outInfo.setStatus(0);
		return outInfo;
	}

}
