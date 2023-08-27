package com.baosight.wilp.fa.qr.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年12月14日 11:25
 */
public class ServiceFAQR0101 extends ServiceBase {
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		List<Map<String, String>> mapList = dao.query("FAQR01.queryFaConfirmVOInfo", new HashMap<String, Object>() {{
			put("id", inInfo.getString("faConfirmId"));
		}});
		if (CollectionUtils.isNotEmpty(mapList)) {
			String matTypeName = (String) mapList.get(0).get("matTypeName");
			// 通过资产类别进行查询
			List<Map<String, String>> FaTypeInfo = dao.query("FAQR01.queryFaTypeInfoByMatTypeName", matTypeName);
			String manufacturerNatyCode = StringUtils.isNotEmpty(mapList.get(0).get("manufacturerNatyCode")) ? mapList.get(0).get("manufacturerNatyCode") : "156";
			mapList.get(0).put("manufacturerNatyCode", manufacturerNatyCode);
			inInfo.setRows("info", mapList);
			inInfo.set("surpNum", mapList.get(0).get("surpNum"));
			inInfo.set("surpName", mapList.get(0).get("surpName"));
			inInfo.set("deptNum", mapList.get(0).get("deptNum"));
			inInfo.set("deptName", mapList.get(0).get("deptName"));
			if (CollectionUtils.isNotEmpty(FaTypeInfo)) {
				inInfo.set("goodsTypeCode", FaTypeInfo.get(0).get("goodsTypeCode"));
				inInfo.set("goodsTypeName", FaTypeInfo.get(0).get("goodsTypeName"));
				inInfo.setCell("info", 0, "useYears", FaTypeInfo.get(0).get("useYears"));
				inInfo.setCell("info", 0, "goodsClassifyCode", FaTypeInfo.get(0).get("goodsClassifyCode"));
				inInfo.setCell("info", 0, "goodsClassifyName", FaTypeInfo.get(0).get("goodsClassifyName"));
			}
		}
		return inInfo;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		return super.query(inInfo);
	}

}
