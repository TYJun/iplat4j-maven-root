package com.baosight.wilp.dm.common.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.BaseObject;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;
import com.ibm.db2.jcc.a.b;

/**
 * 保洁基础对接工具类
 * 
 * <p>调用基础档案的微服务接口工具类</p>
 * 
 * TODO(这里用一句话描述这个方法的作用)
 * 
 * @Title: CsBaseDockingUtils.java
 * @ClassName: CsBaseDockingUtils
 * @Package：com.baosight.wilp.cs.common
 * @author: fangzekai
 * @date: 2021年11月18日 上午9:27:21
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class CsBaseDockingUtils {
	
	/**
	 * 查询人员信息（有分页）
	 * 
	 * @Title: queryPersonnel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		offset		 :分页开始的索引
	 *		limit		 :获取的数量
	 *		workNo		 :员工工号
	 *		userName	 :用户姓名
	 *		deptNum      :科室编码
	 *		datagroupCode:院区编码
	 * @param:  @return
	 * @return: EiInfo  
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态 
	 * @throws
	 */
	public static EiInfo queryPersonnelPage (Map<String, Object> map) {
		//获取参数
		map.put("datagroupCode", map.get("dataGroupCode"));
		map.put("deptNum", map.get("wgroupNum"));
		return BaseDockingUtils.getStaffAllPage(map, "person");
	}
	
	/**
	 * 查询人员信息（无分页）
	 * @Title: queryPersonnelNoPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		workNo		 :员工工号
	 *		userName	 :用户姓名
	 *		datagroupCode:院区编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态  
	 * @throws
	 */
	public static EiInfo queryPersonnelNoPage (Map<String, Object> map) {
		//获取参数
		map.put("datagroupCode", map.get("dataGroupCode"));
		map.put("workNo", map.get("guaranteeNum"));
		List<Map<String, Object>> list = BaseDockingUtils.getStaffAllNoPage(map);
		return CommonUtils.BuildOutEiInfo("person", list);
	}
	
	/**
	 * 查询指定工号的人员信息
	 * 
	 * <p>调用微服务接口S_AC_FW_02，按工号查询人员信息</p>
	 * 
	 * @Title: queryPersonnelByWorkNo
	 * @Description: 查询指定工号的人员信息
	 * @param:  @param workNo : 工号
	 * @param:  @return
	 * @return: Map<String,String> 
	 * 		id			：	员工ID
	 *		workNo		：	员工工号 
	 *		name		：	员工姓名
	 *		gender		：	员工性别
	 *		contactTel	：	联系电话
	 * 		deptId		:	科室ID
	 *		deptNum		:	科室编码
	 *		deptName	:	科室名称 
	 *		idNo		：	身份证号
	 *		type		：	员工类型编码
	 *		staffType	：	员工类型
	 *		isService	：	是否是服务人员
	 *		status		：	员工状态编码
	 *		isStatus	：	员工状态  
	 * @throws
	 */
	public static Map<String, Object> queryPersonnelByWorkNo(String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		Map<String, Object> rMap = BaseDockingUtils.getStaffByWorkNo(workNo);
		return rMap;
	}
	
	/**
	 * 查询执行人 (根据科室查询人员)
	 * 
	 * <p>调用微服务接口S_AC_FW_03根据科室编码查询人员信息</p>
	 * 
	 * @Title: queryExcutor
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map ： 参数集合
	 * 		deptNum： 保洁科室编码
	 * @param:  @return
	 * @return: EiInfo 
	 * 		workNo ： 工号
	 *		name ： 姓名
	 *		gender ： 性别
	 *		deptNum ： 科室编码
	 *		contactTel ： 联系电话 
	 * @throws
	 */
	public static EiInfo queryExcutor (Map<String, Object> map) {
		List<Map<String, Object>> list = BaseDockingUtils.getStaffByDeptNum(map.get("deptNum").toString());
		return CommonUtils.BuildOutEiInfoWithLogicalPage(list, map, "result");
	}
	
	/**
	 * 根据用户所属科室编码获取返回结果workNo字符串
	 * 
	 * <p>调用微服务S_AC_FW_03根据科室编码查询科室下员工信息，遍历员工信息获取工号</p>
	 * 
	 * @Title: getStaffByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum ： 科室编码
	 * @param:  @return 
	 * @return: String  ： 工号字符串
	 * @throws
	 */
	public static String getStaffByDeptNum (String deptNum) {
		return BaseDockingUtils.getStaffWorkNosByDeptNum(deptNum);
	}
	
	/**
	 * 科室查询 -CSRE01
	 * 
	 * <p>调用微服务接口S_AC_FW_05查询科室信息</p>
	 * 
	 * @Title: queryDept
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
     * 		deptNum：科室编码
     * 		deptName：科室名称
     * 		dataGroupCode： 账套（院区）
     * @param:  @return
     * @return: EiInfo 
     * 		deptNum：科室编码
     * 		deptName：科室名称
	 * @throws
	 */
	public static EiInfo queryDept (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		//调用微服务接口S_AC_FW_05查询科室信息
		return BaseDockingUtils.getDeptAllPage(map, "dept");
	}
	
	
	/**
	 * <p>调用微服务接口S_AC_FW_12查询服务科室信息</p>
	 * 
	 * @Title: queryServerDept
	 * @Description: 选择保洁服务科室 -CSRE01
	 * @param:  @param map
	 * 		dataGroupCode ： 账套(院区)
	 * @param:  @return
	 * @return: EiInfo  
	 * 		wgroupNum ：保洁科室编码
     * 		wgroupName ： 保洁科室名称
	 * @throws
	 */
	public static EiInfo queryServerDept (Map<String, Object> map) {
		String dataGroupCode = map.get("dataGroupCode") == null ? "" : map.get("dataGroupCode").toString(); 
		List<Map<String, Object>> list = BaseDockingUtils.getServerDept(dataGroupCode);
		return CommonUtils.BuildOutEiInfo("dept", list);
	}
	
	/**
	 * 报修电话补全查询 -CSRE01
	 * 
	 * <p>1.获取参数，判读telNum参数是否为空</br>
	 *  2.调用微服务S_AC_FW_10获取科室和地点信息</br>
	 *  3.telNum为空时，直接返回科室和地点信息</br>
	 *  4.telNum不为空时，过滤科室和地点信息，返回指定电话对应的科室和地点信息
	 * </p>
	 * 
	 * @Title: selectOfficePhone
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
     * 		reqTelNum : 电话（模糊查询）
     * 		telNum ： 电话（精确查询）
     * 		dataGroupCode ： 账套（院区）
     * @param:  @return
     * @return: EiInfo  
     * 		telNum ： 联系电话
     * 		deptNum ： 科室编码
     * 		deptName ： 科室名称
     * 		building ： 楼
     * 		floor ： 层
	 * @throws
	 */
	public static EiInfo selectOfficePhone (Map<String, Object> map) {
		//获取参数
		map.put("datagroupCode", map.get("dataGroupCode"));
		//判读telNum参数是否为空
		boolean tempFlag = true;
		if(map.get("telNum") !=null && StringUtils.isNotBlank(map.get("telNum").toString())){
			tempFlag = false;
		} else {
			if(map.get("reqTelNum") == null || StringUtils.isBlank(map.get("reqTelNum").toString())){
				map.put("telNum", "1");//参数全为空时处理
			} else {
				map.put("telNum", map.get("reqTelNum"));
			}
		}
		//调用微服务S_AC_FW_10获取科室和地点信息
		List<Map<String, Object>> phoneList = BaseDockingUtils.getSpotAndDeptByPhone(map);
		//telNum不为空时，过滤科室和地点信息，返回指定电话对应的科室和地点信息
		if(!tempFlag){
			phoneList = phoneList.stream().filter(pMap -> map.get("telNum").equals(pMap.get("telNum")))
					.collect(Collectors.toList());
		}
		return CommonUtils.BuildOutEiInfo("phone",phoneList);
	}
	
	/**
     * <p>调用微服务接口S_AC_FW_09通过科室查询地点信息</p>
     * 
     * @Title: selectSpotInfoByDeptName
     * @Description: 楼、层、地点信息补全 -CSRE01
     * @param:  @param map
     *      deptNum ： 科室名称
     * @param:  @return
     * @return: EiInfo  
     *      building ： 楼
     *      floor ： 层
     *      room ： 房间
     *      spotNum ： 地点编码
     *      spotName ： 地点名称
     * @throws
     */
    public static EiInfo selectSpotInfoByDeptName (Map<String, Object> map) {
        map.put("datagroupCode", map.get("dataGroupCode"));
        String deptNum = map.get("deptNum") == null ? "" : map.get("deptNum").toString(); 
        map.put("deptNum", deptNum);
        //调用微服务接口S_AC_FW_05查询科室信息
        return BaseDockingUtils.getSpotByDept(map);
    }
	

	
	/**
	 * 楼补全 -CSRE01
	 * 
	 * <p>调用微服务接口S_AC_FW_13查询楼信息</p>
	 * 
	 * @Title: selectSpotBuildingName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		building ： 楼
	 * @param:  @return
	 * @return: EiInfo  
	 * 		building ： 楼
	 * @throws
	 */
	public static EiInfo selectSpotBuildingName (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		String building = map.get("building") == null ? "" : map.get("building").toString(); 
		//调用微服务接口S_AC_FW_13查询楼信息
		List<Map<String, Object>> list = BaseDockingUtils.getBuilding(building);
		return CommonUtils.BuildOutEiInfo("building", list);
	}
	
	/**
	 * 层补全 -CSRE01
	 * 
	 * <p>调用微服务接口S_AC_FW_14查询楼信息</p>
	 * 
	 * @Title: selectSpotFloorName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		building ： 层
     * 		floor ： 层
     * @param:  @return
     * @return: EiInfo 
     * 		floor ： 层 
	 * @throws
	 */
	public static EiInfo selectSpotFloorName (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		String building = map.get("building") == null ? "" : map.get("building").toString(); 
		String floor = map.get("floor") == null ? "" : map.get("floor").toString(); 
		List<Map<String, Object>> list = BaseDockingUtils.getFloor(building, floor);
		return CommonUtils.BuildOutEiInfo("floor", list);
	}
	
	/**
	 * 地点补全 -CSRE01
	 * 
	 * <p>调用微服务接口S_AC_FW_15查询地点信息</p>
	 * 
	 * @Title: selectSpotRoomName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		building ： 层
     * 		floor ： 层
     * 		spotName ： 地点名称 
     * @param:  @return
     * @return: EiInfo 
     * 		reqSpotName ： 房间
     * 		spotNum ： 地点编码
     * 		spotName ： 地点名称
	 * @throws
	 */
	public static EiInfo selectSpotRoomName (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		String building = map.get("building") == null ? "" : map.get("building").toString(); 
		String floor = map.get("floor") == null ? "" : map.get("floor").toString(); 
		String room = map.get("spotName") == null ? "" : map.get("spotName").toString(); 
		List<Map<String, Object>> list = BaseDockingUtils.getRoom(building, floor, room);
		for (Map<String, Object> pMap : list) {
			pMap.put("reqSpotName", pMap.get("room"));
			pMap.put("spotNum", pMap.get("spot_num"));
			pMap.put("spotName", pMap.get("spot_name"));
		}
		return CommonUtils.BuildOutEiInfo("room", list);
	}
	
//	/**
//	 * 发送短信
//	 * 
//	 * <p>调用微服务接口S_MC_FW_01发送短信</p>
//	 * 
//	 * @Title: sendMsg
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param:  @param recvList ： 接收人集合
//	 * @param:  @param smsContent ：消息内容
//	 * @param:  @return
//	 * @return: boolean  : 是否成功结果
//	 * @throws
//	 */
//	public static boolean sendMsg(List<Map<String,String>> recvList, String smsContent){
//		List<String> list = listChange(recvList);
//		String templateCode = CSUtils.getCSConfigValueText("dxTemplateCode");
//		List<String> paramList = new ArrayList<>();
//		paramList.add(smsContent);
//		System.out.println("保洁短信参数：workNoList:"+list.toString()+",paramList:"+paramList.toString()+"templateCode:"+templateCode);
//		return BaseDockingUtils.sendMsg(list, paramList, templateCode);
//	}
	
//	/**
//	 * 消息推送
//	 * 
//	 * <p>调用微服务接口S_MC_FW_02发送消息推送</p>
//	 * 
//	 * @Title: pushMsg
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param:  @param recvList ： 接收人集合
//	 * @param:  @param smsContent ： 消息内容
//	 * @param:  @return
//	 * @return: boolean  : 是否成功结果
//	 * @throws
//	 */
//	public static boolean pushMsg(List<Map<String,String>> recvList, String smsContent){
//		String appCode = PlatApplicationContext.getProperty("common_appCode");
//		String templateCode = CSUtils.getCSConfigValueText("tsTemplateCode");
//		List<String> list = listChange(recvList);
//		List<String> paramList = new ArrayList<>();
//		paramList.add(smsContent);
//		System.out.println("保洁消息推送参数：workNoList:"+list.toString()+",paramList:"+paramList.toString()
//			+"templateCode:"+templateCode+",appCode:"+appCode);
//		return BaseDockingUtils.pushMsg(list, paramList, templateCode, appCode, null);
//	}
//	
	/**
	 * 判断EiInfo是否为空 
	 * @Title: isEmptyInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param outInfo : EiInfo对象
	 * @param:  @param blockId : Block的id
	 * @param:  @return
	 * @return: boolean  ： 是否为空结果
	 * @throws
	 */
	public static boolean isEmptyInfo(EiInfo outInfo, String blockId){
		blockId = StringUtils.isBlank(blockId) ? "result" : blockId;
		EiBlock block = outInfo.getBlock(blockId);
		if(block == null){
			return true;
		}
		if(block.getRowCount() > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取工号list
	 * @Title: listChange
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param recvList ： 人员信息集合List
	 * @param:  @return
	 * @return: List<String>  ： 工号集合List
	 * @throws
	 */
	private static List<String> listChange(List<Map<String,String>> recvList) {
		List<String> list = new ArrayList<>();
		recvList.forEach(map -> list.add(map.get("workNo")));
		return list;
	}
	
	/**
	 * 获取指定人员的院区编码
	 * 
	 * <p>调用微服务接口S_AU_FW_03获取人员的院区编码</p>
	 * 
	 * @Title: getDatagroupCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo ： 工号
	 * @param:  @return
	 * @return: String  ： 院区编码
	 * @throws
	 */
	public static String getDatagroupCode (String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getUser().getUsername() : workNo;
		return BaseDockingUtils.getUserGroupByWorkNo(workNo);
	}

	/**
	 * 获取指定科室的院区编码
	 * 
	 * <p>调用微服务接口S_AU_FW_04获取指定科室的院区编码</p>
	 * 
	 * @Title: getDatagroupCodeByDeptNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param deptNum : 科室编码
	 * @param:  @return
	 * @return: String  ： 账套
	 * @throws
	 */
	public static String getDatagroupCodeByDeptNum(String deptNum) {
		return BaseDockingUtils.getUserGroupByDeptNum(deptNum);
	}
	
	/***
	 * 查询物资
	 * 
	 * @Title: queryMat
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param map
	 * 		matCode : 物资编码
	 *		matName : 物资名称
	 *		matClassId : 物资分类ID
	 *		matClassCode : 物资分类编码
	 *		matClassName : 物资分类名称
	 * @param:  @return
	 * @return: EiInfo  
	 * 		id : 物资ID
	 *		matCode : 物资编码
	 *		matName : 物资名称
	 *		matClassId : 物资分类ID
	 *		matClassCode : 物资分类编码
	 *		matClassName : 物资分类名称
	 *		matSpec : 物资规格
	 *		matModel : 物资型号
	 *		unit : 单位
	 *		price : 价格
	 *		supplier : 供应商
	 *		manufacturer : 制造商
	 *		matTypeCode : 物资大类编码
	 * @throws
	 */
	public static EiInfo queryMat (Map<String, Object> map) {
		map.put("datagroupCode", map.get("dataGroupCode"));
		map.put("matClassName", map.get("matTypeName"));
		EiInfo eiInfo = BaseDockingUtils.getMatPage(map, "result");
		EiBlock block = eiInfo.getBlock("result");
		if(block !=null && block.getRowCount() > 0){
			List<Map<String, Object>> rows = block.getRows();
			rows.forEach(pMap -> {
				pMap.put("matNum", pMap.get("matCode"));
				pMap.put("matTypeNum", pMap.get("matClassCode"));
				pMap.put("matTypeName", pMap.get("matClassName"));
			});
		}
		return eiInfo;
	}

}
