package org.playground.jmoney;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.playground.jmoney.income.Income;
import org.playground.jmoney.income.IncomeRepo;
import org.playground.jmoney.jar.Jar;
import org.playground.jmoney.jar.JarRepo;
import org.playground.jmoney.jar.JarType;
import org.playground.jmoney.model.Status;
import org.playground.jmoney.model.WebIncome;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.playground.jmoney.payment.Payment;
import org.playground.jmoney.payment.PaymentRepo;
import org.playground.jmoney.user.JUser;
import org.playground.jmoney.user.JUserRepo;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AllArgsConstructor
@NoArgsConstructor
public class TestDataContainer {

  private JUserRepo userRepo;
  private JarRepo jarRepo;
  private PaymentRepo paymentRepo;
  private IncomeRepo incomeRepo;

  public static class TestJar {
    public Jar jar;
    public List<Payment> payments = new ArrayList<>();
  }
  public static class TestJUser {
    public JUser user;
    public List<Income> incomes = new ArrayList<>();
    public List<TestJar> jars = new ArrayList<>();
  }

//  public JUser givenOneUserWithNoIncomeNoJars() {
//    JUser user = createUser();
//    return saveJUser(user);
//  }

  public TestJUser givenOneUserWith(int nrJars, int nrIncomes, int nrPayments) {

    TestJUser testUser = new TestJUser();
    JUser user = saveJUser(createUser());

    for (int i = 0; i < nrJars; i++) {

      TestJar testJar = new TestJar();
      Jar jar = createAndStoreJar("Test" + i, user);
      testJar.jar = jar;
      testUser.jars.add(testJar);

      for (int j = 0; j < nrPayments; j++) {
        Payment pm = createAndStorePayment("TestPayment" + i + "_" + j, jar);
        testJar.payments.add(pm);
      }
    }

    for (int i = 0; i < nrIncomes; i++) {
      Income income = createAndStoreIncome("Test income" + i, user);
      testUser.incomes.add(income);
    }

    testUser.user = user;

    return testUser;
  }

  public Payment createAndStorePayment(String paymentReason, Jar necessitiesJar) {
    // GIVEN
    var newPayment = createPayment(paymentReason, necessitiesJar);
    // WHEN
    var savedPayment = paymentRepo.save(newPayment);
    // THEN
    assertNotNull(savedPayment);
    return savedPayment;
  }

  public Jar createAndStoreJar(String jarname, JUser savedJUser) {
    // GIVEN
    var newJar = createJar(jarname, savedJUser);
    // WHEN
    var savedJar = jarRepo.save(newJar);
    // THEN
    assertNotNull(savedJar);
    return savedJar;
  }

  public Income createAndStoreIncome(String source, JUser savedJUser) {
    // GIVEN
    var income = createIncome(source, savedJUser);
    // WHEN
    var savedIncome = incomeRepo.save(income);
    // THEN
    assertNotNull(savedIncome);

    return savedIncome;
  }

  public JUser saveJUser(JUser user) {

    // GIVEN

    // WHEN
    var savedJUser = userRepo.save(user);
    // THEN
    assertNotNull(savedJUser);

    return savedJUser;
  }

  public JUser createUser() {
    return JUser.builder()
            .createdAt(OffsetDateTime.now())
            .username("unittest")
            .password("unittest")
            .build();
  }

  public Jar createJar(String name, JUser user) {
    return Jar.builder()
            .createdAt(OffsetDateTime.now())
            .percent(BigDecimal.TEN)
            .variant(JarType.PRIMARY)
            .userid(user.getUserid())
            .name(name)
            .build();
  }

  public WebJar createWebJar(String name, @Nullable String encryptedId) {
    return new WebJar()
            .createdAt(OffsetDateTime.now())
            .percent(BigDecimal.valueOf(90L))
            .variant(JarType.LIGHT.getName())
            .id(encryptedId)
            .name(name);
  }

  public WebJar createWebJar(String name, @Nullable String encryptedId, Status status) {
    return new WebJar()
            .createdAt(OffsetDateTime.now())
            .percent(BigDecimal.valueOf(90L))
            .variant(JarType.LIGHT.getName())
            .id(encryptedId)
            .status(status)
            .name(name);
  }

  public Payment createPayment(String reason, Jar jar) {
    return Payment.builder()
            .createdAt(OffsetDateTime.now())
            .amount(BigDecimal.valueOf(1000L))
            .reason(reason)
            .jarid(jar.getJarid())
            .paymentDate(OffsetDateTime.now())
            .build();
  }

  public WebJarPayment createWebJarPayment(String reason, @Nullable String encryptedJarId, @Nullable String encryptedPaymentId) {
    return new WebJarPayment()
            .createdAt(OffsetDateTime.now())
            .amount(BigDecimal.valueOf(200L))
            .reason(reason)
            .id(encryptedPaymentId)
            .jarid(encryptedJarId)
            .paymentDate(OffsetDateTime.now());
  }

  public WebJarPayment createWebJarPayment(String reason, @Nullable String encryptedJarId, @Nullable String encryptedPaymentId, Status status) {
    return new WebJarPayment()
            .createdAt(OffsetDateTime.now())
            .amount(BigDecimal.valueOf(200L))
            .reason(reason)
            .id(encryptedPaymentId)
            .status(status)
            .jarid(encryptedJarId)
            .paymentDate(OffsetDateTime.now());
  }

  public Income createIncome(String reason, JUser user) {
    return Income.builder()
            .createdAt(OffsetDateTime.now())
            .amount(BigDecimal.valueOf(300L))
            .source(reason)
            .userid(user.getUserid())
            .incomeDate(OffsetDateTime.now())
            .build();
  }

  public WebIncome createWebIncome(String reason, @Nullable String encryptedIncomeId) {
    return new WebIncome()
            .createdAt(OffsetDateTime.now())
            .amount(BigDecimal.valueOf(500L))
            .source(reason)
            .id(encryptedIncomeId)
            .incomeDate(OffsetDateTime.now());
  }

  public WebIncome createWebIncome(String reason, @Nullable String encryptedIncomeId, Status status) {
    return new WebIncome()
            .createdAt(OffsetDateTime.now())
            .amount(BigDecimal.valueOf(500L))
            .source(reason)
            .status(status)
            .id(encryptedIncomeId)
            .incomeDate(OffsetDateTime.now());
  }
}
