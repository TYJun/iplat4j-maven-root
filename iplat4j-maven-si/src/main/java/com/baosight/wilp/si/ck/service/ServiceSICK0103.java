package com.baosight.wilp.si.ck.service;

import java.util.Arrays;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.si.ck.domain.SiOutDetail;

/**
 * 出库详情子页面Service
 *
 * <p>页面加载</p>
 * <p>页面查询</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceSICK0103.java
 * @ClassName:  ServiceSICK0103
 * @Package com.baosight.wilp.si.ck.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月24日 下午3:28:32
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceSICK0103 extends ServiceBase {

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
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * 		outBillNo ： 出库单号
	 * @return
	 * 		matNum : 物资编码
	 *		matName : 物资名称
	 *		matTypeName : 物资分类
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unit : 单位
	 *		unitName : 物资单位
	 *		outNum : 出库数量
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		inInfo = CommonUtils.buildEiInfoParamter(inInfo, null, Arrays.asList(new String[]{"outBillNo"}));
		EiInfo info = super.query(inInfo, "SICK0101.query", new SiOutDetail());
		return info;
    }

}
