package com.baosight.wilp.mp.cg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.pz.domain.MpPurchaseTypeConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  采购计划管理Service
 * <p>页面加载</p>
 * <p>查询采购计划</p>
 * <p>删除</p>
 * <p>提交</p>
 * <p>撤回</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceMPCG01.java
 * @ClassName:  ServiceMPCG01
 * @Package com.baosight.wilp.mp.cg.service
 * @Description: TODO
 * @author lyf
 * @date:   2022年10月19日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceMPCG01 extends ServiceBase {
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo info){
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(com.baosight.iplat4j.core.web.threadlocal.UserSession.getLoginName());
		info.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
		info.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
		info.setCell(MpConstant.QUERY_BLOCK, 0, "recCreator", com.baosight.iplat4j.core.web.threadlocal.UserSession.getLoginName());
		info.setCell(MpConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
		// 设置info中的id
		MpUtils.initQueryTime(info,"recCreateTimeS","recCreateTimeE");
		return this.query(info);
	}

	/**
	 *
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo info){
		return super.query(info, "MPCG01.query", new MpPurchasePlan());
	}

	/**deleter
	 * 删除采购计划和明细表
	 * 删除采购计划和明细表
	 * @param info
	 * id 主键
	 * @return
	 */
	public EiInfo deleter(EiInfo info){
		//获取主键删除明细表和主表
		String id = info.getString("id");

		//获取来源单据ID
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		List<HashMap<String, Object>> list = dao.query("MPCG01.query", map);

		int delete = dao.delete("MPCG01.deleter",id);
		if(delete > 0) {
			dao.delete("MPCG01.deleterDetail", id);
		}
		//修改需求计划汇总页面已生成采购计划的状态为审批通过
		if(CollectionUtils.isNotEmpty(list)){
			String resourceId = MpUtils.toString(list.get(0).get("resourceId"));
			if(StringUtils.isNotBlank(resourceId)) {
				MpUtils.invoke("MPHZ02", "purchasePlanDelCallback", Arrays.asList("resourceId"), resourceId);
			}
		}
		return info;
	}

	/**submit
	 * 提交采购计划和明细表
	 * 提交采购计划和明细表
	 * @param info
	 * id 主键
	 * @return
	 */
	public EiInfo submit(EiInfo info){
		String id = info.getString("id");
		if(StringUtils.isBlank(id)){
			info.setStatus(-1);
			info.setMsg("请选择一条采购计划进行提交");
		}else {
           String statusCode = info.getString("statusCode");
           if(MpConstant.PROCUREMENT_STATUS_NEW.equals(statusCode) || MpConstant.PROCUREMENT_STATUS_REJECT.equals(statusCode)){
           		dao.update("MPCG01.submit",id);
		   }else {
           		info.setStatus(-1);
           		info.setMsg("提交后无须提交");
		   }
		}
		return info;
	}



	/**submit
	 * 撤回采购计划和明细表
	 * 撤回采购计划和明细表
	 * @param info
	 * id 主键
	 * @return
	 */
	public EiInfo withdraw(EiInfo info){
		String id = info.getString("id");
		if(StringUtils.isBlank(id)){
			return ValidatorUtils.errorInfo("请选择需要撤回的采购计划");
		}else {
			String statusCode = info.getString("statusCode");
			if(MpConstant.PROCUREMENT_STATUS_UN_APPROVAL.equals(statusCode)){
				dao.update("MPCG01.withdraw",id);
			}else {
				return ValidatorUtils.errorInfo("该采购计划无法撤回");
			}
		}
		return info;
	}

	/**获取采购额度
	 * @param info
	 * @return info
	 */
	public EiInfo comparePurchaseConfigCost(EiInfo info){

		String purchaseType = info.getString("purchaseType");
		//采购计划总价
		BigDecimal purchasePlanCost = new BigDecimal(info.getString("purchaseCost"));

		HashMap<String, Object> map = new HashMap<>();
		map.put("purchaseYear", DateUtils.curDateStr("yyyy"));
		map.put("purchaseType", purchaseType);

		//查询已提交的采购单总额
		List<HashMap<String, Object>> costList = dao.query("MPCG01.getPurchaseTypeAllCost", map);
		BigDecimal allPurchaseCost = CollectionUtils.isEmpty(costList) ? BigDecimal.ZERO : (BigDecimal) costList.get(0).get("purchaseCost");

		//查询采购总额度
		List<MpPurchaseTypeConfig> config = dao.query("MPPZ03.query", map);
		BigDecimal purchaseCost = CollectionUtils.isEmpty(config) ? BigDecimal.ZERO : config.get(0).getPurchaseCost();

		//计算剩余额度
		BigDecimal subtract = purchaseCost.subtract(allPurchaseCost);
		subtract = subtract.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : subtract;

		if(purchasePlanCost.compareTo(subtract) > 0){
			info.setStatus(-1);
			info.set("limit", subtract);
		}

		return info;
	}




}
