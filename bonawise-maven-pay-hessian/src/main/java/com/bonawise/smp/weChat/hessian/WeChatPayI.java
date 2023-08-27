package com.bonawise.smp.weChat.hessian;

import com.bonawise.domain.base.ResultEntry;

public interface WeChatPayI {

	/**
	 * 病患支付成功回调
	 * @param billId
	 * @return	ResultEntry
	 */
	ResultEntry patientMealNotify(String billId);
	
	/**
	 * 职工支付成功回调
	 * @param billId
	 * @return	ResultEntry
	 */
	ResultEntry workMealNotify(String billId);
	
	/**
	 * 套餐支付成功回调
	 * @param billId
	 * @return	ResultEntry
	 */
	ResultEntry comboMealNotify(String billId);
	/**
	  * 
	  * @param billId        充值UUID
	  * @param rechargeType  充值类型：职工-MEAL_RECHARGE，病患-PATIENT_RECHARGE
	  * @return
	  */
	ResultEntry rechargeNotify(String billId,String rechargeType);
	
	/**
	 * 病患支付成功回调
	 * @param billId
	 * @return	ResultEntry
	 */
	ResultEntry escortNotify(String billId);
	
	
}
