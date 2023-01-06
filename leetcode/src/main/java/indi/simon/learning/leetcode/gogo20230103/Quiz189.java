package indi.simon.learning.leetcode.gogo20230103;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz189 {

    public static void main(String[] args) {
        Quiz189 quiz189 = new Quiz189();
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        quiz189.rotate(arr, 10);
        System.out.println(arr);
    }

    public void rotate(int[] nums, int k) {
        if (k == 0) {
            return;
        }

        if (nums.length <= 1) {
            return;
        }

        if (k > nums.length) {
            k = k % nums.length;
        }

        int baseIndex = 0;
        int index = 0;
        boolean started = false;
        int num = nums[index];
        //todo: 这个是关键，前几次提交有问题都是因为当index重新等于baseIndex的时候，如何判断是否index和baseIndex需要进位重新来一轮有问题，
        // 其实只需要记录下之前移动过的数有多少个，在每次index移动回baseIndex的时候，如果之前移动过的数字个数小于总数目，那么就需要进位再来一轮
        int movedCount = 0;
        while (true) {
            if (index == baseIndex && movedCount == nums.length) {
                break;
            } else if (index == baseIndex) {
                index = increment(nums.length, index);
                baseIndex = increment(nums.length, baseIndex);
                num = nums[index];
            }
            int targetPos = index + k;
            if (targetPos >= nums.length) {
                targetPos = targetPos - nums.length;
            }

            int tmp = nums[targetPos];
            nums[targetPos] = num;
            index = targetPos;
            num = tmp;
            if (!started) {
                started = true;
            }
            movedCount++;
        }
    }

    private int increment(int length, int curr) {
        curr += 1;
        if (curr >= length) {
            return 0;
        } else {
            return curr;
        }
    }


}
