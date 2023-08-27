package com.baosight.wilp.ac.gm.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 新增物资子页面.
 * <p>
 * 页面初始化方法, 新增物资, 新增分类.
 * </p>
 *
 * @Title ServiceACGM0103.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACGM0103 extends ServiceBase {

	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 页面初始化方法
	 * 作者：jzm
	 * 入参：EiInfo（matClassId 物资分类id）
	 * 出参：EiInfo（物资分类信息）
	 * 处理：
	 * 1.通过 matClassId
	 * 查询物资分类信息
	 * 2.返回
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo info1 = new EiInfo();
		info1.set("fkey", "MatImageSizeAndFormat");
		info1.set(EiConstant.serviceId, "S_AC_FW_21");
		EiInfo outInfo1 = XServiceManager.call(info1);
		ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) outInfo1.get("result");
		String config = list.get(0).get("FVALUE").replace(" ", "");
		String maxSize = config.split(";")[0];
		String format = config.split(";")[1];
		HashMap<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("matClassId", inInfo.getString("matClassId"));
		List<Map<String, Object>> mats = dao.query("ACGM01.queryMatClassNameById", map);
		EiInfo out = new EiInfo();
		if (!mats.isEmpty()) {
			out.setAttr(mats.get(0));
		}
		out.set("maxSize",maxSize);
		out.set("format",format);
		return out;
	}

	/**
	 * 新增物资
	 * 入参：EiInfo(matName 物资名称， matClassId 物资分类ID，matSpec 物资规格， matModel 物资型号
	 * unit 计量单位 price 价格 supplier 供应商 manufacturer 制造商 matTypeCode 物资大类编码 remark 备注
	 * )
	 * 出参：inInfo（返回新增结果）
	 * 处理：
	 * 1.读入参数，生成主键，读取创建人，创建时间，生成 物资编码
	 * 2.存入数据库
	 * 3.返回操作结果
	 */
	@Override
	@ArchivesLog(model = "AC", sign = "新增物资")
	public EiInfo insert(EiInfo inInfo) {
		/**
		 * 1.读入参数，生成主键，读取创建人，创建时间，生成 物资编码
		 */
		Map<String,String> map = inInfo.getAttr();
		String id = UUID.randomUUID().toString(); /* 主键 */

		String recCreator = UserSession.getUser().getUsername();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */
		map.put("projectSchema", projectSchema);
		String matCode=map.get("matCode");
		if("".equals(matCode)){
			// 先查询出当前最新的 rec_create_time 取出该记录的matCode
			List<Map<String, String>> list = dao.query("ACGM01.queryLastMatCode", map);
			if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号MA00001
				matCode = "MA00001";
			} else {
				matCode = ServiceACGM01.genMatCode(list.get(0).get("matCode")); // 生成物编码
			}
		}

		EiInfo info2 = new EiInfo();
		info2.set("matCode", matCode);
		info2.set(EiConstant.serviceId, "S_AC_FW_17");
		EiInfo outInfo2 = XServiceManager.call(info2);
		ArrayList<HashMap<String, String>> list2 = (ArrayList<HashMap<String, String>>) outInfo2.get("result");
		if(!list2.isEmpty()){
			outInfo2.setMsg("物资编码不能重复");
			outInfo2.setStatus(-2);
			return outInfo2;
		}

		/**
		 * 2.存入数据库
		 */
		map.put("id", id);
		map.put("matCode", matCode);
		map.put("status", "1"); // 状态默认 启用
		map.put("recCreator", recCreator);
		map.put("recCreateTime", recCreateTime);

		dao.insert("ACGM0103.insert", map);


		/**
		 * 3.返回操作结果
		 */
		inInfo.setMsg("新增成功");
		return inInfo;
	}

	public EiInfo getLastMatCode(EiInfo inInfo) {
		/**
		 *  1.从EiInfo中读取入参
		 */
		Map<String,String> map = inInfo.getAttr();
		map.put("projectSchema", projectSchema);
		String matCode;
		// 先查询出当前最新的 rec_create_time 取出该记录的matCode
		List<Map<String, String>> list = dao.query("ACGM01.queryLastMatCode", map);
		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号MA00001
			matCode = "MA00001";
		} else {
			matCode = ServiceACGM01.genMatCode(list.get(0).get("matCode")); // 生成物编码
		}
		inInfo.set("lastMatCode",matCode);
		inInfo.setMsg("生成最新的编号成功");
		inInfo.setStatus(0);
		return inInfo;
	}
}
