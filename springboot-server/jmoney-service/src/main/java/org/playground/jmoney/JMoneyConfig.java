package org.playground.jmoney;

import java.util.Comparator;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableEncryptableProperties
public class JMoneyConfig {

  @Value("${jasypt.encryptor.password}")
  private String pwd;

  @SuppressWarnings("unused")
	@Autowired
  private StringEncryptor jasyptEncryptorBean;

  @Bean(name = "jasyptEncryptorBean")
  public StringEncryptor stringEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();

    config.setPassword(pwd);

    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
    encryptor.setConfig(config);

    return encryptor;
  }

  @SuppressWarnings("unused")
	@Autowired
  private PasswordEncoder bCryptPasswordEncoder;

  @Bean(name = "bCryptPasswordEncoder")
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Component
  public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

      log.info("Listing all registered endpoints:");

      ApplicationContext applicationContext = event.getApplicationContext();

      applicationContext.getBean(RequestMappingHandlerMapping.class)
              .getHandlerMethods()
              .entrySet()
              .stream()
              .sorted(Comparator.comparing(e -> e.getKey().getPatternsCondition().getPatterns().iterator().next()))
              .forEach(
                      entry -> {
                        var iterator = entry.getKey().getMethodsCondition().getMethods().iterator();
                        log.info("{}{} -> {}()", entry.getKey().getPatternsCondition().getPatterns(),
                                iterator.hasNext() ? iterator.next().name() : "",
                                entry.getValue().getMethod().getName());
                      }
              );
    }
  }
}
