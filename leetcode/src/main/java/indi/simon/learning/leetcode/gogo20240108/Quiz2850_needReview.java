package indi.simon.learning.leetcode.gogo20240108;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Chen Zhuo on 2024/1/13.
 */
public class Quiz2850_needReview {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 2, 2},
                {1, 1, 0},
                {0, 1, 1}};
        Quiz2850_needReview quiz2850NeedReview = new Quiz2850_needReview();
        int res = quiz2850NeedReview.minimumMoves(grid);
        System.out.println(res);
    }

    public int minimumMoves(int[][] grid) {
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 1) {
                    for (int k = 1; k < grid[i][j]; k++) {
                        from.add(new int[]{i, j});
                    }
                } else if (grid[i][j] == 0) {
                    to.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (List<int[]> from2 : permutations(from)) {
            int total = 0;
            for (int i = 0; i < from2.size(); i++) {
                int[] f = from2.get(i);
                int[] t = to.get(i);
                total += Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
            }
            ans = Math.min(ans, total);
        }
        return ans;
    }

    private List<List<int[]>> permutations(List<int[]> arr) {
        List<List<int[]>> result = new ArrayList<>();
        permute(arr, 0, result);
        return result;
    }

    private void permute(List<int[]> arr, int start, List<List<int[]>> result) {
        if (start == arr.size()) {
            result.add(new ArrayList<>(arr));
        }
        for (int i = start; i < arr.size(); i++) {
            swap(arr, start, i);
            permute(arr, start + 1, result);
            swap(arr, start, i);
        }
    }

    private void swap(List<int[]> arr, int i, int j) {
        int[] temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }


}
