package org.playground.jmoney;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
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

@Configuration
//@ComponentScan(basePackages = "org.playground")
@EnableEncryptableProperties
public class JMoneyConfig {

  @Value("${jasypt.encryptor.password}")
  private String pwd;

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
      ApplicationContext applicationContext = event.getApplicationContext();
      applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
              .forEach(
                      (o, r) -> System.out.println(o.getName() + " " + r.toString())
              );
    }
  }
}
