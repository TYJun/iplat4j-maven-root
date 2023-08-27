package com.baosight.wilp.hi.bz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.hi.common.domain.HiFile;
import com.baosight.wilp.hi.common.domain.HiStandard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医院标准管理详情页面Service
 * 
 * <p>页面加载</p>
 * 
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceHIBZ0103.java
 * @ClassName:  ServiceHIBZ0103
 * @Package com.baosight.wilp.hi.bz.service
 * @Description: TODO
 * @author liangyongfei
 * @date:   2022年8月21日 下午1:13:50
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceHIBZ0103 extends ServiceBase {

	/**
	 * 页面加载
	 *
	 * <p>当页面是查看页面时回显页面数据</p>
	 * 	 * 1、获取参数（类型）
	 * 	 * 2、判断是否是编辑或查看
	 * 	 * 3、是。回显数据
	 * 	 * 4、否。返回
	 *
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param inInfo
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
    public EiInfo initLoad(EiInfo inInfo) {
		//1、获取参数(类型)
		String type = inInfo.getString("type");
		//2、判断是否为编辑或查看
		if("edit".equals(type) || "see".equals(type)){
			//2.1、是。回显数据
			Map<String, String> pMap = new HashMap<>(4);
			//获取id
			pMap.put("id", inInfo.getString("id"));
			//通过id，获取标准信息(标准名称、标识分类、备注)
	        List<HiStandard> list = dao.query("HIBZ01.query", pMap);
			//通过id，获取附件信息
			List<HiFile> list1 = dao.query("HIBZ01.queryId", pMap);
	        HiStandard hiStandard = list.get(0);
	        inInfo.getAttr().putAll(hiStandard.toMap());
			EiBlock block = new EiBlock("resultB");
			block.setRows(list1);
			inInfo.setBlock(block);
		}
		//2.2、返回
		return inInfo;
    }



}
