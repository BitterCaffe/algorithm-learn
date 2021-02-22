package com.caffe.algorithm.learn;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("ok ……");
        try {
            int[] arr = new int[1];
            int res = arr[2];
            int a = 1;
            int b = 0;
            int c = a / b;
        } catch (Exception e) {
            System.out.println("异常信息e.get:" + e.getMessage());
            System.out.println("异常信息e" + e);
        }
    }
}
