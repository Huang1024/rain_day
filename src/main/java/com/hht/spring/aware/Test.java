package com.hht.spring.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hht on 2017/11/29.
 */
@Configuration
@ComponentScan("com.hht.spring.aware")
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
        MySpringAware mySpringAware = context.getBean(MySpringAware.class);
        mySpringAware.outputResult();
        context.close();
    }

}
