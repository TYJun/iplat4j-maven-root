package com.bonawise.domain.base;
/**
 * 支付的一些常量
 * @author hunter
 *
 * 2018年3月7日
 */
public interface PayConstant {
	/**
	 * App编号    公司   wxb543b3e233dabc62
	 * 
	 * 医院后勤医
	 */
	public static final String APPID = "wx35c5b9fef9ee85bc";
	/**
	 * test   ：：  Bonawise57355735BonawisepaytestA
	 * app_secrt
	 */
	public static final String APP_SECRET = "9eb82f109857532f3ccbb4703d989712";
	
	
	public static final String API_KEY = "Bonawise57355735Bonawisepay7166A";
	
	/**
	 * 商品号     公司  1289572401
	 * 
	 */
	public static final String MCH_ID = "1263577001";
	/**
	 * 设备号
	 */
	public static final String DEVICE_INFO = "013467007045764";
	/**
	 * 资金类型   人民币
	 */
	public static final String FEE_TYPE = "CNY";
	/**
	 * 签名类型
	 */
	public static final String MD5 = "MD5";
	
	public static final String SHA256 = "HMAC-SHA256";
	
//	/**
//	 * 商品描述
//	 */
//	public static final String BODY = "腾讯充值中心-QQ会员充值";
//	/**
//	 * 交易类型   ： 默认APP
//	 */
//	public static final String TRADY_TYPE = "APP";
	
	/**
	 * 限制支付类型     不支持信用卡支付
	 */
	public static final String LIMIT_PAY = "no_credit";
	/**
	 * 成功编码
	 */
	public static final String SUCCESS_CODE = "SUCCESS";
	
	/**
	 * 支付结果通知地址
	 */
	public static final String NOTIFY_PAY_URL = "http://wxpaytest.yyhq365.cn/weChatPay/notifyPay.do";
	
	/**
	 * 退款结果通知地址
	 */
	public static final String NOTIFY_REFUND_URL = "";
	
	
	/**
	 * 失败编码
	 */
	public static final String FAIL_CODE = "FAIL";
	
	/**
	 * 微信统一下单地址
	 */
	public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * 交易保障地址
	 */
	public static final String REPORD_URL = "https://api.mch.weixin.qq.com/payitil/report";
	
	/**
	 * 订单查询地址
	 */
	public static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	/**
	 * 	订单关闭地址
	 */
	public static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	/**
	 * 退款地址
	 */
	public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	/**
	 * 退款订单查询地址
	 */
	public static final String REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	/**
	 * 下载对账单地址
	 */
	public static final String DOWNLOADBILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	/**
	 * 
	 * 二维码长链接转换短链接
	 */
	public static final String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	/**
	 * 获取授权码code。
	 */
	public static final String GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	/**
	 * 获取授权码后的回调地址，
	 */
	public static final String REDIRECT_URI = "http://wxpaytest.yyhq365.cn/weChatPay/getOpenId.do";
	/**
	 * 根据code去获取用户的openID
	 */
	public static final String GET_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	/**
	 * h5支付成功后的跳转页面
	 */
	public static final String PAY_SUCCESS_PAY = "http://wxpaytest.yyhq365.cn/paySuccess.html";
	
	/**
	 * 秘钥		公司	GffRMpWWjSblqX3B6bmlGz9I60hGTauZNmz8QHXiiMD
	 * 
	 * 这个属于推送公共号消息时，对消息进行ASE对称加密解密的key
	 * 
	 * 	192006250b4c09247ec02edce69f6a2d
	 */
	public static final String KEY = "GffRMpWWjSblqX3B6bmlGz9I60hGTauZNmz8QHXiiMD";
	
	
	
	public static final String CONFIG_SEPARATOR = "%3A";
	
	
//	public static final String API_FILE_PATH = "";
}
