package com.baosight.wilp.ci.rk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.CiEnter;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 仓库入库管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>查询科室</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIRK01.java
 * @ClassName:  ServiceCIRK01
 * @Package com.baosight.wilp.ci.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午1:37:58 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIRK01 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 100);
		inInfo.addBlock("processsResult").set(EiConstant.limitStr, 100);
		return this.query(inInfo);
    }
	
	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		enterBillNo:入库单号
	 * 		enterType:入库类别
	 * 		beginTime:制单日期起（>=）
	 * 		endTime:制单日期止（<=）
	 * @return   
	 * 		id ：主键
	 *		enterBillNo : 入库单号
	 *		enterType : 入库类别
	 *		originBillNo : 来源单据号
	 *		wareHouseName : 仓库
	 *		billMakeTime : 制单日期
	 *		billMakerName : 制单人员
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo.setCell(EiConstant.queryBlock, 0, "isCheck", 0);
    	inInfo.set("inqu_status-0-dataGroupCode", CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
        EiInfo outInfo = super.query(inInfo, "CIRK01.query", new CiEnter());
		List<Map<String, Object>> list = dao.query("CIRK01.queryItem", new HashMap(2) {{
			put("enterBillNo", inInfo.getAttr().get("mainEnterBillNo"));
		}});
		System.out.println("enterBillNo:"+inInfo.getAttr().get("mainEnterBillNo"));
		outInfo.setRows("processsResult", list);
//		System.out.println("list:"+list);
        return outInfo;
    }

	/**
	 * 查询入库明细
	 * @Title: queryDetail
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo queryDetail(EiInfo inInfo) {
		System.out.println("inInfo:--"+inInfo);
		List<CiEnterDetail> list = dao.query("CIRK01.queryItem", new HashMap(2) {{
			put("enterBillNo", inInfo.getAttr().get("mainEnterBillNo"));
		}});
		System.out.println("enterBillNo:"+inInfo.getAttr().get("mainEnterBillNo"));
		inInfo.setRows("processsResult", list);
		System.out.println("list:"+list);
		return inInfo;
	}
	
	/**
	 * 查询科室
	 * @Title: queryDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		deptNum：科室编码
	 * 		deptName : 科室名称
	 * @param:  @return
	 * @return: EiInfo
	 * 		deptNum：科室编码
	 * 		deptName : 科室名称  
	 * @throws
	 */
	public EiInfo queryDept(EiInfo inInfo) {
		//参数处理
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "dept");
		map.put("dataGroupCode", CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
		if(inInfo.getAttr().containsKey("pagesize")){
			map.put("limit",inInfo.get("pagesize"));
		}
		//调用工具类
		EiInfo dept = CiUtils.getDept(map);
		dept.setBlockInfoValue("dept", "showCount", "true");
		return dept;
	}


	/**
	 * 查询所有科室，用于app端处理展示
	 * @Title: queryDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		deptNum：科室编码
	 * 		deptName : 科室名称
	 * @param:  @return
	 * @return: EiInfo
	 * 		deptNum：科室编码
	 * 		deptName : 科室名称
	 * @throws
	 */
	public EiInfo queryDeptAll(EiInfo inInfo) {
		//参数处理
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "dept");
		map.put("dataGroupCode", CiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
		map.put("limit",2000);//设置最多查询2000个科室
		//调用工具类
		EiInfo dept = CiUtils.getDept(map);
		dept.setBlockInfoValue("dept", "showCount", "true");
		return dept;
	}


}
