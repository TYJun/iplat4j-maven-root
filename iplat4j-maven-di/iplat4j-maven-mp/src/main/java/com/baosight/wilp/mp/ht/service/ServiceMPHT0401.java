package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.lj.domain.MpContract;
import com.baosight.wilp.mp.lj.domain.MpContractFile;
import com.baosight.wilp.mp.lj.service.MpContractService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * 合同台账详情子页面
 * <p>1.初始化查询 initLoad
 *
 * @Title: ServiceMPHT0401.java
 * @ClassName: ServiceMPHT0401
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 下午1:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceMPHT0401 extends ServiceBase {

	@Autowired
	private MpContractService contractService;

	/**
	 * @param inInfo
	 * 	id 主键
	 *  type 操作类型
	 * @return EiInfo
	 * @Title: initLoad
	 * @Description: 初始化查询
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		//获取合同信息
		String id = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id");
		List<MpContract> list = dao.query("MPLJ02.query", inInfo.getRow(MpConstant.QUERY_BLOCK, 0));
		inInfo.setRows(MpConstant.QUERY_BLOCK, list);
		//获取合同明细及关联的采购计划明细
		List<Map<String, Object>> details = dao.query("MPLJ02.queryDetailUnionPurchaseDetail", id);

		details.forEach(m -> {
			m.put("contractsNum", m.get("contedNum"));
			m.put("orderCost", new BigDecimal(String.valueOf(m.get("orderNum"))).multiply(new BigDecimal(String.valueOf(m.get("price")))));
			m.put("enterCost", new BigDecimal(String.valueOf(m.get("enterNum"))).multiply(new BigDecimal(String.valueOf(m.get("price")))));
			m.put("billedCost", new BigDecimal(String.valueOf(m.get("billedNum"))).multiply(new BigDecimal(String.valueOf(m.get("price")))));
			m.put("payCost", new BigDecimal(String.valueOf(m.get("payNum"))).multiply(new BigDecimal(String.valueOf(m.get("price")))));
		});
		inInfo.setRows("detail", details);
		//获取合同附件
		List<MpContractFile> files = contractService.queryFileList(id);
		inInfo.setRows("file", files);
		return inInfo;
	}
}
