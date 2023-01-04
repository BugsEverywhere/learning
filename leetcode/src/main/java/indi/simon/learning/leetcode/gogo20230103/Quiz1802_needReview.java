package indi.simon.learning.leetcode.gogo20230103;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1802_needReview {

    public static void main(String[] args) {
        Quiz1802_needReview quiz1802NeedReview = new Quiz1802_needReview();
        int res = quiz1802NeedReview.maxValue(6, 1, 10);
        System.out.println(res);
    }

    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (valid(mid, n, index, maxSum)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public boolean valid(int mid, int n, int index, int maxSum) {
        int left = index;
        int right = n - index - 1;
        return mid + cal(mid, left) + cal(mid, right) <= maxSum;
    }

    public long cal(int big, int length) {
        if (length + 1 < big) {
            int small = big - length;
            return (long) (big - 1 + small) * length / 2;
        } else {
            int ones = length - (big - 1);
            return (long) big * (big - 1) / 2 + ones;
        }
    }

}
