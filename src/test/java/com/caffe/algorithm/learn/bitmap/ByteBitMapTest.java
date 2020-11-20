package com.caffe.algorithm.learn.bitmap;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description: TODO
 */
public class ByteBitMapTest {
    public static void main(String[] args) {
//        exists();
//        remove();
//        repeat();
        sort();
    }


    /**
     * 判断是否存在
     */
    private static void exists() {
        ByteBitMap byteBitMap = ByteBitMap.build(8);
        boolean res = byteBitMap.exists(1);
        System.out.println("1是否存在:" + res);
        byteBitMap.add(1);
        res = byteBitMap.exists(1);
        System.out.println("1是否存在:" + res);


    }

    /**
     * 删除
     */
    public static void remove() {
        ByteBitMap byteBitMap = ByteBitMap.build(10);
        byteBitMap.add(1);
        boolean res = byteBitMap.exists(1);
        System.out.println("1是否存在:" + res);
        byteBitMap.remove(1);
        res = byteBitMap.exists(1);
        System.out.println("1是否存在:" + res);
    }

    /**
     * 测试重复
     */
    public static void repeat() {
        int[] ints = {1, 2, 3, 4, 5, 6, 6, 7, 7, 8, 8};
        ByteBitMap byteBitMap = ByteBitMap.build(8);
        for (int anInt : ints) {
            boolean res = byteBitMap.repeat(anInt);
            if (res) {
                System.out.println("value" + anInt + "重复" + res);
            }
        }
    }

    /**
     * 排序测试
     */
    public static void sort() {
        int[] ints = {1, 3, 2, 4, 6, 5, 7, 9, 8};
        int[] res = ByteBitMap.intArraySort(ints, 9);
        for (int anInt : res) {
            System.out.println(anInt);
        }
    }
}
