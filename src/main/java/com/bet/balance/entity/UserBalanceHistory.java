package com.bet.balance.entity;

import com.bet.balance.dto.UserBalanceDto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table
public class UserBalanceHistory implements Serializable {

  @Id
  @SequenceGenerator(name = "GEN_USER_BALANCE", sequenceName = "SEQ_USER_BALANCE", allocationSize = 1)
  @GeneratedValue(generator = "GEN_USER_BALANCE", strategy = GenerationType.SEQUENCE)
  @Column(name = "ID", nullable = false)
  private Long id;

  @CreationTimestamp
  @Column(name = "CREATED_AT")
  private LocalDateTime createdAt;

  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

  @Column(name = "BALANCE_SOURCE")
  @Enumerated(EnumType.ORDINAL)
  private UserBalanceDto.BalanceSource source;


  @Column(name = "BALANCE_TARGET")
  @Enumerated(EnumType.ORDINAL)
  private UserBalanceDto.TargetSource target;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public UserBalanceDto.BalanceSource getSource() {
    return source;
  }

  public void setSource(UserBalanceDto.BalanceSource source) {
    this.source = source;
  }

  public UserBalanceDto.TargetSource getTarget() {
    return target;
  }

  public void setTarget(UserBalanceDto.TargetSource target) {
    this.target = target;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
