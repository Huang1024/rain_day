package com.hht.practice.jdk8.methodReferences;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hht on 2017/10/10.
 */
public class Test2 {

    public static void main(String[] args) {
        final Test test = Test.create( Test::new );
        final List<Test> tests = Arrays.asList( test );
        tests.forEach( Test::repair );
    }
}
