package com.baosight.wilp.di.common.util;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 关于eiinfo的 工具类：将EIinfo中的参数提取出来（包含分页）、构建参数 不分页、返回结果、构建查询、
 * 根据周期获取下一次执行的时间
 * <p>1.将EIinfo中的参数提取出来（包含分页） buildParam
 * <p>2.构建参数 不分页  buildParam
 * <p>3.返回结果  buildResult
 * <p>4.构建查询   buildParamter
 * <p>5.根据周期获取下一次执行的时间 getNextCreateTime
 * 
 * @Title: DeviceEiUtil.java
 * @ClassName: DeviceEiUtil
 * @Package：com.baosight.wilp.di.common.util
 * @author: LENOVO
 * @date: 2021年8月11日 下午4:00:20
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class DeviceEiUtil {
	
	
	/**
	 * 将EIinfo中的参数提取出来（包含分页）
	 * @param inInfo
	 * @param blockId
	 * @return
	 */
	public static Map<String, Object> buildParam(EiInfo inInfo, List<String> params, String blockId) {
		Map<String, Object> rMap = new HashMap<>();
		//inInfo中的所有参数
		Map attr = inInfo.getAttr();
		//取出查询的参数
		params.forEach(e -> rMap.put(e, attr.get(e)));
		//处理分页数据
		if (StringUtil.isNotEmpty(blockId)) {
			EiBlock block = inInfo.getBlock(blockId);
			Integer offset = 0;
			Integer limit = 10;
			if (block != null) {
				Map blockMap = block.getAttr();
				Integer offset1 = (Integer) blockMap.get("offset");
				Integer limit1 = (Integer) blockMap.get("limit");			
				if (offset1 != null && limit1 != null) {
					offset = offset1;
					limit = limit1;
				}
			}
			rMap.put("offset", offset);
			rMap.put("limit", limit);
		}
		return rMap;
	}
	
	/**
	 * 构建参数 不分页
	 * @param inInfo
	 * @param params
	 * @return
	 */
	public static Map<String, Object> buildParam(EiInfo inInfo, List<String> params) {
		return buildParam(inInfo, params, null);
	}
	
	/**
	 * 返回结果
	 * @param list
	 * @param blockId
	 * @param count
	 * @return
	 */
	public static EiInfo buildResult(EiInfo inInfo, List list, Integer count, String blockId) {
		EiBlock result = inInfo.getBlock(blockId);
		if(result != null && !result.getAttr().isEmpty()){
			result.setRows(list);
			result.getAttr().put("count", count);
		} else {
			EiBlock resultBlock = new EiBlock(blockId);
			
			EiBlockMeta eiBlockMeta = new EiBlockMeta();
			Map<String, Object> map;
			if (list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			} else {
				map = new HashMap<>();
			}
			if(!map.isEmpty()){
				for(String key : map.keySet()){
					eiBlockMeta.addMeta(new EiColumn(key));
				}
			}
			
			resultBlock.setBlockMeta(eiBlockMeta);
			resultBlock.addRows(list);
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", 0);
			rAttr.put("limit", 10);
			resultBlock.setAttr(rAttr);
			inInfo.addBlock(resultBlock);
		}
		return inInfo;
	}
	
	/**
	 * 构建查询
	 * @param inInfo
	 * @param inBlock
	 * @param outBlock
	 * @return
	 */
	public static Map<String,Object> buildParamter(EiInfo inInfo, String inBlock, String outBlock){
		Map<String,Object> param = new HashMap<>();
		//获取参数
		EiBlock block = inInfo.getBlock(inBlock);
		if(block != null && block.getRowCount() > 0){
			param = block.getRow(0);
		}
		//获取分页信息
		EiBlock result = inInfo.getBlock(outBlock);
		Integer offset = 0;Integer limit = 10;
		if(result != null && !result.getAttr().isEmpty()){
			offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
			limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
		}
		param.put("offset", offset);
		param.put("limit", limit);
		return param;
	}
	
	/**
	 * 根据周期获取下一次执行的时间
	 * @param cycle
	 * @param unit
	 * @param startTime
	 * @return
	 */
    public static String getNextCreateTime(String cycle, String unit, String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int code = Integer.parseInt(cycle);
        try {
            Date date = sdf.parse(startTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            switch (unit) {
                case "h":
                    calendar.add(Calendar.HOUR, code);
                    break;
                case "d":
                    calendar.add(Calendar.DAY_OF_YEAR, code);
                    break;
                case "m":
                    calendar.add(Calendar.MONTH, code);
                    break;
            }
            String result = sdf.format(calendar.getTime());
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startTime;
    }
    
    /**
     * 获取当前用户信息（包含角色）
     * @Title: getUserInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo
     * @param:  @return
     * @return: Map<String,Object>  
     * @throws
     */
    @SuppressWarnings("unchecked")
   	public static Map<String,Object> getUserInfo(String workNo) {
       	//获取用户
    	EiInfo info = new EiInfo();
    	info.set("workNo", StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo);
    	info.set(EiConstant.serviceName, "MTRE01");
    	info.set(EiConstant.methodName, "queryPersonnel");
    	EiInfo infoLogin =XLocalManager.call(info);
    	if(infoLogin.getBlock("person")!=null && infoLogin.getBlock("person").getRowCount() > 0){
    		Map<String,Object> map = infoLogin.getBlock("person").getRow(0);
    		//获取用户角色（普通用户组）
    		EiInfo eiInfo = new EiInfo("role");
       		eiInfo.set(EiConstant.serviceId,"S_XS_14");
       		eiInfo.set("loginName", info.get("workNo"));
       		eiInfo.set("groupType", "NORMAL");
       		EiInfo normalGroupInfo = XServiceManager.call(eiInfo);
       		String role = getRoleStr(normalGroupInfo);
       		//获取用户角色（管理员用户组）
       		eiInfo.set("groupType", "MANAGERGROUP");
       		EiInfo ManagerGroupInfo = XServiceManager.call(eiInfo);
       		role = role + getRoleStr(ManagerGroupInfo);
       		map.put("role", role.length() > 0 ? role.substring(0,role.length()-1) : "");
       		return map;
    	} else {
    		return null;
    	}
    }
    
    /**
     * 获取用户角色（管理员用户组）
     *
     * @Title: getRoleStr 
     * @param eiInfo
     * @return 
     * @return: String
     */
    @SuppressWarnings("unchecked")
	private static String getRoleStr(EiInfo eiInfo) {
		StringBuilder sb = new StringBuilder();
		if(eiInfo.getStatus() ==1){
			List<Map<String, String>> list = (List<Map<String, String>>) eiInfo.get("result");
			list.forEach(map -> sb.append(map.get("groupEname")).append(","));
		}
		return sb.toString();
	}
	
}
