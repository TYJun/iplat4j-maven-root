package com.baosight.wilp.hi.common.util;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
public class HIUtils {
	
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
	 * 生成单号
	 *
	 * <p>1.查询model_number表中的单号</br>
	 *  2.判断工单号是否存在，不存在，构建一个初始工单号</br>
	 *  3.存在,截取工单号的数字部分，然后加1</br>
	 *  4.重新拼接，返回</br>
	 *
	 * @Title: generationSerialNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param type ： 单号在model_number表中的标识符
	 * @param:  @param prefix ： 前缀
	 * @param:  @return
	 * @return: String  ： 单号
	 * @throws
	 */
	public static String generationSerialNo(String module, String prefix, String way){
		lock.lock();
		String serialNo = "";String op = "update";
		try {
			//查询model_number表中的序号
			EiInfo eiInfo = new EiInfo();
			eiInfo.set("type", module);
			EiInfo outInfo = invoke(eiInfo, "HISQ0101", "querySerialNo");
			String lastSerialNo = outInfo.getString("serialNo");
			//判断工单号是否存在，不存在，构建一个初始工单号
			String head = HIUtils.createTop();
			if (StringUtils.isBlank(lastSerialNo)) {
				serialNo = prefix + ("0".equals(way) ? head + "001" : "00000001");
				op = "insert";
			} else {
				//存在,截取单号的除固定部分之外的数字部分，然后加1
				int num = Integer.parseInt(lastSerialNo.replace(prefix, "").replace(head, "")) + 1;
				//判断生成方式，0=每天都从001开始生成，1= 直接累加
				if("0".equals(way)){
					if(!head.equals(lastSerialNo.replace(prefix, "").substring(0, 6))){
						serialNo = prefix + head + "001";
					} else {
						//重新拼接，返回
						String remianNo = num > 999 ? String.valueOf(num) : String.valueOf(num+1000).substring(1);
						serialNo = prefix + head + remianNo;
					}
				} else {
					serialNo = prefix + String.valueOf(num+100000000).substring(1);
				}
			}
			//更新单号
			eiInfo.set("serialNo", serialNo);
			eiInfo.set("op", op);
			invoke(eiInfo, "HISQ0101", "updateSerialNo");
		} finally {
			lock.unlock();
		}
		return serialNo;
	}



	/**
	 * 生成指定格式的字符串（yyMMdd）
	 * @Title: createTop
	 * @Description:
	 * @param:  @return
	 * @return: String
	 * @throws
	 */
	public static String createTop() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String head = sdf.format(new Date());
		return head;
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







}
