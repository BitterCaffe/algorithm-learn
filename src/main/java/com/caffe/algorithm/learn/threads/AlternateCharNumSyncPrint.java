package com.caffe.algorithm.learn.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 数字、字母交替打印
 */
public class AlternateCharNumSyncPrint {

    private int times;

    private AtomicInteger state;

    private Object monitor;

    public AlternateCharNumSyncPrint(int times) {
        this.times = times;
        state = new AtomicInteger(0);
        monitor = new Object();
    }


    private void printV1() throws InterruptedException {
        synchronized (monitor) {
            for (int i = 0; i < times; i++) {

                //奇数打印数字、偶数打印字符
                if (state.get() % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + (char) ('A' + i));
                    state.incrementAndGet();
                    monitor.notify();
                    monitor.wait();

                } else {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    state.incrementAndGet();
                    monitor.notify();
                    monitor.wait();
                }
            }
            monitor.notifyAll();
        }

    }


    public static void main(String[] args) {
        AlternateCharNumSyncPrint alternateCharNumSyncPrint = new AlternateCharNumSyncPrint(2);
        new Thread(() -> {
            try {
                alternateCharNumSyncPrint.printV1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a").start();

        new Thread(() -> {
            try {
                alternateCharNumSyncPrint.printV1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b").start();
    }
}
