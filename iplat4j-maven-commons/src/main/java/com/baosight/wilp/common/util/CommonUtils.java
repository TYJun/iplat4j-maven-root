package com.baosight.wilp.common.util;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	 * @param:  @param outBlock : 返回 EiBlock的id
	 * @param:  @param fieldList :参数字段名称集合
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
		
		//获取分页信息
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
	 * 获取页面传过来的参数(有分页,需要传分页参数)
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
	 * @param:  @param inBlock : 参数Block的Id
	 * @param:  @param outBlock ： 返回Block的Id
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
	 * 获取页面传过来的参数(构建成参数Block块)
	 * @Title: buildParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo ： 入参inInfo
	 * @param:  @param fieldList ： 参数名集合
	 * @param:  @param inBlock ： 入参block的id
	 * @param:  @return
	 * @return: EiInfo  : 入参inInfo
	 * @throws
	 */
	public static EiInfo buildEiInfoParamter(EiInfo inInfo, String inBlock, List<String> fieldList){
		inBlock = StringUtils.isBlank(inBlock) ? "inqu_status" : inBlock;
		EiBlock block = inInfo.getBlock(inBlock);
		if(block == null){
			block = new EiBlock(inBlock);
			EiBlockMeta mate = new EiBlockMeta();
			block.setBlockMeta(mate);
			inInfo.addBlock(block);
		}
		fieldList.forEach(field -> inInfo.set("inqu_status-0-"+field, inInfo.get(field)));
		return inInfo;
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
				if(list ==null || list.size()==0 || list.get(0) ==null || !(list.get(0) instanceof Map)){
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
			rAttr.put("limit", list.size() < 10 ? 10 : list.size());
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
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
	 * 构建返回对象(逻辑分页)
	 * @Title: BuildOutEiInfoWithLogicalPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param resultList ： 需要逻辑分页的数据集
	 * @param:  @param param ： 参数Map
	 * @param:  @param resultId : 返回EiBlock的ID : 为空时默认为result
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public static EiInfo BuildOutEiInfoWithLogicalPage (List<?> resultList, Map<String, Object> param, String resultId){
		int offset = 0;int limit=10; 
		if(param == null || param.isEmpty()){
			List<?> subList = offset > resultList.size()-1 ? new ArrayList<>() : resultList.subList(offset, limit);
	        return BuildOutEiInfo(null,resultId,null,subList,resultList.size());
		} else {
			if(param.containsKey("offset") && param.get("offset") !=null && StringUtils.isNotBlank(param.get("offset").toString())){
				offset = Integer.parseInt(param.get("offset").toString());
			}
			if(param.containsKey("limit") && param.get("limit") !=null && StringUtils.isNotBlank(param.get("limit").toString())){
				limit = offset + Integer.parseInt(param.get("limit").toString());
			}
			Stream<?> stream = resultList.stream();
			for (String key : param.keySet()) {
				if("offset".equals(key) || "limit".equals(key) || "sql".equals(key)){
					continue;
				}
				if(param.get(key) !=null && StringUtils.isNotBlank(param.get(key).toString())){
		        	stream = stream.filter(e -> getValue(e,key).contains(param.get(key).toString()));
		        } 
			}
			List<?> list = stream.collect(Collectors.toList());
			limit = limit > list.size() ? list.size() : limit; 
			List<?> subList = offset > list.size()-1 ? new ArrayList<>() : list.subList(offset, limit);
			return BuildOutEiInfo(null,resultId,null,subList,list.size());
		}
	}
	
	//获取对象指定属性的值
	private static String getValue(Object e, String key) {
		try{
			if(e instanceof Map){//Map
				Map<String, Object> map = (Map<String, Object>) e;
				return map.get(key) == null ? "" : map.get(key).toString();
				//基础数据类型或String
			} else if (((Class<?>)e.getClass().getField("TYPE").get(null)).isPrimitive() || e instanceof String) {
				return e.toString();
			} else {//javaBean
				Method method = e.getClass().getDeclaredMethod("get"+key.substring(0,1).toUpperCase()+key.substring(1));
				Object invoke = method.invoke(e);
				return invoke != null ? invoke.toString() : "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
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
    
    /**
     * 获取小代码数据
     * @Title: getDataCode
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param codeset ： 代码分类编号
     * @param:  @return
     * @return: List<Map<String,String>> ： 代码明细集合
     * 		label：代码项中文名称
     * 		value： 代码项编号
     * @throws
     */
    public static List<Map<String,String>> getDataCode(String codeset) {
    	List<Map<String,String>> list = new ArrayList<>();
    	EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId, "S_ED_02");
		eiInfo.set("codeset",codeset);
		//服务接口调用
		EiInfo outInfo = XServiceManager.call(eiInfo);
		Object object = outInfo.get("list");
		if(object !=null){
			list = (List<Map<String, String>>) object;
		}
		return list;
    }
    
    /**
     * 获取小代码项的名称
     * @Title: getDataCodeItemName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param codeset
     * @param:  @param itemCode
     * @param:  @return
     * @return: String  
     * @throws
     */
    public static String getDataCodeItemName(String codeset, String itemCode){
    	List<Map<String, String>> list = getDataCode(codeset);
    	if(StringUtils.isBlank(itemCode)){
    		return "";
    	}
    	for (Map<String, String> map : list) {
			if(itemCode.equals(map.get("value"))){
				return map.get("label");
			}
		}
    	return "";
    }

	/**
	 * 除去参数前后的空格
	 * @Title: trimForParams
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj obj ： 参数
	 * @return java.lang.Object
	 * @throws
	 **/
	public static Object trimForParams(Object obj) {
		if(Optional.ofNullable(obj).isPresent()){
			if(String.class.isInstance(obj)){
				return Optional.ofNullable(obj).orElse("").toString().trim();
			}
		}
		return obj;
	}

}
