package com.baosight.wilp.ci.kc.service;

import java.util.*;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.kc.domain.CiStorge;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.kc.domain.CiStorgeRecord;

/**
 * 库存操作履历Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>查询库存操作履历（无分页）</p>
 * <p>保存库存操作履历</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIKC02.java
 * @ClassName:  ServiceCIKC02
 * @Package com.baosight.wilp.ci.kc.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月27日 上午9:52:59 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIKC02 extends ServiceBase {
	
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
        return query(inInfo);
    }
	
	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		wareHouseNo：仓库编码
	 * 		matNumLK:物资编码
	 * 		matName:物资名称
	 * 		originBillNoLK:来源单号
	 * @return 
	 * 		id : 主键
	 *		originBillNo : 来源单据号
	 *		originBillTypeName : 来源单据类型
	 *		wareHouseName : 仓库
	 *		wareHouseNo : 仓库号
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matTypeName : 物资分类名称
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unit : 单位
	 *		unitName : 计量单位
	 *		beforeNum : 操作前数量
	 *		beforeAmount : 操作前总价
	 *		afterNum : 操作后数量
	 *		afterAmount : 操作后总价  
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "CIKC02.query", new CiStorgeRecord());
        return outInfo;
    }
	
	/**
	 * 查询库存操作履历
	 * @Title: queryStorgeRecord
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		wareHouseNo：仓库编码
	 * 		matNumLK:物资编码
	 * 		matName:物资名称
	 * 		originBillNoLK:来源单号
	 * @param:  @return
	 * @return: EiInfo
	 * 		id : 主键
	 *		originBillNo : 来源单据号
	 *		originBillTypeName : 来源单据类型
	 *		wareHouseName : 仓库
	 *		wareHouseNo : 仓库号
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matTypeName : 物资分类名称
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unit : 单位
	 *		unitName : 计量单位
	 *		beforeNum : 操作前数量
	 *		beforeAmount : 操作前总价
	 *		afterNum : 操作后数量
	 *		afterAmount : 操作后总价    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo queryStorgeRecord(EiInfo inInfo) {
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, Arrays.asList(
				new String[]{"id","originBillNo","wareHouseNo","matNum","matName","batchNo","transBillNo"}));
		List<CiStorgeRecord> list = dao.query("CIKC02.query", map);
		inInfo.set("list", list == null ? new ArrayList<>() : list);
		inInfo.set("record", list == null ? null : list.get(0));
		return inInfo;
	}
	
	/**
	 * 保存库存操作履历
	 * <p>Title: insert</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#insert(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EiInfo insert(EiInfo inInfo) {
		//获取参数
		Object object = inInfo.get("record");
		Object object2 = inInfo.get("recordList");
		if(object != null){
			CiStorgeRecord record = (CiStorgeRecord) object;
			dao.insert("CIKC02.insert", record);
		}
		if(object2 != null){
			List<CiStorgeRecord> list = (List<CiStorgeRecord>) object2;
			list.forEach(storgeRecord -> dao.insert("CIKC02.insert", storgeRecord));
		}
		return inInfo;
	}

	public EiInfo insert1(EiInfo inInfo) {
		//获取参数
		Object object = inInfo.get("record");
		Object object2 = inInfo.get("recordList");
		CiStorgeRecord recordList = new CiStorgeRecord();
		//获取操作履历数据
		if(object != null){
			recordList = (CiStorgeRecord) object;
		}
		if(object2 != null){
			List<CiStorgeRecord> list = (List<CiStorgeRecord>) object2;
			recordList = list.get(0);
		}
		//更改插入操作履历信息
		//获取操作履历数据

		//获取操作前数量和价格
		Double beforeNum = recordList.getBeforeNum();
		Double beforeAmount = recordList.getBeforeAmount();
		//获取操作后数量和价格
		Double afterNum = recordList.getAfterNum();
		Double afterAmount = recordList.getAfterAmount();
		//获取出入库数量数量差和价格差
		double num = beforeNum - afterNum;
		double amount = beforeAmount - afterAmount;

		//获取操作类型
		String originBillType = recordList.getOriginBillType();

		//修改操作前和操作后的数量和价格
		//1.获取物资库存量
		HashMap<Object, Object> map = new HashMap<>();
		String matNum = recordList.getMatNum();
		map.put("matNumEQ", matNum);
		//获取库存表中的信息
		List<CiStorge> query = dao.query("CIKC01.query", map);
		Double totalNum = query.get(0).getTotalNum();
		//2.获取库存量作为操作前数量，库存量加减数量差作为操作后数量
		if (originBillType.equals("06")){//出库入库操作
			if (recordList.getOriginBillTypeName().equals("食堂进销存出库")){

			}else if (recordList.getOriginBillTypeName().equals("采购入库")){

			}

		}else if (originBillType.equals("01")){ //直入直出操作

		}else if (originBillType.equals("05")){//红冲操作

		}else if (originBillType.equals("03")){//盘亏盘盈出库操作

		}

		if(object != null){
			CiStorgeRecord record = (CiStorgeRecord) object;
			dao.insert("CIKC02.insert", record);
		}
		if(object2 != null){
			List<CiStorgeRecord> list = (List<CiStorgeRecord>) object2;
			list.forEach(storgeRecord -> dao.insert("CIKC02.insert", storgeRecord));
		}
		return inInfo;
	}

}
