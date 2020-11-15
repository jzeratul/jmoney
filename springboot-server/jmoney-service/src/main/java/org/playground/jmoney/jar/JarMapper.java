package org.playground.jmoney.jar;

import org.playground.jmoney.JMoneyUtil;
import org.playground.jmoney.model.WebJar;
import org.springframework.stereotype.Component;

@Component
public class JarMapper {

  private JMoneyUtil util;

  public Jar fromWebJar(WebJar webJar) {
    return Jar.builder()
            .jarid(util.decrypt(webJar.getId()))
            .createdAt(webJar.getCreatedAt())
            .name(webJar.getName())
            .percent(webJar.getPercent())
            .variant(JarType.valueOf(webJar.getVariant()))
            .build();
  }

  public WebJar toWebJar(Jar jar) {
   return new WebJar()
           .createdAt(jar.getCreatedAt())
           .id(util.encrypt(jar.getJarid()))
           .name(jar.getName())
           .percent(jar.getPercent())
           .variant(jar.getVariant().getName());
  }
}
