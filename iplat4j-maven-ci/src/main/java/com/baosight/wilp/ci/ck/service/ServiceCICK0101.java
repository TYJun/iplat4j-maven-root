package com.baosight.wilp.ci.ck.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.wilp.ci.ck.domain.OutTypeEnum;
import com.baosight.wilp.ci.ck.domain.CiOut;
import com.baosight.wilp.ci.ck.domain.CiOutDetail;
import com.baosight.wilp.ci.common.BeanExchangeUtils;
import com.baosight.wilp.ci.common.CiConstant;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.kc.domain.CiStorge;
import com.baosight.wilp.ci.kc.domain.CiStorgeDetail;
import com.baosight.wilp.ci.kc.domain.CiStorgeRecord;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 出库录入子页面Service
 * 
 * <p>页面加载</p>
 * <p>出库</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCICK0101.java
 * @ClassName:  ServiceCICK0101
 * @Package com.baosight.wilp.ci.ck.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月24日 下午2:08:39 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
@Aspect
@Component
public class ServiceCICK0101 extends ServiceBase {
	
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
		// 获取当前人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(null);
		System.out.println("userInfo"+userInfo);
		if(userInfo !=null){
			inInfo.set("inqu_status-0-userDeptNum", userInfo.get("deptNum"));
			inInfo.set("inqu_status-0-userDeptNum_textField", userInfo.get("deptName"));
		}
		return inInfo;
    }

	@Override
	public EiInfo query(EiInfo inInfo) {
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
		//参数处理
		CiOut out = new CiOut();
		List<CiOutDetail> outDetailList = new ArrayList<>();
		String outType = inInfo.getString("outType");
		if(StringUtils.isBlank(outType)){
			out = (CiOut) inInfo.get("out");
			outDetailList = (List<CiOutDetail>) inInfo.get("outDetailList");
		} else {
			out = BeanExchangeUtils.mapToOut(inInfo.getAttr());
			List<Map<String, String>> rows  = (List<Map<String, String>>) inInfo.get("rows");
			for (Map<String, String> pMap : rows) {
				outDetailList.add(BeanExchangeUtils.mapToOutDetail(pMap, out));
			}
		}
		//校验仓库是否正常（存在、冻结）
		EiInfo outInfo = CiUtils.invoke(null, "CIWH01", "isCheckWareHouse", new String[]{"wareHouseNo"}, out.getWareHouseNo());
		if("false".equals(outInfo.getString("isCheck"))){
			inInfo.setStatus(-1);
			inInfo.setMsg("仓库不存在或已被冻结");
			return inInfo;
		}
		//保存出库单据
		dao.insert("CICK01.insert", out);
		dao.insert("CICK0101.insert", outDetailList);
		//根据出库类型，执行对应的方法
		outInfo = CiUtils.invoke(null, "CICK0101", OutTypeEnum.getMethodNameByCode(out.getOutType()),
				new String[]{"out","outDetailList"}, out,outDetailList);
		//方法执行失败
		if(CiUtils.dealOutInfo(inInfo, outInfo, "出库失败")){
			return inInfo;
		}
		inInfo.setMsg("出库成功");
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
	 * 报废出库
	 *
	 * <p>不存在库存操作，所以无后续操作，由审核流程驱动</p>
	 *
	 * @Title: outStockByBF
	 * @Description:
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo outStockByBF (EiInfo inInfo) {return inInfo;}
	
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
		//获取参数
		CiOut out = (CiOut) inInfo.get("out");
		List<CiOutDetail> outDetailList = (List<CiOutDetail>) inInfo.get("outDetailList");
		List<CiStorgeRecord> recordDBList = new ArrayList<>();
		//遍历出库明细
		for (CiOutDetail outDetail : outDetailList) {
			//获取指定物资的库存
			EiInfo outInfo = CiUtils.invoke(null, "CIKC01", "getStockByMatNum", new String[]{"wareHouseNo","matNum"},
					out.getWareHouseNo(), outDetail.getMatNum());
			CiStorge storge = (CiStorge) outInfo.get("storge");
			//判断库存是否足够出库
			if(storge.getTotalNum() < outDetail.getOutNum()){
				inInfo.setStatus(-1);
				inInfo.setMsg("库存不足 无法出库");
				return inInfo;
			}
			//足够出库,获取库存物资的批次 
			outInfo = CiUtils.invoke(null, "CIKC0101", "queryStorgeDetail", new String[]{"wareHouseNo","matNum"},
					out.getWareHouseNo(), outDetail.getMatNum());
			List<CiStorgeDetail> storgeDetailList = (List<CiStorgeDetail>) outInfo.get("list");
			//构建库存操作履历对象集合
			List<CiStorgeRecord> storgeRecordList = new ArrayList<>();
			//扣库存，得到扣取了库存的库存明细
			List<CiStorgeDetail> newStorgeDetailList = reduceStorge(outDetail, storgeDetailList, storgeRecordList);
			//更新库存
			Double totalAmount = calTotalAmount(newStorgeDetailList);
			storge.setTotalNum(outDetail.getOutNum());
			storge.setTotalAmount(totalAmount);
			CiUtils.invoke(null,"CIKC01","updateStorgeByReduce",new String[]{"storge","storgeDetailList"},
					storge, newStorgeDetailList);
			//更新出库单价和总价、
			outDetail.setOutAmount(totalAmount);
			outDetail.setOutPrice(CiUtils.cal(totalAmount, outDetail.getOutNum(), "divide"));
			dao.update("CICK0101.update", outDetail);
			//调用本地服务插入库存履历表
			CiUtils.invoke(null,"CIKC02","insert",new String[]{"recordList"},storgeRecordList);
			//调拨出库、将库存操作履历存入缓存，在调拨入库时使用
			if(CiConstant.OUT_TYPE_DB.equals(out.getOutType())){
				recordDBList.addAll(storgeRecordList);
			}
		}
		if(CiConstant.OUT_TYPE_DB.equals(out.getOutType())){
			UserSession.setOutSessionProperty(out.getOriginBillNo(), recordDBList);
		}
		inInfo.setStatus(0);
		return inInfo;
	}
	

	/**
	 * 扣除库存
	 * 先出库即将过期商品，再按批次出库
	 * @Title  reduceStorge2
	 * @author liu
	 * @date 2022-06-14 16:26
	 * @param outDetail 出库明细
	 * @param storgeDetailList 库存明细
	 * @param storgeRecordList 库存操作履历
	 * @return java.util.List<com.baosight.wilp.ci.kc.domain.CiStorgeDetail>
	 */
	private List<CiStorgeDetail> reduceStorge(CiOutDetail outDetail, List<CiStorgeDetail> storgeDetailList, List<CiStorgeRecord> storgeRecordList) {

		/**
		 * 1.先按照过期时间排序, 再按照批次排序
		 **/
		storgeDetailList.sort(Comparator.comparing(CiStorgeDetail::getExpirationDate).thenComparing(CiStorgeDetail::getBatchNo));

		List<CiStorgeDetail> list = new ArrayList<>();
		//剩余出库数量（初始为出库数量）
		Double remainNum = outDetail.getOutNum();
		//遍历
		for (CiStorgeDetail detail : storgeDetailList) {
			//库存明细(批次)的库存数量大于剩余出库数量，出库数量为剩余出库数量
			if (detail.getEnterNum() >= remainNum) {
				//构建库存操作履历对象
				storgeRecordList.add(BeanExchangeUtils.storgeDetailToStorgeRecord(detail, detail.getEnterNum(),
						detail.getEnterNum() - remainNum, outDetail));
				//计算库存、及出库总价
				detail.setEnterNum(remainNum);
				detail.setEnterAmount(CiUtils.cal(remainNum, detail.getEnterPrice(), "multiply"));
				list.add(detail);
				return list;
			} else {//库存明细(批次)的库存数量小于剩余出库数量，出库数量为库存数量
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
		CiOut out = (CiOut) inInfo.get("out");
		List<CiOutDetail> outDetailList = (List<CiOutDetail>) inInfo.get("outDetailList");
		//新的库存操作履历
		List<CiStorgeRecord> newRecordList = new ArrayList<>();
		//遍历红冲明细
		for (CiOutDetail outDetail : outDetailList) {
			//获取红冲出库对应来源出库单的库存操作履历
			EiInfo outInfo = CiUtils.invoke(null, "CIKC02", "queryStorgeRecord", new String[]{"wareHouseNo","originBillNo","matNum"},
					out.getWareHouseNo(), out.getOriginBillNo(), outDetail.getMatNum());
			List<CiStorgeRecord> recordList = (List<CiStorgeRecord>) outInfo.get("list");
			//库存红冲还原
			List<CiStorgeDetail> newStorgeDetailList = addStorge(outDetail, recordList, newRecordList);
			//更新库存
			Double totalAmount = calTotalAmount(newStorgeDetailList);
			CiStorge storge = BeanExchangeUtils.storgeDetailToStorge(newStorgeDetailList.get(0),totalAmount,outDetail.getOutNum());
			CiUtils.invoke(null,"CIKC01","updateStorgeByAdd",new String[]{"storge", "storgeDetailList"},
					storge, newStorgeDetailList);
		}
		//调用本地服务插入库存履历表
		CiUtils.invoke(null,"CIKC02","insert",new String[]{"recordList"}, newRecordList);
		inInfo.setMsg("红冲出库成功");
		return inInfo;
	}

	/**
	 * 红冲出库还原库存
	 * 
	 * <p>根据出库操作履历，对原先出库的库存批次的数量进行回加<p>
	 * 
	 * @Title: addStorge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param outDetail ： 出库明细
	 * @param:  @param recordList ： 出库操作履历集合
	 * @param:  @param newRecordList 
	 * @param:  @return
	 * @return: List<CiStorgeDetail>  ： 库存明细集合
	 * @throws
	 */
	private List<CiStorgeDetail> addStorge(CiOutDetail outDetail, List<CiStorgeRecord> recordList, List<CiStorgeRecord> newRecordList) {
		List<CiStorgeDetail> list = new ArrayList<>();
		Double remainNum = outDetail.getOutNum();//剩余红冲出库数量（初始为红冲数量）
		//遍历库存操作履历记录
		recordList.sort((c1, c2) -> c1.getBatchNo().compareTo(c2.getBatchNo()));//排序
		for (int i = 0; i < recordList.size(); i++) {
			CiStorgeRecord record = recordList.get(i);
			CiStorgeRecord newRecord = SerializationUtils.clone(record);//克隆库存操作履历
			Double beforeNum = record.getBeforeNum();//出库前数量
			Double afterNum = record.getAfterNum();//出库后数量
			Double redRushNum = beforeNum-afterNum;//可红冲数量
			if(redRushNum >= remainNum){//可红冲数量大于剩余红冲出库数量，回加数量为剩余红冲出库数量
				CiStorgeDetail detail = BeanExchangeUtils.storgeRecordToStorgeDetail(record, remainNum);
				list.add(detail);
				newRecord.setAfterNum(afterNum + remainNum);
			} else {//可红冲数量小于剩余红冲出库数量，回加数量为可红冲数量
				remainNum = remainNum - redRushNum;
				CiStorgeDetail detail = BeanExchangeUtils.storgeRecordToStorgeDetail(record,redRushNum);
				list.add(detail);
				newRecord.setAfterNum(afterNum + redRushNum);
			}
			//属性赋值
			newRecord.setId(UUID.randomUUID().toString());
			newRecord.setOriginBillNo(outDetail.getOutBillNo());
			newRecord.setOriginBillType(outDetail.getOutType());
			newRecord.setOriginBillTypeName(outDetail.getOutTypeName());
			newRecord.setBeforeNum(afterNum);
			newRecord.setBeforeAmount(CiUtils.cal(afterNum, record.getPrice(), "multiply"));
			newRecord.setAfterAmount(CiUtils.cal(newRecord.getAfterNum(), record.getPrice(), "multiply"));
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
	private Double calTotalAmount(List<CiStorgeDetail> newStorgeDetailList) {
		Double totalAmount = 0d;
		for (CiStorgeDetail detail : newStorgeDetailList) {
			totalAmount = totalAmount + detail.getEnterAmount();
		}
		return totalAmount;
	}

}
