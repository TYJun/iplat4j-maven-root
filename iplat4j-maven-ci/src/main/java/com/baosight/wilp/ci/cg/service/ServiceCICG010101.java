package com.baosight.wilp.ci.cg.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物资选择页面
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 *
 * @Title: ServiceCICG010101.java
 * @ClassName: ServiceCICG010101
 * @Package：com.baosight.wilp.ci.cg.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCICG010101 extends ServiceBase {
	
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
	 * 		matName:物资名称
	 * 		matTypeNum:物资分类编码
	 * @return inInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	//@Override
    public EiInfo query8(EiInfo inInfo) {
		String[] param = {"matName",""};
		//将取参数封装包含分页
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", Arrays.asList(param));
		List<Map<String, String>> list = dao.query("CICG01.queryMat", map);
		int count = dao.count("CICG01.countMat", map);
		return CommonUtils.BuildOutEiInfo(inInfo, null, null, list, count);
    }
	@Override
	public EiInfo query(EiInfo inInfo) {
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", Arrays.asList(new String[]{"matName","matTypeNum"}));
		map.put("matClassId",map.get("matTypeNum"));
		//S_AC_FW_17查物资信息
		EiInfo mat = CiUtils.getMat(map);
		EiBlock result = mat.getBlock("result");
		List<HashMap<String,Object>> rows = result.getRows();
		for (HashMap<String, Object> row : rows) {
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("matNumEQ", row.get("matNum"));
			List<Map<String, String>> list = dao.query("CICG01.queryMat", pMap);
			if(CollectionUtils.isEmpty(list)){
				row.put("zongNum",0);
			}else{
				row.put("zongNum",list.get(0).get("zongNum") );
			}
			if(StringUtils.isNotEmpty((String) row.get("supplier"))){

				String supplierCode = CiUtils.getSupplierByName((String) row.get("supplier"));
				row.put("supplierName",row.get("supplier"));
				row.put("supplier",supplierCode);
			}

		}

		mat.setBlockInfoValue("result", "showCount", "true");
		return mat;
	}

}
