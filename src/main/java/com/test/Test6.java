package com.test;

import java.lang.reflect.Field;

public class Test6 {
    public static void main(String[] args) throws Exception {
        getFieldTest();
    }
    public static void getFieldTest() throws Exception {
        Class<?> personClass = Class.forName("com.test.Person");
        Field[] personFields = personClass.getFields();
        System.out.println("Public Fields:");
        for (Field f : personFields) {
            System.out.println(f);
        }
        System.out.println("======================================================");

        Field[] declaredFields = personClass.getDeclaredFields();
        System.out.println("All Fields:");
        for (Field f : declaredFields) {
            System.out.println(f);
        }
        System.out.println("======================================================");

        Person person = (Person)personClass.newInstance();
        person.name = "hello";

        personClass.getDeclaredField("name").set(person, "name");

        personClass.getDeclaredField("type").set(person, "type");

        Field subTypeField = personClass.getDeclaredField("subType");
        subTypeField.setAccessible(true);
        subTypeField.set(person, "subType");

        Field genderField = personClass.getDeclaredField("gender");
        genderField.setAccessible(true);
        genderField.set(person, "gender");

        Field addressField = personClass.getDeclaredField("address");
        addressField.setAccessible(true);
        addressField.set(person, "address");

        System.out.println(person);
    }
}
