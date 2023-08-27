package com.baosight.wilp.ss.wx.config;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;

/**
 * 企业微信相关配置常量类
 */
public class WxConstants {

    /**填写自己的企业微信ID*/
    public static final String WX_APP_ID = PlatApplicationContext.getProperty("work_wx_appid");
//    public static final String WX_APP_ID = "wx4c90a34951e319cc";
    /**填写自己创建应用的SECRENT*/
    public static final  String WX_SECRENT="OvHrQgulhrPyreJEYdTYtXEjsdqgENej_Wnfv5PtsiI";
//    public static final  String WX_SECRENT="-BK-FHMpmzOMK09bC_tiRanMRJVVlXjHilWbr_Xd5nM";
    /**应用授权作用域。企业自建应用固定填写：snsapi_base*/
    public static final String WX_SNSAPI_BASE = "snsapi_base";

    public static final String WX_SNSAPI_PRIVATEINFO = "snsapi_privateinfo";
    /**重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值，长度不可超过128个字节*/
    public static final String WX_STATE= "STATE";
    /**网页授权连接URL，固定*/
    public static String WX_CONNECT_OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect";
    /**获取token地址URL，固定*/
    public static String WX_GET_TOKEN_URL="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";
//    public static String WX_GET_TOKEN_URL="http://172.16.200.20:8016/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";
    /**获取访问用户身份URL，固定*/
    public static String WX_GET_USERID_URL=PlatApplicationContext.getProperty("work_wx_api_url")+"user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
//    public static String WX_GET_USERID_URL="http://172.16.200.20:8016/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
    /**读取通讯录用户信息URL，固定*/
    public static String WX_GET_USERINFO_URL=PlatApplicationContext.getProperty("work_wx_api_url")+"user/get?access_token=ACCESS_TOKEN&userid=USERID";
//    public static String WX_GET_USERINFO_URL="http://172.16.200.20:8016/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

    public static String WX_GET_JSAPI_TICKET_URL = PlatApplicationContext.getProperty("work_wx_api_url")+"get_jsapi_ticket?access_token=ACCESS_TOKEN";

    /**填写自己创建应用的SECRET,T5xNNnJ-oB5Ao6T2uKGcaVcZHjDh1BSMeqtdc_rvQF4*/
    public static final  String WX_SECRET = PlatApplicationContext.getProperty("work_wx_meal_secret");

    /**自定义的回调地址*/
    /**登录页面地址 REQUEST_URL_LOGIN_SUCCESS*/
    public static final String REQUEST_URL_LOGIN_SUCCESS = PlatApplicationContext.getProperty("wework_url")+"mzsrmyywe/login.html";

    /**报错页面地址REQUEST_URL_LOGIN_ERROR */
    public static final String REQUEST_URL_LOGIN_ERROR = PlatApplicationContext.getProperty("wework_url")+"mzsrmyywe/baocuo.html";

    public static final String REQUEST_URL = PlatApplicationContext.getProperty("wework_url")+"thirdpartieswechat/userinfo2";


    /**
     * 构造第三方应用oauth2链接
     * 如果第三方应用需要在打开的网页里面携带用户的身份信息，第一步需要构造如下的链接来获取code
     */
    public static String WX_H5_OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    /**
     * 敏感信息
     */
    public static String WX_H5_OAUTH2_PRIVATE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&agentid=%s#wechat_redirect";


    public static final String H5_REQUEST_URL = PlatApplicationContext.getProperty("wework_url")+"thirdpartieswechat/userinfo2";

    public static final String  SUITE_ID = PlatApplicationContext.getProperty("suite_id");
    public static final String  SUITE_SECRET = PlatApplicationContext.getProperty("suite_secret");

    /**
     * 应用agentid，snsapi_privateinfo时必填
     */
    public static final String  AGENT_ID = PlatApplicationContext.getProperty("agent_id");

    /**
     * 获取第三方应用凭证（suite_access_token）
     * 由于第三方服务商可能托管了大量的企业，其安全问题造成的影响会更加严重，故API中除了合法来源IP校验之外，还额外增加了suite_ticket作为安全凭证。
     * 获取suite_access_token时，需要suite_ticket参数。suite_ticket由企业微信后台定时推送给“指令回调URL”，每十分钟更新一次，见推送suite_ticket。
     * suite_ticket实际有效期为30分钟，可以容错连续两次获取suite_ticket失败的情况，但是请永远使用最新接收到的suite_ticket。
     * 通过本接口获取的suite_access_token有效期为2小时，开发者需要进行缓存，不可频繁获取
     */
//    public static final String GET_SUITE_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";
    public static String GET_SUITE_TOKEN= PlatApplicationContext.getProperty("work_wx_api_url")+"service/get_suite_token";

    /**
     * 获取访问用户身份
     */
//    public static final String OAUTH_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/auth/getuserinfo3rd?suite_access_token=%s&code=%s";
    public static final String OAUTH_USER_URL = PlatApplicationContext.getProperty("work_wx_api_url")+"service/auth/getuserinfo3rd?suite_access_token=%s&code=%s";

    /**
     * 该API用于使用临时授权码换取授权方的永久授权码，并换取授权信息、企业access_token，临时授权码一次有效。
     */
    public static final String PERMANENT_CODE_URL = PlatApplicationContext.getProperty("work_wx_api_url")+"service/get_permanent_code?suite_access_token=%s";


    /**
     * 获取access_token
     * 获取access_token是调用企业微信API接口的第一步，相当于创建了一个登录凭证，其它的业务API接口，都需要依赖于access_token来鉴权调用者身份。
     * 因此开发者，在使用业务接口前，要明确access_token的颁发来源，使用正确的access_token。
     */
    public static final String GET_ACCESS_TOKEN = PlatApplicationContext.getProperty("work_wx_api_url")+"gettoken?corpid=%s&corpsecret=%s";


    /**
     * 获取访问用户敏感信息, 自建应用与代开发应用可通过该接口获取成员授权的敏感字段
     */
    public static final String GET_USER_DETAIL = PlatApplicationContext.getProperty("work_wx_api_url")+"auth/getuserdetail?access_token=ACCESS_TOKEN";

    /**
     * 获取指定成员的userId
     */
    public static final  String GET_USER_ID = PlatApplicationContext.getProperty("work_wx_api_url")+"user/get?access_token=ACCESS_TOKEN&userid=USERID";

//    public static final  String GET_USER_ID_TOW = PlatApplicationContext.getProperty("work_wx_api_url")+"batch/openuserid_to_userid?access_token=ACCESS_TOKEN";

    public static final  String GET_USER_ID_TOW = "http://199.168.200.95:9080/wechat/enterprise/api.asmx/OpenUserIdToUserId";

    public static final  String GET_BASE_USER_INFO = PlatApplicationContext.getProperty("work_wx_api_url")+"service/auth/getuserinfo3rd?access_token=SUITE_ACCESS_TOKEN&code=CODE";

    public static final  String GET_OPEN_USER_ID = PlatApplicationContext.getProperty("work_wx_api_url")+"batch/userid_to_openuserid?access_token=ACCESS_TOKEN";




}
