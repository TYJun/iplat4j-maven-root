package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ss.bm.domain.SSBMStzf01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 支付方式配置service
 * 
 * @Title: ServiceSSBMSTZF01.java
 * @ClassName: ServiceSSBMSTZF01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:31:50
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMSTZF01 extends ServiceBase{
	
	/**
	 * 初始化查询
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMStzf01());
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//canteenType:食堂类别
		paramMap.put("mealgroupTypeCode", "canteenType");
		List<HashMap<String,Object>> listCanteenType = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("canteenType").addRows(listCanteenType);
		//orallyType:入口类别
		List<HashMap<String,Object>> listorallyType = dao.query("SSBMStzf01.queryOrallyType", paramMap);
		initLoad.addBlock("orallyType").addRows(listorallyType);
		return initLoad;
	}

	/**
	 * grid数据集查询
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "SSBMStzf01.query", new SSBMStzf01());
		return outInfo;
	}

	/**
	 * 删除数据
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		return super.delete(inInfo, "SSBMStzf01.delete");
	}

	/**
	 * 新增数据
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		return super.insert(inInfo, "SSBMStzf01.delete");
	}
	
	/**
	 * 启用/停用
	 * */
	public EiInfo effect(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			Map attr = inInfo.getAttr();
			//接收弹窗数据
			ArrayList<HashMap<String,Object>> updateList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
			if(updateList != null && updateList.size() > 0) {
				//将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				//更新数据
				outInfo = super.update(inInfo, "SSBMStzf01.update"); 
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
	 * 更新数据
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		// TODO Auto-generated method stub
		return super.update(inInfo, "SSBMStzf01.update");
	}

	/**
	 * 调整排序
	 * 
	 * */
	public EiInfo saveSort(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//获取页面重新排序后的数据
			Map attr = inInfo.getAttr();
			ArrayList<HashMap<String,Object>> sortList = (ArrayList<HashMap<String,Object>>) attr.get("sort");
			
			inInfo.addBlock("result").addRows(sortList);
			outInfo = super.update(inInfo, "SSBMStzf01.update"); 
			
			outInfo.setMsg("更新成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("更新失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
        return outInfo;
	}
	
	public void test(EiInfo inInfo) {
		//调用注册的服务
		EiInfo eiInfo = new EiInfo();
		//对应 xm01 画面维护的服务注册信息
		eiInfo.set(EiConstant.serviceId,"S_TY_TSERIAL");
		eiInfo.set(EiConstant.methodName,"getSerialIndex");
		EiInfo info = XServiceManager.call(eiInfo);
		//注意必须对 outInfo 的 status 状态进行校验
		if(info.getStatus() < 0){
			System.out.println("获取序号失败！");
		}
	}
	public void test1(EiInfo inInfo) {
		EiInfo eiInfo = new EiInfo();
		//设置服务名
		eiInfo.set(EiConstant.serviceName, "SSBMTyTserial");
		//设置方法名
		eiInfo.set(EiConstant.methodName, "getSerialIndex");
		EiInfo outInfo = XLocalManager.call(eiInfo);
		if(outInfo.getStatus()<0){
			System.out.println("获取序号失败！");
		}
	}
}
