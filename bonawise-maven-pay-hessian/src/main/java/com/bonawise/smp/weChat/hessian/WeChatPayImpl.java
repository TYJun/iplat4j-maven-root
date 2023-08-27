package com.bonawise.smp.weChat.hessian;

import java.util.Map;

import com.bonawise.smp.config.MealGlobalConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


import com.bonawise.domain.base.BusinessInfoDTO;
import com.bonawise.domain.base.OrderRecord;
import com.bonawise.domain.base.ResultEntry;
import com.bonawise.smp.entity.HessianAjaxJson;
import com.caucho.hessian.client.HessianProxyFactory;

@Component
public class WeChatPayImpl implements WeChatPayI{
	
	private static HessianProxyFactory factory = null;

	private static WeChatPay weClient = null;

	static {
		System.out.println("WeChatPaySender 加载……");
		factory = new HessianProxyFactory();
		factory.setReadTimeout(80000);
		try {
			weClient = (WeChatPay) factory.create(WeChatPay.class,
					MealGlobalConfig.getSmpConfig().getCommonNodeUrlWechat()
						+ MealGlobalConfig.getSmpConfig().getHessianPatternWechat());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("hessian服务连接异常，部分功能将不可用");
		}
	}
	
	/**
	 * 默认构造器
	 */
	public WeChatPayImpl() {
		super();
	}

	/**
	 * @Desc 获取食堂对应的微信公众号appid
	 * @date 2018年7月20日 15点56分
	 * @author yk
	 * @param busInfoDTO
	 * @return
	 */
	public static ResultEntry getHospitalAccount(BusinessInfoDTO busInfoDTO){
		return weClient.getHospitalAccount(busInfoDTO);
	}
	
	/**
	 * @desc 调用微信支付接口
	 * @date 2018年5月29日 14点18分
	 * @author yk
	 * @param order			支付参数
	 * @param dto			支付参数
	 * @param ip   			本地ip
	 * @param openId		支付凭证
	 * @return	ResultEntry
	 */
	public static ResultEntry unifiedOrderWithJSAPI(OrderRecord order, BusinessInfoDTO dto, String ip, String openId) {
		return weClient.unifiedOrderWithJSAPI(order, dto, ip, openId);
	}
	
	/**
	 * 微信H5支付
	 * @param order
	 * @param dto
	 * @param ip
	 * @return
	 */
	public static ResultEntry unifiedOrderWithH5(OrderRecord order, BusinessInfoDTO dto, String ip) {
		return weClient.unifiedOrderWithH5(order, dto, ip);
	}
	
	/**
	 * @desc 调用微信支付接口
	 * @date 2018年5月29日 14点18分
	 * @author yk
	 * @param order			支付参数
	 * @param dto			支付参数
	 * @param ip   			本地ip
	 * @return	ResultEntry
	 */
	public static ResultEntry unifiedOrderWithNative(OrderRecord order, BusinessInfoDTO dto, String ip) {
		return weClient.unifiedOrderWithNative2(order, dto, ip);
	}
	
	/**
	 * 根据订单号查询订单详细信息
	 * @param orderId 订号单
	 * @param dto 医院配置
	 * @return
	 */
	public static ResultEntry OrderQuery(String orderId, BusinessInfoDTO dto) {
		return weClient.OrderQuery(orderId, dto);
	}
	
	public static HessianAjaxJson downloadBillByDate(Map<String, Object> map) {
		return weClient.downloadBillByDate(map);
	}

	public static ResultEntry refund(BusinessInfoDTO dto, String orderId, Integer refundFee) {
		return weClient.refund(dto, orderId, refundFee);
	}
	
	/**
	 * 扫码支付,模式二,返回一个二维码，扫码进行支付
	 * @param order 订单信息，付款金额为分
	 * @param ip 客户端ip
	 * @return
	 */
	public static ResultEntry unifiedOrderWithNative2(OrderRecord order, BusinessInfoDTO dto, String ip) {
		return weClient.unifiedOrderWithNative2(order, dto, ip);
	}

	@Override
	public ResultEntry patientMealNotify(String billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntry workMealNotify(String billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntry comboMealNotify(String billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntry rechargeNotify(String billId, String rechargeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntry escortNotify(String billId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @desc 获取支付授权码
	 * @date 2018年5月29日 13点49分
	 * @author yk
	 * @param code
	 * @param busInfoDTO
	 * @return	ResultEntry
	 */
	public static ResultEntry getOpenId(String code, BusinessInfoDTO busInfoDTO){
		ResultEntry result = new ResultEntry();
		if(StringUtils.isBlank(code)) {
			result.setRespCode(201);
			result.setRespMsg("缺少code参数！");
			return result;
		}
		
		//调用微信支付节点
		result = weClient.getOpenId(code, busInfoDTO);
		if(result.getRespCode() == 500) {
			System.out.println("获取微信支付凭证出错：" + result.getRespMsg());
		}
		
		return result;
	}
}
