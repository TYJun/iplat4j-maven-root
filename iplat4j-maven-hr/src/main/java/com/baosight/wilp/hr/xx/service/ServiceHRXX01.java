package com.baosight.wilp.hr.xx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.common.ResultData;
import com.baosight.wilp.hr.pb.utils.LocalServiceUtil;
import com.baosight.wilp.hr.xx.domain.HrMan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 * <p>3.删除新建人员信息 delete
 * <p>4.调用远程服务获取科室信息 queryDept
 * <p>5.更新人员信息（入职和取消入职） updateInduction
 *
 * @Title: ServiceHRXX01.java
 * @ClassName: ServiceHRXX01
 * @Package：com.baosight.wilp.hr.xx.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRXX01 extends ServiceBase {
	/**
	 * 初始化查询
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return outInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
		EiBlock eiBlock = new EiBlock("result");
		eiBlock.setAttr(new HashMap(){{
			put("showCount","true");
			put("offset","0");
			put("count","0");
			put("limit","50");
			put("orderBy","");
		}});
		info.setBlock(eiBlock);
		if (!"admin".equals(UserSession.getLoginName())) {
			info.setCell("inqu_status", 0, "managementDeptNum", deptMap.get("deptName"));
		}
		return this.query(info);
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * workNo : 工号
	 * name   : 姓名
	 * jobCode : 岗位
	 * company : 公司名称
	 * statusCode :状态
	 * deptNum   :所属科室
	 * @return outInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo info) {
		EiInfo outInfo = super.query(info,"HRXX01.query",new HrMan());
		return outInfo;
	}

	/**
	 * 删除新建人员信息
	 * delete
	 * 1.根据前台获取的id 删除人员主表数据、人员证书表、人员履历表数据
	 * @param info
	 * @return
	 */
	public EiInfo delete(EiInfo info) {
		// 1.根据前台获取的id 删除人员主表数据、人员证书表、人员履历表数据
		String id = info.getString("id");
		// 删除人员主表数据
		int num = dao.delete("HRXX01.delete", id);
		// 删除人员证书表
		dao.delete("HRXX02.delete", id);
		// 删除人员履历表数据
		dao.delete("HRXX03.delete", id);
		if (num > 0) {
			info.setStatus(1);
			info.setMsg("删除成功");
			return info;
		}
		info.setStatus(-1);
		info.setMsg("删除失败");
		return info;
	}

	/**
	 * @param info
	 * @return info
	 * deptNum : 科室编码
	 * deptName : 科室名称
	 * @Title: queryDept
	 * <p>1.调用远程服务获取科室信息</p>
	 * @Description: 接口改造(科室)
	 */
	public EiInfo queryDept(EiInfo info) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
		return BaseDockingUtils.getDeptAllPage(map, "dept");
	}

	/**
	 *更新人员信息（入职和取消入职）
	 * updateInduction
	 * @param info id   ：  主键
	 * type  类型 1.如果值为uninduction则是取消入职
	 * 2.如果值为induction则是确认入职
	 * @return
	 */
	@SuppressWarnings("all")
	public EiInfo updateInduction(EiInfo info) {
		// 获取id 和类型值
		String id = info.getString("id");
		String type = info.getString("type");
		 /*类型 1.如果值为uninduction则是取消入职
		  *   2.如果值为induction则是确认入职
		  */
		if (type.equals("uninduction")) {
			// 更新新建状态为取消入职状态
			dao.update("HRXX01.updateUninduction", id);
			// 插入履历表
			HrUtils.insertHistory(id, "取消入职", dao);
			info.setMsg("取消入职成功");
			info.setStatus(1);
		}
		if (type.equals("induction")) {
			// 获取入职时间
			String inTime = info.getString("inTime");
			Map map = new HashMap();
			map.put("id", id);
			map.put("inTime", inTime);
			// 更新人员主表入职时间
			dao.update("HRXX01.updateInduction", map);
			// 插入履历表
			HrUtils.insertHistory(id, "入职", dao);
			info.setMsg("入职成功");
			info.setStatus(1);
		}
		return info;
	}

	public ResultData getUserInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//获取前端参数
		String updateDate = request.getParameter("updateDate");
		Map map = new HashMap();
		map.put("updateDate", updateDate);

		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.callNoTx("HRXX0101", "getUserInfo", map);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("ServiceHRXX0101.getUserInfo失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg(call.getMsg());
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
				resultData.setRespCode("200");
				resultData.setRespMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		return resultData;
	}

	public ResultData putUserInfo(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		//获取前端参数
		try {
			//调用服务接口
			EiInfo call = LocalServiceUtil.callNoTx("HRXX0101", "putUserInfo", null);
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("ServiceHRXX0101.putUserInfo失败！");
				resultData.setRespCode("201");
				resultData.setRespMsg(call.getMsg());
				resultData.setSuccess(false);
			}else {
				resultData.setObj(call.getAttr().get("obj"));
				resultData.setRespCode("200");
				resultData.setRespMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		return resultData;
	}
}
