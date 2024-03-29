package com.caffe.algorithm.learn.sort;

/**
 * @author BitterCaffe
 * @date 2020/12/7
 * @description: TODO
 * 快速排序
 */
public class QuickSort {
    /**
     * @param arr
     * @param n
     */
    public static void quickSort(int[] arr, int n) {
        quickSortInternal(arr, 0, n - 1);
    }

    /**
     * 数据分组、排序
     *
     * @param arr
     * @param p
     * @param r
     */
    private static void quickSortInternal(int[] arr, int p, int r) {
        // 递归返回
        if (p >= r) {
            return;
        }
        // 分区，q值为后移的结果
        int q = partition2(arr, p, r);
        // 分区开始，前半部分
        quickSortInternal(arr, p, q - 1);
        // 分区开始后半部分
        quickSortInternal(arr, q + 1, r);

    }


    /**
     * 1、选中一个数据点作为排序的基点
     * 2、选择从最左、最后开始
     * 3、如果左侧的数据大于基数则替换为右侧索引处
     * 4、如果右侧的数据小于基数则替换为当前基数左侧索引处
     * 5、遍历结束基数左侧、右侧的数据已经分开
     * 6、然后再开始上面的流程
     *
     * @param arr
     * @param p   数组前索引
     * @param r   数组后索引
     * @return
     */
    private static int partition(int[] arr, int p, int r) {
        //基数选择,选择最后元素座位基准元素
        int pivot = arr[r];
        // i 从头开始遍历，和最后的值进行比较
        // 最左边的元素
        int i = p;
        //从最左边遍历检测是否满足划分的要求
        for (int j = p; j <= r - 1; j++) {
            // 满足基数排序规则，则元素需要向右移动，也就是分界线移动
            if (arr[j] < pivot) {
                //满足基数则，两个数同时移动
                if (i == j) {
                    i++;
                } else {
                    //如果不满足则两个索引位置处的值进行交换并且左边位置右移
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                }
            }
        }
        //遍历结束代表着某个基数两边的数就分开了
        // 不相同的元素进行交换
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
        return i;
    }

    /**
     * @param arr   数组
     * @param left  0
     * @param right 数组大小
     * @return
     */
    private static int partition1(int[] arr, int left, int right) {
        //基准对比值
        int middle = arr[right];
        // 最左边的元素，进行对比
        // 这里的curr就是一个分界线，一轮结束后分界线左边和右边的数据分开了……
        // 在整个过程都是来判断是否要移动curr，如果满足大小条件则会进行移动否则不会进行移动
        int curr = left;
        for (int a = left; a < right; a++) {
            //满足条件不用进行交换，只需要元素移动
            if (arr[a] < middle) {
                //这里的判断是是否都是满足要求的，如果都满足则是相等，如果不满足则
                if (curr == a) {
                    //分界线两边的元素满足条件，只需要移动元素
                    //如果移动量相等则直接移动位置就OK
                    curr++;
                } else {
                    //如果不相等则说明有不满足分界数据，则将大于分界的数据向右移动
                    // curr分界两边数据交换
                    int tmp = arr[curr];
                    arr[curr] = arr[a];
                    arr[a] = tmp;
                    curr++;
                }
            }
        }
        //当所有a值大于middle 的值则说明，最后的值是最小的，但不保证前面数据有序，只能保证最后一个值最小
        int tmp = arr[curr];
        arr[curr] = arr[right];
        arr[right] = tmp;
        //返回分界线,在对分界线两边进行查找
        return curr;
    }

    /**
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition2(int[] arr, int left, int right) {
        //如果是第一次，则left=0 right= max
        //元素排序，从左边开始
        int curr = left;
        //基数
        int value = arr[right];
        //如果当前遍历值小于基数则向后移动，如果遍历值大于基数则不移动，
        for (int i = left; i < right; i++) {
            if (arr[i] < value) {
                if (curr == i) {
                    curr++;
                } else {
                    //如果有一个值比基数大则小的值和大的值进行替换
                    //这样移动后，最后的一个值就是最大的，但是前面的值不一定是有序的，只能说curr作为分界线两边的值是区分开了。这是排序方式但是和二分查找有点相似。

                    int tmp = arr[curr];
                    arr[curr] = arr[i];
                    arr[i] = tmp;
                    curr++;
                }
            }
        }
        //大数向后移动
        int tmp = arr[curr];
        arr[curr] = arr[right];
        arr[right] = tmp;
        return curr;
    }

    /**
     * 打印
     *
     * @param arr
     */
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] num = new int[]{3, 1, 2, 4, 7, 6, 5, 8, 10, 9};
        QuickSort.quickSort(num, num.length);
        QuickSort.print(num);
    }
}
