package com.baosight.wilp.sq.wj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.sq.dx.service.ServiceSQDXSendMsg;
import com.baosight.wilp.sq.wj.domain.SQWJ01;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成问卷逻辑处理类，页面初始化方法，查询方法 ，修改问卷状态问卷， 删除问卷
 * <p>页面初始化方法 initload
 * <p>查询方法 query
 * <p>修改问卷状态问卷 update
 * <p>删除问卷 delete
 */
public class ServiceSQWJ01 extends ServiceBase {


	/**
	 * 页面初始化方法
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}


	/**
	 * 查询方法
	 */
	public EiInfo query(EiInfo inInfo) {
//		List list = dao.query("SQWJ01.query", new HashMap<>());
//		inInfo.setRows("result", list);
		EiInfo outInfo = super.query(inInfo, "SQWJ01.query", new SQWJ01());
		return outInfo;
	}


	/**
	 * 修改问卷状态问卷
	 *
	 * @param inInfo id               主键
	 * @return statusCode       状态码
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		ServiceSQDXSendMsg sendmsg = new ServiceSQDXSendMsg();
		String manageId = inInfo.getString("manageId");
		String UPD = inInfo.getString("UPD");
		if (UPD.equals("UPD")) {
			Map map = new HashMap();
			//完成状态99
			map.put("statusCode", "99");
			map.put("manageId", manageId);
			int update = dao.update("SQWJ01.updateSqManage", map);
			if (update == 0) {
				inInfo.setMsg("修改失败，请联系管理员");
				inInfo.setStatus(-1);
				return inInfo;
			}
			inInfo.setMsg("终止成功");
			inInfo.setStatus(1);
			return inInfo;
		}
		Map map1 = new HashMap();
		//完成状态99
		map1.put("statusCode", "10");
		map1.put("manageId", manageId);
		int update = dao.update("SQWJ01.updateSqManage", map1);
		if (update == 0) {
			inInfo.setMsg("修改失败，请联系管理员");
			inInfo.setStatus(-1);
			return inInfo;
		}
		inInfo.setMsg("开始成功");
		inInfo.setStatus(1);
		return inInfo;
	}


	/**
	 * 删除新增问卷
	 *
	 * @param inInfo id           主键
	 * @return setStatus   状态码
	 * setMsg      msg
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#delete(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>();
		String manageId = inInfo.getString("manageId");
		String batchNo = inInfo.getString("batchNo");
		map.put("manageId", manageId);
		map.put("batchNo", batchNo);
		int delete = dao.delete("SQWJ01.deleteManage", map);
		dao.delete("SQWJ01.deleteExamItem", map);
		dao.delete("SQWJ01.deleteExamInstance", map);
		dao.delete("SQWJ01.deleteExamProject", map);
		dao.delete("SQWJ01.deleteExamStandard", map);
		if (delete != 1) {
			inInfo.setMsg("删除失败，请联系管理员");
			inInfo.setStatus(-1);
			return inInfo;
		}
		inInfo.setMsg("删除成功");
		inInfo.setStatus(1);
		return inInfo;
	}

	// 终止周期计划
	public EiInfo stopCycle(EiInfo info) {
		Map<String, Object> map = new HashMap<>();
		String manageId = info.getString("manageId");
		String batchNo = info.getString("batchNo");
		map.put("manageId", manageId);
		map.put("batchNo", batchNo);
		dao.update("SQWJ01.stopCycle", map);
		return info;
	}
}
