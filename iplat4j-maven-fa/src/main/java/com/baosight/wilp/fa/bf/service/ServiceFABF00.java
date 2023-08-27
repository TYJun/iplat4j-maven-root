package com.baosight.wilp.fa.bf.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.sun.org.apache.bcel.internal.generic.NEW;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2023年03月09日 9:36
 */
public class ServiceFABF00 extends ServiceBase {
	@Override
	public EiInfo initLoad(EiInfo info) {
		String goodsClassifyCode = info.getString("goodsClassifyCode");
		// 通过资产类别编码查询鉴定科室与归口科室
		List<Map<String,Object>> msgList = dao.query("FABF01.queryDeptNameByCode", goodsClassifyCode);
		info.setRows("info",msgList);
		return info;
	}


	public EiInfo query(EiInfo info) {
		return info;
	}

	/**
	 * 资产科指定鉴定科室并将资产类别进行保存到鉴定科室
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2023/3/10 10:13
	 */
	public EiInfo appointExpert(EiInfo info) {
		EiBlock block = info.getBlock("info");
		Map<String, String> row = block.getRow(0);
		String functionId = row.get("id");
		if (info.getAttr().get("goods") != null) {
			List<Map<String, String>> goods = (List<Map<String, String>>) info.getAttr().get("goods");
			if (CollectionUtils.isNotEmpty(goods)) {
				// 查询之前的保存记录看看是否需要加入
				List<Map<String, String>> queryOnceRecord = dao.query("FABF01.queryOnceRecord", functionId);
				List<String> list = new ArrayList<>();
				for (int i = 0; i < queryOnceRecord.size(); i++) {
					Map<String, String> map = queryOnceRecord.get(i);
					list.add(map.get("goodsClassifyCode"));
				}
				list = list.stream().distinct().collect(Collectors.toList());
				for (Map map : goods) {
					String goodsClassifyCode = (String) map.get("goodsClassifyCode");
					if (list.contains(goodsClassifyCode)) {
						continue;
					}
					map.put("id", UUID.randomUUID().toString());
					map.put("functionId", functionId);
					dao.insert("FABF01.appointExpert", map);
				}
			}
		}
		return info;
	}
}
