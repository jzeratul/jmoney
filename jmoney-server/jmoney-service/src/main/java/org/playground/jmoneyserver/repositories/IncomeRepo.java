package org.playground.jmoneyserver.repositories;

import org.playground.jmoneyserver.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
}
