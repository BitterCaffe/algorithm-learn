package com.caffe.algorithm.learn.arrays;

/**
 * @author BitterCaffe
 * @date 2020/12/6
 * @description: 1、数组，在物理上使用连续内存存储一种类型的数据
 * 2、添加、删除最好的情况下时间复杂度为o(1)
 * 3、添加、删除、最坏时间复杂度为o(n)
 */
public class ArrayUtil {

    /**
     * 使用左位移定义capacity
     */
    private static int default_capacity = 2 << 2;

    /**
     * 使用数组，数组使用连续内存空间并且保存在堆内存
     */
    private Object[] table;

    /**
     * 记录元素个数
     * 成员变量是java对象大小计算的属性值
     */
    private int size;

    /**
     * 构造函数即编译器添加的《init》方法执行，来初始化变量
     *
     * @throws Exception
     */
    public ArrayUtil() throws Exception {
        this(default_capacity);
    }

    public ArrayUtil(int capacity) throws Exception {
        if (capacity > Integer.MAX_VALUE || capacity < 0) {
            throw new Exception("数组参数有误");
        }
        //连续内存空间申请
        table = new Object[capacity];
    }

    /**
     * add
     *
     * @param object
     */
    public void add(Object object) {
        //扩容
        if (size >= table.length) {
            doubleCapacity();
        }
        table[size] = object;
        size++;
    }


    /**
     * 扩容，arrayList 扩容也是2倍扩容和这里很像
     */
    private void doubleCapacity() {
        Object[] newTable = new Object[table.length << 1];
        for (int i = 0; i < size; i++) {
            newTable[i] = table[i];
        }
        //这一步操作释放了老对象，在堆内存被回收。引用了新对对象
        table = newTable;
    }

    /**
     * 获取数据
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        // 这种获取时间复杂度o(1),直接按照索引度获取
        return table[index];
    }

    /**
     * 删除，时间复杂度o(n),空间复杂度o(1)
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 0 || index > size) {
            return;
        }

        for (int i = index + 1; i <= size; i++) {
            table[i - 1] = table[i];
        }
        size--;
    }

    public static void main(String[] args) throws Exception {
        ArrayUtil arrayUtil = new ArrayUtil();
        for (int i = 0; i < 10; i++) {
            arrayUtil.add(i);
            System.out.println(arrayUtil.size);
        }
        System.out.println("size=" + arrayUtil.size);
        arrayUtil.remove(-1);
        System.out.println("size=" + arrayUtil.size);
    }
}
