package com.baosight.wilp.cp.pz.service;

import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cp.domain.CpBill;
import com.baosight.wilp.cp.domain.CpWorkerBindDept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2023年06月16日 19:14
 */
public class ServiceCPPZ01 extends ServiceBase {
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo info = super.query(inInfo, "CPPZ01.query", new CpWorkerBindDept());
		return info;
	}

	public EiInfo queryCpDept(EiInfo info){
		info.set("result-limit", 1000);
		String pEname = info.getCellStr(EiConstant.queryBlock, 0, "node");
		List<Map<String,String>> deptInfo = dao.query("CPPZ01.queryCpDept", new HashMap<>());
		EiInfo outInfo = new EiInfo();
		EiBlock outBlock = outInfo.addBlock(pEname);
		outBlock.addRows(deptInfo);
		return outInfo;
	}

	public EiInfo delete(EiInfo info){
		String id = info.getString("id");
		List<String> ids = new ArrayList<String>(){{
			add(id);
		}};
		try {
			int i = dao.delete("CPPZ01.delete", ids);
			info.setMsg("删除成功");
			info.setStatus(EiConstant.STATUS_SUCCESS);
		} catch (Exception e){
			info.setMsg(e.toString());
			info.setStatus(EiConstant.STATUS_FAILURE);
		}
		return info;
	}

	/**
	 * 查询基础框架中的人员
	 * @author zhaowei
	 * @date 2023/6/19 9:26
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryWorker(EiInfo info){
		try {
			SqlMapDao sqlMapDao = (SqlMapDao) this.dao;
			sqlMapDao.setMaxQueryCount(99999);
			List<Map<String,String>> list = dao.query("CPPZ01.queryWorker", new HashMap<>());
			info.addBlock("worker").addRows(list);
		}catch (Exception e){
			info.setMsg(e.getMessage());
		}
		return info;
	}

	/**
	 * 查询基础框架中的科室
	 * @author zhaowei
	 * @date 2023/6/19 9:26
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryDept(EiInfo info){
		try {
			List<Map<String,String>> list = dao.query("CPPZ01.queryDept", new HashMap<>());
			info.addBlock("dept").addRows(list);
		}catch (Exception e){
			info.setMsg(e.getMessage());
		}
		return info;
	}
}
