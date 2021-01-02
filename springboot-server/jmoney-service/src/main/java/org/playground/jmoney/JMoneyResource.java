package org.playground.jmoney;

import java.util.List;

import javax.validation.Valid;

import org.playground.jmoney.api.IncomeApi;
import org.playground.jmoney.api.JarApi;
import org.playground.jmoney.api.PaymentApi;
import org.playground.jmoney.model.WebIncome;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
//@RequestMapping("${openapi.jmoney.base-path:}")
public class JMoneyResource implements JarApi, PaymentApi, IncomeApi {

  private JMoneyService service;

  @Override
  public ResponseEntity<List<WebJar>> updateJars(@Valid List<WebJar> jarsToUpdate) {
    return ResponseEntity.ok(service.updateJars(jarsToUpdate));
  }

  @Override
  public ResponseEntity<List<WebIncome>> getIncomes() {
    return ResponseEntity.ok(service.getIncomes());
  }

  @Override
  public ResponseEntity<List<WebJar>> getJars() {
    return ResponseEntity.ok(service.getJars());
  }

  @Override
  public ResponseEntity<List<WebIncome>> updateIncomes(@Valid List<WebIncome> incomesToUpdate) {
    return ResponseEntity.ok(service.updateIncomes(incomesToUpdate));
  }

  @Override
  public ResponseEntity<List<WebJarPayment>> updatePayments(String encodedJarId, @Valid List<WebJarPayment> paymentsToUpdate) {
    return ResponseEntity.ok(service.updatePayments(encodedJarId, paymentsToUpdate));
  }

  @Override
  public ResponseEntity<List<WebJarPayment>> getPayments(String encodedJarId) {
    return ResponseEntity.ok(service.getPayments(encodedJarId));
  }
}

