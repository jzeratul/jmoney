package org.playground.jmoney.payment;

import lombok.RequiredArgsConstructor;
import org.playground.jmoney.JMoneyEncryptionService;
import org.playground.jmoney.model.WebJarPayment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

  private final JMoneyEncryptionService encryption;

  public Payment fromWebJarPayment(WebJarPayment webJarPayment) {
    return Payment.builder()
            .paymentid(decryptId(webJarPayment.getId()))
            .createdAt(webJarPayment.getCreatedAt())
            .amount(webJarPayment.getAmount())
            .jarid(decryptId(webJarPayment.getJarid()))
            .reason(webJarPayment.getReason())
            .paymentDate(webJarPayment.getPaymentDate())
            .build();
  }

  public WebJarPayment toWebPayment(Payment payment) {
    return new WebJarPayment()
            .createdAt(payment.getCreatedAt())
            .id(encryption.jasyptEncryptId(payment.getPaymentid()))
            .reason(payment.getReason())
            .amount(payment.getAmount())
            .paymentDate(payment.getPaymentDate());
  }

  public Long decryptId(String id) {
    return encryption.jasyptDecryptId(id);
  }
}
