package indi.simon.learning.复习.排序;

/**
 * @author chenzhuo(zhiyue)
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class Quiz215_数组中第k个最大的元素 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        Quiz215_数组中第k个最大的元素 quiz215_数组中第k个最大的元素 = new Quiz215_数组中第k个最大的元素();
        int res = quiz215_数组中第k个最大的元素.findKthLargest(nums, 2);
        System.out.println(res);
    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickselect(nums, 0, n - 1, n - k);
    }

    //todo: 基于快排的快速查找。
    // 要找倒数第k个最大的元素，就是要找第n-k大的元素，令 n-k == index
    // 接下来就是用快排的思想来找到找到第index大的元素
    // 复习一下快排，就是随机挑一个元素（此处是nums[l]），比他大的放右边，比他小的放左边，然后以该元素为分界点，左右边递归继续
    // 此处while循环就是完成一次排序，排完之后（此时必须使用j作为分界点，这样才保证[l,j]都是小于等于x的，[j+1,r]都是大于等于x的），
    // 最终会收敛到l, r, index都是同一个数，返回即可
    int quickselect(int[] nums, int l, int r, int index) {
        int x = nums[l];
        int i = l - 1;
        int j = r + 1;

        while (i < j) {
            do {
                i++;
            } while (nums[i] < x);
            do {
                j--;
            } while (nums[j] > x);

            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        if(i == j){
            //此时i和j指向的就是x
            if (index < j) {
                //要找的第index个数比j小，继续递归j左侧
                return quickselect(nums, l, j - 1, index);
            } else if(index > j) {
                //要找的第index个数比j大，继续递归j右侧
                return quickselect(nums, j + 1, r, index);
            } else {
                //index就是j，直接返回
                return nums[j];
            }
        } else if (i > j){
            //此时i指向第一个大于x的数，j指向最后一个小于x的数
            if (index <= j) {
                return quickselect(nums, l, j, index);
            } else if(index >= i) {
                return quickselect(nums, i, r, index);
            }
        }
        return -1;
    }

}
