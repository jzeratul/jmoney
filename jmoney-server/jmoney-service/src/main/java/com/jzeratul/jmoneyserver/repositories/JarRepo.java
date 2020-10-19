package com.jzeratul.jmoneyserver.repositories;

import com.jzeratul.jmoneyserver.model.Jar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JarRepo extends JpaRepository<Jar, Long> {

  Optional<List<Jar>> findByUserid(long userid);
}
