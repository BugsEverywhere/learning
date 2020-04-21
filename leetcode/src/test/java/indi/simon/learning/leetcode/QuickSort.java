package indi.simon.learning.leetcode;


/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, devider;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        devider = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (devider <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (devider >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                swap(arr, i, j);
            }

        }
        //最后将基准位与i和j相等位置的数字交换
        swap(arr, low, i);
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}