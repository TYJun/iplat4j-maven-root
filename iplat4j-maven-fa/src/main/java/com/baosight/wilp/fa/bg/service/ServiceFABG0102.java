package com.baosight.wilp.fa.bg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.List;
import java.util.Map;

/**
 * 固定资产变更管理详情类.
 * 固定资产变更管理详情初始化方法.
 * @author zhaowei
 * @date 2022年07月13日 9:48
 */
public class ServiceFABG0102 extends ServiceBase {
	/**
	 * 固定资产变更管理详情初始化方法.
	 * 1.获取前端操作类型进行逻辑判断.
	 * 1.1.新增直接返回.
	 * 1.2.编辑回显数据.
	 * @author zhaowei
	 * @date 2022/10/13 10:39
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.获取前端操作类型进行逻辑判断
		String type = info.getString("type");
		switch (type) {
			// 1.1.新增直接返回
			case "enter":
				break;
			// 1.2.编辑回显数据
			case "edit":
				List<Map<String, Object>> faInfoInfoList = dao.query("FADA01.query", info.getAttr());
				info.set("build", faInfoInfoList.get(0).get("build"));
				info.set("floor", faInfoInfoList.get(0).get("floor"));
				info.set("installLocation", faInfoInfoList.get(0).get("installLocation"));
				info.set("installLocationNum", faInfoInfoList.get(0).get("installLocationNum"));
				info.set("info-0-faInfoId", faInfoInfoList.get(0).get("faInfoId"));
				info.set("info-0-goodsNum", faInfoInfoList.get(0).get("goodsNum"));
				info.set("info-0-goodsName", faInfoInfoList.get(0).get("goodsName"));
				info.set("info-0-goodsTypeCode", faInfoInfoList.get(0).get("goodsTypeCode"));
				info.set("info-0-goodsTypeCode_textField", faInfoInfoList.get(0).get("goodsTypeName"));
				info.set("info-0-goodsClassifyCode", faInfoInfoList.get(0).get("goodsClassifyCode"));
				info.set("info-0-goodsClassifyName", faInfoInfoList.get(0).get("goodsClassifyName"));
				info.set("info-0-rfidCode", faInfoInfoList.get(0).get("rfidCode"));
				info.set("info-0-model", faInfoInfoList.get(0).get("model"));
				info.set("info-0-unitName", faInfoInfoList.get(0).get("unitName"));
				info.set("info-0-deptNum", faInfoInfoList.get(0).get("deptNum"));
				info.set("info-0-deptNum_textField", faInfoInfoList.get(0).get("deptName"));
				info.set("info-0-manufacturer", faInfoInfoList.get(0).get("manufacturer"));
				info.set("info-0-surpName", faInfoInfoList.get(0).get("surpName"));
				info.set("info-0-buyDate", faInfoInfoList.get(0).get("buyDate"));
				info.set("info-0-useDate", faInfoInfoList.get(0).get("useDate"));
				info.set("info-0-statusCode", faInfoInfoList.get(0).get("statusCode"));
				info.set("info-0-build", faInfoInfoList.get(0).get("build"));
				info.set("info-0-floor", faInfoInfoList.get(0).get("floor"));
				info.set("info-0-installLocation", faInfoInfoList.get(0).get("installLocation"));
				info.set("info-0-installLocationNum", faInfoInfoList.get(0).get("installLocationNum"));
				info.set("info-0-residualRate", faInfoInfoList.get(0).get("residualRate"));
				info.set("info-0-estimatedResidualValue", faInfoInfoList.get(0).get("estimatedResidualValue"));
				info.set("info-0-useYears", faInfoInfoList.get(0).get("useYears"));
				info.set("info-0-amount", faInfoInfoList.get(0).get("amount"));
				info.set("info-0-price", faInfoInfoList.get(0).get("price"));
				info.set("info-0-buyCost", faInfoInfoList.get(0).get("buyCost"));
				info.set("info-0-monthDepreciation", faInfoInfoList.get(0).get("monthDepreciation"));
				info.set("info-0-netAssetValue", faInfoInfoList.get(0).get("netAssetValue"));
				info.set("info-0-fundsSource", faInfoInfoList.get(0).get("fundsSource"));
				info.set("info-0-finaceClassNum", faInfoInfoList.get(0).get("finaceClassNum"));
				info.set("info-0-invoiceNo", faInfoInfoList.get(0).get("invoiceNo"));
				info.set("info-0-inAccountDate", faInfoInfoList.get(0).get("inAccountDate"));
				info.set("info-0-invoiceDate", faInfoInfoList.get(0).get("invoiceDate"));
				info.set("info-0-deviceTypeCode", faInfoInfoList.get(0).get("deviceTypeCode"));
				info.set("info-0-deviceCode", faInfoInfoList.get(0).get("deviceCode"));
				info.set("info-0-remark", faInfoInfoList.get(0).get("remark"));
				info.set("info-0-archiveFlag", faInfoInfoList.get(0).get("archiveFlag"));
				break;
			case "look":
				info.set("isByModificationNum",true);
				List<Map<String, Object>> faModificationInfoList = dao.query("FABG01.query", info.getAttr());
				info.set("build", faModificationInfoList.get(0).get("build"));
				info.set("floor", faModificationInfoList.get(0).get("floor"));
				info.set("installLocation", faModificationInfoList.get(0).get("installLocation"));
				info.set("installLocationNum", faModificationInfoList.get(0).get("installLocationNum"));
				info.set("info-0-faInfoId", faModificationInfoList.get(0).get("faInfoId"));
				info.set("info-0-goodsNum", faModificationInfoList.get(0).get("goodsNum"));
				info.set("info-0-goodsName", faModificationInfoList.get(0).get("goodsName"));
				info.set("info-0-goodsTypeCode", faModificationInfoList.get(0).get("goodsTypeCode"));
				info.set("info-0-goodsTypeCode_textField", faModificationInfoList.get(0).get("goodsTypeName"));
				info.set("info-0-goodsClassifyCode", faModificationInfoList.get(0).get("goodsClassifyCode"));
				info.set("info-0-goodsClassifyName", faModificationInfoList.get(0).get("goodsClassifyName"));
				info.set("info-0-rfidCode", faModificationInfoList.get(0).get("rfidCode"));
				info.set("info-0-model", faModificationInfoList.get(0).get("model"));
				info.set("info-0-unitNum", faModificationInfoList.get(0).get("unitName"));
				info.set("info-0-unitName", faModificationInfoList.get(0).get("unitName"));
				info.set("info-0-deptNum", faModificationInfoList.get(0).get("deptNum"));
				info.set("info-0-deptNum_textField", faModificationInfoList.get(0).get("deptName"));
				info.set("info-0-manufacturer", faModificationInfoList.get(0).get("manufacturer"));
				info.set("info-0-surpName", faModificationInfoList.get(0).get("surpName"));
				info.set("info-0-buyDate", faModificationInfoList.get(0).get("buyDate"));
				info.set("info-0-useDate", faModificationInfoList.get(0).get("useDate"));
				info.set("info-0-inAccountDate", faModificationInfoList.get(0).get("inAccountDate"));
				info.set("info-0-statusCode", faModificationInfoList.get(0).get("statusCode"));
				info.set("info-0-statusCodeMean", faModificationInfoList.get(0).get("statusCodeMean"));
				info.set("info-0-build", faModificationInfoList.get(0).get("build"));
				info.set("info-0-floor", faModificationInfoList.get(0).get("floor"));
				info.set("info-0-installLocation", faModificationInfoList.get(0).get("installLocation"));
				info.set("info-0-installLocationNum", faModificationInfoList.get(0).get("installLocationNum"));
				info.set("info-0-residualRate", faModificationInfoList.get(0).get("residualRate"));
				info.set("info-0-estimatedResidualValue", faModificationInfoList.get(0).get("estimatedResidualValue"));
				info.set("info-0-useYears", faModificationInfoList.get(0).get("useYears"));
				info.set("info-0-amount", faModificationInfoList.get(0).get("amount"));
				info.set("info-0-price", faModificationInfoList.get(0).get("price"));
				info.set("info-0-buyCost", faModificationInfoList.get(0).get("buyCost"));
				info.set("info-0-monthDepreciation", faModificationInfoList.get(0).get("monthDepreciation"));
				info.set("info-0-totalDepreciation", faModificationInfoList.get(0).get("totalDepreciation"));
				info.set("info-0-netAssetValue", faModificationInfoList.get(0).get("netAssetValue"));
				info.set("info-0-estimateCost", faModificationInfoList.get(0).get("estimateCost"));
				info.set("info-0-fundsSource", faModificationInfoList.get(0).get("fundsSource"));
				info.set("info-0-finaceClassNum", faModificationInfoList.get(0).get("finaceClassNum"));
				info.set("info-0-invoiceNo", faModificationInfoList.get(0).get("invoiceNo"));
				info.set("info-0-inAccountDate", faModificationInfoList.get(0).get("inAccountDate"));
				info.set("info-0-invoiceDate", faModificationInfoList.get(0).get("invoiceDate"));
				info.set("info-0-remark", faModificationInfoList.get(0).get("remark"));
				info.set("info-0-changeReason", faModificationInfoList.get(0).get("changeReason"));
				info.set("info-0-recCreateName", faModificationInfoList.get(0).get("recCreateName"));
				info.set("info-0-recCreateTime", faModificationInfoList.get(0).get("recCreateTime"));
				break;
		}
		return info;
	}
}