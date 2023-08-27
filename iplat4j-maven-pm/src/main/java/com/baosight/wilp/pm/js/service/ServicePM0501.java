package com.baosight.wilp.pm.js.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.domain.TpmCalculate;
import com.baosight.wilp.pm.domain.TpmCalculateDel;
import com.baosight.wilp.util.PMUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
 * 工程项目：初始化查询、归档结算、获取归档编号
 * <p>1.初始化查询 initLoad
 * <p>2.归档结算 archiveSettlement
 * <p>3.获取归档编号 generateCode
 * 
 * @Title: ServicePM0501.java
 * @ClassName: ServicePM0501
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午4:41:31
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0501 extends ServiceBase {

    /**
     * 
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
			// 实例化EiColumn
			EiColumn column1 = new EiColumn("projectNos");
			EiColumn column2 = new EiColumn("documentsName");
			// 实例化EiBlockMeta
			EiBlockMeta blockMate = new EiBlockMeta();
			blockMate.addMeta(column1);blockMate.addMeta(column2);
			pBlock.setBlockMeta(blockMate);
			pBlock.setCell(0, "projectNos", inInfo.get("projectNos"));
			pBlock.setCell(0, "documentsName", inInfo.get("projectName"));
			inInfo.addBlock(pBlock);
		} else {
			block.setCell(0, "projectNos", inInfo.get("projectNos"));
			block.setCell(0, "documentsName", inInfo.get("projectName"));
		}
		return inInfo;
	}
	
	/**
	 * 归档结算
	 * @Title: archiveSettlement
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo archiveSettlement(EiInfo inInfo){
		//参数处理
		EiBlock block = inInfo.getBlock("inqu_status");
		// 实例化map
		Map<String, Object> map = new HashMap<String, Object>();
		// 如果block不为空
		if(block != null && block.getRowCount() > 0){
		    // 设置map
			map = block.getRow(0);
		}
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//赋值
		TpmCalculate calculate = new TpmCalculate();
		// 参数类型转换
		calculate.fromMap(map);
		calculate.setId(UUID.randomUUID().toString());
		calculate.setDocumentsCode(generateCode());
		calculate.setSubmitterName((String)map.get("submitterCode_textField"));
		calculate.setRecipientName((String)map.get("recipientCode_textField"));
		calculate.setDocStatusCode("01");
		calculate.setRecCreator((String)staffByUserId.get("name"));
		calculate.setRecCreateTime(DateUtils.curDateTimeStr19());
		calculate.setDataGroupCode(PMUtils.getDataGroup());
		//保存归档主表
		dao.insert("PM05.insertCalculate", calculate);
		//更新项目状态和保存项目归档中间表
		String projectNos = map.get("projectNos").toString();//获取项目号
		String[] projectList = projectNos.split(",");
		//参数
		Map<String,Object> pMap = new HashMap<String, Object>();
		pMap.put("projectStatus", "07");
		//项目归档中间表赋值
		TpmCalculateDel calculateDel = new TpmCalculateDel();
		calculateDel.setRecCreator((String)staffByUserId.get("name"));
		calculateDel.setRecCreateTime(DateUtils.curDateTimeStr19());
		calculateDel.setDataGroupCode(PMUtils.getDataGroup());
		calculateDel.setDocStatusCode("01");
		calculateDel.setDocumentsCode(calculate.getDocumentsCode());
		//数据操作
		for (String projectNo : projectList) {
			pMap.put("projectNo", projectNo);
			calculateDel.setId(UUID.randomUUID().toString());
			calculateDel.setProjCode(projectNo);
			//数据库操作
			dao.update("PM02.updateProjectStatus", pMap);
			dao.insert("PM05.insertCalculateDetail", calculateDel);
		}
		//统计乙方提交人员和甲方收件人员的选择的次数
		inInfo.setMsg("归档结算成功");
		return inInfo;
	}
	
	//获取归档编号
	private String generateCode() {
	    // 加锁
		synchronized (dao){
		    // 获取时间
			String date = DateUtils.curDateStr8();
			// 查询数据
			List<String> list = dao.query("PM0501.queryDocumentNo","DOC"+date);
			// 如果数据为空
			if(list==null || list.size() == 0 || list.get(0) == null){
				// 返回归档编号
			    return "DOC"+date+"0001";
			} else {
			    // 获取最大归档编号
				String maxNo = list.get(0);
				// 切割最大归档编号
				maxNo = maxNo.substring(3);
				// 返回归档编号
				return "DOC"+ (Long.parseLong(maxNo)+1L);
			}
		}
	}

 
}
