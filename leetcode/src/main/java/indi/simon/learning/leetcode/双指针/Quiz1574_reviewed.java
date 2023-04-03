package indi.simon.learning.leetcode.双指针;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 本质是为了找到arr中的这样两个序列：[0,i]区间，以及[j,arr.length-1]区间，满足以下两个条件：
// 1. 两者都非递减，
// 2. 且arr[j]>=arr[i]，
public class Quiz1574_reviewed {

    public static void main(String[] args) {
        Quiz1574_reviewed quiz1574Reviewed = new Quiz1574_reviewed();
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
        // 如果遍历到某个i本身和i+1就是逆序的，那么从i到j（更新完的j）就是一个需要删除的序列，后续也不要考虑了，本轮更新出来的j与该i之间就是要删除的序列
        // 而如果i也一直是非递减，那么因为每一轮更新完的j总是符合条件的j，那么取出来的res最终也必定是那个最小的要删除的序列长度
        for (int i = 0; i < n; i++) {
            while (j < n && arr[j] < arr[i]) {
                j++;
            }
            res = Math.min(res, j - i - 1);
            if (i + 1 < n && arr[i] > arr[i + 1]) {
                break;
            }
        }
        return res;
    }

}
