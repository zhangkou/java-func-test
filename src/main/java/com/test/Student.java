package com.test;

public class Student implements AtHome, InSchool {
    private int age;
    private String name;

    public Student(){}

    public Student(int age){
        this.age = age;
    }

    public Student(String name){
        this.name = name;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void doHomeWork() {
        System.out.println("I am a student, I am doing homework at home");
    }

    @Override
    public void attendClasses() {
        System.out.println("I am a student, I am attend class in school");
    }
}
