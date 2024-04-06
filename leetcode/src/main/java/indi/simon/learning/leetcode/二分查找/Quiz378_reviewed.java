package indi.simon.learning.leetcode.二分查找;


/**
 * @author chenzhuo(zhiyue)
 */
//todo: 凡是碰到已经排好序的数组或者矩阵，寻找其中某一个数，都要想到二分法，此处的思想是，如果能找到矩阵中一个数x，矩阵中小于等于x的数的个数刚好是k，
// 则这个x就是我们要找的数。与传统的二分不同的是，这里不是拿数组下标去二分，而是拿具体的数组值来二分（问题的关键并不是说这里数组时二维的，
// 其实如果是排好序的一维数组，要在其中找排行第k的数，如果也要用二分来找的话，也是拿数组值来二分）。这样就带来一个问题，二分循环中的mid是否存在于数组中？
// 如果找到一个mid，check(mid)==true 且 check(mid-1)==false，那么这个mid肯定在矩阵中且是我们需要找的数。
// 所以这里需要注意的技巧有：
// 1. 二维行列有序数组寻找第k大的数，转化为：寻找x,使得二维行列有序数组中刚好有k个数小于等于x
// 2. 计算二维行列有序数组中小于等于某个x的数的个数的方法是，逐行遍历，每遍历到一个数，如果其小于等于x，说明该列上行数小于它的数都满足条件，那么假设该数在第i行，
//    则该列的前i+1个数都满足条件，结果加上i+1。如果碰到比x大的数，则行减1继续遍历，相当于描绘一副小于等于x的边界线，这样就充分利用了行列有序的特性
// 3. 不要总是把二分法局限在使用数组下标来进行二分，使用数组值同样可以二分，关键就是这个check方法怎么写。
public class Quiz378_reviewed {

    public static void main(String[] args) {
        Quiz378_reviewed quiz378 = new Quiz378_reviewed();
        int[][] arr = new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}};
        int res = quiz378.kthSmallest(arr, 8);
        System.out.println(res);
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        int mid = 0;
        while (left <= right) {
            mid = (right + left) >> 1;
            boolean checkMid = check(matrix, mid, k, n);
            boolean checkMidMinus = check(matrix, mid - 1, k, n);
            if (checkMid && checkMidMinus) {
                right = mid - 1;
            } else if(!checkMid) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return mid;
    }

    /**
     * 校验matrix中小于等于mid的数的个数是否>=k个，是则返回true，否则返回false
     *
     * @param matrix
     * @param mid
     * @param k
     * @param n
     * @return
     */
    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }
}
