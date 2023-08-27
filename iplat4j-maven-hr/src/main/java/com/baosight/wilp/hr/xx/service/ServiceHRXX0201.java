package com.baosight.wilp.hr.xx.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.xx.domain.HrMan;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *人员信息查看页面
 * <p>1.初始化查询 initLoad
 * @Title: ServiceHRXX0201.java
 * @ClassName: ServiceHRXX0201
 * @Package：com.baosight.wilp.hr.xx.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRXX0201 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
	 * id 主键
     * @return info
     * @see ServiceBase#query(EiInfo)
     */
	@SuppressWarnings("all")
	public EiInfo initLoad(EiInfo info) {
		// 获取前台的id，查询出人员信息表的人员信息
		Map map = new HashMap();
		map.put("id", info.getString("id") == null ? "" : info.getString("id"));
		List<HrMan> list = dao.query("HRXX01.query", map);
		if (CollectionUtils.isEmpty(list)) {
			return info;
		}
		//获取attr中属性
		info.setAttr(list.get(0).toMap());
		List list1 = dao.query("HRXX02.query", map);
		List list2 = dao.query("HRXX03.query", map);
		//获取文件信息
		info.addRows("resultC", list1);
		//获取履历信息
		info.addRows("resultD", list2);
		return info;
	}

}
