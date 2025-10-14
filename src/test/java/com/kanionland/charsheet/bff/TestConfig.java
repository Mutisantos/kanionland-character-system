package com.kanionland.charsheet.bff;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
@EnableFeignClients(basePackages = "com.kanionland.charsheet.bff.infrastructure.clients")
public class TestConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}