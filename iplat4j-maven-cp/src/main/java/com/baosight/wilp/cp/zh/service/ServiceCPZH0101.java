package com.baosight.wilp.cp.zh.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.cp.domain.CpBill;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投诉综合查询查看子页面Service
 *
 * <p>1.页面加载查看信息</p>
 * <p>2.生成投诉编号</p>
 * <p>3.TabGrid查询方法</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCPZH0101.java
 * @ClassName:  ServiceCPZH0101
 * @Package com.baosight.wilp.cp.pj.service
 * @Description:
 * @author liangyongfei
 * @date:   2022年4月10日 下午4:20:35
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCPZH0101 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 投诉综合查询查看页面
     * 通过投诉工单号
     * 进行投诉信息查看
     * @param inInfo
     * id 主键
     * billNo 投诉单号
     * @return EiInfo
	 * complaintDate 发生日期
	 * complaintName 投诉人
	 * complaintPhone电话
	 * complaintDept部门/单位
	 * complaintEmail邮箱
	 * complaintType投诉类别
	 * complaintWay 投诉方式
	 * complaintContent投诉内容
	 * idendify 识别
	 * dealType处理类型
	 * dealDept处理部门
	 * dealWorkName处理人
	 * dealDate处理日期
	 * dealReason处理原因
	 * dealDesc处理描述
	 * returnWorkName回访人
	 * returnDate回访日期
	 * returnDesc回访情况
	 * teskEval评价等级
     */
	public EiInfo initLoad(EiInfo inInfo) {
		// 获取参数
		String id = inInfo.getAttr().get("id").toString();
		String billNo = inInfo.getAttr().get("billNo").toString();
		//String type = inInfo.getAttr().get("type").toString();
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new CpBill().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			map.put("billNo",billNo);
			// 调用查询方法
			List<HashMap<String, String>> list = dao.query("CPZH01.queryId", map);

			// 数据返回
			block.addRows(list);
			// 特殊数据回显
			EiColumn column1 = new EiColumn("complaintType_textField");
			EiColumn column2 = new EiColumn("complaintWay_textField");
//			String surpNum=list.get(0).getSurpNum();
			block.addMeta(column1);
			block.addMeta(column2);
			//String contdeptNUm = list.get(0).get("contdeptNUm");
			block.setCell(0, "complaintType_textField", list.get(0).get("complaintType"));
			block.setCell(0, "complaintWay_textField", list.get(0).get("complaintWay"));
		}
		inInfo.addBlock(block);
		// 返回参数

		//inInfo.set("inqu_status-0-type",inInfo.getString("type"));
		return inInfo;
	}

	public EiInfo queryTabA(EiInfo info){
		String billNo = info.getString("billNo");
		try {
			List<Map<String,String>> list = dao.query("CPZH01.queryDetail1", new HashMap<String, String>() {{
				put("billNo", billNo);
			}});
			info.setStatus(EiConstant.STATUS_SUCCESS);
			info.setRows("resultA",list);
		} catch (Exception e){
			info.setMsg(e.getMessage());
			info.setStatus(EiConstant.STATUS_FAILURE);
		}
		return info;
	}

	public EiInfo queryTabB(EiInfo info){
		String billNo = info.getString("billNo");
		try {
			List<Map<String,String>> list = dao.query("CPZH01.queryDetail2", new HashMap<String, String>() {{
				put("billNo", billNo);
			}});
			info.setStatus(EiConstant.STATUS_SUCCESS);
			info.setRows("resultB",list);
		} catch (Exception e){
			info.setMsg(e.getMessage());
			info.setStatus(EiConstant.STATUS_FAILURE);
		}
		return info;
	}

	/**
     * @Title: queryTabGrid 
     * @Description: TabGrid查询方法
     * @param inInfo
     * querySql 查询sql
     * countSql 统计sql
     * resultBlock blockId 
     * blockMeta EiBlockMeta
     * @return: EiInfo
     */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock,
			EiBlockMeta blockMeta) {
	    // 调用封装方法构造map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		// 查询数据
		List<?> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
		// 获取总数
		int count = dao.count(countSql, map);
		// 数据返回
		return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}
}
