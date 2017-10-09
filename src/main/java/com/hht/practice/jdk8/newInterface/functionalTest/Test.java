package com.hht.practice.jdk8.newInterface.functionalTest;

/**
 * practice FunctionalInterface
 *
 * Created by hht on 2017/10/9.
 */
@FunctionalInterface
public interface Test {

    String toStr(Integer a);

    //interface allow default method
    default void defaultMethod(){
        System.out.println("------>");
    }

    static void main(String[] args) {
        Test functional = integer -> integer + "~~~~~";
        Integer a = 123;
        System.out.println(functional.toStr(a));
        functional.defaultMethod();
    }
}
