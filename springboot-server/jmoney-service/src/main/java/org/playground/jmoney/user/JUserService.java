package org.playground.jmoney.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.model.WebUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class JUserService {

  private JUserMapper mapper;
  private JUserRepo repo;

  public WebUser login(WebUser webUser) {

    final JUser jUser = mapper.existingUserFromWebUser(webUser);

    final Optional<JUser> user = repo.findByUsernameAndPassword(jUser.getUsername(), jUser.getPassword());

    if (user.isEmpty()) {
      log.info("Attempted login with {} {} {} failed.", webUser.getUsername(), jUser.getPassword(), webUser.getPassword());
      return null;
    }

    return mapper.toWebUser(user.get());
  }

  public WebUser signup(WebUser webUser) {

    final JUser jUser = mapper.existingUserFromWebUser(webUser);

    final Optional<JUser> user = repo.findByUsername(jUser.getUsername());

    if (user.isPresent()) {
      log.info("Attempted signup with existing username {}.", webUser.getUsername());
      return null;
    }

    final JUser savedUser = repo.save(jUser);

    return mapper.toWebUser(savedUser);
  }
}
