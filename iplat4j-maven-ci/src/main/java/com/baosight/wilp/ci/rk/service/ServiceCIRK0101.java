package com.baosight.wilp.ci.rk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.wilp.ci.common.CiConstant;
import com.baosight.wilp.ci.kc.domain.CiStorge;
import com.baosight.wilp.ci.kc.domain.CiStorgeDetail;
import com.baosight.wilp.ci.kc.domain.CiStorgeRecord;
import com.baosight.wilp.common.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ci.ck.domain.CiOut;
import com.baosight.wilp.ci.ck.domain.CiOutDetail;
import com.baosight.wilp.ci.common.BeanExchangeUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.ci.rk.domain.EnterTypeEnum;
import com.baosight.wilp.ci.rk.domain.CiEnter;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 直入直出子页面Service
 * 
 * <p>页面加载</p>
 * <p>入库</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIRK0101.java
 * @ClassName:  ServiceCIRK0101
 * @Package com.baosight.wilp.ci.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午4:44:05 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIRK0101 extends ServiceBase {

	private final static Logger logger = LoggerFactory.getLogger(ServiceCIRK0101.class);
	
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
		return inInfo;
    }
	
	/**
	 * 入库
	 * 
	 * <p>1.获取参数，判断是否存在入库类型参数。存在，则为直入直出、采购入库、红冲入库；不存在则为调拨入库、盘盈入库</br>
	 *    2.直入直出、采购入库、红冲入库时，根据页面参数构建入库主单据、入库明细集合</br>
	 *    3.校验仓库是否正常（存在、冻结）。仓库不正常时，给出错误提示"仓库不存在或已被冻结"</br>
	 *    4.仓库正常时,保存入库主单据、入库明细</br>
	 *    5.根据入库类型执行不同的入库方法
	 * <p>
	 * 
	 * @Title: enterStock
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		wareHouseNo：仓库号
	 * 		wareHouseName：仓库名称
	 * 		userDeptNum： 领用科室编码
	 * 		userDeptName：领用科室名称
	 * 		enterBillNo：入库单号
	 * 		enterType：入库类型
	 * 		rows：物资集合
	 * 		enter：入库主单据
	 * 		enterDetailList：入库明细集合
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo enterStock(EiInfo inInfo) {
		//获取参数
		CiEnter enter = new CiEnter();
		List<CiEnterDetail> enterDetailList = new ArrayList<>();
		//判断是否存在入库类型参数。存在，则为直入直出、采购入库、红冲入库；不存在则为调拨入库、盘盈入库
		String enterType = inInfo.getString("enterType");
		logger.info("入库类型enterType:{}",enterType);
		if(StringUtils.isBlank(enterType)){
			enter = (CiEnter) inInfo.get("enter");
			enterDetailList = (List<CiEnterDetail>) inInfo.get("enterDetailList");
		} else {
			enter = BeanExchangeUtils.mapToEnter(inInfo.getAttr());
			List<Map<String, String>> rows  = (List<Map<String, String>>) inInfo.get("rows");
			for (Map<String, String> pMap : rows) {
				enterDetailList.add(BeanExchangeUtils.mapToEnterDetail(pMap, enter));
			}
		}
		
		//校验仓库是否正常（存在、冻结）
		EiInfo outInfo = CiUtils.invoke(null, "CIWH01", "isCheckWareHouse", new String[]{"wareHouseNo"}, enter.getWareHouseNo());
		if("false".equals(outInfo.getString("isCheck"))){
			inInfo.setStatus(-1);
			inInfo.setMsg("仓库不存在或已被冻结");
			return inInfo;
		}
		//保存入库单据
		dao.insert("CIRK01.insert", enter);
		dao.insert("CIRK0101.insert", enterDetailList);
		//根据入库类型，执行对应的方法
		outInfo = CiUtils.invoke(null, "CIRK0101", EnterTypeEnum.getMethodNameByCode(enter.getEnterType()),
				new String[]{"enter","enterDetailList"},
				enter,enterDetailList);
		//方法执行失败
		if(CiUtils.dealOutInfo(inInfo, outInfo, "入库失败")){
			return inInfo;
		}
		inInfo.setMsg("入库成功");
		return inInfo;
	}
	
	/**
	 * 直入直出
	 * 
	 * <p>根据入库信息构建出库信息，调用出库方法进行出库</p>
	 * 
	 * @Title: enterStockByZRZC
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		enter：入库主单据
	 * 		enterDetailList：入库明细集合
	 * 		userDeptNum： 领用科室编码
	 * 		userDeptName：领用科室名称
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo enterStockByZRZC(EiInfo inInfo) {
		//构建出库主单据
		CiEnter enter = (CiEnter) inInfo.get("enter");
		List<CiEnterDetail> enterDetailList = (List<CiEnterDetail>) inInfo.get("enterDetailList");

		CiOut out = BeanExchangeUtils.enterToOut(enter);
		//将入库明细转换成出库明细
		List<CiOutDetail> outDetailList = new ArrayList<CiOutDetail>();
		enterDetailList.forEach(enterDetail -> outDetailList.add(BeanExchangeUtils.enterDetailToOutDetail(enterDetail,out)));
		//调用本地服务出库
		EiInfo outInfo = CiUtils.invoke(null, "CICK0101", "outStock", new String[]{"out","outDetailList"}, out, outDetailList);
		if(CiUtils.dealOutInfo(inInfo, outInfo, "出库失败")){
			return inInfo;
		}
		inInfo.setMsg("直入直出操作成功");
		return inInfo;
	}
	
	/**
	 * 通用入库（盘盈入库、采购入库）
	 * 
	 * <p>1.获取参数，遍历入库明细集合</br>
	 *    2.根据入库明细构建库存主单、库存明细、库存操作履历</br>
	 *    3.判断库存中是否存在入库 的物资。存在更新库存中物资数量和总价格</br>
	 *    4.不存在，向库存中插入一条记录</br>
	 *    5.调用本地服插入库存明细表</br>
	 *    6.调用本地服务插入库存履历表
	 * </p>
	 * 
	 * @Title: enterStockByCommon
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		enter：入库主单据
	 * 		enterDetailList：入库明细集合
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo enterStockByCommon(EiInfo inInfo) {
		//获取参数
		CiEnter enter = (CiEnter) inInfo.get("enter");
		List<CiEnterDetail> enterDetailList = (List<CiEnterDetail>) inInfo.get("enterDetailList");
		//遍历库存明细
		List<CiStorgeDetail> storgeDetailList = new ArrayList<>();//库存明细List
		List<CiStorgeRecord> storgeRecordList = new ArrayList<>();//库存履历List
		enterDetailList.forEach(enterDetail -> {
			//构建库存主表对象
			CiStorge storge = BeanExchangeUtils.enterDetailToStorge(enter,enterDetail);
			//构建库存明细表对象
			CiStorgeDetail storgeDetail = BeanExchangeUtils.enterDetailToStorgeDetail(enterDetail,storge);
			storgeDetailList.add(storgeDetail);
			//构建库存履历对象
			CiStorgeRecord record = BeanExchangeUtils.storgeDetailToStorgeRecord(storgeDetail, 0d, storgeDetail.getEnterNum());
			storgeRecordList.add(record);
			//判断库存中是否存在该物资
			EiInfo outInfo = CiUtils.invoke(null, "CIKC01", "isExistMat", new String[]{"wareHouseNo","matNum"},
					enter.getWareHouseNo(),enterDetail.getMatNum());
			if("true".equals(outInfo.getString("isExistMat"))){//存在，更新库存主表
				//调用本地服务更新库存主表
				CiUtils.invoke(null,"CIKC01","updateStorge",new String[]{"storge"},storge);
			} else {//新增，插入库存主表
				//调用本地服务插入存主表
				CiUtils.invoke(null,"CIKC01","insertStorge",new String[]{"storge"},storge);
			}
		});
		//调用本地服插入库存明细表
		CiUtils.invoke(null,"CIKC0101","insertStorgeDetail",new String[]{"storgeDetailList"},storgeDetailList);
		//调用本地服务插入库存履历表
		CiUtils.invoke(null,"CIKC02","insert",new String[]{"recordList"},storgeRecordList);
		inInfo.setMsg("入库成功");
		return inInfo;
	}
	
	/**
	 * 调拨入库
	 * 
	 * <p>1.从缓存中获取对应调拨出库的库存操作履历，清空缓存（由于调拨出入库在同一事务，事务未提交，无法从数据库查询调拨出库库存操作履历，
	 * 		所以采取在调拨出库时，将库存操作履历存入缓存）</br>
	 * 	  2.根据库存操作履历记录构建新的库存主表对象、库存明细对象集合（跟出库保持一致）、入库库存操作履历对象集合</br>
	 * 	  3.遍历库存主表对象集合，判断库存中是否存在该物资</br>
	 * 	  4.存在，更新库存主表</br>
	 * 	  5.不存在，插入库存主表</br>
	 * 	  6.调用本地服插入库存明细表</br>
	 *    7.调用本地服务插入库存履历表
	 * </p>
	 * 
	 * @Title: enterStockByDB
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		enter：入库主单据
	 * 		enterDetailList：入库明细集合
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo enterStockByDB(EiInfo inInfo) {
		CiEnter enter = (CiEnter) inInfo.get("enter");
		//获取对应调拨出库的库存操作履历
		List<CiStorgeRecord> recordList = (List<CiStorgeRecord>) UserSession.getInSessionProperty(enter.getOriginBillNo());
		UserSession.setOutSessionProperty(enter.getOriginBillNo(), null);//清空缓存
		//构建对象
		List<CiStorge> storgeList = new ArrayList<>();//库存主表对象集合
		List<CiStorgeDetail> storgeDetailList = new ArrayList<>();//库存明细对象集合
		List<CiStorgeRecord> storgeRecordList = new ArrayList<>();//入库库存操作履历对象集合
		Map<String, Double[]> numMap = new HashMap<>();//库存主表数量Map
		for (CiStorgeRecord record : recordList) {
			//构建库存主表对象
			CiStorge siStorge = BeanExchangeUtils.storgeRecordToStorge(record,enter,numMap);
			if(siStorge != null){
				storgeList.add(siStorge);
			}
			//构建库存明细对象
			CiStorgeDetail detail = BeanExchangeUtils.storgeRecordToStorgeDetail(record,enter);
			storgeDetailList.add(detail);
			//重新赋值
			storgeRecordList.add(BeanExchangeUtils.storgeDetailToStorgeRecord(detail, 0d, detail.getEnterNum()));
		}
		//遍历库存主表对象集合
		for (CiStorge storge : storgeList) {
			storge.setTotalNum(numMap.get(storge.getMatNum())[0]);
			storge.setTotalAmount(numMap.get(storge.getMatNum())[1]);
			//判断库存中是否存在该物资
			EiInfo outInfo = CiUtils.invoke(null, "CIKC01", "isExistMat", new String[]{"wareHouseNo","matNum"},
					storge.getWareHouseNo(),storge.getMatNum());
			if("true".equals(outInfo.getString("isExistMat"))){//存在，更新库存主表
				//调用本地服务更新库存主表
				CiUtils.invoke(null,"CIKC01","updateStorge",new String[]{"storge"},storge);
			} else {//新增，插入库存主表
				//调用本地服务插入存主表
				CiUtils.invoke(null,"CIKC01","insertStorge",new String[]{"storge"},storge);
			}
		}
		//调用本地服插入库存明细表
		CiUtils.invoke(null,"CIKC0101","insertStorgeDetail",new String[]{"storgeDetailList"},storgeDetailList);
		//调用本地服务插入库存履历表
		CiUtils.invoke(null,"CIKC02","insert",new String[]{"recordList"},storgeRecordList);
		inInfo.setMsg("调拨入库成功");
		return inInfo;
	}
	
	/**
	 * 红冲入库
	 * 
	 * <p>对指定入库记录对应的库存记录进行操作，扣除红冲的数量</p>
	 * 
	 * @Title: enterStockByHC
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		enter：入库主单据
	 * 		enterDetailList：入库明细集合
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo enterStockByHC(EiInfo inInfo) {
		CiEnter enter = (CiEnter) inInfo.get("enter");
		List<CiEnterDetail> enterDetailList = (List<CiEnterDetail>) inInfo.get("enterDetailList");
		//判断是否是直出
		List<String> pList = dao.query("CIRK01.queryOriginEnterBillType", enter.getOriginBillNo());
		if(CiConstant.ENTER_TYPE_ZRZC.equals(pList.get(0))) {
			redPushZRZC(enter, enterDetailList);
		} else {
			//获取指定的物资明细集合
			EiInfo outInfo = CiUtils.invoke(null, "CIKC0101", "queryStorgeDetail", new String[]{"enterBillNo"}, enter.getOriginBillNo());
			List<CiStorgeDetail> siStorgeDetailList = (List<CiStorgeDetail>) outInfo.get("list");
			//构建库存操作履历对象集合
			List<CiStorgeRecord> storgeRecordList = new ArrayList<>();
			//判断物资是足够红冲入库,足够，返回要红冲的库存明细;不够，返回null
			List<CiStorgeDetail> storgeDetailList = isEnoughHC(enterDetailList, siStorgeDetailList, storgeRecordList);
			if (storgeDetailList == null) {
				inInfo.setStatus(-1);
				inInfo.setMsg("当前库存不足 无法红冲");
				return inInfo;
			}
			//调用本地服务更新库存及库存明细
			CiUtils.invoke(null, "CIKC01", "updateStorgeByReduce", new String[]{"storgeDetailList"}, storgeDetailList);
			//调用本地服务插入库存履历表
			CiUtils.invoke(null, "CIKC02", "insert", new String[]{"recordList"}, storgeRecordList);
			inInfo.setMsg("红冲入库成功");
		}
		return inInfo;
	}

	/**
	 * 判断库存是否足够红冲入库
	 * 
	 * <p>判断物资是足够红冲入库,足够，返回要红冲的库存明细;不够，返回null</p>
	 * 
	 * @Title: isEnoughHC
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param enterDetailList ： 入库明细
	 * @param:  @param siStorgeDetailList ： 库存明细
	 * @param:  @param storgeRecordList ： 库存操作履历
	 * @param:  @return
	 * @return: List<CiStorgeDetail>  ： 库存明细
	 * @throws
	 */
	private List<CiStorgeDetail> isEnoughHC(List<CiEnterDetail> enterDetailList,
											List<CiStorgeDetail> siStorgeDetailList, List<CiStorgeRecord> storgeRecordList) {
		List<CiStorgeDetail> list = new ArrayList<>();
		for (CiStorgeDetail storgeDetail : siStorgeDetailList) {
			for (CiEnterDetail enterDetail : enterDetailList) {
				//判断物资是否一致
				if(storgeDetail.getMatNum().equals(enterDetail.getMatNum())){
					//一致，判断库存数量是否小于红冲数量
					if(storgeDetail.getEnterNum() < enterDetail.getEnterNum()){//是，返回null
						return null;
					}
					//构建库存操作履历对象
					CiStorgeRecord record = BeanExchangeUtils.storgeDetailToStorgeRecord(storgeDetail,
							storgeDetail.getEnterNum(), storgeDetail.getEnterNum()-enterDetail.getEnterNum());
					record.setOriginBillNo(enterDetail.getEnterBillNo());
					record.setOriginBillType(enterDetail.getEnterType());
					record.setOriginBillTypeName(enterDetail.getEnterTypeName());
					storgeRecordList.add(record);
					//不是，修改数量，将库存明细加入新的list
					storgeDetail.setEnterNum(enterDetail.getEnterNum());
					storgeDetail.setEnterAmount(enterDetail.getEnterAmount());
					list.add(storgeDetail);
				}
			}
		}
		return list;
	}

	private void redPushZRZC(CiEnter enter, List<CiEnterDetail> enterDetailList) {
		CiOut out = BeanExchangeUtils.enterToOut(enter);
		out.setOutType(CiConstant.OUT_TYPE_HC);
		out.setOutTypeName(CommonUtils.getDataCodeItemName("wilp.ci.outType", out.getOutType()));
		out.setOriginBillType("05");
//		out.setTotalNum(enter.getTotalNum());
//		out.setTotalAmount(enter.getTotalAmount());
		//将入库明细转换成出库明细
		List<CiOutDetail> outDetailList = new ArrayList<CiOutDetail>();
		enterDetailList.forEach(enterDetail -> {
			CiOutDetail siOutDetail = BeanExchangeUtils.enterDetailToOutDetail(enterDetail, out);
			siOutDetail.setOutType(out.getOutType());
			siOutDetail.setOutTypeName(out.getOutTypeName());
			outDetailList.add(siOutDetail);
		});
		dao.insert("CICK01.insert", out);
		dao.insert("CICK0101.insert", outDetailList);
	}

}
