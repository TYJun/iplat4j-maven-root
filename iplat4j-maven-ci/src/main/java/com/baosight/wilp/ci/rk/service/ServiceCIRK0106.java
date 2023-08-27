package com.baosight.wilp.ci.rk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * 物资选择子页面Service
 * 
 * <p>页面加载</p>
 * <p>页面查询</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCIRK0104.java
 * @ClassName:  ServiceCIRK0104
 * @Package com.baosight.wilp.ci.rk.service
 * @Description: TODO
 * @author fangjian
 * @date:   2021年8月23日 下午2:38:40 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCIRK0106 extends ServiceBase {
	
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
	 * 		matTypeNum：物资分类ID
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
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", Arrays.asList(new String[]{"matCode","matName","matTypeNum"}));
		map.put("matClassId",map.get("matTypeNum"));
		EiInfo mat = CiUtils.getMat(map);
		mat.setBlockInfoValue("result", "showCount", "true");
		return mat;
    }


}
