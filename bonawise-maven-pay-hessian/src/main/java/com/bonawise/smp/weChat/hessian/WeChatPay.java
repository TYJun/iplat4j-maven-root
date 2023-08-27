package com.bonawise.smp.weChat.hessian;

import java.util.Map;

import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.OrderRecord;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.entity.HessianAjaxJson;



public interface WeChatPay {
	
	/**
	 * 公众号支付
	 * @param order 订单信息，付款金额为分
	 * @param ip 客户端ip
	 * @return
	 */
	ResultEntry unifiedOrderWithJSAPI(OrderRecord order, BusinessInfoDTO dto, String ip, String openId) ;
	
	
	/**
	 * 根据code获取用户openId
	 * @param code 授权码
	 * @return
	 */
	ResultEntry getOpenId(String code, BusinessInfoDTO dto);
	
	/**
	 * 根据医院信息获取医院公众号配置
	 * @param dto 医院配置
	 * @return
	 */
	ResultEntry getHospitalAccount(BusinessInfoDTO dto);
	

	/**
	 * 根据订单号查询订单详细信息
	 * @param orderId 订号单
	 * @param dto 医院配置
	 * @return
	 */
	ResultEntry OrderQuery(String orderId, BusinessInfoDTO dto);
	
	/**
	 * 退款
	 * @param dto 医院配置
	 * @param orderId 订单号
	 * @param refundFee 退款金额
	 * @return
	 */
	ResultEntry refund(BusinessInfoDTO dto, String orderId, Integer refundFee);
	
	/**
	 * 退款查询
	 * @param dto 医院配置
	 * @param orderId 订单号
	 * @return
	 */
	ResultEntry refundQuery(BusinessInfoDTO dto, String orderId);
	
	/**
	 * 扫码支付,模式二,返回一个二维码，扫码进行支付
	 * @param order 订单信息，付款金额为分
	 * @param ip 客户端ip
	 * @return
	 */
	ResultEntry unifiedOrderWithNative2(OrderRecord order, BusinessInfoDTO dto, String ip);
	
	/**
	 * h5支付，返回一个地址，需要重定向到这个地址
	 * @param order 订单信息，付款金额为分
	 * @param ip 客户端ip
	 * @return
	 */
	ResultEntry unifiedOrderWithH5(OrderRecord order, BusinessInfoDTO dto, String ip);
	
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
	public HessianAjaxJson downloadBillByDate(Map<String, Object> map);
}
