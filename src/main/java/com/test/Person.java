package com.test;

public class Person {
    public static   String type;
    private static  String subType;
    public          String name;
    protected       String gender;
    private         String address;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
