package com.company.project;

import com.company.project.entity.User;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  @Autowired
  RabbitTemplate rabbitTemplate;

  @Autowired
  AmqpAdmin amqpAdmin; //RabbitMQ 系统管理功能组建。创建删除 Queue,Exchange,Binding

  @Test
  public void amqpAdminTest() {
    Exchange exchange = new DirectExchange("amqpAdmin.exchange");
    amqpAdmin.declareExchange(exchange);//创建一个exchange

    Queue queue = new Queue("amqpAdmin.queue");
    amqpAdmin.declareQueue(queue);//创建一个queue

    Binding binding = new Binding("amqpAdmin.queue", DestinationType.QUEUE,
        "amqpAdmin.exchange", "amqpAdmin.routKey", null);
    amqpAdmin.declareBinding(binding);


  }

  /**
   * 点对点(direct)发送数据
   */
  @Test
  public void directSendTest() {
    //    Map<String,Object> messageMap = new HashMap<>();
    //    messageMap.put("msg","这是点对点消息-1");
    //    messageMap.put("data", Arrays.asList("测试数据",123,true));

    User user = User.builder().uId(1).name("测试姓名").age(4).testField("testField").studentIs(true)
        .birthday(new Date()).build();
    //发送消息
    //对象被默认序列化（application/x-java-serialized-object）后发送到队列.可以更改默认序列化(自定义 MessageConverter)
    rabbitTemplate.convertAndSend("exchange.direct", "direct.a", user);
  }

  /**
   * 点对点(direct)接收数据
   */
  @Test
  public void directReceiveTest() {
    //接收消息
    Object message = rabbitTemplate.receiveAndConvert("myqueue.a");
    System.out.println(message.getClass());
    System.out.println(message);
  }

}
