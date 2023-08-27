package com.baosight.wilp.mp.cg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  编辑计划详情页面Service
 * <p>页面加载</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceMPCG0105.java
 * @ClassName:  ServiceMPCG0105
 * @Package com.baosight.wilp.mp.cg.service
 * @Description: TODO
 * @author lyf
 * @date:   2022年10月19日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceMPCG0105 extends ServiceBase {
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		String id = info.getString("id");
		String type = info.getString("type");
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		if ("edit".equals(type)) {
			//如果是回显操作回显数据
			List<Map<String, Object>> inqu = dao.query("MPCG01.query", map);
			List<Map<String, Object>> result = dao.query("MPCG01.queryDetail", map);
			info.set("inqu_status-0-recCreator", inqu.get(0).get("recCreator"));
			info.set("inqu_status-0-recCreateTime", inqu.get(0).get("recCreateTime"));
			info.set("inqu_status-0-purchaseRemark",inqu.get(0).get("purchaseRemark"));
			info.set("inqu_status-0-purchaseType",inqu.get(0).get("purchaseType"));
			info.set("id", inqu.get(0).get("id"));
			//返回信息
			info.setRows("result", result);
		}
		return info;
	}
}
