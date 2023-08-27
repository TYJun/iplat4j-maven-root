package com.baosight.wilp.ss.wx.controller;

import com.alibaba.fastjson.JSON;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.log.Logger;
import com.baosight.iplat4j.core.log.LoggerFactory;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.ss.bm.utils.CalendarUtils;
import com.baosight.wilp.ss.bm.utils.LocalServiceUtil;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;
import com.baosight.wilp.ss.wx.config.WxConstants;
import com.baosight.wilp.ss.wx.constant.DefalutConst;
import com.baosight.wilp.ss.wx.constant.WeWork;
import com.baosight.wilp.ss.wx.domain.SSWxThirdCompany;
import com.baosight.wilp.ss.wx.domain.WWAuth;
import com.baosight.wilp.ss.wx.domain.WWContactEvent;
import com.baosight.wilp.ss.wx.domain.WWReceive;
import com.baosight.wilp.ss.wx.domain.WWSuite;
import com.baosight.wilp.ss.wx.service.WeWorkOAuthService;
import com.baosight.wilp.ss.wx.util.JacksonXmlUtil;
import com.baosight.wilp.ss.wx.util.RestUtils;
import com.baosight.wilp.ss.wx.util.Result;
import com.baosight.wilp.ss.wx.util.YmlValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname InitController
 * @Description
 * @Date 2022/9/17 15:50
 * @Author keke
 */

@RestController
@RequestMapping(value = "/init")
public class InitController {
    private static final Logger log = LoggerFactory.getLogger(InitController.class);
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @Resource
    private WeWorkOAuthService weWorkOAuthService;


    /**
     * @param sVerifyMsgSig
     * @param sVerifyTimeStamp
     * @param sVerifyNonce
     * @param echostr
     * @return String
     * @Description 服务商后台-企业应用代开发-应用代开发-新建应用时创建
     * @date 2022/9/17 15:15
     */
    @GetMapping("/agentDevelopTemplateCallBack")
    public String agentDevelopTemplateCallBackGet(@RequestParam("msg_signature") String sVerifyMsgSig,
                                                  @RequestParam("timestamp") String sVerifyTimeStamp,
                                                  @RequestParam("nonce") String sVerifyNonce,
                                                  @RequestParam("echostr") String echostr,
                                                  @RequestParam("corpId") String corpId) {
        log.info("GET代开发模板回调 msg_signature: " + sVerifyMsgSig);
        log.info("GET代开发模板回调 timestamp: " + sVerifyTimeStamp);
        log.info("GET代开发模板回调 nonce: " + sVerifyNonce);
        log.info("GET代开发模板回调 echostr: " + echostr);
        log.info("GET代开发模板回调 corpId: " + corpId);
        return weWorkOAuthService.decryptGet(DefalutConst.CALLBACK_TYPE_AGENT_DEVELOP_TEMPLATE, sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, echostr, corpId);
    }

    /**
     * @param sVerifyMsgSig
     * @param sVerifyTimeStamp
     * @param sVerifyNonce
     * @param echostr
     * @return String
     * @Description 服务商后台-企业应用代开发-应用代开发-新建应用时创建
     * @date 2022/9/17 15:16
     */
    @PostMapping("/agentDevelopTemplateCallBack")
    public String agentDevelopTemplateCallBackPost(@RequestParam("msg_signature") String sVerifyMsgSig,
                                                   @RequestParam("timestamp") String sVerifyTimeStamp,
                                                   @RequestParam("nonce") String sVerifyNonce,
                                                   @RequestBody  String echostr) {
        log.info("POST代开发模板回调 msg_signature: " + sVerifyMsgSig);
        log.info("POST代开发模板回调 timestamp: " + sVerifyTimeStamp);
        log.info("POST代开发模板回调 nonce: " + sVerifyNonce);
        log.info("POST代开发模板回调 echostr: " + echostr);
        log.info("POST代开发模板回调 收到WeWork指令消息sVerifyTimeStamp={}", sVerifyTimeStamp);
        return receiveAndDealWithCallBack(DefalutConst.CALLBACK_TYPE_AGENT_DEVELOP_TEMPLATE, sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, echostr);
    }
    /**
     * @param
     * @return void
     * @Description 三方和代开发应用的处理流程
     * @date 2022/9/17 16:14
     */
    private String receiveAndDealWithCallBack(String agentType, String sVerifyMsgSig, String sVerifyTimeStamp, String sVerifyNonce, String echostr) {
        String doc = weWorkOAuthService.decryptPost(agentType, sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, echostr);
        log.info("POST代开发模板回调 收到WeWork指令消息doc={}", doc);
        if (StringUtils.isNotEmpty(doc)) {
            Result r = null;
            WWReceive receive = JacksonXmlUtil.xmlToBean(doc, WWReceive.class);
            packWWReceive(receive);
            log.info("receiveAndDealWithCallBack -> receive={}", JSON.toJSONString(receive));
            log.info("infoType:{}", receive.getInfoType());
            switch (receive.getInfoType()) {
                // 推送suite_ticket
                case WeWork.INFO_TYPE_suite_ticket:
                    WWSuite wwSuite = JacksonXmlUtil.xmlToBean(doc, WWSuite.class);
                    log.info("receiveAndDealWithCallBack -> suite_ticket={}", JSON.toJSONString(wwSuite));
                    setSuitTicket(wwSuite);
                    getSuiteAccessToken(wwSuite);
                    break;
                // 授权通知事件,包括应用的授权成功/授权变更/取消授权
                case WeWork.INFO_TYPE_create_auth:
                    WWAuth wwAuth = JacksonXmlUtil.xmlToBean(doc, WWAuth.class);
                    receiveAuth(wwAuth);
                    break;
                default:
                    log.info("未知的指令类型InfoType：:{}", receive.getInfoType());
            }
        }
        return SUCCESS;
    }

    /**
     * 获取永久授权码
     * @Title  receive_auth
     * @author liu
     * @date 2022-10-26 15:34
     * @param wwAuth
     */
    private void receiveAuth(WWAuth wwAuth) {
        EiInfo eiInfo = LocalServiceUtil.callCode1("S_ED_02", "wilp.sc.wx", "suiteAccessToken");
        List<Map<String, Object>> list = (List<Map<String, Object>>) eiInfo.get("list");
        String cacheToken =(String) list.get(0).get("label");
        String suiteAccessToken = cacheToken.split("#?#")[2];
        log.info("suite_access_token:{}",suiteAccessToken);
        log.info("临时授权码auth_code:{}",wwAuth.getAuthCode());

        com.alibaba.fastjson.JSONObject postJson = new com.alibaba.fastjson.JSONObject();
        postJson.put("auth_code",wwAuth.getAuthCode());
        String url = String.format(WxConstants.PERMANENT_CODE_URL,suiteAccessToken);
        Map response = RestUtils.post(url,postJson);

        log.info("临时授权码获取永久授权码返回:{}",response.toString());
        if(response.containsKey("errcode") && (Integer) response.get("errcode") != 0){
            log.error("临时授权码获取永久授权码失败:{}",response.toString());
        }else{
            //获取永久授权码
            String permanenCode= (String) response.get("permanent_code");
            //获取corpId
            Map<String, Object> authCorpInfo =(Map<String, Object>) response.get("auth_corp_info");
            String corpId = (String) authCorpInfo.get("corpid");
            //获取agent
            Map<String, Object> authInfo = (Map<String, Object>) response.get("auth_info");
            List<Map<String, Object>> agentList = (List<Map<String, Object>>) authInfo.get("agent");
            Map<String, Object> agent = agentList.get(0);
            String agentId = String.valueOf(agent.get("agentid"));

            SSWxThirdCompany company = new SSWxThirdCompany();
            company.setId(UUIDUtils.getUUID32());
            company.setAgentSecret(permanenCode);
            company.setCorpId(YmlValue.corpID);
            company.setCorpEncryptId(corpId);

            company.setCorpName((String) authCorpInfo.get("corp_name"));
            String fullName = authCorpInfo.get("corp_full_name") ==  null  ? "" :  (String)authCorpInfo.get("corp_full_name");
            company.setCorpFullName(fullName);
            company.setSubjectType(String.valueOf(authCorpInfo.get("subject_type")));
            //设置授权应用id  用于Jssdk agentconfig等使用
            company.setAgentId(agentId);
            company.setStatus(1);

            log.info("company:{}",company.toString());
            EiInfo info = new EiInfo();
            info.set(EiConstant.serviceName, "SSWXThirdCompany");
            info.set(EiConstant.methodName, "insert");
            info.set("param", company);
            EiInfo outInfo = XLocalManager.call(info);

        }

    }


    private void setSuitTicket(WWSuite wwSuite){
        //修改小代码
        LocalServiceUtil.editCodeSet("wilp.sc.wx", "suitTicket", wwSuite.getSuiteTicket());
    }
    /**
     * 获取suiteAccessToken存放在小代码中
     * @Title  getSuiteAccessToken
     * @author liu
     * @date 2022-10-26 13:41
     * @param wwSuite
     */
    private void getSuiteAccessToken(WWSuite wwSuite) {
        String dateTimeShort = CalendarUtils.dateTimeShortFormat(new Date());
        //小代码获取suiteAccessToken
        EiInfo eiInfo = LocalServiceUtil.callCode1("S_ED_02", "wilp.sc.wx", "suiteAccessToken");
        List<Map<String, Object>> list = (List<Map<String, Object>>) eiInfo.get("list");
        String cacheToken =(String) list.get(0).get("label");

        log.info("cacheToken:{}",cacheToken);
        String suiteAccessToken = "";
        if(StringUtil.isBlank(cacheToken)){
            //小代码中没有
            suiteAccessToken = getSuiteAccessToken(wwSuite.getSuiteTicket());
            String s = dateTimeShort + "#?#" + suiteAccessToken;
            //修改小代码
            LocalServiceUtil.editCodeSet("wilp.sc.wx", "suiteAccessToken", s);

        }else{
            //小代码中有，判断调用凭据access_token是否过期
            String time = cacheToken.split("#?#")[0];
            suiteAccessToken = cacheToken.split("#?#")[2];
            boolean compareTime = compareTime(time, dateTimeShort);
            log.info("compareTime:"  + compareTime);
            //凭据有效时间2小时,过期要重新获取 或者小代码中的suiteAccessToken =null 重新获取
            if(!compareTime || StringUtil.isBlank(suiteAccessToken) || "null".equals(suiteAccessToken)){
                //凭据有效时间2小时，
                suiteAccessToken = getSuiteAccessToken(wwSuite.getSuiteTicket());
                String s = dateTimeShort + "#?#" + suiteAccessToken;
                //修改小代码
                LocalServiceUtil.editCodeSet("wilp.sc.wx", "suiteAccessToken", s);
            }else{
                suiteAccessToken = cacheToken.split("#?#")[2];
            }
        }
        log.info("suite_access_token:{}",suiteAccessToken);
    }


    /**
     * @param wwReceive
     * @return void
     * @Description 封装父类为WWReceive对象
     * @date 2022/2/22 15:13
     */
    public static void packWWReceive(WWReceive wwReceive){
        if(StringUtils.isNotEmpty(wwReceive.getEvent()) && StringUtils.isEmpty(wwReceive.getInfoType())){
            wwReceive.setInfoType(wwReceive.getEvent());
        }
        if(wwReceive.getCreateTime() != null && wwReceive.getTimeStamp() == null){
            wwReceive.setTimeStamp(wwReceive.getCreateTime());
        }

    }

    /**
     * @param wwContactEvent
     * @return void
     * @Description 封装父类为WWContactEvent对象
     * @date 2022/2/22 15:12
     */
    public static void packWWContactEvent(WWContactEvent wwContactEvent){
        if(StringUtils.isNotEmpty(wwContactEvent.getToUserName()) && StringUtils.isEmpty(wwContactEvent.getAuthCorpId())){
            wwContactEvent.setAuthCorpId(wwContactEvent.getToUserName());
        }
        packWWReceive(wwContactEvent);
    }

    /**
     * 获取第三方应用凭证（suite_access_token）
     * @Title  getSuiteAccessToken
     * @author liu
     * @date 2022-10-21 14:10
     * @param suiteTicket
     * @return java.lang.String
     */
    public String getSuiteAccessToken(String suiteTicket) {
        log.info("开始获取第三方应用凭证.....");
        com.alibaba.fastjson.JSONObject postJson = new com.alibaba.fastjson.JSONObject();
        postJson.put("suite_id",WxConstants.SUITE_ID);
        postJson.put("suite_secret",WxConstants.SUITE_SECRET);
        postJson.put("suite_ticket",suiteTicket);
        Map response = RestUtils.post(WxConstants.GET_SUITE_TOKEN,postJson);

        log.info("获取第三方应用凭证结果:{}",response.toString());
        //获取错误日志
        if(response.containsKey("errcode") && (Integer) response.get("errcode") != 0){
            log.error(response.toString());
        }
        return (String) response.get("suite_access_token");
    }

    //判断两个时间的差值,  起始时间 + 2小时 - 当前时间 > 5秒,不满足是返回false，表示需要重新获取
    public static boolean compareTime(String start , String end){
        try {
            Date startDate = CalendarUtils.dateTimeShortFormat.parse(start);
            Date endDate = CalendarUtils.dateTimeShortFormat.parse(end);

            long startTime = startDate.getTime() + 120*60*1000;
            //判断当前时间与需求时间的差值
            long between = (startTime - endDate.getTime())/1000;

            return between > 1;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
