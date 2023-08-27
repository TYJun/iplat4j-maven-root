package com.baosight.wilp.fa.cf.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.fa.cf.domain.FaSplitDetailVO;
import com.baosight.wilp.fa.cf.domain.FaSplitVO;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 固定资产拆分管理详情类.
 * 固定资产拆分详情初始化方法.
 * 固定资产拆分管理详情查询方法.
 * 固定资产按价值拆分保存方法.
 *
 * @author zhaowei
 * @date 2022年07月13日 9:52
 */
public class ServiceFACF0102 extends ServiceBase {
	/**
	 * 固定资产拆分详情初始化方法.
	 * 1.查询固定资产拆分管理信息
	 * 2.查询固定资产拆分管理按价值拆分详情信息
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 13:31
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		info.setCell("inqu_status", 0, "faInfoId", info.getString("faInfoId"));
		String typeCode = OneSelfUtils.privateCreateCode(info.getString("goodsTypeCode"));
		EiInfo out = super.query(info, "FACF01.queryFaInfoDOInfo", new FaSplitVO(), false, null, null, "info", "info");
		out.setCell("info", 0, "goodsNumMax", typeCode);
		return out;
	}

	/**
	 * 固定资产拆分管理详情查询方法
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 13:31
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 固定资产按价值拆分保存方法
	 * 1.在主表中填写拆分理由
	 * 2.在主表中新增拆分信息
	 * 3.在拆分表中保存
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/26 13:31
	 */
	public EiInfo saveSplitByValueInfo(EiInfo info) {
		// 获取基础信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String splitNo = OneSelfUtils.publicCreateCode(FixedAssetsEum.CF.getAcronym());
		String splitReason = (String) info.getRow("info", 0).get("splitReason");
		FaSplitVO faSplitVO = new FaSplitVO();
		faSplitVO.setId(UUID.randomUUID().toString());
		faSplitVO.setSplitNo(splitNo);
		faSplitVO.setSplitReason(splitReason);
		faSplitVO.setSplitWay("00");
		faSplitVO.setStatusCode("100");
		faSplitVO.setRecCreateName((String) staffByUserId.get("name"));
		faSplitVO.setRecCreateTime(new Timestamp(new Date().getTime()));
		List<FaInfoDO> faInfoDOList = dao.query("FACF01.queryFaInfoDO", new HashMap<String, String>() {{
			put("goodsNum", info.getCellStr("info", 0, "goodsNum"));
		}});
		if (CollectionUtils.isNotEmpty(faInfoDOList)) {
			// 获取资产信息
			FaInfoDO faInfoDO = faInfoDOList.get(0);
			Map faInfoDOMap = faInfoDO.toMap();
			faInfoDOMap.put("recCreateTime",new Date());
			if (info.get("splitList") != "") {
				// 资产拆分列
				List<Map<String, Object>> splitList = (List<Map<String, Object>>) info.get("splitList");
				List<FaInfoDO> collect = splitList.stream().map(map -> {
							// 将资产信息替换成最新的拆分信息
							FaInfoDO infoDO = new FaInfoDO();
							infoDO.fromMap(faInfoDOMap);
							infoDO.setId(UUID.randomUUID().toString());
							infoDO.setFaInfoId(infoDO.getId());
							infoDO.setRecCreateName((String) staffByUserId.get("name"));
							infoDO.setRecCreator((String) staffByUserId.get("workNo"));
							infoDO.setGoodsNum((String) map.get("goodsNum"));
							infoDO.setGoodsName((String) map.get("goodsName"));
							infoDO.setBuyCost(new BigDecimal((String) map.get("buyCost")));
							infoDO.setTotalDepreciation(new BigDecimal((String) map.get("totalDepreciation")));
							infoDO.setNetAssetValue(new BigDecimal((String) map.get("netAssetValue")));
							return infoDO;
						}
				).collect(Collectors.toList());
				collect.stream().forEach(DO -> {
					if (DO.getGoodsNum().equals(faInfoDO.getGoodsNum())) {
						DO.setFaInfoId(faInfoDO.getFaInfoId());
					}
				});
				List<FaInfoDO> collect1 = collect.stream().filter(faInfoDO1 -> faInfoDO1.getGoodsNum().equals(faInfoDO.getGoodsNum())).collect(Collectors.toList());
				List<FaInfoDO> collect2 = collect.stream().filter(faInfoDO1 -> !faInfoDO1.getGoodsNum().equals(faInfoDO.getGoodsNum())).collect(Collectors.toList());
				List<Map<String, String>> collect3 = collect.stream().map(faInfoDO1 -> {
					Map<String, String> map = faInfoDO1.toMap();
					map.put("splitNo", splitNo);
					map.put("splitTime",DateUtils.curDateTimeStr19());
					return map;
				}).collect(Collectors.toList());
				// 在主表中新增拆分数据并修改主表中的原有记录
				dao.update("FACF01.updateSplitInfoToFaInfo", collect1.get(0));
				dao.insert("FACF01.insertSplitInfoToFaInfo", collect2);
				// 在拆分主表中插入数据
				dao.insert("FACF01.insertSplitInfoToFaSplit", faSplitVO);
				// 在拆分明细表中插入数据
				dao.insert("FACF01.insertSplitInfoToFaSplitDetail", collect3);
			}
		}
		return info;
	}
}
