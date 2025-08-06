package indi.simon.learning.复习.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 *
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 * 示例 1：
 *
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 示例 2：
 *
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 * 提示：
 *
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 */
public class Quiz907所有连续子数组最小值之和_needReview {

    public static void main(String[] args) {
        Quiz907所有连续子数组最小值之和_needReview quiz907NeedReview = new Quiz907所有连续子数组最小值之和_needReview();
        int res = quiz907NeedReview.sumSubarrayMins(new int[]{11, 81, 94, 43, 3});
        System.out.println(res);
    }

    //todo: 官方所谓单调栈，思路是，任何一个arr[i]肯定是以它为最小元素的所有连续子数组的最小元素，那么假设有N个包含他且以他为
    // 最小元素的连续子数组，那么arr[i]*N就是这些连续子数组的最小值之和，用此种方法求出每一个arr[i]的该乘积，再相加就是结果。
    // 那么如何得到arr[i]的N，这里单调栈就是用来得到arr中每一个arr[i]左边最近的小于arr[i]的数，以及arr[i]右边最近的小于等于
    // arr[i]的数（一定要记住要么左边是严格小于，右边是小于等于，要么反过来，避免同一个连续子数组被重复计算两次，比如1,4,5,6,4,7，
    // 如果左右两边都是采用小于等于，那么子序列4,5,6,4会被第一个4记录一次，也会被第二个4记录一次，导致重复计算，相反，如果左右两边
    // 都采用严格小于，那么该子序列会被两个4都漏掉，导致少计算了），找到左右两边这两个元素之后，在这之间（开区间）的所有元素都是大于等于
    // arr[i]的元素，所有包含arr[i]的连续子序列的个数就是该开区间中左边元素个数乘以右边元素个数
    //
    // todo:
    //  技巧1：遍历数组时，使用单调栈记录每一个元素的最近的floor或者ceiling的下标，可以跳过中间元素，直接使用栈顶来找，速度更快
    //  技巧2：在数组中（或者某区间内）以某个元素为中心，以它为右边界的包含它的连续子数组个数假设为L，以它为左边界的包含它的连续子数组个数假设为R，
    //        则在该区间内所有包含它的连续子数组的个数为 L * R
    //  技巧3：左右两次遍历，求出每一个元素左右两边最近floor或者ceiling
    public int sumSubarrayMinsOfficial(int[] arr) {
        int n = arr.length;
        Deque<Integer> monoStack = new ArrayDeque<>();
        //用于记录arr中每个元素左边最近的满足严格小于arr[i]的元素下标与i的距离数组
        int[] left = new int[n];
        //用于记录arr中每个元素右边最近的满足小于等于arr[i]的元素下标与i的距离数组
        int[] right = new int[n];
        //从左向右遍历，求出每一个left[i]，此时每一轮的时候，monoStack从栈底到栈顶单调递增，每一轮结束
        // 都把当前i push到栈顶，这样下面的元素都是比arr[i]严格小的元素，在处理i+1时，无论arr[i+1]比arr[i]大还是小
        // ，都不影响arr[i+1]，如果arr[i]小于arr[i+1]，则left[i+1]=1，如果arr[i]大于arr[i+1]，则把i pop出来之后，
        // 下一个就是最近的比arr[i]更小的数，跳过了中间比arr[i]更大的数，这样更方便找到最近比arr[i+1]小的数
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && arr[i] <= arr[monoStack.peek()]) {
                monoStack.pop();
            }
            //如果栈为空，即左侧没有严格小于arr[i]的元素，则0~i一共i+1个连续子数组都是符合条件的子数组
            left[i] = i - (monoStack.isEmpty() ? -1 : monoStack.peek());
            monoStack.push(i);
        }
        monoStack.clear();
        //同理，从右向左遍历，求出每一个right[i]
        for (int i = n - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && arr[i] < arr[monoStack.peek()]) {
                monoStack.pop();
            }
            right[i] = (monoStack.isEmpty() ? n : monoStack.peek()) - i;
            monoStack.push(i);
        }
        long ans = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) left[i] * right[i] * arr[i]) % MOD;
        }
        return (int) ans;
    }

    //todo: 穷举，超时
    public int sumSubarrayMins(int[] arr) {
        int i = 0;
        int sum = 0;
        while (i < arr.length) {
            int minThisRound = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                minThisRound = Math.min(arr[j], minThisRound);
                sum += minThisRound;
                sum = (int) (sum % (Math.pow(10, 9) + 7));
            }
            i++;
        }

        return sum;
    }

}
