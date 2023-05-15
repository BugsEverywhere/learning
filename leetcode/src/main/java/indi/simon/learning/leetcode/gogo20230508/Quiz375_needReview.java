package indi.simon.learning.leetcode.gogo20230508;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz375_needReview {

    public static void main(String[] args) {
        Quiz375_needReview quiz375NeedReview = new Quiz375_needReview();
        int res = quiz375NeedReview.getMoneyAmount(16);
        System.out.println(res);
    }

    //todo: 错误的dfs，看似是dfs了，但是在每一个range内，先入为主的只考虑了一种情况，那就是(left+right)/2为根节点，以他为分界往下查找。
    // 实际上在每一个range内也需要遍历所有节点作为根节点，来区分左区间和右区间的情况。
    public int getMoneyAmount(int n) {
        if (n == 1) {
            return 0;
        }
        //遍历以1~n中不同数字为二分查找的根节点的情况，每一种情况的最坏花销，并在所有最坏花销里取最小值即为答案
        int minBadCase = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            //每个i的左子树与右子树最坏情况取更坏的一个
            int worstCase = i + Math.max(mostCostRange(1, i - 1), mostCostRange(i + 1, n));
            minBadCase = Math.min(minBadCase, worstCase);
        }
        return minBadCase;
    }

    private int mostCostRange(int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        return mid + Math.max(mostCostRange(left, mid - 1), mostCostRange(mid + 1, right));
    }


    //todo: 正确的dfs示范
    public int getMoneyAmountRight(int n) {
        return dfs(1, n);
    }

    private int dfs(int start, int end) {
        if (start >= end) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int k = start; k <= end; k++) {
            ans = Math.min(ans, Math.max(dfs(start, k - 1), dfs(k + 1, end)) + k);
        }
        return ans;
    }


}
