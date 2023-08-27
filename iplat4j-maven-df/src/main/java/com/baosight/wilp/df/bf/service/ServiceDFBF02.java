/** 
* @author zhaowei E-mail: zhaowei0425@foxmail.com
* @version 创建时间：2021年8月5日 下午8:29:30 
* 类说明 
*/ 
package com.baosight.wilp.df.bf.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备报废管理编辑页面：初始化查询、设备信息查询、设备报废信息编辑
 * <p>1.初始化查询 initLoad
 * <p>2.设备信息查询 query
 * <p>3.设备报废信息编辑 compile
 * 
 * @Title: ServiceDFBF02.java
 * @ClassName: ServiceDFBF02
 * @Package：com.baosight.wilp.df.bf.service
 * @author: liangyongfei
 * @date: 2022年6月27日 上午9:22:29
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFBF02 extends ServiceBase{
    /**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param info
     * @return info
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 获取attr中的id
		String id = (String)info.getAttr().get("id");
		// info中设置id
		info.set("id",id);
		// 调用设备分类查询
        return this.query(info);
	}
	
	/**
     * 
     * @Title: query
     * @Description: 设备信息查询
	 * 通过获取设备主键id，来查询出设备报废信息
     */
	@Override
	public EiInfo query(EiInfo info) {
	    //获取参数
		String id = (String)info.get("id");
		//通过参数进行设备报废信息查询
		List<Map<String, String>> list = dao.query("DFBF01.queryId",id);
        //查询数据为空
		if(CollectionUtils.isEmpty(list)) {
			//返回数据
		    return info;
        }
		//返回数据列表
		info.setAttr(list.get(0));
		String moduleId = list.get(0).get("machineTypeId");
		Map<String, String> map = new HashMap<>();
		map.put("moduleId", moduleId);
		List<Map<String,String>> list2 = dao.query("DFFL10.query",map);
		info.addRows("result", list2);
		return info;
	}
	
	/**
     * 
     * @Title: compile
     * @Description: 设备报废编辑
     * @param info
     * id : 设备档案id
     * machineCode : 设备编码
     * machineName : 设备名称
     * fixedPlace : 安装地点
     * scrapDate : 报废日期
     * scrapWay : 报废方式
     * scrapReason : 报废原因
     * @return info
     */
	public EiInfo compile(EiInfo info) {
	    // 实例化map
		Map<String, String> map = new HashMap<String, String>();
		// 获取id
		String id = (String)info.get("id"); 
		// 设备编码
		//String scrapNo = createScrapNo();
		// 设备编码
		String machineCode = (String)info.get("machineCode");
		// 设备名称
		String machineName = (String)info.get("machineName_textField");
		// 规格型号
		String fixedPlace = (String) info.get("fixedPlace");
		// 设备状态
		String status = (String) info.get("status");
		// 报废日期
		String scrapDate = (String) info.get("scrapDate");
		// 报废方式
		String scrapWay = (String) info.get("scrapWay");
		// 报废原因
		String scrapReason = (String) info.get("scrapReason");
		// map赋值id(下同)
		map.put("id", id);
		map.put("machineCode", machineCode);
		map.put("machineName", machineName);
		map.put("fixedPlace", fixedPlace);
		map.put("status", status);
		map.put("scrapDate", scrapDate);
		map.put("scrapWay", scrapWay);
		map.put("scrapReason", scrapReason);
		map.put("recCreator", (String)BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername()).get("name"));
		map.put("recCreateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// 更新
		dao.update("DFBF02.update",map);
		// 返回
		return info;
	}
}
