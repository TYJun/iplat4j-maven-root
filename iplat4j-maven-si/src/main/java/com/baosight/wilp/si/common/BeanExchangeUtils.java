package com.baosight.wilp.si.common;

import java.util.Map;
import java.util.UUID;

import com.baosight.wilp.si.bf.domain.SiScrapDetail;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.db.domain.SiTrans;
import com.baosight.wilp.si.db.domain.SiTransDetail;
import com.baosight.wilp.si.kc.domain.SiStorge;
import com.baosight.wilp.si.kc.domain.SiStorgeDetail;
import com.baosight.wilp.si.kc.domain.SiStorgeRecord;
import com.baosight.wilp.si.pk.domain.SiInven;
import com.baosight.wilp.si.pk.domain.SiInvenDetail;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 实体转换工具类
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  BeanExchangeUtils.java
 * @ClassName:  BeanExchangeUtils
 * @Package com.baosight.wilp.si.common
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
	 * @return: SiInven  
	 * @throws
	 */
	public static SiInven storgeToInven(SiStorge storge) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
	    SiInven inven = new SiInven();
	    inven.setId(UUID.randomUUID().toString());
	    inven.setRecCreator(workNo);
	    inven.setRecCreateTime(DateUtils.curDateTimeStr19());
	    inven.setDataGroupCode(dataGroupCode);
        //String invenBillNo ="IW" + DateUtils.curDateTimeStr14();
		String invenBillNo =SerialNoUtils.generateSerialNo("si_inven", "IW");
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
	 * @return: SiInven  
	 * @throws
	 */
	public static SiInvenDetail storgeToInvenDetail(SiStorge storge, String invenBillNo) {
	    //属性赋值
	    SiInvenDetail invenDetail = new SiInvenDetail();
	    invenDetail.setId(UUID.randomUUID().toString());
	    invenDetail.setRecCreator(UserSession.getUser().getUsername());
	    invenDetail.setRecCreateTime(DateUtils.curDateTimeStr19());
	    invenDetail.setInvenBillNo(invenBillNo);
        //String invenBillDetailNo = "IWD" + DateUtils.curDateTimeStr14();
		String invenBillDetailNo = SerialNoUtils.generateSerialNo("si_inven_detail", "IWD", DateUtils.DATE8_PATTERN, 6);
        invenDetail.setInvenBillDetailNo(invenBillDetailNo);
		invenDetail.setMatTypeNum(storge.getMatTypeNum());
		invenDetail.setMatTypeName(storge.getMatTypeName());
        invenDetail.setMatNum(storge.getMatNum());
        invenDetail.setMatName(storge.getMatName());
        invenDetail.setMatModel(storge.getMatModel());
        invenDetail.setMatSpec(storge.getMatSpec());
        invenDetail.setUnit(storge.getUnit());
        invenDetail.setUnitName(storge.getUnitName());
		invenDetail.setPrice(storge.getPrice());
        invenDetail.setBeforeInvenNum(storge.getTotalNum());
        invenDetail.setBeforeInvenAmount(storge.getTotalAmount());
        invenDetail.setInvenDate(DateUtils.curDateStr10());
        invenDetail.setInvenTime(DateUtils.curDateTimeStr19());
		return invenDetail;
	}

	/**
	 * 将盘库主表实体转换成入库主表实体
	 * @Title: invenToEnter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inven
	 * @param:  @return
	 * @return: SiEnter  
	 * @throws
	 */
	public static SiEnter invenToEnter(SiInven inven) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
		SiEnter enter = new SiEnter();
		enter.setRecCreator(workNo);
		enter.setRecCreateTime(DateUtils.curDateTimeStr19());
		enter.setDataGroupCode(dataGroupCode);
		enter.setId(UUID.randomUUID().toString());
		//String enterBillNo = "EBN" + DateUtils.curDateTimeStr14();
		String enterBillNo = SerialNoUtils.generateSerialNo("si_enter", "EBN");
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
		String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			enter.setEnterTime(DateUtils.curDateTimeStr19());
		}
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
	 * @return: SiOut  
	 * @throws
	 */
	public static SiOut invenToOut(SiInven inven) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
	    SiOut out = new SiOut();
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    //String outBillNo = "OW" + DateUtils.curDateTimeStr14();
		String outBillNo = SerialNoUtils.generateSerialNo("si_out", "OW");
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
	 * @return: SiEnterDetail  
	 * @throws
	 */
	public static SiEnterDetail invenDetailToEnterDetail(SiInvenDetail invenDetail, SiEnter enter) {
		SiEnterDetail detail = new SiEnterDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        //String detailBillNo = "EBDN" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_enter_detail", "EBDN", DateUtils.DATE8_PATTERN, 6);
        detail.setEnterBillNo(enter.getEnterBillNo());
        detail.setEnterBillDetailNo(detailBillNo);
        detail.setEnterType("03");
        detail.setEnterTypeName("盘盈入库");
		detail.setMatTypeNum(invenDetail.getMatTypeNum());
		detail.setMatTypeName(invenDetail.getMatTypeName());
        detail.setMatNum(invenDetail.getMatNum());
        detail.setMatName(invenDetail.getMatName());
        detail.setMatModel(invenDetail.getMatModel());
        detail.setMatSpec(invenDetail.getMatSpec());
        detail.setUnit(invenDetail.getUnit());
        detail.setUnitName(invenDetail.getUnitName());
        detail.setEnterNum(invenDetail.getAfterInvenNum()-invenDetail.getBeforeInvenNum());
        detail.setEnterPrice(invenDetail.getPrice());
        detail.setEnterAmount(invenDetail.getAfterInvenAmount()-invenDetail.getBeforeInvenAmount());
		detail.setBatchNo(enter.getBatchNo());
		String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			detail.setEnterDate(DateUtils.curDateStr10());
			detail.setEnterTime(DateUtils.curDateTimeStr19());
		}
		enter.setTotalNum(enter.getTotalNum() + detail.getEnterNum());
		enter.setTotalAmount(SiUtils.cal(enter.getTotalAmount(), detail.getEnterAmount(), SiConstant.MATH_ADD));
		return detail;
	}

	/**
	 * 盘库明细表实体转换成出库明细表实体
	 * @Title: invenDetailToOutDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param invenDetail
	 * @param:  @param out
	 * @param:  @return
	 * @return: SiOutDetail  
	 * @throws
	 */
	public static SiOutDetail invenDetailToOutDetail(SiInvenDetail invenDetail, SiOut out) {
		SiOutDetail detail = new SiOutDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        //String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
		detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(detailBillNo);
        detail.setOutType("03");
        detail.setOutTypeName("盘亏出库");
		detail.setMatTypeNum(invenDetail.getMatTypeNum());
		detail.setMatTypeName(invenDetail.getMatTypeName());
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
		//out.setTotalNum(out.getTotalNum() + detail.getOutNum());
		//out.setTotalAmount(SiUtils.cal(out.getTotalAmount(), detail.getOutAmount(), "add"));
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
	public static SiTrans mapToTrans(Map<String, Object> map) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
		SiTrans trans = new SiTrans();
		trans.fromMap(map);
		trans.setRecCreator(workNo);
		trans.setRecCreateTime(DateUtils.curDateTimeStr19());
		trans.setDataGroupCode(dataGroupCode);
		trans.setId(UUID.randomUUID().toString());
		trans.setTransBillNo(SerialNoUtils.generateSerialNo("si_trans", "TR"));
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
	 * @return: SiTransDetail  
	 * @throws
	 */
	public static SiTransDetail mapToTransDetail(Map<String, Object> map, SiTrans trans) {
		SiTransDetail detail = new SiTransDetail();
		detail.fromMap(map);
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        //String detailBillNo = "TRD" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_trans_detail", "TRD", DateUtils.DATE8_PATTERN, 6);
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
	 * @return: SiEnter  
	 * @throws
	 */
	public static SiEnter transToEnter(SiTrans trans) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
	    SiEnter enter = new SiEnter();
	    enter.setRecCreator(workNo);
	    enter.setRecCreateTime(DateUtils.curDateTimeStr19());
	    enter.setDataGroupCode(dataGroupCode);
	    enter.setId(UUID.randomUUID().toString());
	    //String enterBillNo = "EBN" + DateUtils.curDateTimeStr14();
		String enterBillNo = SerialNoUtils.generateSerialNo("si_enter", "EBN");
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
		String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			enter.setEnterTime(DateUtils.curDateTimeStr19());
		}
		enter.setBillMaker(workNo);
		enter.setBillMakerName(name);
		enter.setBillMakeTime(DateUtils.curDateTimeStr19());
		enter.setIsCheck(0);
		String batchNo = "BCN" + DateUtils.curDateTimeStr14();
		enter.setBatchNo(batchNo);
		return enter;
	}

	/**
	 * 将调拨主单据转成出库主单据
	 * @Title: transToOut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param trans
	 * @param:  @return
	 * @return: SiOut  
	 * @throws
	 */
	public static SiOut transToOut(SiTrans trans) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
	    SiOut out = new SiOut();
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    //String outBillNo = "OW" + DateUtils.curDateTimeStr14();
		String outBillNo = SerialNoUtils.generateSerialNo("si_out","OW");
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
	 * @return: SiEnterDetail  
	 * @throws
	 */
	public static SiEnterDetail transDetailToEnterDetail(SiTransDetail transDetail, SiEnter enter) {
		SiEnterDetail detail = new SiEnterDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        //String detailBillNo = "EBDN" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_enter_detail", "EBDN", DateUtils.DATE8_PATTERN, 6);
        detail.setEnterBillNo(enter.getEnterBillNo());
        detail.setEnterBillDetailNo(detailBillNo);
        detail.setEnterType("02");
        detail.setEnterTypeName("调拨入库");
		detail.setMatTypeNum(transDetail.getMatTypeNum());
		detail.setMatTypeName(transDetail.getMatTypeName());
        detail.setMatNum(transDetail.getMatNum());
        detail.setMatName(transDetail.getMatName());
        detail.setMatModel(transDetail.getMatModel());
        detail.setMatSpec(transDetail.getMatSpec());
        detail.setUnit(transDetail.getUnit());
        detail.setUnitName(transDetail.getUnitName());
        detail.setEnterNum(transDetail.getTransNum());
        detail.setEnterPrice(transDetail.getPrice());
        detail.setEnterAmount(transDetail.getTransNum()*transDetail.getPrice());
		detail.setBatchNo(enter.getBatchNo());
		String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			detail.setEnterDate(DateUtils.curDateStr10());
			detail.setEnterTime(DateUtils.curDateTimeStr19());
		}
		enter.setTotalNum(enter.getTotalNum() + detail.getEnterNum());
		enter.setTotalAmount(SiUtils.cal(enter.getTotalAmount(), detail.getEnterAmount(), SiConstant.MATH_ADD));
		return detail;
	}

	/**
	 * 将调拨明细转成出库明细
	 * @Title: transDetailToOutDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param detail
	 * @param:  @param out
	 * @param:  @return
	 * @return: SiOutDetail  
	 * @throws
	 */
	public static SiOutDetail transDetailToOutDetail(SiTransDetail transDetail, SiOut out) {
		SiOutDetail detail = new SiOutDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        //String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
        detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(detailBillNo);
        detail.setOutType(SiConstant.OUT_TYPE_DB);
        detail.setOutTypeName("调拨出库");
		detail.setMatTypeNum(transDetail.getMatTypeNum());
		detail.setMatTypeName(transDetail.getMatTypeName());
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
		return detail;
	}

	/**
	 * map转换成入库主单据
	 * @Title: mapToEnter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * @param:  @return
	 * @return: SiEnter  
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static SiEnter mapToEnter(Map map) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		
	    //属性赋值
		SiEnter enter = new SiEnter();
		enter.fromMap(map);
		enter.setRecCreator(workNo);
	    enter.setRecCreateTime(DateUtils.curDateTimeStr19());
	    enter.setDataGroupCode(dataGroupCode);
	    enter.setId(UUID.randomUUID().toString());
	    //String enterBillNo = "EBN" + DateUtils.curDateTimeStr14();
		String enterBillNo = SerialNoUtils.generateSerialNo("si_enter", "EBN");
	    enter.setEnterBillNo(enterBillNo);
		enter.setEnterTypeName(CommonUtils.getDataCodeItemName("wilp.si.enterType", enter.getEnterType()));
		//红冲入库
		if("05".equals(enter.getEnterType())){
			enter.setEnterTime(null);
			enter.setOriginBillNo(map.get("enterBillNo").toString());
			enter.setOriginBillType("05");
			enter.setOriginBillTypeName("红冲");
		}
		String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			enter.setEnterTime(DateUtils.curDateTimeStr19());
		}
		enter.setBillMaker(workNo);
		enter.setBillMakerName(name);
		enter.setBillMakeTime(DateUtils.curDateTimeStr19());
		enter.setIsCheck(0);
		String batchNo = "BCN" + DateUtils.curDateTimeStr14();
		enter.setBatchNo(batchNo);
		return enter;
	}

	/**
	 * map转换成入库明细
	 * @Title: mapToEnterDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param pMap
	 * @param:  @param enter 
	 * @param:  @return
	 * @return: SiEnterDetail  
	 * @throws
	 */
	public static SiEnterDetail mapToEnterDetail(Map<String, String> pMap, SiEnter enter) {
		SiEnterDetail detail = new SiEnterDetail();
		detail.fromMap(pMap);
		
		//属性赋值
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
		String detailBillNo = SerialNoUtils.generateSerialNo("si_enter_detail", "EBDN", DateUtils.DATE8_PATTERN, 6);
        detail.setEnterBillNo(enter.getEnterBillNo());
        detail.setEnterBillDetailNo(detailBillNo);
		detail.setBatchNo(enter.getBatchNo());
        if("05".equals(enter.getEnterType())){//红冲入库
        	detail.setEnterNum(Double.parseDouble(StringUtils.isBlank(pMap.get("redRushNum")) ? "0" : pMap.get("redRushNum")));
			detail.setEnterDate(null);
			detail.setEnterTime(null);
        }
        detail.setEnterType(enter.getEnterType());
        detail.setEnterTypeName(enter.getEnterTypeName());
		detail.setEnterAmount(detail.getEnterNum()*detail.getEnterPrice());
		String hasCheck = SiConfigCache.getConfigRadioValue(enter.getDataGroupCode().trim(), SiConfigCache.SI_CONFIG_ENTER_CHECK);
		if(!SiUtils.toBoolean(hasCheck)) {
			detail.setEnterDate(DateUtils.curDateStr10());
			detail.setEnterTime(DateUtils.curDateTimeStr19());
		}
		enter.setTotalNum(enter.getTotalNum() + detail.getEnterNum());
		enter.setTotalAmount(SiUtils.cal(enter.getTotalAmount(), detail.getEnterAmount(), SiConstant.MATH_ADD));
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
	 * @return: SiOut  
	 * @throws
	 */
	public static SiOut enterToOut(SiEnter enter, String userDeptNum, String userDeptName) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    
	    //属性赋值
	    SiOut out = new SiOut();
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    //String outBillNo = "OW" + DateUtils.curDateTimeStr14();
		String outBillNo = SerialNoUtils.generateSerialNo("si_out","OW");
	    out.setOutBillNo(outBillNo);
	    out.setOutType("01");
	    out.setOutTypeName("直入直出");
		out.setUserDeptNum(userDeptNum);
		out.setUserDeptName(userDeptName);
		out.setCostDeptNum(userDeptNum);
		out.setCostDeptName(userDeptName);
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
	 * @return: SiOutDetail  
	 * @throws
	 */
	public static SiOutDetail enterDetailToOutDetail(SiEnterDetail enterDetail, SiOut out) {
		SiOutDetail detail = new SiOutDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        //String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
        detail.setOutBillNo(out.getOutBillNo());
        detail.setOutBillDetailNo(detailBillNo);
        detail.setOutType("01");
        detail.setOutTypeName("直入直出");
        detail.setMatNum(enterDetail.getMatNum());
        detail.setMatName(enterDetail.getMatName());
		detail.setMatTypeNum(enterDetail.getMatTypeNum());
		detail.setMatTypeName(enterDetail.getMatTypeName());
        detail.setMatModel(enterDetail.getMatModel());
        detail.setMatSpec(enterDetail.getMatSpec());
        detail.setUnit(enterDetail.getUnit());
        detail.setUnitName(enterDetail.getUnitName());
        detail.setOutNum(enterDetail.getEnterNum());
        detail.setOutPrice(enterDetail.getEnterPrice());
        detail.setOutAmount(enterDetail.getEnterAmount());
        detail.setOutDate(DateUtils.curDateStr10());
        detail.setOutTime(DateUtils.curDateTimeStr19());
		detail.setSortNo(enterDetail.getSortNo());
		detail.setBatchNo(enterDetail.getBatchNo());
		return detail;
	}

	/**
	 * 将入库明细转换成库存主单据
	 * @Title: enterDetailToStorge
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param enter
	 * @param:  @param enterDetail
	 * @return: SiStorge  
	 * @throws
	 */
	public static SiStorge enterDetailToStorge(SiEnter enter, SiEnterDetail enterDetail) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    //属性赋值
	    SiStorge storge = new SiStorge();
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
	    /*Map<String,String> map = SiUtils.getMatByMatNum(enterDetail.getMatNum());
	    storge.setMatTypeNum(map.get("matClassCode"));
	    storge.setMatTypeName(map.get("matClassName"));*/
		storge.setMatTypeNum(enterDetail.getMatTypeNum());
		storge.setMatTypeName(enterDetail.getMatTypeName());
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
	    return storge;
	}

	/**
	 * 将入库明细转换成库存明细
	 * @Title: enterDetailToStorgeDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param enterDetail
	 * @param:  @param storge
	 * @param:  @return
	 * @return: SiStorgeDetail  
	 * @throws
	 */
	public static SiStorgeDetail enterDetailToStorgeDetail(SiEnterDetail enterDetail, SiStorge storge) {
		SiStorgeDetail detail = new SiStorgeDetail();
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
		detail.setSurpNum(enterDetail.getSurpNum());
		detail.setSurpName(enterDetail.getSurpName());
		return detail;
	}

	/**
	 * map转换成出库主单据
	 * @Title: mapToOut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param attr
	 * @param:  @return
	 * @return: SiOut  
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static SiOut mapToOut(Map attr) {
		//获取当前登录人信息
		Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
		String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
	    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
	    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
	    //属性赋值
	    SiOut out = new SiOut();
	    out.fromMap(attr);
	    out.setRecCreator(workNo);
	    out.setRecCreateTime(DateUtils.curDateTimeStr19());
	    out.setDataGroupCode(dataGroupCode);
	    out.setId(UUID.randomUUID().toString());
	    //String outBillNo = "OW" + DateUtils.curDateTimeStr14();
		String outBillNo = SerialNoUtils.generateSerialNo("si_out", "OW");
		out.setOutBillNo(outBillNo);
	    out.setOutTypeName(CommonUtils.getDataCodeItemName("wilp.si.outType", out.getOutType()));
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
	 * @return: SiOutDetail  
	 * @throws
	 */
	public static SiOutDetail mapToOutDetail(Map<String, String> pMap, SiOut out) {
		SiOutDetail detail = new SiOutDetail();
		detail.fromMap(pMap);
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
        //String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_enter_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
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
	 * @return: SiStorge  
	 * @throws
	 */
	public static SiStorge storgeDetailToStorge(SiStorgeDetail storgeDetail, Double totalAmount, Double outNum) {
		SiStorge storge = new SiStorge();
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
	 * @return: SiStorgeRecord  
	 * @throws
	 */
	public static SiStorgeRecord storgeDetailToStorgeRecord(SiStorgeDetail storgeDetail, Double beforeNum, Double afterNum) {
		SiStorgeRecord record = new SiStorgeRecord();
		record.fromMap(storgeDetail.toMap());
		//属性赋值
		record.setId(UUID.randomUUID().toString());
		record.setOriginBillNo(storgeDetail.getEnterBillNo());
		record.setOriginBillType(storgeDetail.getEnterBillType());
		record.setOriginBillTypeName(storgeDetail.getEnterBillTypeName());
		record.setPrice(storgeDetail.getEnterPrice());
		record.setBeforeNum(beforeNum);
		record.setBeforeAmount(SiUtils.cal(beforeNum, storgeDetail.getEnterPrice(), "multiply"));
		record.setAfterNum(afterNum);
		record.setAfterAmount(SiUtils.cal(afterNum, storgeDetail.getEnterPrice(), "multiply"));
		record.setRecCreateTime(DateUtils.curDateTimeStr19());
		return record;
	}

	/**
	 * 将库存明细转换成库存操作履历
	 * @Title: storgeDetailToStorgeRecord
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param storgeDetail
	 * @param:  @return
	 * @return: SiStorgeRecord  
	 * @throws
	 */
	public static SiStorgeRecord storgeDetailToStorgeRecord(SiStorgeDetail storgeDetail, Double beforeNum, double afterNum,
			SiOutDetail outDetail) {
		SiStorgeRecord record = new SiStorgeRecord();
		record.fromMap(storgeDetail.toMap());
		//属性赋值
		record.setId(UUID.randomUUID().toString());
		record.setOriginBillNo(outDetail.getOutBillNo());
		record.setOriginBillType(outDetail.getOutType());
		record.setOriginBillTypeName(outDetail.getOutTypeName());
		record.setPrice(storgeDetail.getEnterPrice());
		record.setBeforeNum(beforeNum);
		record.setBeforeAmount(storgeDetail.getEnterAmount());
		//record.setBeforeAmount(SiUtils.cal(beforeNum, storgeDetail.getEnterPrice(), "multiply"));
		record.setAfterNum(afterNum);
		if(afterNum > 0) {
			Double outAmount = SiUtils.cal(SiUtils.cal(beforeNum, afterNum, SiConstant.MATH_SUB), storgeDetail.getEnterPrice(), "multiply");
			record.setAfterAmount(SiUtils.cal(record.getBeforeAmount(),outAmount, SiConstant.MATH_SUB));
		} else {
			record.setAfterAmount(0d);
		}
		record.setRecCreateTime(DateUtils.curDateTimeStr19());
		return record;
	}

	/**
	 * 将库存履历转换成库存明细
	 * @Title: storgeRecordToStorgeDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param record
	 * @param:  @param redRushNum
	 * @param:  @return
	 * @return: SiStorgeDetail  
	 * @throws
	 */
	public static SiStorgeDetail storgeRecordToStorgeDetail(SiStorgeRecord record, Double redRushNum) {
		SiStorgeDetail detail = new SiStorgeDetail();
		detail.fromMap(record.toMap());
		//属性赋值
		detail.setId(null);
		detail.setEnterNum(redRushNum);
		detail.setEnterAmount(SiUtils.cal(record.getPrice(), redRushNum, "multiply"));
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
	 * @return: SiStorge  
	 * @throws
	 */
	public static SiStorge storgeRecordToStorge(SiStorgeRecord record, SiEnter enter, Map<String, Double[]> numMap) {
		if(numMap.isEmpty() || !numMap.containsKey(record.getMatNum())){
			//获取当前登录人信息
			Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
			String workNo = userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString();
		    String name = userInfo.get("name") == null ? "" : userInfo.get("name").toString();
		    String dataGroupCode = userInfo.get("dataGroupCode") == null ? "" : userInfo.get("dataGroupCode").toString();
		    //属性赋值
		    SiStorge storge = new SiStorge();
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
	 * @return: SiStorgeDetail  
	 * @throws
	 */
	public static SiStorgeDetail storgeRecordToStorgeDetail(SiStorgeRecord record, SiEnter enter) {
		SiStorgeDetail detail = new SiStorgeDetail();
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
		return detail;
	}

	/**
	 * 将报废明细转换成出库明细
	 * @Title: scrapDetailToOutDetail
	 * @param siScrapDetail siScrapDetail : 报废明细
	 * @param out out : 出库单
	 * @return com.baosight.wilp.si.ck.domain.SiOutDetail
	 * @throws
	 **/
	public static SiOutDetail scrapDetailToOutDetail(SiScrapDetail siScrapDetail, SiOut out) {
		SiOutDetail detail = new SiOutDetail();
		detail.setRecCreator(UserSession.getUser().getUsername());
		detail.setRecCreateTime(DateUtils.curDateTimeStr19());
		detail.setId(UUID.randomUUID().toString());
		//String detailBillNo = "OBDN" + DateUtils.curDateTimeStr14();
		String detailBillNo = SerialNoUtils.generateSerialNo("si_out_detail", "OBDN", DateUtils.DATE8_PATTERN, 6);
		detail.setOutBillNo(out.getOutBillNo());
		detail.setOutBillDetailNo(detailBillNo);
		detail.setOutType(out.getOutType());
		detail.setOutTypeName(out.getOutTypeName());
		detail.setMatNum(siScrapDetail.getMatNum());
		detail.setMatName(siScrapDetail.getMatName());
		detail.setMatTypeNum(siScrapDetail.getMatTypeNum());
		detail.setMatTypeName(siScrapDetail.getMatTypeName());
		detail.setMatModel(siScrapDetail.getMatModel());
		detail.setMatSpec(siScrapDetail.getMatSpec());
		detail.setUnit(siScrapDetail.getUnit());
		detail.setUnitName(siScrapDetail.getUnitName());
		detail.setOutNum(siScrapDetail.getScrapNum());
		detail.setOutPrice(siScrapDetail.getEnterPrice());
		detail.setOutAmount(siScrapDetail.getScrapAmount().doubleValue());
		detail.setOutDate(DateUtils.curDateStr10());
		detail.setOutTime(DateUtils.curDateTimeStr19());
		return detail;
	}
}
