package org.playground.jmoney.integration;

import org.junit.jupiter.api.Test;
import org.playground.jmoney.JMoneyEncryptionService;
import org.playground.jmoney.TestDataContainer;
import org.playground.jmoney.TestDataContainer.TestJUser;
import org.playground.jmoney.income.IncomeRepo;
import org.playground.jmoney.income.IncomeService;
import org.playground.jmoney.jar.JarRepo;
import org.playground.jmoney.jar.JarService;
import org.playground.jmoney.model.WebIncome;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.playground.jmoney.payment.PaymentRepo;
import org.playground.jmoney.payment.PaymentService;
import org.playground.jmoney.user.JUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Sql({"/h2-init.sql"})
@Transactional
public class IntegrationTests {

  @Autowired
  private JUserRepo userRepo;
  @Autowired
  private JarRepo jarRepo;
  @Autowired
  private PaymentRepo paymentRepo;
  @Autowired
  private IncomeRepo incomeRepo;
  @Autowired
  public JMoneyEncryptionService util;

  @Autowired
  private IncomeService incomeService;
  @Autowired
  private JarService jarService;
  @Autowired
  private PaymentService paymentService;

  @Test
  @Transactional
  public void allTests() {

    TestDataContainer testDataContainer = new TestDataContainer(userRepo, jarRepo, paymentRepo, incomeRepo/*, util*/);
    TestJUser data = testDataContainer.givenOneUserWith(0, 0, 0);

    // CREATE Income
    WebIncome webIncome = createWebIncome(testDataContainer, data);

    // UPDATE Income
    updateWebIncome(testDataContainer, data, webIncome);

    // DELETE Income
    deleteWebIncome(testDataContainer, data, webIncome);

    // CREATE Jar
    WebJar webJar = createWebJar(testDataContainer, data);

    // UPDATE Jar
    updateWebJar(testDataContainer, data, webJar);

    // CREATE Jar Payment
    WebJarPayment webJarPayment = createWebJarPayment(testDataContainer, data, webJar);

    // UPDATE Jar Payment
    updateWebJarPayment(testDataContainer, data, webJarPayment);

    // DELETE Jar Payment
    deleteWebJarPayment(testDataContainer, data, webJarPayment);

    // DELETE Jar
    deleteWebJar(testDataContainer, data, webJar);
  }

  private WebJar createWebJar(TestDataContainer testDataContainer, TestJUser data) {
    // GIVEN
    WebJar createRequest = testDataContainer.createWebJar("Test Jar", null);

    // WHEN
    WebJar created = jarService.create(data.user.getUserid(), createRequest);

    // THEN
    assertEquals(createRequest.getName(), created.getName());
    assertEquals(createRequest.getCreatedAt(), created.getCreatedAt());
    assertEquals(createRequest.getPercent(), created.getPercent());
    assertEquals(createRequest.getVariant(), created.getVariant());
    assertNotEquals(createRequest.getId(), created.getId());
    return created;
  }

  private void updateWebJar(TestDataContainer testDataContainer, TestJUser data, WebJar webJar) {
    // GIVEN
    WebJar updateRequest = testDataContainer.createWebJar("Test Jar modified", webJar.getId());
    updateRequest.percent(BigDecimal.TEN);

    // WHEN
    WebJar updated = jarService.update(data.user.getUserid(), updateRequest);

    // THEN
    assertEquals(updateRequest.getName(), updated.getName());
    assertEquals(updateRequest.getCreatedAt(), updated.getCreatedAt());
    assertEquals(updateRequest.getPercent(), updated.getPercent());
    assertEquals(updateRequest.getVariant(), updated.getVariant());
    assertEquals(updateRequest.getId(), updated.getId());
  }

  private void deleteWebJar(TestDataContainer testDataContainer, TestJUser data, WebJar webJar) {
    // GIVEN
    WebJar deleteRequest = testDataContainer.createWebJar("Test Jar", webJar.getId());

    // WHEN
    jarService.delete(data.user.getUserid(), deleteRequest);

    // THEN prove that the object was deleted
    assertTrue(jarRepo.findById(util.jasyptDecryptId(webJar.getId())).isEmpty());
  }

  private WebIncome createWebIncome(TestDataContainer testDataContainer, TestJUser data) {
    // GIVEN
    WebIncome createRequest = testDataContainer.createWebIncome("Test income", null);

    // WHEN
    WebIncome created = incomeService.create(data.user.getUserid(), createRequest);

    // THEN
    assertEquals(createRequest.getAmount(), created.getAmount());
    assertEquals(createRequest.getCreatedAt(), created.getCreatedAt());
    assertEquals(createRequest.getIncomeDate(), created.getIncomeDate());
    assertEquals(createRequest.getSource(), created.getSource());
    assertNotEquals(createRequest.getId(), created.getId());
    return created;
  }

  private void updateWebIncome(TestDataContainer testDataContainer, TestJUser data, WebIncome webIncome) {
    // GIVEN
    WebIncome updateRequest = testDataContainer.createWebIncome("Test income modified", webIncome.getId());
    updateRequest.amount(BigDecimal.TEN);

    // WHEN
    WebIncome updated = incomeService.update(data.user.getUserid(), updateRequest);

    // THEN
    assertEquals(updateRequest.getAmount(), updated.getAmount());
    assertEquals(updateRequest.getCreatedAt(), updated.getCreatedAt());
    assertEquals(updateRequest.getIncomeDate(), updated.getIncomeDate());
    assertEquals(updateRequest.getSource(), updated.getSource());
    assertEquals(updateRequest.getId(), updated.getId());
  }

  private void deleteWebIncome(TestDataContainer testDataContainer, TestJUser data, WebIncome webIncome) {
    // GIVEN
    WebIncome deleteRequest = testDataContainer.createWebIncome("Test income modified", webIncome.getId());

    // WHEN
    WebIncome deleted = incomeService.delete(data.user.getUserid(), deleteRequest);

    // THEN prove that the object was deleted
    assertTrue(incomeRepo.findById(util.jasyptDecryptId(deleted.getId())).isEmpty());
  }

  private WebJarPayment createWebJarPayment(TestDataContainer testDataContainer, TestJUser data, WebJar webJar) {
    // GIVEN
    WebJarPayment createRequest = testDataContainer.createWebJarPayment("Test payment", webJar.getId(), null);

    // WHEN
    WebJarPayment created = paymentService.create(data.user.getUserid(), createRequest);

    // THEN
    assertEquals(createRequest.getAmount(), created.getAmount());
    assertEquals(createRequest.getCreatedAt(), created.getCreatedAt());
    assertEquals(createRequest.getPaymentDate(), created.getPaymentDate());
    assertEquals(createRequest.getReason(), created.getReason());
    assertNotEquals(createRequest.getId(), created.getId());
    return created;
  }

  private void updateWebJarPayment(TestDataContainer testDataContainer, TestJUser data, WebJarPayment webJarPayment) {
    // GIVEN
    WebJarPayment updateRequest = testDataContainer.createWebJarPayment("Test payment modified", webJarPayment.getJarid(), webJarPayment.getId());
    updateRequest.amount(BigDecimal.TEN);

    // WHEN
    WebJarPayment updated = paymentService.update(data.user.getUserid(), updateRequest);

    // THEN
    assertEquals(updateRequest.getAmount(), updated.getAmount());
    assertEquals(updateRequest.getCreatedAt(), updated.getCreatedAt());
    assertEquals(updateRequest.getReason(), updated.getReason());
    assertEquals(updateRequest.getPaymentDate(), updated.getPaymentDate());
    assertEquals(updateRequest.getId(), updated.getId());
  }

  private void deleteWebJarPayment(TestDataContainer testDataContainer, TestJUser data, WebJarPayment webJarPayment) {
    // GIVEN
    WebJarPayment deleteRequest = testDataContainer.createWebJarPayment("Test payment", webJarPayment.getJarid(), webJarPayment.getId());

    // WHEN
    WebJarPayment deletedIncome = paymentService.delete(data.user.getUserid(), deleteRequest);

    // THEN prove that the object was deleted
    assertTrue(paymentRepo.findById(util.jasyptDecryptId(deletedIncome.getId())).isEmpty());
  }
}
