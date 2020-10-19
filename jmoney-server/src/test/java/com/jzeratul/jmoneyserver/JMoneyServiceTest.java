package com.jzeratul.jmoneyserver;


import com.jzeratul.jmoneyserver.model.JUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
public class JMoneyServiceTest {

  @Autowired
  private JMoneyService service;

//  @Test
//  @Transactional
//  public void thatUserCanBeSaved() {
//
//    // GIVEN
//    // ...
//
//    // WHEN
//    final Optional<JUser> jUser = service.getJUser(1L);
//
//    // THEN
//    assertFalse(jUser.isPresent());
//  }
}
