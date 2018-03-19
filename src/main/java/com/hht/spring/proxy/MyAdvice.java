package com.hht.spring.proxy;

import org.springframework.stereotype.Component;

@Component
public class MyAdvice implements Advice{

    @Override
    public void beforeMethod() {
        System.out.println("beforeMethod!!!");
    }

    @Override
    public void afterMethod() {
        System.out.println("afterMethod!!!");
    }
}
