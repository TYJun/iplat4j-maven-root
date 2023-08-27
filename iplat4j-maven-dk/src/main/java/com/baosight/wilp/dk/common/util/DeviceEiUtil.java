package com.baosight.wilp.dk.common.util;

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
 * 关于eiinfo的 工具类
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: DeviceEiUtil.java
 * @ClassName: DeviceEiUtil
 * @Package：com.baosight.wilp.dk.common.util
 * @author: LENOVO
 * @date: 2021年10月25日 上午9:33:44
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
			//获取分页条数
			if (block != null) {
				Map blockMap = block.getAttr();
				//偏移量赋值
				Integer offset1 = (Integer) blockMap.get("offset");
				//limit赋值
				Integer limit1 = (Integer) blockMap.get("limit");
				//偏移量和limit不为空判断
				if (offset1 != null && limit1 != null) {
					offset = offset1;
					limit = limit1;
				}
			}
			//赋值分页条数
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
		//resultBlock不为空直接塞数据
		if(result != null && !result.getAttr().isEmpty()){
			result.setRows(list);
			result.getAttr().put("count", count);
		//resultBlock为空先保存源数据再赋值数据集
		} else {
			EiBlock resultBlock = new EiBlock(blockId);
			//new一个block源数据对象
			EiBlockMeta eiBlockMeta = new EiBlockMeta();
			Map<String, Object> map;
			//数据集不为空
			if (list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			} else {
				map = new HashMap<>();
			}
			//保存源数据
			if(!map.isEmpty()){
				for(String key : map.keySet()){
					eiBlockMeta.addMeta(new EiColumn(key));
				}
			}
			//将数据集保存到新创建的block里
			resultBlock.setBlockMeta(eiBlockMeta);
			resultBlock.addRows(list);
			//new一个map对象并赋予偏移量值和条数
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
		//获取block对象并获取参数
		EiBlock block = inInfo.getBlock(inBlock);
		if(block != null && block.getRowCount() > 0){
			param = block.getRow(0);
		}
		//获取分页信息
		EiBlock result = inInfo.getBlock(outBlock);
		Integer offset = 0;Integer limit = 10;
		//如果结果集不为空则偏移量为结果集的数量，为空则赋值0
		if(result != null && !result.getAttr().isEmpty()){
			offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
			limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
		}
		//赋值分页数据
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
        //获取时间转换格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int code = Integer.parseInt(cycle);
        try {
            //通过开始时间参数获取日历日期格式
            Date date = sdf.parse(startTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //校验时间
            switch (unit) {
                //h为小时
                case "h":
                    calendar.add(Calendar.HOUR, code);
                    break;
                //d为天
                case "d":
                    calendar.add(Calendar.DAY_OF_YEAR, code);
                    break;
                //m为月
                case "m":
                    calendar.add(Calendar.MONTH, code);
                    break;
            }
            //将时间转换成字符日期
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
    	//工作编号赋值
    	info.set("workNo", StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo);
    	//调用后台服务并执行
    	info.set(EiConstant.serviceName, "MTRE01");
    	info.set(EiConstant.methodName, "queryPersonnel");
    	EiInfo infoLogin =XLocalManager.call(info);
    	//person命名的block不为空判断
    	if(infoLogin.getBlock("person")!=null && infoLogin.getBlock("person").getRowCount() > 0){
    		Map<String,Object> map = infoLogin.getBlock("person").getRow(0);
    		//获取用户角色（普通用户组）
    		EiInfo eiInfo = new EiInfo("role");
    		//调用后台服务并执行
       		eiInfo.set(EiConstant.serviceId,"S_XS_14");
       		eiInfo.set("loginName", info.get("workNo"));
       		eiInfo.set("groupType", "NORMAL");
       		EiInfo normalGroupInfo = XServiceManager.call(eiInfo);
       		String role = getRoleStr(normalGroupInfo);
       		//获取用户角色（管理员用户组）
       		eiInfo.set("groupType", "MANAGERGROUP");
       		EiInfo ManagerGroupInfo = XServiceManager.call(eiInfo);
       		role = role + getRoleStr(ManagerGroupInfo);
       		//判断角色长度
       		map.put("role", role.length() > 0 ? role.substring(0,role.length()-1) : "");
       		return map;
    	} else {
    		return null;
    	}
    }
    
    @SuppressWarnings("unchecked")
	private static String getRoleStr(EiInfo eiInfo) {
		StringBuilder sb = new StringBuilder();
		//通过状态循环添加数据
		if(eiInfo.getStatus() ==1){
			List<Map<String, String>> list = (List<Map<String, String>>) eiInfo.get("result");
			//循环增加角色组名字
			list.forEach(map -> sb.append(map.get("groupEname")).append(","));
		}
		return sb.toString();
	}
	
}
