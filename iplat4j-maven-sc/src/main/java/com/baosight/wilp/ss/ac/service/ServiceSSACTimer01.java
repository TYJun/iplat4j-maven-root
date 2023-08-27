package com.baosight.wilp.ss.ac.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.ps.pc.domain.PSPCBillStatusInfo;
import com.baosight.wilp.ss.ac.commen.CanteenTimesEntity;
import com.baosight.wilp.ss.ac.commen.CommTimes;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBill;
import com.baosight.wilp.ss.ac.domain.SSACTscWorkOrderBillDetail;
import com.baosight.wilp.ss.bm.config.MealCommonConfig;
import com.baosight.wilp.ss.bm.domain.SSBMDcsj01;
import com.baosight.wilp.ss.bm.utils.*;
import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.hessian.AliPaySender;
import com.bonawise.smp.weChat.entity.WeChatPayGlobalConfig;
import com.bonawise.smp.weChat.hessian.WeChatPayImpl;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * (职工订餐定时任务)
 *
 * @Title:ServiceSSACTimer01
 * @ClassName:ServiceSSACTimer01
 * @Package：
 * @author: xiajunqing
 * @date:2022年2月21日
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSSACTimer01  extends ServiceBase {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSSACTimer01.class);

    /**
     * Todo(定时任务查询下单后1分钟未支付的订单，推送提醒)
     * 1.获取订餐配置超时时间，没有配置则默认15分钟
     * 2.统计下单后1分钟未付款的订单，没查到则立即结束
     * 3.查询订单数据，遍历获取信息
     * 4.从小代码里获取订餐APP推送模板编码，没有配置则立即结束，不进行推送
     * 5.调用APP推送接口推送消息
     * 6.推送完成后保存记录到订单消息推送记录表
     * 调用BaseDockingUtils.pushMsg执行APP推送
     * @Title: appPushMsg
     * @author xiajunqing
     * @date: 2022/2/15 10:57
     * @Param
     * @return:
     */
    public EiInfo appPushMsg(EiInfo inInfo) {
        String msg = "开始执行appPushMsg";
        try {
            /**1.获取订餐配置超时时间，没有配置则默认15分钟*/
            HashMap<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("mealgroupTypeCode", "billTimeOut");
            List<HashMap<String,Object>> listHosArea = dao.query("SSBMStxx01.getMealTypeData", paramMap);
            HashMap<String, Object> typeGroup = new HashMap<String, Object>();
            if(listHosArea.size() > 0){
                typeGroup = listHosArea.get(0);
            }
            //订餐配置表中没有配置则使用默认值15分钟
            Integer timeout = typeGroup.isEmpty() ? 15 : Integer.parseInt(StringUtil.toString(typeGroup.get("typeCode")));
            paramMap.put("billTimeOut",timeout);
            /**2.统计下单后1分钟未付款的订单，没查到则立即结束*/
            Integer count = SqlUtils.countByMap("SSACTscWorkOrderBill.countUnpaidBill",paramMap);
            if(count < 1) {
                inInfo.setStatus(1);
                //直接结束任务
                return inInfo;
            }
            /**3.查询订单数据，遍历获取信息*/
            List<HashMap<String,Object>> queryForList = SqlUtils.queryForListMap("SSACTscWorkOrderBill.queryUnpaidBill",paramMap);
            System.out.println("开始执行，下单后未支付的订单，推送提醒--------------------------");
            if(queryForList.size() > 0) {
                //工号的集合
                List<String> workNoList = new ArrayList<>();
                //推送消息内容
                List<String> paramList = new ArrayList<>();
                //遍历信息
                for (int i = 0; i < queryForList.size(); i++) {
                    HashMap<String, Object> hashMap = queryForList.get(i);
                    String billNo = StringUtil.toString(hashMap.get("billNo"));
                    String openId = StringUtil.toString(hashMap.get("openId"));
                    String userName = StringUtil.toString(hashMap.get("userName"));
                    //准备数据，用于保存推送记录
                    hashMap.put("id",UUIDUtils.getUUID32());
                    hashMap.put("userNo",openId);
                    hashMap.put("userName",userName);
                    hashMap.put("status","01");
                    hashMap.put("msg","您的订单"+billNo+"尚未支付，快去看看吧，超过"+timeout+"分钟订单将自动关闭");
                    hashMap.put("recCreateTime",CalendarUtils.dateTimeFormat(new Date()));
                    workNoList.add(openId);
                    paramList.add((String)hashMap.get("msg"));
                }
                inInfo.setStatus(1);

                /**4.从小代码里获取订餐APP推送模板编码*/
                EiInfo callSendFee = LocalServiceUtil.callCode1("S_ED_02","wilp.sc.msg.pushMsg", "pushMsg");
                List<HashMap<String,Object>> listSendFee = (List<HashMap<String, Object>>) callSendFee.get("list");
                if(listSendFee != null && listSendFee.size() > 0){
                    HashMap<String, Object> hashMap = listSendFee.get(0);
                    String templateCode = StringUtil.toString(hashMap.get("label"));
                    String appCode = PlatApplicationContext.getProperty("common_appCode");
                    /**5.调用APP推送接口推送消息**/
                    boolean b = BaseDockingUtils.pushMsg(workNoList, paramList, templateCode, appCode, null);
                    System.out.println("---------------订餐APP推送" + workNoList + paramList);
                    if(b){
                        /**6.推送完成后保存记录到订单消息推送记录表*/
                        SqlUtils.insertSqlByList("SSACTscWorkOrderBill.insertOrderMsg",queryForList);
                        logger.info("推送完成后保存记录到订单消息推送记录表");
                    }
                }else{
                    //没有配置不推送
                    return inInfo;
                }
            }
        } catch (Exception e) {
            inInfo.setStatus(-1);
            logger.error(msg + "：" + e.getMessage());
            e.printStackTrace();
        }
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
        String aliHospitalCode = MealCommonConfig.getSmpConfig().getHospitalCodeWechat();
        String weChatHospitalCode = MealCommonConfig.getSmpConfig().getHospitalCodeWechat();

        String aliModulCode = "work_ali";
        String wechatModulCode = "work_wechat";
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
        }else if("0401".equals(payType)){
            flag = false;
        }
        return flag;
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
            int count = SqlUtils.countByMap("SSACTworkOrderPayMiddle.countMiddle",new HashMap<String,Object>());

            if(count < 1) {
                inInfo.setStatus(1);
                return inInfo;
            }
            HashMap<String,Object> map = new HashMap<String,Object>();
            List<HashMap<String,Object>> queryForList = SqlUtils.queryForListMap("SSACTworkOrderPayMiddle.queryOrderQueueInfo",map);
            System.out.println("开始执行closeOvertimeOrder--------------------------");
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
            if(!hashMap.isEmpty() && "00".equals(hashMap.get("statusCode")) && hashMap.get("reviseTime") == null) {
                msg = "从支付节点获取支付结果";
                /**2.查询支付节点获取支付结果，为false表示该订单支付失败,执行关闭操作*/
                String payType = StringUtil.toString(hashMap.get("payType"));
                //获取支付结果
                boolean flag = getPayStatus(payType,payNo,canteenNum);
                System.out.println(payType+"订单"+billId+"支付结果flag："+flag);
                //设置statusChange信息
                PSPCBillStatusInfo billInfo = new PSPCBillStatusInfo();
                billInfo.setPboTbName("sc_work_order_bill");
                billInfo.setBeforeStatus("00");
                billInfo.setAfterStatus("99");
                billInfo.setCurrentDealer(StringUtil.toString(hashMap.get("currentDealer")));
                billInfo.setBillId(StringUtil.toString(hashMap.get("billId")));
                billInfo.setCreatorId(StringUtil.toString(hashMap.get("recCreator")));
                billInfo.setCreatorName(StringUtil.toString(hashMap.get("userName")));
                billInfo.setPboCode("MEAL");
                billInfo.setHandleAdvice("作废");
                billInfo.setIsReject("1");
                billInfo.setRejectCode("3");
                if(flag == false) {
                    //flag为false表示该订单支付失败,执行关闭操作
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String sdate = hashMap.get("recCreateTime").toString();
                    Date start = sdf.parse(sdate);
                    Date end = new Date();
                    //判断当前时间与下单时间的差值
                    long between = (end.getTime() - start .getTime())/1000;
                    long min = between/60;
                    //获取超时timeout配置
                    HashMap<String, String> payMap = new HashMap<String, String>();
                    payMap.put("typeGroupCode", "billTimeOut");
                    HashMap<String,Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("mealgroupTypeCode", "billTimeOut");
                    List<HashMap<String,Object>> listHosArea = dao.query("SSBMStxx01.getMealTypeData", paramMap);
                    HashMap<String, Object> typeGroup = new HashMap<String, Object>();
                    if(listHosArea.size() > 0){
                        typeGroup = listHosArea.get(0);
                    }
                    //订餐配置表中没有配置则使用默认值15分钟
                    Integer timeout = typeGroup.isEmpty() ? 15 : Integer.parseInt(StringUtil.toString(typeGroup.get("typeCode")));
                    System.out.println("当前时间："+sdf.format(end)+"，timeout："+timeout);
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
                        paramMap.put("sql","SSACTscWorkOrderBillDetail.queryDetailByBillNo");
                        paramMap.put("str",billNo);
                        EiInfo callQueryDetail = LocalServiceUtil.call("SSBMTy", "querySqlForListByString", paramMap);
                        List<SSACTscWorkOrderBillDetail> orderMenuList = (List<SSACTscWorkOrderBillDetail>)callQueryDetail.get("result");

                        /**6.返还菜品数量*/
                        updateMenuNumCancel(hashMap,orderMenuList);
                        //从中间表移除记录
                        if("200".equals(billStatusChangeResult.getRespCode())){
                            dao.delete("SSACTworkOrderPayMiddle.deleteByBillId",billId);
                            logger.info("移除中间表记录：" + billNo);
                        }
                        respCode = "201";
                        inInfo.setMsgKey(respCode);
                        inInfo.setMsg(msg);
                        return inInfo;
                    }else{
                        msg = "关闭截止订单";
                        /**5.关闭达到截止时间的订单*/
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
                            paramMap.put("sql","SSACTscWorkOrderBillDetail.queryDetailByBillNo");
                            paramMap.put("str",billNo);
                            EiInfo callQueryDetail = LocalServiceUtil.call("SSBMTy", "querySqlForListByString", paramMap);
                            List<SSACTscWorkOrderBillDetail> orderMenuList = (List<SSACTscWorkOrderBillDetail>)callQueryDetail.get("result");
                            /**6.返还菜品数量*/
                            updateMenuNumCancel(hashMap,orderMenuList);
                            //从中间表移除记录
                            if("200".equals(billStatusChangeResult.getRespCode())){
                                paramMap.put("sql","SSACTworkOrderPayMiddle.deleteByBillId");
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
                    }
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
                paramMap.put("sql","SSACTworkOrderPayMiddle.deleteByBillId");
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
    public void updateMenuNumCancel(HashMap<String, Object> hashMap, List<SSACTscWorkOrderBillDetail> orderMenuList){
        //用餐时间 餐次 菜品编码 菜品数量 食堂
        String mealNum = StringUtil.toString(hashMap.get("mealNum"));
        String mealDate = StringUtil.toString(hashMap.get("needDate"));
        String canteenNum = StringUtil.toString(hashMap.get("canteenNum"));
        //构建更新参数
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("canteenNum", canteenNum);
        map.put("mealDate", mealDate);
        map.put("mealNum", mealNum);
        for (SSACTscWorkOrderBillDetail orderMenu : orderMenuList) {
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

}
