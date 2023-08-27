package com.baosight.wilp.mp.ht.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpContract;
import com.baosight.wilp.mp.lj.domain.MpContractDetail;
import com.baosight.wilp.mp.lj.service.MpContractHistoryService;
import com.baosight.wilp.mp.lj.service.MpContractService;
import com.baosight.wilp.mp.lj.service.MpProcurementPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 该页面为采购合同管理
 * <p>1.初始化查询 initLoad
 * <p>2.合同模块数据查询 query
 * <p>3.合同生效 takeEffect
 * <p>4.合同删除 delete
 * <p>5.合同终止 stop
 * @Title: ServiceMPHT01.java
 * @ClassName: ServiceMPHT01
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 上午9:41:58
 * @version: V5.0.0
 */

public class ServiceMPHT01 extends ServiceBase {

	@Autowired
	private MpContractService contractService;

	@Autowired
	private MpProcurementPlanService purchasePlanService;

	@Autowired
	private MpContractHistoryService historyService;

    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param
     * @return EiInfo
     */
    @Override
	public EiInfo initLoad(EiInfo info){
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
		info.setCell(MpConstant.QUERY_BLOCK, 0, "manageDeptNum", deptMap.get("deptNum"));
		info.setCell(MpConstant.QUERY_BLOCK, 0, "manageDeptName", deptMap.get("deptName"));
		info.setCell(MpConstant.QUERY_BLOCK, 0, "recCreator", UserSession.getLoginName());
		info.setCell(MpConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
		// 初始化页面时间查询参数
		MpUtils.initQueryTime(info,"recCreateTimeS","recCreateTimeE");
		return this.query(info);
	}

	/**
	 * 页面数据查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 */
	@Override
	public EiInfo query(EiInfo info){
		return super.query(info, "MPLJ02.query", new MpContract());
	}

   /**
    * 生效
    * @Title: takeEffect
    * @param inInfo inInfo
	* 		id: 合同ID
    * @return com.baosight.iplat4j.core.ei.EiInfo
    * @throws
    **/
	public EiInfo takeEffect(EiInfo inInfo) {
	    //获取参数
		String id = inInfo.getString("id");
		if(StringUtils.isBlank(id)) {
			return ValidatorUtils.errorInfo("参数不能为空");
		}
		//更新合同
		MpContract contract = MpContract.getStatusInstance(id, MpConstant.CONTRACT_STATUS_USE);
		contract.setStatusCodes(Arrays.asList(MpConstant.CONTRACT_STATUS_NEW));
		int update = contractService.update(contract);
		//数据返回
		if(update == 0) {
			return ValidatorUtils.errorInfo("合同不存在或已经生效过");
		}
		//添加履历
		historyService.saveEffectHistory(id, UserSession.getLoginName(), UserSession.getLoginCName());
		return inInfo;
	}
	
	/**
	 * 合同信息删除
	 * @Title: delete
	 * @param inInfo inInfo
	 * 		id: 合同ID
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	@Override
	public EiInfo delete(EiInfo inInfo) {
		//获取参数
		String id = inInfo.getString("id");
		if(StringUtils.isBlank(id)) {
			return ValidatorUtils.errorInfo("参数不能为空");
		}
		//删除合同
		int delete = contractService.delete(id);
		if(delete == 0) {
			return ValidatorUtils.errorInfo("合同不存在或无法删除");
		}
		//删除合同明细及附件
		List<MpContractDetail> details = contractService.queryContractDetailList(id);
		contractService.deleteDetail(id);
		contractService.deleteContFile(id);
		//修改采购计划中已生成合同数量
		purchasePlanService.updateContNum(details, "reduce");
		return inInfo;
	}
	
	/**
     * @Title: stop
     * @Description: 合同终止
     * @param inInfo inInfo
	 * 		id: 合同ID
     * @return: EiInfo
     */
	public EiInfo stop(EiInfo inInfo) {
		//获取参数
		String id = inInfo.getString("id");
		if(StringUtils.isBlank(id)) {
			return ValidatorUtils.errorInfo("参数不能为空");
		}
		//更新合同
		MpContract contract = MpContract.getStatusInstance(id, MpConstant.CONTRACT_STATUS_STOP);
		contract.setStatusCodes(Arrays.asList(MpConstant.CONTRACT_STATUS_NEW, MpConstant.CONTRACT_STATUS_USE));
		int update = contractService.update(contract);
		//数据返回
		if(update == 0) {
			return ValidatorUtils.errorInfo("合同不存在");
		}
		//添加履历
		historyService.saveTerminationHistory(id, UserSession.getLoginName(), UserSession.getLoginCName());
		return inInfo;
	}
}
