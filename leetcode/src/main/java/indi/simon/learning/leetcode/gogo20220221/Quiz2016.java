package indi.simon.learning.leetcode.gogo20220221;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2016 {

    public static void main(String[] args) {
        Quiz2016 quiz2016 = new Quiz2016();
        int[] nums = new int[]{87, 68, 91, 86, 58, 63, 43, 98, 6, 40};
        int res = quiz2016.maximumDifference(nums);
        System.out.println(res);

    }

    public int maximumDifference(int[] nums) {
        return findMaxDiffInternal(nums, 0, nums.length - 1);
    }

    private int findMaxDiffInternal(int[] nums, int startIndex, int endIndexInclusive) {
        if (endIndexInclusive == startIndex) {
            return -1;
        }

        int maxIndex = startIndex;
        int minIndex = startIndex;

        //遍历一遍数组，找到最大最小值
        for (int i = startIndex; i <= endIndexInclusive; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }

        if (maxIndex == minIndex) {
            //说明此时整个子数组值都相等，二者都没出发过，一直等于初始值startIndex
            return -1;
        } else if (maxIndex > minIndex) {
            return nums[maxIndex] - nums[minIndex];
        } else {
            int maxDiffLeft = findMaxDiffInternal(nums, startIndex, maxIndex);
            int maxDiffMid = -1;
            if (maxIndex + 1 < nums.length && minIndex - 1 >= 0 && maxIndex + 1 < minIndex - 1) {
                maxDiffMid = findMaxDiffInternal(nums, maxIndex + 1, minIndex - 1);
            }
            //todo: 此处要注意，因为是拿最大值和最小值当边界来切分数组进行递归，递归的时候的边界下标一定是Inclusive的，也就是最大值元素要在前一个子数组中，最小值元素要在后一个子数组中
            // 中间的数组不包含两端边界，就这里错了一次，第二次错是审题不清，maxVal == minVal时应该返回-1而不是0
            int maxDiffRight = findMaxDiffInternal(nums, minIndex, endIndexInclusive);
            return Math.max(maxDiffLeft, Math.max(maxDiffMid, maxDiffRight));
        }
    }
}
