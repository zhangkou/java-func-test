package com.test.sync;

import java.util.ArrayList;
import java.util.List;

public class SynchTest {
    public static void main(String[] args) {
        Object lock = new Object();
        List<String> list = new ArrayList<String>();

        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++){
                    list.add("abc");
                    System.out.println("thread A add element " + list.size());
                    try{
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if(list.size() == 5){
                        lock.notify();
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true){
                synchronized (lock){
                    if(list.size() != 5){
                        try{
                            lock.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("thread B get the lock, will start the business");
                    break;
                }
            }
        });

        threadB.start();
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        threadA.start();


    }
}
