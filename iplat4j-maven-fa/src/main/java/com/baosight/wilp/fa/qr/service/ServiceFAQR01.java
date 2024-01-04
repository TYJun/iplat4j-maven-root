package com.baosight.wilp.fa.qr.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.fa.qr.domain.FaConfirmDO;
import com.baosight.wilp.fa.utils.OneSelfUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年11月30日 15:15
 */
public class ServiceFAQR01 extends ServiceBase {
	/**
	 * 资产确认管理初始化方法
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/1 14:50
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
//		OneSelfUtils.initQueryTime(info,"buyDateS","buyDateE");
//		OneSelfUtils.initQueryTime(info,"receptionDateS","receptionDateE");
		return info;
	}

	/**
	 * 资产确认管理查询方法
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/1 14:51
	 */
	@Override
	public EiInfo query(EiInfo info) {
		EiInfo outInfo = super.query(info, "FAQR01.queryFaConfirmDOInfo", new FaConfirmDO());
		return outInfo;
	}

	/**
	 * 资产已确认查询
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/1 14:51
	 */
	public EiInfo confirmedQuery(EiInfo info) {
		info.setCell("inqu_status", 0, "confirmStatus", "confirmed");
		return super.query(info, "FAQR01.queryFaConfirmDOInfo", new FaConfirmDO(), false, null, null, "resultA", "resultA");
	}

	/**
	 * 资产待确认查询
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/1 14:53
	 */
	public EiInfo unconfirmedQuery(EiInfo info) {
		info.setCell("inqu_status", 0, "confirmStatus", "unconfirmed");
		EiInfo outInfo = super.query(info, "FAQR01.queryFaConfirmDOInfo", new FaConfirmDO(), false, null, null, "resultB", "resultB");
		EiBlock block = info.getBlock("resultB");
		if (block != null){
			Map attr = block.getAttr();
			Integer limit = (Integer) attr.get("limit");
			outInfo.addBlock("resultB").set(EiConstant.limitStr, limit);
		} else {
			outInfo.addBlock("resultB").set(EiConstant.limitStr, 50);
		}
		return outInfo;
	}

	/**
	 * 资产已删除查询
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/2 13:00
	 */
	public EiInfo removeQuery(EiInfo info) {
		info.setCell("inqu_status", 0, "confirmStatus", "remove");
		return super.query(info, "FAQR01.queryFaConfirmDOInfo", new FaConfirmDO(), false, null, null, "resultC", "resultC");
	}

	/**
	 * 批量确认
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/2 12:57
	 */
	public EiInfo batchApproval(EiInfo info) {
		if (info.get("id") != null) {
			List<String> idLists = (List<String>) info.get("id");
			int i = dao.update("FAQR01.batchApproval", idLists);
			info.setMsg("批量确认" + i + "条数据");
		} else {
			info.setStatus(-1);
			info.setMsg("请选择数据再进行批量确认");
		}
		return info;
	}

	/**
	 * 批量撤销
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/2 12:57
	 */
	public EiInfo batchRevocation(EiInfo info) {
		if (info.get("id") != null) {
			List<String> idLists = (List<String>) info.get("id");
			int i = dao.update("FAQR01.batchRevocation", idLists);
			info.setMsg("批量撤销" + i + "条数据");
		} else {
			info.setStatus(-1);
			info.setMsg("请选择数据再进行批量撤销");
		}
		return info;
	}

	/**
	 * 批量删除
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/2 12:49
	 */
	public EiInfo batchRemove(EiInfo info) {
		if (info.get("id") != null) {
			List<String> idLists = (List<String>) info.get("id");
			int i = dao.update("FAQR01.batchRemove", idLists);
			info.setMsg("批量删除" + i + "条数据");
		} else {
			info.setStatus(-1);
			info.setMsg("请选择数据再进行批量删除");
		}
		return info;
	}

	/**
	 * 批量恢复
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/2 12:49
	 */
	public EiInfo batchRecover(EiInfo info) {
		if (info.get("id") != null) {
			List<String> idLists = (List<String>) info.get("id");
			int i = dao.update("FAQR01.batchRecover", idLists);
			info.setMsg("批量恢复" + i + "条数据");
		} else {
			info.setStatus(-1);
			info.setMsg("请选择数据再进行批量恢复");
		}
		return info;
	}

	// 查询国别
	public EiInfo queryManufacturerNatyCode(EiInfo info) {
		List<Map<String, String>> maps = dao.query("FAQR01.queryManufacturerNatyCode", new HashMap<>());
		info.setRows("result", maps);
		return info;
	}
}
