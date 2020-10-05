package com.jzeratul.jmoneyserver.repositories;

import com.jzeratul.jmoneyserver.model.Jar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JarRepo extends JpaRepository<Jar, Long> {
}
