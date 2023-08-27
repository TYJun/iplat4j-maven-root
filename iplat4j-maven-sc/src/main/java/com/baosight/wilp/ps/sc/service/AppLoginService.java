package com.baosight.wilp.ps.sc.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
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
public class AppLoginService {

	protected static final Logger log = LoggerFactory.getLogger(AppLoginService.class);

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
	 * 职工登录
	 *
	 * @Title: login 
	 * @param request
	 * @param response
	 * @return 
	 * @return: ResultData 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:59:58
	 */
	@CrossOrigin
    public ResultData login(HttpServletRequest request, HttpServletResponse response){
    	ResultData result = new ResultData();
        EiInfo eiInfo = new EiInfo();
        eiInfo.set(EiConstant.serviceId,"S_XS_08");
        String userName=request.getParameter("userName");
        String passWord=request.getParameter("passWord");
        String cid=request.getParameter("cid");
        eiInfo.set("loginName",userName);
        eiInfo.set("password",passWord);
        EiInfo outInfo = XServiceManager.call(eiInfo);
        int status=outInfo.getStatus();
        String msg=outInfo.getMsg();
        if(status!=1) {
            result.setRespCode("199");
            result.setRespMsg(msg);
            result.setParam("null");
            return result;
        }
        EiInfo info = new EiInfo();
        info.set("userName", userName);
        info.set(EiConstant.serviceName, "PSPCUser");
        info.set(EiConstant.methodName, "login");
        EiInfo infoLogin =XLocalManager.call(info);
        if(infoLogin.getStatus() < 0) {
        	result.setRespCode("201");
        	result.setRespMsg("用户信息查询失败");
        }else {
			//登录成功后，更新cid
			//判断登录是否传过来有效的cid参数
			if(!StringUtil.isBlank(cid)){
				if (!StringUtil.isBlank(userName)) {
					try {
						//cid不为空时，调用微服务接口ACFW01.updateCid更新用户的cid
						info.set("cid", cid);
						info.set("workNo", userName);
						System.out.println("ACFW01-updateCid参数："+info.getAttr());
						info.set(EiConstant.serviceName, "ACFW01");
						info.set(EiConstant.methodName, "updateCid");
						info = XLocalManager.call(info);
					} catch (PlatException ex) {
						System.out.println("服务ACFW01-updateCid调用失败:" + ex.getMessage() + ",错误原因:" + ex.getCause());
						info.setStatus(EiConstant.STATUS_FAILURE);
						log.error(ex.getMessage(), ex);
					}
				}else {
					throw new PlatException("传入参数json中未获取到userName");
				}
			}
        	Map<String, String> map=(Map<String, String>)infoLogin.get("map");
        	result.setRespCode("200");
        	result.setRespMsg("登录成功");
        	result.setObj(map);
        }
        return result;
    }
}
