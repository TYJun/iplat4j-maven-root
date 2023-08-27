package com.baosight.wilp.cp.cl.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cp.domain.CpBill;
import com.baosight.wilp.cp.util.CPUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  投诉处理页面Service
 * <p>页面加载</p>
 * <p>查询投诉处理数据</p>
 * <p>删除</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCPCL01.java
 * @ClassName:  ServiceCPCL01
 * @Package com.baosight.wilp.cp.cl.service
 * @Description: TODO
 * @author gcc
 * @date:   2022年4月10日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCPCL03 extends ServiceBase {
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo info){
		return this.query(info);
	}

	/**
	 * 查询投诉处理管理
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	@SuppressWarnings("all")
	public EiInfo query(EiInfo info){
		List<Map<String, String>> admin = CPUtils.isAdmin();
		if (CollectionUtils.isNotEmpty(admin)){
			info.set("inqu_status-0-role","admin");
			info.set("inqu_status-0-dealDeptList",admin);
		}
		info.set("inqu_status-0-tabStatus","deal");
		return super.query(info, "CPCL01.query", new CpBill());

	}

	/**deleter
	 * 删除投诉处理处理数据
	 * 通过主键来删除投诉处理工单
	 * @param info
	 * id 主键
	 * @return
	 */
	public EiInfo deleter(EiInfo info){
		//获取主键删除明细表和主表
		String id = info.getString("id");
		dao.delete("CPCL01.deleterDetail",id);
		dao.delete("CPCL01.deleter",id);
		return info;
	}
}
