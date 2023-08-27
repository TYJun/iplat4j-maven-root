package com.baosight.wilp.im.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.jk.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceIMJK01 extends ServiceBase {

    /**
     *
     *  查询手持机登录人信息所属角色
     *
     * @Title: queryUserRole
     * @param request
     * @param response
     * @return
     * @return: ResultData
     * @author: keke
     * @date: 2022年10月2日 下午1:55:12
     */
    public ResultData queryUserRole(HttpServletRequest request, HttpServletResponse response){
        ResultData resultData = new ResultData();
        //时间
        String createTime = request.getParameter("createTime");
        //地点id
        String spotId = request.getParameter("spotId");
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("createTime",createTime);
        paramMap.put("spotId",spotId);

        try {
            EiInfo call = LocalServiceUtil.callNoTx("IMJK02", "queryUserRole", paramMap);
            //调用结果
            if(call.getStatus() < 0){
                System.out.println("调用ServiceIMJK02.queryUserRole失败！");
                resultData.setRespCode("201");
                resultData.setRespMsg("操作失败");
                resultData.setSuccess(false);
            }else {
                resultData.setObj(call.getAttr().get("obj"));
                resultData.setRespCode("200");
                resultData.setRespMsg("操作成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setRespCode("201");
            resultData.setRespMsg("程序异常");
            resultData.setSuccess(false);
        }
        return resultData;
    }

}
