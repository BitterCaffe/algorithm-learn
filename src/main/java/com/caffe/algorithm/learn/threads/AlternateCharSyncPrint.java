package com.caffe.algorithm.learn.threads;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 字母交替打印
 */
public class AlternateCharSyncPrint {

    /**
     * 重复次数
     */
    private int times;

    /**
     * 状态
     */
    private int state;

    /**
     * 锁
     */
    private Object lock;

    /**
     * 数据打印
     *
     * @param times
     */
    public AlternateCharSyncPrint(int times) {
        this.times = times;
        state = 0;
        lock = new Object();
    }


    /**
     * print
     *
     * @param name
     * @param target
     * @throws InterruptedException
     */
    public void printV1(String name, int target) throws InterruptedException {
        for (int i = 0; i < times; ) {
            synchronized (lock) {
                if ((state % 3 == target)) {
                    System.out.print(name);
                    i++;
                    state++;
                }
            }
        }
    }


    /**
     * 重新打印
     *
     * @param name
     * @param target
     */
    public void printV2(String name, int target) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            synchronized (lock) {
                while (!(state % 3 == target)) {
                    lock.wait();
                }
                System.out.print(name);
                state++;
                lock.notifyAll();
            }
        }
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        AlternateCharSyncPrint alternateCharSyncPrint = new AlternateCharSyncPrint(3);
        new Thread(() -> {
            try {
                alternateCharSyncPrint.printV2("A", 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                alternateCharSyncPrint.printV2("B", 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                alternateCharSyncPrint.printV2("C", 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();


    }

}
