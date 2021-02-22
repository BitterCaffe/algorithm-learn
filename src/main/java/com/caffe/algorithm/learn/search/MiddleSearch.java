package com.caffe.algorithm.learn.search;

/**
 * @author BitterCaffe
 * @date 2020/12/9
 * @description: middle  search
 */
public class MiddleSearch {


    /**
     * arr 有序数组、len 数组长度、value查找的值
     * 现在这种写法有个问题如果len的值大于arr.length 则会出现问题
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int search(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (arr[middle] == value) {
                return middle;
            } else if (arr[middle] < value) {
                low = middle + 1;
            } else if (arr[middle] > value) {
                high = middle - 1;
            }
        }
        return -1;
    }

    /**
     * search 解决low+high 的溢出问题，这个溢出其实不影响整个流程的
     * search 这里说的解决溢出是使用low+(high-low)/2 ；这里防止的溢出是指 （low+high）/2 的值大于high的值
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int search2(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (arr[middle] == value) {
                return middle;
            } else if (arr[middle] < value) {
                low = middle + 1;
            } else if (arr[middle] > value) {
                high = middle - 1;
            }
        }
        return -1;
    }

    /**
     * rewrite
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int search22(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] == value) {
                return middle;
            } else if (arr[middle] > value) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 使用位移计算
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int search3(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] == value) {
                return middle;
            } else if (arr[middle] < value) {
                low = middle + 1;
            } else if (arr[middle] > value) {
                high = middle - 1;
            }
        }
        return -1;
    }

    public static int search4(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        int index = search5(arr, low, high, value);
        return index;
    }

    private static int search5(int[] arr, int low, int high, int value) {
        if (low > high) {
            return -2;
        }
        int middle = low + ((high - low) >> 1);
        if (arr[middle] == value) {
            return middle;
        } else if (arr[middle] > value) {
            return search5(arr, low, middle - 1, value);
        } else {
            return search5(arr, middle + 1, high, value);
        }

    }


    /**
     * 查找第一个等于的值
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int firstValue(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {

            int middle = low + ((high - len) >> 1);
            if (arr[middle] > value) {
                high = middle - 1;
            } else if (arr[middle] < value) {
                low = middle + 1;
            } else {
                if (middle == 0 || arr[middle - 1] != value) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找和给定值相等的第一个元素,注意这里是第一个相等的元素，所以获取到相等元素之后进行向前在进行判断来判断是否第一个元素
     * 第一个相等：
     * 1、查找相等的值
     * 2、判断是否是第一个，判断前一个元素是否相等，如果相等则判断前半部分，如果不相等则这个就是第一个
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int firstValue2(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] == value) {
                if (middle == 0 || arr[middle - 1] != value) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            } else if (arr[middle] > value) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }


    /**
     * 获取给定值在数组中第一个相等的索引位
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int firstValue3(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;

        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] > value) {
                high = middle - 1;
            } else if (arr[middle] < value) {
                low = middle + 1;
            } else {
                if (middle == 0 || arr[middle - 1] != value) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个等于的值
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int lastValue(int[] arr, int len, int value) {

        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] > value) {
                high = middle - 1;
            } else if (arr[middle] < value) {
                low = middle + 1;
            } else {
                if (middle == (len - 1) || arr[middle + 1] != value) {
                    return middle;
                } else {
                    low = middle + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找给定元素在数组中的最后一个相等的索引位置
     * 1、查找相等元素
     * 2、判断是否是最后一个元素，如果是最后一个则返回
     * 3、如果不是最后一个元素并且后一个元素不相等则说明是最后一个
     * 4、如果后一个元素相等则还要在后半部分查找数据
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int lastValue2(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] > value) {
                high = middle - 1;
            } else if (arr[middle] < value) {
                low = middle + 1;
            } else {
                if (middle == (len - 1) || arr[middle + 1] != value) {
                    return middle;
                } else {
                    low = middle + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于或等于给定值的元素
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int firstEqualValue(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] >= value) {
                if (middle == 0 || arr[middle - 1] < value) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 超找数组中第一个大于等于给定值的元素
     * 查找第一个大于等于的值，这里注意两点，第一第一个，第二大于等于所以对算法进行拆分
     * 1、先查找大于等于的元素
     * 2、确认是不是第一个，怎么确定是第一个那？
     * 3、如果middle是0 则前面没元素肯定是第一个
     * 4、如果middle的前一个元素<value则说明当前元素就是第一个大于给定值的索引位置
     * <p>
     * 算法实现：
     * 1、查找大于等于的middle
     * 2、判断是否是第一个元素满足条件的元素
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int firstEqualValue2(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] >= value) {
                if (middle == 0 || arr[middle - 1] < value) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 获取最后一个小于或等于给定值的元素
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int lastEqualValue(int[] arr, int len, int value) {
        int low = 0;
        int high = len;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] <= value) {
                if (middle == len - 1 || arr[middle + 1] > value) {
                    return middle;
                } else {
                    low = middle + 1;
                }
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }


    /**
     * 查找最后一个小于等于给定元素在索引中的位置
     * <p>
     * 1、小于等于，这是一个条件
     * 2、最后一个这是一个条件
     * 算法就是解决一个问题的方式方法，所以都可以按照这种条件拆分思路来实现
     * 1、查找小于等于元素
     * 2、判断是否最后一个小于等于即第一是否是最后一个元素，第二当前后一个元素> 则说明是最后一个小于value的index
     *
     * @param arr
     * @param len
     * @param value
     * @return
     */
    public static int lastEqualValue2(int[] arr, int len, int value) {
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (arr[middle] <= value) {
                if (middle == len - 1 || arr[middle + 1] > value) {
                    return middle;
                } else {
                    low = middle + 1;
                }
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 6, 6, 6, 8, 9};
//        int len = arr.length;
//        int value = 6;
//        int index = search(arr, len, value);
//        System.out.println("index:" + index);
//        int index2 = search2(arr, len, value);
//        System.out.println("index2:" + index2);
//        int index3 = search3(arr, len, value);
//        System.out.println("index3:" + index3);
//
//        int index4 = search4(arr, len, value);
//        System.out.println("index4:" + index4);
//
//        int indexFirst = firstValue(arr, len, value);
//        System.out.println("indexFirst:" + indexFirst);
//
//        int indexLast = lastValue(arr, len, value);
//        System.out.println("lastValue:" + indexLast);
//
//        int indexFirstEqualValue = firstEqualValue(arr, len, value);
//        System.out.println("firstEqualValue:" + indexFirstEqualValue);
//
//        int indexLastEqualValue = lastEqualValue(arr, len, value);
//        System.out.println("indexLastEqualValue:" + indexLastEqualValue);


        // second

        int[] arr = {1, 2, 3, 3, 3, 5, 6, 7, 8};
        int len = arr.length;
        int value = 4;
        int index = search22(arr, len, value);
        System.out.println("查找相等的元素：" + index);

        int firstValue2 = firstValue2(arr, len, value);
        System.out.println("查找相等的第一个元素：" + firstValue2);

        int lastValue2 = lastValue2(arr, len, value);
        System.out.println("查找相等的最后一个元素：" + lastValue2);

        int firstEqualValue2 = firstEqualValue2(arr, len, value);
        System.out.println("查找第一个大于等于的元素：" + firstEqualValue2);

        int lastEqualValue2 = lastEqualValue2(arr, len, value);
        System.out.println("查找最后一个小于等于的元素：" + lastEqualValue2);


    }
}
