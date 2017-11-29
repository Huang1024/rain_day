package com.hht.practice.activeMq;

import org.springframework.stereotype.Component;

/**
 * Created by hht on 2017/10/31.
 */
@Component
public class Consumer {

//    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String text) {
        try {
            System.out.println("------------start----------");
            Thread.sleep(5000L);
            System.out.println(text);
            System.out.println("------------end----------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
