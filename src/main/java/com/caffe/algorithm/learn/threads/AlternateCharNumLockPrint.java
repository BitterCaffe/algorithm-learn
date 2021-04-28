package com.caffe.algorithm.learn.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 数字、字符交替打印
 */
public class AlternateCharNumLockPrint {

    /**
     * 总次数
     */
    private int times;

    /**
     * 判断打印的数字、字符
     */
    private AtomicInteger state;

    /**
     * 锁
     */
    ReentrantLock lock;

    /**
     * 条件
     */
    Condition condition;

    public AlternateCharNumLockPrint(int times) {
        this.times = times;
        state = new AtomicInteger(0);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }


    /**
     * 交替打印
     */
    private void printV1() {
        lock.lock();
        try {
            for (int i = 0; i < times; i++) {
                if (state.get() % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + ((char) ('A' + i)));
                    state.incrementAndGet();
                    condition.signal();
                    condition.await();
                } else {
                    System.out.println(Thread.currentThread().getName() + " " + (i + 1));
                    condition.signal();
                    state.incrementAndGet();
                    condition.await();
                }
            }
            condition.signalAll();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        AlternateCharNumLockPrint alternateCharNumLockPrint = new AlternateCharNumLockPrint(2);
        new Thread(() -> {
            alternateCharNumLockPrint.printV1();
        }, "线程1").start();

        new Thread(() -> {
            alternateCharNumLockPrint.printV1();
        }, "线程2").start();
    }
}
