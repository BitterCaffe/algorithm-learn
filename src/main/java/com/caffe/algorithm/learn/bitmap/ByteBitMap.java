package com.caffe.algorithm.learn.bitmap;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description: 使用byte数组来存储、判重、排序一个大文件中的数据
 * 如果使用byte数组来存储数据则1byte=8bit能存储8个数字。最大值=数组.length*8 +8这样的一个大小值。
 * 如果使用int数组来存储数据则1int= 4*8=32bit即能存储32个数字。最大值=数组.length*32+32这样的一个大小值。
 */
public final class ByteBitMap {

    private static final int BIT = 8;
    byte[] bitArray;

    int maxValue;

    /**
     * construct build object
     *
     * @param maxValue
     */
    private ByteBitMap(int maxValue) {

        this.maxValue = maxValue;
        int len = (maxValue / 8) + 1;
        bitArray = new byte[len];
    }

    /**
     * build method
     *
     * @param maxValue
     * @return
     * @throws Exception
     */
    public static  ByteBitMap build(int maxValue) {
        return new ByteBitMap(maxValue);
    }


    /**
     * @param value
     */
    public  void add(int value) {
        int index = getIndex(value);
        int offset = getOffset(value);
        bitArray[index] |= (1 << offset);
    }

    /**
     * judge value is exists
     *
     * @param value
     * @return
     */
    public  boolean exists(int value) {
        int index = getIndex(value);
        int offset = getOffset(value);
        return (bitArray[index] & (1 << offset)) != 0;
    }

    /**
     * int数组排序
     *
     * @param intArray
     * @return
     */
    public static int[] intArraySort(int[] intArray, int maxValue) {
        int len = (maxValue / BIT) + 1;
        byte[] byteArray = new byte[len];
        //将intArray数组保存到byteArray保存后就是有序的，只需要遍历取出就OK
        for (int i : intArray) {
            byteArray[getIndex(i)] |= (1 << getOffset(i));
        }
        int index = 0;
        int value = 0;
        for (int i = 0; i < byteArray.length; i++) {
            byte indexValue = byteArray[i];
            for (int j = 0; j < BIT; j++) {
                byte offsetValue = (byte) (indexValue & (1 << j));
                if (offsetValue != 0) {
                    value = i * BIT + j;
                    intArray[index] = value;
                    index++;
                }

            }
        }
        return intArray;
    }

    /**
     * 删除数据
     *
     * @param value
     */
    public  void remove(int value) {
        int index = getIndex(value);
        int offset = getOffset(value);
        bitArray[index] &= ~(1 << offset);
    }

    /**
     * 判重，如果重复则返回重复，否则添加并返回不重复
     *
     * @param value
     * @return
     */
    public  boolean repeat(int value) {
        if (exists(value)) {
            return true;
        }
        add(value);
        return false;
    }

    /**
     * byte数组索引下标位置确认
     *
     * @param value
     * @return
     */
    private static  int getIndex(int value) {
        return value / BIT;
    }

    /**
     * 一bit种offset位置确定
     *
     * @param value
     * @return
     */
    private static  int getOffset(int value) {
        return value % BIT;
    }
}
