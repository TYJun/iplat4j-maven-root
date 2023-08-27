package com.baosight.wilp.df.bf.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.DatagroupUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备报废信息保存：初始化查询、设备信息查询、设备报废信息保存、创建报废单号
 * <p>1.初始化查询 initLoad
 * <p>2.设备信息查询 query
 * <p>3.设备报废信息保存 insert
 * <p>4.创建报废单号 createScrapNo
 * 
 * @Title: ServiceDFBF0101.java
 * @ClassName: ServiceDFBF0101
 * @Package：com.baosight.wilp.df.bf.service
 * @author: liangyongfei
 * @date: 2022年6月27日 上午8:45:02
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFBF0101 extends ServiceBase{
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param info
     * @return info
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 调用父类初始化方法
		return super.initLoad(info);
	}

	/**
	 *
	 * @Title: query
	 * @Description: 设备信息查询
	 * 通过获取设备主键id，来查询出设备报废信息
	 */
	@Override
	public EiInfo query(EiInfo info) {
	    // 获取设备类型分类id
		String machineTypeId = (String)info.getAttr().get("machineTypeId");
		// info设置machineTypeId参数
		info.set("queryModuleId",machineTypeId);
		// 调用本地类
		info.set(EiConstant.serviceName, "DFFL10");
		// 调用本地类的方法
		info.set(EiConstant.methodName, "query");
        // 获取返回值
		EiInfo outInfo =XLocalManager.call(info);
        // 返回值
		return outInfo;
	}

	/**
	 *
	 * @Title: insert
	 * @Description: 设备报废保存
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
	@Override
	public EiInfo insert(EiInfo info) {
	    // 实例化map
		Map<String, String> map = new HashMap<String, String>(32);
		// 生成uuid
		String id = UUID.randomUUID().toString();
		//生成报废单号
		String scrapNo = createScrapNo();
		// 设备编码
		String machineCode = (String)info.get("machineCode");
		// 设备名称
		String machineName = (String)info.get("machineName_textField");
		// 安装地点
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
		map.put("scrapNo", scrapNo);
		map.put("machineCode", machineCode);
		map.put("machineName", machineName);
		map.put("fixedPlace", fixedPlace);
		map.put("status", status);
		map.put("scrapDate", scrapDate);
		map.put("scrapWay", scrapWay);
		map.put("scrapReason", scrapReason);
		//map.put("recCreator", (String)BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername()).get("name"));
		map.put("recCreateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		map.put("dataGroupCode", DatagroupUtil.getDatagroupCode());
		//校验设备编码是否存在
		int count = dao.count("DFBF01.isExist", machineCode);
		//List<Map<String, Object>> machineName = dao.query("DFBF01.queryDeviceName", map);
		if(count > 0){
			info.setStatus(-1);
			info.setMsg("设备编码已存在");
			return info;
		}
		// 插入数据
		dao.insert("DFBF01.insert",map);
		//更新设备地点表
		//updateDeviceSpot(info);
		// 返回
		return info;
	}



	/**
	 * @param
	 * @Title: createScrapNo
	 * @Description: 生成报废单号
	 * <p>1.加锁
	 * <p>2.调用时间接口
	 * <p>3.调用查询方法
	 * <p>4.如果结果为空
	 * <p>5.获取最大报废单号号
	 * <p>6.对最大报废号进行拆分
	 * 通过获取当前时间
	 * 判断今天是否存在报废单号，不存在就返回新的报废单号
	 * 存在报废单号，对报废单号进行累加
	 * @return: String
	 * String 报废单号
	 */

	private String createScrapNo() {
		synchronized (dao) {
			// 调用时间接口
			String date = DateUtils.curDateStr8();
			// 调用查询方法
			List<String> list = dao.query("DFBF01.createScrapNo", "BF" + date);
			// 如果结果为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
				// 返回合同号
				return "BF" + date + "0001";
			} else {
				// 获取最大报废单号
				String maxNo = list.get(0);
				// 对最大报废单号进行拆分
				maxNo = maxNo.substring(2);
				// 返回报废单号
				return "BF" + (Long.parseLong(maxNo) + 1L) + "";
			}
		}

	}

}
