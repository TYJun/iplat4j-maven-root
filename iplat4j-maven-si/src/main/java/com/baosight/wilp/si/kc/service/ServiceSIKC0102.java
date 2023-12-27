package com.baosight.wilp.si.kc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.si.common.BeanExchangeUtils;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.kc.domain.SiStorge;
import com.baosight.wilp.si.pk.domain.SiInven;
import com.baosight.wilp.si.pk.domain.SiInvenDetail;

/**
 * 库存存量管理-生成盘库单子子页面Service
 *
 * <p>页面加载</p>
 * <p>生成盘库单</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIKC0102.java
 * @ClassName:  ServiceSIKC0102
 * @Package com.baosight.wilp.si.kc.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午6:22:21
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIKC0102 extends ServiceBase {

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
	 * 生成盘库单
	 *
	 * <p>1.获取指定仓库、指定分类的物资库存信息</br>
	 * 	  2.判断是否存在物资信息。不存在，结束方法</br>
	 *    3.构建盘库主表对象和构建盘库明细表对象集合</br>
	 *    4.调用本地服务保存盘库主表信息和盘库明细表</br>
	 *    5.冻结仓库
	 * </p>
	 *
	 * @Title: generateStockInven
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		wareHouseNo：仓库号
	 * 		matTypeNum：物资编码
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	public EiInfo generateStockInven(EiInfo inInfo) {
		//获取指定仓库、指定分类的物资库存信息
		List<SiStorge> slist = getWareHouseStock(inInfo);
		// 构建一个新的获取指定仓库、指定分类的物资库存信息列表，筛选掉库存数量为0的物资
		// （不从sql上进行筛选主要是该sql涉及其他地方的引用，担心其他地方会有影响。）
		List<SiStorge> newSlist = new ArrayList<>();
		for (SiStorge stockMat : slist){
			if(NumberUtils.toDouble(stockMat.getTotalNum(), 0d) == 0){ continue;}
			newSlist.add(stockMat);
		}
		//判断是否存在物资信息
		if(newSlist.size() == 0){
			inInfo.setMsg("该仓库中不存在物资,盘库单无法生成");
			return inInfo;
		}
		//构建盘库主表对象
		SiStorge storge = newSlist.get(0);
		SiInven inven = BeanExchangeUtils.storgeToInven(storge);
		//构建盘库明细表对象
		List<SiInvenDetail> list = new ArrayList<>();
		newSlist.forEach(siStorge -> list.add(BeanExchangeUtils.storgeToInvenDetail(siStorge, inven.getInvenBillNo())));
		//调用本地服务保存盘库主表信息
		EiInfo info = new EiInfo();
		info.set("inven", inven);
		info.set(EiConstant.serviceName, "SIPK01");
		info.set(EiConstant.methodName, "insertInven");
	    XLocalManager.call(info);
	    //保存盘库明细表
		info.set("invenDetailList", list);
		info.set(EiConstant.serviceName, "SIPK0101");
		info.set(EiConstant.methodName, "insertInvenDetail");
	    XLocalManager.call(info);
		//冻结仓库
	    info.set("type", "dj");
	    info.set("wareHouseNo", inInfo.getString("wareHouseNo"));
	    info.set(EiConstant.serviceName, "SIWH01");
		info.set(EiConstant.methodName, "djOrJd");
	    XLocalManager.call(info);

	    inInfo.setMsg("盘库单生成成功");
        return inInfo;
	}

	/**
	 * 获取指定仓库、指定分类的物资库存信息
	 * @Title: getWareHouseStock
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		wareHouseNo：仓库号
	 * 		matTypeNum：物资编码
	 * @param:  @return
	 * @return: List<SiStorge>
	 * 		 wareHouseNo : 仓库号
	 *		wareHouseName : 仓库名称
	 *		storageNo : 库位号
	 *		stackNo : 货位号
	 *		matTypeNum : 物资分类编码
	 *		matTypeName : 物资分类名称
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unit : 计量单位编码
	 *		unitName : 计量单位名称
	 *		price : 单价
	 *		totalNum : 库存总量
	 *		totalAmount : 库存总价
	 *		batchNo : 批次编码（未用）
	 *		minNum : 最低库存量
	 *		maxNum : 最高库存量
	 *		remindFlag : 是否预警标记，N=不预警，Y=预警
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private List<SiStorge> getWareHouseStock(EiInfo inInfo) {
		//构建参数
		Map<String, String> map = new HashMap<>();
		map.put("wareHouseNo", inInfo.getString("wareHouseNo"));
		map.put("matTypeNums", SiUtils.getMatTypeStrs(inInfo.getString("matTypeNum")));
		//查询
		List<SiStorge> list = dao.query("SIKC01.query", map);
		return list == null ? new ArrayList<>() : list;
	}

}
