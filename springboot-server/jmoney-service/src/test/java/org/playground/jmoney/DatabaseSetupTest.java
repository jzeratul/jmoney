package org.playground.jmoney;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.playground.jmoney.jar.Jar;
import org.playground.jmoney.jar.JarRepo;
import org.playground.jmoney.jar.JarType;
import org.playground.jmoney.payment.Payment;
import org.playground.jmoney.payment.PaymentRepo;
import org.playground.jmoney.user.JUser;
import org.playground.jmoney.user.JUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Sql({"/h2-init.sql"})
public class DatabaseSetupTest {

  @Autowired
  private JUserRepo userRepo;
  @Autowired
  private JarRepo jarRepo;
  @Autowired
  private PaymentRepo paymentRepo;
//  @Autowired
//  private IncomeRepo incomeRepo;

  @Test
  @Transactional
  public void thatUserCanBeSaved() {

    var user = createUser();
    var savedJUser = saveJUser(user);

//    saveIncome("Salary", savedJUser);

    var necessitiesJar = saveJar("Necessities", savedJUser);
    var freedomJar = saveJar("Freedom", savedJUser);
    var playJar = saveJar("Play", savedJUser);

    var necessitiesPayment = savePayment("Necessities Payment", necessitiesJar);
    var freedomPayment = savePayment("Freedom Payment", freedomJar);
    var playPayment = savePayment("Play Payment", playJar);

    List<JUser> all = userRepo.findAll();
    System.out.println(all.size());

    Optional<JUser> byId = userRepo.findById(savedJUser.getUserid());
    assertTrue(byId.isPresent());

    Optional<List<Jar>> byUserid = jarRepo.findByUserid(savedJUser.getUserid());
    assertEquals(3, byUserid.get().size());

    byUserid.get().forEach(
            jar -> {
              Optional<List<Payment>> byJarid = paymentRepo.findByJarid(jar.getJarid());
              assertEquals(1, byJarid.get().size());
            }
    );
  }

  private Payment savePayment(String paymentReason, Jar necessitiesJar) {
    // GIVEN
    var newPayment = createRandomPayment(paymentReason, necessitiesJar);
    // WHEN
    var savedPayment = paymentRepo.save(newPayment);
    // THEN
    assertNotNull(savedPayment);
    return savedPayment;
  }

  private Jar saveJar(String jarname, JUser savedJUser) {
    // GIVEN
    var newJar = createJar(jarname, savedJUser);
    // WHEN
    var savedJar = jarRepo.save(newJar);
    // THEN
    assertNotNull(savedJar);
    return savedJar;
  }

//  private Income saveIncome(String source, JUser savedJUser) {
//    // GIVEN
//    var income = createRandomIncome(source, savedJUser);
//    // WHEN
//    var savedIncome = incomeRepo.save(income);
//    // THEN
//    assertNotNull(savedIncome);
//
//    return savedIncome;
//  }

  private JUser saveJUser(JUser user) {

    // GIVEN

    // WHEN
    var savedJUser = userRepo.save(user);
    // THEN
    assertNotNull(savedJUser);

    return savedJUser;
  }

  private JUser createUser() {
    return JUser.builder()
            .createdAt(OffsetDateTime.now())
            .username("unittest")
            .password("unittest")
            .build();
  }

  private Jar createJar(String name, JUser user) {
    return Jar.builder()
            .createdAt(OffsetDateTime.now())
            .percent(BigDecimal.TEN)
            .variant(JarType.LIGHT)
            .userid(user.getUserid())
            .name(name)
            .build();
  }

  private Payment createRandomPayment(String reason, Jar jar) {
    return Payment.builder()
            .createdAt(OffsetDateTime.now())
            .amount(BigDecimal.TEN)
            .reason(reason)
            .jarid(jar.getJarid())
            .paymentDate(OffsetDateTime.now())
            .build();
  }

//  private Income createRandomIncome(String reason, JUser user) {
//    return Income.builder()
//            .createdAt(LocalDateTime.now())
//            .amount(BigDecimal.TEN)
//            .source(reason)
//            .userid(user.getUserid())
//            .incomeDate(LocalDate.now())
//            .build();
//  }
}
