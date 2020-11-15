package org.playground.jmoney;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
//@ComponentScan(basePackages = "org.playground")
@EnableEncryptableProperties
public class JMoneyConfig {

  @Component
  public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
      ApplicationContext applicationContext = event.getApplicationContext();
      applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
              .forEach(
                      (o, r) -> System.out.println(o.getName() + " " + r.toString())
              );
    }
  }
}
