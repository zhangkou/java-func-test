package com.test.sync;

import java.util.ArrayList;
import java.util.List;

public class VolatileTest {
    static volatile boolean notice = false;

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(() -> {
            for(int i = 0; i < 10; i++){
                list.add("abc");
                System.out.println("thread a add element, size = " + list.size());
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(list.size() == 5){
                    notice = true;
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true){
                if(notice){
                    System.out.println("thread b get notice, now start the business");
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
