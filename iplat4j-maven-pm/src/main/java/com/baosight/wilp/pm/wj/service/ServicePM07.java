package com.baosight.wilp.pm.wj.service;

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
 * 工程项目：初始化查询、归档页面查询(主表格) 、归档页面查询(子表格)、提交
 * <p>1.初始化查询 initLoad
 * <p>2.归档页面查询(主表格) queryCalculate
 * <p>3.归档页面查询(子表格) queryCalculateDetail
 * <p>4.提交 endArchive
 * 
 * @Title: ServicePM07.java
 * @ClassName: ServicePM07
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午5:02:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM07 extends ServiceBase {

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
		EiBlock resultA = new EiBlock("resultA");
		EiBlock resultB = new EiBlock("resultB");
		//创建字段
		EiColumn eiColumn;
		EiBlockMeta eiMetadata = new EiBlockMeta();
        //inquStatus
        eiColumn = new EiColumn("documentsCode");
        eiColumn.setDescName("文档编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("documentsName");
        eiColumn.setDescName("文档名称");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("submitter");
        eiColumn.setDescName("提交人");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("docStatusCode");
        eiColumn.setDescName("归档文件状态编码");
        eiMetadata.addMeta(eiColumn);
        inquStatus.addBlockMeta(eiMetadata);
        inquStatus.setCell(0, "documentsCode", "XX");
        inquStatus.setCell(0, "docStatusCode", "04,05");
        //resultA
        resultA.addBlockMeta(new TpmCalculate().eiMetadata);
        //resultB
        resultB.addBlockMeta(new Tpm01().eiMetadata);
        inInfo.addBlock(inquStatus);
        inInfo.addBlock(resultA);
        inInfo.addBlock(resultB);
		return inInfo;
	}
	
	/**
	 * 归档页面查询(主表格)
	 * @Title: queryCalculate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryCalculate(EiInfo inInfo) {
		//获取参数
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", "resultA");
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
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", "resultB");
		//查询数据
		List<TpmVia> list = dao.query("PM05.queryProjectListByDocumentsCode",map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		//获取总数
		int count = dao.count("PM05.queryProjectCountByDocumentsCode", map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultB", new Tpm01().eiMetadata, list, count);
	}

	/**
	 * 提交
	 * @Title: audiaArchive
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo endArchive(EiInfo inInfo) {
		//参数处理
		String ids = inInfo.get("ids").toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("docStatusCode", "05");
		dao.update("PM05.updateCalculateStatus", map);
		//发送短信
//		List<String> pidList = dao.query("PM05.queryProjectIdByDocumentId", map);
//		for (String pid : pidList) {
//			SmsCallUtils.smsCall("projectFinal", pid);
//		}
		return inInfo;
	}

}
