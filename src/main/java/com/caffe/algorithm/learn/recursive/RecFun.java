package com.caffe.algorithm.learn.recursive;

/**
 * @author BitterCaffe
 * @date 2020/12/7
 * @description: TODO
 */
public class RecFun {

    /**
     * 递归需要有返回条件，如果没有返回条件容易出现stack异常
     *
     * @param n
     * @return
     */
    public int fun(int n) {
        if (n == 1) {
            return 1;
        }
        return fun(n - 1) + 1;
    }

    public static void main(String[] args) {
        RecFun recFun = new RecFun();
        int res = recFun.fun(3);
        System.out.println("res=" + res);
    }
}
