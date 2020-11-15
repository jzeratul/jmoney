package org.playground.jmoney.jar;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.model.WebJar;
import org.playground.jmoney.user.JUser;
import org.playground.jmoney.user.JUserRepo;
import org.playground.jmoney.web.model.RequestPayment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JarService {

  private JarRepo repo;
  private JarMapper mapper;

  public Optional<List<Jar>> getJars(long userid) {
    return repo.findByUserid(userid);
  }

  public WebJar createJar(long userid, WebJar jarToCreate) {

    Jar jar = mapper.fromWebJar(jarToCreate);
    jar.setUserid(userid);

    Jar saved = repo.save(jar);

    return mapper.toWebJar(saved);
  }

  public WebJar deleteJar(long userid, WebJar jarToDelete) {
    // todo check if jar belongs to user
    return null;
  }
}


