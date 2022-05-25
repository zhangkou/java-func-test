package com.test.proxy;

public class BSender {
    public boolean send(){
        System.out.println("sending msg");
        return true;
    }
    public String echo(String info) {
        return info;
    }
}
