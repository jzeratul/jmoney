package org.playground.jmoney.income;

import lombok.RequiredArgsConstructor;
import org.playground.jmoney.JMoneyUtil;
import org.playground.jmoney.model.WebIncome;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncomeMapper {

  private final JMoneyUtil util;

  public Income fromWebIncome(WebIncome webIncome) {
    return Income.builder()
            .amount(webIncome.getAmount())
            .incomeid(decryptId(webIncome.getId()))
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

  public Long decryptId(String id) {
    return util.decrypt(id);
  }
}
