package com.caffe.algorithm.learn.bitmap;

/**
 * @author BitterCaffe
 * @date 2020/11/20
 * @description: bit数组存储的核心是位运算：&所有的都是1则为1 ；|所有的都为0则为0 ；~对所有位置的值进行取反，0变1，1变0
 * bit数组存储就是按照位移计算结果来存储
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
        return doExists(index, offset);
    }

    /**
     * 判断是否存在
     *
     * @param index
     * @param offset
     * @return
     */
    private boolean doExists(int index, int offset) {
        //这里判断是否存在时原来的值和位移值做了一个& ，
        // 其实很好理解就是1<<offset这个值除了位移位置的值为1其他地方的值为0所以如果哪个位置有值即1则1&1=1否则0&1=0就是没值。
        return (intArray[index] & (1 << offset)) != 0;
    }

    /**
     * 重复性判断
     *
     * @param value
     * @return
     */
    public boolean repeat(int value) {
        int index = getIndex(value);
        int offset = getOffset(value);
        if (doExists(index, offset)) {
            return true;
        }
        doAdd(index, value);
        return false;
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
     * 和上面方法是一样的，只是这里详细描述了整个存储和获取的过程
     *
     * @param maxValue
     * @param intArr
     */
    public static int[] intSort(int maxValue, int[] intArr) {
        //计算存储最大值的集合大小,除数就是计算应该放在哪个集合中。
        //余数就是计算他应该存放在哪个index中的具体的存储空间
        //你说其中存储了具体的值吗，其实他没有存储。只是按照这样的一个公式计算了标记位置。
        int len = (maxValue / 32) + 1;
        int[] bitStory = new int[len];
        //按照公式、规则在bit中做标记
        for (int i : intArr) {
            int index = i / 32;
            int offset = i % 32;
            bitStory[index] |= (1 << offset);
        }
        //bitmap存储方式有个问题就是重复数据不能存储，或者说优点是可以快速查找重复数据时间复杂度O(1)
        //上面这种存储方式其实存储后就已经排序了，所以遍历取出之后就是有序的。唯一要做的就是遍历输出。

        int num = 0;
        int count = 0;
        //这层遍历是遍历数据，其实就是最大值个数的校验
        for (int i = 0; i < bitStory.length; i++) {
            int currentStory = bitStory[i];
            //int中每一bit遍历,其实每一位都是存储的具体值，他的值的存储是使用数组index*8、32、64来表示具体值。
            for (int j = 0; j < 32; j++) {
                //这里使用当前索引位置值和位移后的值做& 计算这样就能计算出当前位移位置的值。如果是0 则说明之前哪个位置没有值存储，所以也就不取数据了。
                //位移之后做&计算就能判断之前这里是否有值，如果有获取数据，如果没有则0
                int res = currentStory & (1 << j);
                if (res != 0) {
                    //num值的计算可以参考上面存入时的方式，因为这里就是按照存储时的方式来取值的。
                    //存储时是按照value/32和value%32的方式来计算value被标记的位置的。
                    //这里的值为何是i*32+j,i就是索引index 所以index*32就计算出了整除部分，而j是余数，余数是多少就存储在bit的第几位。
                    //索引index*32+j就是具体value值的还原。
                    num = i * 32 + j;
                    //刚开始这里看着很是郁闷，看看上面标记方式就明白了。我们数组中的每一位值都保存在一bit中所以遍历bit给arr赋值是没问题的。
                    intArr[count] = num;
                    count++;
                }
            }
        }
        return intArr;
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
