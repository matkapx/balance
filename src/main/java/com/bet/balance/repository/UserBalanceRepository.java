package com.bet.balance.repository;

import com.bet.balance.entity.UserBalance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {

  Optional<UserBalance> findOneByUserId(String userId);

}
