package com.baosight.wilp.cs.common;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.eu.dm.PlatFileUploadManager;
import com.baosight.wilp.common.util.BaseDockingUtils;
//import com.baosight.wilp.mt.cf.domain.MtPz;
//import com.baosight.wilp.mt.re.domain.MtConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

	private static SimpleDateFormat dayFormat = new SimpleDateFormat("YYYY-MM-dd");
	
	/**
	 * 接口（本地服务）调用
	 * @Title: invoke
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param param：可以为string、map、EiInfo
	 * @param:  @param serviceName：服务名
	 * @param:  @param methodName：方法名称
	 * @param:  @return
	 * @return: EiInfo  ： 调用服务的返回结果
	 * @throws
	 */
	public static EiInfo invoke (Object param, String serviceName, String methodName) {
		EiInfo inInfo = new EiInfo();
		if(param instanceof EiInfo) {
			inInfo = (EiInfo) param;
		} else if (param instanceof String) {
			inInfo.set((String) param, param);
		} else if (param instanceof Map) {
			for (Object key : ((Map) param).keySet()) {
				inInfo.set((String)key, ((Map) param).get(key));
			}
		}
		inInfo.set(EiConstant.serviceName, serviceName);
		inInfo.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(inInfo);
		return outInfo;
	}

	/**
	 * 执行本地服务调用
	 * @Title: invoke
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo ： inInfo对象
	 * @param:  @param serviceName ： 服务名
	 * @param:  @param methodName ： 方法名
	 * @param:  @param paramNames ： 参数名数组
	 * @param:  @param params：参数
	 * @param:  @return
	 * @return: EiInfo  ： 返回inInfo对象
	 * @throws
	 */
	public static EiInfo invoke(EiInfo inInfo, String serviceName, String methodName,String[] paramNames, Object... params) {
		EiInfo info = null;
		if(inInfo != null){
			info = inInfo;
		} else {
			info = new EiInfo();
		}
		//参数赋值
		if(paramNames != null){
			for (int i = 0; i < paramNames.length; i++) {
				info.set(paramNames[i], params[i]);
			}
		}
		//执行方法
		info.set(EiConstant.serviceName, serviceName);
		info.set(EiConstant.methodName, methodName);
		EiInfo outInfo = XLocalManager.call(info);
		return outInfo;
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
	 * 获取指定任务的账套
	 * 
	 * <p>调用本地服务MTRE01.getTaskDataGroupCode 获取指定任务的账套</p>
	 * 
	 * @Title: getTaskDataGroupCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param taskNo : 任务单号
	 * @param:  @return
	 * @return: String ： 账套
	 * @throws
	 */
    public static String getTaskDataGroupCode(String taskNo){
    	EiInfo inInfo = new EiInfo();
    	inInfo.set("taskNo", taskNo);
    	inInfo.set(EiConstant.serviceName, "MTRE01");
      	inInfo.set(EiConstant.methodName, "getTaskDataGroupCode");
      	EiInfo outInfo = XLocalManager.call(inInfo);
      	String dataGroupCode = outInfo.getString("dataGroupCode");
		return dataGroupCode;
    }
    
    /**
     * 获取指定用户的信息
     * 
     * <p>1.获取维修配置项:是否与基础对接。判断是否与基础对接</br>
     * 	2.是，调用微服务接口S_AC_FW_02获取指定人员 的信息</br>
     *  3.否，调用本地服务MTRE01.queryPersonnel获取人员信息</br>
     *  4.获取人员的角色信息
     * </p>
     * 
     * @Title: getUserInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo : 工号
     * @param:  @return
     * @return: Map<String,Object> : 用户信息
     * @throws
     */
    @SuppressWarnings("unchecked")
//	public static Map<String,Object> getUserInfo(String workNo) {
//    	//获取用户
//    	workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
//    	/** 与基础模块对接 */
//    	Map<String, Object> map = null;
//    	String mtBaseBocking = MTUtils.getMtConfigValueRedio("baseBocking");
//		if(MtConstant.MTBASEBOCKING_YES.equals(mtBaseBocking)){
//			//调用微服务接口S_AC_FW_02获取指定人员 的信息
//			map = MtBaseDockingUtils.queryPersonnelByWorkNo(workNo);
//			map.put("dataGroupCode", MtBaseDockingUtils.getDatagroupCode(workNo));
//		} else {
//			map = MtObsoleteUtils.getUserInfo(workNo);
//		}
//		//获取人员的角色信息
//		String roles = getUserRole(workNo);
//		if(map !=null){
//			map.put("role", roles);
//		}
//    	return map;
//    }
//
    /**
     * 获取指定用户的角色信息
     * 
     * <p>1.获取维修配置项:是否与基础对接。判断是否与基础对接</br>
     * 	2.是，将账套参数置为空；不是，获取对应的账套
     *  3.调用本地服务MTRE01.getUserRole获取角色信息
     * </p>
     * 
     * @Title: getRole
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo : 工号
     * @param:  @return
     * @return: Object  ： 角色
     * @throws
     */
//    private static Object getRole(String workNo) {
//    	String dataGroupCode = "";
//		workNo =StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
//    	/***基础对接**/
//		EiInfo eiInfo = new EiInfo("role");
//		String mtBaseBocking = MTUtils.getMtConfigValueRedio("baseBocking");
//		if(!MtConstant.MTBASEBOCKING_YES.equals(mtBaseBocking)){
//			//对账套赋值（5.0.x不支持）
//			dataGroupCode = MtObsoleteUtils.getDataGroupCode(workNo);
//		}
//		//调用本地服务MTRE01.getUserRole获取角色信息
//		eiInfo.set("dataGroupCode", dataGroupCode);
//    	eiInfo.set("workNo", workNo);
//    	EiInfo outInfo = invoke(eiInfo,"MTRE01","getUserRole");
//    	Object object = outInfo.get("role");
//		return object;
//	}
    
   /**
    * 获取指定用户的角色编码
    * @Title: getUserRole
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param:  @param workNo ： 工号
    * @param:  @return
    * @return: String  ： 角色编码字符串
    * @throws
    */
//    public static String getUserRole(String workNo) {
//    	Object object = getRole(workNo);
//    	if(object == null){
//    		return "";
//    	} else {
//    		List<Map<String, String>> list = (List<Map<String, String>>) object;
//    		Map<String, String> map = list.get(0);
//    		return map.get("roles");
//    	}
//    }

    /**
     * 获取指定用户的角色名称
     * @Title: getUserRoleName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param workNo ： 工号
     * @param:  @return
     * @return: String  ： 角色名称字符串
     * @throws
     */
//    public static String getUserRoleName(String workNo) {
//    	Object object = getRole(workNo);
//    	if(object == null){
//    		return "";
//    	} else {
//    		List<Map<String, String>> list = (List<Map<String, String>>) object;
//    		Map<String, String> map = list.get(0);
//    		return map.get("roleNames");
//    	}
//    }

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
	 * 获取调度中心角色的用户信息
	 * @Title: getDDZXUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @return
	 * @return: List<Map<String, String>>  ： 用户集合
	 * 		workNo : 用户工号
	 * 	    name : 姓名
	 * 	    contactTel : 电话
	 * @throws
	 */
//	public static List<Map<String, String>> getDDZXUser(String dataGroupCode){
//		List<Map<String, String>> list = new ArrayList<>();
//		/**基础对接*/
//		String mtBaseBocking = MTUtils.getMtConfigValueRedio("baseBocking");
//		//是基础对接,直接查询指定用户组下的人员信息
//		if(MtConstant.MTBASEBOCKING_YES.equals(mtBaseBocking)){
//			list = getGroupUsers(MtConstant.ROLE_DDZX, null);
//			//多院区独立调度中心时处理 ....
//		} else {
//			//不是,根据账套获取账套下的指定用户组的人员信息(5.0.x不支持)
//			list = getGroupUsers(MtConstant.ROLE_DDZX, dataGroupCode);
//		}
//		return list;
//	}

	/**
	 * 获取指定科室维修组长角色的用户信息
	 * @Title: getWXZZUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @param dataGroupCode ： 账套
	 * @param:  @return
	 * @return: List<Map<String, String>>  ： 用户集合
	 *		workNo : 用户工号
	 * 	    name : 姓名
	 * 	    contactTel : 电话
	 * @throws
	 */
//	public static List<Map<String, String>> getWXZZUser(String deptNum, String dataGroupCode){
//		List<Map<String, String>> list = new ArrayList<>();
//		/**基础对接*/
//		String mtBaseBocking = MTUtils.getMtConfigValueRedio("baseBocking");
//		//是基础对接,直接查询指定用户组下的人员信息
//		if(MtConstant.MTBASEBOCKING_YES.equals(mtBaseBocking)){
//			//获取指定科室的人员
//			List<Map<String, Object>> dList = BaseDockingUtils.getStaffByDeptNum(deptNum);
//			if(dList == null || dList.size() == 0){
//				return list;
//			}
//			//获取维修组长角色（用户组）下的人员信息
//			list = getGroupUsers(MtConstant.ROLE_WXZZ, null);
//			//人员信息过滤
//			list = list.stream().filter(map -> listContain(map,dList)).collect(Collectors.toList());
//		} else {
//			//不是,根据账套获取账套下的指定用户组的人员信息（5.0.x不支持）
//			list = getGroupUsers(MtConstant.ROLE_WXZZ, dataGroupCode);
//			//获取科室人员信息
//			List<Map<String, Object>> dList = MtObsoleteUtils.getDeptUser(deptNum);
//			//人员信息过滤
//			list = list.stream().filter(map -> listContain(map,dList)).collect(Collectors.toList());
//		}
//		return list;
//	}
	/**
	 * 判断人员信息集合中是否包含指定人员
	 * @Title: listContain
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map : 人员信息Map
	 * 		workNo : 工号
	 * 		name: 姓名
	 * 		contactTel ： 联系电话
	 * @param:  @param sList : 人员信息List
	 * 		workNo : 工号
	 * 		name: 姓名
	 * 		contactTel ： 联系电话
	 * @param:  @return
	 * @return: boolean
	 * @throws
	 */
	private static boolean listContain(Map<String, String> map, List<Map<String, Object>> sList) {
		for (Map<String, Object> pMap : sList) {
			if(map.get("workNo").equals(pMap.get("workNo").toString())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取指定任务的执行人信息
	 * @Title: getWXYUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param taskNo ： 科室编码
	 * @param:  @return
	 * @return: List<Map<String, String>>  ： 用户集合
	 * 		workNo : 用户工号
	 * 		name : 姓名
	 *      contactTel : 电话
	 * @throws
	 */
	public static List<Map<String, String>> getWXYUser(String taskNo) {
		List<Map<String, String>> list = new ArrayList<>();
		EiInfo invoke = invoke(null, "MTAC0103", "query",new String[]{"taskNo"},taskNo);
		EiBlock excutor = invoke.getBlock("excutor");
		if(excutor != null && excutor.getRowCount() > 0){
			List<Map<String, String>> rows = excutor.getRows();
			list.addAll(rows);
		}
		return list;
	}
    
    /**
     * 获取维修配置项对象
     * @Title: getMtConfig
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param configCode ： 配置项编码
     * @param:  @return
     * @return: MtPz ： 配置项对象
     * @throws
     */
//    public static MtPz getMtConfig(String configCode){
//    	EiInfo info = new EiInfo();
//    	info.set("config", configCode);
//    	EiInfo eiInfo = invoke(info, "MTCF04", "getConfig");
//    	Object object = eiInfo.get("mtConfig");
//    	if(object == null){
//    		return null;
//    	} else {
//    		return (MtPz) object;
//    	}
//    }

    /**
     * 获取指定配置项中单选项的值
     * @Title: getMtConfigValueRedio
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param configCode ： 配置项编码
     * @param:  @return
     * @return: String  
     * @throws
     */
//    public static String getMtConfigValueRedio(String configCode){
//    	MtPz pz = getMtConfig(configCode);
//    	if(pz == null){
//    		return "";
//    	} else {
//    		String redioValue = pz.getConfigValueRedio();
//    		return StringUtils.isBlank(redioValue) ? "" : redioValue;
//    	}
//    }
    
    /**
     * 获取指定配置项中多选项的值或输入文本的值
     * @Title: getMtConfigValueText
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param configCode ： 配置项编码
     * @param:  @return
     * @return: String  
     * @throws
     */
//    public static String getMtConfigValueText(String configCode){
//    	MtPz pz = getMtConfig(configCode);
//    	if(pz == null){
//    		return "";
//    	} else {
//    		String textValue = pz.getConfigValueText();
//    		return StringUtils.isBlank(textValue) ? "" : textValue;
//    	}
//    }

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
			EiInfo outInfo = invoke(eiInfo, "MTRE01", "querySerialNo");
			String lastSerialNo = outInfo.getString("serialNo");
			//判断工单号是否存在，不存在，构建一个初始工单号
			String head = createTop();
			if (StringUtils.isBlank(lastSerialNo)) {
				serialNo = prefix + ("0".equals(way) ? head + "001" : "000001");
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
					serialNo = prefix + String.valueOf(num+1000000).substring(1);
				}
			}
			//更新单号
			eiInfo.set("serialNo", serialNo);
			eiInfo.set("op", op);
			invoke(eiInfo, "MTRE01", "updateSerialNo");
		} finally {
			lock.unlock();
		}
		return serialNo;
	}
    
    /**
     * 生成指定格式的字符串（yyMMdd）
     * @Title: createTop
     * @Description: TODO(这里用一句话描述这个方法的作用)
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
	 * 将音频文件转成base64码
	 * @Title: audioToBase64Str
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param audioUrl ： 图片路径
	 * @param:  @return
	 * @return: String  ： 音频base64
	 * @throws
	 */
	public static String audioToBase64Str(String audioUrl) {
		InputStream inputStream = null;
		File file =new File(audioUrl);
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
	 * 空处理
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param object ： 参数
	 * @param:  @return
	 * @return: String  ： 参数字符串
	 * @throws
	 */
	public static String isEmpty(Object object) {
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
	 * @Title: isEmpty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param object ： 参数
	 * @param:  @param defultValue ： 默认值
	 * @param:  @return
	 * @return: String  ： 参数字符串
	 * @throws
	 */
	public static String isEmpty(Object object, String defultValue) {
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
	 * @Title: isEmpty
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
	 * 页面查询-报修日期初始赋值
	 * @Title: addTimeParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param info info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public static EiInfo addTimeParamter(EiInfo info) {
		//报修日期止
		Calendar cal = Calendar.getInstance();
		info.set("recCreateTimeE", dayFormat.format(cal.getTime()));
		//报修日期起
		cal.add(Calendar.MONTH, -1);
		info.set("recCreateTimeS", dayFormat.format(cal.getTime()));
		return info;
	}
}
