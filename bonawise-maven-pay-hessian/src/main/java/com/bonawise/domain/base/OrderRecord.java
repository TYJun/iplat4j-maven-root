package com.bonawise.domain.base;

import java.io.Serializable;

/**
 * 订单表
 * @author hunter
 *
 * 2018年3月7日
 */
public class OrderRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4118908584562309296L;

	private String id;
	
	private String hospitalCode;
	
	private String modulCode;
	
	private String usedUnitCode;
	
	private String userUnitName;
	
	/**
	 * 各个平台的订单号
	 * √
	 */
	private String orderId;
	
	/**
	 * 订单号
	 * √
	 */
	private String billNo;
	
	/**
	 * 支付节点自己生成的订单号
	 */
	private String out_trade_no;
	
	/**
	 * 微信订单号
	 */
	private String transactionId;
	
	/**
	 * 商品描述
	 * √
	 */
	private String productDescription;
	/**
	 * 商品详细描述
	 */
	private String productDetail;
	
	private String attach;
	/**
	 * 总金额
	 * √  以分为单位
	 */
	private Integer totalFee;
	
	private String feeType = "CNY";
	
	private Integer cashFee;
	
	private String cashFeeType;
	
	private String spbillCreateIp;
	
	private String createDate;
	
	private String updateDate;
	/**
	 * 订单状态
	 * 
	 */
	private String status;
	
	private String tradeType;
	
	private String bankType;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getModulCode() {
		return modulCode;
	}
	public void setModulCode(String modulCode) {
		this.modulCode = modulCode;
	}
	public String getUsedUnitCode() {
		return usedUnitCode;
	}
	public void setUsedUnitCode(String usedUnitCode) {
		this.usedUnitCode = usedUnitCode;
	}
	public String getUserUnitName() {
		return userUnitName;
	}
	public void setUserUnitName(String userUnitName) {
		this.userUnitName = userUnitName;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Integer getCashFee() {
		return cashFee;
	}
	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	@Override
	public String toString() {
		return "OrderRecord [id=" + id + ", hospitalCode=" + hospitalCode
				+ ", modulCode=" + modulCode + ", usedUnitCode=" + usedUnitCode
				+ ", userUnitName=" + userUnitName + ", orderId=" + orderId
				+ ", billNo=" + billNo + ", out_trade_no=" + out_trade_no
				+ ", transactionId=" + transactionId + ", productDescription="
				+ productDescription + ", productDetail=" + productDetail
				+ ", attach=" + attach + ", totalFee=" + totalFee
				+ ", feeType=" + feeType + ", cashFee=" + cashFee
				+ ", cashFeeType=" + cashFeeType + ", spbillCreateIp="
				+ spbillCreateIp + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", status=" + status
				+ ", tradeType=" + tradeType + ", bankType=" + bankType + "]";
	}
	
	
	
}
