package com.baosight.wilp.fa.pd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import com.baosight.wilp.fa.pd.domain.FaInventoryDO;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.wilp.fa.zj.domain.FaDepreciation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 固定资产盘点接口.
 * 1.固定资产盘点初始化方法.
 * 2.固定资产盘点查询方法.
 * @author zhaowei
 * @date 2022年05月30日 12:06
 * @version v5.0.0
 */
public class ServiceFAPD01 extends ServiceBase {
	/**
	 * 固定资产盘点初始化方法.
	 * 1.调用本地query方法.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/13 16:36
	 * @version v5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.调用本地query方法
		return this.query(info);
	}

	/**
	 * 固定资产盘点查询方法.
	 * 1.根据当前登录人信息查询固定资产角色.
	 * 2.构建分页参数.
	 * 3.查询固定资产盘点信息.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/13 16:36
	 * @version v5.0.0
	 */
	@Override
	public EiInfo query(EiInfo info) {
		// 1.根据当前登录人信息查询固定资产角色
		String deptName = OneSelfUtils.specifyDept();
		if (StringUtils.isNotEmpty(deptName)){
			info.set("deptName",deptName);
		}
		// 2.构建分页参数
		Map<String, Object> pageMap = new HashMap<>(8);
		pageMap.put("offset", 0);
		pageMap.put("limit", 10);
		if (info.getBlocks().size() > 0) {
			EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
			pageMap = eiBlock.getAttr();
		}
		// 3.查询固定资产盘点信息
		List<Map<String, Object>> queryFaInventoryInfoList = dao.query("FAPD01.queryFaInventoryInfo", info.getAttr(), (Integer) pageMap.get("offset"),(Integer) pageMap.get("limit"));
		int count = dao.count("FAPD01.queryFaInventoryInfo", info.getAttr());
		pageMap.put("count", count);
		info.setRows("result", queryFaInventoryInfoList);
		info.setAttr(pageMap);
		return info;
	}
	
	/**
	 * 工单资产待盘点查询
	 * @author zhaowei
	 * @date 2023/2/2 19:14
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo unCheckedQuery(EiInfo info){
		info.setCell("inqu_status", 0, "checkStatus", "unChecked");
		EiInfo outInfo = super.query(info, "FAPD01.queryFaInventoryInfo", new FaInventoryDO(),false, null, null, "resultA", "resultA");
		return outInfo;
	}

	/**
	 * 固定资产已盘点
	 * @author zhaowei
	 * @date 2023/2/2 19:15
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo checkedQuery(EiInfo info){
		info.setCell("inqu_status", 0, "checkStatus", "checked");
		EiInfo outInfo = super.query(info, "FAPD01.queryFaInventoryInfo", new FaInventoryDO(), false, null, null, "resultB", "resultB");
		return outInfo;
	}
}
