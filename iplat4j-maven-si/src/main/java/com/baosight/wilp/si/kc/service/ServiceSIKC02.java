package com.baosight.wilp.si.kc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;
import com.baosight.wilp.si.kc.domain.SiStorgeRecord;

/**
 * 库存操作履历Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>查询库存操作履历（无分页）</p>
 * <p>保存库存操作履历</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIKC02.java
 * @ClassName:  ServiceSIKC02
 * @Package com.baosight.wilp.si.kc.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月27日 上午9:52:59 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIKC02 extends ServiceBase {
	
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
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 50);
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
		inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getLoginName()));
        EiInfo outInfo = super.query(inInfo, "SIKC02.query", new SiStorgeRecord());
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
		List<SiStorgeRecord> list = dao.query("SIKC02.query", map);
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
			SiStorgeRecord record = (SiStorgeRecord) object;
			dao.insert("SIKC02.insert", record);
		}
		if(object2 != null){
			List<SiStorgeRecord> list = (List<SiStorgeRecord>) object2;
			list.forEach(storgeRecord -> dao.insert("SIKC02.insert", storgeRecord));
		}
		return inInfo;
	}

}
