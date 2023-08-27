package com.baosight.wilp.ss.bm.utils;

import java.io.Serializable;
import java.util.Properties;

public class CardUrlConfig implements Serializable{

	@Override
	public String toString() {
		return "CardUrlConfig [dataGroupCode=" + dataGroupCode
				+ ", dataGroupTreeCode=" + dataGroupTreeCode + ", card_pay="
				+ card_pay + ", card_back=" + card_back + ", card_query="
				+ card_query + ", card_query_consume=" + card_query_consume
				+ ", card_loss_work_url=" + card_loss_work_url
				+ ", card_rebind_work_url=" + card_rebind_work_url
				+ ", card_query_consume_patient_num="
				+ card_query_consume_patient_num
				+ ", card_query_recharge_record=" + card_query_recharge_record
				+ ", card_recharge_patient_url=" + card_recharge_patient_url
				+ ", card_active_patient_url=" + card_active_patient_url
				+ ", all_his_patient_url=" + all_his_patient_url
				+ ", card_pos_consume_record=" + card_pos_consume_record
				+ ", work_process_fee=" + work_process_fee
				+ ", handle_consume_order=" + handle_consume_order
				+ ", handle_consume_order_patient="
				+ handle_consume_order_patient
				+ ", update_paypassword_patient_url="
				+ update_paypassword_patient_url
				+ ", check_paypassword_patient_url="
				+ check_paypassword_patient_url + ", qrcode_patient_url="
				+ qrcode_patient_url + ", card_mealtype_url="
				+ card_mealtype_url + ", card_query_work_url="
				+ card_query_work_url + ", card_query_work_records_url="
				+ card_query_work_records_url + ", card_pay_work_url="
				+ card_pay_work_url + ", card_back_work_url="
				+ card_back_work_url + ", card_query_work_consume="
				+ card_query_work_consume
				+ ", card_query_work_recharge_record="
				+ card_query_work_recharge_record + ", card_recharge_work_url="
				+ card_recharge_work_url + ", work_query_fuli_consume="
				+ work_query_fuli_consume + ", register_check_tel="
				+ register_check_tel + ", stock_info_url=" + stock_info_url
				+ ", card_update_url=" + card_update_url
				+ ", card_loss_url=" + card_loss_url
				+ "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5492741755612083813L;
	
	/**
	 * 
	 */
	public CardUrlConfig(){
		
	}

	private String card_query_consume_record;

	public String getCard_query_consume_record() {
		return card_query_consume_record;
	}

	public void setCard_query_consume_record(String card_query_consume_record) {
		this.card_query_consume_record = card_query_consume_record;
	}

	private String card_transfer_record;

	public String getCard_transfer_record() {
		return card_transfer_record;
	}

	public void setCard_transfer_record(String card_transfer_record) {
		this.card_transfer_record = card_transfer_record;
	}

	/**
	 * 账套
	 */
	private String dataGroupCode;
	
	/**
	 * 账套树
	 */
	private String dataGroupTreeCode;
	
	/**
	 * 卡片付款
	 */
	private  String card_pay;
	
	/**
	 * 卡片退款
	 */
	private  String card_back;
	
	/**
	 * 卡片查询信息
	 */
	private  String card_query;
	
	/**
	 * 查询订单消费记录
	 */
	private String card_query_consume;
	/**
	 * 卡片挂失接口
	 */
	private String card_loss_work_url;
	/**
	 * 卡片解挂接口
	 */
	private String card_rebind_work_url;

	/**
	 * 查询订单消费记录通过住院号
	 */
	private String card_query_consume_patient_num;
	
	/**
	 * 查询充值记录
	 */
	private String card_query_recharge_record;
	
	/**
	 * 病患卡片充值接口
	 */
	private String card_recharge_patient_url;
	/**
	 * 病患卡片激活接口
	 */
	private String card_active_patient_url;
	/**
	 * 获取病患his信息接口(常州二院)
	 */
	private String all_his_patient_url;
	
	
	private String card_pos_consume_record;
	//一卡通扣款手续费
	private String work_process_fee;
	
	
	/**
     * 更新一卡通信息
     */
    private String card_update_url;
    
    /**
     * 病患POS机卡信息与订餐人信息不一致挂失卡片
     */
    private String card_loss_url;
    
    public String getCard_loss_url() {
        return card_loss_url;
    }

    public void setCard_loss_url(String card_loss_url) {
        this.card_loss_url = card_loss_url;
    }

    public String getCard_update_url() {
        return card_update_url;
    }

    public void setCard_update_url(String card_update_url) {
        this.card_update_url = card_update_url;
    }
	
	
	public String getWork_process_fee() {
		return work_process_fee;
	}

	public void setWork_process_fee(String work_process_fee) {
		this.work_process_fee = work_process_fee;
	}

	public String getAll_his_patient_url() {
		return all_his_patient_url;
	}

	public void setAll_his_patient_url(String all_his_patient_url) {
		this.all_his_patient_url = all_his_patient_url;
	}

	public String getCard_pos_consume_record() {
		return card_pos_consume_record;
	}

	public void setCard_pos_consume_record(String card_pos_consume_record) {
		this.card_pos_consume_record = card_pos_consume_record;
	}

	public String getCard_active_patient_url() {
		return card_active_patient_url;
	}

	public void setCard_active_patient_url(String card_active_patient_url) {
		this.card_active_patient_url = card_active_patient_url;
	}

	/**
	 * POS机刷卡直接消费
	 */
	private String handle_consume_order;
	/**
	 * POS机刷卡直接消费病患2019年4月18日 13:07:12
	 */
	private String handle_consume_order_patient;
	/**
	 * 更新病患支付密码
	 * 2019年7月10日 17:00:37
	 */
	private String update_paypassword_patient_url;
	/**
	 * 更新病患支付密码
	 * 2019年7月10日 17:00:37
	 */
	private String check_paypassword_patient_url;
	/**
	 * 支付码,用于堂食扫码支付
	 * 2019年7月11日 09:01:00
	 */
	private String qrcode_patient_url;
	
	/**
	 * 获取一卡通数据字典配置项
	 */
	private String card_mealtype_url;
	
	
	public String getCard_mealtype_url() {
		return card_mealtype_url;
	}

	public void setCard_mealtype_url(String card_mealtype_url) {
		this.card_mealtype_url = card_mealtype_url;
	}

	public String getQrcode_patient_url() {
		return qrcode_patient_url;
	}

	public void setQrcode_patient_url(String qrcode_patient_url) {
		this.qrcode_patient_url = qrcode_patient_url;
	}

	public String getUpdate_paypassword_patient_url() {
		return update_paypassword_patient_url;
	}

	public void setUpdate_paypassword_patient_url(
			String update_paypassword_patient_url) {
		this.update_paypassword_patient_url = update_paypassword_patient_url;
	}

	public String getCheck_paypassword_patient_url() {
		return check_paypassword_patient_url;
	}

	public void setCheck_paypassword_patient_url(
			String check_paypassword_patient_url) {
		this.check_paypassword_patient_url = check_paypassword_patient_url;
	}

	public String getHandle_consume_order_patient() {
		return handle_consume_order_patient;
	}

	public void setHandle_consume_order_patient(String handle_consume_order_patient) {
		this.handle_consume_order_patient = handle_consume_order_patient;
	}

	/**
	 * 职工一卡通卡片信息查询
	 */
	private String card_query_work_url;
	
	/**
	 * 职工一卡通余额详细查询
	 */
	private String card_query_work_records_url;
	
	/**
	 * 职工卡片付款接口
	 */
	private String card_pay_work_url;
	
	/**
	 * 职工退款接口
	 */
	private String card_back_work_url;
	
	/**
	 * 查询职工订单消费记录
	 */
	private String card_query_work_consume;
	
	/**
	 * 查询职工卡片充值记录
	 */
	private String card_query_work_recharge_record;
	
	/**
	 * 职工充值接口
	 */
	private String card_recharge_work_url;
	
	/**
	 * 福利金院外消费接口
	 */
	private String work_query_fuli_consume;
	/**
	 * 病患信息注册时是否校验手机号唯一
	 */
	private boolean register_check_tel;
	/**
	 * 获取物资信息
	 */
	private String stock_info_url;
	
	public String getStock_info_url() {
		return stock_info_url;
	}

	public void setStock_info_url(String stock_info_url) {
		this.stock_info_url = stock_info_url;
	}

	public String getCard_rebind_work_url() {
		return card_rebind_work_url;
	}

	public void setCard_rebind_work_url(String card_rebind_work_url) {
		this.card_rebind_work_url = card_rebind_work_url;
	}
	
	public String getCard_loss_work_url() {
		return card_loss_work_url;
	}

	public void setCard_loss_work_url(String card_loss_work_url) {
		this.card_loss_work_url = card_loss_work_url;
	}
	
	public boolean getRegister_check_tel() {
		return register_check_tel;
	}

	public void setRegister_check_tel(boolean register_check_tel) {
		this.register_check_tel = register_check_tel;
	}

	/**
	 * 账套
	 * @return
	 */
	public String getDataGroupCode() {
		return dataGroupCode;
	}
	
	/**
	 * 账套
	 * @param dataGroupCode
	 */
	public void setDataGroupCode(String dataGroupCode) {
		this.dataGroupCode = dataGroupCode;
	}
	
	/**
	 * 账套树
	 * @return
	 */
	public String getDataGroupTreeCode() {
		return dataGroupTreeCode;
	}

	/**
	 * 账套树
	 * @param dataGroupTreeCode
	 */
	public void setDataGroupTreeCode(String dataGroupTreeCode) {
		this.dataGroupTreeCode = dataGroupTreeCode;
	}

	/**
	 * 卡片付款
	 * @return
	 */
	public  String getCard_pay() {
		return card_pay;
	}

	/**
	 * 卡片付款
	 * @param card_pay
	 */
	public  void setCard_pay(String card_pay) {
		this.card_pay = card_pay;
	}
    /**
     * 卡片退款
     * @return
     */
	public  String getCard_back() {
		return card_back;
	}
	/**
	 * 卡片退款
	 * @param card_back
	 */
	public  void setCard_back(String card_back) {
		this.card_back = card_back;
	}
	/**
	 * 卡片查询信息
	 * @return
	 */
	public  String getCard_query() {
		return card_query;
	}
	/**
	 * 卡片查询信息
	 * @param card_query
	 */
	public  void setCard_query(String card_query) {
		this.card_query = card_query;
	}
	/**
	 * 查询订单消费记录
	 * @return
	 */
	public String getCard_query_consume() {
		return card_query_consume;
	}
	/**
	 * 查询订单消费记录
	 * @param card_query_consume
	 */
	public void setCard_query_consume(String card_query_consume) {
		this.card_query_consume = card_query_consume;
	}
	/**
	 * 查询订单消费记录通过住院号
	 * @return
	 */
	public String getCard_query_consume_patient_num() {
		return card_query_consume_patient_num;
	}
	/**
	 * 查询订单消费记录通过住院号
	 * @param card_query_consume_patient_num
	 */
	public void setCard_query_consume_patient_num(
			String card_query_consume_patient_num) {
		this.card_query_consume_patient_num = card_query_consume_patient_num;
	}
	/**
	 * 查询充值记录
	 * @return
	 */
	public String getCard_query_recharge_record() {
		return card_query_recharge_record;
	}
	/**
	 * 查询充值记录
	 * @param card_query_recharge_record
	 */
	public void setCard_query_recharge_record(String card_query_recharge_record) {
		this.card_query_recharge_record = card_query_recharge_record;
	}
	/**
	 * 病患充值接口
	 * @return
	 */
	public String getCard_recharge_patient_url() {
		return card_recharge_patient_url;
	}
	/**
	 * 病患充值接口
	 * @param card_recharge_patient_url
	 */
	public void setCard_recharge_patient_url(String card_recharge_patient_url) {
		this.card_recharge_patient_url = card_recharge_patient_url;
	}
	/**
	 * POS机消费接口
	 * @return
	 */
	public String getHandle_consume_order() {
		return handle_consume_order;
	}
	/**
	 * POS机消费接口
	 * @param handle_consume_order
	 */
	public void setHandle_consume_order(String handle_consume_order) {
		this.handle_consume_order = handle_consume_order;
	}
	/**
	 * 职工一卡通卡片信息查询
	 * @return card_query_work_url
	 */
	public String getCard_query_work_url() {
		return card_query_work_url;
	}
	/**
	 * 职工一卡通卡片信息查询
	 * @param card_query_work_url
	 */
	public void setCard_query_work_url(String card_query_work_url) {
		this.card_query_work_url = card_query_work_url;
	}
	/**
	 * 职工一卡通余额详细查询
	 * @return card_query_work_records_url
	 */
	public String getCard_query_work_records_url() {
		return card_query_work_records_url;
	}
	/**
	 * 职工一卡通余额详细查询
	 * @param card_query_work_records_url
	 */
	public void setCard_query_work_records_url(String card_query_work_records_url) {
		this.card_query_work_records_url = card_query_work_records_url;
	}
	/**
	 * 职工卡片付款接口
	 * @return card_pay_work_url
	 */
	public String getCard_pay_work_url() {
		return card_pay_work_url;
	}
	/**
	 * 职工卡片付款接口
	 * @param card_pay_work_url
	 */
	public void setCard_pay_work_url(String card_pay_work_url) {
		this.card_pay_work_url = card_pay_work_url;
	}
	/**
	 * 职工退款接口
	 * @return card_back_work_url
	 */
	public String getCard_back_work_url() {
		return card_back_work_url;
	}
	/**
	 * 职工退款接口
	 * @param card_back_work_url
	 */
	public void setCard_back_work_url(String card_back_work_url) {
		this.card_back_work_url = card_back_work_url;
	}
	/**
	 * 查询职工订单消费记录
	 * @return card_query_work_consume
	 */
	public String getCard_query_work_consume() {
		return card_query_work_consume;
	}
	/**
	 * 查询职工订单消费记录
	 * @param card_query_work_consume
	 */
	public void setCard_query_work_consume(String card_query_work_consume) {
		this.card_query_work_consume = card_query_work_consume;
	}
	/**
	 * 查询职工卡片充值记录
	 * @return card_query_work_recharge_record
	 */
	public String getCard_query_work_recharge_record() {
		return card_query_work_recharge_record;
	}
	/**
	 * 查询职工卡片充值记录
	 * @param card_query_work_recharge_record
	 */
	public void setCard_query_work_recharge_record(
			String card_query_work_recharge_record) {
		this.card_query_work_recharge_record = card_query_work_recharge_record;
	}
	/**
	 * 职工充值接口
	 * @return
	 */
	public String getCard_recharge_work_url() {
		return card_recharge_work_url;
	}
	/**
	 * 职工充值接口
	 * @param card_recharge_work_url
	 */
	public void setCard_recharge_work_url(String card_recharge_work_url) {
		this.card_recharge_work_url = card_recharge_work_url;
	}
	
	/**
	 * 福利金院外消费记录
	 * @return
	 */
	public String getWork_query_fuli_consume() {
		return work_query_fuli_consume;
	}
	
	/**
	 * 福利金院外消费记录
	 * @param work_query_fuli_consume
	 */
	public void setWork_query_fuli_consume(String work_query_fuli_consume) {
		this.work_query_fuli_consume = work_query_fuli_consume;
	}

	/**
	 * 
	 * @param proper
	 */
	public CardUrlConfig(Properties proper) {
		if(!proper.isEmpty() && proper != null){
			this.dataGroupCode = proper.getProperty("data_group_code");
			this.dataGroupTreeCode = proper.getProperty("data_group_tree_code");
			this.card_pay = proper.getProperty("card_pay_url");
			this.card_back = proper.getProperty("card_back_url");
			this.card_query = proper.getProperty("card_query_url");
			this.card_query_consume = proper.getProperty("card_query_consume");
			this.card_query_consume_record = proper.getProperty("card_query_consume_record");
			this.card_query_consume_patient_num = proper.getProperty("card_query_consume_patient_num");
			this.card_query_recharge_record = proper.getProperty("card_query_recharge_record");
			this.card_recharge_patient_url = proper.getProperty("card_recharge_patient_url");
			this.handle_consume_order = proper.getProperty("handle_consume_order");
			this.handle_consume_order_patient = proper.getProperty("handle_consume_order_patient");
			this.update_paypassword_patient_url = proper.getProperty("update_paypassword_patient_url");
			this.check_paypassword_patient_url = proper.getProperty("check_paypassword_patient_url");
			this.qrcode_patient_url = proper.getProperty("qrcode_patient_url");
			this.card_query_work_url = proper.getProperty("card_query_work_url");
			this.card_active_patient_url = proper.getProperty("card_active_patient_url");
			this.card_loss_work_url = proper.getProperty("card_loss_work_url");
			this.card_rebind_work_url = proper.getProperty("card_rebind_work_url");
			this.card_query_work_records_url = proper.getProperty("card_query_work_records_url");
			this.card_pay_work_url = proper.getProperty("card_pay_work_url");
			this.card_back_work_url = proper.getProperty("card_back_work_url");
			this.card_query_work_consume = proper.getProperty("card_query_work_consume");
			this.card_query_work_recharge_record = proper.getProperty("card_query_work_recharge_record");
			this.card_recharge_work_url = proper.getProperty("card_recharge_work_url");
			this.work_query_fuli_consume = proper.getProperty("work_query_fuli_consume");
			this.register_check_tel = "true".equals(proper.getProperty("register_check_tel"))?true:false;
			this.card_mealtype_url = proper.getProperty("card_mealtype_url");
			this.card_pos_consume_record = proper.getProperty("card_pos_consume_record");
			this.all_his_patient_url = proper.getProperty("all_his_patient_url");
			this.work_process_fee=proper.getProperty("work_process_fee");
			this.stock_info_url=proper.getProperty("stock_info_url");
			this.card_update_url=proper.getProperty("card_update_url");
			this.card_loss_url = proper.getProperty("card_loss_url");
			this.card_transfer_record = proper.getProperty("card_transfer_record");
		} else {
			return;
		}
	} 
	
}
