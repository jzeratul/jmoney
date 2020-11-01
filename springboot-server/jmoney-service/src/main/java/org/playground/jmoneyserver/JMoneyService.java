package org.playground.jmoneyserver;

import org.playground.jmoneyserver.model.JUser;
import org.playground.jmoneyserver.repositories.JUserRepo;
import org.playground.jmoneyserver.web.model.RequestPayment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JMoneyService {

  private JUserRepo userRepo;

  public Optional<JUser> getJUser(long userid) {
    return userRepo.findById(userid);
  }

  public void savePayment(RequestPayment paymentUpdates) {

    Optional<JUser> findById = userRepo.findById(paymentUpdates.getUserId());

    if (findById.isEmpty()) {
      log.error("User {} does not exist", paymentUpdates.getUserId());
    return;
  }

//		JUser jUser = userRepo.findById(1L);
//		Map<Long, Jar> jars = jUser.getJars();

//		// update the jars with the new payments
//		if (paymentUpdates.getAddPayments() != null && !paymentUpdates.getAddPayments().isEmpty()) {
//
//			paymentUpdates.getAddPayments().forEach(newPayment -> {
//			  // TODO 1 error handling if on the request we have ids that do not exist
//			  // TODO 2 update the model if possible to avoid for loops in this case
//				// Maybe add a map instead of a list?
//			  Jar updateJar = jars.stream().filter(j -> j.getId() == newPayment.getJarId()).findFirst().get();
//
//				updateJar.getLastPayments().add(
//						Payment.builder()
//						.amount(newPayment.getAmount())
//						.createdAt(newPayment.getCreatedAt())
//				    .reason(newPayment.getReason())
//				    .paymentDate(newPayment.getPaymentDate())
//				    .build());
//			});
//		}
//
//		// update the jars with the deleted payments
//		if (paymentUpdates.getRemovePayments() != null && !paymentUpdates.getRemovePayments().isEmpty()) {
//
//			paymentUpdates.getRemovePayments().forEach(pu -> {
//			  // TODO 1 error handling if on the request we have ids that do not exist
//			  // TODO 2 update the model if possible to avoid for loops in this case
//				// Maybe add a map instead of a list?
//			  Jar updateJar = jars.stream().filter(j -> j.getId() == pu.getJarId()).findFirst().get();
//
//				Iterator<Payment> iterator = updateJar.getLastPayments().iterator();
//				while (iterator.hasNext()) {
//					Payment next = iterator.next();
//					if (next.getId() == pu.getPaymentId()) {
//						iterator.remove();
//						break;
//					}
//				}
//			});
//		}

//		userRepo.save(jUser);
  }
}

