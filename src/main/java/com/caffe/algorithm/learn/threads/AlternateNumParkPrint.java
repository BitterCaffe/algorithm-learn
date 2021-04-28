package com.caffe.algorithm.learn.threads;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 数字交替打印
 */
public class AlternateNumParkPrint {

    static Thread thread1, thread2;
    static int times = 10;
    static int count = 1;


    public static void main(String[] args) {

        thread1 = new Thread(() -> {
            while (count < times) {
                System.out.println(Thread.currentThread().getName() + " " + count++);
                LockSupport.unpark(thread2);
                LockSupport.park();
            }
        });

        thread2 = new Thread(() -> {
            while (count < times) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + " " + count++);
                LockSupport.unpark(thread1);
            }
        });

        thread2.start();
        thread1.start();
    }


}
