package com.baosight.wilp.ci.sq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 物资申请页面
 * <p>1.初始化查询 initLoad
 * <p>2.项目查询 query
 * <p>3.更新工单状态 updateSubmit
 * <p>4.删除工单 delete
 *
 * @Title: ServiceCISQ01.java
 * @ClassName: ServiceCISQ01
 * @Package：com.baosight.wilp.md.sq.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceCISQ01 extends ServiceBase {
	
	private static Lock lock = new ReentrantLock();

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * 		statusCode:工单状态
	 * 		startTime:制单日期起（>=）
	 * 		endTime:制单日期止（<=）
	 * @return inInfo
	 * @see ServiceBase#query(EiInfo)
	 */
    public EiInfo query(EiInfo inInfo) {
		String[] param = {"startTime","endTime","statusCode"};
		//将取参数封装包含分页
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result",Arrays.asList(param));
		List<Map<String, String>> list = dao.query("CISQ01.query", map);
		int count = dao.count("CISQ01.count", map);
		return CommonUtils.BuildOutEiInfo(inInfo, null, null, list, count);
    }


	/**
	 * 更新工单状态
	 * <p>Title: updateSubmit</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * idList ：id集合
	 * @return inInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo updateSubmit(EiInfo inInfo) {
	    List idList= (List) inInfo.get("idList");
		//获取id集合更新工单状态
		int count = dao.update("CISQ01.updateApplySign",idList);
		if (count>0){
			inInfo.setStatus(1);
			inInfo.setMsg("提交成功");
			return inInfo;
		}
		inInfo.setStatus(-1);
		inInfo.setMsg("提交失败");
		return inInfo;
	}
	/**
	 * 删除工单
	 * <p>Title: delete</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * idList ：id集合
	 * @return inInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo delete(EiInfo inInfo) {
		//获取id集合
		List idList= (List) inInfo.get("list");
		//根据id查询申请单号
		List<Map<String,String>> mapList = dao.query("CISQ01.getApplyBillNo",idList);
		if (!mapList.isEmpty()){
			Map<String,String> map = (Map) mapList.get(0);
			 String applyBillNo = (String) map.get("applyBillNo");
			 //根据申请单号删除申请明细
			int countDetail  = dao.delete("CISQ0101.delete" ,applyBillNo);
		}
		//删除主表数据
		int count  = dao.delete("CISQ01.delete" ,idList);
		if(count>0){
			inInfo.setStatus(1);
			inInfo.setMsg("删除成功");
			return inInfo;
		}
		inInfo.setStatus(-1);
		inInfo.setMsg("删除失败");
		return inInfo;
	}
}
