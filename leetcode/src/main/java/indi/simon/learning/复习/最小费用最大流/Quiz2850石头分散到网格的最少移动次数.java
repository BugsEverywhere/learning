package indi.simon.learning.复习.最小费用最大流;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2024/1/13.
 */
//todo: 技巧：
// 1. 流出的石头肯定和流入的石头数量相等，所以列举出所有流出石头的格子from（流出多个则记多次），以及流入石头的格子to，
//    from和to的长度肯定是一样的，所以可以通过计算from[0]->to[0]，from[1]->to[1]，......的曼哈顿距离之和，来得到
//    特定from到特定to的石头移动次数。
// 2. 而且一共也就3*3的矩阵，9个格子，所以可以穷举所有的from排列，to不动，那么就是穷举∑(from1[i]->to[i])，
//    ∑(from2[i]->to[i])，......，然后计算这其中的最小值，就是答案。
// 3. 生成数组arr的全排列，可以使用从游标start开始的for循环递归，每一层递归时start+1，循环中交换i与start，遍历到底时，
//    当前的arr就是一个原数组的排列
public class Quiz2850石头分散到网格的最少移动次数 {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 2},
                {1, 1, 0},
                {0, 0, 1}};
        Quiz2850石头分散到网格的最少移动次数 quiz2850NeedReview = new Quiz2850石头分散到网格的最少移动次数();
        int res = quiz2850NeedReview.minimumMoves(grid);
        System.out.println(res);
    }


    public int minimumMoves(int[][] grid) {
        //记录所有流出石头和流入石头的单元，[i,j]单元如果有多个石头，就记录多次
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
        List<List<int[]>> from2List = permutations(from);
        //使用from的每一个排列（也就是from2）与to来计算曼哈顿距离之和
        for (List<int[]> from2 : from2List) {
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

    /**
     * 生成from的全排列，这个得到排列的技巧值得借鉴
     *
     * @param arr
     * @return
     */
    private List<List<int[]>> permutations(List<int[]> arr) {
        List<List<int[]>> result = new ArrayList<>();
        permute(arr, 0, result);
        return result;
    }

    //todo: 技巧，从start开始的for循环递归，每一层递归时交换i与start，遍历到底时，当前的arr就是一个原数组的排列
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
