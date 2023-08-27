package com.baosight.wilp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;

public class PMUtils {
	
	private static SimpleDateFormat nationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	 
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * List转EiBlock
	 * @Title: ObjectToBlock
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param data
	 * @param:  @param blockId
	 * @param:  @return
	 * @return: EiBlock  
	 * @throws
	 */
	public static EiBlockMeta createBlockMeta(Map<String,Object> map) {
		EiBlockMeta eiBlockMeta = new EiBlockMeta();
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
	 * 将格林威治时间转为北京时间
	 * @Title: timeZoneExchange
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param date
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
	 * @param:  @param inBlock
	 * @param:  @param outBlock
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
	 * @param:  @param outBlock
	 * @param:  @param list
	 * @param:  @param count
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
