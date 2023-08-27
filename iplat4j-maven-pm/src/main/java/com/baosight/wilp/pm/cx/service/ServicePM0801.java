package com.baosight.wilp.pm.cx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pm.domain.*;
import com.baosight.wilp.util.PMUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 工程项目：初始化查询、查询执行人 、查询知会人、查询项目附件信息、查询督办信息、查询项目甲方材料、查询项目乙方材料、查询项目进程、查询项目资料信息、查询验收人信息、TabGrid查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.查询执行人 queryStaff
 * <p>3.查询知会人 queryKnow
 * <p>4.查询项目附件信息 queryFile
 * <p>5.查询督办信息 queryVia
 * <p>6.查询项目甲方材料 queryJStuff
 * <p>7.查询项目乙方材料 queryYStuff
 * <p>8.查询项目进程 queryCed
 * <p>9.查询项目资料信息 queryZiliao
 * <p>10.查询验收人信息 queryAcceptStaff
 * <p>11.TabGrid查询方法 queryTabGrid
 * 
 * @Title: ServicePM0801.java
 * @ClassName: ServicePM0801
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午5:10:02
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0801 extends ServiceBase {

    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
		//获取参数
		String id = String.valueOf(inInfo.get("id"));
		//构建Block
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new Tpm01().eiMetadata);
		Map<String,Object> param = new HashMap<>();
		param.put("id", id);
		List<Tpm01> list = dao.query("PM01.query", param);//获取数据
		block.addRows(list);//数据返回
		//特殊数据处理
		EiColumn column1 = new EiColumn("contorId_textField");
		EiColumn column2 = new EiColumn("projectObjectCons_textField");
		EiColumn column3 = new EiColumn("undertakeDeptNum_textField");
		EiColumn column4 = new EiColumn("supplierNum_textField");
		block.addMeta(column1);block.addMeta(column2);block.addMeta(column3);block.addMeta(column4);
		block.setCell(0, "contorId_textField", list.get(0).getProjectPrincipal());
		block.setCell(0, "projectObjectCons_textField", list.get(0).getProjectObjectConsName());
		block.setCell(0, "undertakeDeptNum_textField", list.get(0).getUndertakeDeptName());
		block.setCell(0, "supplierNum_textField", list.get(0).getSupplierName());
			
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
	 * 查询执行人
	 * @Title: queryStaff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryStaff(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0103.queryStaffList","PM0103.queryStaffCount","resultA",new TpmStaff().eiMetadata);
	}
	
	/**
	 * 查询知会人
	 * @Title: queryKnow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryKnow(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0103.queryKnowList","PM0103.queryKnowCount","resultB",new TpmKnow().eiMetadata);
	}
	
	/**
	 * 查询项目附件信息
	 * @Title: queryFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryFile(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0103.queryFileList","PM0103.queryFileCount","resultC",new TpmAtt().eiMetadata);
	}
	
	/**
	 * 查询督办信息
	 * @Title: queryVia
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryVia(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0301.queryViaList","PM0301.queryViaCount","resultD",new TpmVia().eiMetadata);
	}
	
	/**
	 * 查询项目材料
	 * @Title: queryJStuff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryJStuff(EiInfo inInfo){
		//构建参数对象
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", "resultE");
		map.put("supType", "1");
		//数据查询
		List<TpmStuff> list = dao.query("PM0201.queryStuffList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		int count = dao.count("PM0201.queryStuffCount", map);
		//返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultE", new TpmStuff().eiMetadata, list, count);
	}
	
	/**
     * 查询项目材料
     * @Title: queryYStuff
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	public EiInfo queryYStuff(EiInfo inInfo){
		//构建参数对象
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", "resultF");
		map.put("supType", "2");
		//数据查询
		List<TpmStuff> list = dao.query("PM0201.queryStuffList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		int count = dao.count("PM0201.queryStuffCount", map);
		//返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultF", new TpmStuff().eiMetadata, list, count);
	}
	
	/**
	 * 查询项目进程
	 * @Title: queryCed
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryCed(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0401.queryCedList","PM0401.queryCedCount","resultG",new TpmCed().eiMetadata);
	}
	
	/**
	 * 查询项目资料信息
	 * @Title: queryZiliao
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryZiliao(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0401.queryZiliaoList","PM0401.queryZiliaoCount","resultH",new TpmZiliao().eiMetadata);
	}
	
	/**
	 * 查询验收人信息
	 * @Title: queryAcceptStaff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryAcceptStaff(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0401.queryAcceptStaffList","PM0401.queryAcceptStaffCount","resultI",new TpmAcceptStaff().eiMetadata);
	}
	
	
	/**
     * TabGrid查询方法
     * @Title: queryTabGrid
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql,String countSql, String resultBlock, EiBlockMeta blockMeta){
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		//查询数据
		List<TpmStaff> list = dao.query(querySql, map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		//获取总数
		int count = dao.count(countSql, map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}

}
