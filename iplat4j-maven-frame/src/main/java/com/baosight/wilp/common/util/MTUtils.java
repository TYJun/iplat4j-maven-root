package com.baosight.wilp.common.util;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * 维修工具类
 * 
 * 1. 接口（本地服务）调用
 * 2. 接口（本地服务）调用
 * 3. 发送短息和消息推送
 * 4. 获取指定任务的账套
 * 5. 获取指定用户的信息
 * 6. 获取指定用户的角色信息
 * 7. 获取指定用户的角色编码
 * 8. 获取指定用户的角色名称
 * 9. 获取指定用户组下的用户信息
 * 10. 获取维修调度中心用户组下人员信息
 * 11. 获取指定科室的维修组长信息
 * 12. 获取指定任务的执行人信息
 * 13. 获取指定配置项中单选项的值
 * 14. 获取指定配置项中多选项的值或输入文本的值
 * 15. 生成指定格式的字符串（yyMMdd）
 * 16. 将音频文件转换成base64码
 * 17. 参数空处理
 * 18. 构建空EiInfo对象
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  MTUtils.java
 * @ClassName:  MTUtils
 * @Package com.baosight.wilp.mt.common
 * @Description: TODO
 * @author fangjian
 * @date:   2021年9月10日 下午1:19:56 
 * @version V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 *
 */
@SuppressWarnings("all")
public class MTUtils {
	
	private static final Lock lock = new ReentrantLock();
	private static String defaultBlankValue = "";

	/**
	 * @Title: invoke
	 * @Description: TODO(本地服务调用)
	 * @param inInfo inInfo : 参数EiInfo
	 * @param serviceName serviceName : 服务名
	 * @param methodName methodName : 方法名
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo invoke (EiInfo inInfo, String serviceName, String methodName) {
		inInfo.set(EiConstant.serviceName, serviceName);
		inInfo.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * @Title: invoke
	 * @Description: TODO(本地服务调用)
	 * @param pMap pMap ： 参数Map
	 * @param serviceName serviceName : 服务名
	 * @param methodName methodName : 方法名
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo invoke (Map<String, Object> pMap, String serviceName, String methodName) {
		EiInfo inInfo = new EiInfo();
		//遍历map,将参数添加到EiInfo中
		pMap.forEach((key, value) -> inInfo.set(key, value));
		return invoke(inInfo, serviceName, methodName);
	}

	/**
	 * @Title: invoke
	 * @Description: TODO(本地服务调用)
	 * @param inInfo inInfo : 参数EiInfo
	 * @param serviceName serviceName ：服务名
	 * @param methodName methodName ： 方法名
	 * @param paramNames paramNames ： 参数名数组
	 * @param params params ：参数值
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName, List<String> paramNames, Object... params) {
		//参数赋值
		if(paramNames != null){
			for (int i = 0; i < paramNames.size(); i++) {
				inInfo.set(paramNames.get(i), params[i]);
			}
		}
		return invoke(inInfo, serviceName, methodName);
	}

	/**
	 * @Title: invoke
	 * @Description: TODO(本地服务调用)
	 * @param serviceName serviceName ：服务名
	 * @param methodName methodName ： 方法名
	 * @param paramNames paramNames ： 参数名数组
	 * @param params params ：参数值
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo invoke(String serviceName, String methodName, List<String> paramNames, Object... params) {
		EiInfo inInfo = new EiInfo();
		return invoke(inInfo, serviceName, methodName, paramNames, params);
	}
	
	/**
	 * 发送短息和消息推送
	 * 
	 * <p>1.调用本地服务MTCF01.smsSend 发送短信
	 *  2.调用本地服务MTCF02.pushMsg 发送消息推送
	 * <p>
	 * 
	 * @Title: pushMsg
	 * @Description: TODO(这里用一句话描述这个方法的作用
	 * @param:  @param inInfo
	 * 		taskNo:任务单号
	 * 		smsType:短信类型（任务流程）
	 * 		pushType:消息推送类型（任务流程）
	 * @param:  @return
	 * @return: EiInfo  ： 无
	 * @throws
	 */
	public static EiInfo pushMsg(EiInfo inInfo){
		try{
			//发送短信
			inInfo.set(EiConstant.serviceName, "MTCF01");
			inInfo.set(EiConstant.methodName, "smsSend");
			EiInfo outInfo = XLocalManager.call(inInfo);
			//消息推送
			inInfo.set(EiConstant.serviceName, "MTCF02");
			inInfo.set(EiConstant.methodName, "pushMsg");
			outInfo = XLocalManager.call(inInfo);
			return outInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return inInfo;
		}
	}
	



	/**
	 * 获取指定用户组下所有的用户信息
	 * @Title: getGroupUsers
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param groupCode ： 用户组编码
	 * @param:  @param dataGroupCode ： 账套（院区）
	 * @param:  @return
	 * @return: List<Map<String, String>>  ： 用户集合
	 *		workNo : 用户工号
	 * 	    name : 姓名
	 * 	    contactTel : 电话
	 * @throws
	 */
	public static List<Map<String, String>> getGroupUsers(String groupCode, String dataGroupCode){
		//调用本地服务MTRE01.getGroupUsers获取用户信息
		EiInfo eiInfo = new EiInfo("user");
		eiInfo.set("dataGroupCode", dataGroupCode);
		eiInfo.set("roleCode", groupCode);
		EiInfo outInfo = invoke(eiInfo,"MTRE01","getRoleUsers");
		List<Map<String, String>> list = (List<Map<String, String>>) outInfo.get("users");
		return list;
	}




	/**
	 * 空处理
	 * @Title: toString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param object ： 参数
	 * @param:  @return
	 * @return: String  ： 参数字符串
	 * @throws
	 */
	public static String toString(Object object) {
		if(object == null){
			return "";
		}
		if(StringUtils.isBlank(object.toString())){
			return "";
		}
		return object.toString();
	}

	/**
	 * 空处理
	 * @Title: toString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param object ： 参数
	 * @param:  @param defultValue ： 默认值
	 * @param:  @return
	 * @return: String  ： 参数字符串
	 * @throws
	 */
	public static String toString(Object object, String defultValue) {
		if(StringUtils.isNotBlank(defultValue)){
			defaultBlankValue = defultValue;
		}
		if(object == null){
			return defaultBlankValue;
		}
		if(StringUtils.isBlank(object.toString())){
			return defaultBlankValue;
		}
		return object.toString();
	}

	/**
	 * 构建
	 * @Title: toString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param blockId ： block块的id
	 * @param:  @return
	 * @return: EiInfo  ： EiInfo对象
	 * @throws
	 */
	public static EiInfo buildBlankEiInfo (String blockId) {
		EiInfo inInfo = new EiInfo();
		EiBlock block = new EiBlock(blockId);
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
	 * 构建参数对象
	 *
	 * 在原先的基础上，新增3个固定从参数
	 *
	 * @Title: buildParams
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param inInfo inInfo
	 * @param list list
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 * @throws
	 **/
	public static Map<String, Object> buildParams (EiInfo inInfo,List<String> list) {
		List<String> params = new ArrayList<>();
		params.add("workNo");params.add("role"); params.add("dataGroupCode");
		params.addAll(list);
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, params);
		return map;
	}

	/**
	 * 判断是否是正整数
	 * @Title: isInteger
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param num 参数
	 * @return boolean : true=是， false;
	 * @throws
	 **/
    public static boolean isInteger(String num) {
		Pattern pattern = Pattern.compile("^[0-9]+$");
		return pattern.matcher(num).matches();
	}

	/**
	 * 根据附件id获取附件路径
	 * @Title: getFilePath
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param docId docId ： 附件id
	 * @return String ： 附件路径
	 * @throws
	 **/
	public static String getFilePath(String docId) {
		//获取文件管理器
		PlatFileUploadManager fileUpLoadManager = (PlatFileUploadManager) PlatApplicationContext.getBean("fileUpLoadManager");
		//获取附件信息
		Map<String,String> docMap = fileUpLoadManager.getDocById(docId);
		//获取路径
		if(docMap == null || docMap.isEmpty()){
			return "";
		} else {
			return docMap.get("realPath") + docMap.get("chgName");
		}
	}

	/**
	 * 计算完工率
	 *
	 * <p>完工率 = 工单完工数量/总工单数</p>
	 *
	 * @Title: calFinishRate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param dayTotal ： 总工单数
	 * @param:  @param dayFinish ： 工单完工数量
	 * @param:  @param scale : 保留位数
	 * @param:  @return
	 * @return: BigDecimal  ： 完工率(小数)
	 * @throws
	 */
	public static BigDecimal calFinishRate(Integer dayTotal, Integer dayFinish){
		if(dayTotal == 0){
			return BigDecimal.ZERO;
		}
		BigDecimal rate = new BigDecimal(dayFinish).multiply(new BigDecimal(100)).divide(new BigDecimal(dayTotal), 2, BigDecimal.ROUND_HALF_UP);
		return rate;
	}

}
