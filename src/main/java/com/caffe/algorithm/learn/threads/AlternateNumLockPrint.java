package com.caffe.algorithm.learn.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caffe
 * 线程交替打印
 */
public class AlternateNumLockPrint {

    /**
     * 范围
     */
    private int times;
    /**
     * 循环次数
     */
    private volatile int count;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public AlternateNumLockPrint(int times) {
        this.times = times;
    }

    /**
     * 信息打印
     */
    private void print() {
        lock.lock();
        try {
            while (count < times) {
                System.out.println(String.format("线程%s打印%d", Thread.currentThread().getName(), ++count));
                condition.signalAll();
                condition.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 交替执行
     *
     * @param args
     */
    public static void main(String[] args) {

        AlternateNumLockPrint alternatePrint = new AlternateNumLockPrint(10);
        new Thread(() -> {
            alternatePrint.print();
        }, "A").start();

        new Thread(() -> {
            alternatePrint.print();
        }, "B").start();

    }


}
