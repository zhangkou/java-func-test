package com.test;

import java.io.PrintStream;
import java.util.function.Function;

public class Test2 {
    public static void main(String[] args) {
        Function<String, String> fn = parameter -> parameter + " from lambda";
        fnTest("Hello", fn);

        FunInterfaceTest("aaa", new Foo() {
            @Override
            public String method(String string) {
                return "I'm " + string;
            }
        });

        FunInterfaceTest("bb", param -> "param: " + param);
    }
    public static void fnTest(String info, Function<String, String> fn){
        String fnResult = fn.apply(info);
        System.out.println(fnResult);
    }

    public static void FunInterfaceTest(String info, Foo foo){
        System.out.println(foo.method(info));
    }

    @FunctionalInterface
    public interface Foo {
        String method(String string);
        default void defaultMethod() {}
        default void xxx(){}
        static void ooo(){}
    }
}
