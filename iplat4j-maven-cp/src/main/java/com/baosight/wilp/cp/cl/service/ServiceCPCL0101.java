package com.baosight.wilp.cp.cl.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cp.domain.CpBillDeal;
import com.baosight.wilp.cp.util.CPUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  投诉处理子页面Service
 * <p>页面加载</p>
 * <p>保存投诉处理跟进情况</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCPCL0101.java
 * @ClassName:  ServiceCPCL0101
 * @Package com.baosight.wilp.cp.cl.service
 * @Description:
 * @author gcc
 * @date:   2022年4月10日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCPCL0101 extends ServiceBase {
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param  inInfo
	 * id  主键
	 * dealReason 处理原因
	 * idendify  识别
	 * dealType   处理类型
	 * estimateDealDate 预计处理日期
	 * dealDept  处理部门
	 * dealWorkName  处理人
	 * dealDate  处理日期
	 * dealReason 处理原因
	 * dealDesc  处理描述
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 获取参数
		String id = inInfo.getString("id");
		String billNo =inInfo.getString("billNo");
		Map<String, Object> userInfo = CPUtils.getUserInfo(null);
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new CpBillDeal().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			map.put("billNo",billNo);
			// 调用查询方法，显示上方列表信息
			List<HashMap<String, String>> list = dao.query("CPCL01.queryId", map);
			// 数据返回
			block.addRows(list);
			inInfo.set("complaintDate", list.get(0).get("complaintDate"));
		}
		inInfo.addBlock(block);
		inInfo.set("inqu_status-0-dealDept", userInfo.get("deptName"));
		inInfo.set("inqu_status-0-dealWorkName", userInfo.get("name"));
		return inInfo;

	}

	/**
	 * 保存投诉处理情况
	 * <p>Title: saveComolaintHandling</p>
	 * <p>Description: </p>
	 * @param :inInfo
	 * 	 * id  主键
	 * 	 * idendify  识别
	 * 	 *  deal_type 处理类型
	 * 	 * estimate_deal_date 预计处理时间
	 * 	 * deal_dept 处理部门
	 * 	 * deal_work_name 处理人
	 * 	 * deal_date 处理时间
	 * 	 * deal_reason 处理原因
	 * 	 * deal_desc 处理描述
	 * @return
	 */
	public EiInfo saveComolaintHandling(EiInfo inInfo){
		// 1.获取表单参数
		String billNo =inInfo.getString("billNo");
		Map<String,String> map = inInfo.getRow("inqu_status", 0);
		Map<String, Object> userInfo = CPUtils.getUserInfo(null);
		map.put("id",UUID.randomUUID().toString());
		map.put("billNo",billNo);
		map.put("creator",(String) userInfo.get("workNo"));
		map.put("createName", (String) userInfo.get("name"));
		try {
			//插入项目
			dao.insert("CPCL01.saveComolaintHandling", map);
			//更新工单状态
			dao.update("CPCL01.updateComolaintHandling",map);
			inInfo.setStatus(EiConstant.STATUS_SUCCESS);
			inInfo.setMsg("处理完成");
		} catch (Exception e){
			inInfo.setStatus(EiConstant.STATUS_FAILURE);
			inInfo.setMsg(e.toString());
		}
		return inInfo;
	}
}
