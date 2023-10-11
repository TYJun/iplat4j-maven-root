package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.SSBMBqgl01;
import com.baosight.wilp.ss.bm.domain.SSBMCtm01;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * 病区管理service
 * 
 * @Title: ServiceSSBMBQGL01.java
 * @ClassName: ServiceSSBMBQGL01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午3:15:14
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMBQGL01 extends ServiceBase{
	

    /**
     * 
     * 初始化加载
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMBqgl01());
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//canteenDataGroup:食堂院区
		paramMap.put("userId", UserSession.getLoginName());
		List<HashMap<String,Object>> listDataGroup = dao.query("SSBMStxx01.queryCanteenDataGroup", paramMap);
		initLoad.addBlock("canteenDataGroup").addRows(listDataGroup);
		return initLoad;
	}

	/**
	 * 查询病区
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		//grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMBqgl01.query", new SSBMBqgl01());
		return outInfo;
	}

	/**
	 * 
     * 删除病区
	 * @param inInfo
	 * @return
	 * @see ServiceBase#delete(EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			List<HashMap<String,Object>> rows = inInfo.getBlock("result").getRows();
			//判断是否有被引用的病区
			boolean flag = true;
			for (int i = 0; i < rows.size(); i++) {
				HashMap<String, Object> hashMap = rows.get(i);
				String quoteAddress = StringUtil.toString(hashMap.get("quoteAddress"));
				if("1".equals(quoteAddress)) {
					flag = false;
				}
			}
			if(flag) {
				outInfo = super.delete(inInfo, "SSBMBqgl01.delete");
				outInfo.setMsg("操作成功！");
			}else {
				inInfo.setStatus(-1);
				inInfo.setMsg("所选项中有正在被引用的数据！");
				return inInfo;
			}
		}catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("操作失败！");
		}
		return outInfo;
	}


	/**
     * 新增病区
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
				
				//根据病区编码查询
				String deptCode = map.get("deptCode").toString();
				HashMap<String,Object> param = new HashMap<String,Object>();	
				param.put("deptCode", deptCode);
				List<SSBMCtm01> queryList = dao.query("SSBMBqgl01.queryByDeptCode", param);
				if(queryList.size()>0) {
					outInfo.setMsg("病区编码重复,请更换!");
					return outInfo;
					
				}		
				//修改数据
				map.put("status","1");
				map.put("recCreateTime",format);
				map.put("recCreator",UserSession.getLoginName());
				map.put("id",UUIDUtils.getUUID32());
				map.put("quoteAddress", "0");
				//将数据填充到result
				inInfo.addBlock("result").addRows(submitList);
				outInfo = super.insert(inInfo, "SSBMBqgl01.insert");
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
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				for (int i = 0; i < updateList.size(); i++) {
					HashMap<String, Object> updateMap = updateList.get(i);
					updateMap.put("recReviseTime",format);
					updateMap.put("recRevisor",UserSession.getLoginName());
				}
				//将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
			}
			//更新数据
			outInfo = super.update(inInfo, "SSBMBqgl01.update"); 
			outInfo.setMsg("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setMsg("更新失败！");
		}
		return outInfo;
	}
	
	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *	被床头码引用的设置病区引用状态为1，没有被引用的为0
	 * @Title: synchroDept同步
	 * @Param
	 * @return:
	 */
	public EiInfo synchroDept(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			//将没有被引用的病区设置为0
			int i = dao.update("SSBMBqgl01.synchroDept1",inInfo); 
			//将被引用的病区设置为1
			int j = dao.update("SSBMBqgl01.synchroDept2",inInfo); 
			outInfo.setMsg("共执行同步记录:" + (i + j));
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setStatus(-1);
			outInfo.setMsg("同步失败！");
		}
		return outInfo;
	}
	
}
