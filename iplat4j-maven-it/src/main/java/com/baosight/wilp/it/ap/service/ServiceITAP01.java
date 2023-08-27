package com.baosight.wilp.it.ap.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.it.utils.CreateNum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 独立任务医生活App接口.
 * 1.App工单查询接口.
 * 2.App工单完工接口.
 * 第二段
 * 1.App工单查询接口:通过账套、完工状态，查询待完工信息、已完工信息、工单详情.
 * 2.App工单完工接口:通过工单Id将该工单进行完工.
 * 第三段
 * @author zhaowei
 * @date 2022年08月11日 14:03
 * @version v1.0
 */
public class ServiceITAP01 extends ServiceBase {
	/**
	 * 第一段
	 * App工单查询接口.
	 * 第二段
	 * 通过账套（dataGroupCode）、完工状态（status）[no-待完工,yes-已完工]
	 * 查询待完工信息、已完工信息、工单详情.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/12 16:09
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 调用数据库查询方法，传入分页、账套、完工状态.
		 * 将获取的数据存入blockId是result的数据块中
		 */
		if("all".equals(CreateNum.getDataGroupCode(info.getString("workNo")))){
			if (CreateNum.getDataGroupCodeAll(info.getString("workNo"))!=null){
				info.set("dataGroupCode", CreateNum.getDataGroupCodeAll(info.getString("workNo")));
				info.set("dataGroupCodeAll", StringUtils.join(CreateNum.getDataGroupCodeAll(info.getString("workNo")).toArray(),","));
			}
		}else {
			List<String> dataGroupCode = new ArrayList<>();
			dataGroupCode.add(CreateNum.getDataGroupCode(info.getString("workNo")));
			info.set("dataGroupCode", dataGroupCode);
		}
		List<Map<String, Object>> queryitTaskInfoList = dao.query("ITAP01.queryItTaskInfo", info.getAttr(), Integer.valueOf(info.getString("offset")), Integer.valueOf(info.getString("limit")));
		info.setRows("result", queryitTaskInfoList);
		return info;
	}

	/**
	 * 第一段
	 * App工单完工接口.
	 * 第二段
	 * 通过工单Id
	 * 更改工单状态.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/12 17:03
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo completion(EiInfo info) {
		/*
		 * 1.获取用户组信息.
		 * 2.将用户组参数进行封装.
		 * 3.通过工单Id，更改工单状态.
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo((String) info.getAttr().get("workNo"));
		info.set("finishManNo", staffByUserId.get("workNo"));
		info.set("finishManName", staffByUserId.get("name"));
		info.set("finishTime", DateUtils.curDateTimeStr19());
		info.set("status", "02");
		dao.update("ITAP01.completion", info.getAttr());
		return info;
	}
}
