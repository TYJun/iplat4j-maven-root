package com.baosight.wilp.pm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.domain.TpmCalculate;
import com.baosight.xservices.xs.util.UserSession;
/**
 * 
 * 工程项目：初始化查询、编辑、获取归档编号
 * <p>1.初始化查询 initLoad
 * <p>2.编辑 editArchive
 * <p>3.获取指定数据字典的指定项名称 getGroupTypeItemName
 * 
 * @Title: ServicePM0601.java
 * @ClassName: ServicePM0601
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午4:54:02
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0601 extends ServiceBase {

    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 实例化EiBlock
		EiBlock block = inInfo.getBlock("inqu_status");
		// 如果block为空
		if(block == null){
		    // 实例化EiBlock
			EiBlock pBlock = new EiBlock("inqu_status");
			pBlock.setBlockMeta(new TpmCalculate().eiMetadata);
			inInfo.addBlock(pBlock);
		}
		// 实例化map
		Map<String, Object> map = new HashMap<>();
		// 获取数据
		map.put("id", inInfo.get("id"));
		map.put("docStatusCode", inInfo.get("docStatusCode"));
		// 查询工程项目信息
		List<TpmCalculate> list = dao.query("PM05.queryCalculateList", map);
		inInfo.getBlock("inqu_status").setRows(list);
		//处理人员选择显示字段
		EiColumn eiColumn;
		eiColumn = new EiColumn("submitterCode_textField");
		inInfo.getBlock("inqu_status").addMeta(eiColumn);
		
		eiColumn = new EiColumn("recipientCode_textField");
		inInfo.getBlock("inqu_status").addMeta(eiColumn);
		
		eiColumn = new EiColumn("dataSubmitterCode_textField");
		inInfo.getBlock("inqu_status").addMeta(eiColumn);
		
		eiColumn = new EiColumn("dataRecipientCode_textField");
		inInfo.getBlock("inqu_status").addMeta(eiColumn);
		
		eiColumn = new EiColumn("dataCode_textField");
		inInfo.getBlock("inqu_status").addMeta(eiColumn);
		
		inInfo.getBlock("inqu_status").setCell(0, "submitterCode_textField", list.get(0).getSubmitter());
		inInfo.getBlock("inqu_status").setCell(0, "recipientCode_textField", list.get(0).getRecipient());
		inInfo.getBlock("inqu_status").setCell(0, "dataSubmitterCode_textField", list.get(0).getDataSubmitter());
		inInfo.getBlock("inqu_status").setCell(0, "dataRecipientCode_textField", list.get(0).getDataRecipient());
		inInfo.getBlock("inqu_status").setCell(0, "dataCode_textField", getGroupTypeItemName("pm.projectData", list.get(0).getDataCode()));
		return inInfo;
	}

	/**
	 * 编辑
	 * @Title: editArchive
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo editArchive(EiInfo inInfo) {
		//参数处理
		EiBlock block = inInfo.getBlock("inqu_status");
		// 实例化map
		Map<String, Object> map = new HashMap<String, Object>();
		// 如果入参参数不为空
		if(block != null && block.getRowCount() > 0){
			// 参数赋值
		    map = block.getRow(0);
		}
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//赋值
		TpmCalculate calculate = new TpmCalculate();
		calculate.fromMap(map);
		calculate.setDataSubmitterName((String)map.get("dataSubmitterCode_textField"));
		calculate.setDataRecipientName((String)map.get("dataRecipientCode_textField"));
		calculate.setSubmitterName((String)map.get("submitterCode_textField"));
		calculate.setRecipientName((String)map.get("recipientCode_textField"));
		calculate.setDataName((String)map.get("dataCode_textField"));
		calculate.setRecRevisor((String)staffByUserId.get("name"));
		calculate.setRecReviseTime(DateUtils.curDateTimeStr19());
		dao.update("PM05.updateCalculate",calculate);
		return inInfo;
	}
	
	/**
	 * 获取指定数据字典的指定项名称
	 * @Title: getGroupTypeItemName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param groupTypeCode
	 * @param:  @param typeCode
	 * @param:  @return
	 * @return: String  
	 * @throws
	 */
	private String getGroupTypeItemName(String groupTypeCode, String typeCode){
		// 实例化EiInfo
	    EiInfo eiInfo = new EiInfo();
	    // 初始化服务名
		String serviceId = "S_ED_02";
		String condition = "ITEM_CODE='"+typeCode+"'";
		eiInfo.set(EiConstant.serviceId,serviceId);
		eiInfo.set("codeset",groupTypeCode);
		eiInfo.set("condition",condition);
		//服务接口调用
		EiInfo outInfo = XServiceManager.call(eiInfo);
		// 获取参数
		List<Map<String,Object>> listValue = (List<Map<String, Object>>) outInfo.get("list");
		// 如果参数不存在
		if(listValue == null || listValue.size() == 0){
			// 返回
		    return "";
		}
		// 获取参数
		Map<String, Object> mapValue = listValue.get(0);
		String returnValues =(String) mapValue.get("label");
		// 返回
		return returnValues;
	}

}
