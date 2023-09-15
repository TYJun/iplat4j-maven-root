package com.baosight.wilp.hr.dp.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.dp.domain.HrJobChangeBill;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.保存调派信息 insert
 * <p>3.保存调派人员信息 saveJobMan
 *
 * @Title: ServiceHRDP0101.java
 * @ClassName: ServiceHRDP0101
 * @Package：com.baosight.wilp.hr.dp.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRDP0102 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
	 * id  主键
	 * type 1.获取type类型，
	 * 	    2.如果类型是edit则是编辑页面回显数据
     * @return
     * 		info
     * @see ServiceBase#query(EiInfo)
     */
	@SuppressWarnings("all")
	public EiInfo initLoad(EiInfo info) {

		Map map = new HashMap();
		map.put("id", info.getString("id") == null ? "" : info.getString("id"));
		map.put("billNo", info.getString("billNo") == null ? "" : info.getString("billNo"));
		List<HrJobChangeBill> list = dao.query("HRDP01.query", map);
		if (CollectionUtils.isEmpty(list)) {
			return info;
		}
		// 获取attr中属性放入info中
		info.setAttr(list.get(0).toMap());
		// 查询tab页信息
		List list1 = dao.query("HRDP02.query", map);
		info.addRows("result", list1);

		return info;
	}
}
