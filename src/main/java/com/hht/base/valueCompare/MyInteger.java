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

        String str = "123";
        String str2 = "123";
        String str3 = new String("123");
        System.out.println(str == str2);
        System.out.println(str == str3);
        //str创建之后，缓冲区有了"123"的变量，str2创建的时候，发现缓冲区有了"123"的字符串，直接赋予了上去
        //str3是创建的新的对象，用等于比较，肯定是false的s
    }
}
