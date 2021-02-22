package com.caffe.algorithm.learn.sort;

/**
 * @author BitterCaffe
 * @date 2020/12/14
 * @description: TODO
 */
public class QuickSort1 {
    /**
     * @param arr
     * @param left
     * @param right
     */
    public static void classicQuickSort(int[] arr, int left, int right) {
        if (left < right) {
            int p = partition(arr, left, right);
            classicQuickSort(arr, left, p - 1);
            classicQuickSort(arr, p + 1, right);
        }
    }

    /**
     * 分区获取
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] arr, int left, int right) {
        //主元
        int x = arr[right];
        //比主元小的最右元素
        int p = left - 1;
        //遍历比较
        for (int i = left; i < right; i++) {
            if (arr[i] <= x) {
                p++;
                swap(arr, p, i);
            }
        }
        swap(arr, right, p + 1);
        return p + 1;

    }

    /**
     * 交换
     *
     * @param arr
     * @param p
     * @param i
     */
    private static void swap(int[] arr, int p, int i) {
        int temp = arr[p];
        arr[p] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 4, 7, 5, 6};
        classicQuickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
