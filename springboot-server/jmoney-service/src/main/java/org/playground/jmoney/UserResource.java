package org.playground.jmoney;

import lombok.AllArgsConstructor;
import org.playground.jmoney.api.UserApi;
import org.playground.jmoney.model.WebUser;
import org.playground.jmoney.user.JUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
//@RequestMapping("${openapi.jmoney.base-path:}")
public class UserResource implements UserApi {

  private JUserService service;

  @Override
  public ResponseEntity<WebUser> login(@Valid WebUser webUser) {

    final WebUser login = service.login(webUser);

    if(login == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    return ResponseEntity.ok(login);
  }

  @Override
  public ResponseEntity<WebUser> signup(@Valid WebUser webUser) {

    final WebUser signup = service.signup(webUser);

    if(signup == null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    return ResponseEntity.ok(signup);
  }
}

