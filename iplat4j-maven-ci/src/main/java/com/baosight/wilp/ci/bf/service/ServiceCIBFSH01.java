package com.baosight.wilp.ci.bf.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ci.common.CiUtils;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *报废出库单审核页面
 * <p>1.初始化查询 initLoad
 * <p>2.项目查询 query
 *
 * @Title: ServiceCICG01.java
 * @ClassName: ServiceCICG01
 * @Package：com.baosight.wilp.ci.cg.service
 * @author: jeffery.gao
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class ServiceCIBFSH01 extends ServiceBase {
	
	private static Lock lock = new ReentrantLock();

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
	 * @see ServiceBase#query(EiInfo)
	 */
    public EiInfo query(EiInfo inInfo) {

        String[] param = {"startTime","endTime"};
        //将取参数封装包含分页
        Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result",Arrays.asList(param));
		map.put("outType","09");//只查询报废出库单类型
        List<Map<String, String>> list = dao.query("CICK01.query", map);
        int count = dao.count("CICK01.count", map);
        return CommonUtils.BuildOutEiInfo(inInfo, null, null, list, count);
    }

}
