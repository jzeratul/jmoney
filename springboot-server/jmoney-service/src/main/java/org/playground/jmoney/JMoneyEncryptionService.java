package org.playground.jmoney;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JMoneyEncryptionService {

  private final StringEncryptor jasyptEncryptorBean;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Long jasyptDecryptId(String encrypted) {
    log.debug("Decrypting {} ", encrypted);
    if(encrypted == null) {
      log.debug("Null encrypted id, returning null");
      return null;
    }

    log.info("Decrypt  " + encrypted);
    String decrypt = jasyptEncryptorBean.decrypt(encrypted);

    log.debug("Decrypted {} into {}", encrypted, decrypt);
    return Long.valueOf(decrypt);
  }

  public String jasyptEncryptId(Long id) {
    return jasyptEncryptorBean.encrypt(id + "");
  }

  public String jasyptDecryptString(String encrypted) {
    return jasyptEncryptorBean.decrypt(encrypted);
  }

  public String jasyptEncryptString(String decrypted) {
    return jasyptEncryptorBean.encrypt(decrypted);
  }

  public String bCryptEncrypt(String decrypted) {
    return bCryptPasswordEncoder.encode(decrypted);
  }

  public boolean bCryptMatch(String encrypted, String decrypted) {
    return bCryptPasswordEncoder.matches(decrypted, encrypted);
  }

  public static void main(String[] ar) {
    System.out.println(new BCryptPasswordEncoder().encode("jmoney"));
  }
}
