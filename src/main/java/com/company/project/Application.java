package com.company.project;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit//开启注解版本的RabbitMQ模式
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
