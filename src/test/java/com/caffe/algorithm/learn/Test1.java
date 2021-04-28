package com.caffe.algorithm.learn;

import java.util.concurrent.CountDownLatch;

/**
 * @author BitterCaffe
 * @date 2021/2/23
 * @description: TODO
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        System.out.println("begin");
        countDownLatch.await();
    }

}
