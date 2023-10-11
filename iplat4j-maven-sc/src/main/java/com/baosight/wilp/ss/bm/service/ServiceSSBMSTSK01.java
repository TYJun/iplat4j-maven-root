package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMStsk01;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * 收款账号配置service
 * 
 * @Title: ServiceSSBMSTSK01.java
 * @ClassName: ServiceSSBMSTSK01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:32:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMSTSK01 extends ServiceBase{
	

    /**
     * 
     * 初始化查询
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMStsk01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//初始化加载数据
		//canteenType:食堂类别
		paramMap.put("mealgroupTypeCode", "canteenType");
		List<HashMap<String,Object>> listCanteenType = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("canteenType").addRows(listCanteenType);
		//mealOperate:食堂信息
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String,Object>> listCanteenData = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenData);
		
		return initLoad;
	}

	/**
	 * 
	 * grid数据集查询收款账号
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		//
		EiInfo outInfo = super.query(inInfo, "SSBMStsk01.query", new SSBMStsk01());
		return outInfo;
	}


	/**
	 * 删除数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#delete(EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		return super.delete(inInfo, "SSBMStsk01.delete");
		
		/*		EiInfo outInfo = new EiInfo();
				try {
					super.delete(inInfo, "SSBMStsk01.delete");
					outInfo.setMsg("操作成功！");
				}catch (Exception e) {
					e.printStackTrace();
					outInfo.setMsg("操作失败！");
				}
				return outInfo;*/
	}


	/**
	 * 
	 * 新增数据
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
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				//修改数据
				map.put("createDate",format);
				map.put("id",UUIDUtils.getUUID32());
				String canteenNum = "";
				map.put("canteenNum",canteenNum);
				//将数据填充到result
				inInfo.addBlock("result").addRows(submitList);
				outInfo = super.insert(inInfo, "SSBMStsk01.insert");
				outInfo.setMsg("保存成功！");
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
			ArrayList<HashMap<String,Object>> updateList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			if(updateList != null && updateList.size() > 0) {
				//将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				//更新数据
				outInfo = super.update(inInfo, "SSBMStsk01.update"); 
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
}
