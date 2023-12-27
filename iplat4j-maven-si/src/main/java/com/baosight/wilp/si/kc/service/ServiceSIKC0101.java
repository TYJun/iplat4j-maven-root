package com.baosight.wilp.si.kc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.si.kc.domain.SiStorgeDetail;

/**
 * 库存存量详情子页面Service
 *
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>获取库存明细数据(无分页)</p>
 * <p>插入库存明细</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIKC0101.java
 * @ClassName:  ServiceSIKC0101
 * @Package com.baosight.wilp.si.kc.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月18日 下午6:23:10
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIKC0101 extends ServiceBase {

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
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * 		wareHouseNo：仓库号
	 * 		wareHouseName ： 仓库名称
	 * 		matNum：物资编码
	 * @return
	 * 		batchNo : 批次号
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matSpec : 物资规格
	 *		unitName : 计量单位
	 *		totalNum : 库存量
	 *		price : 库存单价
	 *		totalAmount : 库存总价
	 *		recCreateTime : 入库时间
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "SIKC0101.query", new SiStorgeDetail());
        return outInfo;
    }

	/**
	 * 获取库存明细数据
	 * @Title: queryStorgeDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		enterBillNo：入库单号
	 * @param:  @return
	 * @return: EiInfo
	 * 		batchNo : 批次号
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matSpec : 物资规格
	 *		unitName : 计量单位
	 *		totalNum : 库存量
	 *		price : 库存单价
	 *		totalAmount : 库存总价
	 *		recCreateTime : 入库时间
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryStorgeDetail(EiInfo inInfo) {
		Map<String,String> map = new HashMap<>();
		map.put("enterBillNo", inInfo.getString("enterBillNo"));
		map.put("wareHouseNo", inInfo.getString("wareHouseNo"));
		map.put("matNum", inInfo.getString("matNum"));
		map.put("batchNos", inInfo.getString("batchNo"));
		if(StringUtils.isBlank(inInfo.getString("batchNo"))){
			map.put("isNot0", "Y");
		}
        List<SiStorgeDetail> list = dao.query("SIKC0101.query", map);
        inInfo.set("list", list == null ? new ArrayList<>() : list);
        return inInfo;
    }

	/**
	 * 插入库存明细
	 * @Title: insertStorgeDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		storgeDetailList: 库存明细集合
	 * @param:  @return
	 * @return: EiInfo
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo insertStorgeDetail (EiInfo inInfo) {
		List<SiStorgeDetail> storgeDetailList = inInfo.get("storgeDetailList") == null ? null : (List<SiStorgeDetail>)inInfo.get("storgeDetailList");
		if(storgeDetailList == null) {
			inInfo.setStatus(-1);
			inInfo.setMsg("插入库存明细失败");
			return inInfo;
		} else {
			storgeDetailList.forEach(detail -> dao.insert("SIKC0101.insert", detail));
		}
		return inInfo;
    }

}
