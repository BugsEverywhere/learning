package indi.simon.learning.复习.二分查找;


/**
 * @author chenzhuo(zhiyue)
 *
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *
 * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *  [1,5,9],
 *  [10,11,13],
 *  [12,13,15]
 * ], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * 示例 2：
 *
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 * 提示：
 *
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 * 1 <= k <= n2
 * 进阶：
 *
 * 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
 * 你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。
 */
//todo: 凡是碰到已经排好序的数组或者矩阵，寻找其中某一个数，都要想到二分法，此处的思想是，如果能找到矩阵中一个数x，矩阵中小于等于x的数的个数刚好是k，
// 则这个x就是我们要找的数。与传统的二分不同的是，这里不是拿数组下标去二分，而是拿具体的数组值来二分（问题的关键并不是说这里数组时二维的，
// 其实如果是排好序的一维数组，要在其中找排行第k的数，如果也要用二分来找的话，也是拿数组值来二分）。这样就带来一个问题，二分循环中的mid是否存在于数组中？
// 如果找到一个mid，check(mid)==true 且 check(mid-1)==false，那么这个mid肯定在矩阵中且是我们需要找的数。
// 所以这里需要注意的技巧有：
// 1. 二维行列有序数组寻找第k大的数，转化为：寻找x,使得二维行列有序数组中刚好有k个数小于等于x
// 2. 计算二维行列有序数组中小于等于某个x的数的个数(cnt)的方法是，逐行遍历，每遍历到一个数，如果其小于等于x，说明该列上方的数都满足条件，那么假设该数在第i行，
//    则该列的前i+1个数都满足条件，cnt加上i+1。如果碰到比x大的数，则行减1继续遍历，相当于描绘一副小于等于x的边界线，这样就充分利用了行列有序的特性
// 3. 不要总是把二分法局限在使用数组下标来进行二分，使用数组值同样可以二分，关键就是这个check方法怎么写。
public class Quiz378_二维行列有序数组查找第k大的数 {

    public static void main(String[] args) {
        Quiz378_二维行列有序数组查找第k大的数 quiz378 = new Quiz378_二维行列有序数组查找第k大的数();
        int[][] arr = new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}};
        int res = quiz378.kthSmallest(arr, 8);
        System.out.println(res);
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        //todo: right，left，mid都是数组值，不是数组下标
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        int mid = 0;
        while (left <= right) {
            mid = (right + left) >> 1;
            //校验小于等于mid的数至少有k个
            boolean checkMid = check(matrix, mid, k, n);
            //校验小于等于mid-1的数至少有k个
            boolean checkMidMinus = check(matrix, mid - 1, k, n);
            if (checkMid && checkMidMinus) {
                //如果都满足，说明mid偏大，移动right
                right = mid - 1;
            } else if(!checkMid) {
                //如果checkMid不满足，说明mid偏小，移动left
                left = mid + 1;
            } else {
                //正好满足checkMid且不满足checkMidMinus，mid就是要找的这个，且妥妥的肯定在矩阵中
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
                //收割该列上方元素
                num += i + 1;
                j++;
            } else {
                //在该列上移一位
                i--;
            }
        }
        return num >= k;
    }
}
