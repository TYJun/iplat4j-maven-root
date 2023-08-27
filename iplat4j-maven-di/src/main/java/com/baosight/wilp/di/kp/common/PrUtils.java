package com.baosight.wilp.di.kp.common;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 卡片通用类：List转EiBlock、时间转换、获取页面传过来的参数、构建返回结果EiInfo
 * <p>List转EiBlock createBlockMeta
 * <p>时间转换 timeZoneExchange
 * <p>获取页面传过来的参数 buildParamter
 * <p>构建返回结果EiInfo BuildOutEiInfo
 * 
 * @Title: PrUtils.java
 * @ClassName: PrUtils
 * @Package：com.baosight.wilp.di.kp.common
 * @author: LENOVO
 * @date: 2021年8月11日 下午3:55:22
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class PrUtils {
	
	private static SimpleDateFormat nationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	 
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * List转EiBlock
	 * @Title: ObjectToBlock
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param data    时间
	 * @param:  @param blockId  blockid
	 * @param:  @return
	 * @return: EiBlock  
	 * @throws
	 */
	public static EiBlockMeta createBlockMeta(Map<String,Object> map) {
		EiBlockMeta eiBlockMeta = new EiBlockMeta();
		//校验map不为空
		if(!map.isEmpty()){
			for(String key : map.keySet()){
				eiBlockMeta.addMeta(new EiColumn(key));
			}
		}
		return eiBlockMeta;
	}
	
	public static String getDataGroup(){
		return "IPLAT";
	}
	
	/**
	 * 时间转换
	 * @Title: timeZoneExchange
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param date  时间
	 * @param:  @return
	 * @return: String  
	 * @throws
	 */
	public static String timeZoneExchange (String date) {
		Calendar cal = Calendar.getInstance();
		try {
			long time = nationDateFormat.parse(date).getTime();
			cal.setTimeInMillis(time);
			cal.add(Calendar.HOUR, +8);
		} catch (ParseException e) {
			return date;
		}
		return dateFormat.format(cal.getTime());
	}
	
	/**
	 * 获取页面传过来的参数
	 * @Title: buildParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo    
	 * @param:  @param inBlock   传入Block
	 * @param:  @param outBlock  传出Block
	 * @param:  @return
	 * @return: Map<String,Object>  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
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
	 * 构建返回结果EiInfo
	 * @Title: BuildOutEiInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @param outBlock   传出Block
	 * @param:  @param list
	 * @param:  @param count       数量 
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static EiInfo BuildOutEiInfo(EiInfo inInfo, String outBlock, EiBlockMeta eiMetadata, List<?> list, int count){
		EiBlock result = inInfo.getBlock(outBlock);
		if(result != null && !result.getAttr().isEmpty()){
			result.setRows(list);
			result.getAttr().put("count", count);
		} else {
			EiBlock resultBlock = new EiBlock(outBlock);
			resultBlock.setBlockMeta(eiMetadata);
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

	
}
