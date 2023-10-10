package com.baosight.wilp.mc.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.wilp.mc.jk.utils.LocalServiceUtil;
import com.baosight.wilp.mc.jk.utils.ResultData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InfaceBaseInfoService {
    /**
     *
     * 查找该病患是否为住院病人、获取床位号、病区
     *
     * @Title: checkCardInfo
     * @param request
     * @param response
     * @return
     * @return: ResultData
     * @author: keke
     * @date: 2022年8月17日 下午1:54:46
     */
    public ResultData updatePop(HttpServletRequest request, HttpServletResponse response){
        ResultData resultData = new ResultData();

        try {
            EiInfo call = LocalServiceUtil.callNoTx("MCJK02", "updatePop", null);
            //调用结果
            if(call.getStatus() < 0){
                System.out.println("调用MCJK02.updatePop！");
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
