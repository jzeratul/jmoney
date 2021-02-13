package org.playground.jmoney.security;

import java.util.Collections;
import java.util.Optional;

import org.playground.jmoney.model.WebUser;
import org.playground.jmoney.user.JUser;
import org.playground.jmoney.user.JUserMapper;
import org.playground.jmoney.user.JUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final JUserRepo repo;
  private final JUserMapper mapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    final Optional<JUser> byUsername = repo.findByUsername(username);

    if(byUsername.isPresent()) {

      final JUser user = byUsername.get();
      return new JMoneyUser(user.getUserid(), user.getUsername(), user.getPassword(), Collections.emptyList());

    } else {
      throw new UsernameNotFoundException("user not found");
    }
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
