package com.test.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<String> list = new ArrayList<String>();
        Thread threadA = new Thread(() -> {
            lock.lock();
            for(int i = 1; i <= 10; i++){
                list.add("abc");
                System.out.println("Thread A add element, size = " + list.size());
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(list.size() == 5){
                    condition.signal();
                }
            }
            lock.unlock();
        });
        Thread threadB = new Thread(() -> {
            lock.lock();
            if(list.size() != 5){
                try {
                    condition.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("thread b get notice, start the business");
            lock.unlock();
        });
        threadB.start();
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        threadA.start();
    }
}
