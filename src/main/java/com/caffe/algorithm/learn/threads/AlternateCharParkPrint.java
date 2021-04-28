package com.caffe.algorithm.learn.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 字符交替打印
 */
public class AlternateCharParkPrint {

    static Thread threada, threadb, threadc;
    static int times = 3;

    public static void main(String[] args) {
        /**
         * 按照线程顺序打印
         */
        threada = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "A");
                LockSupport.unpark(threadb);
                LockSupport.park();
            }
        });

        threadb = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + " " + "B");
                LockSupport.unpark(threadc);

            }
        });

        threadc = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + " " + "C");
                LockSupport.unpark(threada);
            }
        });

        threada.start();
        threadb.start();
        threadc.start();
    }
}
