package com.baosight.wilp.df.bf.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 设备报废查看页面：初始化查询、设备报废信息查看
 * <p>1.初始化查询 initLoad
 * <p>2.设备报废信息查看 detail
 * @Title: ServiceDFBF0102.java
 * @ClassName: ServiceDFBF0102
 * @Package：com.baosight.wilp.df.bf.service
 * @author: liangyongfei
 * @date: 2022年6月27日 上午10:48:30
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFBF0102 extends ServiceBase{

	/**
	 *
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @param info
	 * scrapNo : 报废单号
	 * machineName : 设备名称
	 * status : 状态
	 * scrapWay : 报废方式
	 * scrapDateS : 报废日期起
	 * scrapDateE : 报废日期止
	 * @return info
	 * scrapNo : 报废单号
	 * machineName : 设备名称
	 * fixedPlace : 安装地点
	 * status : 状态
	 * scrapWay : 报废方式
	 * scrapDateS : 报废日期
	 * scrapDateE : 报废原因
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 获取info中的id
		String id = (String)info.getAttr().get("id");
		// 设置info中的id
		info.set("id",id);
		// 调用设备档案查看方法
        return this.detail(info);
	}
	/**
	 * 
	 * @Title: detail
	 * @Description: 设备报废信息查看
	 * @param info
	 * id : 设备档案id
	 * @return info
	 * id : 设备档案id
	 * machineCode : 设备编码
	 * machineName : 设备名称
	 * fixedPlace : 安装地点
	 * scrapDate : 报废日期
	 * scrapWay : 报废方式
	 * scrapReason : 报废原因
	 */
	public EiInfo detail(EiInfo info) {
	    // 获取info的id
		String id = (String)info.get("id");
		// 查询设备报废信息
		List<Map<String, String>> list = dao.query("DFBF01.queryId",id);
        // 如果集合为空
		if(CollectionUtils.isEmpty(list)) {
            // 返回
		    return info;
        }
		// 设置info的attr
		info.setAttr(list.get(0));
		// 获取设备分类id
		String moduleId = list.get(0).get("machineTypeId");
		// 实例化map
		Map<String, String> map = new HashMap<>();
        // map赋值moduleId
		map.put("moduleId", moduleId);
        // 查询参数值
		List<Map<String,String>> list2 = dao.query("DFFL10.query",map);
        // info添加结果
		info.addRows("result", list2);
		// 返回
		return info;
	}
}
