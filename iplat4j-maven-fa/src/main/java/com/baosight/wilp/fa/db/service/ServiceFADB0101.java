package com.baosight.wilp.fa.db.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.db.domain.FaTransferDetailVO;
import com.baosight.wilp.fa.db.domain.FaTransferVO;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产调拨管理详情.
 * 固定资产调拨管理初始化方法.
 * 固定资产调拨管理详情查询方法.
 * 固定资产调拨录入方法.
 * 固定资产调拨管理确认.
 * 固定资产调拨管理删除方法.
 * 固定资产调拨管理上传图片时回显.
 * 根据附件id获取附件路径.
 * 固定资产调拨管理保存图片.
 * 固定资产调拨管理图片回显.
 * 查询固定资产调拨信息.
 *
 * @author zhaowei
 * @date 2022年05月31日 10:06
 */
public class ServiceFADB0101 extends ServiceBase {
	/**
	 * 固定资产调拨管理初始化方法.
	 * 1.获取操作类型并进行逻辑判断
	 * 1.1.录入分支
	 * 1.2.编辑分支
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/31 10:08
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		String type = info.getString("type");
		String transferNo = info.getString("transferNo");
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		String applyDeptNum = (String) staffByUserId.get("deptNum");
		String applyDeptName = (String) staffByUserId.get("deptName");
		String confirmDeptNum = "", confirmDeptName = "";
		StringBuilder stringBuilder = new StringBuilder();
		// 通过调拨单号查询调拨单信息
		List<FaTransferVO> faTransferVOList = dao.query("FADB01.queryFaTransferByTransferNo", new HashMap<String, String>() {{
			put("transferNo", transferNo);
		}});
		if (CollectionUtils.isNotEmpty(faTransferVOList)) {
			info.setRows("info", faTransferVOList);
			info.set("applyDeptNum", faTransferVOList.get(0).getApplyDeptNum());
			info.set("applyDeptName", faTransferVOList.get(0).getApplyDeptName());
			info.set("confirmDeptNum", faTransferVOList.get(0).getConfirmDeptNum());
			info.set("confirmDeptName", faTransferVOList.get(0).getConfirmDeptName());
			info.set("confirmLocationNum", faTransferVOList.get(0).getConfirmLocationNum());
			info.set("confirmLocationName", faTransferVOList.get(0).getConfirmLocationName());
			switch (type) {
				case "enter":
				case "apply":
					applyDeptNum = faTransferVOList.get(0).getApplyDeptNum();
					applyDeptName = faTransferVOList.get(0).getApplyDeptName();
					break;
				case "confirm":
				case "audit":
					applyDeptNum = faTransferVOList.get(0).getApplyDeptNum();
					applyDeptName = faTransferVOList.get(0).getApplyDeptName();
					confirmDeptNum = faTransferVOList.get(0).getConfirmDeptNum();
					confirmDeptName = faTransferVOList.get(0).getConfirmDeptName();
					break;
			}
		}
		if (StringUtils.isNotEmpty(applyDeptNum)) {
			// 查询调出科室
			int outCount = dao.count("FADB01.queryOutFaInventoryInfo", applyDeptNum);
			if (outCount > 0) {
				stringBuilder.append(applyDeptName + "——正在进行盘库操作;");
			} else {
				stringBuilder.append(applyDeptName + "——可进行调拨操作;");
			}
		}
		if (StringUtils.isNotEmpty(confirmDeptNum)) {
			// 查询调入科室
			int inCount = dao.count("FADB01.queryOutFaInventoryInfo", confirmDeptNum);
			if (inCount > 0) {
				stringBuilder.append(confirmDeptName + "——正在进行盘库操作;");
			} else {
				stringBuilder.append(confirmDeptName + "——可进行调拨操作;");
			}
		}
		switch (type) {
			case "enter":
			case "apply":
				info.setCell("info", 0, "applyFileCode", info.getString("fileCode"));
				break;
			case "confirm":
				info.setCell("info", 0, "confirmFileCode", info.getString("fileCode"));
				break;
			case "audit":
				info.setCell("info", 0, "auditFileCode", info.getString("fileCode"));
				break;
		}
		info.set("inventoryStatus", stringBuilder);
		return info;
	}

	/**
	 * 固定资产调拨管理详情查询方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/31 10:19
	 */
	@Override
	public EiInfo query(EiInfo info) {
		String transferNo = info.getString("transferNo");
		info.setCell("inqu_status", 0, "transferNo", transferNo);
		info.addBlock("resultFixedAssests").set(EiConstant.limitStr, 1000);
		EiInfo outInfo = super.query(info, "FADB01.queryFaTransferDetailByTransferNo", new FaInfoDO(), false, null, null, "resultFixedAssests", "resultFixedAssests");
		return outInfo;
	}

	/**
	 * 固定资产调拨管理删除方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/6 10:15
	 */
	public EiInfo removeFaTransferInfo(EiInfo info) {
		List<Map<String, Object>> queryFixedAssestsTabInfoList = dao.query("FADB01.queryFixedAssestsTabInfo", info.getAttr());
		dao.update("FADA01.updateFaInfoUnlockList", queryFixedAssestsTabInfoList);
		dao.delete("FADB01.removeFaTransferInfo", info.getAttr());
		dao.delete("FADB01.removeFaTransferDetailInfo", info.getAttr());
		// 删除文件
		List<Map<String, Object>> picInfoList = dao.query("FADB01.queryPicInfo", info.getAttr());
		dao.delete("FADB01.removePicInfo", info.getAttr());
		// 物理删除
		if (CollectionUtils.isNotEmpty(picInfoList)) {
			for (Map<String, Object> picInfoMap : picInfoList) {
				if (StringUtils.isNotEmpty((String) picInfoMap.get("filePath"))) {
					File file = new File((String) picInfoMap.get("filePath"));
					//判断是否存在
					if (file.exists()) {
						file.delete();
					}
				}
			}
		}
		return info;
	}

	/**
	 * 固定资产调拨管理上传图片时回显
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/1 17:34
	 */
	public EiInfo showTempPic(EiInfo info) {
		Object object = info.get("picList");
		List<Map<String, String>> list = object == null ? new ArrayList<>() : (List<Map<String, String>>) object;
		list.forEach(map -> {
			map.put("base64", CommonUtils.imageToBase64Str(getFilePath(map.get("docId"))));
		});
		info.set("list", list);
		return info;
	}

	/**
	 * 根据附件id获取附件路径
	 *
	 * @param docId
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/6/1 17:36
	 */
	public String getFilePath(String docId) {
		//获取文件管理器
		PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");
		//获取附件信息
		Map<String, String> docMap = fileUpLoadManager.getDocById(docId);
		//获取路径
		if (docMap == null || docMap.isEmpty()) {
			return "";
		} else {
			return docMap.get("realPath") + docMap.get("chgName");
		}
	}

	/**
	 * 固定资产调拨管理保存图片
	 *
	 * @param
	 * @author zhaowei
	 * @date 2022/6/6 14:00
	 */
	public void savePicInfo(String fileCode, String num, String type, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<>();
//		String property = System.getProperty("catalina.home");
		String dir = System.getProperty("user.dir");
//		String docRootDir = PlatApplicationContext.getProperty("docRootDir");
		String destPath = dir + File.separator + "upload" + File.separator + "fa" + File.separator + "img" + File.separator;
		map.put("type", type);
		map.put("relateId", num);
		map.put("fileId", fileCode);
		map.put("fileName", fileCode + ".png");
		map.put("filePath", destPath);
		String path = destPath + File.separator + fileCode + ".png";
		String signatureImg = "data:image/png;base64," + data.get("signatureImg");
		CommonUtils.base64StrToImage(signatureImg, path);
		dao.insert("FADB01.savePicInfo", map);
	}

	/**
	 * 固定资产调拨管理图片回显
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/5 14:51
	 */
	public EiInfo showPic(EiInfo info) {
		List<Map<String, String>> list = dao.query("FADB01.showPic", info.getAttr());
		list.forEach(map -> {
			map.put("base64", CommonUtils.imageToBase64Str(map.get("path")));
			map.put("path", "");
		});
		info.set("list", list);
		return info;
	}

	/**
	 * 查询地点信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/10 23:46
	 */
	public EiInfo querySpot(EiInfo info) {
		// 构建参数map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "spot");
		// 调用远程接口返回集合
		List<Map<String, Object>> spotList = BaseDockingUtils.getSpotByDeptNum(info.getString("confirmDeptNum"));
		//将数据进行逻辑分页
		EiInfo spot = CommonUtils.BuildOutEiInfoWithLogicalPage(spotList, map, "spot");
		spot.setBlockInfoValue("dept", "showCount", "true");
		return spot;
	}

	/**
	 * 保存调拨申请草稿
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/11 0:13
	 */
	public EiInfo saveFaTransferInfo(EiInfo info) {
		String transferNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.DB.getAcronym());
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		Map<String, Object> applyInfo = info.getBlock("info").getRow(0);
		// 使用EFPopupInput获取科室名称
//		String confirmDeptName = (String) applyInfo.get("confirmDeptNum_textField");
//		applyInfo.put("confirmDeptName", confirmDeptName);
		applyInfo.put("confirmDeptNum", applyInfo.get("turnDeptNum"));
		applyInfo.put("confirmDeptName", applyInfo.get("turnDeptName"));
		applyInfo.put("applyTime", DateUtils.toDateTimeStr19(new Date()));
		applyInfo.put("applyFileCode", applyInfo.get("applyFileCode"));
		FaTransferVO faTransferVO = new FaTransferVO();
		faTransferVO.fromMap(applyInfo);
		faTransferVO.setId(UUID.randomUUID().toString());
		faTransferVO.setApplyDeptNum((String) staffByUserId.get("deptNum"));
		faTransferVO.setApplyDeptName((String) staffByUserId.get("deptName"));
		faTransferVO.setTransferNo(transferNo);
		faTransferVO.setApplyPerson((String) staffByUserId.get("name"));
		faTransferVO.setTransferStatus("10");
		faTransferVO.setSource("PC");
		List<Map<String, Object>> transferDetail = (List<Map<String, Object>>) info.get("transferDetail");
		List<FaTransferDetailVO> faTransferDetailVOList = transferDetail.stream().map(map -> {
			FaTransferDetailVO faTransferDetailVO = new FaTransferDetailVO();
			faTransferDetailVO.setId(UUID.randomUUID().toString());
			faTransferDetailVO.setTransferNo(transferNo);
			faTransferDetailVO.setFaInfoId((String) map.get("faInfoId"));
			faTransferDetailVO.setGoodsNum((String) map.get("goodsNum"));
			faTransferDetailVO.setGoodsName((String) map.get("goodsName"));
			faTransferDetailVO.setModel((String) map.get("model"));
			faTransferDetailVO.setSpec((String) map.get("spec"));
			return faTransferDetailVO;
		}).collect(Collectors.toList());
		dao.insert("FADB01.saveFaTransferInfo", faTransferVO);
		dao.insert("FADB01.saveFaTransferDetailInfo", faTransferDetailVOList);
		dao.update("FADB01.updateFaInfoStatus", faTransferDetailVOList);
		info.set(EiConstant.serviceName, "XQMS03");
		info.set(EiConstant.methodName, "verifySignData");
		info.set("fileCode", faTransferVO.getApplyFileCode());
		info.set("isBackSignatureImg", 1);
		EiInfo outInfo = XLocalManager.call(info);
		OneSelfUtils.savePicInfo(faTransferVO.getApplyFileCode(), faTransferVO.getTransferNo(), "transfer", (Map<String, Object>) outInfo.getAttr().get("data"), "apply");
		return info;
	}

	/**
	 * 提交调拨单信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/11 10:58
	 */
	public EiInfo submitFaTransferInfo(EiInfo info) {
		FaTransferVO faTransferVO = new FaTransferVO();
		faTransferVO.setTransferNo(info.getString("transferNo"));
		faTransferVO.setTransferStatus("10");
		int update = dao.update("FADB01.submitFaTransferInfo", faTransferVO);
		info.setMsg("成功提交" + update + "条");
		return info;
	}

	/**
	 * 确认调拨单
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/11 11:04
	 */
	public EiInfo confirmFaTransferInfo(EiInfo info) {
		String type = info.getString("type");
		Map<String, Object> confirmInfo = info.getBlock("info").getRow(0);
		confirmInfo.put("applyTime", DateUtils.toDateTimeStr19(new Date()));
		confirmInfo.put("confirmTime", DateUtils.toDateTimeStr19(new Date()));
		confirmInfo.put("auditTime", DateUtils.toDateTimeStr19(new Date()));
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		// 判断调入科室是否在盘库中
		FaTransferVO faTransferVO = new FaTransferVO();
		faTransferVO.fromMap(confirmInfo);
		faTransferVO.setTransferNo((String) confirmInfo.get("transferNo"));
		faTransferVO.setConfirmLocationName((String) confirmInfo.get("confirmLocationNum_textField"));
		faTransferVO.setConfirmPerson((String) staffByUserId.get("name"));
		switch (type) {
			case "pass":
				faTransferVO.setTransferStatus("20");
				break;
			case "reject":
				faTransferVO.setTransferStatus("50");
				dao.update("FADB01.revocationFaInfoStatus", new ArrayList<String>() {{
					add((String) confirmInfo.get("transferNo"));
				}});
				break;
		}
		int update = dao.update("FADB01.confirmFaTransferInfo", faTransferVO);
		info.setMsg("确认调拨" + update + "条");
		info.set(EiConstant.serviceName, "XQMS03");
		info.set(EiConstant.methodName, "verifySignData");
		info.set("fileCode", faTransferVO.getConfirmFileCode());
		info.set("isBackSignatureImg", 1);
		EiInfo outInfo = XLocalManager.call(info);
		OneSelfUtils.savePicInfo(faTransferVO.getConfirmFileCode(), faTransferVO.getTransferNo(), "transfer", (Map<String, Object>) outInfo.getAttr().get("data"), "confirm");
		return info;
	}

	/**
	 * 资产调拨部分同意
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/1/30 19:08
	 */
	public EiInfo confirmFaTransferInfoPart(EiInfo info) {
		String type = info.getString("type");
		Map<String, Object> confirmInfo = info.getBlock("info").getRow(0);
		confirmInfo.put("applyTime", DateUtils.toDateTimeStr19(new Date()));
		confirmInfo.put("confirmTime", DateUtils.toDateTimeStr19(new Date()));
		confirmInfo.put("auditTime", DateUtils.toDateTimeStr19(new Date()));
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		// 判断调入科室是否在盘库中
		FaTransferVO faTransferVO = new FaTransferVO();
		faTransferVO.fromMap(confirmInfo);
		faTransferVO.setConfirmLocationName((String) confirmInfo.get("confirmLocationNum_textField"));
		faTransferVO.setConfirmPerson((String) staffByUserId.get("name"));
		List<String> faInfoIdList = (List<String>) info.get("faInfoIdList");
		String[] faInfoIdStr = faInfoIdList.toArray(new String[faInfoIdList.size()]);
		for (int i = 0; i < faInfoIdStr.length; i++) {
			faInfoIdStr[i] = "'" + faInfoIdStr[i] + "'";
		}
		String param = org.apache.commons.lang.StringUtils.join(faInfoIdStr, ",");
		// 查出当前工单的所有信息，将勾选的通过，不勾选的退回
//		List<Map<String, Object>> pass = faTransferInfoList.stream().filter(map -> faInfoIdList.contains(map.get("faInfoId"))).collect(Collectors.toList());
//		List<Map<String, Object>> reject = faTransferInfoList.stream().filter(map -> !faInfoIdList.contains(map.get("faInfoId"))).collect(Collectors.toList());
		switch (type) {
			case "pass":
				faTransferVO.setTransferStatus("20");
				dao.update("FADB01.updatePart",new HashMap<String,Object>(){{
					put("transferNo", confirmInfo.get("transferNo"));
					put("reject", param);
				}});
				dao.delete("FADB01.removePart", new HashMap<String, Object>() {{
					put("transferNo", confirmInfo.get("transferNo"));
					put("reject", param);
				}});
				break;
			case "reject":
				faTransferVO.setTransferStatus("50");
				dao.update("FADB01.revocationFaInfoStatus", new ArrayList<String>() {{
					add((String) confirmInfo.get("transferNo"));
				}});
				break;
		}
		int update = dao.update("FADB01.confirmFaTransferInfo", faTransferVO);
		info.setMsg("确认调拨" + update + "条");
		info.set(EiConstant.serviceName, "XQMS03");
		info.set(EiConstant.methodName, "verifySignData");
		info.set("fileCode", faTransferVO.getConfirmFileCode());
		info.set("isBackSignatureImg", 1);
		EiInfo outInfo = XLocalManager.call(info);
		OneSelfUtils.savePicInfo(faTransferVO.getConfirmFileCode(), faTransferVO.getTransferNo(), "transfer", (Map<String, Object>) outInfo.getAttr().get("data"), "confirm");
		return info;
	}

	/**
	 * 资产科审批
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/11 11:28
	 */
	public EiInfo auditFaTransferInfo(EiInfo info) {
		String type = info.getString("type");
		Map<String, Object> confirmInfo = info.getBlock("info").getRow(0);
		confirmInfo.put("applyTime", DateUtils.toDateTimeStr19(new Date()));
		confirmInfo.put("confirmTime", DateUtils.toDateTimeStr19(new Date()));
		confirmInfo.put("auditTime", DateUtils.toDateTimeStr19(new Date()));
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		// 判断调入科室是否在盘库中
		FaTransferVO faTransferVO = new FaTransferVO();
		faTransferVO.fromMap(confirmInfo);
		faTransferVO.setTransferNo(info.getString("transferNo"));
		faTransferVO.setAuditDeptNum((String) staffByUserId.get("deptNum"));
		faTransferVO.setAuditDeptName((String) staffByUserId.get("deptName"));
		faTransferVO.setAuditPerson((String) staffByUserId.get("name"));
		switch (type) {
			case "pass":
				faTransferVO.setTransferStatus("100");
				StringBuilder stringBuilder = new StringBuilder();
				int outCount = dao.count("FADB01.queryOutFaInventoryInfo", confirmInfo.get("applydeptNum"));
				if (outCount > 0) {
					stringBuilder.append("调出科室:" + confirmInfo.get("applydeptName") + "正在进行盘库操作;");
					info.setStatus(-1);
					info.setMsg(stringBuilder.toString());
					return info;
				}
				int inCount = dao.count("FADB01.queryOutFaInventoryInfo", confirmInfo.get("confirmDeptNum"));
				if (inCount > 0) {
					stringBuilder.append("调入科室" + confirmInfo.get("confirmDeptName") + "正在进行盘库操作;");
					info.setStatus(-1);
					info.setMsg(stringBuilder.toString());
					return info;
				}
				info.setCell("info", 0, "transferNo", info.getString("transferNo"));
				dao.update("FADB01.updateFaInfoDept", info.getRow("info", 0));
				break;
			case "reject":
				faTransferVO.setTransferStatus("40");
				break;
		}
		dao.update("FADB01.revocationFaInfoStatus", new ArrayList<String>() {{
			add(info.getString("transferNo"));
		}});
		int update = dao.update("FADB01.auditFaTransferInfo", faTransferVO);
		info.setMsg("审批调拨" + update + "条");
		info.set(EiConstant.serviceName, "XQMS03");
		info.set(EiConstant.methodName, "verifySignData");
		info.set("fileCode", faTransferVO.getConfirmFileCode());
		info.set("isBackSignatureImg", 1);
		EiInfo outInfo = XLocalManager.call(info);
		OneSelfUtils.savePicInfo(faTransferVO.getAuditFileCode(), faTransferVO.getTransferNo(), "transfer", (Map<String, Object>) outInfo.getAttr().get("data"), "audit");
		return info;
	}


}
