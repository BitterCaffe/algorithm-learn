package com.caffe.algorithm.learn.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author BitterCaffe
 * @date 2020/12/7
 * @description: TODO
 * <p>
 * 1、对原始数据没有要求，有序、乱序都可以进行排序
 * 2、如果不优化则有序和无序的时间复杂度都是o(n^2)
 * 3、空间复杂度为o(1)
 * <p>
 * 如果进行优化则对原始数据是有要求的，如果原始数据已排序则时间复杂度为o(logn)
 * <p>
 * <p>
 * 1、冒泡排序
 * 2、冒泡排序优化
 * 3、插入排序
 * 4、快速排序
 */
public class SortCompare {

    /**
     * 未优化冒泡排序方式
     * 这种排序方式即使数组是有序的则整个遍历流程都需要走一遍，所以就有了优化方式
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int len = arr.length;
        //第一层循环没走一个值，则内循环遍历一次，而内循环每遍历一次则有一个元素排到了正确的位置，这也是内循环-i的原因
        for (int i = 0; i < len; i++) {
            //内循环每遍历一次就有一个元素正确排序，所以遍历n次就有n个元素正确排序所以之后的内循环没必要在遍历所有数据而应该是还未排序的数据。
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println("sort:" + JSON.toJSONString(arr));
    }


    /**
     * 对冒泡进行优化，如果数组本来就是有序的则不再进行排序
     *
     * @param arr
     */
    public static void optimizeSort(int[] arr) {
        boolean sortFlag = false;
        int len = arr.length;
        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    sortFlag = true;
                }
            }
            //经过内循环一轮遍历发现不需要排序，说明原来数组就是有序的，所以后面遍历就不需要了
            if (!sortFlag) {
                break;
            }
        }
        System.out.println("sortFlag:" + sortFlag);
        System.out.println("optimizeSort:" + JSON.toJSONString(arr));
    }

    /**
     * 从大-》小
     *
     * @param arr
     */
    public static void optimizeSort21(int[] arr) {

        boolean sortFlag = false;
        int len = arr.length;
        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    sortFlag = true;
                }
            }
            if (!sortFlag) {
                break;
            }
        }
        System.out.println("optimizeSort:" + JSON.toJSONString(arr));

    }


    /**
     * 插入排序
     * <p>
     * 插入排序相比冒泡的优势
     * 1、再最好情况下优化后的冒泡排序需要一次内循环遍历，而插入排序需要遍历一次外循环，复杂度一样
     * 2、最坏情况下冒泡法需要外层遍历*内层遍历即n!
     * 3、最坏情况下插入发需要外层遍历*内层遍历即n!
     * <p>
     * 两种排序方式空间复杂度都是o(1)而时间复杂度在最好情况和最坏情况下都是一样的。当然这里说的是优化后的冒泡排序。
     * 但是冒泡排序中的变量移动有三次而插入排序法只有一次所以在实际使用中都会使用插入排序而不是冒泡排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        //第一个元素作为比较对象，后面的元素认为是无序的，待插入的
        for (int i = 1; i < arr.length; i++) {
            //待插入值
            int value = arr[i];
            //有序位置,也就是前面排序后的值
            int j = i - 1;
            for (; j >= 0; j--) {
                //待插入元素和有序元素进行对比
                if (arr[j] > value) {
                    //如果有序元素位置大于要插入的值则后移一位，被覆盖的值就保存在了value中所以，这里的循环都是对大于value值的后移并找到合适的位置
                    //这里为何可以直接覆盖那，因为j+1的值已经在上面记录了即value所以可以直接覆盖
                    arr[j + 1] = arr[j];
                } else {
                    //这里为何break，因为arr[j]以及之前的都是有序的，只要第一个小于value 则前面的都小于这个值所以不用判断了
                    break;
                }
            }
            //找到合适位置，将带插入的值插入。
            arr[j + 1] = value;
        }
        System.out.println("insertSort1:" + JSON.toJSONString(arr));

    }

    //两种排序比较：冒泡排序要做的就是就是一个个老老实实的遍历，碰到不满足的就后移。这种方式要遍历所有元素。为了优化这种方式后边出现了优化后的冒泡排序，这种方式只需要判断遍历的元素是否有序，如果有序则直接退出不用遍历所有元素！
    //插入排序是一个新思路：默认数组中第一个元素是有序而后面的元素都是无序的，后面的元素通过遍历一个个来尝试插入有序位置，但是没遍历一个元素，有序元素的位置也在发生者改变。


    /**
     * @param arr
     */
    static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //带插入值
            int value = arr[i];
            //有序位置,有序位置为何是插入值的前一位，因为待插入的度列是有序的。

            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > value) {
                    //后移一位，继续比较
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            //这里为何是j+1因为上面的循环都会-1
            arr[j + 1] = value;
        }

        System.out.println("insertSort2" + JSON.toJSONString(arr));
    }

    static void insertSort3(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }
        System.out.println("insertSort3=" + JSON.toJSONString(arr));

    }


    /**
     * 普通方式、优化方式、插入方式三种思路
     *
     * @param args
     */
    public static void main(String[] args) {
        SortCompare sortCompare = new SortCompare();
        int[] arr = {1, 2, 3, 4, 6, 5};
//        optimizeSort(arr);
//        optimizeSort21(arr);
//        insertSort(arr);
        insertSort3(arr);
    }

}
