package org.playground.jmoney.payment;

import lombok.RequiredArgsConstructor;
import org.playground.jmoney.JMoneyUtil;
import org.playground.jmoney.model.WebJarPayment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

  private final JMoneyUtil util;

  public Payment fromWebJarPayment(WebJarPayment webJarPayment, Long jarId) {
    return Payment.builder()
            .paymentid(decryptId(webJarPayment.getId()))
            .createdAt(webJarPayment.getCreatedAt())
            .amount(webJarPayment.getAmount())
            .jarid(jarId)
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

  public Long decryptId(String id) {
    return util.decrypt(id);
  }
}
