package com.hht.practice.jdk8.optional;

import java.util.Optional;

/**
 * Created by hht on 2017/10/11.
 */
public class Test {

    public static void main(String[] args) {

        Optional< String > fullName = Optional.ofNullable( null );
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
        //此处的map只是将optional对象转换为新的对象，见源码
        System.out.println( fullName.map( s -> "Hey " + s + "!" ));
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

        System.out.println("------------------------------------------------------------------------");

        Optional< String[] > fullNames = Optional.ofNullable( new String[]{"Tom", null, "Tony"} );
        System.out.println( fullNames.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

        System.out.println("------------------------------------------------------------------------");

    }

}
