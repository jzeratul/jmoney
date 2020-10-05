package com.jzeratul.jmoneyserver;

import com.jzeratul.jmoneyserver.model.JUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class JMoneyResource {

  private JMoneyService service;

  @GetMapping(path = "/jars/{userid}")
  public JUser getJUser(@PathVariable long userid) {
    return service.getJUser(userid).orElse(new JUser());
  }

  @PostMapping(path = "/jars")
  public void saveJUser(@RequestBody JUser juser) {
    service.save(juser);
  }
}
