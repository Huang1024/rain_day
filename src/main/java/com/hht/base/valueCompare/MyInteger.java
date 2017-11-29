package com.hht.base.valueCompare;

/**
 * Created by hht on 2017/11/29.
 */
public class MyInteger {

    public static void main(String[] args) {
        Integer a = 60;
        Integer b = Integer.valueOf(60);
        System.out.println(a == b);
        // true 上面那种方式其实隐含了装箱操作（Integer.valueOf）

        a = 200;
        b = Integer.valueOf(200);
        System.out.println(a == b);
        // false java中Integer类型对于-128-127之间的数是缓冲区取的，所以用等号比较是一致的。
        // 但对于不在这区间的数字是在堆中new出来的。所以地址空间不一样，也就不相等。

        a = new Integer(1);
        b = new Integer(1);
        System.out.println(a == b);
        // false 对象之间的比对，肯定是false
    }
}
