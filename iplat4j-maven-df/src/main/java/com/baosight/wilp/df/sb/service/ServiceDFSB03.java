package com.baosight.wilp.df.sb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>页面初始化</p>
 * <p>页面查询</p>
 * (特种设备检验预警页面)
 *
 * @Title: ServiceDFSB03.java
 * @ClassName: ServiceDFSB03
 * @Package：com.baosight.wilp.df.sb.service
 * @author: 24128
 * @date: 2021年9月2日 下午4:30:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFSB03 extends ServiceBase{

	/**
	 * 页面初始化
	 * <p>1.调用本地查询方法</p>
	 * @Title: initLoad
	 * @Description: 页面初始化
	 * @param info
	 * Id 					id
	 * workNo              工号
	 * machineCode         特种设备编码
	 * machineId           特种设备Id
	 * machineName         特种设备名称
	 * innerMachineCode    特种设备内部编码
	 * checkType           检查类型
	 * statusCode          工单状态
	 * thisCheckDate       本次检验时间
	 * thisFinishDate      本次检验完工时间
	 * nextCheckDate       下次检验时间
	 * @return:
	 * Id 					id
	 * workNo              工号
	 * machineCode         特种设备编码
	 * machineId           特种设备Id
	 * machineName         特种设备名称
	 * innerMachineCode    特种设备内部编码
	 * checkType           检查类型
	 * statusCode          工单状态
	 * thisCheckDate       本次检验时间
	 * thisFinishDate      本次检验完工时间
	 * nextCheckDate       下次检验时间
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		return query(info);
	}
	        /**
        	 * 页面查询
			 * <p>1.构建参数查询出下次检验日期90天内得工单</p>
        	 * @Title: 页面查询
        	 * @param： info
        	 * checkType           检查类型
        	 * machineName         设备名称
        	 * machineCode         设备编码
        	 * thisFinishDateS     查询时间起
        	 * thisFinishDateE     查询时间止
        	 * @return
        	 * Id					id
        	 * workNo              工号
        	 * machineCode         特种设备编码
        	 * machineId           特种设备Id
        	 * machineName         特种设备名称
        	 * innerMachineCode    特种设备内部编码
        	 * checkType           检查类型
        	 * statusCode          工单状态
        	 * thisCheckDate       本次检验时间
        	 * thisFinishDate      本次检验完工时间
        	 * nextCheckDate       下次检验时间
        	 * @see ServiceBase#query(EiInfo)
        	 */
	 @SuppressWarnings("deprecation")
	public EiInfo query(EiInfo info) {

	    	 //获取前台数据
			String[] find = {"checkType","machineName","machineCode",
					"thisFinishDateS","thisFinishDateE"};
			//将数据封装到map中
			Map<String, Object> map = CommonUtils.buildParamter(info, Arrays.asList(find));
			//查询数据
			List<Map<String, Object>> list = dao.query("DFSB02.query03", map);
			//计数
			Integer count = dao.count("DFSB02.count", map);
			//判断是否为空
			if(list==null || list.size() == 0){
				return info;
			}
			//返回
			return CommonUtils.BuildOutEiInfo(list, count);
		}
}
