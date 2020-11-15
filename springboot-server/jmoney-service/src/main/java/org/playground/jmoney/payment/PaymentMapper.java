package org.playground.jmoney.payment;

import org.playground.jmoney.JMoneyUtil;
import org.playground.jmoney.model.WebJarPayment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

  private JMoneyUtil util;

  public Payment fromWebJarPayment(WebJarPayment webJarPayment) {
    return Payment.builder()
            .paymentid(util.decrypt(webJarPayment.getId()))
            .createdAt(webJarPayment.getCreatedAt())
            .amount(webJarPayment.getAmount())
            .reason(webJarPayment.getReason())
            .paymentDate(webJarPayment.getPaymentDate())
            .build();
  }

  public WebJarPayment toWebPayment(Payment payment) {
    return new WebJarPayment()
            .createdAt(payment.getCreatedAt())
            .id(util.encrypt(payment.getPaymentid()))
            .reason(payment.getReason())
            .amount(payment.getAmount())
            .paymentDate(payment.getPaymentDate());
  }
}
