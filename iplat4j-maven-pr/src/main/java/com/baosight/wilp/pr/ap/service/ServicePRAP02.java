package com.baosight.wilp.pr.ap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.pr.TimeUtil;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.pr.ap.domain.Pic;
import com.baosight.wilp.pr.ap.domain.ResultData;
import com.baosight.wilp.pr.ap.domain.ResultPic;
import com.baosight.wilp.pr.ap.domain.SaftyInformation;
import com.baosight.wilp.pr.common.TyepCode;


/**
 * 
 * 主页数据统计，安全问题数据统计，问题台账，查询问题大类，问题台账详情
 * <p>1.主页数据统计queryDangerAmount
 * <p>2.安全问题数据统计queryDangerList
 * <p>3.问题台账quertSaftyLedger
 * <p>4.查询问题大类querySafty
 * <p>5.问题台账详情queryDangerDetial
 * 
 * 
 * @Title: ServicePRAP02.java
 * @ClassName: ServicePRAP02
 * @Package：com.baosight.zdyyaq.pr.ap.service
 * @author: zhangjiaqian
 * @date: 2021年5月8日 下午4:50:59
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePRAP02 extends ServiceBase{


    /**
     * 注入dao
     */
    private static Dao dao = (Dao) PlatApplicationContext.getBean("dao");



    /**
     *
     * 主页数据统计
     * flowStatusCode:问题状态
     * 代码逻辑：
     * 1.获取问题状态
     * 2.参数非空校验
     * 3.查询数据库
     * 4.获取参数并返回
     * @param inInfo
     * @return: inInfo
     */
    public EiInfo queryDangerAmount(EiInfo inInfo) {
        //1.获取问题状态
        String flowStatusCode = (String)inInfo.get("flowStatusCode");
        //2.参数非空校验
        if(null == flowStatusCode) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("系统异常请联系管理员");
            return inInfo;
        }
        //3.查询数据库
        List<HashMap> param = dao.query("PRAP02.querySaftyCount", flowStatusCode);
        //4.获取参数并返回
        HashMap hashMap = param.get(0);
        inInfo.setMsgKey("200");
        inInfo.setMsg("查询成功");
        inInfo.set("param", hashMap.get("amount"));
        return inInfo;
    }





    /**
     * 
     * 安全问题数据统计
     * flowStatusCode:问题状态
     * workNo:用户工号
     * 代码逻辑：
     * 1.获取问题状态
     * 2.获取工号
     * 3.参数非空校验
     * 4.查询数据库
     * 5.封装参数并返回
     * @Title: queryDangerList 
     * @return: inInfo
     */
    public EiInfo queryDangerList(EiInfo inInfo) {
        //获取问题状态
        String flowStatusCode = (String)inInfo.get("flowStatusCode");
        //获取工号
        String workNo = (String)inInfo.get("workNo");
        //参数非空校验
        if(null == flowStatusCode || null == workNo) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("系统异常请联系管理员");
            return inInfo;
        }
        Map<String,String> param = new HashMap<String,String>();
        param.put("flowStatusCode", flowStatusCode);
        param.put("workNo", workNo);
        //查询数据库
        List<HashMap> query = dao.query("PRAP02.querySaftyWork", param);
        //封装参数并返回
        inInfo.setMsgKey("200");
        inInfo.setMsg("查询成功");
        inInfo.set("param", query);
        return inInfo;
    }


    /**
     * 
     * 问题台账
     * timeParam:时间范围参数
     * localTypeName:问题类型
     * flowStatusCode:问题状态
     * row:获取行数
     * page:获取页数
     * 代码逻辑:
     * 1.获取参数
     * 2.将时间范围，转换成标准时间格式，放入数据库中查询
     * 3.查询数据库
     * 4.将参数返回
     *
     * @Title: quertSaftyLedger 
     * @return: inInfo
     */
    public EiInfo quertSaftyLedger(EiInfo inInfo) {
        //1.获取参数
        //获取时间范围
        String timeParam = (String)inInfo.get("timeParam");
        //获取问题类型
        String localTypeName = (String)inInfo.get("localTypeName");
        //获取问题状态
        String flowStatusCode = (String)inInfo.get("flowStatusCode");
        //获取行数
        Object row = inInfo.get("row");
        //获取页数
        Object page = inInfo.get("page");
        Map param = new HashMap();;
        if(!localTypeName.equals("全部")){
            param.put("localTypeName", localTypeName);
        }
        param.put("flowStatusCode", flowStatusCode);
        param.put("row", row);
        param.put("page", page);
        //2.将时间范围，转换成标准时间格式，放入数据库中查询
        Map<String,String> paramTime = new HashMap();
        if(null != timeParam && timeParam.length()>0) {
            paramTime = TimeUtil.historyTime(timeParam);
            param.put("beginDate", paramTime.get("beginDate"));
            param.put("endDate", paramTime.get("endDate"));
        }
        //3.查询数据库
        List<Map> query = dao.query("PRAP02.saftyLedger", param);
        //4.将参数返回
        inInfo.setMsgKey("200");
        inInfo.setMsg("查询成功");
        inInfo.set("param", query);
        return inInfo;
    }


    /**
     * 
     * 查询问题大类
     * WILP.im.saftyType:安全问题大类小代码配置编码
     * 代码逻辑:
     * 1.查询小代码数据
     * 2.将查询的参数返回
     * @Title: querySafty 
     * @return: inInfo
     */
    public EiInfo querySafty(EiInfo inInfo) {
        List<Map<String, String>> query = new ArrayList<>();
        try {
            //查询小代码数据
            query = (List<Map<String, String>>) TyepCode.dealUseDay("WILP.im.saftyType");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("value","");
        map.put("label","全部");
        query.add(map);
        //将查询的参数返回
        inInfo.setMsgKey("200");
        inInfo.setMsg("查询成功");
        inInfo.set("param", query);
        return inInfo;
    }


    /**
     * 
     * 问题台账详情
     * dangerID:问题id
     * 代码逻辑:
     * 1.获取问题id
     * 2.查询整改信息
     * 3.查询挂账原因
     * 4.查询驳回原因
     * 5.查询整改前图片信息
     * 6.查询整改后图片信息
     * 7.添加整改前图片base64编码
     * 8.添加整改后图片base64编码
     * 9.构建返回参数
     * 10.封装参数
     * 11.将参数返回
     *
     * @Title: queryDangerDetial 
     * @return: inInfo
     */
    public EiInfo queryDangerDetial(EiInfo inInfo) {
        //1.获取问题id
        String dangerID = (String)inInfo.get("dangerID");
        if(null == dangerID) {
            inInfo.setMsgKey("199");
            inInfo.setMsg("系统错误请联系管理员");
            return inInfo;
        }
        //2.查询整改信息
        List<SaftyInformation> query = dao.query("PRAP02.querySaftyDetail", dangerID);

        //3.查询挂账原因
        List<SaftyInformation> query1 = dao.query("PRAP02.querySaftyLog", dangerID);
        
        //4.查询驳回原因
        List<SaftyInformation> query12 = dao.query("PRAP02.querySaftyLog2", dangerID);

        //5.查询整改前图片信息
        List<Pic> query2 = dao.query("PRAP02.queryPic", dangerID);

        //6.查询整改后图片信息
        List<ResultPic> query3 = dao.query("PRAP02.queryResultPic", dangerID);

        //7.添加整改前图片base64编码
        query2.forEach(pic ->{
            pic.setBase64(CommonUtils.imageToBase64Str(pic.getStoragePath()));
        });
        //8.添加整改后图片base64编码
        query3.forEach(resultpic ->{
            resultpic.setBase64(CommonUtils.imageToBase64Str(resultpic.getStoragePath()));
        });

        SaftyInformation log = null;
        String rejectReason = null;
        String rejectReason2 = null;
        //9.构建返回参数
        if(query1.size()>0) {
            log = query1.get(0);
            //返回挂账信息
            rejectReason = log.getRejectReason();
        }
        if(query12.size()>0) {
            log = query12.get(0);
            //返回驳回原因信息
            rejectReason2 = log.getRejectReason2();
        }
        //10.封装参数
        SaftyInformation saftyInformation = query.get(0);
        saftyInformation.setPic(query2);
        saftyInformation.setResultPic(query3);
        saftyInformation.setRejectReason(rejectReason);
        saftyInformation.setRejectReason2(rejectReason2);
        //11.将参数返回
        inInfo.setMsgKey("200");
        inInfo.setMsg("查询成功");
        inInfo.set("param", saftyInformation);
        return inInfo;
    }
}
