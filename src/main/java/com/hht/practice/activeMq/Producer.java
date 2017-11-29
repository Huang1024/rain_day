package com.hht.practice.activeMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.io.*;

/**
 * Created by hht on 2017/10/31.
 */
@Component
public class Producer implements Serializable {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000)
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(this.queue, "hi,activeMQ");
    }

}
