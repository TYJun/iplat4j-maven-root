package com.baosight.wilp.ci.db.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.ck.domain.CiOut;
import com.baosight.wilp.ci.ck.domain.CiOutDetail;
import com.baosight.wilp.ci.common.BeanExchangeUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.CiEnter;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.db.domain.CiTrans;
import com.baosight.wilp.ci.db.domain.CiTransDetail;

/**
 * 库存调拨子页面Service
 * 
 * <p>页面加载</p>
 * <p>调拨录入保存</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIDB0101.java
 * @ClassName:  ServiceCIDB0101
 * @Package com.baosight.wilp.ci.db.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月20日 下午1:30:32 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIDB0101 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		String type = inInfo.getString("type");
		if("see".equals(type)){
			String transBillNo = inInfo.getString("transBillNo");
			Map<String, String> pMap = new HashMap<>();
			pMap.put("transBillNo", transBillNo);
			List<CiTransDetail> list = dao.query("CIDB0101.query", pMap);
	        return CommonUtils.BuildOutEiInfo(inInfo, "result", new CiTransDetail().eiMetadata, list, list.size());
		}
		return inInfo;
    }
	
	/**
	 * 调拨录入保存
	 * 
	 * <p>1.根据页面参数构建调拨主表、调拨明细集合数据</br>
	 * 	  2.保存调拨主表、调拨明细表数据</br>
	 * 	  3.调用审核方法
	 * </p>
	 * 
	 * @Title: saveStockTrans
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		outWareHouseNo: 调出仓库号
	 * 		outWareHouseName:调出仓库名称
	 * 		inWareHouseNo:调入仓库号
	 * 		inWareHouseName:调入仓库名称
	 * 		rows:物资集合
	 * 			matNum : 物资编码
	 *			matName : 物资名称
	 *			matSpec : 物资规格
	 *			unitName : 单位
	 *			enterNum : 当前数量
	 *			transNum : 调拨数量 
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo saveStockTrans(EiInfo inInfo) {
		//构建调拨主单据对象
		CiTrans trans = BeanExchangeUtils.mapToTrans(inInfo.getAttr());
		//构建调拨明细实体集合
		List<CiTransDetail> transDetailList = new ArrayList<CiTransDetail>();
		List<Map<String, Object>> rows = inInfo.get("rows") == null ? null : (List<Map<String, Object>>) inInfo.get("rows");
		rows.forEach(map -> transDetailList.add(BeanExchangeUtils.mapToTransDetail(map, trans)));
		//调用本地服务保存调拨主单据
		CiUtils.invoke(null, "CIDB01", "insertTrans", new String[]{"trans"}, trans);
		//保存调拨明细实
		transDetailList.forEach(detail -> dao.insert("CIDB0101.insert", detail));
		//审核
		EiInfo info = new EiInfo();
		info.set("trans", trans);
		info.set("transDetailList", transDetailList);
		approveStockTrans(info);
		inInfo.setMsg("调拨成功");
		return inInfo;
	}
	
	/**
	 * 审核（暂时不做为单独功能）
	 * 
	 * <p>1.获取参数（调拨主表、调拨明细对象集合）</br>
	 *    2.根据调拨主表构建入库主表、出库主表数据</br>
	 *    3.根据调拨明细集合构建入库明细、出库明细集合</br>
	 *    4.调用本地服务出库</br>
	 *    5.调用本地服务入库</br>
	 *    6.调用本地服务更新调拨主单据
	 * </p>
	 * 
	 * @Title: approveStockTrans
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo approveStockTrans(EiInfo inInfo) {
		//获取参数
		CiTrans trans = inInfo.get("trans") == null ? null : (CiTrans)inInfo.get("trans");
		List<CiTransDetail> transDetailList = inInfo.get("transDetailList") == null ? null : (List<CiTransDetail>)inInfo.get("transDetailList");
		//构建入库主表对象
		CiEnter enter = BeanExchangeUtils.transToEnter(trans);
		//构建入库明细表对象集合
		List<CiEnterDetail> enterDetailList = new ArrayList<>();
		//构建出库主表对象
		CiOut out = BeanExchangeUtils.transToOut(trans);
		//构建出库明细对象集合
		List<CiOutDetail> outDetailList = new ArrayList<>();
		transDetailList.forEach(detail -> {
			enterDetailList.add(BeanExchangeUtils.transDetailToEnterDetail(detail,enter));
			outDetailList.add(BeanExchangeUtils.transDetailToOutDetail(detail,out));
		});
		//调用本地服务出库
		EiInfo info = new EiInfo();
		EiInfo outInfo = CiUtils.invoke(info, "CICK0101", "outStock", new String[]{"out","outDetailList"}, out, outDetailList);
		if(CiUtils.dealOutInfo(inInfo, outInfo, "出库失败")){
			return inInfo;
		}
		//调用本地服务入库
		outInfo = CiUtils.invoke(info, "CIRK0101", "enterStock", new String[]{"enter","enterDetailList"}, enter, enterDetailList);
		if(CiUtils.dealOutInfo(inInfo, outInfo, "入库失败")){
			return inInfo;
		}
		//调用本地服务更新调拨主单据
		outInfo = CiUtils.invoke(null, "CIDB01", "updateTrans", new String[]{"trans"}, trans);
		if(CiUtils.dealOutInfo(inInfo, outInfo, "调拨失败")){
			return inInfo;
		}
		return inInfo;
	}
	

}
