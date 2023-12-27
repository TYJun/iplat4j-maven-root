package com.baosight.wilp.si.yj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.si.common.SiUtils;
import com.baosight.wilp.si.common.WareHouseDataSplitUtils;

/**
 * 库存预警页面Service
 *
 * <p>页面加载</p>
 * <p>页面查询</p>
 * <p>删除仓库预警</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSIYJ01.java
 * @ClassName:  ServiceSIYJ01
 * @Package com.baosight.wilp.si.yj.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 上午9:56:35
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSIYJ01 extends ServiceBase {

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
		inInfo.addBlock(EiConstant.resultBlock).set(EiConstant.limitStr, 20);
		return query(inInfo);
    }

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * 		wareHouseNo：仓库号
	 * 		matTypeNum： 物资分类
	 * 		matNum：物资编码
	 * 		matName：物资名称
	 * 		isNot0 ： 是否显示0库存
	 * @return
	 * 		wareHouseName : 仓库
	 *		wareHouseNo : 仓库号
	 *		matTypeName : 物资分类名称
	 *		matNum : 物资编码
	 *		matName : 物资名称
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unitName : 计量单位
	 *		totalNum : 库存总量
	 *		totalAmount : 库存总价
	 *		minNum : 最低存量
	 *		maxNum : 最高存量
	 * @see ServiceBase#query(EiInfo)
	 */
    @Override
    public EiInfo query(EiInfo inInfo) {
    	inInfo.set("inqu_status-0-earlyWarning", "Y");
		inInfo.set("inqu_status-0-wareHouseNos", WareHouseDataSplitUtils.getWareHouseNos(UserSession.getLoginName()));
    	return SiUtils.invoke(inInfo, "SIKC01", "query", null);
    }

    /**
     * 删除预警信息
     *
     * <p>将库存表中的最低库存和最高库存更新成0</p>
     *
     * <p>Title: delete</p>
     * <p>Description: </p>
     * @param inInfo
     * 		id : 主键
     * @return
     * @see ServiceBase#delete(EiInfo)
     */
    public EiInfo delete(EiInfo inInfo) {
    	 return  super.update(inInfo, "SIYJ01.delete");
    }

}
