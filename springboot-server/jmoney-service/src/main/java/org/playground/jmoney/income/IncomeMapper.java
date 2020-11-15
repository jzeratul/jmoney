package org.playground.jmoney.income;

import org.playground.jmoney.model.WebIncome;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

  public Income fromWebIncome(WebIncome webIncome) {
    return Income.builder()
            .amount(webIncome.getAmount())
            .incomeid(webIncome.getId())

            .build();
  }
}
