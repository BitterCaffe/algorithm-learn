package com.caffe.algorithm.learn.treeall;

import com.caffe.algorithm.learn.tree.SolutionInc;

/**
 * @author BitterCaffe
 * @date 2021/2/28
 * @description: TODO
 */
public class SolutionTest {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        boolean res = SolutionInc.inc(arr);
        System.out.println("res=" + res);
    }
}
