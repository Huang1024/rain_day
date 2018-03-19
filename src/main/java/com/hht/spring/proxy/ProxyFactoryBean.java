package com.hht.spring.proxy;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class ProxyFactoryBean<T> {

    public Object getProxy(Advice advice, T object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                (Object objectProxy, Method method, Object[] args) -> {
                    advice.beforeMethod();
                    Object reVal = method.invoke(object, args);
                    System.out.println(reVal);
                    advice.afterMethod();
                    return reVal;
                });
    }

}
