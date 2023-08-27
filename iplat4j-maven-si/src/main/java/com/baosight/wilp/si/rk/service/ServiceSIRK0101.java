package com.baosight.wilp.si.rk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.*;
import com.baosight.wilp.si.kc.domain.SiStorge;
import com.baosight.wilp.si.kc.domain.SiStorgeDetail;
import com.baosight.wilp.si.kc.domain.SiStorgeRecord;
import com.baosight.wilp.si.rk.domain.EnterTypeEnum;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 直入直出子页面Service
 *
 * <p>页面加载</p>
 * <p>入库</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIRK0101.java
 * @ClassName:  ServiceSIRK0101
 * @Package com.baosight.wilp.si.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午4:44:05
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIRK0101 extends ServiceBase {

	/**模块编码：物资采购(MP)**/
	public static final String MODULE_CODE_MP = "MP";

	/**微服务ID**/
	public static final String FA_SERVICE_ID = "S_FA_CONFIRM_01";

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
		/**参数处理**/
		SiEnter enter = new SiEnter();
		List<SiEnterDetail> enterDetailList = new ArrayList<>();
		//判断是否存在入库类型参数。存在，则为直入直出、手工入库、红冲入库；不存在则为调拨入库、盘盈入库、采购入库
		String enterType = inInfo.getString("enterType");
		if(StringUtils.isBlank(enterType)){
			enter = (SiEnter) inInfo.get("enter");
			enterDetailList = (List<SiEnterDetail>) inInfo.get("enterDetailList");
		} else {
			enter = BeanExchangeUtils.mapToEnter(inInfo.getAttr());
			List<Map<String, String>> rows  = (List<Map<String, String>>) inInfo.get("rows");
			for(int index=0; index < rows.size(); index++) {
				Map<String, String> pMap = rows.get(index);
				//过滤数量为0的
				if(NumberUtils.toDouble(pMap.get("enterNum"), 0d) == 0) { continue;}
				//数据处理
				SiEnterDetail detail = BeanExchangeUtils.mapToEnterDetail(pMap, enter);
				detail.setSurpNum(inInfo.getString("supplierNum"));
				detail.setSurpName(inInfo.getString("supplierName"));
				detail.setSortNo(index);
				enterDetailList.add(detail);
			}
		}

		/**校验仓库是否正常（存在、冻结）**/
		EiInfo outInfo = SiUtils.invoke(null, "SIWH01", "isCheckWareHouse", new String[]{"wareHouseNo"}, enter.getWareHouseNo());
		if(!SiUtils.toBoolean(outInfo.getString("isCheck"))){
			return ValidatorUtils.errorInfo("仓库不存在或已被冻结");
		}

		/**保存入库单据**/
		dao.insert("SIRK01.insert", enter);
		dao.insert("SIRK0101.insert", enterDetailList);
		inInfo.setMsg("保存成功");

		/**是否需要验收/审核, 是,只保存入库单，不入库**/
		String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			//根据入库类型，执行对应的方法
			outInfo = SiUtils.invoke(null, "SIRK0101", EnterTypeEnum.getMethodNameByCode(enter.getEnterType()),
					new String[]{"enter","enterDetailList"}, enter,enterDetailList);
			//方法执行失败
			if(outInfo.getStatus() < 0) {
				return ValidatorUtils.errorInfo(SiUtils.isEmpty(outInfo.getMsg(), "入库失败"));
			}
			//推送到固定资产
			dockFixedAssets(enter, enterDetailList);
			inInfo.setMsg("入库成功");
		}
		inInfo.set("enterBillNo", enter.getEnterBillNo());
		return inInfo;
	}

	/**
	 * 审核后入库，修改库存
	 * @Title: enterStorage
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo enterStorage(EiInfo inInfo) {
		List<String> enterBillNos = SiUtils.toList(inInfo.get("enterBillNos"), String.class);
		//遍历，入库
		EiInfo outInfo = null;
		enterBillNos = enterBillNos.stream().distinct().collect(Collectors.toList());
		for (String enterBillNo : enterBillNos) {
			//获取入库信息
			HashMap pMap = new HashMap(4) {{put("enterBillNoEQ", enterBillNo);}};
			List<SiEnter> list = dao.query("SIRK01.query", pMap);
			if(CollectionUtils.isEmpty(list)) {continue;}
			SiEnter enter = list.get(0);
			//获取入库明细
			pMap.put("enterBillNo", enterBillNo);
			List<SiEnterDetail> details = dao.query("SIRK0101.query", pMap);
			//调用指定类型的入库接口
			outInfo = SiUtils.invoke(null, "SIRK0101", EnterTypeEnum.getMethodNameByCode(enter.getEnterType()),
					new String[]{"enter", "enterDetailList"}, enter, details);
			//方法执行失败
			if(outInfo.getStatus() < 0) {
				return ValidatorUtils.errorInfo(SiUtils.isEmpty(outInfo.getMsg(), "入库失败"));
			}
			//推送到固定资产
			dockFixedAssets(enter, details);
			inInfo.setMsg("入库成功");
		}
		return inInfo;

	}

	/**
	 * 入库推送物资到固定资产
	 * @Title: dockFixedAssets
	 * @param enter enter : 入库单
	 * @param enterDetailList enterDetailList : 入库单明细
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	private EiInfo dockFixedAssets(SiEnter enter, List<SiEnterDetail> enterDetailList) {
		//存在微服务、存在采购模块、入库类型为手工入库
		if(SiUtils.isExistService(FA_SERVICE_ID) && SiUtils.isExistModule(MODULE_CODE_MP)
				&& (SiConstant.ENTER_TYPE_SG.equals(enter.getEnterType()) ||
				SiConstant.ENTER_TYPE_ZRZC.equals(enter.getEnterType()))) {
			EiInfo eiInfo = new EiInfo();
			eiInfo.set("enterDetails", enterDetailList);
			eiInfo.set("originBillType", enter.getOriginBillType());
			eiInfo.set("originBillTypeName", enter.getOriginBillTypeName());
			eiInfo.set("enterType", enter.getEnterType());
			eiInfo.set("enterTypeName", enter.getEnterTypeName());
			eiInfo.set("deptNum", enter.getUserDeptNum());
			eiInfo.set("deptName", enter.getUserDeptName());
			eiInfo.set("invoiceNum", enter.getInvoiceNum());
			eiInfo.set("wareHouseNo", enter.getWareHouseNo());
			eiInfo.set("wareHouseName", enter.getWareHouseName());
			return SiUtils.invoke(eiInfo, "SIJK04", "pushEnter", null, null);
		}
		return new EiInfo();
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
		SiEnter enter = (SiEnter) inInfo.get("enter");
		List<SiEnterDetail> enterDetailList = (List<SiEnterDetail>) inInfo.get("enterDetailList");
		String userDeptNum = enter.getUserDeptNum();
		String userDeptName = enter.getUserDeptName();
		SiOut out = BeanExchangeUtils.enterToOut(enter, userDeptNum, userDeptName);
		out.setRecCreator(StringUtils.isNotBlank(enter.getRecCreator()) ? enter.getRecCreator() : out.getRecCreator());
		out.setBillMaker(StringUtils.isNotBlank(enter.getBillMaker()) ? enter.getBillMaker() : out.getBillMaker());
		out.setBillMakerName(StringUtils.isNotBlank(enter.getBillMakerName()) ? enter.getBillMakerName() : out.getBillMakerName());
		out.setTotalNum(enter.getTotalNum());
		out.setTotalAmount(enter.getTotalAmount());
		//将入库明细转换成出库明细
		List<SiOutDetail> outDetailList = new ArrayList<SiOutDetail>();
		enterDetailList.forEach(enterDetail -> outDetailList.add(BeanExchangeUtils.enterDetailToOutDetail(enterDetail,out)));
		//调用本地服务出库
		EiInfo outInfo = SiUtils.invoke(null, "SICK0101", "outStock", new String[]{"out","outDetailList"}, out, outDetailList);
		if(SiUtils.dealOutInfo(inInfo, outInfo, "出库失败")){
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
		SiEnter enter = (SiEnter) inInfo.get("enter");
		List<SiEnterDetail> enterDetailList = (List<SiEnterDetail>) inInfo.get("enterDetailList");
		//库存明细List
		List<SiStorgeDetail> storgeDetailList = new ArrayList<>();
		//库存履历List
		List<SiStorgeRecord> storgeRecordList = new ArrayList<>();
		//遍历库存明细
		enterDetailList.forEach(enterDetail -> {
			//构建库存主表对象
			SiStorge storge = BeanExchangeUtils.enterDetailToStorge(enter,enterDetail);
			//构建库存明细表对象
			SiStorgeDetail storgeDetail = BeanExchangeUtils.enterDetailToStorgeDetail(enterDetail,storge);
			storgeDetailList.add(storgeDetail);
			//构建库存履历对象
			SiStorgeRecord record = BeanExchangeUtils.storgeDetailToStorgeRecord(storgeDetail, 0d, storgeDetail.getEnterNum());
			storgeRecordList.add(record);
			//判断库存中是否存在该物资
			EiInfo outInfo = SiUtils.invoke(null, "SIKC01", "isExistMat", new String[]{"wareHouseNo","matNum"},
					enter.getWareHouseNo(),enterDetail.getMatNum());
			if(SiUtils.toBoolean(outInfo.getString("isExistMat"))){//存在，更新库存主表
				//调用本地服务更新库存主表
				SiUtils.invoke(null,"SIKC01","updateStorge",new String[]{"storge"},storge);
			} else {//新增，插入库存主表
				//调用本地服务插入存主表
				SiUtils.invoke(null,"SIKC01","insertStorge",new String[]{"storge"},storge);
			}
		});
		//调用本地服插入库存明细表
		SiUtils.invoke(null,"SIKC0101","insertStorgeDetail",new String[]{"storgeDetailList"},storgeDetailList);
		//调用本地服务插入库存履历表
		SiUtils.invoke(null,"SIKC02","insert",new String[]{"recordList"},storgeRecordList);

		//更新常用物资单价
		if(SiUtils.isExistService("S_RM_JK_06")) {
			EiInfo remoteInfo = new EiInfo();
			remoteInfo.set("matNumList", storgeDetailList.stream().map(detail -> detail.getMatNum()).collect(Collectors.toList()));
			SiUtils.invokeRemote(remoteInfo, "S_RM_JK_06" );
		}
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
		SiEnter enter = (SiEnter) inInfo.get("enter");
		//获取对应调拨出库的库存操作履历
		List<SiStorgeRecord> recordList = (List<SiStorgeRecord>) UserSession.getInSessionProperty(enter.getOriginBillNo());
		if(CollectionUtils.isEmpty(recordList)) {
			recordList = dao.query("SIKC02.query", new HashMap(){{put("outTransBillNo", enter.getOriginBillNo());}});
		}
		UserSession.setOutSessionProperty(enter.getOriginBillNo(), null);//清空缓存
		//构建对象
		List<SiStorge> storgeList = new ArrayList<>();//库存主表对象集合
		List<SiStorgeDetail> storgeDetailList = new ArrayList<>();//库存明细对象集合
		List<SiStorgeRecord> storgeRecordList = new ArrayList<>();//入库库存操作履历对象集合
		Map<String, Double[]> numMap = new HashMap<>();//库存主表数量Map
		for (SiStorgeRecord record : recordList) {
			//构建库存主表对象
			SiStorge siStorge = BeanExchangeUtils.storgeRecordToStorge(record,enter,numMap);
			if(siStorge != null){
				storgeList.add(siStorge);
			}
			//构建库存明细对象
			SiStorgeDetail detail = BeanExchangeUtils.storgeRecordToStorgeDetail(record,enter);
			storgeDetailList.add(detail);
			//重新赋值
			storgeRecordList.add(BeanExchangeUtils.storgeDetailToStorgeRecord(detail, 0d, detail.getEnterNum()));
		}
		//遍历库存主表对象集合
		for (SiStorge storge : storgeList) {
			storge.setTotalNum(numMap.get(storge.getMatNum())[0]);
			storge.setTotalAmount(numMap.get(storge.getMatNum())[1]);
			//判断库存中是否存在该物资
			EiInfo outInfo = SiUtils.invoke(null, "SIKC01", "isExistMat", new String[]{"wareHouseNo","matNum"},
					storge.getWareHouseNo(),storge.getMatNum());
			if(SiUtils.toBoolean(outInfo.getString("isExistMat"))){//存在，更新库存主表
				//调用本地服务更新库存主表
				SiUtils.invoke(null,"SIKC01","updateStorge",new String[]{"storge"},storge);
			} else {//新增，插入库存主表
				//调用本地服务插入存主表
				SiUtils.invoke(null,"SIKC01","insertStorge",new String[]{"storge"},storge);
			}
		}
		//调用本地服插入库存明细表
		SiUtils.invoke(null,"SIKC0101","insertStorgeDetail",new String[]{"storgeDetailList"},storgeDetailList);
		//调用本地服务插入库存履历表
		SiUtils.invoke(null,"SIKC02","insert",new String[]{"recordList"},storgeRecordList);
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
		SiEnter enter = (SiEnter) inInfo.get("enter");
		List<SiEnterDetail> enterDetailList = (List<SiEnterDetail>) inInfo.get("enterDetailList");
		//判断是否是直出
		List<String> pList = dao.query("SIRK01.queryOriginEnterBillType", enter.getOriginBillNo());
		if(SiConstant.ENTER_TYPE_ZRZC.equals(pList.get(0))) {
			redPushZRZC(enter, enterDetailList);
		} else {
			//获取指定的物资明细集合
			EiInfo outInfo = SiUtils.invoke(null, "SIKC0101", "queryStorgeDetail", new String[]{"enterBillNo"}, enter.getOriginBillNo());
			List<SiStorgeDetail> siStorgeDetailList = (List<SiStorgeDetail>) outInfo.get("list");
			//构建库存操作履历对象集合
			List<SiStorgeRecord> storgeRecordList = new ArrayList<>();
			//判断物资是足够红冲入库,足够，返回要红冲的库存明细;不够，返回null
			List<SiStorgeDetail> storgeDetailList = isEnoughHC(enterDetailList, siStorgeDetailList, storgeRecordList);
			if (storgeDetailList == null) {
				inInfo.setStatus(-1);
				inInfo.setMsg("当前库存不足 无法红冲");
				return inInfo;
			}
			//调用本地服务更新库存及库存明细
			SiUtils.invoke(null, "SIKC01", "updateStorgeByReduce", new String[]{"storgeDetailList"}, storgeDetailList);
			//调用本地服务插入库存履历表
			SiUtils.invoke(null, "SIKC02", "insert", new String[]{"recordList"}, storgeRecordList);
			inInfo.setMsg("红冲入库成功");
		}
		return inInfo;
	}

	/**
	 * 直入直出红冲
	 * @Title: redPushZRZC
	 * @param enter enter
	 * @return void
	 * @throws
	 **/
	private void redPushZRZC(SiEnter enter, List<SiEnterDetail> enterDetailList) {
		SiOut out = BeanExchangeUtils.enterToOut(enter, enter.getUserDeptNum(), enter.getUserDeptName());
		out.setOutType(SiConstant.OUT_TYPE_HC);
		out.setOutTypeName(CommonUtils.getDataCodeItemName("wilp.si.outType", out.getOutType()));
		out.setOriginBillType("05");
		out.setTotalNum(enter.getTotalNum());
		out.setTotalAmount(enter.getTotalAmount());
		out.setBillMaker(enter.getBillMaker());
		out.setBillMakerName(enter.getBillMakerName());
		//将入库明细转换成出库明细
		List<SiOutDetail> outDetailList = new ArrayList<SiOutDetail>();
		enterDetailList.forEach(enterDetail -> {
			SiOutDetail siOutDetail = BeanExchangeUtils.enterDetailToOutDetail(enterDetail, out);
			siOutDetail.setOutType(out.getOutType());
			siOutDetail.setOutTypeName(out.getOutTypeName());
			outDetailList.add(siOutDetail);
		});
		dao.insert("SICK01.insert", out);
		dao.insert("SICK0101.insert", outDetailList);
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
	 * @return: List<SiStorgeDetail>  ： 库存明细
	 * @throws
	 */
	private List<SiStorgeDetail> isEnoughHC(List<SiEnterDetail> enterDetailList,
											List<SiStorgeDetail> siStorgeDetailList, List<SiStorgeRecord> storgeRecordList) {
		List<SiStorgeDetail> list = new ArrayList<>();
		for (SiStorgeDetail storgeDetail : siStorgeDetailList) {
			for (SiEnterDetail enterDetail : enterDetailList) {
				//判断物资是否一致
				if(storgeDetail.getMatNum().equals(enterDetail.getMatNum())){
					//一致，判断库存数量是否小于红冲数量
					if(storgeDetail.getEnterNum() < enterDetail.getEnterNum()){//是，返回null
						return null;
					}
					//构建库存操作履历对象
					SiStorgeRecord record = BeanExchangeUtils.storgeDetailToStorgeRecord(storgeDetail,
							storgeDetail.getEnterNum(), storgeDetail.getEnterNum()-enterDetail.getEnterNum());
					record.setOriginBillNo(enterDetail.getEnterBillNo());
					record.setOriginBillType(enterDetail.getEnterType());
					record.setOriginBillTypeName(enterDetail.getEnterTypeName());
					record.setRecCreateTime(DateUtils.curDateTimeStr19());
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



}
