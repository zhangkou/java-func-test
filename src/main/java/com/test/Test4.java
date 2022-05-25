package com.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test4 {
    public static void main(String[] args) throws Exception {
        createInstanceByClass();
        getConstructTest();
        runInterfaceMethodTest();
    }
    public static void createInstanceByClass(){
        try {
            Class<?> studentClass = Class.forName("com.test.Student");
            Student student = (Student) studentClass.newInstance();
            student.setAge(10);
            student.setName("test");
            System.out.println(student);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void getConstructTest() throws ClassNotFoundException {
        Class<?> studentClass = Class.forName("com.test.Student");
        Constructor<?>[] studentConstructors = studentClass.getConstructors();
        for (Constructor<?> c : studentConstructors) {
            System.out.println(c);
            System.out.println("name " + c.getName());
            System.out.println("modifer " + c.getModifiers());
            System.out.println("paramCount " + c.getParameterCount());
            System.out.println("construc params: ");
            for (Class<?> t : c.getParameterTypes()) {
                System.out.println(t.getName() + "");
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }
    public static void runInterfaceMethodTest() throws Exception {
        Class<?> studentClass = Class.forName("com.test.Student");
        Class<?>[] stuInterfaces = studentClass.getInterfaces();
        for (Class<?> stuInterface : stuInterfaces) {
            System.out.println(stuInterface);
            Method[] methods = stuInterface.getMethods();
            for (Method method : methods) {
                Student student = (Student) studentClass.newInstance();
                method.invoke(student, null);
            }
        }
    }
}
