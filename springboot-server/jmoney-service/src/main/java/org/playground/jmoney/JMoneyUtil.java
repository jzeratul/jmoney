package org.playground.jmoney;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class JMoneyUtil {

  private StringEncryptor encryptorBean;

  public Long decrypt(String encryptedJarId) {
    String decrypt = encryptorBean.decrypt(encryptedJarId);
    return Long.valueOf(decrypt);
  }

  public String encrypt(Long id) {
    return encryptorBean.encrypt(id + "");
  }
}
