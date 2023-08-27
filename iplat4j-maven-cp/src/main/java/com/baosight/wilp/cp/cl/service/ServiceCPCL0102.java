package com.baosight.wilp.cp.cl.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cp.domain.CpBillDeal;
import com.baosight.wilp.cp.util.CPUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  投诉回访子页面Service
 * <p>页面加载</p>
 * <p>保存投诉回访情况</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceCPCL0102.java
 * @ClassName:  ServiceCPCL0102
 * @Package com.baosight.wilp.cp.cl.service
 * @Description: TODO
 * @author gcc
 * @date:   2022年4月10日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceCPCL0102 extends ServiceBase {
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param  inInfo
	 * id  主键
	 * billNo 投诉工单
	 * @return
	 * returnWorkName  回访人
	 * returnDesc  回访情况
	 * returnDate  回访日期
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 获取参数
		String id = inInfo.getAttr().get("id").toString();
		String billNo =inInfo.getString("billNo");
		// 实例化EiBlock
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new CpBillDeal().eiMetadata);
		// 如果参数存在
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, String> map = new HashMap<>();
			// map赋值
			map.put("id", id);
			map.put("billNo",billNo);
			// 调用查询方法 查询上方列表信息
			List<HashMap<String, String>> list = dao.query("CPCL02.queryId", map);
			// 数据返回
			block.addRows(list);
			inInfo.set("dealDate",list.get(0).get("dealDate"));
		}
		inInfo.addBlock(block);
		Map<String, Object> userInfo = CPUtils.getUserInfo(null);
		inInfo.set("inqu_status-0-returnWorkName",userInfo.get("name"));
		return inInfo;

	}

	/**
	 * 保存投诉回访计划
	 * <p>Title: saveComplaintReturn</p>
	 * <p>Description: </p>
	 * @param :inInfo
	 * 	 * id  主键
	 * 	 * billNo 投诉单号
	 * 	 * return_work_name  回访人
	 * 	 * return_date  回访日期
	 * 	 * return_desc  回访描述
	 * @return
	 */

	public EiInfo saveComplaintReturn(EiInfo inInfo){
		// 1.获取表单参数
		String billNo =inInfo.getString("billNo");
		String isDeal =inInfo.getString("isDeal");
		Map<String, Object> userInfo = CPUtils.getUserInfo(null);
		Map<String,String> map = inInfo.getRow("inqu_status", 0);
		// 判断是否继续处理
		if (isDeal.equals("是")){
			// 更新项目--状态为待处理
			dao.update("CPCL02.updateIsDeal",new HashMap<String,String>(){{
				put("billNo",billNo);
			}});
		}else {
			// 更新项目--状态为待评价
			dao.update("CPCL02.updateStatusCode",new HashMap<String,String>(){{
				put("billNo",billNo);
			}});
		}
		map.put("id", UUID.randomUUID().toString());
		map.put("billNo", billNo);
		map.put("create",(String) userInfo.get("workNo"));
		map.put("createName",(String) userInfo.get("name"));
		dao.insert("CPCL02.saveComplaintReturn", map);
		return inInfo;
	}

	/**
	 * @param
	 * @Title: createBillNo
	 * @Description: 生成投诉单号
	 * <p>1.加锁
	 * <p>2.调用时间接口
	 * <p>3.调用查询方法
	 * <p>4.如果结果为空
	 * <p>5.获取最大投诉号
	 * <p>6.对最大投诉号进行拆分
	 * 通过获取当前时间
	 * 判断今天是否存在投诉单号，不存在就返回新的投诉号
	 * 存在投诉单号，对投诉单号进行累加
	 * @return: String
	 * String 投诉工单
	 */
	private String createBillNo() {
		// 1.加锁
		synchronized (dao) {
			// 2.调用时间接口
			String date = DateUtils.curDateStr8();
			// 3.调用查询方法
			List<String> list = dao.query("CPDJ0101.createBillNo", "PO" + date);
			// 4.如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
				// 返回合同号
				return "PO" + date + "0001";
			} else {
				// 5.获取最大合同号
				String maxNo = list.get(0);
				// 6.对最大合同号进行拆分
				maxNo = maxNo.substring(2);
				// 返回合同号
				return "PO" + (Long.parseLong(maxNo) + 1L) + "";
			}
		}

	}
}
