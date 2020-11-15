package org.playground.jmoney.income;

import org.playground.jmoney.model.WebIncome;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

  public Income fromWebIncome(WebIncome webIncome) {
    return Income.builder()
            .amount(webIncome.getAmount())
            .incomeid(webIncome.getId()) // todo add decryption
            .createdAt(webIncome.getCreatedAt())
            .incomeDate(webIncome.getIncomeDate())
            .source(webIncome.getSource())
            .build();
  }

  public WebIncome toWebIncome(Income income) {
    return new WebIncome()
            .amount(income.getAmount())
            .createdAt(income.getCreatedAt())
            .incomeDate(income.getIncomeDate())
            .source(income.getSource())
            .id(income.getIncomeid()); // TODO add encryption
  }
}
