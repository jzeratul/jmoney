package org.playground.jmoney;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.income.IncomeService;
import org.playground.jmoney.jar.JarService;
import org.playground.jmoney.model.Status;
import org.playground.jmoney.model.WebIncome;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.model.WebJarPayment;
import org.playground.jmoney.payment.PaymentService;
import org.playground.jmoney.security.JMoneyUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JMoneyService {

  private final JarService jarService;
  private final IncomeService incomeService;
  private final PaymentService paymentService;

  public List<WebJar> getJars() {
    return jarService.get(getLoggedUserId());
  }

  public List<WebJarPayment> getPayments(String encryptedJarId) {
    return paymentService.get(getLoggedUserId(), encryptedJarId);
  }

  public List<WebIncome> getIncomes() {
    return incomeService.get(getLoggedUserId());
  }

  public List<WebJar> updateJars(List<WebJar> jarsToUpdate) {

    if (jarsToUpdate == null) {
      return Collections.emptyList();
    }

    jarsToUpdate.stream().forEach(o -> {
      if (Status.DELETED.equals(o.getStatus())) {
        jarService.delete(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getName());
      } else if (Status.NEW.equals(o.getStatus())) {
        jarService.create(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getName());
      } else if (Status.UPDATED.equals(o.getStatus())) {
        jarService.update(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getName());
      }
    });

    return jarService.get(getLoggedUserId());
  }

  public List<WebJarPayment> updatePayments(String encryptedJarId, List<WebJarPayment> paymentsToUpdate) {

    if (paymentsToUpdate == null) {
      return Collections.emptyList();
    }

    paymentsToUpdate.stream().forEach(o -> {
      if (Status.DELETED.equals(o.getStatus())) {
        paymentService.delete(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getReason());
      } else if (Status.NEW.equals(o.getStatus())) {
        paymentService.create(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getReason());
      } else if (Status.UPDATED.equals(o.getStatus())) {
        paymentService.update(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getReason());
      }
    });

    return paymentService.get(getLoggedUserId(), encryptedJarId);
  }

  public List<WebIncome> updateIncomes(List<WebIncome> incomesToUpdate) {

    if (incomesToUpdate == null) {
      return Collections.emptyList();
    }

    incomesToUpdate.stream().forEach(o -> {
      if (Status.DELETED.equals(o.getStatus())) {
        incomeService.delete(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getSource());
      } else if (Status.NEW.equals(o.getStatus())) {
        incomeService.create(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getSource());
      } else if (Status.UPDATED.equals(o.getStatus())) {
        incomeService.update(getLoggedUserId(), o);
      	log.debug("Operation {} on {}", o.getStatus(), o.getSource());
      }
    });

    return incomeService.get(getLoggedUserId());
  }

  private Long getLoggedUserId() {

  	UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();	
  	JMoneyUser currentUser = (JMoneyUser) authToken.getPrincipal();
  
  	return currentUser.getId();
  }
}


