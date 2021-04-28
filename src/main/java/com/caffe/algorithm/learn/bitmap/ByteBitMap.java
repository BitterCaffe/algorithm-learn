package com.caffe.algorithm.learn.bitmap;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description: 使用byte数组来存储、判重、排序一个大文件中的数据
 * 如果使用byte数组来存储数据则1byte=8bit能存储8个数字。最大值=数组.length*8 +8这样的一个大小值。
 * 如果使用int数组来存储数据则1int= 4*8=32bit即能存储32个数字。最大值=数组.length*32+32这样的一个大小值。
 * 为何使用bit来存储能花费更少空间，因为在这里使用bit位来存储数据，而且数据比较大因为具体数据存储还是用了计算规则
 * <p>
 * 数值/数组数据类型大小 是用来确定数值在数组中的索引位置，这样一来就确定了整除部分
 * 数值%数组数据类型大小 是用来确定余数部分，其实在数组中具体存储的也是余数，整数部分是不会被存储的因为整数部分可以反向计算
 * 注意：余数都会小于数组中类型的字节数，如果是byte数组则小于8 也就是0-7，如果是int数组则小于32即0-31所以数据都能被存储
 *
 * bitmap在操作系统以及其他中间件中使用的也是比较多，他的好处就是占用内存少。
 *
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
    public static ByteBitMap build(int maxValue) {
        return new ByteBitMap(maxValue);
    }


    /**
     * @param value
     */
    public void add(int value) {
        int index = getIndex(value);
        int offset = getOffset(value);
        //& 只有两个都是1则为1，只要有一个为0 则为0
        //| 只有两个都是0则为0，只要有一个为1 则为1
        // 注意这里的位移量为余数，不会出现冲突，如果大于8则会进位，如果小于8则都按照余数走
        bitArray[index] |= (1 << offset);
    }

    /**
     * judge value is exists
     *
     * @param value
     * @return
     */
    public boolean exists(int value) {
        //获取具体存储数组索引位置
        int index = getIndex(value);
        //获取偏移量，上面存储时是按照位移方式存储
        int offset = getOffset(value);
        //按照上面存储方式，这里按照存储规则判断是否存在
        //存储时计算规则为| 则只要有一个是1则为1 ，所以判断是否存在可以使用& 来判断只有两个都是1 则结果为1
        //这里使用了这种方式
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
     * 添加数据：计算index 设置offset 并且计算|
     * 判断是否存在：获取offset 计算&
     * 删除数据
     *
     * @param value
     */
    public void remove(int value) {
        // 获取index
        int index = getIndex(value);
        // 获取偏移量
        int offset = getOffset(value);
        // 两个都是1 则&结果为1，两个都是0 则|结果为0
        //删除计算，先取反然后在求与，取反是因为要删除，除了offset位置的值其他的都是1 这样在求&不会影响其他位的值。
        bitArray[index] &= ~(1 << offset);
    }

    /**
     * 判重，如果重复则返回重复，否则添加并返回不重复
     *
     * @param value
     * @return
     */
    public boolean repeat(int value) {
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
    private static int getIndex(int value) {
        return value / BIT;
    }

    /**
     * 一bit种offset位置确定
     *
     * @param value
     * @return
     */
    private static int getOffset(int value) {
        return value % BIT;
    }
}
