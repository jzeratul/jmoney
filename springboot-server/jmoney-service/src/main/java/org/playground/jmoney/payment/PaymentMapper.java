package org.playground.jmoney.payment;

import org.playground.jmoney.model.WebJarPayment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

  public Payment fromWebJarPayment(WebJarPayment webJarPayment) {
    return Payment.builder()
            .paymentid(webJarPayment.getId()) // todo add decryption
            .createdAt(webJarPayment.getCreatedAt())
            .amount(webJarPayment.getAmount())
            .reason(webJarPayment.getReason())
            .paymentDate(webJarPayment.getPaymentDate())
            .build();
  }

  public WebJarPayment toWebPayment(Payment payment) {
    return new WebJarPayment()
            .createdAt(payment.getCreatedAt())
            .id(payment.getPaymentid()) // todo add encryption
            .reason(payment.getReason())
            .amount(payment.getAmount())
            .paymentDate(payment.getPaymentDate());
  }
}
