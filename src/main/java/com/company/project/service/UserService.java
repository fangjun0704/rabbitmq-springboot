package com.company.project.service;

import com.company.project.entity.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @RabbitListener(queues = "myqueue.a")
  public void receive(User user) {
    System.out.println("收到消息:::" + user);
  }

  @RabbitListener(queues = "myqueue.a")
  public void receiveHeader(Message message) {
    System.out.println("收到消息body:::" + message.getBody());
    System.out.println("收到消息header:::" + message.getMessageProperties().getHeaders());
  }
}
