package com.iframe.web.config;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqListener {

    @RabbitListener(queues = "queue_1st")
    public void receiveMessage(String msg, Channel channel, Message message) {
        // 只包含发送的消息
        System.out.println("接收到消息1：" + msg);
        // channel 通道信息
        // message 附加的参数信息
    }

    @RabbitListener(queues = "queue_2nd")
    public void receiveMessage2(String msg, Channel channel, Message message) {
        // 包含所有的信息
        System.out.println("接收到消息2：" + msg);
    }

    @RabbitListener(queues = "queue_3rd")
    public void receiveMessage3(String msg, Channel channel, Message message) {
        // 包含所有的信息
        System.out.println("接收到消息3：" + msg);
    }


}
