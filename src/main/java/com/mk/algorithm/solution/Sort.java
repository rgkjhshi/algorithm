package com.mk.algorithm.solution;

import java.util.Arrays;

/**
 * @author song.shi
 * @since 2018-07-06
 */
public class Sort {


    /**
     * 直接插入排序
     */
    public static void insertSort(int[] array, int n) {
        int i, j;
        // [1, n-1]依次插入到前面的有序表
        for (i = 1; i <= n - 1; i++) {
            // 临时存放待插入元素
            int tmp = array[i];
            // 若tmp小于前序, 需将前序后移并寻找插入位置
            if (tmp < array[i - 1]) {
                // 向前逐个比较，大于tmp的元素都要后移，寻找插入位置
                for (j = i - 1; j >= 0 && tmp < array[j]; j--) {
                    array[j + 1] = array[j];
                }
                // 找到插入位置(j后面)
                array[j + 1] = tmp;
            }
        }
    }

    /**
     * 折半插入排序
     */
    public static void binaryInsertSort(int[] array, int n) {
        int i, j;
        // [1, n-1]依次插入到前面的有序表
        for (i = 1; i <= n - 1; i++) {
            if (array[i] < array[i - 1]) {
                int temp = array[i];
                int low = 0;
                int high = i - 1;
                // 折半法寻找i的插入位置
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (temp > array[mid]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                // low = high + 1, [low, i-1] 都需要后移
                for (j = i; j > low; j--) {
                    array[j] = array[j - 1];
                }
                array[j] = temp;
            }
        }
    }


    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] array, int n) {
        // 外层循环控制排序的趟数(n-1 趟)
        for (int i = 0; i < n - 1; i++) {
            // 内层循环负责一趟排序
            for (int j = 0; j < n - i - 1; j++) {
                // 若逆序(大的在前为逆序), 则交换
                if (array[j] > array[j + 1]) {
                    // swap(j, j+1)
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] array, int left, int right) {

        if (left < right) {
            int pos = partition(array, left, right);
            // 排序左边
            quickSort(array, left, pos - 1);
            // 排序右边
            quickSort(array, pos + 1, right);
        }
    }

    /**
     * 快排的划分函数
     */
    private static int partition(int[] array, int left, int right) {
        // 基准值
        int posValue = array[left];
        while (left < right) {
            // 右边都应该大于等于基准值, 从右往左找第一个不满足的元素
            while (left < right && array[right] >= posValue) {
                right--;
            }
            // 把比基准小的元素放左边
            array[left] = array[right];
            // 左边都应下雨等于基准值, 从左往右找第一个不满足的元素
            while (left < right && posValue >= array[left]) {
                left++;
            }
            // 把比基准值大的元素放右边
            array[right] = array[right];
        }
        // 出循环时有 left == right, 把基准值放到这里
        array[left] = posValue;
        // 返回基准位置
        return right;
    }

    /**
     * 简单选择排序
     */
    public static void selectSort(int[] array, int n) {
        for (int i = 0; i <= n - 1; i++) {
            int min = i;
            // 从[i+1, n-1]中选出最小的
            for (int j = i + 1; j <= n - 1; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            // swap(i, min)
            swap(array, i, min);
        }
    }

    /**
     * 交换元素的函数
     */
    private static void swap(int[] array, int i, int j) {
        // swap(i, j)
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 5, 3, 4};
        quickSort(array, 0, 4);
        System.out.println(Arrays.toString(array));
    }
}
