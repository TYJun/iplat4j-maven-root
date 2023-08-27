package com.baosight.wilp.fa.bg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.fa.bg.domain.FaModificationRecordVO;
import com.baosight.wilp.fa.da.domain.FaInfoDO;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 固定资产变更详情接口.
 * 固定资产变更详情方法.
 * @author zhaowei
 * @date 2022年07月13日 9:48
 */
public class ServiceFABG0103 extends ServiceBase {
	/**
	 * 固定资产变更详情方法.
	 * 1.新增直接返回
	 * 2.编辑回显数据
	 * @author zhaowei
	 * @date 2022/8/26 15:00
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.获取操作类型并进行逻辑判断
		String type = info.getString("type");
		List<Map<String, Object>> faInfoInfoList = dao.query("FADA01.query", info.getAttr());
		info.setAttr(faInfoInfoList.get(0));
		switch (type) {
			case "look":
				List<Map<String,String>> faModificationRecordVOList = dao.query("FABG01.queryFaModificationRecordByFaInfoId", info.getString("faInfoId"));
				info.setRows("resultModificationRecord",faModificationRecordVOList);
		}
		return info;
	}
}