package com.bet.balance.controller;


import com.bet.balance.dto.UserBalanceDto;
import com.bet.balance.service.UserBalanceService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {


  @Autowired
  private UserBalanceService userBalanceService;


  @PostMapping("/user")
  public BigDecimal checkUserBalance(@RequestBody String userId) {
    return userBalanceService.checkUserBalance(userId);
  }


  @PostMapping("/user/addBalance")
  public void checkUserBalance(@RequestBody UserBalanceDto balanceDto) {
    userBalanceService.addBalance(balanceDto);
  }


}
