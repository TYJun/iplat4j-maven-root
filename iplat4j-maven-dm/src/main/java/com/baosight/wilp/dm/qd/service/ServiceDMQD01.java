package com.baosight.wilp.dm.qd.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.wilp.dm.gg.domain.DMGG01;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宿舍检查清单页面service
 *
 *@ClassName: ServiceDMQD01
 *@Package: com.baosight.wilp.dm.qd.service
 */
public class ServiceDMQD01 extends ServiceBase {
	/**
	 * 此方法用于宿舍检查清单页面初始化
	 *
	 * @Title: initLoad
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 页面数据回显查询
	 *
	 * <p>查询宿舍检查清单dorms_check_list表中对应RoomManId的所有项目记录</p>
	 *
	 * <p>Title: queryByRoomManId</p>
	 * <p>Description: </p>
	 * @param inInfo ： 无
	 * @return
	 * 		id ： 主键
	 *		configCode ： 配置项编码
	 *		configName ： 配置项名称
	 *		configValueRedio ： 配置项值（单选框的值）
	 *		configValueText : 配置项值（多选框、输入框的值）
	 *		dataGroupCode : 账套
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
	public EiInfo queryByRoomManId(EiInfo inInfo) {
		Map<Object, String> pMap = new HashMap<>();
		pMap.put("roomManId", inInfo.getString("roomManId"));
		List list = dao.query("DMQD01.queryByRoomManId", pMap);
		inInfo.set("list", list);
		return inInfo;
	}

	/**
	 * 保存
	 *
	 * <p>1.接收前台传过来的参数</br>
	 *  2.判断参数中的id是否为空。为空，将数据插入数据库表</br>
	 *  3.不为空，根据id更新数据库表中数据</p>
	 *
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		id ： 主键
	 * 	    roomManId : 宿舍人员绑定关系表id
	 *		itemCode ： 项目编码
	 *		itemName ： 物品/项目名称
	 *		exits ： 是否有该物品
	 *		intact : 是否完好/损坏
	 *		extraCharges ： 其他费用
	 *  	note ： 备注
	 *      operator ： 操作人工号
	 *      operation_name ： 操作人
	 *      operation_time ： 操作时间
	 * @param:  @return
	 * @return: EiInfo
	 * 		msg: 提示消息
	 * @throws
	 */
	public EiInfo save(EiInfo inInfo) {
		/*
		 * 1、获取当前用户信息.
		 */
		// 获取当前登陆工号
		String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
				UserSession.getUser().getUsername():(String)inInfo.get("workNo");
		Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
		inInfo.set("operator", loginName);
		String operationName = userInfo.get("name").toString(); /*操作人名称*/
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
		//接收前台传过来的参数
		String roomManId = inInfo.getString("roomManId");
		// 做后台校验，避免空值
		if (roomManId == null && roomManId.equals("")){
			inInfo.setStatus(-1);
			inInfo.setMsg("提交失败!");
			return inInfo;
		}
		List<Map<String, Object>> list = (List<Map<String, Object>>) inInfo.get("list");
		List<Map<String, Object>> addAllItems = new LinkedList<>();
		List<Map<String, Object>> updateAllItems = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			//判断参数中的id是否为空。为空，将数据插入数据库表
			if(StringUtils.isBlank((String)list.get(i).get("id"))) {
				Map<String, Object> item = new HashMap<>();
				item.put("id",UUID.randomUUID().toString());
				item.put("roomManId",roomManId);
				item.put("serialNumber",list.get(i).get("serialNumber"));
				item.put("itemName",list.get(i).get("itemName"));
				item.put("existence",list.get(i).get("exits"));
				item.put("isIntact",list.get(i).get("intact"));
				item.put("extraCharges",list.get(i).get("extraCharges"));
				item.put("note",list.get(i).get("note"));
				item.put("operator",loginName);
				item.put("operationName",operationName);
				item.put("operationTime",operationTime);
				addAllItems.add(item);
			}else {
				Map<String, Object> item = new HashMap<>();
				item.put("id",list.get(i).get("id"));
				item.put("roomManId",roomManId);
				item.put("serialNumber",list.get(i).get("serialNumber"));
				item.put("itemName",list.get(i).get("itemName"));
				item.put("existence",list.get(i).get("exits"));
				item.put("isIntact",list.get(i).get("intact"));
				item.put("extraCharges",list.get(i).get("extraCharges"));
				item.put("note",list.get(i).get("note"));
				item.put("operator",loginName);
				item.put("operationName",operationName);
				item.put("operationTime",operationTime);
				updateAllItems.add(item);
			}
		}
		if (addAllItems.size()>0){
			dao.insert("DMQD01.insertDormsCheckList", addAllItems);
		}
		if (updateAllItems.size()>0){
			dao.update("DMQD01.updateDormsCheckList", updateAllItems);
		}
		/*
		 * 调用本地服务DMQR0101.updateDormRoomMan更新检查清单的操作状态，由0变为1已检查。
		 */
		inInfo.set("id",roomManId);
		inInfo.set("checkoutRoomStatus","1");
		inInfo.set(EiConstant.serviceName, "DMQR0101");
		inInfo.set(EiConstant.methodName, "updateDormRoomMan");
		EiInfo outInfo = XLocalManager.call(inInfo);
		inInfo.setMsg("保存成功");
		return inInfo;
	}
	

	
}
