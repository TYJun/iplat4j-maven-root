package com.baosight.wilp.ss.bm.utils;

/**
 * 企业微信相关配置常量类
 */
public class WxConstant {
    /**填写自己的企业微信ID*/
    public static final String WX_APP_ID = "wwa499cbcf7df50066";
//    public static final String WX_APP_ID = "wx4c90a34951e319cc";
    /**填写自己创建应用的SECRENT*/
    public static final  String WX_SECRENT="aYSMyAniAAO_fyw0_SRLWJSV0zksiJjkI192ugRbuHk";
//    public static final  String WX_SECRENT="-BK-FHMpmzOMK09bC_tiRanMRJVVlXjHilWbr_Xd5nM";
    /**应用授权作用域。企业自建应用固定填写：snsapi_base*/
    public static final String WX_SNSAPI_BASE = "snsapi_base";
    /**重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值，长度不可超过128个字节*/
    public static final String WX_STATE= "STATE";
    /**网页授权连接URL，固定*/
    public static String WX_CONNECT_OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect";
    /**获取token地址URL，固定*/
    public static String WX_GET_TOKEN_URL="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";
//    public static String WX_GET_TOKEN_URL="http://172.16.200.20:8016/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";
    /**获取访问用户身份URL，固定*/
    public static String WX_GET_USERID_URL="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
//    public static String WX_GET_USERID_URL="http://172.16.200.20:8016/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
    /**读取通讯录用户信息URL，固定*/
    public static String WX_GET_USERINFO_URL="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
//    public static String WX_GET_USERINFO_URL="http://172.16.200.20:8016/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

    /**自定义的回调地址*/
    public static final String REQUEST_URL = "http://lch520.nat200.top/wechat/userinfo";
//        public static final String REQUEST_URL = "http://mzsrmyy.yyhq365.cn/wechat/userinfo";
//        public static final String REQUEST_URLs = "http://mzsrmyy.yyhq365.cn/www/login.html";
    public static final String REQUEST_URLs = "http://lch520.nat200.top/www/login.html";
    public static final String REQUEST_URLt = "http://lch520.nat200.top/www/baocuo.html";
//       public static final String REQUEST_URLt = "http://mzsrmyy.yyhq365.cn/www/baocuo.html";

}
