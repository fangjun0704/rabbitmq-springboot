package com.company.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAMQPConfig {

  @Bean
  public MessageConverter messageConverter() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDateFormat((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));

    return new Jackson2JsonMessageConverter(mapper);
  }

}
