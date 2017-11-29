package com.hht.practice.activeMq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;


/**
 * Created by hht on 2017/10/31.
 */
//@EnableJms
@Configuration
public class QueueBean {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }

}
