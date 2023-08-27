package com.baosight.wilp.ps.pc.service;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ps.pc.domain.PSPCTmealOrderBillDetail;
import com.baosight.wilp.ss.ac.commen.CanteenTimesEntity;
import com.baosight.wilp.ss.ac.commen.CommTimes;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.RespResult;
import com.baosight.wilp.ss.bm.utils.SqlUtils;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.hessian.AliPaySender;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (病员订餐定时任务)
 *
 * @Title: ServicePSPCTimer01
 * @ClassName: ServicePSPCTimer01
 * @Package：com.baosight.wilp.ps.pc.service
 * @author: liu
 * @date: 2022-03-30 14:59
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History: // 历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version> // 版本
 * <desc>   // 描述修改内容
 */
public class ServicePSPCTimer01 extends ServiceBase {

    private static final Logger logger = LoggerFactory.getLogger(ServicePSPCTimer01.class);



    /**
     * Todo(执行订单操作)
     * 1.判断更新时间是否为null，为null的时候才会进行关闭
     * 2.查询支付节点获取支付结果，为false表示该订单支付失败,执行关闭操作
     * 3.支付结果为true表示该订单已支付成功,更新订单状态(已支付:00–>已确认:02)
     * 4.关闭超过15分钟的订单
     * 5.关闭达到截止时间的订单
     * 6.返还菜品数量
     * 7.核对主表确认订单状态不为00且更新状态不为null，表示已支付或已作废，删除中间表
     * @Title: billHandle
     * @author xiajunqing
     * @date: 2022/2/15 10:56
     * @Param inInfo
     * @return: EiInfo
     * respCode：200正常订单，201关闭超时订单，202关闭截止订单，206支付订单,无需重复操作
     */
    public EiInfo billHandle(EiInfo inInfo) {
        HashMap<String,Object> hashMap = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");
        if(hashMap == null || hashMap.isEmpty()) {
            //iplat 服务传参直接获取
            hashMap =  (HashMap<String, Object>) inInfo.getAttr();
        }
        String msg = "";
        String respCode = "200";
        String billNo = StringUtil.toString(hashMap.get("billNo"));
        String billId = StringUtil.toString(hashMap.get("billId"));
        String payNo = StringUtil.toString(hashMap.get("payNo"));
        String canteenNum = StringUtil.toString(hashMap.get("canteenNum"));
        try {
            /**1.判断更新时间是否为null，为null的时候才会进行关闭*/
            if(!hashMap.isEmpty() && "00".equals(hashMap.get("statusCode")) && StringUtil.isBlank((String) hashMap.get("reviseTime"))) {
                msg = "从支付节点获取支付结果";
                /**2.查询支付节点获取支付结果，为false表示该订单支付失败,执行关闭操作*/
                String payType = StringUtil.toString(hashMap.get("payType"));
                //获取支付结果
                boolean flag = getPayStatus(payType,payNo,canteenNum);
                System.out.println(payType+"订单"+billId+"支付结果flag："+flag);
                //设置statusChange信息
                PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
                billInfo.setPboTbName("sc_order_bill_patient");
                billInfo.setBeforeStatus("00");
                billInfo.setAfterStatus("99");
                billInfo.setCurrentDealer(StringUtil.toString(hashMap.get("currentDealer")));
                billInfo.setBillId(StringUtil.toString(hashMap.get("billId")));
                billInfo.setCreatorId(StringUtil.toString(hashMap.get("recCreator")));
                billInfo.setCreatorName(StringUtil.toString(hashMap.get("userName")));
                billInfo.setPboCode("PATIENT_MEAL");
                billInfo.setHandleAdvice("作废");
                billInfo.setIsReject("1");
                billInfo.setRejectCode("3");
                if(!flag) {
                    //flag为false表示该订单支付失败,执行关闭操作
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String sdate = hashMap.get("recCreateTime").toString();
                    Date start = sdf.parse(sdate);
                    Date end = new Date();
                    //判断当前时间与下单时间的差值
                    long between = (end.getTime() - start.getTime())/1000;
                    long min = between/60;
                    //获取超时timeout配置
                    HashMap<String,Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("mealgroupTypeCode", "billTimeOut");
                    List<HashMap<String,Object>> listHosArea = dao.query("SSBMStxx01.getMealTypeData", paramMap);
                    HashMap<String, Object> typeGroup = new HashMap<String, Object>();
                    if(listHosArea.size() > 0){
                        typeGroup = listHosArea.get(0);
                    }
                    //订餐配置表中没有配置则使用默认值15分钟
                    Integer timeout = typeGroup.isEmpty() ? 15 : Integer.parseInt(StringUtil.toString(typeGroup.get("typeCode")));
                    System.out.println("当前时间："+sdf.format(end)+"，timeout："+timeout+",min:"+min);
                    if(min >= timeout) {
                        /**4.关闭超过15分钟的订单*/
                        msg = "关闭超时订单";
                        billInfo.setOprationRoute("订单超时未支付");
                        billInfo.setRejectReason("订单超时未支付");
                        //关闭达到15分钟的超时订单
                        paramMap.put("billInfo", billInfo);
                        EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
                        RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
                        logger.info(billNo + msg);
                        System.out.println(StringUtil.separator+"关闭超时订单:"+billStatusChangeResult.getRespCode()+billStatusChangeResult.getRespMsg());
                        //查询订单明细
                        paramMap.put("sql","PSPCTmealOrderBillDetail.queryBillDetailByBillNo");
                        paramMap.put("str",billNo);
                        EiInfo callQueryDetail = LocalServiceUtil.call("SSBMTy", "querySqlForListByString", paramMap);
                        List<PSPCTmealOrderBillDetail> orderMenuList = (List<PSPCTmealOrderBillDetail>)callQueryDetail.get("result");

                        /**6.返还菜品数量*/
                        updateMenuNumCancel(hashMap,orderMenuList);
                        //从中间表移除记录
                        if("200".equals(billStatusChangeResult.getRespCode())){
                            dao.delete("PSPCTmealOrderMiddle.deleteByBillId",billId);
                            logger.info("移除中间表记录：" + billNo);
                        }
                        respCode = "201";
                        inInfo.setMsgKey(respCode);
                        inInfo.setMsg(msg);
                        return inInfo;
                    }/*else{
                        msg = "关闭截止订单";
                        *//**5.关闭达到截止时间的订单*//*
                        String needDate = StringUtil.toString(hashMap.get("needDate"));
                        String mealNum = StringUtil.toString(hashMap.get("mealNum"));
                        //加载食堂时间配置
                        List<CanteenTimesEntity> listTimes = dao.query("SSBMDcsj01.getCanteenTimesByCode",new HashMap<>());
                        CommTimes.setCanteenTimes(listTimes);
                        //校验餐次截止订餐时间
                        ResponseResult result = CommTimes.checkOrderTimes(canteenNum, mealNum, needDate);
                        String archiveFlag = StringUtil.toString(hashMap.get("archiveFlag"));
                        System.out.println("订单archiveFlag:"+archiveFlag);
                        //pos机不校验截止时间
                        if("201".equals(result.getRespCode()) && !"POS".equals(archiveFlag)) {
                            //判断订单餐次是否超过截止时间，超过则关闭订单
                            billInfo.setOprationRoute("订单达到截止支付时间");
                            billInfo.setRejectReason("订单达到截止支付时间");
                            //校验失败 关闭达到截止时间还未支付的订单
                            paramMap.put("billInfo", billInfo);
                            EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
                            RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
                            System.out.println("关闭达到截止时间还未支付的订单：" + billNo + billStatusChangeResult.getRespMsg());
                            logger.info(billNo + msg);
                            //查询订单明细
                            paramMap.put("sql","PSPCTmealOrderBillDetail.queryBillDetailByBillNo");
                            paramMap.put("str",billNo);
                            EiInfo callQueryDetail = LocalServiceUtil.call("SSBMTy", "querySqlForListByString", paramMap);
                            List<PSPCTmealOrderBillDetail> orderMenuList = (List<PSPCTmealOrderBillDetail>)callQueryDetail.get("result");
                            *//**6.返还菜品数量*//*
                            updateMenuNumCancel(hashMap,orderMenuList);
                            //从中间表移除记录
                            if("200".equals(billStatusChangeResult.getRespCode())){
                                paramMap.put("sql","PSPCTmealOrderMiddle.deleteByBillId");
                                paramMap.put("str",billId);
                                EiInfo callInsertMiddle = LocalServiceUtil.call("SSBMTy", "deleteSqlByString", paramMap);
                                logger.info("移除中间表记录：" + billNo);
                            }
                            respCode = "202";
                            inInfo.setMsgKey(respCode);
                            inInfo.setMsg(msg);
                            return inInfo;
                        }else if("202".equals(result.getRespCode())) {
                            System.out.println("加载食堂时间配置信息为空！");
                        }
                    }*/
                } else {
                    /**3.支付结果为true表示该订单已支付成功,更新订单状态(已支付:00–>已确认:02)*/
                    msg = "已支付订单,无需重复操作";
                    billInfo.setAfterStatus("02");
                    billInfo.setHandleAdvice("确认");
                    billInfo.setOprationRoute("支付成功确认状态");
                    billInfo.setIsReject("0");
                    HashMap<String,Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("billInfo", billInfo);
                    EiInfo callStatusChange = LocalServiceUtil.call("PSPCStatusChange", "statusChange", paramMap);
                    RespResult billStatusChangeResult = (RespResult) callStatusChange.get("result");
                    logger.info(billNo + msg);
                    System.out.println("支付成功确认订单：" + billNo + billStatusChangeResult.getRespMsg());
                    respCode = "206";
                    inInfo.setMsgKey(respCode);
                    inInfo.setMsg(msg);
                    return inInfo;
                }
            }else {
                /**7.核对主表确认订单状态不为00更新状态不为null，表示已支付或已作废，删除中间表*/
                msg = "删除失效的中间表数据" + billNo;
                //删除订单中间表记录
                HashMap<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("sql","PSPCTmealOrderMiddle.deleteByBillId");
                paramMap.put("str",billId);
                EiInfo callInsertMiddle = LocalServiceUtil.call("SSBMTy", "deleteSqlByString", paramMap);
                logger.info(msg);
            }
        } catch (Exception e) {
            inInfo.setStatus(-1);
            e.printStackTrace();
        }
        inInfo.setMsgKey(respCode);
        inInfo.setMsg(msg);
        return inInfo;
    }


    /**
     * 获取支付节点支付是否成功
     * @param payType 支付类型
     * @param alipayNo 支付编码
     * @param canteenNum 食堂编码
     * @return boolean
     * */
    public boolean getPayStatus(String payType,String alipayNo, String canteenNum) {
        boolean flag = false;
        String aliHospitalCode = MealCommonConfig.getSmpConfig().getHospitalCodeAli();
        String weChatHospitalCode = MealCommonConfig.getSmpConfig().getHospitalCodeWechat();

        String aliModulCode = "patient_ali";
        String wechatModulCode = "patient_wechat";
        System.out.println("支付宝医院编码："+aliHospitalCode);
        System.out.println("微信医院编码："+weChatHospitalCode);
        if("0101".equals(payType)) {
            //支付宝查询支付结果
            ResponseResult aliResult = AliPaySender.singleTradeQuery( aliHospitalCode,aliModulCode,alipayNo );

            if("200".equals(aliResult.getRespCode())) {
                flag = true;
            }
        }else if("0201".equals(payType)) {
            //微信查询支付结果
            BusinessInfoDTO dto = new BusinessInfoDTO();
            dto.setHospitalCode(weChatHospitalCode);
            dto.setModulCode(wechatModulCode);
            dto.setUsedUnitCode(canteenNum);
            ResultEntry weChatResult = WeChatPayImpl.OrderQuery(alipayNo, dto);
            if(weChatResult.getRespCode() == 200) {
                flag = true;
            }
        }else if("0301".equals(payType)){
            flag = false;
        }
        return flag;
    }

    /**
     *
     * 退单时返回菜品剩余数量
     *
     * @Title: updateMenuNumCancel
     * @param hashMap 参数
     * @param orderMenuList 订单明细集合
     * @return: void
     * @author: liutao
     * @date: 2021年9月9日 下午3:09:05
     */
    public void updateMenuNumCancel(HashMap<String, Object> hashMap, List<PSPCTmealOrderBillDetail> orderMenuList){
        //用餐时间 餐次 菜品编码 菜品数量 食堂
        String mealNum = StringUtil.toString(hashMap.get("mealNum"));
        String mealDate = StringUtil.toString(hashMap.get("needDate"));
        String canteenNum = StringUtil.toString(hashMap.get("canteenNum"));
        //构建更新参数
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("canteenNum", canteenNum);
        map.put("mealDate", mealDate);
        map.put("mealNum", mealNum);
        for (PSPCTmealOrderBillDetail orderMenu : orderMenuList) {
            String menuNum = orderMenu.getMenuNum();
            Integer num = orderMenu.getMenuNumber();
            map.put("menuNum", menuNum);
            map.put("num", num);
            try {
                HashMap<String,Object> paramMap = new HashMap<String, Object>();
                //更新菜品排班表
                paramMap.put("sql", "SSBMCpsl01.updateMenuScheSurplus");
                paramMap.put("map", map);
                System.out.println("订单菜品信息:"+paramMap);
                EiInfo call = LocalServiceUtil.call("SSBMTy", "updateSqlByMap", paramMap);
                System.out.println("取消订单菜品数量:"+Boolean.parseBoolean(StringUtil.toString(call.get("success"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Todo(定时任务扫描中间表，关闭超时未支付的订单)
     * 调用billHandle执行订单操作方法
     * @Title: closeOvertimeOrder
     * @author xiajunqing
     * @date: 2022/2/15 10:57
     * @Param
     * @return:
     */
    public EiInfo closeOvertimeOrder(EiInfo inInfo) {
        String msg = "开始执行closeOvertimeOrder";
        try {
            int count = SqlUtils.countByMap("PSPCTmealOrderMiddle.countMiddle",new HashMap<String,Object>());

            if(count < 1) {
                inInfo.setStatus(1);
                return inInfo;
            }
            HashMap<String,Object> map = new HashMap<String,Object>();
            List<HashMap<String,Object>> queryForList = SqlUtils.queryForListMap("PSPCTmealOrderMiddle.queryOrderQueueInfo",map);
            System.out.println("病患开始执行closeOvertimeOrder--------------------------");
            if(queryForList.size() > 0) {
                for (int i = 0; i < queryForList.size(); i++) {
                    HashMap<String, Object> hashMap = queryForList.get(i);
                    try {
                        EiInfo info = new EiInfo();
                        info.setAttr(hashMap);
                        EiInfo eiInfo = billHandle(info);
                        msg = eiInfo.getMsg();
                    } catch (Exception e) {
                        //发生异常时继续处理其它订单
                        logger.error(hashMap.get("billId")+msg + "：" + e.getMessage());
                        System.out.println(hashMap.get("billId")+msg + "：" + e.getMessage());
                        e.printStackTrace();
                    }
                }
                inInfo.setStatus(1);
            }
        } catch (Exception e) {
            inInfo.setStatus(-1);
            logger.error(msg + "：" + e.getMessage());
            e.printStackTrace();
        }
        return inInfo;
    }

    /**
     * 订单自动评价
     * 1.获取小代码自动评价天数配置 未配置自动评价天数 默认5天
     * 2.封装评价内容
     * 3.调用订单评价的方法
     * @Title  automaticEvaluation
     * @author liu
     * @date 2022-03-29 16:28
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo automaticEvaluation(EiInfo inInfo) {

        try {
            /**1.获取小代码自动评价天数配置*/
            EiInfo callCode = LocalServiceUtil.callCode1("S_ED_02", "wilp.sc.jc.autoEval", "patient");
            List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)callCode.get("list");

            HashMap<String, Object> patient = new HashMap<String, Object>();
            if(list.size() > 0){
                patient = list.get(0);
            }
            //未配置自动评价天数 默认5天
            Integer day = patient.isEmpty() ? 5 : Integer.parseInt(StringUtil.toString(patient.get("label")));

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("statusCode","03");
            paramMap.put("dayOut",day);
            List<HashMap<String, Object>> noEvalBillList = SqlUtils.queryForListMap("PSPCTmealOrderBillPatient.queryBillNotEval", paramMap);
            if(noEvalBillList.isEmpty()){
                inInfo.setStatus(1);
                inInfo.setMsg("没有待评价的订单");
                return inInfo;
            }

            String billNo ;
            /**2.封装评价内容,默认好评*/
            for (HashMap<String, Object> noEvalBill : noEvalBillList) {
                Map<String, Object> map = new HashMap<>();
                List<Map<String, Object>> orderEvalList = new ArrayList<>();
                Map<String, Object> orderEvalMap = new HashMap<>();
                orderEvalMap.put("menuNum",noEvalBill.get("menuNum"));
                orderEvalMap.put("evalLevel",10);
                orderEvalMap.put("evalContent","非常好");
                orderEvalList.add(orderEvalMap);

                billNo = (String) noEvalBill.get("billNo");

                map.put("sendDate",noEvalBill.get("reqSendTime"));
                map.put("billNo",billNo);
                map.put("evalLevel","10");
                map.put("evalContent","非常好");
                map.put("evalTypeName","服务质量");
                map.put("evalType","00");
                map.put("userCode","timeTask");
                map.put("canteenNum",noEvalBill.get("canteenNum"));
                map.put("billMenuList",orderEvalList);

                String json = JSON.toJSONString(map);

                /**3.调用订单评价的方法*/
                Map<String, Object> paramObject = new HashMap<>();
                paramObject.put("json",json);
                EiInfo call = LocalServiceUtil.call("PSPCDDGL01", "savePatientEval", paramObject);
                inInfo.setStatus(call.getStatus());
            }
        } catch (Exception e) {
            inInfo.setStatus(-1);
            e.printStackTrace();
        }
        return inInfo;
    }


    /**
     * Todo(定时任务每天晚上11：50把职工订单表里的数据抽取到历史记录表里)
     *
     * @Title: history
     * @author kwr
     * @date: 2022/8/8 10:57
     * @Param
     * @return:
     */
    public EiInfo billTimerHistory(EiInfo inInfo) {
        String msg = "开始执行billTimerHistory";
        try {
            int count = SqlUtils.countByMap("PSPCTmealOrderMiddle.queryWorkBill",new HashMap<String,Object>());
            if(count < 1) {
                System.out.println("执行失败！！");
                inInfo.setMsg("未查询到数据，执行出错");
                inInfo.setStatus(1);
                return inInfo;
            }
            HashMap<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sql","PSPCTmealOrderMiddle.billTimerHistory");
            EiInfo call = LocalServiceUtil.call("SSBMTy", "querySqlByMap", paramMap);
        } catch (Exception e) {
            inInfo.setStatus(-1);
            logger.error(msg + "：" + e.getMessage());
            e.printStackTrace();
        }
        return inInfo;
    }

}
