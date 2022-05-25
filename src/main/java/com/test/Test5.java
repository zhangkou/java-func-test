package com.test;

public class Test5 {
    public static void main(String[] args) throws Exception {
        getSuperClassTest();
    }
    public static void getSuperClassTest() throws Exception {
        Class<?> dogClass = Class.forName("com.test.Dog");
        System.out.println(dogClass);
        dogClass.getMethod("doSomething").invoke(dogClass.newInstance(), null);

        Class<?> animalClass = dogClass.getSuperclass();
        System.out.println(animalClass);
        animalClass.getMethod("doSomething").invoke(animalClass.newInstance(), null);

        Class<?> objClass = animalClass.getSuperclass();
        System.out.println(objClass);
        objClass.getMethod("toString").invoke(objClass.newInstance(), null);
    }
}
