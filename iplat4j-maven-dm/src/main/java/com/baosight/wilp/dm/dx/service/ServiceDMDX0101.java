package com.baosight.wilp.dm.dx.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 编辑短信模板页面逻辑处理
 *
 *@ClassName: ServiceDMDX0101
 *@Package: com.baosight.wilp.dm.dx.service
 */
public class ServiceDMDX0101 extends ServiceBase {
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 页面数据回显查询
	 *
	 * <p>Title: query</p>
	 * @param inInfo ： 无
	 * @return
	 *     id ：主键
	 *     configType ：配置类型
	 *     configTypeName ：配置类型名称
	 *     smsTemp ：短信模板
	 *     isRunning ：是否启用: 1-启用，0-未启用
	 *     smsRecvCode ：短信接收人代码
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
		Map<Object, String> pMap = new HashMap<>();
		pMap.put("configType", inInfo.getString("configType"));
		List list = dao.query("DMDX01.query", pMap);
		inInfo.set("list", list);
		return inInfo;
	}

	/**
	 * 保存短信配置
	 *
	 * <p>1.接收前台传过来的参数</br>
	 *    2.判断参数中的id是否为空。为空，将数据插入数据库表</br>
	 *    3.不为空，根据id更新数据库表中数据</p>
	 *
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		id ： 主键
	 *		configType ： 短息配置项编码
	 *		configTypeName ： 短信配置项名称
	 *		smsTemp ： 短信内容模板
	 *		statusCode ： 状态
	 *		lateDays ： 评分/超期天数
	 *		isRuning ： 是否启用
	 *		smsRecvCode ： 短信接收人角色编码
	 * @param:  @return
	 * @return: EiInfo
	 * 		msg: 提示消息
	 * @throws
	 */
	@SuppressWarnings("all")
	public EiInfo save(EiInfo inInfo) {
		//接收前台传过来的参数
		Map<String, Object> map = (Map<String, Object>) inInfo.get("list");
		//判断参数中的id是否为空。为空，将数据插入数据库表
		if(map.get("id") == null || StringUtils.isBlank(map.get("id").toString())){
			map.put("id", UUID.randomUUID().toString());
			map.put("configType",map.get("smsCode"));
			map.put("configTypeName",map.get("smsName"));
			map.put("smsTemp",map.get("smsTemp"));
			map.put("isRunning",map.get("running"));
			map.put("smsRecvCode",map.get("smsRecvCode"));
			dao.insert("DMDX01.insert", map);
		} else {
			map.put("configType",map.get("smsCode"));
			map.put("configTypeName",map.get("smsName"));
			map.put("isRunning",map.get("running"));
			//不为空，根据id更新数据库表中数据
			dao.update("DMDX01.update", map);
		}
		inInfo.setMsg("保存成功");
		return inInfo;
	}

	
}
