package org.playground.jmoney.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JUserRepo extends JpaRepository<JUser, Long> {
}
