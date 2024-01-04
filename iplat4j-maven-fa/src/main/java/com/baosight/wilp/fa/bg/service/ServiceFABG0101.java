package com.baosight.wilp.fa.bg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.fa.bg.domain.FaModificationBatchDetailVO;
import com.baosight.wilp.fa.bg.domain.FaModifyBaseDTO;
import com.baosight.wilp.fa.common.CompareUtils;
import com.baosight.wilp.fa.common.ComparisonResult;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产变更管理详情.
 * 1.固定资产变更详情初始化方法.
 * 2.固定资产变更管理保存方法.
 *
 * @author zhaowei
 * @date 2022年07月13日 9:48
 */
public class ServiceFABG0101 extends ServiceBase {
	/**
	 * 固定资产变更详情初始化方法.
	 * 1.获取操作类型，根据操作类型进行逻辑判断.
	 * 1.1.新增直接返回.
	 * 1.2.编辑回显数据.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 14:55
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.获取操作类型，根据操作类型进行逻辑判断
		String type = info.getString("type");
		switch (type) {
			// 1.1.新增直接返回
			case "enter":
				break;
			// 1.2.编辑回显数据
			case "edit":
				List<Map<String, Object>> faInfoInfoList = dao.query("FADA01.query", info.getAttr());
				info.setRows("info",faInfoInfoList);
				info.set("surpNum", faInfoInfoList.get(0).get("surpNum"));
				info.set("surpName", faInfoInfoList.get(0).get("surpName"));
				info.set("build", faInfoInfoList.get(0).get("build"));
				info.set("floor", faInfoInfoList.get(0).get("floor"));
				info.set("installLocation", faInfoInfoList.get(0).get("installLocation"));
				info.set("installLocationNum", faInfoInfoList.get(0).get("installLocationNum"));
				info.set("purchaseDept", faInfoInfoList.get(0).get("purchaseDept"));
				info.set("buyCost", faInfoInfoList.get(0).get("buyCost"));
				info.set("estimateCost", faInfoInfoList.get(0).get("estimateCost"));
				info.set("netAssetValue", faInfoInfoList.get(0).get("netAssetValue"));
//				info.set("info-0-goodsTypeCode_textField", faInfoInfoList.get(0).get("goodsTypeName"));
				info.set("info-0-goodsCategoryCode_textField", faInfoInfoList.get(0).get("goodsCategoryName"));
				info.setRows("resultValue",faInfoInfoList);
				info.setRows("resultChange",faInfoInfoList);
				info.setRows("resultAfter",faInfoInfoList);
				break;
		}
		return info;
	}

	/*
	 * 固定资产变更管理保存方法.
	 * 保存固定资产变更信息.
	 * 先将原固定资产信息保存
	 * 然后再更改主表信息
	 * @author zhaowei
	 * @date 2022/7/16 17:13
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo saveModificationInfo(EiInfo info) {
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		Map<String, Object> param = info.getRow("info", 0);
		param.put("faModificationId", UUID.randomUUID().toString().replace("-", ""));
		param.put("goodsTypeName", param.get("goodsTypeCode_textField"));
		param.put("deptName", param.get("deptNum_textField"));
		param.put("amount", StringUtils.isNotEmpty((String) param.get("amount")) ? new BigDecimal((String) param.get("amount")) : new BigDecimal(0));
		param.put("price", StringUtils.isNotEmpty((String) param.get("price")) ? new BigDecimal((String) param.get("price")) : new BigDecimal(0));
		param.put("buyCost", StringUtils.isNotEmpty((String) param.get("buyCost")) ? new BigDecimal((String) param.get("buyCost")) : new BigDecimal(0));
		param.put("estimateCost", StringUtils.isNotEmpty((String) param.get("estimateCost")) ? new BigDecimal((String) param.get("estimateCost")) : new BigDecimal(0));
		param.put("useYears", StringUtils.isNotEmpty((String) param.get("useYears")) ? Integer.valueOf((String) param.get("useYears")) : 0);
		param.put("residualRate", StringUtils.isNotEmpty((String) param.get("residualRate")) ? new BigDecimal((String) param.get("residualRate")) : new BigDecimal(0));
		param.put("estimatedResidualValue", StringUtils.isNotEmpty((String) param.get("estimatedResidualValue")) ? new BigDecimal((String) param.get("estimatedResidualValue")) : new BigDecimal(0));
		param.put("monthDepreciation", StringUtils.isNotEmpty((String) param.get("monthDepreciation")) ? new BigDecimal((String) param.get("monthDepreciation")) : new BigDecimal(0));
		param.put("totalDepreciation", StringUtils.isNotEmpty((String) param.get("totalDepreciation")) ? new BigDecimal((String) param.get("totalDepreciation")) : new BigDecimal(0));
		param.put("netAssetValue", StringUtils.isNotEmpty((String) param.get("netAssetValue")) ? new BigDecimal((String) param.get("netAssetValue")) : new BigDecimal(0));
		param.put("buyDate", StringUtils.isNotEmpty((String) param.get("buyDate")) ? param.get("buyDate") : null);
		param.put("useDate", StringUtils.isNotEmpty((String) param.get("useDate")) ? param.get("useDate") : null);
		param.put("inAccountDate", StringUtils.isNotEmpty((String) param.get("inAccountDate")) ? param.get("inAccountDate") : null);
		param.put("invoiceDate", StringUtils.isNotEmpty((String) param.get("invoiceDate")) ? param.get("invoiceDate") : null);
		param.put("modificationNum", OneSelfUtils.publicCreateCode(FixedAssetsEum.BG.getAcronym()));
		param.put("recCreateTime", DateUtils.curDateTimeStr19());
		param.put("recCreator", staffByUserId.get("name"));
		param.put("statusCode", "01");
		// 设备预留
//		param.put("deviceName",param.get("deviceCode_textField"));
//		param.put("deviceTypeName",param.get("deviceTypeCode_textField"));
		// 先将原固定资产信息保存
		// 然后再更改主表信息
		dao.insert("FABG01.saveModificationInfo", param);
		dao.update("FADA01.modify", param);
		return info;
	}

	// 另起炉灶

	/**
	 * 资产变更
	 * 在资产变更中添加排序字段
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/7 14:10
	 */
	public EiInfo batchModificationInfo(EiInfo info) throws Exception {
		// 基础信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		List<FaModificationBatchDetailVO> faModificationBatchDetailVOList = new ArrayList<>();
		Map<String, String> map = info.getRow("info", 0);
		map.put("surpName",map.get("surpNum_textField"));
		map.put("goodsCategoryName",map.get("goodsCategoryCode_textField"));
		String id = map.get("faInfoId");
		// 价值信息
		List<Map<String,String>> changeRows = (List<Map<String, String>>) info.get("changeRows");
		if (CollectionUtils.isNotEmpty(changeRows)){
			map.put("buyCost",changeRows.get(0).get("afterBuyCost"));
			map.put("netAssetValue",changeRows.get(0).get("afterNetAssetValue"));
			map.put("totalDepreciation",changeRows.get(0).get("afterTotalDepreciation"));
			map.put("equityFund",changeRows.get(0).get("afterEquityFund"));
			map.put("otherFund",changeRows.get(0).get("afterOtherFund"));
		}
		// 查询变更前信息
		List<Map<String,Object>> faInfoDOS = dao.query("FADA01.query", new HashMap<String, String>() {{
			put("faInfoId", id);
		}});
		// 查询主表中对应的资产信息
		List<FaModificationBatchDetailVO> faInfoTOFaModificationVOList = dao.query("FABG01.queryFaInfoTOFaModification", new ArrayList<String>() {{
			add(id);
		}});
		// 过滤出主表对应的实体
		List<FaModificationBatchDetailVO> faInfo = faInfoTOFaModificationVOList.stream().filter(filter -> filter.getFaInfoId().equals(id)).collect(Collectors.toList());
		// 变更后信息
		FaModificationBatchDetailVO faModificationBatchDetailVO = new FaModificationBatchDetailVO();
		faModificationBatchDetailVO.fromMap(map);
		faModificationBatchDetailVO.setFaInfoId(id);
		if (CollectionUtils.isNotEmpty(faInfoDOS)) {
			if (!faInfoDOS.get(0).get("goodsClassifyCode").equals(map.get("goodsClassifyCode"))) {
				String goodsClassifyCode = map.get("goodsClassifyCode");
				if (goodsClassifyCode.contains("A08")){
					goodsClassifyCode = goodsClassifyCode.replace("A08","B");
				} else if (!goodsClassifyCode.contains("A0232")){
					goodsClassifyCode = goodsClassifyCode.replace("A","C");
				}
				String goodsNum = OneSelfUtils.privateCreateCode(goodsClassifyCode);
				if (StringUtils.isNotEmpty(goodsNum)){
					faModificationBatchDetailVO.setGoodsNum(goodsNum);
				} else {
					info.setStatus(-1);
					info.setMsg("资产变更失败，请联系管理员");
					return info;
				}
			}
		}
		String batchId = UUID.randomUUID().toString();
		faModificationBatchDetailVO.setId(batchId);
		faModificationBatchDetailVO.setGoodsCategoryName(map.get("goodsCategoryCode_textField"));
		faModificationBatchDetailVO.setRecCreateName((String) staffByUserId.get("name"));
		faModificationBatchDetailVO.setRecCreator((String) staffByUserId.get("workNo"));
		faModificationBatchDetailVO.setRecCreateTime(DateUtils.curDateTimeStr19());
		faModificationBatchDetailVOList.add(faModificationBatchDetailVO);
		FaModifyBaseDTO faModifyBaseDTO1 = new FaModifyBaseDTO();
		FaModifyBaseDTO faModifyBaseDTO2 = new FaModifyBaseDTO();
		faModifyBaseDTO1.fromMap(faInfo.get(0).toMap());
		faModifyBaseDTO2.fromMap(faModificationBatchDetailVO.toMap());
		// 插入变更后信息
		List<ComparisonResult> results = CompareUtils.compareFields(faModifyBaseDTO1, faModifyBaseDTO2,
				FaModifyBaseDTO.class, id, batchId);
		if (CollectionUtils.isNotEmpty(results)){
			dao.insert("FABG01.batchModificationInfo", faModificationBatchDetailVOList);
			dao.update("FABG01.updateFaInfoByModify", faModificationBatchDetailVO);
			dao.insert("FABG01.saveComparisonResult", results);
		} else {
			info.setStatus(-1);
			info.setMsg("资产没有发生变化，变更失败");
			return info;
		}
		// 变更抛帐
		if (map.get("modificationStatus").equals("021")){
			dao.insert("FAAP01.modificationUpAccount", map);
		} else if (map.get("modificationStatus").equals("026")){
			dao.insert("FAAP01.modificationDownAccount", map);
		}
		return info;
	}
}
