package indi.simon.learning.leetcode.gogo20240205;

/**
 * Created by Chen Zhuo on 2024/2/9.
 */
public class Quiz912 {

    public static void main(String[] args) {
        Quiz912 quiz912 = new Quiz912();
        int[] res = quiz912.sortArray(new int[]{5,2,3,1});
        System.out.println(res);
    }

    public int[] sortArray(int[] nums) {
        sortInternal(nums, 0, nums.length - 1);
        return nums;
    }

    private void sortInternal(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int mid = (i + j) / 2;

        sortInternal(nums, i, mid);
        sortInternal(nums, mid + 1, j);

        int m = i;
        int n = mid + 1;

        int[] tmp = new int[j - i + 1];
        for (int k = 0; k < tmp.length; k++) {
            if (m <= mid && n <= j && nums[m] < nums[n]) {
                tmp[k] = nums[m];
                m++;
            } else if (m <= mid && n <= j && nums[m] >= nums[n]) {
                tmp[k] = nums[n];
                n++;
            } else if (m <= mid) {
                tmp[k] = nums[m];
                m++;
            } else if (n <= j) {
                tmp[k] = nums[n];
                n++;
            }
        }

        for (int k = 0; k < tmp.length; k++) {
            nums[i + k] = tmp[k];
        }
    }

}
