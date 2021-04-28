package com.caffe.algorithm.learn.threads;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 奇偶数交替打印
 */

public class AlternateNumSyncPrint {

    /**
     * 锁
     */
    private Object monitor = new Object();

    /**
     * 循环次数
     */
    private int limit;

    /**
     * 值
     */
    private volatile int count;

    public AlternateNumSyncPrint(int limit, int count) {
        this.limit = limit;
        this.count = count;
    }

    /**
     * 交替打印
     */
    private void print() {
        synchronized (monitor) {
            while (count < limit) {
                try {
                    System.out.println(String.format("线程[%s]打印数字:%d", Thread.currentThread().getName(), ++count));
                    monitor.notifyAll();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //防止有子线程被阻塞未被唤醒，导致主线程不退出
            monitor.notifyAll();
        }
    }


    public static void main(String[] args) {
        AlternateNumSyncPrint alternateNumSyncPrint = new AlternateNumSyncPrint(10, 0);

        new Thread(() -> {
            alternateNumSyncPrint.print();
        }, "B").start();

        new Thread(() -> {
            alternateNumSyncPrint.print();
        }, "A").start();
    }

}
