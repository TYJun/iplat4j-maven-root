package com.baosight.wilp.si.kc.domain;

import java.math.BigDecimal;

/**
 * 数据实体
 * @author fangjian
 **/
public class NumberVo {
    /**数量**/
    Double num = new Double(0d);

    /**金额**/
    BigDecimal amount = new BigDecimal("0.00");

    /**数量**/
    Double transferNum = new Double(0d);

    /**金额**/
    BigDecimal transferAmount = new BigDecimal("0.00");

    public NumberVo() {}

    public NumberVo(Double num) {
        this.num = num;
    }

    public NumberVo(Double num, BigDecimal amount) {
        this.num = num;
        this.amount = amount;
    }

    public NumberVo(Double num, BigDecimal amount, Double transferNum, BigDecimal transferAmount) {
        this.num = num;
        this.amount = amount;
        this.transferNum = transferNum;
        this.transferAmount = transferAmount;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getTransferNum() {
        return transferNum;
    }

    public void setTransferNum(Double transferNum) {
        this.transferNum = transferNum;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }
}