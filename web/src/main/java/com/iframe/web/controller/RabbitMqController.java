package com.iframe.web.controller;


import com.iframe.common.annotations.CheckToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iframe/rabbitMqController")
@Api(tags = "RabbitMqController", description = "消息队列设置")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @CheckToken(value = false)
    @ApiOperation(value="p2p直连型交换机", notes="p2p直连型交换机")
    @RequestMapping(value="/sendWork",method = RequestMethod.GET)
    public Object sendWork() {
        rabbitTemplate.convertAndSend("queue_1st","this is the test message");
        return "发送成功...";
    }


    @CheckToken(value = false)
    @ApiOperation(value="扇形交换机", notes="扇形交换机")
    @RequestMapping(value="/sendFontWork",method = RequestMethod.GET)
    public Object sendFontWork() {
        rabbitTemplate.convertAndSend("TestFanoutExchange","","this is the test 扇形 message ");
        return "发送成功...";
    }


}
