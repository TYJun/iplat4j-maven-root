package com.baosight.wilp.it.zx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.it.utils.CreateNum;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 独立任务执行接口.
 * 1.独立任务执行初始化方法.
 * 2.独立任务执行方法.
 * 第二段
 * 通过单号查看独立任务执行详情信息.
 * 对独立任务详情进行执行.
 * 第三段
 * @author zhaowei
 * @date 2022年07月28日 17:11
 * @version V1.0
 */
public class ServiceITZX0101 extends ServiceBase {
	/*
	 * 第一段
	 * 独立任务执行初始化方法.
	 * 第二段
	 * 1.通过数据库查询操作匹配单号对应的独立任务详情信息.
	 * 2.获取前端操作类型.
	 * 2.1.执行分支.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/16 10:57
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 1.通过数据库查询操作匹配单号对应的独立任务详情信息
		 */
		if("all".equals(CreateNum.getDataGroupCode(null))){
			if (CreateNum.getDataGroupCodeAll(null)!=null){
				info.set("dataGroupCode", CreateNum.getDataGroupCodeAll(null));
				info.set("dataGroupCodeAll", StringUtils.join(CreateNum.getDataGroupCodeAll(null).toArray(),","));
			}
		}else {
			List<String> dataGroupCode = new ArrayList<>();
			dataGroupCode.add(CreateNum.getDataGroupCode(null));
			info.set("dataGroupCode", dataGroupCode);
		}
		List<Map<String,Object>> queryitTaskInfoList = dao.query("ITZX01.queryItTaskInfo", info.getAttr());
		/*
		 * 2.获取前端操作类型
		 * 根据操作类型进入不同的分支
		 */
		String type = info.getString("type");
		switch (type){
			/*
			 * 2.1.执行分支
			 * 返回查到的独立任务详情信息
			 * 并手动设置操作类型
			 */
			case "completion":
				if (CollectionUtils.isNotEmpty(queryitTaskInfoList)){
					info.setAttr(queryitTaskInfoList.get(0));
					info.set("type","completion");
				}
				break;
		}
		return info;
	}

	/*
	 * 第一段
	 * 独立任务执行方法.
	 * 第二段
	 * 1.获取用户组信息，并设置参数信息.
	 * 2.通过工单号进行数据库更新操作.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/16 10:57
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo completion(EiInfo info){
		/*
		 * 1.获取用户组信息，并设置参数信息
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		info.set("finishManNo",staffByUserId.get("workNo"));
		info.set("finishManName",staffByUserId.get("name"));
		info.set("finishTime", DateUtils.curDateTimeStr19());
		info.set("status", "02");
		/*
		 * 2.通过工单号进行数据库更新操作
		 */
		dao.update("ITZX01.completion",info.getAttr());
		return info;
	}
}
