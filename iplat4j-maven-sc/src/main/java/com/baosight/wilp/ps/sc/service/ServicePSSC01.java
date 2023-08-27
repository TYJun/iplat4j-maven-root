package com.baosight.wilp.ps.sc.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.ResultData;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 
 * 登录接口
 * 
 * @Title: AppLoginService.java
 * @ClassName: AppLoginService
 * @Package：com.baosight.wilp.ps.sc.service
 * @author: liutao
 * @date: 2021年9月9日 下午1:59:32
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSSC01 extends ServiceBase {

	protected static final Logger log = LoggerFactory.getLogger(ServicePSSC01.class);

    /**
     * 
     * 病员登录
     *
     * @Title: patientLogin 
     * @param request
     * @param response
     * @return 
     * @return: ResultData 
     * @author: liutao
     * @date: 2021年9月9日 下午1:59:46
     */
	public ResultData patientLogin(HttpServletRequest request, HttpServletResponse response){
		ResultData resultData = new ResultData();
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String dataGroupCode = request.getParameter("dataGroupCode");
		
		HashMap<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName",userName);
		paramMap.put("passWord",passWord);
		paramMap.put("dataGroupCode",dataGroupCode);
		try {
			EiInfo call = LocalServiceUtil.callNoTx("PSPCUser", "patientLogin", paramMap);
			resultData = (ResultData) call.getAttr().get("result");
			//调用结果
			if(call.getStatus() < 0){
				System.out.println("调用PSPCUser.patientLogin失败！");
			}else {
				resultData.setRespCode("200");
				resultData.setRespMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRespCode("201");
			resultData.setRespMsg("程序异常");
			resultData.setSuccess(false);
		}
		System.out.println(resultData);
		return resultData;
	}
	

	/**
	 * 
	 * 职工企业微信登录
	 *
	 * @Title:
	 * @param
	 * @param
	 * @return
	 * @return:
	 * @author: keke
	 * @date:
	 */
	@CrossOrigin
    public EiInfo login(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData result = new ResultData();
		String mobile = StringUtil.toString(attr.get("mobile"));
		String userId = StringUtil.toString(attr.get("userId"));
        EiInfo info = new EiInfo();
        info.set("mobile", mobile);
		info.set("userId", userId);
        info.set(EiConstant.serviceName, "PSPCUser");
        info.set(EiConstant.methodName, "popLogin");
        EiInfo infoLogin =XLocalManager.call(info);
//		String workNo=infoLogin.getWorkNo();
        if(infoLogin.getStatus() < 0) {
			inInfo.setStatus(-1);
        	result.setRespCode("201");
        	result.setRespMsg("用户信息查询失败");
			return inInfo;
        }else {
					Map<String, String> map=(Map<String, String>)infoLogin.get("map");
					System.out.println("9990");
					System.out.println(map);
			        inInfo.set("obj", map);
					result.setRespCode("200");
					result.setRespMsg("登录成功");
					result.setObj(map);
        }
        return inInfo;
    }

	/**
	 *
	 * 职工企业微信代开发应用的免登录
	 *
	 * @Title:
	 * @param
	 * @param
	 * @return
	 * @return:
	 * @author: keke
	 * @date:
	 */
	@CrossOrigin
	public EiInfo wecahtLogin(EiInfo inInfo){
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		ResultData result = new ResultData();
		String mobile = StringUtil.toString(attr.get("mobile"));
		String userId = StringUtil.toString(attr.get("userId"));
		EiInfo info = new EiInfo();
		info.set("mobile", mobile);
		info.set("userId", userId);
		info.set(EiConstant.serviceName, "PSPCUser");
		info.set(EiConstant.methodName, "wecahtLogin");
		EiInfo infoLogin =XLocalManager.call(info);
//		String workNo=infoLogin.getWorkNo();
		if(infoLogin.getStatus() < 0) {
			inInfo.setStatus(-1);
			result.setRespCode("201");
			result.setRespMsg("用户信息查询失败");
			return inInfo;
		}else {
			result.setRespCode("200");
			result.setRespMsg("用户信息查询成功");

		}
		return inInfo;
	}

}
