package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMStgg01;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订餐数据字典service
 * */
public class ServiceSSBMSJZD01 extends ServiceBase{
	
	/**
	 * 初始化查询
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMStgg01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//初始化加载数据，noticeType:公告类型
		paramMap.put("mealgroupTypeCode", "noticeType");
		List<HashMap<String,Object>> listHosArea = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("noticeType").setRows(listHosArea);
		return initLoad;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		//grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMStgg01.query", new SSBMStgg01());
		return outInfo;
	}

	/**
	 * 删除数据
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
			//判断是否有生效中的公告
			boolean flag = true;
			for (int i = 0; i < rows.size(); i++) {
				HashMap<String, Object> hashMap = rows.get(i);
				String isEffective = hashMap.get("isEffective")+"";
				if("1".equals(isEffective)) {
					flag = false;
				}
			}
			if(flag) {
				super.delete(inInfo, "SSBMStgg01.delete");
				outInfo.setMsg("操作成功！");
			}else {
				outInfo.setMsg("所选项中有正在生效的数据！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("操作失败！");
		}
		return outInfo;
	}

	/**
	 * 保存数据
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//获取传参
			List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
			HashMap<String,Object> map = rows.get(0);
			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String format = smf.format(new Date());
			//修改数据
			map.put("isEffective","1");
			map.put("effectiveDate",format);
			map.put("recCreateTime",format);
			map.put("inputDate",format.substring(0, 10));
			map.put("id",UUIDUtils.getUUID32());
			outInfo = super.insert(inInfo, "SSBMStgg01.insert");
			outInfo.setMsg("保存成功！");
		}catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("保存失败！");
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
			ArrayList<HashMap<String,Object>> updateList = (ArrayList<HashMap<String,Object>>) attr.get("update");
			if(updateList != null && updateList.size() > 0) {
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				for (int i = 0; i < updateList.size(); i++) {
					HashMap<String, Object> updateMap = updateList.get(i);
					//修改时间数据
					String isEffective = updateMap.get("isEffective").toString();
					if("1".equals(isEffective)) {
						updateMap.put("effectiveDate",format);
					}
					updateMap.put("inputDate",format.substring(0, 10));
				}
				//将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
			}
			//更新数据
			outInfo = super.update(inInfo, "SSBMStgg01.update"); 
			outInfo.setMsg("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("更新失败！");
		}
		return outInfo;
	}
}
