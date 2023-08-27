package com.baosight.wilp.mp.ht.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpContract;

/**
 * 该页面为采购合同管理
 * <p>1.初始化查询 initLoad
 * <p>2.合同模块数据查询 query
 * @Title: ServiceMPHT02.java
 * @ClassName: ServiceMPHT02
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 上午9:41:58
 * @version: V5.0.0
 */

public class ServiceMPHT02 extends ServiceBase {

    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param
     * @return EiInfo
     */
    @Override
	public EiInfo initLoad(EiInfo info){
		info.setCell(MpConstant.QUERY_BLOCK, 0, "statusCode", MpConstant.CONTRACT_STATUS_USE);
		return MpUtils.invoke(info, "MPHT01", "initLoad");
	}

	/**
	 * 页面数据查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 */
	@Override
	public EiInfo query(EiInfo info){
		return super.query(info, "MPLJ02.query", new MpContract());
	}
}
