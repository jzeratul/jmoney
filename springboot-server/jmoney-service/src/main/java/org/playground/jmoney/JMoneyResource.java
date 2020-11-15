package org.playground.jmoney;

import lombok.AllArgsConstructor;
import org.apache.tomcat.Jar;
import org.playground.jmoney.api.JarApi;
import org.playground.jmoney.api.PaymentApi;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("${openapi.jmoney.base-path:}")
public class JMoneyResource implements JarApi, PaymentApi {

  private JMoneyService service;

  @Override
  public ResponseEntity<List<WebJar>> updateJars(@Valid List<WebJar> jarsToUpdate) {
    return ResponseEntity.ok(service.updateJars(jarsToUpdate));
  }

  @Override
  public ResponseEntity<List<WebJar>> getJars() {
    return ResponseEntity.ok(service.getJars());
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

