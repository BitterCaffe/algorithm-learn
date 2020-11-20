package com.caffe.algorithm.learn.bitmap;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description: TODO
 */
public class IntBitMapTest {

    public static void main(String[] args) {
        exists();
        intSort();

    }


    /**
     * 添加、删除、存在判断
     */

    public static void exists() {
        IntBitMap intBitMap = IntBitMap.build(3);
        //判断是否存在
        boolean res = intBitMap.exists(1);
        System.out.println("1是否存在：" + res);
        //添加
        intBitMap.add(1);
        //判断是否存在
        res = intBitMap.exists(1);
        System.out.println("1是否存在：" + res);
        //删除
        intBitMap.remove(1);
        res = intBitMap.exists(1);
        System.out.println("1是否存在：" + res);
    }

    /**
     * 排序
     */
    public static void intSort() {
        int[] ints = {1, 2, 3, 5, 4, 7, 6, 9, 8};
        int[] res = IntBitMap.intArrSort(9, ints);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }
}
