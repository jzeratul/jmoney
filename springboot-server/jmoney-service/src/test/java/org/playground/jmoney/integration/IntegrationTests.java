package org.playground.jmoney.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.playground.jmoney.JMoneyUtil;
import org.playground.jmoney.TestDataContainer;
import org.playground.jmoney.TestDataContainer.TestJUser;
import org.playground.jmoney.income.IncomeRepo;
import org.playground.jmoney.income.IncomeService;
import org.playground.jmoney.jar.JarRepo;
import org.playground.jmoney.jar.JarService;
import org.playground.jmoney.model.WebIncome;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.payment.PaymentRepo;
import org.playground.jmoney.user.JUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
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
  public JMoneyUtil util;

  @Autowired
  private IncomeService incomeService;
  @Autowired
  private JarService jarService;

  @Test
  @Transactional
  public void allTests() {

    TestDataContainer testDataContainer = new TestDataContainer(userRepo, jarRepo, paymentRepo, incomeRepo, util);
    TestJUser data = testDataContainer.givenOneUserWith(1, 0, 0);

    testThatOneIncomeIsCreated_Updated_And_Deleted(testDataContainer, data);
    testThatOneJarIsCreated_Updated_And_Deleted(testDataContainer, data);
  }

  public void testThatOneIncomeIsCreated_Updated_And_Deleted(TestDataContainer testDataContainer, TestJUser data) {

    // CREATE
    WebIncome webIncome = createWebIncome(testDataContainer, data);

    // UPDATE
    updateWebIncome(testDataContainer, data, webIncome);

    // DELETE
    deleteWebIncome(testDataContainer, data, webIncome);
  }

  public void testThatOneJarIsCreated_Updated_And_Deleted(TestDataContainer testDataContainer, TestJUser data) {

    // CREATE
    WebJar webJar = createWebJar(testDataContainer, data);

    // UPDATE
    updateWebJar(testDataContainer, data, webJar);

    // DELETE
    deleteWebJar(testDataContainer, data, webJar);
  }

  private WebJar createWebJar(TestDataContainer testDataContainer, TestJUser data) {
    // GIVEN
    WebJar request = testDataContainer.createWebJar("Test Jar", null);

    // WHEN
    WebJar webJar = jarService.create(data.user.getUserid(), request);

    // THEN
    assertEquals(request.getName(), webJar.getName());
    assertEquals(request.getCreatedAt(), webJar.getCreatedAt());
    assertEquals(request.getPercent(), webJar.getPercent());
    assertEquals(request.getVariant(), webJar.getVariant());
    assertNotEquals(request.getId(), webJar.getId());
    return webJar;
  }

  private void updateWebJar(TestDataContainer testDataContainer, TestJUser data, WebJar webJar) {
    // GIVEN
    WebJar jarUpdateRequest = testDataContainer.createWebJar("Test Jar modified", webJar.getId());
    jarUpdateRequest.percent(BigDecimal.TEN);

    // WHEN
    WebJar updatedJar = jarService.update(data.user.getUserid(), jarUpdateRequest);

    // THEN
    assertEquals(jarUpdateRequest.getName(), updatedJar.getName());
    assertEquals(jarUpdateRequest.getCreatedAt(), updatedJar.getCreatedAt());
    assertEquals(jarUpdateRequest.getPercent(), updatedJar.getPercent());
    assertEquals(jarUpdateRequest.getVariant(), updatedJar.getVariant());
    assertEquals(jarUpdateRequest.getId(), updatedJar.getId());
  }

  private void deleteWebJar(TestDataContainer testDataContainer, TestJUser data, WebJar webJar) {
    // GIVEN
    WebJar deleteJarRequest = testDataContainer.createWebJar("Test Jar", webJar.getId());

    // WHEN
    jarService.delete(data.user.getUserid(), deleteJarRequest);

    // THEN prove that the object was deleted
    assertTrue(jarRepo.findById(util.decrypt(webJar.getId())).isEmpty());
  }

  private WebIncome createWebIncome(TestDataContainer testDataContainer, TestJUser data) {
    // GIVEN
    WebIncome request = testDataContainer.createWebIncome("Test income", null);

    // WHEN
    WebIncome webIncome = incomeService.create(data.user.getUserid(), request);

    // THEN
    assertEquals(request.getAmount(), webIncome.getAmount());
    assertEquals(request.getCreatedAt(), webIncome.getCreatedAt());
    assertEquals(request.getIncomeDate(), webIncome.getIncomeDate());
    assertEquals(request.getSource(), webIncome.getSource());
    assertNotEquals(request.getId(), webIncome.getId());
    return webIncome;
  }

  private void updateWebIncome(TestDataContainer testDataContainer, TestJUser data, WebIncome webIncome) {
    // GIVEN
    WebIncome incomeUpdateRequest = testDataContainer.createWebIncome("Test income modified", webIncome.getId());
    incomeUpdateRequest.amount(BigDecimal.TEN);

    // WHEN
    WebIncome webIncomeUpdated = incomeService.update(data.user.getUserid(), incomeUpdateRequest);

    // THEN
    assertEquals(incomeUpdateRequest.getAmount(), webIncomeUpdated.getAmount());
    assertEquals(incomeUpdateRequest.getCreatedAt(), webIncomeUpdated.getCreatedAt());
    assertEquals(incomeUpdateRequest.getIncomeDate(), webIncomeUpdated.getIncomeDate());
    assertEquals(incomeUpdateRequest.getSource(), webIncomeUpdated.getSource());
    assertEquals(incomeUpdateRequest.getId(), webIncomeUpdated.getId());
  }

  private void deleteWebIncome(TestDataContainer testDataContainer, TestJUser data, WebIncome webIncome) {
    // GIVEN
    WebIncome deleteIncomeRequest = testDataContainer.createWebIncome("Test income modified", webIncome.getId());

    // WHEN
    WebIncome deletedIncome = incomeService.delete(data.user.getUserid(), deleteIncomeRequest);

    // THEN prove that the object was deleted
    assertTrue(incomeRepo.findById(util.decrypt(deletedIncome.getId())).isEmpty());
  }
}
