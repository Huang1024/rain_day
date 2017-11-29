package com.hht.practice.serialize;

import com.hht.practice.activeMq.Producer;

import java.io.*;

/**
 * Created by hht on 2017/11/14.
 */
public class SerializeTest implements Serializable{

    private static final long serialVersionUID = 5719904071171271898L;

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private static void SerializePerson() throws Exception{
        SerializeTest person = new SerializeTest();
        person.setAge(25);
        person.setName("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(new FileOutputStream(
                    new File("/Users/hht/Desktop/test.txt")));
            oo.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oo.close();
        }
        System.out.println("Person对象序列化成功！");
    }

    private static void DeserializePerson() throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(
                    new File("/Users/hht/Desktop/test.txt")));
            SerializeTest person = (SerializeTest) ois.readObject();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println("Person对象反序列化成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
    }

    public static void main(String[] args) {
        try {
//            SerializeTest.SerializePerson();
            SerializeTest.DeserializePerson();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
