package com.baosight.wilp.ps.pc.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.RespResult;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;


/**
 * 
 * 变更订单状态
 *   更改业务表状态 statusChange()
 *   校验参数 checkParam()
 * 
 * @Title: ServicePSPCStatusChange.java
 * @ClassName: ServicePSPCStatusChange
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liutao
 * @date: 2021年9月9日 下午1:41:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePSPCStatusChange extends ServiceBase {


    /**
     * 
     * 1、更改业务表状态，业务需要自己判断当前处理人是否有权限处理订单
     * 2、增加操作历史
     * 
     * <p>更改状态条件：where status_code=beforeStatus,
     *
     * @Title: statusChange 
     * @param inInfo
     * @return 
     * @return: EiInfo 
     * @author: liutao
     * @date: 2021年9月9日 下午1:42:08
     */
	public EiInfo statusChange(EiInfo inInfo) {
		HashMap<String,Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
		PSPCBillStatusInfo billInfo = (PSPCBillStatusInfo) attr.get("billInfo");
		EiInfo outInfo = new EiInfo();
		RespResult result = new RespResult();
		try {
			System.out.println("--------更改订单状态---------");
			
			result = checkParam(billInfo);
			if("201".equals(result.getRespCode())){
				outInfo.set("result", result);
				outInfo.setStatus(-1);
				return outInfo;
			}
			
			/**
			 * 1、更新业务表
			 */
			HashMap<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sql","PSPCStatusChange.updatePboTable");
			paramMap.put("domain",billInfo);
			EiInfo callUpdatePboTable = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
			boolean flag = Boolean.parseBoolean(StringUtil.toString(callUpdatePboTable.get("success")));
			
			if(!flag){
				result.setRespCode("201");
				result.setRespMsg("更新业务表失败！");
				outInfo.set("result", result);
				outInfo.setStatus(-1);
				return outInfo;
			}
			/**
			 * 2、增加记录
			 */
			paramMap.put("sql","PSPCStatusChange.savePboHistory");
			paramMap.put("domain",billInfo);
			EiInfo callSavePboHistory = LocalServiceUtil.call("SSBMTy", "insertSqlByDomain", paramMap);
			flag = Boolean.parseBoolean(StringUtil.toString(callSavePboHistory.get("success")));
			if(!flag){
				result.setRespCode("201");
				result.setRespMsg("新增历史记录表失败！");
				outInfo.set("result", result);
				outInfo.setStatus(-1);
				return outInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	outInfo.set("result", result);
        return outInfo;
	}
	
	/**
	 * 
     * 校验参数
	 *
	 * @Title: checkParam 
	 * @param billInfo
	 * @return 
	 * @return: RespResult 
	 * @author: liutao
	 * @date: 2021年9月9日 下午1:42:28
	 */
    private RespResult checkParam(PSPCBillStatusInfo billInfo) {

        RespResult result = new RespResult("200","校验通过！");

        if (billInfo == null) {
            result.setRespCode("201");
            result.setRespMsg("billInfo参数为空！");
            return result;
        }

        if (StringUtils.isEmpty(billInfo.getPboTbName())) {
            result.setRespCode("201");
            result.setRespMsg("业务表pboTbName为空！");
            return result;
        }

        if (StringUtils.isEmpty(billInfo.getBillId())) {
            result.setRespCode("201");
            result.setRespMsg("业务billId为空！");
            return result;
        }

        if (StringUtils.isEmpty(billInfo.getCurrentDealer()) && !billInfo.isEndTask()) {
            result.setRespCode("201");
            result.setRespMsg("下一流程处理人currentDealer为空！");
            return result;
        }
        
        if (StringUtils.isEmpty(billInfo.getBeforeStatus())) {
            result.setRespCode("201");
            result.setRespMsg("业务单据当前状态为空！");
            return result;
        }
        if (StringUtils.isEmpty(billInfo.getAfterStatus())) {
            result.setRespCode("201");
            result.setRespMsg("业务单据更新后状态为空！");
            return result;
        }
        if (StringUtils.isEmpty(billInfo.getCreatorId())) {
            result.setRespCode("201");
            result.setRespMsg("操作人为空！");
            return result;
        }
        if (StringUtils.isEmpty(billInfo.getCreatorName())) {
            result.setRespCode("201");
            result.setRespMsg("操作人为空！");
            return result;
        }
        if (StringUtils.isEmpty(billInfo.getOprationRoute())) {
            result.setRespCode("201");
            result.setRespMsg("操作路由为空！");
            return result;
        }
        
        return result;

    }	
}
