package org.playground.jmoney.jar;

import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Component;

@Component
public class JarMapper {

  public Jar fromWebJar(WebJar webJar) {
    return Jar.builder()
            // todo add decryption
            .createdAt(webJar.getCreatedAt())
            .name(webJar.getName())
            .percent(webJar.getPercent())
            .variant(JarType.valueOf(webJar.getVariant()))
            .build();
  }

  public WebJar toWebJar(Jar jar) {

    // todo add encryption
//    WebJar jr = new WebJar();
//    jr.createdAt();

    return null;
  }
}
