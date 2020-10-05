package com.jzeratul.jmoneyserver;

import com.jzeratul.jmoneyserver.model.JUser;
import com.jzeratul.jmoneyserver.repositories.JUserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JMoneyService {

  private JUserRepo userRepo;

  public Optional<JUser> getJUser(long userid) {
    return userRepo.findById(userid);
  }

  public JUser save(JUser user) {
    return userRepo.save(user);
  }
}


