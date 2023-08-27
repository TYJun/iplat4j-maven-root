package com.baosight.wilp.cm.zh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.*;

public class ServiceCMZH00 extends ServiceBase {
	/**
	 * 调用初始化方法
	 * @author zhaowei
	 * @date 2022/2/11 14:03
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		return this.querySplitDate(info);
	}

	/**
	 * 接口一：查询登记执行过程中的合同
	 * 入参：月份
	 * 出参：合同总数，超期合同，临期合同，正常合同
	 *
	 * @param info
	 * @return
	 */
	public EiInfo querySplitDate(EiInfo info) {
		EiBlock block = new EiBlock("result");
		String splitDate = (String) info.get("splitDate");
		Map<String, Object> map = new HashMap<>();
		map.put("splitDate", splitDate);
		List<Map<String, Object>> list = dao.query("CMZH01.querySplitDate", map);
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("normal","0");
		resultMap.put("oneMonth","0");
		resultMap.put("twoMonth","0");
		resultMap.put("threeMonth","0");
		resultMap.put("overdue","0");
		int count = 0;
		int total = 0;
		for (Map<String, Object> result : list) {
			count = Integer.valueOf(result.get("planCount").toString());
			if (result.get("planStatus").toString().equals("5")){
				resultMap.put("normal",result.get("planCount").toString());
			}else if (result.get("planStatus").toString().equals("6")){
				resultMap.put("oneMonth",result.get("planCount").toString());
			}else if (result.get("planStatus").toString().equals("7")){
				resultMap.put("twoMonth",result.get("planCount").toString());
			}else if (result.get("planStatus").toString().equals("8")){
				resultMap.put("threeMonth",result.get("planCount").toString());
			}else if (result.get("planStatus").toString().equals("9")){
				resultMap.put("overdue",result.get("planCount").toString());
			}
			total = total + count;
		}
		resultMap.put("total",String.valueOf(total));
		resultList.add(resultMap);
		info.addRows("result",resultList);
		return info;
	}

	/**
	 * 接口二：合同折线图
	 * 出参：个数，月份
	 * 入参：状态，月份
	 */
	public EiInfo queryLineChart(EiInfo info) {
		String splitDate = (String) info.get("splitDate");
		String contStatus = (String) info.get("contStatus");
		Map<String, Object> map = new HashMap<>();
		map.put("splitDate", splitDate);
		map.put("contStatus", contStatus);
		List<Map<String, Object>> list = dao.query("CMZH01.queryLineChart", map);
		info.addRows("result",list);
		return info;
	}

	/**
	 * 接口三：登记合同圆饼图
	 * 出参：个数
	 * 入参：状态，月份
	 */
	public EiInfo queryPieChart(EiInfo info) {
		String splitDate = (String) info.get("splitDate");
		String contStatus = (String) info.get("contStatus");
		Map<String, Object> map = new HashMap<>();
		map.put("splitDate", splitDate);
		map.put("contStatus", contStatus);
		List<Map<String, Object>> list = dao.query("CMZH01.queryPieChart", map);
		return info;
	}
}
