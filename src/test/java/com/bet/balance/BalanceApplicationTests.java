package com.bet.balance;

import com.bet.balance.dto.UserBalanceDto;
import com.bet.balance.dto.UserBalanceDto.BalanceSource;
import com.bet.balance.dto.UserBalanceDto.TargetSource;
import com.bet.balance.entity.UserBalance;
import com.bet.balance.repository.UserBalanceHistoryRepository;
import com.bet.balance.repository.UserBalanceRepository;
import com.bet.balance.service.UserBalanceService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class BalanceApplicationTests {

  @Autowired
  UserBalanceRepository userBalanceRepository;
  @Autowired
  private UserBalanceService userBalanceService;

  @Autowired
  private UserBalanceHistoryRepository userBalanceHistoryRepository;

  @BeforeEach
  public void initData() {
    MockitoAnnotations.initMocks(this);

    UserBalance userBalance = new UserBalance();
    userBalance.setBalance(BigDecimal.valueOf(0));
    userBalance.setUserId("123");
    userBalanceRepository.saveAndFlush(userBalance);

    userBalance = new UserBalance();
    userBalance.setBalance(BigDecimal.valueOf(0));
    userBalance.setUserId("345");
    userBalanceRepository.saveAndFlush(userBalance);

    UserBalanceDto dto = new UserBalanceDto();
    dto.setAmount(BigDecimal.valueOf(50L));
    dto.setBalanceSource(UserBalanceDto.BalanceSource.CARD);
    dto.setTargetSource(null);
    dto.setUserId("123");
    userBalanceService.addBalance(dto);

    dto = new UserBalanceDto();
    dto.setAmount(BigDecimal.valueOf(10L));
    dto.setBalanceSource(UserBalanceDto.BalanceSource.DEBIT);
    dto.setTargetSource(null);
    dto.setUserId("345");
    userBalanceService.addBalance(dto);


  }

  @Test
  void testUserCheckUserBalance() {
    BigDecimal balance = userBalanceService.checkUserBalance("123");
    Assertions.assertEquals(BigDecimal.valueOf(50L), balance);
  }

  @Test()
  void testUserUserNotFoundException() {
    RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
      userBalanceService.checkUserBalance("wqe");
    });
    Assertions.assertEquals("User not found", ex.getMessage());
  }

  @Test
  void testAddBalanceAndTotalUserBalance() {
    UserBalanceDto dto = new UserBalanceDto();
    dto.setUserId("123");
    dto.setBalanceSource(BalanceSource.CARD);
    dto.setAmount(BigDecimal.valueOf(500));
    userBalanceService.addBalance(dto);
    BigDecimal balance = userBalanceService.checkUserBalance("123");
    Assertions.assertEquals(BigDecimal.valueOf(550), balance );
  }

  @Test
  void testWithDrawAndTotalUserBalance() {
    UserBalanceDto dto = new UserBalanceDto();
    dto.setUserId("123");
    dto.setTargetSource(TargetSource.PreParedCoupon);
    dto.setAmount(BigDecimal.valueOf(10).negate());
    userBalanceService.addBalance(dto);
    BigDecimal balance = userBalanceService.checkUserBalance("123");
    Assertions.assertEquals(BigDecimal.valueOf(40), balance );
  }

  @Test()
  void testUserNotFoundExceptionInAddBalanceAndTransactionIsRollbacked() {
    UserBalanceDto dto = new UserBalanceDto();
    dto.setUserId("qwe");
    dto.setTargetSource(TargetSource.PreParedCoupon);
    dto.setAmount(BigDecimal.valueOf(10).negate());
    RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> {
      userBalanceService.addBalance(dto);;
    });
    Assertions.assertEquals("User Not Found", ex.getMessage());

    int size = userBalanceHistoryRepository.findAll().size();
    Assertions.assertEquals(2, size);
  }

}
