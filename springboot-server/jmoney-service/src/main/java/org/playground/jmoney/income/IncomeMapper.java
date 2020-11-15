package org.playground.jmoney.income;

import org.playground.jmoney.JMoneyUtil;
import org.playground.jmoney.model.WebIncome;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

  private JMoneyUtil util;

  public Income fromWebIncome(WebIncome webIncome) {
    return Income.builder()
            .amount(webIncome.getAmount())
            .incomeid(util.decrypt(webIncome.getId()))
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
            .id(util.encrypt(income.getIncomeid()));
  }
}
