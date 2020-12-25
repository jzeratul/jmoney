package org.playground.jmoney.jar;

import lombok.RequiredArgsConstructor;
import org.playground.jmoney.JasyptEncryptionService;
import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JarMapper {

  private final JasyptEncryptionService util;

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
           .id(util.encryptId(jar.getJarid()))
           .name(jar.getName())
           .percent(jar.getPercent())
           .variant(jar.getVariant().getName());
  }

  public Long decryptId(String id) {
    return util.decryptId(id);
  }
}
