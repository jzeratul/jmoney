package com.jzeratul.jmoneyserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jzeratul.jmoneyserver.model.JUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
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

  @GetMapping(path = "/jmoney/jars/{userid}")
  public JUser getJUser(@PathVariable long userid) {
    return service.getJUser(userid).orElse(new JUser());
  }

  @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
  @PostMapping(path = "/jmoney/jars")
  public void saveJUser(@RequestBody JUser juser) {
	  
	  
	  ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	  
	  try {
			String json = mapper.writeValueAsString(juser);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	  	  
	  log.info("we are here" + juser);
    service.save(juser);
  }
}

