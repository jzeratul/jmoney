package org.playground.jmoney.jar;

import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Component;

@Component
public class JarMapper {

  public Jar fromWebJar(WebJar webJar) {
    return Jar.builder()
            .jarid(webJar.getId()) // todo add decryption
            .createdAt(webJar.getCreatedAt())
            .name(webJar.getName())
            .percent(webJar.getPercent())
            .variant(JarType.valueOf(webJar.getVariant()))
            .build();
  }

  public WebJar toWebJar(Jar jar) {
   return new WebJar()
           .createdAt(jar.getCreatedAt())
           .id(jar.getJarid()) // todo add encryption
           .name(jar.getName())
           .percent(jar.getPercent())
           .variant(jar.getVariant().getName());
  }
}
