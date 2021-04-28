package com.caffe.algorithm.learn.arrays;

/**
 * @author BitterCaffe
 * @date 2021/2/26
 * @description: TODO
 */
public class ArrayTest {

    public static void main(String[] args) throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        for (int i = 0; i < 10; i++) {
            arrayUtil.add(i);
            System.out.println(arrayUtil);
        }
        System.out.println("size=" + arrayUtil.getSize());
        arrayUtil.remove(1);
        System.out.println("size=" + arrayUtil.getSize());
    }
}
