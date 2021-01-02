package org.playground.jmoney.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class JMoneyWebConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final UserDetailsService jwtUserDetailsService;
  private final JwtRequestFilter jwtRequestFilter;
  private final GenericCorsFilter genericCorsFilter;
  private final PasswordEncoder bCryptPasswordEncoder;

  @Value("${springdoc.api-docs.path}")
  private String apiDocsPath;
//
//  @Value("${springdoc.swagger-ui.path}")
//  private String swaggerDocsPath;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // TODO see if we can use one encryptor by replacing the bCrypt with jasypt
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // TODO reenable csrf
    httpSecurity.csrf().disable()
    						.cors().disable()
		            // dont authenticate this particular request
		            .authorizeRequests()
		            	.antMatchers(HttpMethod.POST, 
		            							"/v1/auth/login", 
//		            							"/jmoney/api/v1/auth/login", 
//		            							"/jmoney/api/v1/auth/singup", 
		            							"/v1/auth/signup"
		            							).permitAll()
			            // all other requests need to be authenticated
		            .anyRequest().authenticated().and()
		            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    httpSecurity.addFilterBefore(genericCorsFilter, JwtRequestFilter.class);
  }
}