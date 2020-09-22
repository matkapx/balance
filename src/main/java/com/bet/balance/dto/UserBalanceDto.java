package com.bet.balance.dto;

import java.math.BigDecimal;

public class UserBalanceDto {

  private BigDecimal amount;
  private TargetSource targetSource;
  private BalanceSource balanceSource;
  private String userId;

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public UserBalanceDto.TargetSource getTargetSource() {
    return targetSource;
  }

  public void setTargetSource(UserBalanceDto.TargetSource targetSource) {
    this.targetSource = targetSource;
  }

  public UserBalanceDto.BalanceSource getBalanceSource() {
    return balanceSource;
  }

  public void setBalanceSource(UserBalanceDto.BalanceSource  balanceSource) {
    this.balanceSource = balanceSource;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public enum BalanceSource {
    CARD, DEBIT, EFT,COUPON
  }


  public enum TargetSource {
    PreParedCoupon
  }

}
