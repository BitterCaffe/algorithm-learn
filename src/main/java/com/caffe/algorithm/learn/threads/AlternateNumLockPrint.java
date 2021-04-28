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
    /**
     * 基于AQS的锁，AQS底层是基于LockSupport#park#unpark
     */
    Lock lock = new ReentrantLock();
    /**
     * 条件队列
     */
    Condition condition = lock.newCondition();

    public AlternateNumLockPrint(int times) {
        this.times = times;
    }

    /**
     * 信息交互打印（奇数偶数）、也是线程间的通信
     */
    private void print() {
        lock.lock();
        try {
            while (count < times) {
                System.out.println(String.format("线程%s打印%d", Thread.currentThread().getName(), ++count));
                condition.signal();
                condition.await();
            }
            condition.signalAll();
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
