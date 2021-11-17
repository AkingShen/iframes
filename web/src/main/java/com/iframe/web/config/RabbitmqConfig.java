package com.iframe.web.config;


import com.iframe.common.utils.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@PropertySource("classpath:application.properties")
@Slf4j
@SpringBootConfiguration
public class RabbitmqConfig {


    @Bean
    public Queue queueWork1() {
        return new Queue("queue_1st",true);
    }

    @Bean
    public Queue queueWork2() {
        return new Queue("queue_2nd",true);
    }

    @Bean
    public Queue queueWork3() {
        return new Queue("queue_3rd",true);
    }

    //定义交换机
    @Bean
    FanoutExchange TestFanoutExchange() {
        return new FanoutExchange("TestFanoutExchange");
    }

    //绑定交换机
    @Bean
    Binding bindingFanout1() {
        return BindingBuilder.bind(queueWork1()).to(TestFanoutExchange());
    }

    @Bean
    Binding bindingFanout2() {
        return BindingBuilder.bind(queueWork2()).to(TestFanoutExchange());
    }

    @Bean
    Binding bindingFanout3() {
        return BindingBuilder.bind(queueWork3()).to(TestFanoutExchange());
    }




}
