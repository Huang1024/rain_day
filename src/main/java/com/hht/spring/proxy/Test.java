package com.hht.spring.proxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hht on 2017/11/29.
 */
@Configuration
@ComponentScan("com.hht.spring.proxy")
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
        ProxyFactoryBean bean = context.getBean(ProxyFactoryBean.class);
        context.close();

        MyAdvice advice = new MyAdvice();

        System.out.println("-------MyObject-------");
        MyObject myObject = new MyObject();
        ObjectInterface object = (ObjectInterface)bean.getProxy(advice, myObject);
        object.print("HELLO");
        System.out.println("-------List-------");
        List<String> list = new ArrayList<>();
        List<String> listInterface = (List)bean.getProxy(advice, list);
        listInterface.size();
    }

}
