package com.jzeratul.jmoneyserver;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jzeratul.jmoneyserver.model.JUser;
import com.jzeratul.jmoneyserver.web.model.RequestPayment;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
//TODO move to swagger
public class JMoneyResource {

  private JMoneyService service;

  @GetMapping(path = "/jmoney/jars/{userid}")
  public JUser getJUser(@PathVariable long userid) {
    return service.getJUser(userid).orElse(new JUser());
  }

  @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
	@PostMapping(path = "/jmoney/jars/payments")
	public void savePayment(@RequestBody RequestPayment paymentUpdates) {
	  
		service.savePayment(paymentUpdates);
  }
}

