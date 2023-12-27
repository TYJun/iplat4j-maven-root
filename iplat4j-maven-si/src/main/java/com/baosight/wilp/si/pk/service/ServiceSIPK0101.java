package com.baosight.wilp.si.pk.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOut;
import com.baosight.wilp.si.ck.domain.SiOutDetail;
import com.baosight.wilp.si.common.BeanExchangeUtils;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.pk.domain.SiInven;
import com.baosight.wilp.si.pk.domain.SiInvenDetail;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import com.baosight.xservices.xs.util.UserSession;

/**
 * 盘库子页面Service
 *
 * <p>页面加载</p>
 * <p>插入盘库明细数据</p>
 * <p>维护盘库信息</p>
 * <p>审核确认盘库信息</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIPK0101.java
 * @ClassName:  ServiceSIPK0101
 * @Package com.baosight.wilp.si.pk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午6:38:50
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIPK0101 extends ServiceBase {

	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
    }

	/**
	 * 页面加载查询.
	 *
	 * @Title: query
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo query(EiInfo inInfo) {
		//查询盘库明细
		Map<String, String> pMap = new HashMap<>(2);
		pMap.put("invenBillNo", inInfo.getString("invenBillNo"));
		List<SiInvenDetail> list = dao.query("SIPK0101.query", pMap);
		inInfo.setRows(EiConstant.resultBlock, list);
		if (inInfo.getBlock(EiConstant.resultBlock).getAttr() != null){
			EiInfo info = new EiInfo();
			info.setRows(EiConstant.resultBlock, list);
			return info;
		}else {
			//返回
			return inInfo;
		}
	}

	/**
	 * 插入盘库明细数据
	 *
	 * <p>插入盘库明细，invenDetail不为空时，插入单条盘库明细；invenDetailList不为空时，插入多条盘库明细</p>
	 *
	 * @Title: insertInvenDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		invenDetail: 盘库明细对象
	 * 		invenDetailList: 盘库明细对象集合
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo insertInvenDetail(EiInfo inInfo) {
		//获取参数
		SiInvenDetail invenDetail = inInfo.get("invenDetail") == null ? null : (SiInvenDetail)inInfo.get("invenDetail");
		List<SiInvenDetail> invenDetailList = inInfo.get("invenDetailList") == null ? null : (List<SiInvenDetail>)inInfo.get("invenDetailList");
		//判断参数是否为空
		if(invenDetail == null && invenDetailList==null){
			return inInfo;
		} else if (invenDetail!=null){//单个插入
			dao.insert("SIPK0101.insert", invenDetail);
		} else if (invenDetailList !=null) {//list插入
			for (SiInvenDetail siInvenDetail : invenDetailList) {
				dao.insert("SIPK0101.insert", siInvenDetail);
			}
		}
        return inInfo;
    }

	/**
	 * 维护盘库信息
	 *
	 * <p>1.获取参数</br>
	 * 	  2.遍历参数中list,将参数转成盘库明细对象</br>
	 *    3.计算盘库后总价</br>
	 *    4.更新盘库明细</br>
	 *    5.调用本地服务更新盘库主表信息
	 * </p>
	 *
	 * @Title: updateStockInven
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		invenBillNo ： 盘库单号
	 * 		rows：物资集合
	 * 			id : 主键
	 *			invenBillDetailNo : 盘点明细号
	 *			matNum : 物资编码
	 *			matName : 物资名称
	 *			matSpec : 物资规格
	 *			unitName : 计量单位
	 *			beforeInvenNum : 盘点前数量
	 *			afterInvenNum : 盘点后数量
	 *			price : 盘点单价
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo updateStockInven(EiInfo inInfo) {
		//获取参数
		String invenBillNo = inInfo.getString("invenBillNo");
        List<HashMap<String, String>> plist = (List<HashMap<String, String>>)inInfo.get("rows");
        //将Map转成盘库明细对象,更新库存明细
        plist.forEach(map -> {
        	SiInvenDetail detail = new SiInvenDetail();
        	detail.fromMap(map);
        	detail.setInvenBillNo(invenBillNo);
        	detail.setInvenDate(DateUtils.curDateStr10());
        	detail.setInvenTime(DateUtils.curDateTimeStr19());
        	//计算盘库后总价(盘库前总价+(盘库后数量-盘库前数量)*盘库单价)
        	BigDecimal afterInvenAmount = new BigDecimal(detail.getPrice())
        			.multiply(new BigDecimal(detail.getAfterInvenNum()).subtract(new BigDecimal(detail.getBeforeInvenNum())))
					.setScale(2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(detail.getBeforeInvenAmount()));
        	detail.setAfterInvenAmount(afterInvenAmount.doubleValue());
        	detail.setRecRevisor(UserSession.getUser().getUsername());
        	detail.setRecReviseTime(DateUtils.curDateTimeStr19());
        	dao.update("SIPK0101.update", detail);
        });
        //更新盘库主表
        SiInven inven = new SiInven();
        inven.setId(null);
        inven.setInvenBillNo(invenBillNo);
        inven.setInvenStatus("1");
		inven.setRecRevisor(UserSession.getUser().getUsername());
		inven.setRecReviseTime(DateUtils.curDateTimeStr19());
        //调用本地服务更新盘库主表信息
        SiUtils.invoke(null, "SIPK01", "updateInven", new String[]{"inven"}, inven);
        inInfo.setMsg("盘库信息维护成功");
        return inInfo;
	}

	/**
	 * 审核确认盘库信息
	 *
	 * <p>1.获取参数,通过参数获取盘库明细和获取盘库主单据</br>
	 * 	  2.构建盘盈入库主表对象、入库明细对象集合与盘亏出库主表对象、出库明细对象集合</br>
	 * 	  3.遍历盘库明细</br>
	 *    4.判断盘库前数量是否小于盘库后数量。是，盘盈入库，构建入库明细对象，添加到入库明细对象集合</br>
	 *    5.否，盘亏出库，构建出入明细对象，添加到出库明细对象集合</br>
	 *    6.调用本地服务，保存出入库数据</br>
	 *    7.调用本地服务更新盘库主表信息
	 * </p>
	 *
	 * @Title: approveStockInven
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		invenBillNo ： 盘库单号
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo approveStockInven(EiInfo inInfo) {
		//获取参数
		String invenBillNo = inInfo.getString("invenBillNo");
		//获取盘库明细
		Map<String, String> pMap = new HashMap<>();
		pMap.put("invenBillNo", invenBillNo);
		List<SiInvenDetail> invenDetailList = dao.query("SIPK0101.query", pMap);
		//获取盘库主单据
		List<SiInven> invenList = dao.query("SIPK01.query", pMap);
		SiInven inven = invenList.get(0);
		//解冻仓库
	    EiInfo outInfo = SiUtils.invoke(null, "SIWH01", "djOrJd", new String[]{"type","wareHouseNo"}, "jd",inven.getWareHouseNo());
		if(SiUtils.dealOutInfo(inInfo, outInfo, "解冻仓库失败")){
			return inInfo;
		}
	    //构建盘盈入库主表对象、入库明细对象集合
		SiEnter enter = BeanExchangeUtils.invenToEnter(inven);
		List<SiEnterDetail> enterDetailList = new ArrayList<>();
		//构建盘亏出库主表对象、出库明细对象集合
		SiOut out = BeanExchangeUtils.invenToOut(inven);
		List<SiOutDetail> outDetailList = new ArrayList<>();
		//遍历盘库明细
		invenDetailList.forEach(invenDetail ->{
			if(invenDetail.getBeforeInvenNum() < invenDetail.getAfterInvenNum()){ //盘盈、入库
				SiEnterDetail enterDetail = BeanExchangeUtils.invenDetailToEnterDetail(invenDetail, enter);
				enterDetailList.add(enterDetail);
			} else if(invenDetail.getBeforeInvenNum() > invenDetail.getAfterInvenNum()) { //盘亏、出库
				SiOutDetail outDetail = BeanExchangeUtils.invenDetailToOutDetail(invenDetail, out);
				outDetailList.add(outDetail);
			}
		});
		EiInfo info = new EiInfo();
		if(enterDetailList.size() > 0){
			//调用本地服务入库
			outInfo = SiUtils.invoke(info, "SIRK0101", "enterStock", new String[]{"enter","enterDetailList"}, enter, enterDetailList);
			if(SiUtils.dealOutInfo(inInfo, outInfo, "入库失败")){
				return inInfo;
			}
		}
		if(outDetailList.size() > 0){
			//调用本地服务出库
			outInfo = SiUtils.invoke(info, "SICK0101", "outStock", new String[]{"out","outDetailList"}, out, outDetailList);
			if(SiUtils.dealOutInfo(inInfo, outInfo, "出库失败")){
				return inInfo;
			}
		}
		//调用本地服务更新盘库主表信息
        inven.setInvenStatus("2");
        inven.setRecRevisor(UserSession.getUser().getUsername());
        inven.setRecReviseTime(DateUtils.curDateTimeStr19());
        Map<String, Object> userInfo = SiUtils.getUserInfo(UserSession.getUser().getUsername());
        inven.setBillMaker(userInfo.get("workNo") == null ? "" : userInfo.get("workNo").toString());
        inven.setBillCheckerName(userInfo.get("name") == null ? "" : userInfo.get("name").toString());
        inven.setBillCheckTime(DateUtils.curDateTimeStr19());
        SiUtils.invoke(null, "SIPK01", "updateInven", new String[]{"inven"}, inven);
        inInfo.setMsg("审核成功");
		return inInfo;
	}

}
