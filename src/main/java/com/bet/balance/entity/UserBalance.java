package com.bet.balance.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
public class UserBalance implements Serializable {

  @Id
  @SequenceGenerator(name = "GEN_USER_BALANCE", sequenceName = "SEQ_USER_BALANCE", allocationSize = 1)
  @GeneratedValue(generator = "GEN_USER_BALANCE", strategy = GenerationType.SEQUENCE)
  @Column(name = "ID", nullable = false)
  private Long id;

  @CreationTimestamp
  @Column(name = "CREATED_AT")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "UPDATED_AT")
  private LocalDateTime updatedAt;

  @Column(name="BALANCE")
  private BigDecimal balance;

  @Column(name="USER_ID")
  private String userId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
