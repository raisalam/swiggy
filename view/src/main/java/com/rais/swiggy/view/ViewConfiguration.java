package com.rais.swiggy.view;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rais.swiggy.view.service.ViewService;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class ViewConfiguration {

  @Bean
  public ViewService viewService() {
    return new ViewService();
  }

}
