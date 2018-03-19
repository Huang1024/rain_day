package com.hht.spring.proxy;

public class MyObject implements ObjectInterface {

    private static final String name = "MyObject";

    @Override
    public void print(String tag) {
        System.out.println(
                new StringBuilder()
                        .append("This is class ")
                        .append(name)
                        .append(".It says ")
                        .append(tag)
                        .append(".")
        );
    }
}
