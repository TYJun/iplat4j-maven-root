package com.bonawise.smp.alipay.hessian;

import java.util.List;
import java.util.Map;

import com.bonawise.smp.ResponseResult;
import com.bonawise.smp.alipay.entity.DownLoadAccount;
import com.bonawise.smp.entity.HessianAjaxJson;

/**
 * 支付hessian接口
 * 
 * @declaration
 * @author ldz
 * @datetime 2016年3月28日 下午2:47:19
 * @version 2016
 */
public class AliPayServiceImpl implements AliPayServiceI {


	@Override
	public ResponseResult createPosScanPay(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<DownLoadAccount> downloadbill(Map<String, String> map) {
		return null;
	}
	
	@Override
	public ResponseResult singleTradeMutilQuery(String hospital_code,
			String modul_code, String used_unit, String bill_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResponseResult signAndSaveOrder(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult signAndSaverefundOrder(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult createtradePrecreate(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult saveRefundDetail(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult singleTradeQuery(String hospital_code, String modul_code, String bill_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult uploadAccountconfig(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult closeOrder(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult refundFastPay(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> downloadbillresultMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult notify(String billNo, boolean isNotify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult mealNotify(String billNo, boolean isNotify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult patientRechargeNotify(String billNo, boolean isNotify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult workMealNotify(String billNo, boolean isNotify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult comboMealNotify(String billNo, boolean isNotify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult escortNotify(String billNo, boolean isNotify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult carNotify(String billNo, boolean isNotify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult createWapPay(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseResult signAndSaveAppOrder(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HessianAjaxJson downloadBillByDate(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
