package com.hht.practice.singleton;

import lombok.Data;

/**
 * 序列化
 * Created by hht on 2017/11/23.
 */
@Data
public class Singleton {

    private Integer FLAG;

    public Singleton(Integer flag) {
        this.FLAG = flag;
    }

    public void outFLAG() {
        System.out.println(this + "," + this.FLAG);
    }


    public static void main(String[] args) {

        Runnable runnable = () -> {
            for (int j = 0; j < 100; j++) {
                Singleton singleton = new Singleton(j);
                singleton.outFLAG();
            }
        };
        Runnable runnable1 = () -> {
            for (int j = 0; j < 100; j++) {
                Singleton singleton = new Singleton(j);
                singleton.outFLAG();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable1).start();
    }

}
