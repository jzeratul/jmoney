package org.playground.jmoney.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

}
