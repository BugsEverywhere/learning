package indi.simon.learning.复习.双指针_滑动窗口;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
 *
 * 一个子数组指的是原数组中连续的一个子序列。
 *
 * 请你返回满足题目要求的最短子数组的长度。
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,10,4,2,3,5]
 * 输出：3
 * 解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
 * 另一个正确的解为删除子数组 [3,10,4] 。
 * 示例 2：
 *
 * 输入：arr = [5,4,3,2,1]
 * 输出：4
 * 解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
 * 示例 3：
 *
 * 输入：arr = [1,2,3]
 * 输出：0
 * 解释：数组已经是非递减的了，我们不需要删除任何元素。
 * 示例 4：
 *
 * 输入：arr = [1]
 * 输出：0
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^9
 *
 */
//todo: 本质是为了找到arr中的这样两个序列：[0,i]区间，以及[j,arr.length-1]区间，满足以下两个条件：
// 1. 两者都非递减，
// 2. 且arr[j]>=arr[i]
// 然后找到范围最小的[i,j]，删除这其中的元素
public class Quiz1574_删除最短子数组使剩余有序 {

    public static void main(String[] args) {
        Quiz1574_删除最短子数组使剩余有序 quiz1574Reviewed = new Quiz1574_删除最短子数组使剩余有序();
        int res = quiz1574Reviewed.findLengthOfShortestSubarray(new int[]{10, 20, 30, 40, 50, 60, 1, 2, 3, 4, 5, 6, 7});
        System.out.println(res);
    }

    //todo: 官方双指针
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length, j = n - 1;
        //从后往前找到第一个逆序，这样[j,n-1]的范围内保证非递减
        while (j > 0 && arr[j - 1] <= arr[j]) {
            j--;
        }
        //如果没有逆序，则直接返回
        if (j == 0) {
            return 0;
        }
        //大不了，顶破天，把j以前的全删了
        int res = j;
        //从头开始遍历i，考虑此时arr[j]需要实时更新，往后滚动以找到将将比arr[i]大的那一个，这样就能在每一轮确保上述条件1和条件2。且实时更新结果res，取最小即可
        // 如果遍历到某个i本身和i+1就是逆序的，那么从i到j就是一个需要删除的序列，后续也不要考虑了，直接break
        // 并且，在右移i的过程中，i也一直是非递减，所以j在更新的过程中肯定单调右移，不需要考虑左移的情况
        for (int i = 0; i < n; i++) {
            //todo: 往后移动j，找到将将大过i的j
            while (j < n && arr[j] < arr[i]) {
                j++;
            }
            //todo: 更新答案
            res = Math.min(res, j - i - 1);
            if (i + 1 < n && arr[i] > arr[i + 1]) {
                break;
            }
        }
        return res;
    }

}
