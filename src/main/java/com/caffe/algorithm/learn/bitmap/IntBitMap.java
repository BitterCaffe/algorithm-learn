package com.caffe.algorithm.learn.bitmap;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description:
 */
public final class IntBitMap {
    /**
     * int字节数
     */
    private static final int BIT = 32;

    /**
     * 位移量
     */
    private static final int DIS = 5;
    int[] intArray;
    int maxValue;


    /**
     * private method
     *
     * @param maxValue
     */
    private IntBitMap(int maxValue) {
        this.maxValue = maxValue;
        int len = (maxValue >> DIS) + 1;
        intArray = new int[len];
    }

    /**
     * object build
     *
     * @param maxValue
     * @return
     */
    public static IntBitMap build(int maxValue) {
        return new IntBitMap(maxValue);
    }

    /**
     * 数据添加
     *
     * @param value
     */
    public void add(int value) {
        doAdd(getIndex(value), getOffset(value));
    }

    /**
     * 数据添加
     *
     * @param index
     * @param offset
     */
    private void doAdd(int index, int offset) {
        intArray[index] |= (1 << offset);
    }

    /**
     * 数据删除
     *
     * @param value
     */
    public void remove(int value) {
        doRemove(getIndex(value), getOffset(value));
    }

    /**
     * 数据删除，所说的数据删除就是将1->0
     *
     * @param index
     * @param offset
     */
    private void doRemove(int index, int offset) {
        //第一步：当前这个值取反即除了自己标识的位置其他位置都是1
        //第二步：因为第一步取反所以第二步计算时原来有值的位置也是1即1&1 = 1；
        // 原来没值的地方为0即0&1 =0、0&0=0 也不改变原来位置值；
        // 如果这个数据所对应位置有值1即1&0=0也就达到了删除功能。

        intArray[index] &= ~(1 << offset);
    }

    /**
     * 判断数据是否存在
     *
     * @param value
     * @return
     */
    public boolean exists(int value) {
        int index = getIndex(value);
        int offset = getOffset(value);
        //这里判断是否存在时原来的值和位移值做了一个& ，
        // 其实很好理解就是1<<offset这个值除了位移位置的值为1其他地方的值为0所以如果哪个位置有值即1则1&1=1否则0&1=0就是没值。
        return (intArray[index] & (1 << offset)) != 0;
    }

    /**
     * array sort
     *
     * @param maxValue
     * @param intArray
     * @return
     */
    public static int[] intArrSort(int maxValue, int[] intArray) {
        int len = (maxValue >> DIS) + 1;
        int[] intStory = new int[len];
        for (int item : intArray) {
            int index = getIndex(item);
            int offset = getOffset(item);
            intStory[index] |= (1 << offset);
        }
        int index = 0;
        int value = 0;
        for (int i = 0; i < intStory.length; i++) {
            int indexValue = intStory[i];
            for (int j = 0; j < BIT; j++) {
                int disValue = (indexValue & (1 << j));
                if (disValue != 0) {
                    value = i * BIT + j;
                    intArray[index] = value;
                    index++;
                }
            }
        }
        return intArray;
    }


    /**
     * 按照 value值获取数组中的索引位置
     *
     * @param value
     * @return
     */
    private static int getIndex(int value) {
        return value >> DIS;
    }

    private static int getIndex1(int value) {
        return value / 32;
    }

    /**
     * 求余数
     *
     * @param value
     * @return
     */
    private static int getOffset(int value) {
        return value % BIT;
    }

    private static int getOffset1(int value) {
        return value & 32;
    }
}
