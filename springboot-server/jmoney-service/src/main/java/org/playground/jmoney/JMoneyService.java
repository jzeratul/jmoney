package org.playground.jmoney;

import org.playground.jmoney.income.IncomeService;
import org.playground.jmoney.jar.JarService;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.playground.jmoney.payment.PaymentService;
import org.playground.jmoney.user.JUser;
import org.playground.jmoney.user.JUserRepo;
import org.playground.jmoney.user.JUserService;
import org.playground.jmoney.web.model.RequestPayment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JMoneyService {

  private JarService jarService;
  private IncomeService incomeService;
  private JUserService jUserService;
  private PaymentService paymentService;

  public List<WebJar> updateJars(List<WebJar> jarsToUpdate) {
    return null;
  }

  public List<WebJar> getJars() {
    return null;
  }

  public List<WebJarPayment> updatePayments(String encodedJarId, List<WebJarPayment> paymentsToUpdate) {
    return null;
  }

  public List<WebJarPayment> getPayments(String encodedJarId) {
    return null;
  }
}


