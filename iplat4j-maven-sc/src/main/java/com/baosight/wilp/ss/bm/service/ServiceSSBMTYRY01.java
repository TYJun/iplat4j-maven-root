package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMTyRy01;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import org.apache.commons.lang.StringUtils;


/**
 * 
 * 通用人员service
 * 
 * @Title: ServiceSSBMTYRY01.java
 * @ClassName: ServiceSSBMTYRY01
 * @Package：com.baosight.wilp.ss.bm.service
 * @author: liutao
 * @date: 2021年9月9日 下午4:30:37
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSBMTYRY01 extends ServiceBase{
	
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo initLoad = super.initLoad(inInfo,new SSBMTyRy01());
		return initLoad;
	}
	
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiBlock block = inInfo.getBlock("inqu_status");
		
		String workNo = block.getCellStr(0, "workNo");
		if(StringUtil.isNotEmpty(workNo) && workNo.split(",").length > 1) {
			String[] s = workNo.split(",");
			for (int i = 0; i < s.length; i++) {
				s[i] = "'" + s[i] + "'";
			}
			//替换查询条件select * from aaa where id not in ('111','222','333')
			block.setCell(0, "workNos", "(" + StringUtils.join(s, ",") + ")");
			block.setCell(0, "workNo", "");
		}
		inInfo.setBlock(block);
		return super.query(inInfo, "SSBMTyRy01.query");
	}

	public void insert(Object paramObject) {
		dao.insert(paramObject);
	}
	
	public int update(Object paramObject) {
		return dao.update(paramObject);
	}
	
	public int delete(Object paramObject) {
		return dao.delete(paramObject);
	}
}
