package com.test;

public class Test3 {
    public static void main(String[] args) throws Exception {
        getClassTest();
    }
    public static void getClassTest() throws ClassNotFoundException {
        Person person = new Person();
        System.out.println(person.getClass());
        System.out.println(person.getClass().getSimpleName());
        System.out.println(person.getClass().getName());
        System.out.println(person.getClass().getPackage());

        Class<?> cp1 = Class.forName("com.test.Person");
        Class<?> cp2 = new Person().getClass();
        Class<?> cp3 = Person.class;
        System.out.println(cp1);
        System.out.println(cp2);
        System.out.println(cp3);
    }
}
