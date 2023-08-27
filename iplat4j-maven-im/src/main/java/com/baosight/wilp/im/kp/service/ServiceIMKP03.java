package com.baosight.wilp.im.kp.service;


import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.kp.domain.IMKP03;

/**
 * 
 * 获取项目：跳转项目页面、查询项目
 * <p>1.跳转项目页面 initLoad
 * <p>2.查询项目   query
 * @Title: ServiceDIKP03.java
 * @ClassName: ServiceDIKP03
 * @Package：com.baosight.wilp.di.kp.service
 * @author: LENOVO
 * @date: 2021年8月10日 上午11:30:27
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMKP03 extends ServiceBase{
	private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");
	
	/**
	 * 跳转项目页面
	 * &lt;p&gt;Title: initLoad&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * @return
	 * itemidXmid   项目id
     * cardid       卡片 id
     * jitemname    项目名称
     * itemdesc     项目说明
     * referencevalue   参考值
     * actualvalueunit   实际值单位
     * sortno     排序字段
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {		
		EiInfo outInfo = super.query(inInfo, "IMKP03.query",new IMKP03());
		return outInfo;
	}
	
	/**
	 * 查询项目
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * jitemname    项目名称
	 * @return
	 * itemidXmid   项目id
     * cardid       卡片 id
     * jitemname    项目名称
     * itemdesc     项目说明
     * referencevalue   参考值
     * actualvalueunit   实际值单位
     * sortno     排序字段
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "IMKP03.query",new IMKP03());
		return outInfo;
	}
	
//	@Override
//	public EiInfo delete(EiInfo inInfo) {
//		EiInfo outInfo = super.delete(inInfo, "DIKP03.delete");
//		return outInfo;
//	}
//	
//	@Override
//	public EiInfo update(EiInfo inInfo) {
//		EiInfo outInfo = super.update(inInfo, "DIKP03.update");
//		return outInfo;
//	}
//	
//	@Override
//	public EiInfo insert(EiInfo inInfo) {
//		EiInfo outInfo = super.insert(inInfo, "DIKP03.insert");
//		return outInfo;
//	}

}
