package com.hht.practice.jdk8.methodReferences;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

/**
 * Created by hht on 2017/10/9.
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public Sex getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(SOURCE sourceCollections, Supplier<DEST> collectionFactory) {
        DEST result = collectionFactory.get();
        for (T t : sourceCollections) {
            result.add(t);
        }
        return result;
    }


    public static void main(String[] args) {
        // 1.引用静态方法
        Person[] persons = new Person[10];

        // 使用匿名类
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.birthday.compareTo(o2.birthday);
            }
        });

        // 使用lambda表达式
        Arrays.sort(persons, (o1, o2) -> o1.birthday.compareTo(o2.birthday));

        // 使用lambda表达式和类的静态方法
        Arrays.sort(persons, (o1, o2) -> Person.compareByAge(o1, o2));


        // 使用方法引用
        // 引用的是类的静态方法
        Arrays.sort(persons, Person::compareByAge);



        // 2.引用对象的实例方法
        class ComparisonProvider {
            public int compareByName(Person a, Person b) {
                return a.getName().compareTo(b.getName());
            }

            public int compareByAge(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        ComparisonProvider provider = new ComparisonProvider();

        //使用lambda表达式
        //对象的实例方法
        Arrays.sort(persons, (a, b) -> provider.compareByAge(a, b));

        //使用方法引用
        //引用的是对象的实例方法
        Arrays.sort(persons, provider::compareByAge);



        // 3.引用类型对象的实例方法
        String[] stringsArray = {"Hello", "World"};

        //使用lambda表达式和类型对象的实例方法
        Arrays.sort(stringsArray, (s1, s2) -> s1.compareToIgnoreCase(s2));

        //使用方法引用
        //引用的是类型对象的实例方法
        Arrays.sort(stringsArray, String::compareToIgnoreCase);



        // 4.引用构造方法
        final List<Person> personList = Arrays.asList(persons);

        //使用lambda表达式
        Set<Person> personSet = transferElements(personList, () -> new HashSet<>());

        //使用方法引用
        //引用的是构造方法
        Set<Person> personSet2 = transferElements(personList, HashSet::new);

    }

}
