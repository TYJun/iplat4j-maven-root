package com.baosight.wilp.fa.bf.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.fa.bf.domian.FaScrapDetailVO;
import com.baosight.wilp.fa.bf.domian.FaScrapVO;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产报废详情接口.
 * 1.固定资产报废详情窗口初始化方法.
 * 2.固定资产报废详情保存方法.
 * 3.根据附件id获取附件路径.
 * 4.固定资产报废保存图片方法.
 * 5.固定资产报废删除信息方法.
 * 6.固定资产报废确认方法.
 *
 * @author zhaowei
 * @version V5.0.0
 * @date 2022/8/26 15:00
 */
public class ServiceFABF0101 extends ServiceBase {
	/**
	 * 固定资产报废详情窗口初始化方法.
	 * 1.获取前端传递的操作类型.
	 * 1.1.录入分支.
	 * 1.2.编辑分支.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/5 13:32
	 * @version V5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 1.获取前端传递的操作类型
		 * 不同类型进行不同的信息展示
		 */
		String type = info.getString("type");
		String goodsNum = info.getString("detailId");
		String scrappedNo = info.getString("scrappedNo");
		String fileCode = info.getString("fileCode");
		Map<String, Object> map = new HashMap<>();
		switch (type) {
			case "all":
				map.put("scrappedNo", scrappedNo);
				map.put("goodsNum", goodsNum);
				List<Map> query = dao.query("FABF01.queryFaScarpVOByGoodsNum", map);
				info.setRows("info", query);
				break;
			default:
//				List<FaScrapVO> faScrapVOList = dao.query("FABF01.queryFaScarpVOByScrapNo", scrappedNo);
//				info.setRows("info", faScrapVOList);
		}
		info.setCell("info",0,"applyFileCode",fileCode);
		return info;
	}

	/**
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/9 0:17
	 */
	public EiInfo query(EiInfo info) {
		String scrappedNo = info.getString("scrappedNo");
		String goodsNum = info.getString("detailId");
		List<FaInfoDO> faScarpDetailsVOLists = dao.query("FABF01.queryFaScarpDetailsVOByGoodsNum", goodsNum);
		info.setRows("result", faScarpDetailsVOLists);
		return info;
	}


	/**
	 * 固定资产报废详情保存方法.
	 * 1.获取前端传递的操作类型.
	 * 2.获取前端传递的参数集合.
	 * 3.通过对操作类型进行判断进入不同的分支.
	 * 3.1.录入分支.
	 * 3.2.编辑分支.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/5 13:14
	 * @version V5.0.0
	 */
	public EiInfo saveFaScrapInfo(EiInfo info) {
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		if (info.get("scrap") != null) {
			Map<String, Object> row = info.getRow("info", 0);
			String scrapNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.BF.getAcronym());
			FaScrapVO faScrapVO = new FaScrapVO();
			faScrapVO.setId(UUID.randomUUID().toString());
			faScrapVO.setScrappedNo(scrapNo);
			faScrapVO.setApplyDeptName((String) staffByUserId.get("deptName"));
			faScrapVO.setApplyPerson((String) staffByUserId.get("name"));
			faScrapVO.setApplyReason((String) row.get("applyReason"));
			faScrapVO.setApplyFileCode((String) row.get("applyFileCode"));
			faScrapVO.setApplyTime(new Timestamp(new Date().getTime()));
			faScrapVO.setScrapStatus("00");
			faScrapVO.setSource("PC");
			List<Map<String, String>> faScrapDetailMapList = (List<Map<String, String>>) info.getAttr().get("scrap");
			List<FaScrapDetailVO> faScrapDetailVOList = faScrapDetailMapList.stream().map(
					map -> {
						FaScrapDetailVO faScrapDetailVO = new FaScrapDetailVO();
						faScrapDetailVO.fromMap(map);
						faScrapDetailVO.setScrapDetailStatus("00");
						faScrapDetailVO.setId(UUID.randomUUID().toString());
						faScrapDetailVO.setFaInfoId(map.get("faInfoId"));
						return faScrapDetailVO;
					}
			).collect(Collectors.toList());
			faScrapDetailVOList.stream().forEach(faScrapDetailVO -> faScrapDetailVO.setScrappedNo(scrapNo));
			for (int i = 0; i < faScrapDetailVOList.size(); i++) {
				if (faScrapDetailVOList.get(i).getUseDate() != null) {
					faScrapDetailVOList.get(i).setUsedYears(LocalDate.fromDateFields(new Date()).getYear() - LocalDate.fromDateFields(faScrapDetailVOList.get(i).getUseDate()).getYear());
				}
			}
			dao.insert("FABF01.insertFaScrapVo", faScrapVO);
			dao.insert("FABF01.insertFaScrapDetailVO", faScrapDetailVOList);
			int i = dao.update("FABF01.updateFaInfoStatus", faScrapDetailVOList);
			info.setMsg("批量报废" + i + "条");
			info.set(EiConstant.serviceName, "XQMS03");
			info.set(EiConstant.methodName, "verifySignData");
			info.set("fileCode", faScrapVO.getApplyFileCode());
			info.set("isBackSignatureImg", 1);
			EiInfo outInfo = XLocalManager.call(info);
			for (int j = 0; j < faScrapDetailVOList.size(); j++) {
				OneSelfUtils.savePicInfo(faScrapVO.getApplyFileCode(), faScrapDetailVOList.get(j).getId(), "scrap", (Map<String, Object>) outInfo.getAttr().get("data"), "apply");
			}
			return info;
		} else {
			info.setStatus(-1);
			info.setMsg("请选择需要报废的资产");
			return info;
		}
	}

	/**
	 * 根据附件id获取附件路径
	 *
	 * @param docId
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/6/5 13:15
	 * @version V5.0.0
	 */
	public static String getFilePath(String docId) {
		// 获取文件管理器
		PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");
		// 获取附件信息
		Map<String, String> docMap = fileUpLoadManager.getDocById(docId);
		// 获取路径
		if (docMap == null || docMap.isEmpty()) {
			return "";
		} else {
			return docMap.get("realPath") + docMap.get("chgName");
		}
	}

	/**
	 * 固定资产报废保存图片方法.
	 * 1.获取前端的图片列表.
	 * 2.判断图片列表是否为空.
	 *
	 * @param picObject
	 * @author zhaowei
	 * @date 2022/6/7 15:05
	 * @version V5.0.0
	 */
	public void savePicInfo(Object picObject) {
		/*
		 * 1.获取前端的图片列表
		 * 如果图片列表不为空则保存到实例的集合中
		 */
		List<Map<String, Object>> picList = new ArrayList<>();
		if (picObject != null) {
			picList = (List<Map<String, Object>>) picObject;
		}
		/*
		 * 2.判断图片列表是否为空
		 * 不为空则将图片信息插入数据库
		 */
		if (CollectionUtils.isNotEmpty(picList)) {
			dao.insert("FABF01.savePicInfo", picList);
		}
	}

	/**
	 * 固定资产报废删除信息方法.
	 * 1.删除固定资产报废信息.
	 * 2.数据库删除删除文件记录.
	 * 3.物理删除硬盘上的文件.
	 * 3.1.图片记录信息不为空则判断文件是否存在.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 15:01
	 * @version V5.0.0
	 */
	public EiInfo removeFaScrapInfo(EiInfo info) {
		/*
		 * 1.删除固定资产报废信息
		 * 更新固定资产信息状态
		 */
		int delete = dao.delete("FABF01.removeFaScrapInfo", info.getAttr());
		if (delete == 0) {
			return info;
		}
		dao.update("FADA01.updateFaInfoUnlock", info.getAttr());
		/*
		 * 2.数据库删除删除文件记录
		 * 通过固定资产的信息找到对应的图片记录，再进行删除操作
		 */
		List<Map<String, Object>> picInfoList = dao.query("FADB01.queryPicInfo", info.getAttr());
		dao.delete("FADB01.removePicInfo", info.getAttr());
		/*
		 * 3.物理删除硬盘上的文件
		 * 如果图片列表不为空，遍历图片记录信息
		 */
		if (CollectionUtils.isNotEmpty(picInfoList)) {
			for (Map<String, Object> picInfoMap : picInfoList) {
				/*
				 * 3.1.图片记录信息不为空
				 * 创建一个包含文件名的新对象判断是否存在，如果存在则删除
				 */
				if (StringUtils.isNotEmpty((String) picInfoMap.get("filePath"))) {
					File file = new File((String) picInfoMap.get("filePath"));
					if (file.exists()) {
						file.delete();
					}
				}
			}
		}
		return info;
	}

	/**
	 * 固定资产报废确认方法
	 * 1.改变固定资产状态并将固定资产解锁
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/7 15:31
	 * @version V5.0.0
	 */
	public EiInfo confirmFaScrapInfo(EiInfo info) {
		// 1.改变固定资产状态并将固定资产解锁
		dao.update("FABF01.confirmFaScrapInfo", info.getAttr());
		dao.update("FABF01.updateFaInfoUnlock", info.getAttr());
		return info;
	}

	/**
	 * 提交资产报废信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/9 17:14
	 */
	public EiInfo sumbitFaScrapInfo(EiInfo info) {
		// 修改资产报废单号状态 00 - 申请中 -> 10 - 鉴定中
		if (info.get("detailIdList") != null) {
			List<Map<String, Object>> detailIdList = (List<Map<String, Object>>) info.get("detailIdList");
			Map<String, Object> map = info.getRow("info", 0);
			String parentId = (String) map.get("id");
			List<Map<String, Object>> list = dao.query("FAZN01.queryTwoDeptInfo", new HashMap<String, Object>() {{
				put("parentId", parentId);
			}});
			if (CollectionUtils.isNotEmpty(list)) {
				map.put("identifyDeptNum", list.get(0).get("identifyDeptNum"));
				map.put("identifyDeptName", list.get(0).get("identifyDeptName"));
				map.put("functionDeptNum", list.get(0).get("functionDeptNum"));
				map.put("functionDeptName", list.get(0).get("functionDeptName"));
			}
//		map.put("scrappedNo",info.getString("scrappedNo"));
			map.put("detailIdList", detailIdList);
			int scrappedNo = dao.update("FABF01.sumbitFaScrapInfo", map);
			info.setMsg("提交成功" + scrappedNo + "条");
			info.set(EiConstant.serviceName, "FABF00");
			info.set(EiConstant.methodName, "appointExpert");
			XLocalManager.call(info);
		}
		return info;
	}

	/**
	 * 提交鉴定信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/9 17:43
	 */
	public EiInfo sumbitIdentifyReason(EiInfo info) {
		String result = info.getString("result");
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		Map<String, Object> map = new HashMap<>(8);
		map.put("scrappedNo", info.getString("scrappedNo"));
		map.put("identifyDeptName", staffByUserId.get("deptName"));
		map.put("identifyPerson", staffByUserId.get("name"));
		map.put("identifyTime", DateUtils.toDateTimeStr19(new Date()));
		map.put("identifyReason", info.getString("identifyReason"));
		switch (result) {
			case "pass":
				map.put("scrapStatus", "20");
				break;
			case "reject":
				map.put("scrapStatus", "90");
				// 更新主表资产信息状态
				map.put("statusCode", "020");
				List<String> scrappedNosList = new ArrayList<String>() {{
					add((String) map.get("scrappedNo"));
				}};
				map.put("scrappedNosList", scrappedNosList);
				dao.update("FABF01.revocationFaInfoStatus", map);
				break;
		}
		int update = dao.update("FABF01.sumbitIdentifyReason", map);
		info.setMsg("操作成功" + update + "条");
		return info;
	}

	/**
	 * 提交职能信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/9 17:44
	 */
	public EiInfo sumbitFunctionReason(EiInfo info) {
		String result = info.getString("result");
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		Map<String, Object> map = new HashMap<>(8);
		map.put("scrappedNo", info.getString("scrappedNo"));
		map.put("functionDeptName", staffByUserId.get("deptName"));
		map.put("functionPerson", staffByUserId.get("name"));
		map.put("functionTime", DateUtils.toDateTimeStr19(new Date()));
		map.put("functionReason", info.getString("functionReason"));
		switch (result) {
			case "pass":
				map.put("scrapStatus", "30");
				break;
			case "reject":
				map.put("scrapStatus", "80");
				map.put("statusCode", "020");
				List<String> scrappedNosList = new ArrayList<String>() {{
					add((String) map.get("scrappedNo"));
				}};
				map.put("scrappedNosList", scrappedNosList);
				dao.update("FABF01.revocationFaInfoStatus", map);
				break;
		}
		int update = dao.update("FABF01.sumbitFunctionReason", map);
		info.setMsg("操作成功" + update + "条");
		return info;
	}

	/**
	 * 提交审批信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/9 17:46
	 */
	public EiInfo sumbitAssetReason(EiInfo info) {
		String result = info.getString("result");
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		Map<String, Object> map = new HashMap<>(8);
		map.put("scrappedNo", info.getString("scrappedNo"));
		map.put("assetDeptName", staffByUserId.get("deptName"));
		map.put("assetPerson", staffByUserId.get("name"));
		map.put("assetTime", DateUtils.toDateTimeStr19(new Date()));
		map.put("assetReason", info.getString("assetReason"));
		switch (result) {
			case "pass":
				map.put("scrapStatus", "100");
				map.put("statusCode", "050");
				break;
			case "reject":
				map.put("scrapStatus", "70");
				map.put("statusCode", "020");
				break;
		}
		List<String> scrappedNosList = new ArrayList<String>() {{
			add((String) map.get("scrappedNo"));
		}};
		map.put("scrappedNosList", scrappedNosList);
		dao.update("FABF01.revocationFaInfoStatus", map);
		int update = dao.update("FABF01.sumbitAssetReason", map);
		info.setMsg("操作成功" + update + "条");
		return info;
	}
}