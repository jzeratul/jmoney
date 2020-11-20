package org.playground.jmoney.jar;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class JarService {

  private JarRepo repo;
  private JarMapper mapper;

  public List<WebJar> get(long userid) {

    Optional<List<Jar>> userJars = repo.findByUserid(userid);

    if(userJars.isEmpty()) {
      return Collections.emptyList();
    }

    return userJars.get().stream()
            .map(j -> mapper.toWebJar(j))
            .collect(Collectors.toList());
  }

  public WebJar create(long userid, WebJar create) {

    log.debug("Creating jar {} for user {}", create.getName(), userid);
    Jar jar = mapper.fromWebJar(create);
    jar.setUserid(userid);

    Jar saved = repo.save(jar);

    WebJar webJar = mapper.toWebJar(saved);
    log.info("Updated jar {}#{} for user {}", webJar.getName(), webJar.getId(), userid);

    return webJar;
  }

  public WebJar delete(long userid, WebJar delete) {

    Long id = mapper.decryptId(delete.getId());
    log.debug("Deleting jar {}#{} for user {}", delete.getName(), id, userid);

    Optional<Jar> byId = repo.findById(id);
    if(byId.isEmpty()) {
      throw new IllegalArgumentException("Requested jar to delete does not belong to the requesting user or does not exist");
    }

    repo.delete(byId.get());
    log.info("Deleted jar {}#{} for user {}", delete.getName(), id, userid);

    return delete;
  }

  public WebJar update(long userid, WebJar update) {

    log.debug("Updating jar {}#{} for user {}", update.getName(), update.getId(), userid);
    Jar jar = mapper.fromWebJar(update);

    Optional<Jar> byId = repo.findById(jar.getJarid());
    if(byId.isEmpty()) {
      throw new IllegalArgumentException("Requested jar to update does not belong to the requesting user or does not exist");
    }

    repo.save(jar);
    log.info("Updated jar {}#{} for user {}", update.getName(), update.getId(), userid);

    return update;
  }
}


