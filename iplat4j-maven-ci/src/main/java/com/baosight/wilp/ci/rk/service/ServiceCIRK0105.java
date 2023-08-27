package com.baosight.wilp.ci.rk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 物资选择子页面Service 采购单入库
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIRK0105.java
 * @ClassName:  ServiceCIRK0104
 * @Package com.baosight.wilp.ci.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2022年7月29日 下午2:38:40
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIRK0105 extends ServiceBase {

	private static Lock lock = new ReentrantLock();

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		//默认只查询审核通过的
		inInfo.set("justAuthOk","1");
		EiInfo initLoad = this.query(inInfo);

		//获取小代码小票报表地址配置
		EiInfo callCode = CiUtils.callCode("S_ED_02", "wilp.ci.jc.frUrl", "notify");
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)callCode.get("list");
		initLoad.addBlock("notify").addRows(list);
		return initLoad;
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * 		startTime:制单日期起（>=）
	 * 		endTime:制单日期止（<=）
	 * @return inInfo
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {

		String[] param = {"startTime","endTime","justAuthOk"};
		//将取参数封装包含分页
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result",Arrays.asList(param));
		List<Map<String, String>> list = dao.query("CICG02.queryDetail", map);
		int count = dao.count("CICG02.countDetail", map);
		return CommonUtils.BuildOutEiInfo(inInfo, null, null, list, count);
	}



}
