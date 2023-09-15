package com.baosight.wilp.hr.dp.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.dp.domain.HrJobChangeBill;
import com.baosight.wilp.hr.xx.domain.HrMan;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *调派管理页面
 * <p>1.初始化查询 initLoad
 * <p>2.项目查询 query
 * <p>3.删除申请工单 delete
 * <p>4.提交申请单 submit
 *
 * @Title: ServiceHRDP01.java
 * @ClassName: ServiceHRDP01
 * @Package：com.baosight.wilp.hr.dp.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRDP01 extends ServiceBase {
	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param : info
	 * flag : dp 调派页面
	 * @return :outInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {return this.query(info);
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param : info
	 * flag : dp 调派页面
	 * @return :outInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo info) {
		// 设置flag值表示调派页面
		info.set("inqu_status-0-flag"," ");
		EiInfo outInfo = super.query(info,"HRDP01.query",new HrJobChangeBill());
		return outInfo;
	}

	/**
	 * 删除申请工单
	 * delete
	 * @param info
	 * billNo ：申请单号
	 * @return
	 */
	public EiInfo delete(EiInfo info) {
		// 获取前台传来的申请单号，删除子表和主表的数据
		String billNo = info.getString("billNo");
		int num = dao.delete("HRDP01.delete", billNo);
		dao.delete("HRDP02.delete", billNo);
		if (num > 0) {
			info.setMsg("删除成功");
			info.setStatus(1);
			return info;
		}
		info.setMsg("删除失败");
		info.setStatus(-1);
		return info;
	}

	/**
	 * 提交申请单
	 * submit
	 * @param info
	 * id :  id
	 * type :类型
	 * 1.如果type为submit则是申请页面提交按钮
	 * 2.如果type为agree则是审批页面同意按钮
	 * 3.如果type为back则是审批页面驳回按钮
	 * @return
	 */
	@SuppressWarnings("all")
	public EiInfo submit(EiInfo info) {
		// 获取前台传来的工单id
		String id = info.getString("id");
		/* 获取类型
		 * 1.如果type为submit则是申请页面提交按钮
		 * 2.如果type为agree则是审批页面同意按钮
		 * 3.如果type为back则是审批页面驳回按钮
		 */
		String type = info.getString("type");
		int num = 0;
		if (type.equals("submit")) {
			num = dao.update("HRDP01.submit", id);
		}
		if (type.equals("agree")) {
			num = dao.update("HRDP01.agree", id);
		}
		if (type.equals("back")) {
			// 获取驳回理由
			String auditOpinion = info.getString("auditOpinion");
			Map map = new HashMap();
			map.put("id", id);
			map.put("auditOpinion", auditOpinion);
			num = dao.update("HRDP01.back", map);
		}
		if (num > 0) {
			info.setMsg("操作成功");
			info.setStatus(1);
			return info;
		}
		info.setMsg("操作失败");
		info.setStatus(-1);
		return info;
	}
}
