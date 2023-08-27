package com.baosight.wilp.ci.rk.service;

import java.util.Arrays;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.ci.rk.domain.CiEnterDetail;

/**
 * 仓库入库管理页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIRK01.java
 * @ClassName:  ServiceCIRK01
 * @Package com.baosight.wilp.ci.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午1:37:58 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIRK0201 extends ServiceBase {

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
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		enterBillNo:入库单号
	 * @return
	 * 		matNum : 物资编码
	 *		matName : 物资名称
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unit : 单位
	 *		unitName : 物资单位
	 *		enterNum : 入库数量
	 *		enterPrice : 入库单价
	 *		enterAmount : 入库总价   
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo = CommonUtils.buildEiInfoParamter(inInfo, null, Arrays.asList(new String[]{"enterBillNo"}));
		EiInfo info = super.query(inInfo, "CIRK0101.query", new CiEnterDetail());
		return info;
    }

}
