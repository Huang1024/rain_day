package com.hht.base.polymorphism;

/**
 * Created by hht on 2017/11/28.
 */
public class Circle extends Shape{

    @Override
    void draw() {
        System.out.println("I'm a circle");
    }

    public static void doSomething(Shape shape){
        shape.draw();
    }

    public static void main(String[] args) {
        doSomething(new Circle());
        doSomething(new Line());
    }
}
