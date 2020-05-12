package indi.simon.learning.leetcode.gogo2020april.week27to;

public class P200 {

    public static void main(String[] args) {

        int result = numIslands(new char[][]{{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}});
        System.out.println(result);
    }

    private static boolean[][] mem = null;
    private static int islandsNum = 0;

    public static int numIslands(char[][] grid) {

        if (grid.length <= 0 || grid[0].length <= 0) {
            return -1;
        }

        mem = new boolean[grid.length][grid[0].length];
        //todo 两层循环来遍历网格
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (mem[i][j] || grid[i][j] == '0') {
                    mem[i][j] = true;
                    continue;
                }
                islandExplore(i, j, grid);
                islandsNum++;
            }
        }
        return islandsNum;
    }

    private static void islandExplore(int i, int j, char[][] grid) {
        //todo 递归的时候，永远在递归函数入口处就看一下备忘录
        if (mem[i][j]) {
            return;
        }
        mem[i][j] = true;
        //todo 别忘了边界条件！
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
            islandExplore(i, j + 1, grid);
        }

        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            islandExplore(i + 1, j, grid);
        }
        //todo 后两种情况不能忘！
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            islandExplore(i, j - 1, grid);
        }

        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            islandExplore(i - 1, j, grid);
        }
    }


}
