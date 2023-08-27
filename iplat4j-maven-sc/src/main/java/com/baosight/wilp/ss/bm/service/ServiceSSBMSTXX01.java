package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMStxx01;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * 食堂信息配置service
 * 
 * @Title: ServiceSSBMSTXX01.java
 * @ClassName: ServiceSSBMSTXX01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:32:07
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMSTXX01 extends ServiceBase{
	
	/**
	 * 初始化查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMStxx01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//初始化加载数据，hosArea:院区
		paramMap.put("mealgroupTypeCode", "hosArea");
		List<HashMap<String,Object>> listHosArea = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("hosArea").setRows(listHosArea);
		//canteenType:食堂类别
		paramMap.put("mealgroupTypeCode", "canteenType");
		List<HashMap<String,Object>> listCanteenType = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("canteenType").addRows(listCanteenType);
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

	@Override
	public EiInfo query(EiInfo inInfo) {
		//grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMStxx01.query", new SSBMStxx01());
		return outInfo;
	}

	/**
	 * 删除食堂
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
			//判断是否有启用的食堂
			boolean flag = true;
			for (int i = 0; i < rows.size(); i++) {
				HashMap<String, Object> hashMap = rows.get(i);
				String isEffective = hashMap.get("canteenStatus").toString();
				if("1".equals(isEffective)) {
					flag = false;
				}
			}
			if(flag) {
				outInfo = super.delete(inInfo, "SSBMStxx01.delete");
				outInfo.setMsg("操作成功！");
			}else {
				inInfo.setStatus(-1);
				inInfo.setMsg("所选项中有正在生效的数据！");
				return inInfo;
			}
		}catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("操作失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}

	/**
	 * 新增食堂
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
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				//修改数据
				map.put("recCreateTime",format);
				map.put("id",UUIDUtils.getUUID32());
				//调用服务
				HashMap<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("pix", "ST");
				paramMap.put("leng", "6");
				paramMap.put("serialNum", "dcCanteen");
				EiInfo call = LocalServiceUtil.call("SSBMTyTserial", "getSerialIndex", paramMap);
				if(call.getStatus() < 0) {
					outInfo.setMsg("获取食堂编码失败！");
				}else {
					//获取食堂编码
					String canteenNum = call.getAttr().get("serialIndex").toString();;
					map.put("canteenNum",canteenNum);
					//将数据填充到result
					inInfo.addBlock("result").addRows(submitList);
					outInfo = super.insert(inInfo, "SSBMStxx01.insert");
					if ("1".equals(StringUtil.toString(map.get("canteenStatus")))){
						//食堂新增时就启用自动添加订餐时间
						dao.delete("SSBMDcsj01.deleteByCanteenNum",map);
						dao.insert("SSBMDcsj01.autoInsert1",map);
						dao.insert("SSBMDcsj01.autoInsert2",map);
						dao.insert("SSBMDcsj01.autoInsert3",map);
					}
					outInfo.setMsg("保存成功！");
				}
			}else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		}catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("保存失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
	
	/**
	 * 编辑数据
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> updateList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			if(updateList != null && updateList.size() > 0) {
				//将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				//更新数据
				outInfo = super.update(inInfo, "SSBMStxx01.update"); 
				outInfo.setMsg("更新成功！");
			}else {
				inInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
				return inInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("更新失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}
	
	/**
	 * 获取sc_type中的不同分类数据
	 * hosArea:院区，mealOperate:食堂业务，canteenType:食堂类别，mealNum:餐次类型
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EiInfo getMealTypeData(EiInfo inInfo) {
		try {
			Map attr = inInfo.getAttr();
			HashMap<String,Object> paramMap = (HashMap<String, Object>) attr.get("mealgroupTypeCode");
			List<HashMap<String,Object>> list = dao.query("SSBMStxx01.getMealTypeData", paramMap);
			inInfo.addBlock("mealTypeData").addRows(list);
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("查询失败！");
		}
        return inInfo;
	}
	
}
