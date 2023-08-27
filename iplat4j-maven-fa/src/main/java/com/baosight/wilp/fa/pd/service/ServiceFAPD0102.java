package com.baosight.wilp.fa.pd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 固定资产盘点详情接口.
 * 1.固定资产盘点初始化方法.
 * 2.固定资产盘点查询方法.
 * 3.查询固定资产盘点详情.
 * @author zhaowei
 * @date 2022年05月31日 14:19
 * @version v5.0.0
 */
public class ServiceFAPD0102 extends ServiceBase {
	/**
	 * 固定资产盘点初始化方法.
	 * 1.获取操作类型.
	 * 1.1.查看分支.
	 * @author zhaowei
	 * @date 2022/6/9 11:05
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info){
		// 1.获取操作类型
		String type = info.getString("type");
		switch (type) {
			// 1.1.编辑分支
			case "look":
				List<Map<String, Object>> queryFaInventoryInfoList = dao.query("FAPD01.queryFaInventoryInfo", info.getAttr());
				info.set("info-0-faInventoryId", queryFaInventoryInfoList.get(0).get("faInventoryId"));
				info.set("info-0-inventoryNo", queryFaInventoryInfoList.get(0).get("inventoryNo"));
				info.set("info-0-checkDeptNum", queryFaInventoryInfoList.get(0).get("inventoryDeptNum"));
				info.set("info-0-checkDeptName", queryFaInventoryInfoList.get(0).get("inventoryDeptName"));
				info.set("deptName", queryFaInventoryInfoList.get(0).get("inventoryDeptName"));
//				info.set("info-0-inventoryDeptNum", queryFaInventoryInfoList.get(0).get("inventoryDeptNum"));
//				info.set("info-0-inventoryDeptNum_textField", queryFaInventoryInfoList.get(0).get("inventoryDeptName"));
				info.set("info-0-newBuild", queryFaInventoryInfoList.get(0).get("build"));
				info.set("info-0-newFloor", queryFaInventoryInfoList.get(0).get("floor"));
				info.set("info-0-newGoodsLocationNum", queryFaInventoryInfoList.get(0).get("inventorySpotNum"));
				info.set("info-0-newGoodsLocation", queryFaInventoryInfoList.get(0).get("inventorySpotName"));
				info.set("newBuild", queryFaInventoryInfoList.get(0).get("build"));
				info.set("newFloor", queryFaInventoryInfoList.get(0).get("floor"));
				info.set("newGoodsLocationNum", queryFaInventoryInfoList.get(0).get("inventorySpotNum"));
				info.set("newGoodsLocation", queryFaInventoryInfoList.get(0).get("inventorySpotName"));
				info.set("installLocation", queryFaInventoryInfoList.get(0).get("inventorySpotName"));
				info.set("info-0-remark", queryFaInventoryInfoList.get(0).get("remark"));
				info.set("info-0-archiveFlag", queryFaInventoryInfoList.get(0).get("archiveFlag"));
				break;
		}
		return info;
	}

	/**
	 * 固定资产盘点查询方法.
	 * @author zhaowei
	 * @date 2022/6/9 11:21
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v5.0.0
	 */
	@Override
	public EiInfo query(EiInfo info){
		return info;
	}

	/**
	 * 查询固定资产盘点详情.
	 *
	 * @author zhaowei
	 * @date 2022/6/13 16:35
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo queryInventoryDetailTabInfo(EiInfo info){
		List<Map<String,Object>> queryInventoryDetailFinalTabInfoList = dao.query("FAPD01.queryInventoryDetailFinalTabInfo", info.getAttr());
		List<Map<String,Object>> queryInventoryDetailTabInfoList = dao.query("FAPD01.queryInventoryDetailTabInfo", info.getAttr());
		if (CollectionUtils.isNotEmpty(queryInventoryDetailFinalTabInfoList)){
			info.setRows("resultInventoryDetail",queryInventoryDetailFinalTabInfoList);
		}else {
			info.setRows("resultInventoryDetail",queryInventoryDetailTabInfoList);
		}
		return info;
	}

}
