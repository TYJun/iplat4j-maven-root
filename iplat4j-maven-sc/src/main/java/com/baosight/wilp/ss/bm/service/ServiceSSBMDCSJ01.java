package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMDcsj01;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 订餐时间配置service
 * 
 * @Title: ServiceSSBMDCSJ01.java
 * @ClassName: ServiceSSBMDCSJ01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:16:15
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMDCSJ01 extends ServiceBase{
	

    /**
     * 
     * 初始化查询
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMDcsj01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//初始化加载数据canteenData:食堂名称
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String,Object>> listCanteenData = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenData);
		//mealOperate:食堂业务
		paramMap.put("mealgroupTypeCode", "mealOperate");
		List<HashMap<String,Object>> listMealOperate = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("mealOperate").addRows(listMealOperate);
		//mealNum:餐次类型
		paramMap.put("mealgroupTypeCode", "mealNum");
		List<HashMap<String,Object>> listMealNum = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("mealNum").addRows(listMealNum);
		return initLoad;
	}


	/**
	 * 
	 * grid数据集查询订餐时间
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSBMDcsj01.query", new SSBMDcsj01());
		return outInfo;
	}


	/**
	 * 
	 * 根据食堂编码和餐次查询订餐时间
	 *
	 * @Title: queryByCanteenMeal 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:17:18
	 */
	public EiInfo queryByCanteenMeal(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 食堂编号
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String mealNum = StringUtil.toString(attr.get("mealNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("canteenNum", canteenNum);
		paramMap.put("mealNum", mealNum);
		List<SSBMDcsj01> canteenTimes = dao.query("SSBMDcsj01.query", paramMap);
		outInfo.set("obj", canteenTimes);
		return outInfo;
	}
	

	/**
	 * 
	 * 删除订餐时间
	 * @param inInfo
	 * @return
	 * @see ServiceBase#delete(EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			outInfo = super.delete(inInfo, "SSBMDcsj01.delete");
			outInfo.setMsg("操作成功！");
		}catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("操作失败！");
			inInfo.setStatus(-1);
			return inInfo;
			
		}
		return outInfo;
	}


	/**
	 * 
	 * 新增订餐时间
	 * @param inInfo
	 * @return
	 * @see ServiceBase#insert(EiInfo)
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//获取传参
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			if(submitList != null && submitList.size() > 0) {
				HashMap<String,Object> map = submitList.get(0);
				//判断该食堂对应餐次是否存在记录
				String canteenNum = StringUtils.toString(map.get("canteenNum"));
				String mealNum = StringUtils.toString(map.get("mealNum"));
				int count = countCanteenMeal(canteenNum,mealNum);
				if(count > 0) {
					outInfo.setMsg("订餐时间已存在！");
				}else {
					map.put("id",UUIDUtils.getUUID32());
					//将数据填充到result
					inInfo.addBlock("result").addRows(submitList);
					outInfo = super.insert(inInfo, "SSBMDcsj01.insert");
					outInfo.setMsg("保存成功！");
				}
			}else {
				outInfo.setMsg("数据提交失败！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("保存失败！");
		}
		return outInfo;
	}
	

	/**
	 * 
	 * 查询指定食堂指定餐次订餐时间的记录数
	 *
	 * @Title: countCanteenMeal 
	 * @param canteenNum
	 * @param mealNum
	 * @return 
	 * @return: int 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:18:03
	 */
	public int countCanteenMeal(String canteenNum,String mealNum) {
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//初始化加载数据canteenData:食堂名称
		paramMap.put("canteenNum", canteenNum);
		paramMap.put("mealNum", mealNum);
		List query = dao.query("SSBMDcsj01.count", paramMap);
		int i = (int) query.get(0);
		return i;
	}
	

	/**
	 * 
	 * 编辑数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#update(EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			if(submitList != null && submitList.size() > 0) {
				//将数据填充到result
				inInfo.addBlock("result").addRows(submitList);
				//更新数据
				outInfo = super.update(inInfo, "SSBMDcsj01.update"); 
				outInfo.setMsg("更新成功！");
				
			}else {
				outInfo.setMsg("数据提交失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("更新失败！");
		}
		return outInfo;
	}
}
