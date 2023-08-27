package com.baosight.wilp.it.pj.service;

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
 * 独立任务评价评价接口.
 * 1.独立任务评价初始化方法.
 * 2.独立任务评价评价方法.
 * 第二段
 * 通过单号查看独立任务评价详情信息.
 * 对独立任务详情进行评价.
 * 第三段
 * @author zhaowei
 * @date 2022年07月28日 17:06
 * @version V1.0
 */
public class ServiceITPJ0101 extends ServiceBase {
	/*
	 * 第一段
	 * 独立任务评价初始化方法.
	 * 第二段
	 * 1.通过数据库查询操作匹配单号对应的独立任务详情信息.
	 * 2.获取前端操作类型.
	 * 2.1.评价分支.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/16 10:51
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
		List<Map<String,Object>> queryItTaskInfoList = dao.query("ITPJ01.queryItTaskInfo", info.getAttr());
		/*
		 * 2.获取前端操作类型
		 * 根据操作类型进入不同的分支
		 */
		String type = info.getString("type");
		switch (type){
			/*
			 * 2.1.评价分支
			 * 返回查到的独立任务详情信息
			 * 并手动设置操作类型和默认分数
			 */
			case "evaluate":
				if (CollectionUtils.isNotEmpty(queryItTaskInfoList)){
					info.setAttr(queryItTaskInfoList.get(0));
					info.set("type","evaluate");
					info.set("evalPoint","5");
				}
				break;
		}
		return info;
	}

	/*
	 * 第一段
	 * 独立任务评价评价方法.
	 * 第二段
	 * 1.手动设置状态.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/16 10:51
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo evaluate(EiInfo info){
		/*
		 * 1.手动设置状态，通过数据库更新操作改变指定工单的状态
		 */
		info.set("status","03");
		dao.update("ITPJ01.evaluate",info.getAttr());
		return info;
	}
}
