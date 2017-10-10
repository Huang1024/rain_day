package com.hht.practice.jdk8.methodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Method References
 * Created by hht on 2017/10/9.
 */
public class Test {

    public static Test create( final Supplier< Test > supplier ) {
        return supplier.get();
    }

    public static void collide( final Test test) {
        System.out.println( "Collided " + test.toString() );
    }

    public void follow( final Test another ) {
        System.out.println( "Following the " + another.toString() );
    }

    public void repair() {
        System.out.println( "Repaired " + this.toString() );
    }

    public static int compare(Test test1, Test test2){
        System.out.println( "Compared the " + test1.toString() + " and the " + test2.toString());
        return 1;
    }

    public static void main(String[] args) {
        // 第一种方法引用的类型是"构造器引用"，语法是Class::new，或者更一般的形式：Class<T>::new。
        // 注意：这个构造器没有参数。
        final Test test = Test.create( Test::new );
        final List<Test> tests = Arrays.asList( test );

        // 第二种方法引用的类型是"静态方法引用"，语法是Class::static_method。
        // 注意：这个方法接受一个Car类型的参数。
        tests.forEach( Test::collide );

        // 第三种方法引用的类型是某个"类的成员方法的引用"，语法是Class::method。
        // 注意，这个方法没有定义入参：
        tests.forEach( Test::repair );

        // 第四种方法引用的类型是某个"实例对象的成员方法的引用"，语法是instance::method。
        // 注意：这个方法接受一个Car类型的参数：
        final Test police = Test.create( Test::new );
        tests.forEach( police::follow );

        // 两个入参的例子如下。。
        // 以上都是没有入参或者一个入参的方法，forEach会自动返回一个参数，所以以上方法适用，compare方法是两个入参
        final Test[] testArray = new Test[]{test};
        Arrays.sort(testArray, Test::compare);
    }

}
