package indi.simon.learning.leetcode.gogo20230403;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1053 {

    public static void main(String[] args) {
        Quiz1053 quiz1053 = new Quiz1053();
        int[] res = quiz1053.prevPermOpt1(new int[]{3, 1, 1, 3});
        System.out.println(res);
    }

    public int[] prevPermOpt1(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int i = arr.length - 2;
        //从后向前找到第一个逆序对
        for (; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                break;
            }
        }

        if (i < 0) {
            //说明整个数组都没有逆序对，本数组已经是字典序最小的了
            return arr;
        }

        //从尾部数组查找出比当前arr[i]小的最大数
        int floorIndex = binarySearch(arr[i], arr, i + 1, arr.length - 1);
        //交换
        swap(arr, i, floorIndex);
        return arr;
    }

    private int binarySearch(int target, int[] arr, int left, int right) {
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] >= target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                if (mid + 1 < arr.length && arr[mid + 1] >= target) {
                    //找到了
                    //确定一下是否是一连串相同数字的最后一个
                    while (arr[mid - 1] == arr[mid]) {
                        mid--;
                    }
                    return mid;
                } else if (mid + 1 < arr.length && arr[mid + 1] < target) {
                    //仍然没找到
                    left = mid + 1;
                } else if (mid + 1 == arr.length) {
                    //也找到了
                    return mid;
                }
            }
        }
        //不会到这儿
        return -1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
