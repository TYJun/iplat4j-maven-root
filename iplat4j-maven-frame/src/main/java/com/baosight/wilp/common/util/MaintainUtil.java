package com.baosight.wilp.common.util;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.xservices.xs.util.UserSession;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 维修工具类.
 * <p>
 * .
 * </p>
 *
 * @Title DatagroupUtil.java
 * @Author testzw
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class MaintainUtil {

	private static final Base64.Decoder decoder = Base64.getDecoder();

	/**
	 * 生成任务号的头
	 * 
	 * @return
	 */
	public static String createTop() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String head = sdf.format(new Date());
		return head;
	}

	public static EiInfo getGroup(String loginName) {
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("loginName", loginName);
		eiInfo.set(EiConstant.serviceId, "S_XS_55");
		return XServiceManager.call(eiInfo);

	}

	/**
	 * 前端参数转map
	 * 
	 * @param inInfo
	 * @param list
	 * @return
	 */
	public static Map<String, Object> changeToMap(EiInfo inInfo, List<String> list) {
		Map<String, Object> map = new HashMap<>();
		Map param = inInfo.getAttr();
		// 获取分页信息
		EiBlock result = inInfo.getBlock("result");
		Integer offset = 0;
		Integer limit = 10;
		if (result != null && !result.getAttr().isEmpty()) {
			offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
			limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
		}
		map.put("offset", offset.toString());
		map.put("limit", limit.toString());
		list.forEach(e -> map.put(e, (String) param.get(e)));
		return map;
	}

	/**
	 * 将查询出的数据以eiinfo的格式还回去
	 * 
	 * @param list
	 * @return
	 */
	public static EiInfo changeToEiInfo(List list) {
		EiInfo outInfo = new EiInfo();

		EiBlock eiBlock = new EiBlock(new EiBlockMeta());
		eiBlock.setRows(list);
		HashMap<String, EiBlock> hashMap = new HashMap<String, EiBlock>();
		hashMap.put("result", eiBlock);
		outInfo.setBlocks(hashMap);
		if (!CollectionUtils.isEmpty(list)) {
			outInfo.setMsg("查询成功");
		} else {
			outInfo.setMsg("未获取到数据");
		}
		return outInfo;
	}

	/**
	 * 封装接口调用的请求 调用该方法前需要eiInfo.set(k,v)将参数传好
	 * 
	 * @param serviceName
	 * @param methodName
	 * @param paramInfo
	 * @return
	 */
	public static EiInfo putRequest(String serviceName, String methodName, EiInfo eiInfo) {
		eiInfo.set(EiConstant.serviceName, serviceName);
		eiInfo.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(eiInfo);
		return outInfo;
	}

//    public static ResultData putResponse(Object obj,String respCode,String respMsg) {
//        ResultData resultData=new ResultData();
//        resultData.setRespCode(respCode);
//        resultData.setRespMsg(respMsg);
//        Map<String, Object> resultMap=new HashMap<String,Object>();
//        resultMap.put("obj", obj);
//        resultData.setMap(resultMap);
//        return resultData;
//    }

	public static EiInfo changeToEiInfo(Map map) {
		EiInfo info = new EiInfo();
		info.setAttr(map);
		return info;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getUserInfo(String workNo) {
		// 获取用户
		EiInfo info = new EiInfo();
		info.set("workNo", StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo);
		info.set(EiConstant.serviceName, "MTRE01");
		info.set(EiConstant.methodName, "getUserInformation");
		EiInfo infoLogin = XLocalManager.call(info);
		if (infoLogin.getBlock("user") != null && infoLogin.getBlock("user").getRowCount() > 0) {
			Map<String, Object> map = infoLogin.getBlock("user").getRow(0);
			// 获取用户角色（普通用户组）
			EiInfo eiInfo = new EiInfo("role");
			eiInfo.set(EiConstant.serviceId, "S_XS_14");
			eiInfo.set("loginName", info.get("workNo"));
			eiInfo.set("groupType", "NORMAL");
			EiInfo normalGroupInfo = XServiceManager.call(eiInfo);
			String role = getRoleStr(normalGroupInfo);
			// 获取用户角色（管理员用户组）
			eiInfo.set("groupType", "MANAGERGROUP");
			EiInfo ManagerGroupInfo = XServiceManager.call(eiInfo);
			role = role + getRoleStr(ManagerGroupInfo);
			map.put("role", role.length() > 0 ? role.substring(0, role.length() - 1) : "");
			return map;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private static String getRoleStr(EiInfo eiInfo) {
		StringBuilder sb = new StringBuilder();
		if (eiInfo.getStatus() == 1) {
			List<Map<String, String>> list = (List<Map<String, String>>) eiInfo.get("result");
			list.forEach(map -> sb.append(map.get("groupEname")).append(","));
		}
		return sb.toString();
	}

	/**
	 * base64图片转字节数组
	 * 
	 * @param img
	 * @return
	 */
	public static byte[] castToImg(String img) {
		// base64转化为字节数组
		return decoder.decode(img);
	}

	/**
	 * 图片保存公共方法
	 * 
	 * @param path
	 * @param taskNo
	 * @param node
	 * @param type
	 * @param pics
	 */
	public static void dealPic(String showPath, String savePath, String taskNo, String node, String type, String pics) {
		// 最多允许存放3张图片
		List<String> impList = new ArrayList<String>();
		if (type.equals("more")) {
			JSONObject js = JSONObject.fromObject(pics);
			impList.add(js.get("pic1").toString());
			impList.add(js.get("pic2").toString());
			impList.add(js.get("pic3").toString());
		} else if (type.equals("one")) {
			impList.add(pics);
		}

		for (int i = 0; i < impList.size(); i++) {
			String impStr = impList.get(i);
			if (StringUtils.isBlank(impStr)) {
				continue;
			}

			File files = new File(savePath);
			if (!files.isDirectory()) {
				files.mkdir();
			}
			String picId = UUID.randomUUID().toString();
			File file = new File(savePath + picId + ".jpg");
			EiInfo info = new EiInfo();
			info.set("id", picId);
			info.set("taskNo", taskNo);
			info.set("type", node);
			info.set("path", showPath + picId);
			MaintainUtil.putRequest("MTRG0101", "uploadPic", info);
			try (FileOutputStream outStream = new FileOutputStream(file)) {
				outStream.write(MaintainUtil.castToImg(impStr));
				;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 获取指定日期所在周 @Title: getWeekDay @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param date @param: @return @return:
	 * String[] @throws
	 */
	public static String[] getWeekDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); // 获取周开始基准
		int current = calendar.get(Calendar.DAY_OF_WEEK); // 获取当天周内天数
		calendar.add(Calendar.DAY_OF_WEEK, min - current); // 当天-基准，获取周开始日期
		String firstTime = DateUtils.toDateStr10(calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 6); // 开始+6，获取周结束日期
		String lastTime = DateUtils.toDateStr10(calendar.getTime());
		return new String[] { firstTime, lastTime };

	}

	public static String[] getMonthDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 获取第一天和最后一天
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String firstTime = DateUtils.toDateStr10(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		String lastTime = DateUtils.toDateStr10(calendar.getTime());
		return new String[] { firstTime, lastTime };
	}

//    /**
//     * app和pc获取账套的公共方法
//     * @param workNo
//     * @param type
//     */
//    public static String getDatagroupCode(EiInfo info) {
//        if(StringUtils.isBlank((String)info.get("type"))) {
//            return DatagroupUtil.getDatagroupCode();
//        }
//            return (String)info.get("groupCode");
//    }

}
