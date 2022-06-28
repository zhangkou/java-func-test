package com.test.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        final Thread threadB = new Thread(() -> {
            if(list.size() != 5){
                LockSupport.park();
            }
            System.out.println("thread b get sinal, start the business");
        });
        Thread threadA = new Thread(() -> {
            for(int i = 1; i <= 10; i++){
                list.add("abc");
                System.out.println("thread A add element, size = " + list.size());
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(list.size() == 5){
                    LockSupport.unpark(threadB);
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
