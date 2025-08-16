package indi.simon.learning.复习.二分查找;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 */
public class Quiz240_二维行列有序数组查找指定数 {

    public static void main(String[] args) {
        Quiz240_二维行列有序数组查找指定数 quiz240_搜索有序二维矩阵 = new Quiz240_二维行列有序数组查找指定数();
        boolean res = quiz240_搜索有序二维矩阵.searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5);
        System.out.println(res);
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        int i = n - 1;
        int j = 0;

        //todo：注意这里不是for循环，while包一下即可，i和j的自增都交给循环体内部
        while (i >= 0 && i < n && j >= 0) {
            while (i >= 0 && matrix[i][j] > target) {
                i--;
            }
            if (i >= 0 && matrix[i][j] == target) {
                return true;
            }
            if (i < n - 1) {
                i++;
            }
            j++;
            //todo: 需要记得判断j是否越界，但i无需判断，i有自增机制保障肯定大于等于0
            if (j >= m) {
                return false;
            }
        }
        return false;
    }


}
