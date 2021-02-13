package org.playground.jmoney.jar;

import lombok.RequiredArgsConstructor;
import org.playground.jmoney.JMoneyEncryptionService;
import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JarMapper {

  private final JMoneyEncryptionService encryption;

  public Jar fromWebJar(WebJar webJar) {
    return Jar.builder()
            .jarid(decryptId(webJar.getId()))
            .createdAt(webJar.getCreatedAt())
            .name(webJar.getName())
            .percent(webJar.getPercent())
            .variant(JarType.fromName(webJar.getVariant()))
            .build();
  }

  public WebJar toWebJar(Jar jar) {
   return new WebJar()
           .createdAt(jar.getCreatedAt())
           .id(encryption.jasyptEncryptId(jar.getJarid()))
           .name(jar.getName())
           .percent(jar.getPercent())
           .variant(jar.getVariant().getName());
  }

  public Long decryptId(String id) {
    return encryption.jasyptDecryptId(id);
  }
}
