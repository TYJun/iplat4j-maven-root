package com.baosight.wilp.ss.wx.service;


public interface WeWorkOAuthService {
    /**
        * @Description 验签中的get验证
        * @date 2022/9/17 14:50
        * @param type
        * @param verifyMsgSig
        * @param verifyTimeStamp
        * @param verifyNonce
        * @param echostr
        * @return java.lang.String
    */
    String decryptGet(String type, String verifyMsgSig, String verifyTimeStamp, String verifyNonce, String echostr, String corpId);
    /**
        * @Description 验签中的post验证
        * @date 2022/9/17 14:50
        * @param type
        * @param verifyMsgSig
        * @param verifyTimeStamp
        * @param verifyNonce
        * @param echostr
        * @return java.lang.String
    */
    String decryptPost(String type, String verifyMsgSig, String verifyTimeStamp, String verifyNonce, String echostr);


}
