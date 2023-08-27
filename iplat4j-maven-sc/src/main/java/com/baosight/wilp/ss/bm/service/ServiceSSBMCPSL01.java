package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMCpsl01;
import com.baosight.wilp.ss.bm.domain.SSBMStzf01;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


/**
 * 菜品数量配置service
 * 
 * @Title: ServiceSSBMCPSL01.java
 * @ClassName: ServiceSSBMCPSL01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:52:57
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMCPSL01 extends ServiceBase{
	

    /**
     * 
     * 初始化查询
     * @param inInfo
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMStzf01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//初始化加载数据canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String,Object>> listCanteenData = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenData);
		//mealOperate:食堂业务
		paramMap.put("mealgroupTypeCode", "mealOperate");
		List<HashMap<String,Object>> listMealOperate = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("mealOperate").addRows(listMealOperate);
		return initLoad;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: query
	 * @Param
	 * @return:
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		// grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMCpsl01.query", new SSBMCpsl01());
		return outInfo;
	}

	

	/**
	 * 
	 * 编辑数据
	 * @param inInfo
	 * @return
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#update(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		List<HashMap<String, String>> list = (List<HashMap<String, String>>)inInfo.getAttr().get("rows");
		EiInfo outInfo = new EiInfo();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("获得数据-------------------------------");
			String id = list.get(i).get("id");
			System.out.println(id);
			String num = String.valueOf(list.get(i).get("num"));      //菜品总量
			System.out.println(num);
			String surplus = String.valueOf(list.get(i).get("surplus"));      //剩余份量
			String priceAfterDiscount = String.valueOf(list.get(i).get("priceAfterDiscount"));      //折扣价格
			System.out.println(surplus);
			System.out.println(priceAfterDiscount);
			Integer surplus1 = Integer.valueOf(surplus);
			System.out.println(surplus1);
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("id", id);
			List<SSBMCpsl01> list1 = dao.query("SSBMCpsl01.queryNum", hashMap);
			Integer number = list1.get(0).getNum();            //查询的菜品总量
			System.out.println(number);
			Integer num1 = Integer.valueOf(num);              //转个类型
			Integer a = number - num1;
			if (a >= 0) {
				surplus1 = surplus1 - a;
				if (surplus1 < 0) {
					outInfo.setMsg("剩余数量不足以减去修改数量，请重新修改");
					outInfo.setStatus(-1);
					return outInfo;
				}
			} else {
				surplus1 = surplus1 - a;
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("surplus", surplus1);
			map.put("num", num);
			map.put("priceAfterDiscount", priceAfterDiscount);
			dao.update("SSBMCpsl01.update", map);
		}
		outInfo.setMsg("修改成功");
		outInfo.setStatus(1);
		return outInfo;
	}
}
