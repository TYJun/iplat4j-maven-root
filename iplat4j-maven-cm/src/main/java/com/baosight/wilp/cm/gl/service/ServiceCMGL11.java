package com.baosight.wilp.cm.gl.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.cm.domain.CMGL11;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.UUID;

/**
 * 合同模块：初始化查询、查询方法、删除方法、保存
 * <p>1.初始化查询 initLoad
 * <p>2.查询方法 query
 * <p>3.删除方法 delete
 * <p>4.保存 sava
 *
 * @Title: ServiceCMGL11.java
 * @ClassName: ServiceCMGL11
 * @Package：com.baosight.wilp.cm.gl.service
 * @author: zhaow
 * @date: 2021年8月30日 下午3:00:25
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMGL11 extends ServiceBase {
	/**
	 * @param info
	 * @return
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		// 调用查询方法
		return query(inInfo, "CMGL11.query", new CMGL11());

	}

	/**
	 * @param info
	 * @return
	 * @Title: query
	 * @Description: 查询方法
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
		// 调用查询方法
		EiInfo eiInfo = query(inInfo, "CMGL11.query", new CMGL11());
		return eiInfo;
	}

	/**
	 * @param info
	 * @return
	 * @Title: delete
	 * @Description: 删除方法
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo delete(EiInfo inInfo) {
		// 调用删除方法
		return super.delete(inInfo, "CMGL11.delete");
	}

	/**
	 * 保存
	 *
	 * @param inInfo
	 * @return
	 * @Title: sava
	 * @Description:
	 * 1.
	 * @return: EiInfo
	 */
	public EiInfo sava(EiInfo inInfo) {
		// 创建EiBlock
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 实例化实体
		CMGL11 htgl11 = new CMGL11();
		// 实体进行参数转化
		htgl11.fromMap(param);
		// 如果参数为空
		if (StringUtils.isBlank(htgl11.getId())) {
			// 设置参数
			htgl11.setId(UUID.randomUUID().toString());
			// 调用插入方法
			dao.insert("CMGL11.insert", htgl11);
		}
		// 调用更新方法
		dao.insert("CMGL11.update", htgl11);
		// 返回参数
		return inInfo;
	}
}
