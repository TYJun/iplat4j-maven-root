package com.baosight.wilp.ci.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.wilp.ci.ck.domain.CiOut;
import com.baosight.wilp.ci.ck.domain.CiOutDetail;
import com.baosight.wilp.ci.kc.domain.CiStorge;
import com.baosight.wilp.ci.kc.domain.CiStorgeDetail;
import com.baosight.wilp.ci.kc.domain.CiStorgeRecord;
import com.baosight.wilp.ci.pk.domain.CiInven;
import com.baosight.wilp.ci.pk.domain.CiInvenDetail;
import com.baosight.wilp.ci.rk.domain.CiEnter;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.db.domain.CiTrans;
import com.baosight.wilp.ci.db.domain.CiTransDetail;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 实体转换工具类
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  BeanExchangeUtils.java
 * @ClassName:  BeanExchangeUtils
 * @Package com.baosight.wilp.ci.common
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午4:03:32 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class BeanExchangeUtils {

	/**
	 * 将库存主表实体转换成盘库主表实体
	 * @Title: storgeToInven
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param storge
	 * @param:  @return
	 * @return: CiInven  
	 * @throws
	 */
	public static CiInven storgeToInven(CiStorge storge) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
	    CiInven inven = new CiInven();
	    inven.setId(UUID.randomUUID().toString());
	    inven.setRecCreator(workNo);
	    inven.setRecCreateTime(DateUtils.curDateTimeStr19());
	    inven.setDataGroupCode(dataGroupCode);
        String invenBillNo ="IW" + DateUtils.curDateTimeStr14();
        inven.setInvenBillNo(invenBillNo);
        inven.setInvenStatus("0");
        inven.setWareHouseNo(storge.getWareHouseNo());
        inven.setWareHouseName(storge.getWareHouseName());
        inven.setStorageNo(storge.getStorageNo());
        inven.setStackNo(storge.getStackNo());
        inven.setBillMakeTime(DateUtils.curDateTimeStr19());
        inven.setBillMaker(workNo);
        inven.setBillMakerName(name);
		return inven;
	}

	/**
	 * 将库存主表实体转换成盘库明细表实体
	 * @Title: storgeToInvenDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param storge
	 * @param:  @param invenBillNo
	 * @param:  @return
	 * @return: CiInven  
	 * @throws
	 */
	public static CiInvenDetail storgeToInvenDetail(CiStorge storge, String invenBillNo) {
	    //属性赋值
	    CiInvenDetail invenDetail = new CiInvenDetail();
	    invenDetail.setId(UUID.randomUUID().toString());
	    invenDetail.setRecCreator(UserSession.getUser().getUsername());
	    invenDetail.setRecCreateTime(DateUtils.curDateTimeStr19());
	    invenDetail.setInvenBillNo(invenBillNo);
        String invenBillDetailNo = "IWD" + DateUtils.curDateTimeStr14();
        invenDetail.setInvenBillDetailNo(invenBillDetailNo);
        invenDetail.setMatNum(storge.getMatNum());
        invenDetail.setMatName(storge.getMatName());
        invenDetail.setMatModel(storge.getMatModel());
        invenDetail.setMatSpec(storge.getMatSpec());
        invenDetail.setUnit(storge.getUnit());
        invenDetail.setUnitName(storge.getUnitName());
        invenDetail.setBeforeInvenNum(storge.getTotalNum());
        invenDetail.setBeforeInvenAmount(storge.getTotalAmount());
        invenDetail.setInvenDate(DateUtils.curDateStr10());
        invenDetail.setInvenTime(DateUtils.curDateTimeStr19());
		invenDetail.setShelfLife(storge.getShelfLife());
		invenDetail.setExpirationDate(storge.getExpirationDate());
		return invenDetail;
	}

	/**
	 * 将盘库主表实体转换成入库主表实体
	 * @Title: invenToEnter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inven
	 * @param:  @return
	 * @return: CiEnter  
	 * @throws
	 */
	public static CiEnter invenToEnter(CiInven inven) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
		CiEnter enter = new CiEnter();
		enter.setRecCreator(workNo);
		enter.setRecCreateTime(DateUtils.curDateTimeStr19());
		enter.setDataGroupCode(dataGroupCode);
		enter.setId(UUID.randomUUID().toString());
		String enterBillNo = "EBN" + DateUtils.curDateTimeStr14();
		enter.setEnterBillNo(enterBillNo);
		enter.setEnterType("03");
		enter.setEnterTypeName("盘盈入库");
		enter.setOriginBillNo(inven.getInvenBillNo());
		enter.setOriginBillType("03");
		enter.setOriginBillTypeName("盘盈");
		enter.setWareHouseNo(inven.getWareHouseNo());
		enter.setWareHouseName(inven.getWareHouseName());
		enter.setStorageNo(inven.getStorageNo());
		enter.setStackNo(inven.getStackNo());
		enter.setBillMaker(workNo);
		enter.setBillMakerName(name);
		enter.setBillMakeTime(DateUtils.curDateTimeStr19());
		enter.setIsCheck(0);
		String batchNo = "BCN" + DateUtils.curDateTimeStr14();
		enter.setBatchNo(batchNo);
		return enter;
	}

	/**
	 * 将盘库主表实体转换成出库主表实体
	 * @Title: invenToOut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inven
	 * @param:  @return
	 * @return: CiOut  
	 * @throws
	 */
	public static CiOut invenToOut(CiInven inven) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
	    CiOut out = new CiOut();
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    String outBillNo = "OW" + DateUtils.curDateTimeStr14();
	    out.setOutBillNo(outBillNo);
	    out.setOutType("03");
	    out.setOutTypeName("盘亏出库");
	    out.setOriginBillNo(inven.getInvenBillNo());
	    out.setOriginBillType("01");
	    out.setWareHouseNo(inven.getWareHouseNo());
	    out.setWareHouseName(inven.getWareHouseName());
	    out.setStorageNo(inven.getStorageNo());
	    out.setStackNo(inven.getStackNo());
	    out.setBillMaker(workNo);
	    out.setBillMakerName(name);
	    out.setBillMakeTime(DateUtils.curDateTimeStr19());
	    out.setIsCheck(0);
		return out;
	}

	/**
	 * 盘库明细表实体转换成入库明细表实体
	 * @Title: invenDetailToEnterDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param invenDetail
	 * @param:  @param enter
	 * @param:  @return
	 * @return: CiEnterDetail  
	 * @throws
	 */
	public static CiEnterDetail invenDetailToEnterDetail(CiInvenDetail invenDetail, CiEnter enter) {
		CiEnterDetail detail = new CiEnterDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "EBDN" + DateUtils.curDateTimeStr14();
        detail.setEnterBillNo(enter.getEnterBillNo());
        detail.setEnterBillDetailNo(detailBillNo);
        detail.setEnterType("03");
        detail.setEnterTypeName("盘盈入库");
        detail.setMatNum(invenDetail.getMatNum());
        detail.setMatName(invenDetail.getMatName());
        detail.setMatModel(invenDetail.getMatModel());
        detail.setMatSpec(invenDetail.getMatSpec());
        detail.setUnit(invenDetail.getUnit());
        detail.setUnitName(invenDetail.getUnitName());
        detail.setEnterNum(invenDetail.getAfterInvenNum()-invenDetail.getBeforeInvenNum());
        detail.setEnterPrice(invenDetail.getPrice());
        detail.setEnterAmount(invenDetail.getAfterInvenAmount()-invenDetail.getBeforeInvenAmount());
        detail.setEnterDate(DateUtils.curDateStr10());
        detail.setEnterTime(DateUtils.curDateTimeStr19());
		detail.setShelfLife(invenDetail.getShelfLife());
		detail.setExpirationDate(invenDetail.getExpirationDate());
		return detail;
	}

	/**
	 * 盘库明细表实体转换成出库明细表实体
	 * @Title: invenDetailToOutDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param invenDetail
	 * @param:  @param out
	 * @param:  @return
	 * @return: CiOutDetail  
	 * @throws
	 */
	public static CiOutDetail invenDetailToOutDetail(CiInvenDetail invenDetail, CiOut out) {
		CiOutDetail detail = new CiOutDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
        detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(detailBillNo);
        detail.setOutType("03");
        detail.setOutTypeName("盘亏出库");
        detail.setMatNum(invenDetail.getMatNum());
        detail.setMatName(invenDetail.getMatName());
        detail.setMatModel(invenDetail.getMatModel());
        detail.setMatSpec(invenDetail.getMatSpec());
        detail.setUnit(invenDetail.getUnit());
        detail.setUnitName(invenDetail.getUnitName());
        detail.setOutNum(invenDetail.getBeforeInvenNum()-invenDetail.getAfterInvenNum());
        detail.setOutPrice(invenDetail.getPrice());
        detail.setOutAmount(invenDetail.getBeforeInvenAmount()-invenDetail.getAfterInvenAmount());
        detail.setOutDate(DateUtils.curDateStr10());
        detail.setOutTime(DateUtils.curDateTimeStr19());
		return detail;
	}
	
	/**
	 * 将Map转换成调拨主单据
	 * @Title: mapToTrans
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * @return: void  
	 * @throws
	 */
	public static CiTrans mapToTrans(Map<String, Object> map) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
		CiTrans trans = new CiTrans();
		trans.fromMap(map);
		trans.setRecCreator(workNo);
		trans.setRecCreateTime(DateUtils.curDateTimeStr19());
		trans.setDataGroupCode(dataGroupCode);
		trans.setId(UUID.randomUUID().toString());
		trans.setTransBillNo("TR" + DateUtils.curDateTimeStr14());
		trans.setBillMakerNo(workNo);
		trans.setBillMaker(name);
		trans.setBillMakeTime(DateUtils.curDateTimeStr19());
		trans.setIsCheck(0);
		return trans;
	}

	/**
	 * 将Map转换成调拨明细表对象
	 * @Title: mapToTransDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * @param:  @return
	 * @return: CiTransDetail  
	 * @throws
	 */
	public static CiTransDetail mapToTransDetail(Map<String, Object> map, CiTrans trans) {
		CiTransDetail detail = new CiTransDetail();
		detail.fromMap(map);
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "TRD" + DateUtils.curDateTimeStr14();
		detail.setTransBillNo(trans.getTransBillNo());
		detail.setTransBillDetailNo(detailBillNo);
        return detail;
	}

	/**
	 * 将调拨主单据转成入库主单据
	 * @Title: transToEnter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param trans
	 * @param:  @return
	 * @return: CiEnter  
	 * @throws
	 */
	public static CiEnter transToEnter(CiTrans trans) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
	    CiEnter enter = new CiEnter();
	    enter.setRecCreator(workNo);
	    enter.setRecCreateTime(DateUtils.curDateTimeStr19());
	    enter.setDataGroupCode(dataGroupCode);
	    enter.setId(UUID.randomUUID().toString());
	    String enterBillNo = "EBN" + DateUtils.curDateTimeStr14();
	    enter.setEnterBillNo(enterBillNo);
	    enter.setEnterType("02");
		enter.setEnterTypeName("调拨入库");
		enter.setOriginBillNo(trans.getTransBillNo());
		enter.setOriginBillType("04");
		enter.setOriginBillTypeName("调拨");
		enter.setWareHouseNo(trans.getInWareHouseNo());
		enter.setWareHouseName(trans.getInWareHouseName());
		enter.setStorageNo(trans.getInStorageNo());
		enter.setStackNo(trans.getInStackNo());
		enter.setBillMaker(workNo);
		enter.setBillMakerName(name);
		enter.setBillMakeTime(DateUtils.curDateTimeStr19());
		enter.setIsCheck(0);
		String batchNo = "BCN" + DateUtils.curDateTimeStr14();
		enter.setBatchNo(batchNo);
		enter.setShelfLife(trans.getShelfLife());
		enter.setExpirationDate(trans.getExpirationDate());
		return enter;
	}

	/**
	 * 将调拨主单据转成出库主单据
	 * @Title: transToOut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param trans
	 * @param:  @return
	 * @return: CiOut  
	 * @throws
	 */
	public static CiOut transToOut(CiTrans trans) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
	    CiOut out = new CiOut();
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    String outBillNo = "OW" + DateUtils.curDateTimeStr14();
	    out.setOutBillNo(outBillNo);
	    out.setOutType("02");
	    out.setOutTypeName("调拨出库");
	    out.setOriginBillNo(trans.getTransBillNo());
	    out.setOriginBillType("02");
	    out.setWareHouseNo(trans.getOutWareHouseNo());
	    out.setWareHouseName(trans.getOutWareHouseName());
	    out.setStorageNo(trans.getOutStorageNo());
	    out.setStackNo(trans.getOutStackNo());
	    out.setBillMaker(workNo);
	    out.setBillMakerName(name);
	    out.setBillMakeTime(DateUtils.curDateTimeStr19());
	    out.setIsCheck(0);
		return out;
	}
	
	/**
	 * 将调拨明细转成入库明细
	 * @Title: transDetailToEnterDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param detail
	 * @param:  @param enter
	 * @param:  @return
	 * @return: CiEnterDetail  
	 * @throws
	 */
	public static CiEnterDetail transDetailToEnterDetail(CiTransDetail transDetail, CiEnter enter) {
		CiEnterDetail detail = new CiEnterDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "EBDN" + DateUtils.curDateTimeStr14();
        detail.setEnterBillNo(enter.getEnterBillNo());
        detail.setEnterBillDetailNo(detailBillNo);
        detail.setEnterType("02");
        detail.setEnterTypeName("调拨入库");
        detail.setMatNum(transDetail.getMatNum());
        detail.setMatName(transDetail.getMatName());
        detail.setMatModel(transDetail.getMatModel());
        detail.setMatSpec(transDetail.getMatSpec());
        detail.setUnit(transDetail.getUnit());
        detail.setUnitName(transDetail.getUnitName());
        detail.setEnterNum(transDetail.getTransNum());
        detail.setEnterPrice(transDetail.getPrice());
        detail.setEnterAmount(transDetail.getTransNum()*transDetail.getPrice());
        detail.setEnterDate(DateUtils.curDateStr10());
        detail.setEnterTime(DateUtils.curDateTimeStr19());
		detail.setShelfLife(transDetail.getShelfLife());
		detail.setProductionDate(transDetail.getExpirationDate());
		return detail;
	}

	/**
	 * 将调拨明细转成出库明细
	 * @Title: transDetailToOutDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param detail
	 * @param:  @param out
	 * @param:  @return
	 * @return: CiOutDetail  
	 * @throws
	 */
	public static CiOutDetail transDetailToOutDetail(CiTransDetail transDetail, CiOut out) {
		CiOutDetail detail = new CiOutDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
        detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(detailBillNo);
        detail.setOutType("03");
        detail.setOutTypeName("盘亏出库");
        detail.setMatNum(transDetail.getMatNum());
        detail.setMatName(transDetail.getMatName());
        detail.setMatModel(transDetail.getMatModel());
        detail.setMatSpec(transDetail.getMatSpec());
        detail.setUnit(transDetail.getUnit());
        detail.setUnitName(transDetail.getUnitName());
        detail.setOutNum(transDetail.getTransNum());
        detail.setOutPrice(transDetail.getPrice());
        detail.setOutAmount(transDetail.getTransNum()*transDetail.getPrice());
        detail.setOutDate(DateUtils.curDateStr10());
        detail.setOutTime(DateUtils.curDateTimeStr19());
		detail.setShelfLife(transDetail.getShelfLife());
		detail.setExpirationDate(transDetail.getExpirationDate());
		return detail;
	}

	/**
	 * map转换成入库主单据
	 * @Title: mapToEnter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * @param:  @return
	 * @return: CiEnter  
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static CiEnter mapToEnter(Map map) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		if(userInfo.get("workNo")==null || userInfo.get("workNo").toString().equals("")){
			//兼容app端用户上传
			userInfo = CiUtils.getUserInfo(map.get("recCreator")+"");
		}
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
		CiEnter enter = new CiEnter();
		enter.fromMap(map);
		enter.setRecCreator(workNo);
	    enter.setRecCreateTime(DateUtils.curDateTimeStr19());
	    enter.setDataGroupCode(dataGroupCode);
	    enter.setId(UUID.randomUUID().toString());
	    String enterBillNo = "EBN" + DateUtils.curDateTimeStr14();
	    enter.setEnterBillNo(enterBillNo);
		enter.setEnterTypeName(CommonUtils.getDataCodeItemName("wilp.ci.enterType", enter.getEnterType()));
		//红冲入库
		if("05".equals(enter.getEnterType())){
			String enterBillNos = (String) map.get("enterBillNo");
			System.out.println("enterBillNos"+enterBillNos);
			enter.setOriginBillNo(enterBillNos);
			enter.setOriginBillType("05");
			enter.setOriginBillTypeName("红冲");
		}
		enter.setBillMaker(workNo);
		enter.setBillMakerName(name);
		enter.setBillMakeTime(DateUtils.curDateTimeStr19());
		enter.setIsCheck(0);
		String batchNo = "BCN" + DateUtils.curDateTimeStr14();
		enter.setBatchNo(batchNo);
//		if(map.containsKey("rows")){
//			List<HashMap<String, Object>> rows = (List<HashMap<String, Object>>) map.get("rows");
//			if(CollectionUtils.isNotEmpty(rows)){
//				enter.setOriginBillNo((String) rows.get(0).get("applyBillNo"));
//			}
//		}
		return enter;
	}

	/**
	 * map转换成入库明细
	 * @Title: mapToEnterDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param pMap
	 * @param:  @param enter 
	 * @param:  @return
	 * @return: CiEnterDetail  
	 * @throws
	 */
	public static CiEnterDetail mapToEnterDetail(Map<String, String> pMap, CiEnter enter) {
		CiEnterDetail detail = new CiEnterDetail();
		detail.fromMap(pMap);
		
		//属性赋值
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "EBDN" + DateUtils.curDateTimeStr14();
        detail.setEnterBillNo(enter.getEnterBillNo());
        detail.setEnterBillDetailNo(detailBillNo);
        if("05".equals(enter.getEnterType())){//红冲入库
        	detail.setEnterNum(Double.parseDouble(StringUtils.isBlank(pMap.get("redRushNum")) ? "0" : pMap.get("redRushNum")));
        }
        detail.setEnterType(enter.getEnterType());
        detail.setEnterTypeName(enter.getEnterTypeName());
		detail.setEnterAmount(detail.getEnterNum()*detail.getEnterPrice());
		detail.setEnterDate(DateUtils.curDateStr10());
        detail.setEnterTime(DateUtils.curDateTimeStr19());
		return detail;
	}

	/**
	 * 将入库主单据转换成出库主单据
	 * @Title: enterToOut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param enter
	 * @param:  @param userDeptNum
	 * @param:  @param userDeptName
	 * @param:  @return
	 * @return: CiOut  
	 * @throws
	 */
	public static CiOut enterToOut(CiEnter enter) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
	    CiOut out = new CiOut();
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    String outBillNo = "OW" + DateUtils.curDateTimeStr14();
	    out.setOutBillNo(outBillNo);
	    out.setOutType("01");
	    out.setOutTypeName("直入直出");
	    out.setOriginBillNo(enter.getEnterBillNo());
	    out.setOriginBillType("03");
	    out.setWareHouseNo(enter.getWareHouseNo());
	    out.setWareHouseName(enter.getWareHouseName());
	    out.setStorageNo(enter.getStorageNo());
	    out.setStackNo(enter.getStackNo());
	    out.setBillMaker(workNo);
	    out.setBillMakerName(name);
	    out.setBillMakeTime(DateUtils.curDateTimeStr19());
	    out.setIsCheck(0);
		return out;
	}

	/**
	 * @param out 
	 * 将入库明细转换成出库明细
	 * @Title: enterDetailToOutDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param enterDetail
	 * @param:  @return
	 * @return: CiOutDetail  
	 * @throws
	 */
	public static CiOutDetail enterDetailToOutDetail(CiEnterDetail enterDetail, CiOut out) {
		CiOutDetail detail = new CiOutDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
        detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(detailBillNo);
        detail.setOutType("01");
        detail.setOutTypeName("直入直出");
        detail.setMatNum(enterDetail.getMatNum());
        detail.setMatName(enterDetail.getMatName());
        detail.setMatModel(enterDetail.getMatModel());
        detail.setMatSpec(enterDetail.getMatSpec());
        detail.setUnit(enterDetail.getUnit());
        detail.setUnitName(enterDetail.getUnitName());
        detail.setOutNum(enterDetail.getEnterNum());
        detail.setOutPrice(enterDetail.getEnterPrice());
        detail.setOutAmount(enterDetail.getEnterAmount());
        detail.setOutDate(DateUtils.curDateStr10());
        detail.setOutTime(DateUtils.curDateTimeStr19());
		return detail;
	}

	/**
	 * 将入库明细转换成库存主单据
	 * @Title: enterDetailToStorge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param enter
	 * @param:  @param enterDetail
	 * @return: CiStorge  
	 * @throws
	 */
	public static CiStorge enterDetailToStorge(CiEnter enter, CiEnterDetail enterDetail) {
		System.out.println("enterDetail:"+enterDetail);
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    //属性赋值
	    CiStorge storge = new CiStorge();
	    storge.setRecCreator(workNo);
	    storge.setRecCreateTime(DateUtils.curDateTimeStr19());
	    storge.setRecRevisor(workNo);
		storge.setRecReviseTime(DateUtils.curDateTimeStr19());
	    storge.setDataGroupCode(dataGroupCode);
	    storge.setId(UUID.randomUUID().toString());
	    storge.setWareHouseNo(enter.getWareHouseNo());
	    storge.setWareHouseName(enter.getWareHouseName());
	    storge.setStorageNo(enter.getStorageNo());
	    storge.setStackNo(enter.getStackNo());
	    Map<String,String> map = CiUtils.getMatByMatNum(enterDetail.getMatNum());
	    storge.setMatTypeNum(map.get("matClassCode"));
	    storge.setMatTypeName(map.get("matClassName"));
	    storge.setMatNum(enterDetail.getMatNum());
	    storge.setMatName(enterDetail.getMatName());
	    storge.setMatModel(enterDetail.getMatModel());
	    storge.setMatSpec(enterDetail.getMatSpec());
	    storge.setUnit(enterDetail.getUnit());
	    storge.setUnitName(enterDetail.getUnitName());
	    storge.setPrice(enterDetail.getEnterPrice());
	    storge.setTotalNum(enterDetail.getEnterNum());
	    storge.setTotalAmount(enterDetail.getEnterAmount());
		storge.setEnterTypeName(enter.getEnterTypeName());
		storge.setBillMakerName(name);
		//保质期
		storge.setShelfLife(enterDetail.getShelfLife());
		//根据保质期生成到期时间
		Date date = DateUtils.addDays(DateUtils.curDate(), Integer.parseInt(enterDetail.getShelfLife()));
		storge.setExpirationDate(DateUtils.toDateStr(date));

	    return storge;
	}

	/**
	 * 将入库明细转换成库存明细
	 * @Title: enterDetailToStorgeDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param enterDetail
	 * @param:  @param storge
	 * @param:  @return
	 * @return: CiStorgeDetail  
	 * @throws
	 */
	public static CiStorgeDetail enterDetailToStorgeDetail(CiEnterDetail enterDetail, CiStorge storge) {
		CiStorgeDetail detail = new CiStorgeDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
		detail.setWareHouseNo(storge.getWareHouseNo());
		detail.setWareHouseName(storge.getWareHouseName());
		detail.setStorageNo(storge.getStorageNo());
		detail.setStackNo(storge.getStackNo());
		detail.setMatTypeNum(storge.getMatTypeNum());
		detail.setMatTypeName(storge.getMatTypeName());
		detail.setMatNum(enterDetail.getMatNum());
		detail.setMatName(enterDetail.getMatName());
		detail.setMatModel(enterDetail.getMatModel());
		detail.setMatSpec(enterDetail.getMatSpec());
		detail.setUnit(enterDetail.getUnit());
		detail.setUnitName(enterDetail.getUnitName());
		detail.setEnterBillNo(enterDetail.getEnterBillNo());
		detail.setEnterBillDetailNo(enterDetail.getEnterBillDetailNo());
		detail.setEnterBillType(enterDetail.getEnterType());
		detail.setEnterBillTypeName(enterDetail.getEnterTypeName());
		detail.setEnterDate(enterDetail.getEnterDate());
		detail.setEnterTime(enterDetail.getEnterTime());
		detail.setOriginBillNo(enterDetail.getEnterBillNo());
		detail.setOriginBillType(enterDetail.getEnterTypeName());
		detail.setEnterNum(enterDetail.getEnterNum());
		detail.setEnterPrice(enterDetail.getEnterPrice());
		detail.setEnterAmount(enterDetail.getEnterAmount());
		String batchNo = "BCN" + DateUtils.curDateTimeStr14();
		detail.setBatchNo(batchNo);
		//保质期
		detail.setShelfLife(enterDetail.getShelfLife());
		//根据保质期生成到期时间
		Date date = DateUtils.addDays(DateUtils.curDate(), Integer.parseInt(enterDetail.getShelfLife()));
		detail.setExpirationDate(DateUtils.toDateStr(date));
		return detail;
	}

	/**
	 * map转换成出库主单据
	 * @Title: mapToOut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param attr
	 * @param:  @return
	 * @return: CiOut  
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static CiOut mapToOut(Map attr) {
		//获取当前登录人信息
		Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
		if(userInfo.get("workNo")==null || userInfo.get("workNo").toString().equals("")){
			//兼容app端用户上传
			userInfo = CiUtils.getUserInfo(attr.get("recCreator")+"");
		}
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    //属性赋值
	    CiOut out = new CiOut();
	    out.fromMap(attr);
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    String outBillNo = "OW" + DateUtils.curDateTimeStr14();
	    out.setOutBillNo(outBillNo);
	    out.setOutTypeName(CommonUtils.getDataCodeItemName("wilp.ci.outType", out.getOutType()));
	    //红冲出库
  		if("05".equals(out.getOutType())){
  			out.setOriginBillNo(attr.get("outBillNo").toString());
  		}
	    out.setBillMaker(workNo);
	    out.setBillMakerName(name);
	    out.setBillMakeTime(DateUtils.curDateTimeStr19());
	    out.setIsCheck(0);
		return out;
	}

	/**
	 * map转换成出库明细
	 * @Title: mapToOutDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param pMap
	 * @param:  @param out
	 * @param:  @return
	 * @return: CiOutDetail  
	 * @throws
	 */
	public static CiOutDetail mapToOutDetail(Map<String, String> pMap, CiOut out) {
		CiOutDetail detail = new CiOutDetail();
		detail.fromMap(pMap);
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
        detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(detailBillNo);
        detail.setOutType(out.getOutType());
        detail.setOutTypeName(out.getOutTypeName());
        if("05".equals(out.getOutType())){
        	detail.setOutNum(Double.parseDouble(StringUtils.isBlank(pMap.get("redRushNum")) ? "0" : pMap.get("redRushNum")));
        }
        detail.setOutDate(DateUtils.curDateStr10());
        detail.setOutTime(DateUtils.curDateTimeStr19());
		return detail;
	}

	/**
	 * 将库存明细转换成库存
	 * @Title: storgeDetailToStorge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param outDetail ： 出库
	 * @param:  @param totalAmount
	 * @param:  @param outNum
	 * @param:  @return
	 * @return: CiStorge  
	 * @throws
	 */
	public static CiStorge storgeDetailToStorge(CiStorgeDetail storgeDetail, Double totalAmount, Double outNum) {
		CiStorge storge = new CiStorge();
		storge.setRecRevisor(UserSession.getUser().getUsername());
		storge.setRecReviseTime(DateUtils.curDateTimeStr19());
		storge.setWareHouseNo(storgeDetail.getWareHouseNo());
		storge.setMatNum(storgeDetail.getMatNum());
		storge.setTotalNum(outNum);
		storge.setTotalAmount(totalAmount);
		return storge;
	}

	/**
	 * 将库存明细转换成库存操作履历
	 * @Title: storgeDetailToStorgeRecord
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param storgeDetail
	 * @param:  @return
	 * @return: CiStorgeRecord  
	 * @throws
	 */
	public static CiStorgeRecord storgeDetailToStorgeRecord(CiStorgeDetail storgeDetail, Double beforeNum, Double afterNum) {
		CiStorgeRecord record = new CiStorgeRecord();
		record.fromMap(storgeDetail.toMap());
		//属性赋值
		record.setId(UUID.randomUUID().toString());
		record.setOriginBillNo(storgeDetail.getEnterBillNo());
		record.setOriginBillType(storgeDetail.getEnterBillType());
		record.setOriginBillTypeName(storgeDetail.getEnterBillTypeName());
		record.setPrice(storgeDetail.getEnterPrice());
		record.setBeforeNum(beforeNum);
		record.setBeforeAmount(CiUtils.cal(beforeNum, storgeDetail.getEnterPrice(), "multiply"));
		record.setAfterNum(afterNum);
		record.setAfterAmount(CiUtils.cal(afterNum, storgeDetail.getEnterPrice(), "multiply"));
		record.setRecCreateTime(DateUtils.curDateTimeStr19());
		return record;
	}

	/**
	 * 将库存明细转换成库存操作履历
	 * @Title: storgeDetailToStorgeRecord
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param storgeDetail
	 * @param:  @return
	 * @return: CiStorgeRecord  
	 * @throws
	 */
	public static CiStorgeRecord storgeDetailToStorgeRecord(CiStorgeDetail storgeDetail, Double beforeNum, double afterNum,
															CiOutDetail outDetail) {
		CiStorgeRecord record = new CiStorgeRecord();
		record.fromMap(storgeDetail.toMap());
		//属性赋值
		record.setId(UUID.randomUUID().toString());
		record.setOriginBillNo(outDetail.getOutBillNo());
		record.setOriginBillType(outDetail.getOutType());
		record.setOriginBillTypeName(outDetail.getOutTypeName());
		record.setPrice(storgeDetail.getEnterPrice());
		record.setBeforeNum(beforeNum);
		record.setBeforeAmount(CiUtils.cal(beforeNum, storgeDetail.getEnterPrice(), "multiply"));
		record.setAfterNum(afterNum);
		record.setAfterAmount(CiUtils.cal(afterNum, storgeDetail.getEnterPrice(), "multiply"));
		record.setRecCreateTime(DateUtils.curDateTimeStr19());
		record.setShelfLife(outDetail.getShelfLife());
		record.setExpirationDate(outDetail.getExpirationDate());
		return record;
	}

	/**
	 * 将库存履历转换成库存明细
	 * @Title: storgeRecordToStorgeDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param record
	 * @param:  @param redRushNum
	 * @param:  @return
	 * @return: CiStorgeDetail  
	 * @throws
	 */
	public static CiStorgeDetail storgeRecordToStorgeDetail(CiStorgeRecord record, Double redRushNum) {
		CiStorgeDetail detail = new CiStorgeDetail();
		detail.fromMap(record.toMap());
		//属性赋值
		detail.setId(null);
		detail.setEnterNum(redRushNum);
		detail.setEnterAmount(CiUtils.cal(record.getPrice(), redRushNum, "multiply"));
		return detail;
	}

	/**
	 * 将库存履历对象转换成库存主表对象
	 * @Title: storgeRecordToStorge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param record
	 * @param:  @param enter
	 * @param:  @param numMap
	 * @param:  @return
	 * @return: CiStorge  
	 * @throws
	 */
	public static CiStorge storgeRecordToStorge(CiStorgeRecord record, CiEnter enter, Map<String, Double[]> numMap) {
		if(numMap.isEmpty() || !numMap.containsKey(record.getMatNum())){
			//获取当前登录人信息
			Map<String, Object> userInfo = CiUtils.getUserInfo(UserSession.getUser().getUsername());
			String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
		    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
		    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		    //属性赋值
		    CiStorge storge = new CiStorge();
			storge.fromMap(record.toMap());
		    storge.setRecCreator(workNo);
		    storge.setRecCreateTime(DateUtils.curDateTimeStr19());
		    storge.setRecRevisor(workNo);
			storge.setRecReviseTime(DateUtils.curDateTimeStr19());
		    storge.setDataGroupCode(dataGroupCode);
		    storge.setId(UUID.randomUUID().toString());
		    storge.setWareHouseNo(enter.getWareHouseNo());
		    storge.setWareHouseName(enter.getWareHouseName());
		    storge.setTotalNum(record.getBeforeNum()-record.getAfterNum());
		    storge.setTotalAmount(record.getBeforeAmount()-record.getAfterAmount());
			storge.setEnterTypeName(enter.getEnterTypeName());
			storge.setBillMakerName(name);
			storge.setShelfLife(record.getShelfLife());
			storge.setExpirationDate(record.getExpirationDate());
			//多数据累加
			numMap.put(storge.getMatNum(),new Double[]{storge.getTotalNum(),storge.getTotalAmount()});
			return storge;
		} else {
			Double[] doubles = numMap.get(record.getMatNum());
			Double num = doubles[0] + record.getBeforeNum()-record.getAfterNum();
			Double amount = doubles[1] + record.getBeforeAmount()-record.getAfterAmount();
			numMap.put(record.getMatNum(),new Double[]{num,amount});
			return null;
		}
		
	}

	/**
	 * 将库存履历对象转换成库存明细对象
	 * @Title: storgeRecordToStorgeDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param record
	 * @param:  @param enter
	 * @param:  @return
	 * @return: CiStorgeDetail  
	 * @throws
	 */
	public static CiStorgeDetail storgeRecordToStorgeDetail(CiStorgeRecord record, CiEnter enter) {
		CiStorgeDetail detail = new CiStorgeDetail();
		detail.fromMap(record.toMap());
		//属性赋值
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
		detail.setWareHouseNo(enter.getWareHouseNo());
		detail.setWareHouseName(enter.getWareHouseName());
		detail.setEnterBillNo(enter.getEnterBillNo());
		detail.setEnterBillDetailNo("");
		detail.setEnterBillType(enter.getEnterType());
		detail.setEnterBillTypeName(enter.getEnterTypeName());
		detail.setEnterDate(DateUtils.curDateStr10());
		detail.setEnterTime(DateUtils.curDateTimeStr19());
		detail.setOriginBillNo(enter.getEnterBillNo());
		detail.setOriginBillType(enter.getEnterTypeName());
		detail.setEnterNum(record.getBeforeNum()-record.getAfterNum());
		detail.setEnterPrice(record.getPrice());
		detail.setEnterAmount(record.getBeforeAmount()-record.getAfterAmount());
		String batchNo = "BCN" + DateUtils.curDateTimeStr14();
		detail.setBatchNo(batchNo);
		detail.setShelfLife(record.getShelfLife());
		detail.setExpirationDate(record.getExpirationDate());
		return detail;
	}

}
