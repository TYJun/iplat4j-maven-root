package com.baosight.wilp.ps.pc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.PSPCTmealPatientCard;
import com.baosight.wilp.ps.pc.domain.PSPCTsUserloginInfo;
import com.baosight.wilp.ps.pc.domain.PSPCTscOrderPatientInfo;
import com.baosight.wilp.ps.pc.domain.PSPCUser;
import com.baosight.wilp.ss.bm.domain.SSBMCtm01;
import com.baosight.wilp.ss.bm.domain.SSBMSjzd02;
import com.baosight.wilp.ss.bm.domain.WeChatUser01;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.XServiceUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * ServicePSPCUser 用户信息service
 * 
 * @Title: ServicePSPCUser.java
 * @ClassName: ServicePSPCUser
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月9日 下午1:43:37
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCUser extends ServiceBase {
private ServicePSPCUser pspcUser;
PSPCUser pspcuser = new PSPCUser();

    /**
     * 
     * 病员修改联系电话
     *
     * @Title: changePhoneNo 
     * @param inInfo
     * @return
     * @throws Exception 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午1:44:47
     */
	public EiInfo changePhoneNo(EiInfo inInfo) throws Exception {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 工号
		String patientNum = StringUtil.toString(attr.get("patientNum"));
		String newTel = StringUtil.toString(attr.get("newTel"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("patientNum", patientNum);
		paramMap.put("patientTel", newTel);
		if(StringUtils.isBlank(newTel)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数newTel为空！");
			return inInfo;
		}
		//执行update
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("sql","PSPCTmealPatientCard.updatePhoneNo");
		map.put("map",paramMap);
		EiInfo callMenu = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", map);
		if(!Boolean.parseBoolean(StringUtil.toString(callMenu.get("success")))){
			inInfo.setStatus(-1);
			inInfo.setMsg("更新失败！");
		}else {
			inInfo.setStatus(0);
		}
		return inInfo;
	}
	

	/**
	 * 
	 * 根据员工工号获取员工信息
	 * 1.构建查询条件
	 * 2.获取USER信息 调用服务接口
	 * @Title: getWorkInfoByWorkNo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:45:20
	 */
	public EiInfo getWorkInfoByWorkNo(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 工号
		String workNo = StringUtil.toString(attr.get("workNo"));
		/** 1.构建查询条件*/
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workNo", workNo);
		if(StringUtils.isBlank(workNo)) {
			inInfo.setStatus(-1);
			inInfo.set("respMsg","参数workNo为空！");
			return inInfo;
		}
		List<HashMap<String, Object>> works = new ArrayList<HashMap<String,Object>>();
		/**2.获取USER信息 调用服务接口*/
		EiInfo callUser = XServiceUtil.call("S_AC_FW_02", paramMap);
		if(callUser.getStatus() > 0){
			
			HashMap<String,Object> user = (HashMap<String, Object>) callUser.get("result");
			works.add(user);
			if(works != null && works.size() > 0 ) {
				inInfo.setStatus(0);
			}
			inInfo.set("obj", works);
		}else {
			//没查到
			inInfo.setStatus(-1);
			inInfo.setMsg("查询失败");
		}
		//List<HashMap<String,Object>> works = dao.query("PSPCVpersonnel.query", paramMap);
		return inInfo;
	}
	
	/**
	 * 
	 * 根据住院号模糊查询所有符合的病员
	 *
	 * @Title: queryPatientInfo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:45:48
	 */
	public EiInfo queryPatientInfo(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 卡片信息
		String patientCode = StringUtil.toString(attr.get("patientCode"));
		// 构建查询条件
		List<PSPCTmealPatientCard> patients = dao.query("PSPCTmealPatientCard.queryPatientInfo", patientCode);
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		//获取卡片信息
		for (int i = 0; i < patients.size(); i++) {
			PSPCTmealPatientCard patient = patients.get(i);
			paramMap.put("cardUserCode", patient.getPatientNum());
			//查询电子账户信息
			EiInfo callCardInfo = LocalServiceUtil.callNoTx("PSAEKPXX", "queryCardInfo", paramMap);
			List cardInfos = (List) callCardInfo.getAttr().get("cardInfo");
			if(cardInfos.size() > 0){
				JSONObject json = JSONObject.fromObject(cardInfos.get(0));
				patient.setBalance(json.getString("cardBalance"));
				patient.setCardBaseCode(json.getString("cardBaseCode"));
			}
		}
		inInfo.set("obj", patients);
		return inInfo;
	}
	

	/**
	 * 
	 * 根据地点编码获取病员信息
	 *
	 * @Title: queryPatientInfoByAddNum 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:46:08
	 */
	public EiInfo queryPatientInfoByAddNum(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 地点编码
		String addNum = StringUtil.toString(attr.get("addNum"));
		String patientCode = StringUtil.toString(attr.get("patientCode"));
		String canteenNum = StringUtil.toString(attr.get("canteenNum"));
		String registerType = StringUtil.toString(attr.get("registerType"));
		HashMap<String,Object> hashmap = new HashMap<String, Object>();
		hashmap.put("addNum" , addNum);
		hashmap.put("patientCode" , patientCode);
		hashmap.put("canteenNum" , canteenNum);
		hashmap.put("registerType" , registerType);
		// 构建查询条件
		List<PSPCTscOrderPatientInfo> patient = dao.query("PSPCTscOrderPatientInfo.queryPatientInfoByAddNum", hashmap);
		if(patient == null || patient.size()<1) {
			//没查到历史信息，获取地点绑定的楼层信息
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("addNum", addNum);
			List<SSBMCtm01> addres = dao.query("SSBMCtm01.query", map);
			inInfo.set("type", "address");
			inInfo.set("obj", addres);
		}else {
			inInfo.set("type", "patient");
			inInfo.set("obj", patient);
		}
		return inInfo;
	}
	

	/**
	 * 
	 * 根据住院号获取病员信息病员信息
	 *
	 * @Title: queryPatientInfoByHisNo 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:46:19
	 */
	public EiInfo queryPatientInfoByHisNo(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 住院号
		String patientCode = StringUtil.toString(attr.get("patientCode"));
		// 构建查询条件
		List<PSPCTmealPatientCard> patient = dao.query("PSPCTmealPatientCard.queryPatientInfoPrecise", patientCode);
		inInfo.set("obj", patient);
		return inInfo;
	}
	

	/**
	 * 
	 * 扫码获取病员信息
	 *
	 * @Title: queryPatientInfoByID 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:46:30
	 */
	public EiInfo queryPatientInfoByID(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 卡片信息
		String cardBaseCode = StringUtil.toString(attr.get("cardBaseCode"));
		String patientNum = StringUtil.toString(attr.get("patientNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cardBaseCode", cardBaseCode);
		
		//查询本地信息
		List<PSPCTmealPatientCard> patient = dao.query("PSPCTmealPatientCard.queryPatientInfoPrecise", patientNum);
		inInfo.set("obj", patient);
		return inInfo;
	}
	

	/**
	 * 
	 * 一卡通 根据住院号获取病员信息病员信息
	 *
	 * @Title: queryPatientInfoToCard 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:47:26
	 */
	public EiInfo queryPatientInfoToCard(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 卡片信息
		String patientNum = StringUtil.toString(attr.get("patientNum"));
		// 构建查询条件
		List<PSPCTmealPatientCard> patient = dao.query("PSPCTmealPatientCard.queryPatientInfoToCard", patientNum);
		inInfo.set("obj", patient);
		return inInfo;
	}
	
	
	
	/**
	 * 
	 * 病员登录
	 *
	 * @Title: patientLogin 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:47:56
	 */
	public EiInfo patientLogin(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData result = new ResultData();
		EiInfo outInfo = new EiInfo();
		// 获取传参
		String userName = StringUtil.toString(attr.get("userName"));
		String passWord = StringUtil.toString(attr.get("passWord"));
		String dataGroupCode = StringUtil.toString(attr.get("dataGroupCode"));
		// 校验传参
		if(StringUtils.isBlank(userName)){
			result.setRespMsg("账户不能为空！");
			result.setSuccess(false);
			outInfo.set("result", result);
			outInfo.setStatus(result.isSuccess() ? 1 : -1);
			return outInfo;
		} else if(StringUtils.isBlank(passWord)){
			result.setRespMsg("密码不能为空！");
			result.setSuccess(false);
			outInfo.set("result", result);
			outInfo.setStatus(result.isSuccess() ? 1 : -1);
			return outInfo;
		} else if(StringUtils.isBlank(dataGroupCode)){
			result.setRespMsg("账套为空！");
			result.setSuccess(false);
			outInfo.set("result", result);
			outInfo.setStatus(result.isSuccess() ? 1 : -1);
			return outInfo;
		}
	
		Map<String ,Object> pMap = new HashMap<String ,Object>();
		pMap.put("patientNum", userName);
		
		//查询登录人
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sql","PSPCTmealPatientCard.queryPatientInfoPrecise");
		paramMap.put("str",userName);
		EiInfo callQueryPatient = LocalServiceUtil.callNoTx("SSBMTy", "querySqlByString", paramMap);
		
		List<PSPCTmealPatientCard> patients = (List<PSPCTmealPatientCard>) callQueryPatient.get("result");
		
		if(patients.isEmpty()){
			result.setRespMsg("该用户未注册！");
			result.setSuccess(false);
			outInfo.set("result", result);
			outInfo.setStatus(result.isSuccess() ? 1 : -1);
			return outInfo;
		} else {
			PSPCTmealPatientCard tMealPati = patients.get(0);
			//加密校验
			if(passWord.equals(tMealPati.getIdenNo())){
				result.setRespMsg("验证成功！");
				result.setSuccess(true);
				result.setObj(tMealPati);
				outInfo.set("result", result);
				outInfo.setStatus(result.isSuccess() ? 1 : -1);
				return outInfo;
			} else {
				result.setRespMsg("密码错误！");
				result.setSuccess(false);
				outInfo.set("result", result);
				outInfo.setStatus(result.isSuccess() ? 1 : -1);
				return outInfo;
			}
		}
	}

	/**
	 *
	 * 职工登录基础信息
	 *
	 * @Title: login
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:48:27
	 */
	public EiInfo login(EiInfo inInfo) {
		Map attr = inInfo.getAttr();
		String userName = StringUtil.toString(attr.get("userName"));
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//获取USER信息
		paramMap.put("workNo", userName);
		//调用服务接口
		EiInfo callUser = XServiceUtil.call("S_AC_FW_02", paramMap);
		if(callUser.getStatus() < 0){
			//没查到
		}else {
			Map<String,String> user = (Map<String, String>) callUser.get("result");
			if(user != null && user.size() > 0) {
				//获取DEPT信息调用服务接口
				EiInfo callDept = XServiceUtil.call("S_AC_FW_07", paramMap);
				if(callUser.getStatus() < 0){
					//没查到
				}else {
					Map<String,String> dept = (Map<String, String>) callDept.get("result");
					if(dept != null && dept.size() > 0 ) {
						//将相关信息存入map
						map.put("name", user.get("name"));
						map.put("contactTel", user.get("contactTel"));

						map.put("deptCode", dept.get("deptNum"));
						map.put("deptName", dept.get("deptName"));
					}
				}
				//获取院区信息调用服务接口
				EiInfo callDataGroup = XServiceUtil.call("S_AU_FW_03", paramMap);
				if(callDataGroup.getStatus() < 0){
					//没查到
				}else {
					String datagroupCode = (String) callDataGroup.get("datagroupCode");
					if(StringUtils.isNotBlank(datagroupCode)) {
						//将相关信息存入map
						map.put("datagroupCode", datagroupCode);
					}else {
						map.put("datagroupCode", "");
					}
				}
				List<SSBMSjzd02> types = dao.query("SSBMSjzd02.queryTypeValue", "loginVaildate");
				//组装查询结果
				String loginVaildate = "";
				if(types != null && types.size() > 0) {
					for (int i = 0; i < types.size(); i++) {
						SSBMSjzd02 type = types.get(i);
						if("loginVaildate".equals(type.getTypename()))
							loginVaildate = type.getTypecode();
					}
				}
				if (StringUtil.isNotEmpty(loginVaildate) && loginVaildate.equals("true")) {
					List<PSPCTsUserloginInfo> loginInfos = dao.query("PSPCTsUserloginInfo.queryNeedVaildate", userName);
					if (loginInfos.size() > 0) {
						map.put("needValidate", "N");
					} else {
						map.put("needValidate", "Y");
					}
				} else {
					map.put("needValidate", "N");
				}
			}else {
				inInfo.setStatus(-1);
			}
		}

		inInfo.set("map", map);
		return inInfo;
	}

	/**
	 *
	 * 企业微信免登陆方法
	 *
	 * @Title: popLogin
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: ke
	 * @date: 2022年5月9日 下午1:47:56
	 */
	public EiInfo popLogin(EiInfo inInfo) {
		Map attr = inInfo.getAttr();
		ResultData result = new ResultData();
		EiInfo outInfo = new EiInfo();
		// 获取传参
		String mobile = StringUtil.toString(attr.get("mobile"));
		String userId = StringUtil.toString(attr.get("userId"));
		// 校验传参
		if(StringUtils.isBlank(userId)){
			result.setRespMsg("userId不能为空！");
			result.setSuccess(false);
			outInfo.set("result", result);
			outInfo.setStatus(result.isSuccess() ? 1 : -1);
			return outInfo;
		}
		Map<String ,Object> pMap = new HashMap<String ,Object>();
		pMap.put("userId", userId);

		// 查询数据
		List<WeChatUser01> list = dao.query("PSPCWeChat.queryUser", pMap);

		System.out.println(list);
		// 校验传参
		if(list.isEmpty()){
			result.setRespMsg("人员工号不能为空！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		System.out.println(list.get(0).getUserName());
		String userName = list.get(0).getUserName();
		String datagroupCode = list.get(0).getDatagroupCode();

		System.out.println(userName);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//获取USER信息
		paramMap.put("workNo", userName);
		//调用服务接口
		EiInfo callUser = XServiceUtil.call("S_AC_FW_02", paramMap);
		if (callUser.getStatus() < 0) {
			//没查到
		} else {
			Map<String, String> user = (Map<String, String>) callUser.get("result");
			if (user != null && user.size() > 0) {
				//获取DEPT信息调用服务接口
				EiInfo callDept = XServiceUtil.call("S_AC_FW_07", paramMap);
				if (callUser.getStatus() < 0) {
					//没查到
				} else {
					Map<String, String> dept = (Map<String, String>) callDept.get("result");
					if (dept != null && dept.size() > 0) {
						//将相关信息存入map
						map.put("name", user.get("name"));
						map.put("contactTel", user.get("contactTel"));
						map.put("deptCode", dept.get("deptNum"));
						map.put("deptName", dept.get("deptName"));
					}
				}

				List<SSBMSjzd02> types = dao.query("SSBMSjzd02.queryTypeValue", "loginVaildate");
				//组装查询结果
				String loginVaildate = "";
				if (types != null && types.size() > 0) {
					for (int i = 0; i < types.size(); i++) {
						SSBMSjzd02 type = types.get(i);
						if ("loginVaildate".equals(type.getTypename()))
							loginVaildate = type.getTypecode();
					}
				}
				if (StringUtil.isNotEmpty(loginVaildate) && loginVaildate.equals("true")) {
					List<PSPCTsUserloginInfo> loginInfos = dao.query("PSPCTsUserloginInfo.queryNeedVaildate", userName);
					if (loginInfos.size() > 0) {
						map.put("needValidate", "N");
					} else {
						map.put("needValidate", "Y");
					}
				} else {
					map.put("needValidate", "N");
				}
				String name = (String) map.get("name");
				String contactTel = (String) map.get("contactTel");
				String deptCode = (String) map.get("deptCode");
				String deptName = (String) map.get("deptName");
//					String datagroupCode = (String) map.get("datagroupCode");
				String needValidate = (String) map.get("needValidate");

			} else {
				inInfo.setStatus(-1);
			}
		}
		inInfo.set("map", map);
		inInfo.addRow("result", map);

		System.out.println(inInfo);
		return inInfo;


	}

	/**
	 *
	 * 企业微信代开发应用的免登陆方法
	 *
	 * @Title: wecahtLogin
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: ke
	 * @date: 2022年10月20日 下午1:47:56
	 */
	public EiInfo wecahtLogin(EiInfo inInfo) {
		Map attr = inInfo.getAttr();
		ResultData result = new ResultData();
		EiInfo outInfo = new EiInfo();
		// 获取传参
		String userId = StringUtil.toString(attr.get("userId"));
		// 校验传参
		if(StringUtils.isBlank(userId)){
			result.setRespMsg("userId不能为空！");
			result.setSuccess(false);
			outInfo.set("result", result);
			outInfo.setStatus(result.isSuccess() ? 1 : -1);
			return outInfo;
		}
		Map<String ,Object> pMap = new HashMap<String ,Object>();
		pMap.put("userId", userId);

		// 查询数据
		List<WeChatUser01> list = dao.query("PSPCWeChat.queryUser", pMap);

		System.out.println(list);
		// 校验传参
		if(list.isEmpty()){
			result.setRespMsg("人员工号不能为空！");
			inInfo.setStatus(-1);
			return inInfo;
		}
			System.out.println(list.get(0).getUserName());
			String userName = list.get(0).getUserName();
			String datagroupCode = list.get(0).getDatagroupCode();

			System.out.println(userName);
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//获取USER信息
			paramMap.put("workNo", userName);
			//调用服务接口
			EiInfo callUser = XServiceUtil.call("S_AC_FW_02", paramMap);

			if (callUser.getStatus() < 0) {
				//没查到
			} else {
				Map<String, String> user = (Map<String, String>) callUser.get("result");
				//查找当前员工详细的人员信息
				List<HashMap<String,String>> user1 =  dao.query("PSPCWeChat.queryUserInfo2",userId);
				//查找当前员工的角色信息
				List<HashMap<String,String>> roleInfo =  dao.query("PSPCWeChat.queryUserRole",userId);
				if (user != null && user.size() > 0) {
					//获取DEPT信息调用服务接口
					EiInfo callDept = XServiceUtil.call("S_AC_FW_07", paramMap);
					if (callUser.getStatus() < 0) {
						//没查到
					} else {
						Map<String, String> dept = (Map<String, String>) callDept.get("result");
						if (dept != null && dept.size() > 0) {
							//将相关信息存入map
							map.put("name", user.get("name"));
							map.put("contactTel", user.get("contactTel"));
							map.put("deptCode", dept.get("deptNum"));
							map.put("deptName", dept.get("deptName"));
							map.put("datagroupCode", user1.get(0).get("datagroupCode"));
							map.put("workNo", user1.get(0).get("workNo"));
							map.put("userId", user1.get(0).get("userId"));
							map.put("needValidate", user1.get(0).get("needValidate"));
							StringBuffer sb = new StringBuffer();
							StringBuffer RoleBuffer = new StringBuffer();
							StringBuffer RoleNameBuffer = new StringBuffer();
							for(int i=0;i<=roleInfo.size()-1;i++)
							{
//								Map dqMap = (Map)role.get(i);
								if(i<=roleInfo.size()-1){
									map.put("role", RoleBuffer.append(roleInfo.get(i).get("role")+","));
									map.put("roleName", RoleNameBuffer.append(roleInfo.get(i).get("roleName")+","));
								}else {
									map.put("role",roleInfo.get(i).get("role"));
									map.put("roleName", roleInfo.get(i).get("roleName"));
								}
							}
							System.out.println("maprole:"+map);
//							System.out.println("buffer"+RoleBuffer);
//							System.out.println("RoleNamebuffer"+RoleNameBuffer);

						}
					}

					List<SSBMSjzd02> types = dao.query("SSBMSjzd02.queryTypeValue", "loginVaildate");
					//组装查询结果
					String loginVaildate = "";
					if (types != null && types.size() > 0) {
						for (int i = 0; i < types.size(); i++) {
							SSBMSjzd02 type = types.get(i);
							if ("loginVaildate".equals(type.getTypename()))
								loginVaildate = type.getTypecode();
						}
					}
					if (StringUtil.isNotEmpty(loginVaildate) && loginVaildate.equals("true")) {
						List<PSPCTsUserloginInfo> loginInfos = dao.query("PSPCTsUserloginInfo.queryNeedVaildate", userName);
						if (loginInfos.size() > 0) {
							map.put("needValidate", "N");
						} else {
							map.put("needValidate", "Y");
						}
					} else {
						map.put("needValidate", "N");
					}
				} else {
					inInfo.setStatus(-1);
				}
			}
			inInfo.set("map", map);
			inInfo.addRow("result", map);
			return inInfo;


	}

//    加载当前登录人的信息(单纯企业微信)
	public EiInfo getLoginMap(EiInfo inInfo) {
		ResultData result = new ResultData();
		Map attr = inInfo.getAttr();
		String userId =  StringUtil.toString(attr.get("userId"));
		System.out.println("获取参数:"+userId);
		EiInfo out = new EiInfo();
		List<Map<String, String>> list =  dao.query("PSPCWeChat.queryUserInfo",userId);
		if (list.isEmpty()){
			out.addMsg("404");
			return out;
		}else {
			result.setObj(list);

			out.set("obj",list);
			//查找当前员工的角色信息
			String mobile = list.get(0).get("contactTel");
			List<HashMap<String,String>> roleInfo =  dao.query("PSPCWeChat.queryUserRole",mobile);
			out.set("roleInfo",roleInfo);

			System.out.println(list);
			return out;

		}

	}

	//加载当前登录人的信息（第三方应用开发企业微信）
	public EiInfo getWechatLoginMap(EiInfo inInfo) {
		ResultData result = new ResultData();
		Map attr = inInfo.getAttr();
		String mobile =  StringUtil.toString(attr.get("mobile"));
		System.out.println("获取参数:"+mobile);
		EiInfo out = new EiInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		//查找当前员工详细的人员信息
		List list =  dao.query("PSPCWeChat.queryUserInfo2",mobile);
		//查找当前员工的角色信息
		List<HashMap<String,String>> roleInfo =  dao.query("PSPCWeChat.queryUserRole",mobile);
		if (list.isEmpty()){
			out.addMsg("404");
			return out;
		}else {
			result.setObj(list);

			out.set("obj",list);
			out.set("roleInfo",roleInfo);

			System.out.println(list);
			return out;
		}

	}

	/**
	 *
	 * 用户登录第一次绑卡后，获取当前用户的openid保存到我们的数据库中
	 *
	 * @Title: saveOpenId
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: keke
	 * @date: 2022年9月1日 下午1:46:30
	 */
	public EiInfo saveOpenId(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 卡片信息
		String openId = StringUtil.toString(attr.get("openId"));
		String name = StringUtil.toString(attr.get("name"));
		String phone = StringUtil.toString(attr.get("phone"));
		String cardNum = StringUtil.toString(attr.get("cardNum"));
		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);
		paramMap.put("name", name);
		paramMap.put("phone", phone);
		paramMap.put("cardNum", cardNum);

		//查询本地信息
		 dao.insert("PSPCTmealPatientCard.insertOpenId", paramMap);
		inInfo.set("obj", openId);
		return inInfo;
	}

	/**
	 *
	 * 获取当前用户的openid保存到我们的数据库中
	 *
	 * @Title: queryOpenId
	 * @param inInfo
	 * @return
	 * @return: EiInfo
	 * @author: keke
	 * @date: 2022年9月1日 下午1:46:30
	 */
	public EiInfo queryOpenId(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		// 卡片信息
		String openId = StringUtil.toString(attr.get("openId"));

		// 构建查询条件
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);


		//查询本地信息
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = dao.query("PSPCTmealPatientCard.queryOpenId", openId);

		inInfo.set("obj", list);
		System.out.println("list:"+list);
		return inInfo;
	}



}
