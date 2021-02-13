package org.playground.jmoney.user;

import lombok.RequiredArgsConstructor;
import org.playground.jmoney.JMoneyEncryptionService;
import org.playground.jmoney.model.WebUser;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class JUserMapper {

  private final JMoneyEncryptionService encryption;

  public JUser existingUserFromWebUser(WebUser webUser) {
    return JUser.builder()
            .password(encryption.bCryptEncrypt(webUser.getPassword()))
            .username(webUser.getUsername())
            .build();
  }

  public JUser newUserFromWebUser(WebUser webUser) {
    return JUser.builder()
            .password(encryption.bCryptEncrypt(webUser.getPassword()))
            .username(webUser.getUsername())
            .createdAt(OffsetDateTime.now())
            .build();
  }

  public WebUser toWebUser(JUser jUser) {
    return new WebUser()
            .username(jUser.getUsername());
  }
}
