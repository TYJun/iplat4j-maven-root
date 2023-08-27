package com.bonawise.smp.alipay.hessian;

import java.util.List;
import java.util.Map;

import com.bonawise.smp.config.MealGlobalConfig;
import org.springframework.stereotype.Component;


import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.entity.HessianAjaxJson;
import com.caucho.hessian.client.HessianProxyFactory;

/**
 * @declaration 支付宝支付发起类
 * @author kangroo
 * @datetime 2016年3月30日 上午10:42:49
 * @version 2016
 */
@Component
public class AliPaySender {

	private static HessianProxyFactory factory = null;

	private static AliPayServiceI client = null;

	static {
		System.out.println("AliPaySender 加载……");
		factory = new HessianProxyFactory();
		factory.setReadTimeout(80000);
		try {
			//client = (AliPayServiceI) factory.create(AliPayServiceI.class,
			//		 "http://paytest.yyhq365.cn/aliPayService");
			client = (AliPayServiceI) factory.create(AliPayServiceI.class,
					 MealGlobalConfig.getSmpConfig().getCommonNodeUrlAli()
						+ MealGlobalConfig.getSmpConfig().getHessianPatternAli());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("hessian服务连接异常，部分功能将不可用");
		}
	}

	/**
	 * 默认构造器
	 */
	public AliPaySender() {
		super();
	}

	/**
	 * 移动支付获取签名参数字符串
	 * @param param {
	 *               bill_no       业务单据编号 DC20160524000001
	 *               total_fee     支付总金额 单位（元）
	 *               memo          商品详细描述信息
	 *	             }
	 * @return
	 */
	public static ResponseResult refundFastPay(Map<String,String> param) {
		return client.signAndSaverefundOrder(param);
	}

	public static ResponseResult uploadAccountconfig(List<Map<String,Object>> list) {
		// 上传支付账户信息
		return client.uploadAccountconfig(list);
	}

	/**
	 * 移动支付获取签名参数字符串
	 * @param param {
	 *               hospital_code 医院编码
	 *               modul_code    业务模块
	 *               used_unit     使用单位（食堂）
	 *               bill_no       业务单据编号 DC20160524000001
	 *               subject       商品标题描述信息
	 *               total_fee     支付总金额 单位（元）
	 *               body          商品详细描述信息
	 *	             }
	 * @return
	 */
	public static ResponseResult createDirectPay(Map<String,String> param) {
		return client.signAndSaveOrder(param);
	}


	/**
	 * 下载对账单信息
	 * @param params{
	 *               hospital_code  医院编号
	 *               modul_code     模块编码
	 *               use_unit       使用单位
	 *               gmt_start_time 开始时间
	 *               gmt_end_time   结束时间
	 *               startRow       分页开始记录
	 *               rows           分页总数
	 *               is_refund      0-交易成    1-退款   空-查所有
	 *               }
	 * @return  返回订单数据信息
	 */
	public static Map<String,Object> downBillResultMap(Map<String,String> map){
		return client.downloadbillresultMap(map);
	}

	/**
	 * 下载对账
	 * @param map{
	 *             gmt_date 日期
	 *             hospital_code  医院编码
	 *             modul_code     业务模块 （非必选）
	 *             used_unit      使用单位 （非必选）
	 *           }
	 * @return
	 */
	public static HessianAjaxJson downloadBillByDate(Map<String,String> map){
		return client.downloadBillByDate(map);
	}

	public static ResponseResult createtradePrecreate(Map<String,String> param){
		return client.createtradePrecreate(param);
	}

	public static ResponseResult createDirectAppPay(Map<String,String> param) {
		return client.signAndSaveAppOrder(param);
	}

	/**
	 * 移动支付获取签名参数字符串
	 * @param param {
	 *               hospital_code 医院编码
	 *               modul_code    业务模块
	 *               used_unit     使用单位（食堂）
	 *               bill_no       业务单据编号 DC20160524000001
	 *               subject       商品标题描述信息
	 *               total_fee     支付总金额 单位（元）
	 *               body          商品详细描述信息
	 *               again         二次支付 1-是  其他-不是
	 *	             }
	 * @return
	 */
	public static ResponseResult createWapPay(Map<String,String> param) {
		return client.createWapPay(param);
	}
	/**
	 *
	 * POS机扫码支付 .
	 *
	 * @param param
	 * @return
	 */
	public static ResponseResult createPosScanPay(Map<String,String> param){
		param.put("goods_type", param.get("modul_type"));
		return client.createPosScanPay(param);
	}

	/**
	 * 根据业务单据编号查询支付订单情况
	 * @param bill_no
	 * @return
	 */
	public static ResponseResult singleTradeQuery(String hospital_code, String modul_code, String bill_no){
		return client.singleTradeMutilQuery(hospital_code, modul_code, "",bill_no );
	}

}
