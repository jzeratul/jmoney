package org.playground.jmoney.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

  Optional<List<Income>> findByUserid(long userid);
}
