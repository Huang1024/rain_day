package com.hht.practice.jdk8.newInterface.functionalTest;

import java.util.Arrays;
import java.util.List;

/**
 * practice FunctionalInterface
 *
 * Created by hht on 2017/10/9.
 */
public interface Test {

    String toStr(Integer a);

    static String toStr1(Integer b){
        return b.toString();
    }

    //interface allow default method
    default void defaultMethod(){
        System.out.println("------>");
    }

    static void main(String[] args) {
        Test functional = integer -> integer + "~~~~~";
        Integer a = 123;
        System.out.println(functional.toStr(a));
        functional.defaultMethod();

        List<Integer> list = Arrays.asList(1,2,3,4);
        list.forEach(Test::toStr1);
    }
}
