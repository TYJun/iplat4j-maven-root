package com.baosight.wilp.lc.lg.service;


import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.log.LogUtils;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.service.util.MethodParamConstants;
import com.baosight.iplat4j.ep.lg.domain.EPLG01;
import com.baosight.iplat4j.ep.lg.domain.EPLG0101;
import com.baosight.iplat4j.ep.lg.domain.EPLG02;
import com.baosight.iplat4j.ep.lg.util.LogDbUtils;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;

import net.sf.json.JSONObject;

public class ServiceLCLG01 extends ServiceBase {
	
    public static final String POPUP_INQU_BLOCK_ID = "popup_inqu_status";
    public static final String POPUP_RESULT_BLOCK_ID = "popup_result";
    private static String logAuthenStr = PlatApplicationContext.getProperty("iplat.ep.log.login");
    private static String logFormStr = PlatApplicationContext.getProperty("iplat.ep.log.form");
    private static String logIpStr = PlatApplicationContext.getProperty("iplat.ep.log.ip");

    public ServiceLCLG01() {
    }

    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = super.initLoad(inInfo, new EPLG01());
        outInfo.addBlock("popup_result").setBlockMeta((new EPLG0101()).eiMetadata);
        List classList = new ArrayList();
        Map authClassMap = new HashMap();
        authClassMap.put("operationClassEname", "auth");
        authClassMap.put("operationClassCname", "登录登出认证");
        classList.add(authClassMap);
        Map formClassMap = new HashMap();
        formClassMap.put("operationClassEname", "form");
        formClassMap.put("operationClassCname", "页面按钮分发");
        classList.add(formClassMap);
        outInfo.addBlock("result_class").addRows(classList);
        outInfo.addBlock("result_log").addRows(new ArrayList());
        return outInfo;
    }

    public EiInfo insertLogInfoToDb(EiInfo inInfo) {
        String fileName = LogUtils.getElkFilePath();
        File file = new File(fileName);
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String str = null;

            while((str = bufferedReader.readLine()) != null) {
                JSONObject fromObject = JSONObject.fromObject(str);
                LogDbUtils.insertLog(fromObject, this.dao);
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }

        }

        return inInfo;
    }

    public EiInfo insertLog(EiInfo eiInfo) {
        Map jsonObject = (Map)eiInfo.get("logObject");

        try {
            LogDbUtils.insertLog(jsonObject, this.dao);
        } catch (Exception var4) {
            eiInfo.setMsg(var4.getMessage());
            eiInfo.setStatus(-1);
        }

        return eiInfo;
    }

    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        String itemEnameKey = inInfo.getCell("inqu_status", 0, "operationClass").toString();
        if (itemEnameKey.equals("auth")) {
            outInfo = super.query(inInfo, "LCLG01.query", new EPLG01());
        } else if (itemEnameKey.equals("form")) {
            outInfo = super.query(inInfo, "LCLG02.query", new EPLG02());
        }

        return outInfo;
    }

    public EiInfo queryUser(EiInfo inInfo) {
        inInfo.setMethodParam(MethodParamConstants.queryBlock, "popup_inqu_status");
        inInfo.setMethodParam(MethodParamConstants.inDataBlock, "popup_result");
        inInfo.setMethodParam(MethodParamConstants.outDataBlock, "popup_result");
        inInfo.setMethodParam(MethodParamConstants.sqlName, "LCLG01.queryUser");
        inInfo.setMethodParam(MethodParamConstants.daoEPBaseBean, new EPLG0101());
        EiInfo outInfo = super.query(inInfo, true);
        return outInfo;
    }

    public EiInfo queryOperationList(EiInfo inInfo) {
        String operationClass = inInfo.getString("inqu_status-0-operationClass");
        EiInfo info = new EiInfo();
        String serviceId = "S_ED_02";
        String codeset = "iplat.ep.logsType";
        info.set("codeset", codeset);
        String condition = "";
        if ("auth".equals(operationClass)) {
            condition = "ITEM_CODE IN ('0300','0301')";
        } else if ("form".equals(operationClass)) {
            condition = "ITEM_CODE IN ('0100','0400')";
        } else {
            condition = "";
        }

        info.set("condition", condition);
        info.set(EiConstant.serviceId, serviceId);
        EiInfo outInfo = XServiceManager.call(info);
        List logTypeListByCondition = (List)outInfo.get("list");
        outInfo.addBlock("result_log").addRows(logTypeListByCondition);
        return outInfo;
    }
    
}
