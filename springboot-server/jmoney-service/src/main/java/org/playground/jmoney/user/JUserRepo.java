package org.playground.jmoney.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JUserRepo extends JpaRepository<JUser, Long> {

  Optional<JUser> findByUsername(String username);
}
