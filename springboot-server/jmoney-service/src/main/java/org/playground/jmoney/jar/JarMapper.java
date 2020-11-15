package org.playground.jmoney.jar;

import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Component;

@Component
public class JarMapper {

  public Jar fromWebJar(WebJar jarToCreate) {
    return Jar.builder()
            .createdAt(jarToCreate.getCreatedAt())
            .name(jarToCreate.getName())
            .percent(jarToCreate.getPercent())
            .variant(JarType.valueOf(jarToCreate.getVariant()))
            .build();
  }

  public WebJar toWebJar(Jar jar) {

//    WebJar jr = new WebJar();
//    jr.createdAt();

    return null;
  }
}
