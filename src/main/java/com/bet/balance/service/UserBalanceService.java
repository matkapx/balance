package com.bet.balance.service;

import com.bet.balance.dto.UserBalanceDto;
import com.bet.balance.entity.UserBalance;
import com.bet.balance.entity.UserBalanceHistory;
import com.bet.balance.repository.UserBalanceHistoryRepository;
import com.bet.balance.repository.UserBalanceRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserBalanceService {

  private UserBalanceRepository userBalanceRepository;
  private UserBalanceHistoryRepository userBalanceHistoryRepository;

  public UserBalanceService(@Autowired UserBalanceRepository userBalanceRepository,
      @Autowired UserBalanceHistoryRepository userBalanceHistoryRepository) {
    this.userBalanceRepository = userBalanceRepository;
    this.userBalanceHistoryRepository = userBalanceHistoryRepository;
  }

  public BigDecimal checkUserBalance(String userId) {
    return  userBalanceRepository.findOneByUserId(userId).map(x -> x.getBalance())
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public void addBalance(UserBalanceDto userBalanceDto) {
    UserBalanceHistory userBalanceHistory = new UserBalanceHistory();
    userBalanceHistory.setAmount(userBalanceDto.getAmount());
    userBalanceHistory.setSource(userBalanceDto.getBalanceSource());
    userBalanceHistory.setTarget(userBalanceDto.getTargetSource());
    userBalanceHistory.setUserId(userBalanceDto.getUserId());

    userBalanceHistoryRepository.saveAndFlush(userBalanceHistory);
    BigDecimal balance = userBalanceHistoryRepository.userAccountBalance(userBalanceDto.getUserId());
    userBalanceRepository.findOneByUserId(userBalanceDto.getUserId()).map(z -> {
      z.setBalance(balance);
      return z;
    }).orElseThrow(() -> new RuntimeException("User Not Found"));


  }

}
