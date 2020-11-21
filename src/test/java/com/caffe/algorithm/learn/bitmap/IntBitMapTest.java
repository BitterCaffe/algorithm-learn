package com.caffe.algorithm.learn.bitmap;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description: TODO
 */
public class IntBitMapTest {

    public static void main(String[] args) {
//        exists();
        intSort();
//        repeat();

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
     * 重复判断
     */
    public static void repeat() {
        int[] intArray = {1, 2, 3, 4, 4, 5, 5, 6, 6};
        IntBitMap intBitMap = IntBitMap.build(6);
        for (int item : intArray) {
            boolean res = intBitMap.repeat(item);
            System.out.println(item + "是否重复res:" + res);
        }
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
        System.out.println("-------------------------");
        int[] intSort = {1, 3, 2, 5, 4, 7, 6, 9, 8};
        int[] sortRes = IntBitMap.intSort(9, intSort);
        for (int sortRe : sortRes) {
            System.out.println(sortRe);
        }


    }
}
