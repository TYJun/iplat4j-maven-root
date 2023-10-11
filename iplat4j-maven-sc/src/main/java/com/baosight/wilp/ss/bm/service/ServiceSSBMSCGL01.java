package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMBqgl01;
import com.baosight.wilp.ss.bm.domain.SSBMCtm01;
import com.baosight.wilp.ss.bm.domain.SSBMSCGL01;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * 送餐人员管理service
 * 
 * @Title: ServiceSSBMSCGL01.java
 * @ClassName: ServiceSSBMSCGL01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:21:36
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMSCGL01 extends ServiceBase{
	

    /**
     * 
     * 初始化查询
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo, new SSBMCtm01());
		
//		EiBlock result3 = initLoad.addBlock("result3");
//		result3.addBlockMeta(new SSBMBqgl01().eiMetadata);
		return initLoad;
	}
	

	/**
	 * 
	 * grid数据集查询送餐人员
	 * @param inInfo
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		//grid数据集查询
		EiInfo outInfo = super.query(inInfo, "SSBMSCGL01.query", new SSBMSCGL01());
		return outInfo;
	}
	

	/**
	 * 
	 * 删除数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#delete(EiInfo)
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		return super.delete(inInfo, "SSBMSCGL01.delete");
	}
	

	/**
	 * 
	 * 新增数据
	 * @param inInfo
	 * @return
	 * @see ServiceBase#insert(EiInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		
		
		Map attr = inInfo.getAttr();
		//接收弹窗数据
		ArrayList<HashMap<String,Object>> submitList = (ArrayList<HashMap<String,Object>>) attr.get("submit");
		//接收科室组成数据
		ArrayList<HashMap<String,Object>> deptList = (ArrayList<HashMap<String, Object>>) attr.get("result2");
		
		try {
			if(submitList != null && submitList.size() > 0) {
				HashMap<String,Object> map = submitList.get(0);
				SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format = smf.format(new Date());
				String workName = map.get("workName").toString();
				String workNo = map.get("workNo").toString();
				
				HashMap<String,Object> param = new HashMap<String,Object>();
				
				param.put("workName", workName);
				param.put("workNo", workNo);
				param.put("createTime", format);
				
				param.put("deptType", "0");
				//查询当前工号已配置的科室
				List<?> signedDepts = dao.query("SSBMSCGL01.getSignedDepts", param);
				
				if(deptList !=null && deptList.size() > 0){
					for (int i = 0; i < deptList.size(); i++) {
						param.put("id", UUIDUtils.getUUID32());
						param.put("deptNum", deptList.get(i).get("deptCode"));
						param.put("deptName", deptList.get(i).get("deptName"));
						param.put("datagroupCode", deptList.get(i).get("datagroupCode"));
						param.put("datagroupTreecode", deptList.get(i).get("datagroupCode"));
						//过滤掉已有的科室
						if(signedDepts.size()>0){
							if(signedDepts.contains(deptList.get(i).get("deptCode"))){
								continue;
							}else{
								dao.insert("SSBMSCGL01.insert", param);
							}
						}else{
							dao.insert("SSBMSCGL01.insert", param);
						}
					}
				}else {
					outInfo.setStatus(-1);
					outInfo.setMsg("科室为空！");
					return outInfo;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			outInfo.setStatus(-1);
			outInfo.setMsg("操作失败！");
			return outInfo;
		}
		outInfo.setStatus(1);
		outInfo.setMsg("操作成功！");
		return outInfo;
	}
	
	

	/**
	 * 
	 * 查询科室信息
	 *
	 * @Title: query2 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午4:23:49
	 */
	public EiInfo query2(EiInfo inInfo) {
		EiBlock block = inInfo.getBlock("inqu_status");
		block.setCell(0, "deptName",null);
		block.setCell(0, "workName",null);
		EiInfo outInfo = super.query(inInfo, "SSBMBqgl01.query", new SSBMBqgl01(),false, null, EiConstant.queryBlock, "result3", "result3");
		return outInfo;
	}
	
}
