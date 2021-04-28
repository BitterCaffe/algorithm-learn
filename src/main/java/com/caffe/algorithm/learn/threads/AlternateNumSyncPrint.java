package com.caffe.algorithm.learn.threads;

/**
 * @author Caffe
 * @date 2021/4/28
 * @description: 线程交替打印（线程打印奇偶数），其实是一样的他的考点应该是两个线程之间的通信，线程的交替执行。
 * 1、线程的交替执行
 * 2、成员变量和私有变量的存储位置、是否共享
 * 3、每个线程、线程栈中的栈帧局部变量表的slot中存储了局部变量。
 * 4、同一个方法每个线程执行都是该方法的栈帧结构的入栈和出栈。
 * <p>
 * 所以这里要注意局部变量表数据的情况
 */

public class AlternateNumSyncPrint {

    /**
     * 对象监视器锁
     */
    private Object lock = new Object();

    /**
     * 循环次数
     */
    private int times;

    /**
     * 累加值即交替打印值
     */
    private volatile int count;

    public AlternateNumSyncPrint(int times, int count) {
        this.times = times;
        this.count = count;
    }

    /**
     * 交替打印、奇数偶数打印，其实就是两个线程之间的通信
     */
    private void print() {
        //线程获取对象监视器锁
        synchronized (lock) {
            while (count < times) {
                try {
                    System.out.println(String.format("线程[%s]打印数字:%d", Thread.currentThread().getName(), count++));
                    //唤醒其他线程即从ObjectMonitor 的waitSet添加到EntryList中
                    lock.notify();
                    //将当前线程添加到ObjectMonitor 的waitSet集合中并释放锁
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //防止有子线程被阻塞未被唤醒，导致主线程不退出
            lock.notifyAll();
        }
    }


    public static void main(String[] args) {
        AlternateNumSyncPrint alternateNumSyncPrint = new AlternateNumSyncPrint(10, 1);

        new Thread(() -> {
            alternateNumSyncPrint.print();
        }, "A").start();

        new Thread(() -> {
            alternateNumSyncPrint.print();
        }, "B").start();


    }

}
