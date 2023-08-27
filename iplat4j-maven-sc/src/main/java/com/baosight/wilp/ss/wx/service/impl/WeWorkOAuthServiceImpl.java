package com.baosight.wilp.ss.wx.service.impl;


import com.baosight.wilp.ss.wx.aes.WXBizJsonMsgCrypt;
import com.baosight.wilp.ss.wx.aes.WXBizMsgCrypt;
import com.baosight.wilp.ss.wx.domain.WWEchostr;
import com.baosight.wilp.ss.wx.service.WeWorkOAuthService;
import com.baosight.wilp.ss.wx.util.JacksonXmlUtil;
import com.baosight.wilp.ss.wx.util.YmlValue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class WeWorkOAuthServiceImpl implements WeWorkOAuthService {

    private static final Logger log = LoggerFactory.getLogger(WeWorkOAuthServiceImpl.class);

    @Override
    public String decryptGet(String type, String verifyMsgSig, String verifyTimeStamp, String verifyNonce, String echoStr, String corpId) {

        // 需要返回的明文
        String sEchoStr = null;
        try {
            WXBizJsonMsgCrypt wxcpt = new WXBizJsonMsgCrypt(YmlValue.dataToken, YmlValue.dataEncodingAESKey, StringUtils.isNotEmpty(corpId) ? corpId : YmlValue.corpID);
            sEchoStr = wxcpt.VerifyURL(verifyMsgSig, verifyTimeStamp,  verifyNonce, echoStr);
            log.info("decrypt echoStr: " + sEchoStr);
        } catch (Exception e) {
            log.error("decrypt echoStr error", e);
        }
        return sEchoStr;
    }

    @Override
    public String decryptPost(String type, String verifyMsgSig, String verifyTimeStamp, String verifyNonce, String echostr) {
        // 需要返回的明文
        String sEchoStr = null;
        try {
            WWEchostr echo = JacksonXmlUtil.xmlToBean(echostr, WWEchostr.class);
            log.info("WeWorkOAuthServiceImpl -> decryptPost() -> param: type={}, token={}, aesKey={}, corpId={}", type, YmlValue.dataToken, YmlValue.dataEncodingAESKey, echo.getToUserName());
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(YmlValue.dataToken, YmlValue.dataEncodingAESKey, echo.getToUserName());
            sEchoStr = wxcpt.DecryptMsg(verifyMsgSig, verifyTimeStamp,  verifyNonce, echostr);
            log.info("decrypt echoStr: " + sEchoStr);
        } catch (Exception e) {
            log.error("decrypt echoStr error", e);
        }
        return sEchoStr;
    }
}
