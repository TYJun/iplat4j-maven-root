package com.baosight.wilp.hr.lz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.xx.domain.HrMan;

import java.util.HashMap;
import java.util.Map;

/**
 *人员离职管理页面
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 * <p>3.取消离职按钮,确认离职按钮 updateCancel
 *
 * @Title: ServiceHRLZ01.java
 * @ClassName: ServiceHRLZ01
 * @Package：com.baosight.wilp.hr.lz.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRLZ01 extends ServiceBase {
	/**
	 * 初始化查询
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
		EiBlock eiBlock = new EiBlock("result");
		eiBlock.setAttr(new HashMap(){{
			put("showCount","true");
			put("offset","0");
			put("count","0");
			put("limit","50");
			put("orderBy","");
		}});
		info.setBlock(eiBlock);
		if (!"admin".equals(UserSession.getLoginName())) {
			info.setCell("inqu_status", 0, "managementDeptNum", deptMap.get("deptName"));
		}
		return this.query(info);
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * flag   : lz离职页面
	 * workNo : 工号
	 * name   : 姓名
	 * jobCode : 岗位
	 * company : 公司名称
	 *statusCode :状态
	 * deptNum   :所属科室
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo info) {
		// 设置flag值表示离职页面
		info.set("inqu_status-0-flag","lz");
		info.set("inqu_status-0-statusCode","03");
		EiInfo outInfo = super.query(info,"HRXX01.query",new HrMan());
		return outInfo;
	}

	/**
	 * 取消离职按钮,确认离职按钮
	 *  1.type如果是cancel则是取消离职按钮，
	 *  2.type如果是remove则是离职按钮
	 * <p>Title: query</p>
 	 * <p>Description: </p>
	 * @param info
	 * type ：类型
	 * id  ：主键
	 * @return
	 */
	public EiInfo updateCancel(EiInfo info) {
		// 获取前台数据type 和id
		String id = info.getString("id");
		String type = info.getString("type");
		// 1.如果type是cancel则是取消离职按钮，
		if (type.equals("cancel")) {
			// 更新人员表的预离职时间
			dao.update("HRLZ01.updateCancel", id);
			info.setMsg("取消离职成功");
			info.setStatus(1);
		}
		// 2.如果type是remove则是离职按钮
		if (type.equals("remove")) {
			// 获取离职时间
			String outTime = info.getString("outTime");
			Map map = new HashMap();
			map.put("id", id);
			map.put("outTime", outTime);
			// 更新人员表的离职信息
			dao.update("HRLZ01.updateRemove", map);
			// 将记录插入履历表
			HrUtils.insertHistory(id, "离职", dao);
			info.setMsg("离职成功");
			info.setStatus(1);
		}
		return info;
	}
}
