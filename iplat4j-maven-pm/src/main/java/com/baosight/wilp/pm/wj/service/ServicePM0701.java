package com.baosight.wilp.pm.wj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.domain.TpmCalculate;
import com.baosight.xservices.xs.util.UserSession;
/**
 * 
 * 工程项目：初始化查询、编辑
 * <p>1.初始化查询 initLoad
 * <p>2.编辑 editArchive
 *  
 * @Title: ServicePM0701.java
 * @ClassName: ServicePM0701
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午5:05:16
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0701 extends ServiceBase {

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
		//获取数据
		Map<String, Object> map = new HashMap<>();
		// 设置参数
		map.put("id", inInfo.get("id"));
		map.put("docStatusCode", inInfo.get("docStatusCode"));
		// 查询数据
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
		
		eiColumn = new EiColumn("dataSubmitterCodeBack_textField");
		inInfo.getBlock("inqu_status").addMeta(eiColumn);
		
		eiColumn = new EiColumn("dataRecipientCodeBack_textField");
		inInfo.getBlock("inqu_status").addMeta(eiColumn);
		
		inInfo.getBlock("inqu_status").setCell(0, "submitterCode_textField", list.get(0).getSubmitter());
		inInfo.getBlock("inqu_status").setCell(0, "recipientCode_textField", list.get(0).getRecipient());
		inInfo.getBlock("inqu_status").setCell(0, "dataSubmitterCode_textField", list.get(0).getDataSubmitter());
		inInfo.getBlock("inqu_status").setCell(0, "dataRecipientCode_textField", list.get(0).getDataRecipient());
		inInfo.getBlock("inqu_status").setCell(0, "dataCode_textField", list.get(0).getDataName());
		inInfo.getBlock("inqu_status").setCell(0, "dataSubmitterCodeBack_textField", list.get(0).getDataSubmitterCodeBackName());
		inInfo.getBlock("inqu_status").setCell(0, "dataRecipientCodeBack_textField", list.get(0).getDataRecipientCodeBackName());
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
		// 如果block不为空
		if(block != null && block.getRowCount() > 0){
		    // map赋值
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
        calculate.setDataRecipientCodeBackName((String)map.get("dataRecipientCodeBack_textField"));
        calculate.setDataSubmitterCodeBackName((String)map.get("dataSubmitterCodeBack_textField"));
        calculate.setDataName((String)map.get("dataCode_textField"));
		calculate.setRecRevisor((String)staffByUserId.get("name"));
		calculate.setRecReviseTime(DateUtils.curDateTimeStr19());
		dao.update("PM05.updateCalculate",calculate);
		return inInfo;
	}

}
