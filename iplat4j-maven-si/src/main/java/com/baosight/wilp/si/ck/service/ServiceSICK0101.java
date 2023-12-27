package com.baosight.wilp.si.ck.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.si.ck.domain.OutTypeEnum;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.*;
import com.baosight.wilp.si.kc.domain.SiStorge;
import com.baosight.wilp.si.kc.domain.SiStorgeDetail;
import com.baosight.wilp.si.kc.domain.SiStorgeRecord;
import com.baosight.wilp.si.rk.domain.SiEnter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 出库录入子页面Service
 *
 * <p>页面加载</p>
 * <p>出库</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSICK0101.java
 * @ClassName:  ServiceSICK0101
 * @Package com.baosight.wilp.si.ck.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月24日 下午2:08:39
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSICK0101 extends ServiceBase {

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
		return inInfo;
	}

	/**
	 * 出库
	 *
	 * <p>1.获取参数，判断是否存在出库类型参数。存在，则为领用出库、红冲出库；不存在则为调拨出库、盘亏出库、直入直出</br>
	 *    2.领用出库、红冲出库时，根据页面参数构建入库主单据、入库明细集合</br>
	 *    3.校验仓库是否正常（存在、冻结）。仓库不正常时，给出错误提示"仓库不存在或已被冻结"</br>
	 *    4.仓库正常时,保存出库主单据、出库明细</br>
	 *    5.根据出库类型执行不同的出库方法
	 * <p>
	 *
	 * @Title: outStock
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo outStock(EiInfo inInfo) {
		/**1.参数处理**/
		SiOut out = new SiOut();
		List<SiOutDetail> outDetailList = new ArrayList<>();
		if(StringUtils.isBlank(inInfo.getString("outType"))){
			out = (SiOut) inInfo.get("out");
			outDetailList = (List<SiOutDetail>) inInfo.get("outDetailList");
		} else {
			out = BeanExchangeUtils.mapToOut(inInfo.getAttr());
			List<Map<String, String>> rows  = (List<Map<String, String>>) inInfo.get("rows");
			for (int i = 0; i < rows.size(); i++) {
				Map<String, String> pMap  = rows.get(i);
				SiOutDetail detail = BeanExchangeUtils.mapToOutDetail(pMap, out);
				detail.setSortNo(i);
				outDetailList.add(detail);
			}
		}
		/**3.校验仓库是否正常（存在、冻结）。仓库不正常时，给出错误提示"仓库不存在或已被冻结"**/
		EiInfo outInfo = SiUtils.invoke(null, "SIWH01", "isCheckWareHouse", new String[]{"wareHouseNo"}, out.getWareHouseNo());
		if(!SiUtils.toBoolean(outInfo.getString("isCheck"))){
			return ValidatorUtils.errorInfo("仓库不存在或已被冻结");
		}
		/**4.仓库正常时,保存出库主单据、出库明细**/
		dao.insert("SICK01.insert", out);
		dao.insert("SICK0101.insert", outDetailList);

		/**5.判断出库是否需要仓库确认(签字), 不需要，直接出库**/
		String hasCheck = SiConfigCache.getConfigRadioValue(out.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_STORAGE_MANAGER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			/**6.根据出库类型执行不同的出库方法**/
			outInfo = SiUtils.invoke(null, "SICK0101", OutTypeEnum.getMethodNameByCode(out.getOutType()),
					new String[]{"out","outDetailList"}, out,outDetailList);
			/**7.合计出库金额**/
			SiUtils.invoke(null, "SICK01", "updateTotalAmount", new String[]{"outBillNo"}, out.getOutBillNo());
			//方法执行失败
			if(SiUtils.dealOutInfo(inInfo, outInfo, "出库失败")){
				return inInfo;
			}
			inInfo.setMsg("出库成功");
		}
		inInfo.set("outBillNo", out.getOutBillNo());
		return inInfo;
	}

	/**
	 * 出库操作库存
	 * @Title: outStorage
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo outStorage(EiInfo inInfo) {
		List<String> outBillNos = SiUtils.toList(inInfo.get("outBillNos"), String.class);
		//遍历，出库
		EiInfo outInfo = null;
		for (String outBillNo : outBillNos) {
			//获取出库单
			Map<String, String> pMap = new HashMap(2){{put("outBillNoEQ", outBillNo);}};
			List<SiOut> list = dao.query("SICK01.query", pMap);
			if(CollectionUtils.isEmpty(list)) {continue;}
			SiOut out = list.get(0);
			//获取出库明细
			pMap.put("outBillNo", outBillNo);
			List<SiOutDetail> outDetailList = dao.query("SICK0101.query", pMap);
			/**6.根据出库类型执行不同的出库方法**/
			outInfo = SiUtils.invoke(null, "SICK0101", OutTypeEnum.getMethodNameByCode(out.getOutType()),
					new String[]{"out","outDetailList"}, out,outDetailList);
			/**7.合计出库金额**/
			SiUtils.invoke(null, "SICK01", "updateTotalAmount", new String[]{"outBillNo"}, out.getOutBillNo());
			//方法执行失败
			if(SiUtils.dealOutInfo(inInfo, outInfo, "出库失败")){
				return inInfo;
			}
			inInfo.setMsg("出库成功");
		}
		return inInfo;
	}

	/**
	 * 直入直出
	 *
	 * <p>不存在库存操作，所以无后续操作</p>
	 *
	 * @Title: outStockByZRZC
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo outStockByZRZC (EiInfo inInfo) {return inInfo;}

	/**
	 * 通用出库（调拨、盘库、领用）
	 *
	 * <p>1.获取参数，遍历出库明细集合</br>
	 *    2.根据入库明细构建库存主单、库存明细、库存操作履历</br>
	 *    3.获取库存中指定物资信息。判断库存中的物资数量是否足够出库。不足够出库，提示错误信息:"库存不足 无法出库"</br>
	 *    4.足够出库,获取库存物资的批次（库存明细） </br>
	 *    5.调用扣除库存方法，得到扣除了库存的库存明细、出库库存操作履历</br>
	 *    6.更新库存主表（更新总库存量、库存总价）</br>
	 *    7.更新出库主单据（更新出库单价和总价）</br>
	 *    8.调用本地服务插入库存履历表</br>
	 *    9.判断出库类型是否为调拨出库。是，将出库库存操作履历保存到缓存
	 * </p>
	 *
	 * @Title: outStockByCommon
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo outStockByCommon (EiInfo inInfo) {
		/**1.获取参数，遍历出库明细集合**/
		SiOut out = (SiOut) inInfo.get("out");
		List<SiOutDetail> outDetailList = (List<SiOutDetail>) inInfo.get("outDetailList");
		List<SiStorgeRecord> recordDBList = new ArrayList<>();
		//遍历出库明细
		for (SiOutDetail outDetail : outDetailList) {
			//获取指定物资的库存
			EiInfo outInfo = SiUtils.invoke(null, "SIKC01", "getStockByMatNum", new String[]{"wareHouseNo","matNum"},
					out.getWareHouseNo(), outDetail.getMatNum());
			SiStorge storge = (SiStorge) outInfo.get("storge");
			//判断库存是否足够出库
			if(storge == null || storge.getTotalNum() < outDetail.getOutNum()){
				inInfo.setStatus(-1);
				inInfo.setMsg("库存不足 无法出库");
				return inInfo;
			}
			//足够出库,获取库存物资的批次
			outInfo = SiUtils.invoke(null, "SIKC0101", "queryStorgeDetail", new String[]{"wareHouseNo","matNum"},
					out.getWareHouseNo(), outDetail.getMatNum());
			List<SiStorgeDetail> storgeDetailList = (List<SiStorgeDetail>) outInfo.get("list");
			//构建库存操作履历对象集合(每个库存批次对应一条库存履历)
			List<SiStorgeRecord> storgeRecordList = new ArrayList<>();
			//扣库存，获取需要扣取库存的库存明细(剩余库存明细 = 原库存明细-此库存明细)
			List<SiStorgeDetail> newStorgeDetailList = reduceStorge(outDetail, storgeDetailList, storgeRecordList);
			//更新库存总量及总价
			Double totalAmount = calTotalAmount(newStorgeDetailList);
			storge.setTotalNum(outDetail.getOutNum());
			storge.setTotalAmount(totalAmount);
			SiUtils.invoke(null,"SIKC01","updateStorgeByReduce",new String[]{"storge","storgeDetailList"},
					storge, newStorgeDetailList);
			//更新出库单价和总价、
			outDetail.setOutAmount(totalAmount);
			outDetail.setOutPrice(SiUtils.cal(totalAmount, outDetail.getOutNum(), "divide"));
			dao.update("SICK0101.update", outDetail);
			//调用本地服务插入库存履历表
			SiUtils.invoke(null,"SIKC02","insert",new String[]{"recordList"},storgeRecordList);
			//调拨出库、将库存操作履历存入缓存，在调拨入库时使用
			if(SiConstant.OUT_TYPE_DB.equals(out.getOutType())){
				recordDBList.addAll(storgeRecordList);
			}
		}
		if(SiConstant.OUT_TYPE_DB.equals(out.getOutType())){
			UserSession.setOutSessionProperty(out.getOriginBillNo(), recordDBList);
		}
		return inInfo;
	}

	/**
	 * 扣除库存
	 * <p>按先进先出扣除库存，最先扣除最早的批次，最早批次不够时，依次扣除后面的批次</p>
	 *
	 * @Title: reduceStorge
	 * @param outDetail outDetail : 出库明细对象
	 * @param storgeDetailList storgeDetailList : 库存明细对象集合
	 * @param storgeRecordList storgeRecordList : 库存操作履历对象集合
	 * @return java.util.List<com.baosight.wilp.si.kc.domain.SiStorgeDetail>
	 * @throws
	 **/
	private List<SiStorgeDetail> reduceStorge(SiOutDetail outDetail, List<SiStorgeDetail> storgeDetailList, List<SiStorgeRecord> storgeRecordList) {
		List<SiStorgeDetail> list = new ArrayList<>();
		//剩余出库数量（初始为出库数量）
		Double remainNum = outDetail.getOutNum();
		//遍历，由于数据是倒序的，所以倒序遍历
		for (int i = storgeDetailList.size()-1; i >= 0; i--) {
			SiStorgeDetail detail = storgeDetailList.get(i);
			//库存明细(批次)的库存数量大于剩余出库数量，出库数量为剩余出库数量
			if(detail.getEnterNum() >= remainNum){
				//构建库存操作履历对象
				storgeRecordList.add(BeanExchangeUtils.storgeDetailToStorgeRecord(detail, detail.getEnterNum(),
						SiUtils.cal(detail.getEnterNum(),remainNum, SiConstant.MATH_SUB), outDetail));
				//库存数量大于出库数量时,出库金额=出库数量*单价; 库存数量等于出库数量时,出库金额 = 库存金额
				if(detail.getEnterNum() - remainNum > 0) {
					detail.setEnterAmount(SiUtils.cal(remainNum, detail.getEnterPrice(), "multiply"));
				}
				//设置需要扣减库存数量及总价为出库数量及出库总价
				detail.setEnterNum(remainNum);
				list.add(detail);
				return list;
			} else {
				//库存明细(批次)的库存数量小于剩余出库数量，出库数量为库存数量
				remainNum = remainNum - detail.getEnterNum();
				list.add(detail);
				//构建库存操作履历对象
				storgeRecordList.add(BeanExchangeUtils.storgeDetailToStorgeRecord(detail, detail.getEnterNum(), 0d, outDetail));
			}
		}
		return list;
	}

	/**
	 * 红冲出库
	 *
	 * <p>1.获取参数，获取出库主单据、出库明细集合</br>
	 *    2.遍历出库明细集合</br>
	 *    3.获取红冲出库对应来源出库单的库存操作履历</br>
	 *    4.调用库存红冲还原方法，得到还原的库存记录</br>
	 *    5.更新库存</br>
	 *    6.保存库存操作履历
	 * </p>
	 *
	 * @Title: outStockByHC
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo outStockByHC (EiInfo inInfo) {
		//获取参数
		SiOut out = (SiOut) inInfo.get("out");
		List<SiOutDetail> outDetailList = (List<SiOutDetail>) inInfo.get("outDetailList");
		if(StringUtils.isBlank(out.getOriginBillNo())) {
			hcByNoOrigin(out, outDetailList);
		} else {
			//红冲校验,判断是否可以红冲
			if(validateHC(out, outDetailList)) {
				hcByOrigin(out, outDetailList);
			} else {
				return ValidatorUtils.errorInfo("红冲数量不能超过原先出库数量");
			}
		}
		inInfo.setMsg("红冲出库成功");
		return inInfo;
	}

	/**
	 * 校验是否可以红冲
	 * @Title: validateHC
	 * @param out out
	 * @param outDetailList outDetailList
	 * @return boolean
	 * @throws
	 **/
	private boolean validateHC(SiOut out, List<SiOutDetail> outDetailList) {
		//获取退库对应的原先出库明细
		List<SiOutDetail> query = dao.query("SICK0101.queryHcDetail", out);
		if(CollectionUtils.isEmpty(query)) { return true;}
		//判断退库数量是否超过出库数量
		for (SiOutDetail detail : outDetailList) {
			SiOutDetail de = query.stream().filter(d -> d.getMatNum().equals(detail.getMatNum())).findFirst().orElse(null);
			if(de == null) { return false; }
			//获取剩余可红冲量
			Double remindRedRushNum = SiUtils.cal(de.getOutNum(), de.getTotalRedRushNum(), SiConstant.MATH_SUB);
			//判断红冲出库数量是否大于剩余可红冲量
			if(SiUtils.cal(detail.getOutNum(), remindRedRushNum, SiConstant.MATH_SUB) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 红冲出库: 根据出库单红冲
	 * @Title: hcByOrigin
	 * @param out out
	 * @param outDetailList outDetailList
	 * @return void
	 * @throws
	 **/
	private void hcByOrigin(SiOut out, List<SiOutDetail> outDetailList) {
		//新的库存操作履历
		List<SiStorgeRecord> newRecordList = new ArrayList<>();
		//遍历红冲明细
		for (SiOutDetail outDetail : outDetailList) {
			//获取红冲出库对应来源出库单的库存操作履历
			EiInfo outInfo = SiUtils.invoke(null, "SIKC02", "queryStorgeRecord", new String[]{"wareHouseNo","originBillNo","matNum"},
					out.getWareHouseNo(), out.getOriginBillNo(), outDetail.getMatNum());
			List<SiStorgeRecord> recordList = (List<SiStorgeRecord>) outInfo.get("list");
			//库存红冲还原(剩余库存明细 = 原库存明细-此库存明细)
			List<SiStorgeDetail> newStorgeDetailList = addStorge(outDetail, recordList, newRecordList);
			//更新库存总量和总价
			Double totalAmount = calTotalAmount(newStorgeDetailList);
			SiStorge storge = BeanExchangeUtils.storgeDetailToStorge(newStorgeDetailList.get(0),totalAmount,outDetail.getOutNum());
			SiUtils.invoke(null,"SIKC01","updateStorgeByAdd",new String[]{"storge", "storgeDetailList"},
					storge, newStorgeDetailList);
			//更新红冲出库的单价和总价
			outDetail.setOutAmount(totalAmount);
			outDetail.setOutPrice(SiUtils.cal(totalAmount, outDetail.getOutNum(), "divide"));
			dao.update("SICK0101.update", outDetail);
		}
		//调用本地服务插入库存履历表
		SiUtils.invoke(null,"SIKC02","insert",new String[]{"recordList"}, newRecordList);
	}

	/**
	 * 红冲出库还原库存
	 * <p>根据出库操作履历，对原先出库的库存批次的数量进行回加<p>
	 *
	 * @Title: addStorge
	 * @param:  @param outDetail ： 出库明细
	 * @param:  @param recordList ： 领用出库库存操作履历集合
	 * @param:  @param newRecordList : 红冲出库库存操作履历
	 * @param:  @return
	 * @return: List<SiStorgeDetail>  ： 库存明细集合
	 * @throws
	 */
	private List<SiStorgeDetail> addStorge(SiOutDetail outDetail, List<SiStorgeRecord> recordList, List<SiStorgeRecord> newRecordList) {
		List<SiStorgeDetail> list = new ArrayList<>();
		//剩余红冲出库数量（初始为红冲数量）
		Double remainNum = outDetail.getOutNum();
		//按批次号排序
		recordList.sort((c1, c2) -> c1.getBatchNo().compareTo(c2.getBatchNo()));
		//遍历领用出库库存操作履历
		for (int i = 0; i < recordList.size(); i++) {
			SiStorgeRecord record = recordList.get(i);
			//克隆领用出库库存操作履历
			SiStorgeRecord newRecord = SerializationUtils.clone(record);
			//领用出库前数量
			Double beforeNum = record.getBeforeNum();
			//领用出库后数量
			Double afterNum = record.getAfterNum();
			//可红冲数量
			Double redRushNum = beforeNum-afterNum;
			//可红冲数量大于剩余红冲出库数量，回加数量为剩余红冲出库数量
			if(redRushNum >= remainNum){
				SiStorgeDetail detail = BeanExchangeUtils.storgeRecordToStorgeDetail(record, remainNum);
				list.add(detail);
				newRecord.setAfterNum(afterNum + remainNum);
			} else {
				//可红冲数量小于剩余红冲出库数量，回加数量为可红冲数量
				remainNum = remainNum - redRushNum;
				SiStorgeDetail detail = BeanExchangeUtils.storgeRecordToStorgeDetail(record,redRushNum);
				list.add(detail);
				newRecord.setAfterNum(afterNum + redRushNum);
			}
			//属性赋值
			newRecord.setId(UUID.randomUUID().toString());
			newRecord.setOriginBillNo(outDetail.getOutBillNo());
			newRecord.setOriginBillType(outDetail.getOutType());
			newRecord.setOriginBillTypeName(outDetail.getOutTypeName());
			newRecord.setBeforeNum(afterNum);
			newRecord.setBeforeAmount(SiUtils.cal(afterNum, record.getPrice(), "multiply"));
			newRecord.setAfterAmount(SiUtils.cal(newRecord.getAfterNum(), record.getPrice(), "multiply"));
			newRecordList.add(newRecord);
		}
		return list;
	}

	/**
	 * 计算总价
	 * @Title: calTotalAmount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param newStorgeDetailList : 库存明细集合
	 * @param:  @return
	 * @return: Double  ： 总价
	 * @throws
	 */
	private Double calTotalAmount(List<SiStorgeDetail> newStorgeDetailList) {
		Double totalAmount = 0d;
		for (SiStorgeDetail detail : newStorgeDetailList) {
			totalAmount = totalAmount + detail.getEnterAmount();
		}
		return totalAmount;
	}

	/**
	 * 红冲出库: 手工录入红冲数据
	 * @Title: hcByNoOrigin
	 * @param out out
	 * @param outDetailList outDetailList
	 * @return void
	 * @throws
	 **/
	private void hcByNoOrigin(SiOut out, List<SiOutDetail> outDetailList) {
		/**
		 * 1.根据红冲出库明细构建库存明细 ??  成本计算问题  怎样确定单价？ 确定最后的退库金额？
		 *
		 **/

	}


}
