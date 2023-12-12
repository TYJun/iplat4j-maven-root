package com.baosight.wilp.df.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.df.jk.util.LocalServiceUtil;
import com.baosight.wilp.df.jk.util.ResultData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ServiceDFJK01 extends ServiceBase {

    /**
     *
     *  查询特种设备下次检查时间
     *
     * @Title: queryDf
     * @param request
     * @param response
     * @return
     * @return: ResultData
     * @author: keke
     * @date: 2022年10月2日 下午1:55:12
     */
    public ResultData queryDf(HttpServletRequest request, HttpServletResponse response){
        ResultData resultData = new ResultData();
        //地点id
        String id = request.getParameter("id");
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id",id);

        try {
            EiInfo call = LocalServiceUtil.callNoTx("DFJK02", "queryDf", paramMap);
            //调用结果
            if(call.getStatus() < 0){
                System.out.println("调用ServiceDFJK02.queryDf失败！");
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

    /**
     *
     *  查询特种设备里所有的图片和文件
     *
     * @Title: queryDfPic
     * @param request
     * @param response
     * @return
     * @return: ResultData
     * @author: keke
     * @date: 2022年10月2日 下午1:55:12
     */
    public ResultData queryDfPic(HttpServletRequest request, HttpServletResponse response){
        ResultData resultData = new ResultData();
        //地点id
        String id = request.getParameter("id");
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id",id);

        try {
            EiInfo call = LocalServiceUtil.callNoTx("DFJK02", "queryDfPic", paramMap);
            //调用结果
            if(call.getStatus() < 0){
                System.out.println("调用ServiceDFJK02.queryDfPic失败！");
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
