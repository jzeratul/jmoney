package org.playground.jmoney.user;

import lombok.RequiredArgsConstructor;
import org.playground.jmoney.JMoneyUtil;
import org.playground.jmoney.model.WebUser;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class JUserMapper {

  private final JMoneyUtil util;

  public JUser existingUserFromWebUser(WebUser webUser) {
    return JUser.builder()
            .password(encrypt(webUser.getPassword()))
            .username(webUser.getUsername())
            .build();
  }

  public JUser newUserFromWebUser(WebUser webUser) {
    return JUser.builder()
            .password(encrypt(webUser.getPassword()))
            .username(webUser.getUsername())
            .createdAt(OffsetDateTime.now())
            .build();
  }

  public WebUser toWebUser(JUser jUser) {
    return new WebUser()
            .username(jUser.getUsername());
  }

  public String encrypt(String unencrypted) {
    return util.encryptString(unencrypted);
  }
}
