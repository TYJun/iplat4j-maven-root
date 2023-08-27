package com.baosight.wilp.ci.cg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *物资审核页面
 * <p>1.初始化查询 initLoad
 * <p>2.项目查询 query
 *
 * @Title: ServiceCICG01.java
 * @ClassName: ServiceCICG01
 * @Package：com.baosight.wilp.ci.cg.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceCICG02 extends ServiceBase {
	
	private static Lock lock = new ReentrantLock();

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
		EiInfo initLoad = this.query(inInfo);

		//获取小代码小票报表地址配置
		EiInfo callCode = CiUtils.callCode("S_ED_02", "wilp.ci.jc.frUrl", "notify");
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)callCode.get("list");
		initLoad.addBlock("notify").addRows(list);
		return initLoad;
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * 		startTime:制单日期起（>=）
	 * 		endTime:制单日期止（<=）
	 * @return inInfo
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    public EiInfo query(EiInfo inInfo) {

        String[] param = {"startTime","endTime"};
        //将取参数封装包含分页
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result",Arrays.asList(param));
        List<Map<String, String>> list = dao.query("CICG02.query", map);
        int count = dao.count("CICG02.count", map);
        return CommonUtils.BuildOutEiInfo(inInfo, null, null, list, count);
    }

}
