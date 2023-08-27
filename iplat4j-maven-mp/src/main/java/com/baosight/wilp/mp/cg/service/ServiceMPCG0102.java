package com.baosight.wilp.mp.cg.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * 物资选择子页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceMPCG0102.java
 * @ClassName:  ServiceMPCG0102
 * @Package com.baosight.wilp.mp.cg.service
 * @Description: TODO
 * @author lyf
 * @date:   2022年10月19日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceMPCG0102 extends ServiceBase {
	
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
	 * 		matName： 物资名称
	 * 		matTypeNum：物资分类
	 * @return 
	 * 		matNum : 物资编码
	 *		matName : 物资名称
	 *		matTypeName : 物资分类
	 *		matModel : 物资型号
	 *		matSpec : 物资规格
	 *		unit : 单位
	 *		unitName : 物资单位
	 *		price : 物资价格  
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		/**1.获取参数**/
		Map<String, Object> params = CommonUtils.buildParamter(inInfo, MpConstant.QUERY_BLOCK, "mat");

		/**2.调用本地服务获取物资信息**/
		EiInfo paramInfo = new EiInfo();paramInfo.setAttr(params);
		return MpUtils.invoke(paramInfo, "MPTY01", "selectMat");
    }

}
