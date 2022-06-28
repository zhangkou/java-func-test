package com.test.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        List<String> list = new ArrayList<String>();

        Thread threadA = new Thread(() ->{
            for(int i = 1; i <= 10; i++){
                list.add("abc");
                System.out.println("thread A add element, size = " + list.size());
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(list.size() == 5){
                    countDownLatch.countDown();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true){
                if(list.size() != 5){
                    try{
                        countDownLatch.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("thread B get notice, start run the business");
                break;
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
