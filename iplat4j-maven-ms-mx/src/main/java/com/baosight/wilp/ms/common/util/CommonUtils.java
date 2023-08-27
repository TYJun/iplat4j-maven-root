package com.baosight.wilp.ms.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.util.DateUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 公共工具类
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  CommonUtils.java
 * @ClassName:  CommonUtils
 * @Package com.baosight.wilp.common.util
 * @Description: TODO
 * @author fangjian
 * @date:   2021年7月7日 上午9:50:28 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
@SuppressWarnings("all")
public class CommonUtils {
	
	private static SimpleDateFormat nationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	 
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 根据Map构建BlockMeta
	 * @Title: createBlockMeta
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * @param:  @return
	 * @return: EiBlockMeta  
	 * @throws
	 */
	public static EiBlockMeta createBlockMeta(Map<String,?> map) {
		EiBlockMeta eiBlockMeta = new EiBlockMeta();
		if(!map.isEmpty()){
			for(String key : map.keySet()){
				eiBlockMeta.addMeta(new EiColumn(key));
			}
		}
		return eiBlockMeta;
	}
	
	/**
	 * 获取页面传过来的参数(无分页)
	 * @Title: buildParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo 
	 * @param:  @param fieldList : 参数字段名称集合
	 * @param:  @return
	 * @return: Map<String,Object>  : 参数对象
	 * @throws
	 */
	public static Map<String,Object> buildParamterNoPage(EiInfo inInfo, List<String> fieldList){
		Map<String,Object> param = new HashMap<>();
		for (String field : fieldList) {
			param.put(field, inInfo.get(field));
		}
		
		return param;
	}
	
	/**
	 * 获取页面传过来的参数(无分页)
	 * @Title: buildParamterNoPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @param inBlock:参数EiBlock的id
	 * @param:  @param outBlock:返回 EiBlock的id
	 * @param:  @return
	 * @return: Map<String,Object> :参数map对象
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> buildParamterNoPage(EiInfo inInfo, String inBlock, String outBlock){
		Map<String,Object> param = new HashMap<>();
		//获取参数
		EiBlock block = inInfo.getBlock(inBlock);
		if(block != null && block.getRowCount() > 0){
			param = block.getRow(0);
		}
	
		return param;
	}
	
	/**
	 * 获取页面传过来的参数(有分页)
	 * @Title: buildParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @param outBlock
	 * @param:  @param fieldList
	 * @param:  @return
	 * @return: Map<String,Object>  
	 * @throws
	 */
	public static Map<String,Object> buildParamter(EiInfo inInfo, String outBlock, List<String> fieldList){
		Map<String,Object> param = new HashMap<>();
		for (String field : fieldList) {
			param.put(field, inInfo.get(field));
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
	 * 获取页面传过来的参数(有分页)
	 * @Title: buildParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo 
	 * @param:  @param fieldList : 参数字段名称集合
	 * @param:  @return
	 * @return: Map<String,Object>  : 参数对象
	 * @throws
	 */
	public static Map<String,Object> buildParamter(EiInfo inInfo, List<String> fieldList){
		Map<String,Object> param = new HashMap<>();
		for (String field : fieldList) {
			param.put(field, inInfo.get(field));
		}
		
		//获取分页信息
		Integer offset = 0;Integer limit = 10;
  		String page = inInfo.getString("page");
  		if(StringUtils.isNotBlank(page)){
  			offset = (Integer.parseInt(page)-1)* limit;
  		}
  		String size = inInfo.getString("size");
  		if(StringUtils.isNotBlank(size)){
  			limit = Integer.parseInt(size);
  		}
  		
		param.put("offset", offset);
		param.put("limit", limit);
		return param;
	}
	
	/**
	 * 获取页面传过来的参数(有分页)
	 * @Title: buildParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @param inBlock
	 * @param:  @param outBlock
	 * @param:  @return
	 * @return: Map<String,Object>  
	 * @throws
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
		//APP分页
  		String page = inInfo.getString("page");
  		if(StringUtils.isNotBlank(page)){
  			offset = (Integer.parseInt(page)-1)* limit;
  		}
  		String size = inInfo.getString("size");
  		if(StringUtils.isNotBlank(size)){
  			limit = Integer.parseInt(size);
  		}
  		
		param.put("offset", offset);
		param.put("limit", limit);
		return param;
	}
	
	/**
	 * 构建返回对象
	 * @Title: BuildOutEiInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo ： 返回EiInfo ： 可为空
	 * @param:  @param outBlock ： 返回EiBlock的ID : 为空时默认为result
	 * @param:  @param eiMetadata ： 返回EiBlock的EiBlockMeta :可为空
	 * @param:  @param list ： 返回数据集
	 * @param:  @param count ： 数据总数 （为空时，返回所有数据）
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo BuildOutEiInfo(EiInfo inInfo, String outBlock, EiBlockMeta eiMetadata, List<?> list, Integer count){
		//EiInfo 处理
		EiInfo outInfo = new EiInfo();
		if(inInfo != null){
			outInfo = inInfo;
		}
		//EiBlock处理
		outBlock = StringUtils.isBlank(outBlock) ? "result" : outBlock;
		EiBlock result = outInfo.getBlock(outBlock);
		if(result == null){
			result = new EiBlock(outBlock);
			if(eiMetadata == null){
				if(list.get(0) ==null || !(list.get(0) instanceof Map)){
					eiMetadata = new EiBlockMeta();
				} else {
					eiMetadata = createBlockMeta((Map<String, ?>) list.get(0));
				}
			}
			result.addBlockMeta(eiMetadata);
			result.setAttr(new HashMap<>());
			outInfo.addBlock(result);
		}
		//数据添加
		result.addRows(list);
		//处理分页数据
		count = count == null ? list.size() : count; //count为null时，总数为数据集总数，无分页
		if(result.getAttr().isEmpty()){
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", 0);
			rAttr.put("limit", 10);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "false");
			result.setAttr(rAttr);
		} else {
			result.getAttr().put("count", count);
		}
		return outInfo;
	}
	
	/**
	 * 构建返回对象
	 * @Title: BuildOutEiInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param list ： 返回数据集
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo BuildOutEiInfo(List<Map<String,Object>> list){
		return BuildOutEiInfo(null,null,null,list,null);
	}
	
	/**
	 * 构建返回对象
	 * @Title: BuildOutEiInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param list ： 返回数据集
	 * @param:  @param count ： 数据总数 （为空时，返回所有数据）
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo BuildOutEiInfo(List<Map<String,Object>> list, Integer count){
		return BuildOutEiInfo(null,null,null,list,count);
	}
	
	/**
	 * 构建返回对象
	 * @Title: BuildOutEiInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param outBlock ： 返回EiBlock的ID : 为空时默认为result
	 * @param:  @param list ： 返回数据集
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo BuildOutEiInfo(String outBlock,List<Map<String,Object>> list){
		return BuildOutEiInfo(null,outBlock,null,list,null);
	}
	
	/**
	 * 构建返回对象
	 * @Title: BuildOutEiInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param outBlock ： 返回EiBlock的ID : 为空时默认为result
	 * @param:  @param list ： 返回数据集
	 * @param:  @param count ： 数据总数 （为空时，返回所有数据）
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo BuildOutEiInfo(String outBlock,List<Map<String,Object>> list, Integer count){
		return BuildOutEiInfo(null,outBlock,null,list,count);
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
     * 获取指定日期所在周的第一天和最后一天
     * @Title: getWeekDay
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param date
     * @param:  @return
     * @return: String[]  
     * @throws
     */
    public static String[] getWeekDay(Date date) {
    	if(date == null){
    		date = new Date();
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
    	calendar.setTime(date);
    	int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); //获取周开始基准
    	int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
    	calendar.add(Calendar.DAY_OF_WEEK, min-current); //当天-基准，获取周开始日期
    	String firstTime = DateUtils.toDateStr10(calendar.getTime());
    	calendar.add(Calendar.DAY_OF_WEEK, 6); //开始+6，获取周结束日期
    	String lastTime = DateUtils.toDateStr10(calendar.getTime());
    	return new String[] {firstTime, lastTime};
         
    }
    
    /**
     * 获取指定时间所在月的第一天和最后一天
     * @Title: getMonthDay
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param date
     * @param:  @return
     * @return: String[]  
     * @throws
     */
    public static String[] getMonthDay(Date date) {
    	if(date == null){
    		date = new Date();
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	//获取第一天和最后一天
    	calendar.add(Calendar.MONTH, 0);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	String firstTime = DateUtils.toDateStr10(calendar.getTime());
    	calendar.add(Calendar.MONTH, 1);
    	calendar.set(Calendar.DAY_OF_MONTH, 0);
    	String lastTime = DateUtils.toDateStr10(calendar.getTime());
    	return new String[] {firstTime, lastTime};
    }
    
    /**
     * 时间减法
     * @Title: subDateTime
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param dateStr : 时间字符传(yyyy-MM-dd HH:mm:ss)
     * @param:  @param day : 间隔时间 （天）
     * @param:  @return
     * @param:  @throws ParseException
     * @return: String  
     * @throws
     */
    public static String subDateTime(String dateStr, int day) throws ParseException {
    	Date date = new Date();
    	if(StringUtils.isNotBlank(dateStr)) {
    		date = dateTimeFormat.parse(dateStr);
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(5, -day);
    	return dateTimeFormat.format(calendar.getTime());
    }
	
	/**
     * 将图片转成base64码
     * @Title: imageToBase64Str
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param imgFile ： 图片路径
     * @param:  @return
     * @return: String  ： 图片base64
     * @throws
     */
    public static String imageToBase64Str(String imgFile) {
        InputStream inputStream = null;
        File file =new File(imgFile);
        byte[] data = null;
        try {
            inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    
    /**
     * 将图片base64转图片文件
     * @Title: base64StrToImage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param imgStr ： base64码
     * @param:  @param path ： 图片存储路径
     * @param:  @return
     * @return: boolean  
     * @throws
     */
    public static boolean base64StrToImage(String imgStr, String path) {
    	if(imgStr.contains("data:image/")){
    		imgStr = imgStr.split(",")[1];
    	}
    	
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
