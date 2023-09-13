package com.baosight.wilp.dm.ts.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 退宿审核子页面结算退宿service
 *
 *@ClassName: ServiceDMTS0201
 *@Package: com.baosight.wilp.dm.ts.service
 */
public class ServiceDMTS0201 extends ServiceBase {
	/**
	 * 此方法用于页面初始化
	 *
	 * 逻辑处理：
	 * 1.调用queryInfoCurrentFee方法
	 *
	 * @Title: initLoad
	 * @param: EiInfo inInfo
	 * @return: EiInfo inInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return queryInfoCurrentFee(inInfo);
	}

    /**
     * 获取该宿舍人员绑定关系表id主键对应设置的实际月租和实际费用
     *
	 * 1.获取参数
	 * 2.调用sql方法DMTS02.queryInfoCurrentFee查询实际月租和实际费用
	 *
     * @Title: queryInfoCurrentFee
     * @Param EiInfo
     * @return: EiInfo
     */
	public EiInfo queryInfoCurrentFee(EiInfo inInfo) {
		Map<String, String> map = new HashMap<>();
		/*
		 * 1.获取参数
		 */
		map.put("id", inInfo.getString("id"));
		EiInfo outInfo = new EiInfo();
		/**
		 * 2.调用sql方法DMTS02.queryInfoCurrentFee查询实际月租和实际费用
		 */
		List<Map<String, String>> list = dao.query("DMTS02.queryInfoCurrentFee", map);
		if (CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("未查到数据");
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.setAttr(list.get(0));
		return outInfo;
	}


	/**
     * 更新操作，通过员工的退宿申请
     * 1、获取当前用户信息.
     * 2、调用本地服务DMQR0101.updateDormRoomMan将用户的原宿舍id取消绑定，同时取消床位号，并更新其换宿状态和操作相关。
     * 3、用户退宿审核通过之后，对应的宿舍床位数+1
     * 4、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态，是状态进入流程结束状态.
     * 5、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
     * 6、返回操作结果.
     *
     * @Title: update
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo update(EiInfo inInfo) {
        /*
         * 1、获取当前用户信息.
         */
        // 获取当前登陆工号
        String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
                UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
        inInfo.set("operator", loginName);
        /*
         * 2、调用本地服务DMQR0101.updateDormRoomMan 插入实际的退宿同意时间。
         */
        String actualOutDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 实际退宿时间*/
        inInfo.set("actualOutDate", actualOutDate);
        inInfo.set("outRoomStatus", "1");
        inInfo.set(EiConstant.serviceName, "DMQR0101");
        inInfo.set(EiConstant.methodName, "updateDormRoomMan");
        EiInfo outInfo = XLocalManager.call(inInfo);
        /*
         * 3、用户退宿审核通过之后，对应的宿舍床位数+1
         */
        inInfo.set(EiConstant.serviceName, "DMXX01");
        inInfo.set(EiConstant.methodName, "increaseDormBedNum");
        outInfo = XLocalManager.call(inInfo);
		/*
		 * 4、将退宿结算的相关费用插入到宿舍费用表中
		 */
		inInfo.set(EiConstant.serviceName, "DMTS0201");
		inInfo.set(EiConstant.methodName, "insertSettleFeeTable");
		outInfo = XLocalManager.call(inInfo);
        /*
         * 4、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
         */
        inInfo.set("statusCode", "99");
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "updateStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 5、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
         */
        // 将申请流程插入宿舍操作流程历史表中
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "insertLCInfo");
        outInfo = XLocalManager.call(inInfo);
		/*
		 * 6、企业微信通知退宿申请结果
		 */
		//获取app编码
		String appCode = "AP00002";
		List<String> workNoList =new ArrayList<>();
		List<String> paramList = new ArrayList<>();

		//获取退宿人员工号
		HashMap<Object, Object> map = new HashMap<>();
		map.put ("manId",inInfo.get("manId"));
		List<Map<String,Object>> query = dao.query("DMRZ01.queryRZApplyInfo", map);
		String manNo = (String)query.get(0).get("manNo");
		workNoList.add(manNo);


		//发送的消息
		String smsTemp = "您退宿申请已被审批通过";
		paramList.add(smsTemp);

		//发送企业微信
		BaseDockingUtils.pushWxMsg(workNoList, paramList, "TP00001", appCode);

        /*
         * 7、返回操作结果.
         */
        outInfo.setMsgKey("200");
        outInfo.setMsg("操作成功");
        return outInfo;
    }



	/**
	 * 将退宿结算的相关费用插入到宿舍费用表中
	 *
	 * 1、获取inInfo传来的参数.
	 * 2、新建一个map用来存放获取的数据.
	 * 3、以map作为参数执行 DMFY01.insertDormsRoomFeeInfo 进行数据的插入，插入本月费用信息到人员住宿相关费用表.
	 *
	 * @Title: insertSettleFeeTable
	 * @Param EiInfo
	 * @return: EiInfo
	 */
	public EiInfo insertSettleFeeTable(EiInfo inInfo) {
		/*
		 * 1、获取inInfo传来的参数.
		 */
		String id = UUID.randomUUID().toString();   /*主键*/
		String roomId = inInfo.get("roomId") == null ? "" : inInfo.getString("roomId");     /* 房间id */
		String manId = inInfo.get("manId") == null ? "" : inInfo.getString("manId");     /* 人员id */
		String currentRent = inInfo.get("currentRent") == null ? "0" : inInfo.getString("currentRent");     /* 本月实际月租（元）*/
		String currentManageFee = inInfo.get("currentManageFee") == null ? "0" : inInfo.getString("currentManageFee");       /* 本月实际管理费（元） */
		String waterPriece = inInfo.get("waterPriece") == null ? "0" : inInfo.getString("waterPriece");     /* 本月水费*/
		String elecPriece = inInfo.get("elecPriece") == null ? "0" : inInfo.getString("elecPriece");        /* 本月电费*/
		String waterDegree = inInfo.get("waterDegree") == null ? "0" : inInfo.getString("waterDegree");       /* 本月用水量（吨）*/
		String elecDegree = inInfo.get("elecDegree") == null ? "0" : inInfo.getString("elecDegree");      /* 本月用电量（度）*/
		String remark = inInfo.get("remark") == null ? "" : inInfo.getString("remark");     /* 备注*/
		String operator = inInfo.get("operator") == null ? UserSession.getUser().getUsername() : inInfo.getString("operator");        /* 操作人工号*/
		Map<String, Object> createUserInfo = DMUtils.getUserInfo(operator);
		String operationName =  createUserInfo== null ? "" : createUserInfo.get("name").toString(); /*操作人名称*/
		String currentMonth = inInfo.get("currentMonth") == null ? "" : new SimpleDateFormat("yyyy-MM").format(new Date());      /* 当前年月份 */
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		String getNextMonth = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());      /* 下个月份 */
		String operationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 操作时间*/
		/*
		 * 2、新建一个map用来存放获取的数据.
		 */
		Map<String, Object> map = new HashMap<>();

		map.put("id",id);
		map.put("roomId",roomId);
		map.put("manId", manId);
		map.put("currentRent", NumberUtils.createBigDecimal(currentRent));
		map.put("currentManageFee",NumberUtils.createBigDecimal(currentManageFee));
		map.put("waterPriece",NumberUtils.createBigDecimal(waterPriece));
		map.put("elecPriece",NumberUtils.createBigDecimal(elecPriece));
		map.put("elecDegree",Double.parseDouble(elecDegree));
		map.put("waterDegree",Double.parseDouble(waterDegree));
		map.put("remark",remark);
		map.put("operator",operator);
		map.put("operationName",operationName);
		map.put("currentMonth",currentMonth);
		map.put("settlementMonth",getNextMonth);
		map.put("operationTime",operationTime);
		/*
		 * 3、以map作为参数执行 DMFY01.insertDormsRoomFeeInfo 进行数据的插入，插入本月费用信息到人员住宿相关费用表.
		 */
		dao.insert("DMFY01.insertDormsRoomFeeInfo", map);
		/*
		 * 4、返回一个EiInfo.
		 */
		EiInfo outInfo = new EiInfo();
		outInfo.set("id", id);
		outInfo.setMsg("操作成功");
		return outInfo;
	}
	
}
