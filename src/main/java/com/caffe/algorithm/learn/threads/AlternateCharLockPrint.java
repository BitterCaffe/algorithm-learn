package com.caffe.algorithm.learn.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 多个线程交替打印，上面线程我们使用了双线程打印奇偶数，下面来看看多个数的打印
 */
public class AlternateCharLockPrint {

    /**
     * 循环次数
     */
    private int times;
    /**
     * 决策打印字符
     */
    private AtomicInteger state;
    /**
     * 重入锁
     */
    Lock lock;
    /**
     * 阻塞条件
     */
    Condition condition;

    public AlternateCharLockPrint(int times) {
        this.times = times;
        state = new AtomicInteger(0);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    /**
     * @param name      打印字符
     * @param targetNum
     */
    private void printV1(String name, int targetNum) {
        //这里大家可以注意一下i变量是线程私有变量，所以是线程安全的，
        // 存在线程安全问题的是成员变量即多线程共享的变量
        for (int i = 0; i < times; ) {
            lock.lock();
            if (state.get() % 3 == targetNum) {
                state.incrementAndGet();
                i++;
                System.out.print(name);
            }
            lock.unlock();
        }
    }

    /**
     * @param name
     * @param target
     */
    private void printV2(String name, int target) {
        for (int i = 0; i < times; i++) {
            lock.lock();
            try {
                while (!(state.get() % 3 == target)) {
                    condition.await();
                }
                System.out.print(name);
                state.incrementAndGet();
                condition.signalAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }

    }


    /**
     * 字符交替打印
     *
     * @param args
     */
    public static void main(String[] args) {
        AlternateCharLockPrint alternateCharLockPrint = new AlternateCharLockPrint(2);

        new Thread(() -> {
            alternateCharLockPrint.printV2("C", 2);
        }).start();

        new Thread(() -> {
            alternateCharLockPrint.printV2("A", 0);
        }).start();

        new Thread(() -> {
            alternateCharLockPrint.printV2("B", 1);
        }).start();
    }

}