package com.baosight.wilp.pm.js.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pm.domain.Tpm01;
import com.baosight.wilp.pm.domain.TpmCalculate;
import com.baosight.wilp.pm.domain.TpmVia;
import com.baosight.wilp.util.PMUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 工程项目：初始化查询、单据页面查询、归档页面查询(主表格)、归档页面查询(子表格)、提交归档结算、删除归档结算
 * <p>1.初始化查询 initLoad
 * <p>2.单据页面查询 query
 * <p>3.归档页面查询(主表格) queryCalculate
 * <p>4.归档页面查询(子表格) queryCalculateDetail
 * <p>5.提交归档结算 submitArchive
 * <p>6.删除归档结算 deleteArchive
 * 
 * @Title: ServicePM05.java
 * @ClassName: ServicePM05
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午4:32:28
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM05 extends ServiceBase {

    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 实例化EiBlock
		EiBlock inquStatus = new EiBlock("inqu_status");
		EiBlock inquStatus1 = new EiBlock("inqu_status1");
		EiBlock result = new EiBlock("result");
		EiBlock resultA = new EiBlock("resultA");
		EiBlock resultB = new EiBlock("resultB");
		//创建字段
		EiColumn eiColumn;
		EiBlockMeta eiMetadata = new EiBlockMeta();
		EiBlockMeta metadata = new EiBlockMeta();
		//inquStatus
	 	eiColumn = new EiColumn("projectName");
        eiColumn.setDescName("项目名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectProp");
        eiColumn.setDescName("项目性质");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectTypeNum");
        eiColumn.setDescName("项目类型编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("projectStatus");
        eiColumn.setDescName("项目状态编码");
        eiMetadata.addMeta(eiColumn);
        inquStatus.addBlockMeta(eiMetadata);
        inquStatus.setCell(0, "projectStatus", "06");
        //inquStatus1
        eiColumn = new EiColumn("documentsCode");
        eiColumn.setDescName("文档编码");
        metadata.addMeta(eiColumn);

        eiColumn = new EiColumn("documentsName");
        eiColumn.setDescName("文档名称");
        metadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("submitter");
        eiColumn.setDescName("提交人");
        metadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("docStatusCode");
        eiColumn.setDescName("归档文件状态编码");
        metadata.addMeta(eiColumn);
        inquStatus1.addBlockMeta(metadata);
        inquStatus1.setCell(0, "documentsCode", "XX");
        inquStatus1.setCell(0, "docStatusCode", "01");
        //result
        result.addBlockMeta(new Tpm01().eiMetadata);
        //resultA
        resultA.addBlockMeta(new TpmCalculate().eiMetadata);
        //resultB
        resultB.addBlockMeta(new Tpm01().eiMetadata);
        inInfo.addBlock(inquStatus);
        inInfo.addBlock(inquStatus1);
        inInfo.addBlock(result);
        inInfo.addBlock(resultA);
        inInfo.addBlock(resultB);
		return inInfo;
	}

	/**
	 * 单据页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
	    // 调用查询方法
		return super.query(inInfo,"PM01.query",new Tpm01());
	}

	/**
	 * 归档页面查询(主表格)
	 * @Title: queryProjectStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryCalculate(EiInfo inInfo) {
		//获取参数
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status1", "resultA");
		//查询数据
		List<TpmCalculate> list = dao.query("PM05.queryCalculateList",map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		//获取总数
		int count = dao.count("PM05.queryCalculateCount", map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultA", new TpmCalculate().eiMetadata, list, count);
	}
	
	/**
	 * 归档页面查询(子表格)
	 * @Title: queryCalculateDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryCalculateDetail(EiInfo inInfo) {
		//获取参数
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status1", "resultB");
		//查询数据
		List<TpmVia> list = dao.query("PM05.queryProjectListByDocumentsCode",map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		//获取总数
		int count = dao.count("PM05.queryProjectCountByDocumentsCode", map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultB", new Tpm01().eiMetadata, list, count);
	}
	
	/**
	 * 提交归档结算
	 * @Title: submitArchive
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo submitArchive(EiInfo inInfo) {
		//参数处理
		String ids = inInfo.get("ids").toString();
		// 实例化map
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置参数
		map.put("ids", ids);
		map.put("docStatusCode", "02");
		// 更新档案状态
		dao.update("PM05.updateCalculateStatus", map);
		//发送短信
		List<String> pidList = dao.query("PM05.queryProjectIdByDocumentId", map);
//		for (String pid : pidList) {
//			SmsCallUtils.smsCall("projectAchive", pid);
//		}
		return inInfo;
	}
	
	/**
	 * 删除归档结算
	 * @Title: deleteArchive
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo deleteArchive(EiInfo inInfo) {
		//参数处理
		String ids = inInfo.get("ids").toString();
		// 实例化map
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置参数
		map.put("ids", ids);
		map.put("docStatusCode", "03");
		// 更新状态
		dao.update("PM05.updateCalculateStatus", map);
		dao.update("PM05.updateProjectStatus", map);
		return inInfo;
	}
	
}
