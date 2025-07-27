package indi.simon.learning.leetcode.脑筋急转弯;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k 个更新的操作。
 *
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
 *
 * 请你返回 k 次操作后的数组。
 *
 * 示例:
 *
 * 输入: length = 5, updates = [[1,3,2], [2,4,3], [0,2,-2]]
 * 输出: [-2,0,3,5,3]
 *
 * 解释:
 *
 * 初始状态:
 * [0,0,0,0,0]
 *
 * 进行了操作 [1,3,2] 后的状态:
 * [0,2,2,2,0]
 *
 * 进行了操作 [2,4,3] 后的状态:
 * [0,2,5,5,3]
 *
 * 进行了操作 [0,2,-2] 后的状态:
 * [-2,0,3,5,3]
 *
 * Created by Chen Zhuo on 2023/12/17.
 */
public class Quiz370_区间加法 {

    public static void main(String[] args) {
        Quiz370_区间加法 quiz370Hinted = new Quiz370_区间加法();
        int[] res = quiz370Hinted.getModifiedArrayV2(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}});
        System.out.println(res);
    }

    //todo: 这一题如果严格遍历updates数组并操作原数组所有元素的加减，那复杂度是n方，应该使用差分数组，O(n)解法，
    // 具体方式是：
    // 1. 创建差分数组diff，大小等于原数组
    // 2. 遍历updates数组，仅将updates[i]操作中的起始和结束位置需要对原数组中对应元素进行的操作记录在diff中即可，
    //    代表区间起始位置start相较于前一位需要加上updates[2]，结束位置end后一位相较于end需要减去updates[2]，这就是差分
    // 3. 最后遍历一遍原数组，每一位由前一位加上diff中的对应的差分即可
    public int[] getModifiedArrayV2(int length, int[][] updates) {
        int[] diff = new int[length];
        int[] ans = new int[length];
        for (int[] input : updates) {
            int start = input[0];
            int end = input[1];
            diff[start] += input[2];
            if (end < length - 1) {
                diff[end + 1] -= input[2];
            }
        }
        ans[0] = diff[0];
        for (int i = 1; i < length; i++) {
            ans[i] = ans[i - 1] + diff[i];
        }
        return ans;
    }

}
