package com.caffe.algorithm.learn.tree;

/**
 * @author BitterCaffe
 * @date 2021/2/28
 * @description: 单调数列
 */
public class SolutionInc {

    /**
     * 单调递增
     *
     * @param arr
     * @return
     */
    public static boolean inc(int[] arr) {
        boolean incFlag = true;
        boolean descFlag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                incFlag = false;
            } else if (arr[i] >= arr[i + 1]) {
                descFlag = false;
            }
        }
        //只有两个都是false则为false,这巧妙的使用了或运算符来解决递增问题
        return incFlag || descFlag;
    }

}
