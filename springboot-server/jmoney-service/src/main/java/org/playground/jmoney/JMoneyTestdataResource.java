package org.playground.jmoney;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.api.TestdataApi;
import org.playground.jmoney.jar.JarType;
import org.playground.jmoney.jar.StandardJars;
import org.playground.jmoney.model.Status;
import org.playground.jmoney.model.WebIncome;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
//@RequestMapping("${openapi.jmoney.base-path:}")
public class JMoneyTestdataResource implements TestdataApi {

  private JMoneyService service;

  @Override
  public ResponseEntity<Void> generateTestdata() {

    WebJar necessities = createWebJar(BigDecimal.valueOf(55), JarType.PRIMARY.getName(), StandardJars.NECESSITIES.getName());
    WebJar play = createWebJar(BigDecimal.valueOf(5), JarType.SUCCESS.getName(), StandardJars.PLAY.getName());
    WebJar financialFreedom = createWebJar(BigDecimal.valueOf(15), JarType.DANGER.getName(), StandardJars.FINANCIAL_FREEDOM.getName());
    WebJar education = createWebJar(BigDecimal.valueOf(5), JarType.WARNING.getName(), StandardJars.EDUCATION.getName());
    WebJar longTermSpending = createWebJar(BigDecimal.valueOf(10), JarType.PRIMARY.getName(), StandardJars.LONG_TERM_SPENDING.getName());
    WebJar give = createWebJar(BigDecimal.valueOf(5), JarType.LIGHT.getName(), StandardJars.GIVE.getName());

    final List<WebJar> webJars = service.updateJars(Arrays.asList(necessities, play, financialFreedom, education, longTermSpending, give));
    log.info("Generated jars for user {}");

    WebIncome income1 = createWebIncome(new BigDecimal("109.9"), "Salary");
    WebIncome income2 = createWebIncome(new BigDecimal("209.9"), "Salary2");

    service.updateIncomes(Arrays.asList(income1, income2));
    log.info("Generated incomes for user {}");

    for (WebJar webJar : webJars) {
      WebJarPayment p1 = createWebJarPayment(webJar.getId(), BigDecimal.TEN, "Payment 1");
      WebJarPayment p2 = createWebJarPayment(webJar.getId(), BigDecimal.valueOf(100), "Payment 2");
      WebJarPayment p3 = createWebJarPayment(webJar.getId(), BigDecimal.valueOf(50), "Payment 3");
      WebJarPayment p4 = createWebJarPayment(webJar.getId(), BigDecimal.valueOf(90), "Payment 4");
      WebJarPayment p5 = createWebJarPayment(webJar.getId(), BigDecimal.valueOf(100), "Payment 5");

      service.updatePayments(webJar.getId(), Arrays.asList(p1, p2, p3, p4, p5));
      log.info("Generated payments for user {} for jar {}", 0, webJar.getName());
    }

    return null;
  }

  private WebJar createWebJar(BigDecimal percent, String variant, String name) {
    WebJar wj = new WebJar();
    wj.status(Status.NEW);
    wj.percent(percent);
    wj.setCreatedAt(OffsetDateTime.now());
    wj.setVariant(variant);
    wj.setName(name);
    return wj;
  }

  private WebIncome createWebIncome(BigDecimal amount, String source) {
    WebIncome wi = new WebIncome();
    wi.status(Status.NEW);
    wi.amount(amount);
    wi.source(source);
    wi.incomeDate(OffsetDateTime.now());
    wi.setCreatedAt(OffsetDateTime.now());
    return wi;
  }

  private WebJarPayment createWebJarPayment(String jarid, BigDecimal amount, String reason) {
    WebJarPayment wp = new WebJarPayment();
    wp.status(Status.NEW);
    wp.jarid(jarid);
    wp.paymentDate(OffsetDateTime.now());
    wp.amount(amount);
    wp.reason(reason);
    wp.createdAt(OffsetDateTime.now());
    return wp;
  }
}

