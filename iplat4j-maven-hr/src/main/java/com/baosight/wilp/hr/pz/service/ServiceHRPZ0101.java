package com.baosight.wilp.hr.pz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.*;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.保存人员配置信息 insert
 *
 * @Title: ServiceHRPZ0101.java
 * @ClassName: ServiceHRPZ0101
 * @Package：com.baosight.wilp.hr.pz.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRPZ0101 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
	public EiInfo initLoad(EiInfo info){

		return info;
	}

	/**保存人员配置信息
	 * insert
	 * @param info
	 * id ： 主键
	 * serviceDeptNum ：服务科室编码
	 * serviceDeptName： 服务科室名称
	 * surpName ： 物业公司
	 * position ： 岗位
	 * peopleLines ： 招标配额
	 * peopleLinesStable ： 稳定配额
	 * memo ： 备注
	 * @return info
	 */
	public EiInfo insert(EiInfo info) {
		// 获取前台传来的参数
		Map<String, Object> userInfo = HrUtils.getUserInfo(UserSession.getUser().getUsername());
		String serviceDeptNum = info.getString("serviceDeptNum");
		String serviceDeptName = info.getString("serviceDeptName");
		String surpName = info.getString("surpName");
		String position = info.getString("position");
		String peopleLines = info.getString("peopleLines");
		String peopleLinesStable = info.getString("peopleLinesStable");
		String memo = info.getString("memo");

		Map map = new HashMap<>(16);
		// 封装获取的参数
		HrUtils.getUserInformation(map,info);
		map.put("id", UUID.randomUUID().toString());
		map.put("serviceDeptNum", serviceDeptNum);
		map.put("serviceDeptName", serviceDeptName);
		map.put("surpName", surpName);
		map.put("surpNum", "BM"+DateUtils.curDateTimeStr14());
		map.put("position", position);
		map.put("peopleLines", peopleLines);
		map.put("peopleLinesStable", peopleLinesStable);
		map.put("createTime", DateUtils.curDateTimeStr19());
		map.put("memo", memo);
		// 保存人员配置信息
		dao.insert("HRPZ01.insert",map);
		return info;
	}

}
