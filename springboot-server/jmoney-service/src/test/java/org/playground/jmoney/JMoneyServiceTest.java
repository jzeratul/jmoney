package org.playground.jmoney;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.playground.jmoney.income.IncomeService;
import org.playground.jmoney.jar.JarService;
import org.playground.jmoney.model.Status;
import org.playground.jmoney.model.WebIncome;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.playground.jmoney.payment.PaymentService;
import org.playground.jmoney.user.JUserService;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JMoneyServiceTest {

  private JarService jarService = mock(JarService.class);
  private IncomeService incomeService = mock(IncomeService.class);
  private PaymentService paymentService = mock(PaymentService.class);
  private JUserService userService = mock(JUserService.class);

  private JMoneyService jMoneyService = new JMoneyService(jarService, incomeService, userService, paymentService);

  private TestDataContainer testData = new TestDataContainer();

  @Test
  public void testThatUpdateJarsSelectivelyAppliedCRUD() {

    WebJar existing = testData.createWebJar("Existing", null);
    WebJar delete = testData.createWebJar("ToDelete", null, Status.DELETED);
    WebJar create = testData.createWebJar("New", null, Status.NEW);
    WebJar update = testData.createWebJar("Updated", null, Status.UPDATED);

    jMoneyService.updateJars(Arrays.asList(
       existing, delete, create, update
    ));

    verify(jarService, times(1)).create(0L, create);
    verify(jarService, times(1)).delete(0L, delete);
    verify(jarService, times(1)).update(0L, update);
    verify(jarService, times(1)).get(0L);
  }

  @Test
  public void testThatUpdateIncomeSelectivelyAppliedCRUD() {

    WebIncome existing = testData.createWebIncome("Existing", null);
    WebIncome delete = testData.createWebIncome("ToDelete", null, Status.DELETED);
    WebIncome create = testData.createWebIncome("New", null, Status.NEW);
    WebIncome update = testData.createWebIncome("Updated", null, Status.UPDATED);

    jMoneyService.updateIncomes(Arrays.asList(
            existing, delete, create, update
    ));

    verify(incomeService, times(1)).create(0L, create);
    verify(incomeService, times(1)).delete(0L, delete);
    verify(incomeService, times(1)).update(0L, update);
    verify(incomeService, times(1)).get(0L);
  }

  @Test
  public void testThatUpdatePaymentsSelectivelyAppliedCRUD() {

    WebJarPayment existing = testData.createWebJarPayment("Existing", null, null);
    WebJarPayment delete = testData.createWebJarPayment("ToDelete", null, null, Status.DELETED);
    WebJarPayment create = testData.createWebJarPayment("New", null, null, Status.NEW);
    WebJarPayment update = testData.createWebJarPayment("Updated", null, null, Status.UPDATED);

    jMoneyService.updatePayments("123", Arrays.asList(
            existing, delete, create, update
    ));

    verify(paymentService, times(1)).create(0L, create);
    verify(paymentService, times(1)).delete(0L, delete);
    verify(paymentService, times(1)).update(0L, update);
    verify(paymentService, times(1)).get(0L, "123");
  }
}
