package org.playground.jmoney.jar;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JarService {

  private JarRepo repo;
  private JarMapper mapper;

  public Optional<List<Jar>> get(long userid) {
    return repo.findByUserid(userid);
  }

  public WebJar create(long userid, WebJar jarToCreate) {

    log.debug("Creating jar {} for user {}", jarToCreate.getName(), userid);
    Jar jar = mapper.fromWebJar(jarToCreate);
    jar.setUserid(userid);

    Jar saved = repo.save(jar);

    WebJar webJar = mapper.toWebJar(saved);
    log.info("Updated jar {}#{} for user {}", webJar.getName(), webJar.getId(), userid);

    return webJar;
  }

  public WebJar delete(long userid, WebJar jarToDelete) {

    log.debug("Deleting jar {}#{} for user {}", jarToDelete.getName(), jarToDelete.getId(), userid);
    Jar jar = mapper.fromWebJar(jarToDelete);

    Optional<Jar> byId = repo.findById(jar.getJarid());
    if(byId.isEmpty()) {
      throw new IllegalArgumentException("Requested jar to delete does not belong to the requesting user or does not exist");
    }

    repo.delete(byId.get());
    log.info("Deleted jar {}#{} for user {}", jarToDelete.getName(), jarToDelete.getId(), userid);

    return jarToDelete;
  }

  public WebJar update(long userid, WebJar jarToUpdate) {

    log.debug("Updating jar {}#{} for user {}", jarToUpdate.getName(), jarToUpdate.getId(), userid);
    Jar jar = mapper.fromWebJar(jarToUpdate);

    Optional<Jar> byId = repo.findById(jar.getJarid());
    if(byId.isEmpty()) {
      throw new IllegalArgumentException("Requested jar to update does not belong to the requesting user or does not exist");
    }

    repo.save(byId.get());
    log.info("Updated jar {}#{} for user {}", jarToUpdate.getName(), jarToUpdate.getId(), userid);

    return jarToUpdate;
  }
}


