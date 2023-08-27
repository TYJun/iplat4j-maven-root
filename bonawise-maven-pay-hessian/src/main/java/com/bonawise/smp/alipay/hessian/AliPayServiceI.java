package com.bonawise.smp.alipay.hessian;

import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.entity.DownLoadAccount;
import com.bonawise.smp.entity.HessianAjaxJson;


/**
 * 
 * @declaration 支付hessian接口
 * @author kangroo
 * @datetime 2016年3月28日 上午9:33:44
 * @version 2016
 */
public interface AliPayServiceI {
	
	/**
	 * pos扫码支付
	 * 
	 * @param param
	 *            构建参数
	 * @return ResponseResult respCode=200 
	 */
	public ResponseResult createPosScanPay(Map<String, String> param);

	/**
	 * 获取移动支付参数签名以及保存支付信息
	 * 
	 * @param param
	 *            构建参数
	 * @return ResponseResult respCode=200 respMsg=移动支付参数签名
	 */
	public ResponseResult signAndSaveOrder(Map<String, String> param);

	/**
	 * 获取退款参数签名以及保存支付信息
	 * 
	 * @param param 构建参数
	 * @return ResponseResult respCode=200 respMsg=退款参数签名
	 */
	public ResponseResult signAndSaverefundOrder(Map<String, String> param);
	/**
	 * 获取移动支付参数签名以及保存支付信息
	 * 
	 * @param param
	 *            构建参数
	 * @return ResponseResult respCode=200 respMsg=移动支付参数签名
	 */
	public ResponseResult createtradePrecreate(Map<String, String> param);

	/**
	 * 保存退款单据详细信息
	 * @param param
	 * @return
	 */
	public ResponseResult saveRefundDetail(Map<String, String> param) ;
	
	/**
	 * 查询订单
	 * @param hospital_code   医院编码
	 * @param modul_code      模块代码
	 * @param bill_no         单据编码
	 * @return
	 */
	public ResponseResult singleTradeQuery(String hospital_code, String modul_code, String bill_no);
	
	/**
	 * 查询订单
	 * @param hospital_code   医院编码
	 * @param modul_code      模块代码
	 * @param used_unit       食堂编码
	 * @param bill_no         单据编码
	 * @return
	 */
	public ResponseResult singleTradeMutilQuery(String hospital_code, String modul_code,String used_unit, String bill_no);
	
	
	/**
	 * 上传支付宝账户信息
	 * @param list
	 * @return
	 */
	public ResponseResult uploadAccountconfig(List<Map<String,Object>> list);

	/**
	 * 关闭订单
	 * 
	 * @param outTradeNo 商户系统内部的订单号
	 * @return
	 */
	public ResponseResult closeOrder(Map<String, String> map);

	/**
	 * 退款
	 * @param map
	 * @return
	 */
	public ResponseResult refundFastPay(Map<String, String> map);


	/**
	 * 下载对账
	 * @param map{
	 *             gmt_start_time 开始时间
	 *             gmt_end_time   结束时间
	 *             hospital_code  医院编码
	 *             modul_code     业务模块
	 *             used_unit      使用单位（食堂）
	 *           }
	 * @return
	 */
	public List<DownLoadAccount> downloadbill(Map<String, String> map);
	

	/**
	 * 下载对账
	 * @param map{
	 *             gmt_start_time 开始时间
	 *             gmt_end_time   结束时间
	 *             hospital_code  医院编码
	 *             modul_code     业务模块
	 *             used_unit      使用单位（食堂）
	 *           }
	 * @return
	 */
	public Map<String,Object> downloadbillresultMap(Map<String, String> map);

	/**
	 * 阿里支付通知应用服务器，默认异步通知
	 * @param billNo     业务单据编号
	 * @param isNotify   是否退款通知  false-支付 true-退款
	 * @return
	 */
	public ResponseResult notify(String billNo, boolean isNotify);

	/**
	 * 病患订餐阿里支付异步通知
	 * @param billNo     业务单据编号
	 * @param isNotify   是否退款通知  false-支付 true-退款
	 * @return
	 */
	public ResponseResult mealNotify(String billNo, boolean isNotify);
	
	/**
	 * 病患充值
	 * @param billNo     业务单据编号
	 * @param isNotify   是否退款通知  false-支付 true-退款
	 * @return
	 */
	public ResponseResult patientRechargeNotify(String billNo, boolean isNotify);
	
	/**
	 * 职工订餐阿里支付异步通知
	 * @param billNo     业务单据编号
	 * @param isNotify   是否退款通知  false-支付 true-退款
	 * @return
	 */
	public ResponseResult workMealNotify(String billNo, boolean isNotify);
	/**
	 * 套餐订餐阿里支付异步通知
	 * @param billNo     业务单据编号
	 * @param isNotify   是否退款通知  false-支付 true-退款
	 * @return
	 */
	public ResponseResult comboMealNotify(String billNo, boolean isNotify);
	/**
	 * 陪护阿里支付异步通知
	 * @param billNo     业务单据编号
	 * @param isNotify   是否退款通知  false-支付 true-退款
	 * @return
	 */
	public ResponseResult escortNotify(String billNo, boolean isNotify);

	/**
	 * 车辆阿里支付异步通知
	 * @param billNo     业务单据编号
	 * @param isNotify   是否退款通知  false-支付 true-退款
	 * @return
	 */
	public ResponseResult carNotify(String billNo, boolean isNotify);

	
	public ResponseResult createWapPay(Map<String, String> param);
	
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
	public HessianAjaxJson downloadBillByDate(Map<String, String> map);

	ResponseResult signAndSaveAppOrder(Map<String, String> param);
}
