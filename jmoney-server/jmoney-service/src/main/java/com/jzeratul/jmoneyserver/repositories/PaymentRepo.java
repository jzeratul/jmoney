package com.jzeratul.jmoneyserver.repositories;

import com.jzeratul.jmoneyserver.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

  Optional<List<Payment>> findByJarid(long jarid);
}
