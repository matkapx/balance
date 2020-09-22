package com.bet.balance.repository;

import com.bet.balance.entity.UserBalanceHistory;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserBalanceHistoryRepository extends JpaRepository<UserBalanceHistory, Long> {

  @Query("SELECT SUM(m.amount) FROM UserBalanceHistory m WHERE m.userId= :userId")
  public BigDecimal userAccountBalance(@Param("userId") String userId);


}
