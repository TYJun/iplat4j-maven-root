package com.baosight.wilp.it.zx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.it.utils.CreateNum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 独立任务执行详情接口.
 * 1.独立任务执行详情初始化方法.
 * 第二段
 * 通过任务单号，获取对应独立任务执行详情信息.
 * 第三段
 * @author zhaowei
 * @date 2022年07月28日 17:11
 * @version V1.0
 */
public class ServiceITZX0102 extends ServiceBase {
	/*
	 * 第一段
	 * 独立任务执行详情初始化方法.
	 * 第二段
	 * 1.通过任务单号，获取对应独立任务信息.
	 * 2.获取前端操作类型.
	 * 2.1.查看分支.
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
		 * 1.通过任务单号进行数据库查询操作，获取对应独立任务信息
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
		List<Map<String,Object>> queryItTaskInfoList = dao.query("ITZX01.queryItTaskInfo", info.getAttr());
		/*
		 * 2.获取前端操作类型
		 * 根据操作类型进入不同的分支
		 */
		String type = info.getString("type");
		switch (type){
			/*
			 * 2.1.查看分支
			 * 如果查询结果不为空，将查询到的独立任务详情返回
			 * 并返回查看类型
			 */
			case "look":
				if (CollectionUtils.isNotEmpty(queryItTaskInfoList)){
					info.setAttr(queryItTaskInfoList.get(0));
					info.set("type","look");
				}
				break;
		}
		return info;
	}
}
