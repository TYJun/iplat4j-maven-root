package com.bonawise.smp.alipay.entity;

/**
 * 
 * @declaration
 * @author kangroo
 * @datetime 2016年6月3日 下午9:13:07
 * @version 2016
 */
public class DownLoadAccount  implements java.io.Serializable{
	/**
	 * 序列ID 
	 */
	private static final long serialVersionUID = 1L;
	private String ID ;
	// 单据号
	private String billCode;
	private String tradeNo;
	private String income;
	private String outcome;
	private String serviceFee;
	private String transDate;
	private String rate;
	private String is_refund;
	
	public String getIs_refund() {
		return is_refund;
	}
	public void setIs_refund(String is_refund) {
		this.is_refund = is_refund;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	
}
