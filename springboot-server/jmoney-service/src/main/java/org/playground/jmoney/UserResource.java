package org.playground.jmoney;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.playground.jmoney.api.UserApi;
import org.playground.jmoney.model.WebUser;
import org.playground.jmoney.security.JwtTokenService;
import org.playground.jmoney.security.JwtUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("${openapi.jmoney.base-path:}")
@CrossOrigin
public class UserResource implements UserApi {

  private final AuthenticationManager authenticationManager;
  private final JwtUserDetailsService jwtService;
  private final JwtTokenService jwtToken;
  private final JMoneyEncryptionService jasyptEncryptionService;

  @Override
  public ResponseEntity<WebUser> login(@Valid WebUser webUser) {

    authenticate(webUser);

    final UserDetails userDetails = jwtService.loadUserByUsername(webUser.getUsername());

    final String token = jwtToken.generateToken(userDetails);

    final WebUser loggedUser = new WebUser()
            .username(webUser.getUsername())
            .jwtToken(token);

    return ResponseEntity.ok(loggedUser);

  }

  @Override
  public ResponseEntity<WebUser> signup(@Valid WebUser webUser) {

    final WebUser signup = jwtService.signup(webUser);

    if (signup == null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    return ResponseEntity.ok(signup);
  }

  private void authenticate(WebUser webUser) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(webUser.getUsername(), webUser.getPassword()));
    } catch (DisabledException e) {
      log.error("USER_DISABLED for {} {}", webUser.getUsername(), webUser.getPassword());
      throw new RuntimeException("USER_DISABLED");
    } catch (BadCredentialsException e) {
      log.error("INVALID_CREDENTIALS for {} {}", webUser.getUsername(), webUser.getPassword());
      throw new RuntimeException("INVALID_CREDENTIALS");
    }
  }
}

