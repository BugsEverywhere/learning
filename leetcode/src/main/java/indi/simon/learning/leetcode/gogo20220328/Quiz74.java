package indi.simon.learning.leetcode.gogo20220328;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz74 {

    public static void main(String[] args) {
        Quiz74 quiz74 = new Quiz74();
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        boolean res = quiz74.searchMatrix(matrix, 121);
        System.out.println(res);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = findRow(matrix, target);
        if (row == -1) {
            return false;
        }
        return findAccurate(matrix, row, target);
    }

    private int findRow(int[][] matrix, int target) {
        if (matrix.length == 1) {
            if (matrix[0][0] <= target) {
                return 0;
            } else {
                return -1;
            }
        }

        int up = 0;
        int down = matrix.length - 1;
        int mid;
        while (up <= down) {
            mid = (up + down) / 2;
            if (matrix[mid][0] <= target && matrix[mid][matrix[0].length - 1] >= target) {
                return mid;
            } else if (matrix[mid][0] > target) {
                down = mid - 1;
            } else if (matrix[mid][matrix[0].length - 1] < target) {
                up = mid + 1;
            }
        }
        return -1;
    }

    private boolean findAccurate(int[][] matrix, int rowNum, int target) {
        int left = 0;
        int right = matrix[0].length - 1;

        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (matrix[rowNum][mid] == target) {
                return true;
            } else if (matrix[rowNum][mid] > target) {
                right = mid - 1;
            } else if (matrix[rowNum][mid] < target) {
                left = mid + 1;
            }
        }
        return false;
    }


}
