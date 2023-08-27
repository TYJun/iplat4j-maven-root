package com.baosight.wilp.si.pk.service;

import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.*;
import com.baosight.wilp.si.pk.domain.SiInven;
import com.baosight.wilp.si.rk.domain.EnterTypeEnum;
import com.baosight.wilp.si.rk.domain.SiEnter;
import com.baosight.wilp.si.rk.domain.SiEnterDetail;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 新增盘库物资子界面
 *
 * @Title: ServiceSIPK010101
 * @ClassName: ServiceSIPK010101.java
 * @Package：com.baosight.wilp.si.pk.service
 * @author: fangzekai
 * @date: 2023/5/28 18:59
 * @version: V1.0
 * @Copyright: 2023 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceSIPK010101 extends ServiceBase {
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
        return query(inInfo);
    }

	/**
	 * 页面加载查询
	 * <p>Title: query</p>
	 * 进行页面点击查询
	 * @param inInfo
	 * @return EiInfo
	 */
	public EiInfo query(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 新增盘库物资
	 * 1.构建前端查询相关参数。
	 * 2.查询将要查询的数据列数量，从而进行query 1000条数据的突破。
	 * 3.调用SIPK0101.queryMatInformationByInven 查询在该盘点仓库中无库存的物资。
	 * 4.将物资信息进行数据构建回显。
	 *
	 * @Title: queryMatInformationByInven
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo queryMatInformationByInven(EiInfo inInfo) {
		/*
		 * 1.构建前端查询相关参数。
		 */
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "mat", Arrays.asList(new String[]{"matNum", "matName","matTypeNum","invenBillNo"}));
		map.put("projectSchema", PlatApplicationContext.getProperty("projectSchema"));
		map.put("platSchema", PlatApplicationContext.getProperty("platSchema"));
		/*
		 * 2.查询将要查询的数据列数量，从而进行query 1000条数据的突破。
		 */
		int count = dao.count("SIPK0101.queryMatInformationByInven",map);
		SqlMapDao sqlmapDao = (SqlMapDao) this.dao;
		sqlmapDao.setMaxQueryCount(count);
		/*
		 * 3.调用SIPK0101.queryMatInformationByInven 查询在该盘点仓库中无库存的物资。
		 */
		List<Map<String,Object>> list = sqlmapDao.query("SIPK0101.queryMatInformationByInven", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
		// 判断是否存在，存在则构建返回对象
		/*
		 * 4.将物资信息进行数据构建回显。
		 */
		if(list != null && list.size() > 0){
			return CommonUtils.BuildOutEiInfo(inInfo, "mat", CommonUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			inInfo.setMsg("没有查询到物资信息");
			return inInfo;
		}
	}

	/**
	 * 1.获取前端传来的相关数据。
	 * 2.更新盘库主表,主要登记更新的盘库物资的修改人和修改时间。
	 * 3.更新盘库明细表，对新增的盘库物资更新进盘存明细表中。
	 *
	 * @Title: insertPKInvenDetail
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo insertPKInvenDetail(EiInfo inInfo) {
		/*
		 * 1.获取前端传来的相关数据。
		 */
		String invenBillNo = inInfo.getString("invenBillNo");
		/*
		 * 2.更新盘库主表,主要登记更新的盘库物资的修改人和修改时间。
		 */
		SiInven inven = new SiInven();
		inven.setId(null);
		inven.setInvenBillNo(invenBillNo);
		inven.setInvenStatus("0");
		inven.setRecRevisor(UserSession.getUser().getUsername());
		inven.setRecReviseTime(DateUtils.curDateTimeStr19());
		//调用本地服务更新盘库主表信息
		EiInfo info = new EiInfo();
		info.set("inven", inven);
		info.set(EiConstant.serviceName, "SIPK01");
		info.set(EiConstant.methodName, "updateInven");
		XLocalManager.call(info);
		/*
		 * 3.更新盘库明细表，对新增的盘库物资更新进盘存明细表中。
		 */
		List<Map<String,Object>> newMatLists = (List<Map<String, Object>>) inInfo.get("rows");
		info.set("newMatLists", newMatLists);
		info.set("invenBillNo", invenBillNo);
		info.set(EiConstant.serviceName, "SIPK010101");
		info.set(EiConstant.methodName, "batchInsertInvenDetail");
		XLocalManager.call(info);
		inInfo.setMsg("新增盘库物资成功");
		return inInfo;
	}

	/**
	 * 更新盘库明细表，对新增的盘库物资更新进盘存明细表中。
	 * 1.获取对应参数值。
	 * 2.对数值进行组装，以便进行盘库明细数据批量插入。
	 * 3.调用SIPK0101.batchInsertInvenDetail进行数据批量插入。
	 *
	 * @Title: batchInsertInvenDetail
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo batchInsertInvenDetail(EiInfo inInfo){
		/*
		 * 1.获取对应参数值。
		 */
		List<Map<String,Object>> newMatLists = (List<Map<String, Object>>)inInfo.get("newMatLists");
		String invenBillNo = inInfo.getString("invenBillNo");
		String invenDate = DateUtils.curDateStr10();
		String invenTime = DateUtils.curDateTimeStr19();
		String recCreator = UserSession.getUser().getUsername();
		/*
		 * 2.对数值进行组装，以便进行盘库明细数据批量插入。
		 */
		for (Map<String,Object> mat : newMatLists){
			mat.put("recCreator",recCreator);
			mat.put("recCreateTime",invenTime);
			mat.put("id",UUID.randomUUID().toString());
			String invenBillDetailNo = SerialNoUtils.generateSerialNo("si_inven_detail", "IWD", DateUtils.DATE8_PATTERN, 6);
			mat.put("invenBillDetailNo",invenBillDetailNo);
			mat.put("invenBillNo",invenBillNo);
			mat.put("afterInvenNum",0);
			mat.put("afterInvenAmount",0);
			mat.put("invenDate",invenDate);
			mat.put("invenTime",invenTime);
		}
		/*
		 * 3.调用SIPK0101.batchInsertInvenDetail进行数据批量插入。
		 */
		dao.insert("SIPK0101.batchInsertInvenDetail",newMatLists);
		inInfo.setMsgKey("200");
		return inInfo;
	}

}
