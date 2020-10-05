package com.jzeratul.jmoneyserver.repositories;

import com.jzeratul.jmoneyserver.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
}
