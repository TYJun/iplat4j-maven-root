package com.baosight.wilp.si.rk.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.ValidatorUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 仓库入库管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>查询科室</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIRK01.java
 * @ClassName:  ServiceSIRK01
 * @Package com.baosight.wilp.si.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午1:37:58 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIRK01 extends ServiceBase {
	
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
		SiUtils.initQueryTime(inInfo, false);
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
		return query(inInfo);
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
		inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getUser().getUsername()));
    	inInfo.set("inqu_status-0-dataGroupCode", SiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
        EiInfo outInfo = super.query(inInfo, "SIRK01.query", new SiEnter());
        return outInfo;
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
		map.put("dataGroupCode", SiUtils.getDataGroupCode(UserSession.getUser().getUsername()));
		//调用工具类
		EiInfo dept = SiUtils.getDept(map);
		dept.setBlockInfoValue("dept", "showCount", "true");
		return dept;
	}

	/**
	 * 更新打印标记
	 * @Title: updatePrintFlag
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo updatePrintFlag(EiInfo inInfo) {
		String enterBillNos = inInfo.getString("enterBillNos");
		if(StringUtils.isNotBlank(enterBillNos)) {
			List<String> list = Arrays.asList(enterBillNos.split(","));
			dao.update("SIRK01.updatePrintFlag", list);
		}
		return inInfo;
	}

	/**
	 * 删除
	 * @Title: delete
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	@Override
	public EiInfo delete(EiInfo inInfo) {
		List<String> enterBillNos = SiUtils.toList(inInfo.get("enterBillNos"), String.class);
		if(CollectionUtils.isEmpty(enterBillNos)) {
			return ValidatorUtils.errorInfo("请选择需要删除的入库单");
		}
		//判断是否可以删除
		int count = super.count("SIRK01.hashDelete", enterBillNos);
		if(count == 0) {
			dao.delete("SIRK01.batchDeleteEnter", enterBillNos);
			dao.delete("SIRK01.batchDeleteEnterDetail", enterBillNos);
		} else {
			return ValidatorUtils.errorInfo("已验收的入库单无法删除");
		}
		return inInfo;
	}

}
