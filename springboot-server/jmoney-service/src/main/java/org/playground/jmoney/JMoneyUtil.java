package org.playground.jmoney;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JMoneyUtil {

  private final StringEncryptor encryptorBean;

  public Long decrypt(String encrypted) {
    log.debug("Decrypting {} ", encrypted);
    if(encrypted == null) {
      log.debug("Null encrypted id, returning null");
      return null;
    }

    log.info("Decrypt  " + encrypted);
    String decrypt = encryptorBean.decrypt(encrypted);

    log.debug("Decrypted {} into {}", encrypted, decrypt);
    return Long.valueOf(decrypt);
  }

  public String encrypt(Long id) {
    return encryptorBean.encrypt(id + "");
  }
}
