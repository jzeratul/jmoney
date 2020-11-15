package org.playground.jmoney.payment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.model.WebJarPayment;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {

  private PaymentRepo repo;
  private PaymentMapper mapper;

  public List<WebJarPayment> get(long userid, long jarid) {

    Optional<List<Payment>> jarPayments = repo.findByJarid(jarid);

    if(jarPayments.isEmpty()) {
      return Collections.emptyList();
    }

    return jarPayments.get().stream()
            .map(j -> mapper.toWebPayment(j))
            .collect(Collectors.toList());
  }

  public WebJarPayment create(long userid, WebJarPayment create, Long jarid) {

    log.debug("Creating payment {}# amount:{} for user {}", create.getReason(), create.getAmount(), userid);
    Payment payment = mapper.fromWebJarPayment(create);
    payment.setJarid(jarid);

    Payment saved = repo.save(payment);

    WebJarPayment webJarPayment = mapper.toWebPayment(saved);
    log.info("Updated payment {}# id:{} for user {}", webJarPayment.getReason(), webJarPayment.getId(), userid);

    return webJarPayment;
  }

  public WebJarPayment delete(long userid, WebJarPayment delete) {

    log.debug("Deleting payment {}# id:{} for user {}", delete.getReason(), delete.getId(), userid);
    Payment income = mapper.fromWebJarPayment(delete);

    Optional<Payment> byId = repo.findById(income.getPaymentid());
    if(byId.isEmpty()) {
      throw new IllegalArgumentException("Requested payment to delete does not belong to the requesting user or does not exist");
    }

    repo.delete(byId.get());
    log.info("Deleted payment {}# id:{} for user {}", delete.getReason(), delete.getId(), userid);

    return delete;
  }

  public WebJarPayment update(long userid, WebJarPayment update) {

    log.debug("Updating payment {}# id:{} for user {}", update.getReason(), update.getId(), userid);
    Payment income = mapper.fromWebJarPayment(update);

    Optional<Payment> byId = repo.findById(income.getPaymentid());
    if(byId.isEmpty()) {
      throw new IllegalArgumentException("Requested payment to update does not belong to the requesting user or does not exist");
    }

    repo.save(byId.get());
    log.info("Updated payment {}# id:{} for user {}", update.getReason(), update.getId(), userid);

    return update;
  }
}
