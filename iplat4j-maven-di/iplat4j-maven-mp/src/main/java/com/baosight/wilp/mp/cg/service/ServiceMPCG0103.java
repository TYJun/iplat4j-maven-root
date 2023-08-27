package com.baosight.wilp.mp.cg.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.math.BigDecimal;
import java.util.*;

/**
 *  拆分采购计划子页面Service
 * <p>页面加载</p>
 * <p>拆分采购计划</p>
 * <p>拆分采购计划物资明细</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceMPCG0103.java
 * @ClassName:  ServiceMPCG0103
 * @Package com.baosight.wilp.mp.cg.service
 * @Description: TODO
 * @author lyf
 * @date:   2022年10月19日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceMPCG0103 extends ServiceBase {
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		String id = info.getString("id");
		String type = info.getString("type");
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		if ("split".equals(type)) {
			//如果是回显操作回显数据
			List<Map<String, Object>> inqu = dao.query("MPCG01.query", map);
			List<Map<String, Object>> result = dao.query("MPCG01.queryDetail", map);
			info.set("inqu_status-0-purchaseNo",inqu.get(0).get("purchaseNo"));
			info.set("inqu_status-0-recCreator", inqu.get(0).get("recCreator"));
			info.set("inqu_status-0-recCreateTime", inqu.get(0).get("recCreateTime"));
			info.set("id", inqu.get(0).get("id"));
			//返回信息
			info.setRows("result", result);
		}
		return info;
	}

	/**
	 * 保存采购计划拆分
	 * <p>Title: saveProcurementPlanning</p>
	 * <p>Description: </p>
	 * @param info
	 * 	 * id  主键
	 * 	 * deptName_textField 科室编码
	 * 	 * deptName  科室名称
	 * 	 * recCreator 创建人
	 * 	 * recCreateTime 创建时间
	 * 	 * remark 备注
	 * @return
	 */
	public EiInfo saveProcurementSplit(EiInfo info) {
		// 如果不为空就是编辑
		String id;
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String recCreator = (String) staffByUserId.get("name");
		//获取科室编码和科室名称
		String workNo = (String)staffByUserId.get("workNo");
		Map<String,Object> workNoo = new HashMap<>();
		workNoo.put("workNo",workNo);
		EiInfo personal = getPersonnelCode(workNoo);
		Map<String,Object> deptInformation = (Map<String, Object>) personal.getAttr().get("result");
		String deptName = (String) deptInformation.get("deptName");
		String deptNum  = (String) deptInformation.get("deptNum");
		EiBlock result = info.getBlock("result");
		List<Map<String, Object>> rows = new ArrayList<>();
		/*
		* 开启物资检验
		*
		* */
		//物资信息为空时，提示："请选择选择物资"
		if (result != null) {
			rows = result.getRows();
			List<String> purchasingInformation = new ArrayList<>();
		    for(Map<String, Object> purchaseQuantity : rows){
				purchasingInformation.add((String) purchaseQuantity.get("num"));
		    	if(purchasingInformation.contains("")){
		    		info.setMsg("采购数量不能为空");
		    		info.setStatus(-2);
		    		return info;
				}
		    	for(int i =0 ; i< purchasingInformation.size(); i++){
		    		Double c = Double.valueOf((purchasingInformation.get(i)));
		    		if(c < 0){
						info.setMsg("采购数量不能小于0");
						info.setStatus(-3);
						return info;
					}else if(c == 0){
		    			info.setMsg("采购数量不能等于0");
		    			info.setStatus(-4);
		    			return info;
					}
				}
			}
		} else {
			info.setMsg("请选择物资信息");
			info.setStatus(-1);
			return info;
		}
		//封装数据
		Map<String, Object> procurement = new HashMap<>();
		procurement.put("recCreator", recCreator);
		procurement.put("recCreateTime",DateUtils.curDateTimeStr19());
		/*
		*新增或删除
		* 通过id--判空
		*
		*
		* */
		if (StringUtils.isNotEmpty(info.getString("id"))) {
			String purchaseNo = info.getString("purchaseNo");
			/*
			* 采购计划单号自增
			*
			*
			*
			*
			* */
			Map<String,String> mk = new HashMap<>();
			mk.put("purchaseNo",purchaseNo);
			int  procurementSplitNo = 00;
			List<Map<String,Object>> ad = dao.query("MPCG01.mk",mk);
			List<String> sd = new ArrayList<>();
			for(Map<String,Object> fg :ad) {
				sd.add((String) fg.get("purchaseNo"));
			}
			if(sd.size() == 1){
				procurementSplitNo +=1;
			}else {
				procurementSplitNo=sd.size();
			}
			String kl = purchaseNo + "-"+procurementSplitNo;
			id = UUID.randomUUID().toString();
			Map<String, Object> map = saveProcurementPlanningDetail(rows, id);
			procurement.put("id",id);
			procurement.put("purchaseNo",kl);
			//01为新建状态
			procurement.put("statusCode", "01");
			procurement.put("statusName","新建");
			procurement.put("deptName",deptName);
			procurement.put("deptNum",deptNum);
			procurement.put("purchaseNum", map.get("purchaseNum"));
			procurement.put("purchaseCost", map.get("purchaseCost"));
			dao.insert("MPCG01.saveProcurementPlan",procurement);
		} else {}
		return info;
	}

	/**
	 *  保存科室月度申请物资明细
	 *  saveMatDeptMonthApplyDetail
	 * @param rows id
	 *             rows  集合
	 * @return
	 */
	public Map<String, Object> saveProcurementPlanningDetail(List<Map<String, Object>> rows, String id) {
		Map<String, Object> map = new HashMap<>();
		BigDecimal onepurchaseNum;
		BigDecimal onepurchaseCost;
		BigDecimal oneprice;
		BigDecimal purchaseNum = new BigDecimal(0);
		BigDecimal purchaseCost = new BigDecimal(0);
		int i = 0;
		Map<String, Object> procurementDetails = new HashMap<>();
		//遍历物资数据
		for (Map<String, Object> row : rows) {
			//采购数量
			onepurchaseNum = StringUtils.isNotEmpty((String) row.get("num")) ? new BigDecimal((String) row.get("num")) : new BigDecimal(0);;
			//单价
			oneprice = StringUtils.isNotEmpty((String) row.get("price")) ? new BigDecimal((String) row.get("price")) : new BigDecimal(0);;
			onepurchaseCost = onepurchaseNum.multiply(oneprice);
			procurementDetails.clear();
			//封装数据
			procurementDetails.put("id", UUID.randomUUID().toString());
			procurementDetails.put("purchaseId", id);
			procurementDetails.put("matTypeId", row.get("matTypeId"));
			procurementDetails.put("matTypeName", row.get("matTypeName"));
			procurementDetails.put("matNum", row.get("matNum"));
			procurementDetails.put("matName", row.get("matName"));
			procurementDetails.put("matModel", row.get("matModel"));
			procurementDetails.put("matSpec", row.get("matSpec"));
			procurementDetails.put("unit", row.get("unit"));
			procurementDetails.put("num",row.get("num"));
			procurementDetails.put("price", oneprice);
			procurementDetails.put("purchaseNum", onepurchaseNum);
			procurementDetails.put("purchaseCost", onepurchaseCost);
			dao.insert("MPCG01.saveProcurementPlanDetail", procurementDetails);
			i++;
			purchaseNum = purchaseNum.add(onepurchaseNum);
			purchaseCost = purchaseCost.add(onepurchaseCost);
		}
		//封装好数据返回
		map.put("purchaseNum", purchaseNum);
		map.put("purchaseCost", purchaseCost);
		return map;
	}



	public static EiInfo getPersonnelCode(Object param){
		EiInfo outinfo = null;
		if(param instanceof EiInfo){
			outinfo = MpUtils.invoke((EiInfo)param,"S_AC_FW_07");
		} else if (param instanceof Map) {
			EiInfo inInfo = new EiInfo();
			((Map) param).forEach((key,value) -> inInfo.set((String) key, value));
			outinfo = MpUtils.invoke(inInfo,"S_AC_FW_07");
		}
		return outinfo;
	}

}
