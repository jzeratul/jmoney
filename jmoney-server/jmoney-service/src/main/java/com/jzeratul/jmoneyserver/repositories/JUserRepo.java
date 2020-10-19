package com.jzeratul.jmoneyserver.repositories;

import com.jzeratul.jmoneyserver.model.JUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JUserRepo extends JpaRepository<JUser, Long> {
}
