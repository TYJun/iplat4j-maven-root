package com.baosight.wilp.si.ck.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.common.SiConstant;
import com.baosight.wilp.si.common.SiUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 已签收页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSICK0106.java
 * @ClassName:  ServiceSICK0106
 * @Package com.baosight.wilp.si.ck.service
 * @Description: TODO
 * @author lyf
 * @date:   2023年5月22日 下午2:08:03
 * @version V1.0
 *
 */
public class ServiceSICK0106 extends ServiceBase {
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
        SiUtils.initQueryTime(inInfo, false);
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
		return query(inInfo);
    }
	
	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		outBillNo:出库单号
	 * 		outType:出库类别
	 * 		userDeptName:领用科室
	 * 		beginTime:制单日期起(>=)
	 * 		endTime:制单日期止(<=)
	 * @return
	 * 		id : 主键
	 *		outBillNo : 出库单号
	 *		outTypeName : 出库类别
	 *		originBillNo : 来源单据号
	 *		wareHouseName : 仓库
	 *		userDeptName : 领用科室
	 *		billMakeTime : 制单日期
	 *		billMakerName : 制单人员   
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo.set("workNo", UserSession.getLoginName());
		inInfo.set("name", UserSession.getLoginCName());
		EiInfo eiInfo =SiUtils.invoke(inInfo,"SITY02", "selectUserBusinessDept");
		List<Map<String, String>> list = eiInfo.getBlock("userDept").getRows();
		if(CollectionUtils.isEmpty(list)) {
			//不查出任何数据
			inInfo.setCell(EiConstant.queryBlock, 0, "costDeptNums", Arrays.asList("no data"));
		} else {
			List<String> codes = list.stream().map(map -> map.get("deptNum")).collect(Collectors.toList());
			inInfo.setCell(EiConstant.queryBlock, 0, "costDeptNums", codes);
		}
		inInfo.set("inqu_status-0-dataGroupCode", SiUtils.getDataGroupCode(com.baosight.xservices.xs.util.UserSession.getUser().getUsername()));
		inInfo.set("inqu_status-0-isCheck", SiConstant.OUT_BULL_SIGNED);
        EiInfo outInfo = super.query(inInfo, "SICK01.query", new SiOut());
        return outInfo;
    }

}
