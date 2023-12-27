package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 采购入库子页面Service
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIRK0102.java
 * @ClassName:  ServiceSIRK0102
 * @Package com.baosight.wilp.si.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午4:43:59
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIRK0102 extends ServiceBase {

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
		if(StringUtils.isNotBlank(inInfo.getString("enterBillNo"))) {
			//获取入库单
			List<SiEnter> enterList = dao.query("SIRK01.query", new HashMap(2) {{
				put("enterBillNoEQ", inInfo.getString("enterBillNo"));
			}});
			inInfo.setRows(EiConstant.queryBlock, enterList);
			//获取入库明细
			List<SiEnterDetail> list = dao.query("SIRK0101.query", new HashMap(2) {{
				put("enterBillNo", inInfo.getString("enterBillNo"));
			}});
			list.forEach(row -> {row.setEnterAmount(0.00); row.setEnterNum(0d);});
			inInfo.setRows(EiConstant.resultBlock, list);
			if(CollectionUtils.isNotEmpty(list)) {
				SiEnterDetail detail = list.get(0);
				inInfo.setCell(EiConstant.queryBlock, 0, "supplierNum", detail.getSurpNum());
				inInfo.setCell(EiConstant.queryBlock, 0, "supplierName", detail.getSurpName());
			}
		}
        return inInfo;
    }

	/**
	 * 获取指定供应商最后一次的入库记录
	 * @Title: queryLastEnterBySupplier
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo queryLastEnterBySupplier(EiInfo inInfo) {
		List<SiEnterDetail> list = dao.query("SIRK0101.query", new HashMap(4) {{
			put("showBySupplierNum", "Y");
			put("supplierNum", inInfo.getString("supplierNum"));
		}});
		list.forEach(row -> {row.setEnterAmount(0.00); row.setEnterNum(0d);});
		inInfo.setRows(EiConstant.resultBlock, list);
		return inInfo;
	}
}
