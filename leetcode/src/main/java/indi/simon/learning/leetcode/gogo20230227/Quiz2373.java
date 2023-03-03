package indi.simon.learning.leetcode.gogo20230227;

import java.util.PriorityQueue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2373 {

    public static void main(String[] args) {
        Quiz2373 quiz2373 = new Quiz2373();
        int[][] res = quiz2373.largestLocal(new int[][]{{9, 9, 8, 1}, {5, 6, 2, 6}, {8, 2, 6, 4}, {6, 2, 2, 2}});
        System.out.println(res);
    }

    public int[][] largestLocal(int[][] grid) {

        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        int[][] res = new int[grid.length - 2][grid[0].length - 2];

        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[0].length - 2; j++) {
                fill(heap, i, j, grid, res);
            }
        }

        return res;
    }

    private void fill(PriorityQueue<Integer> heap, int i, int j, int[][] grid, int[][] res) {

        if (j == 0) {
            //新的一行，重新建堆
            for (int k = i; k < i + 3; k++) {
                for (int m = j; m < j + 3; m++) {
                    heap.add(grid[k][m]);
                }
            }
        } else {
            //没换行，只需要往堆里添加新的列元素
            heap.add(grid[i][j + 2]);
            heap.add(grid[i + 1][j + 2]);
            heap.add(grid[i + 2][j + 2]);
        }

        //取堆顶
        res[i][j] = heap.peek();

        //清理堆
        if (j == grid[0].length - 3) {
            //下一次就换行了，需要把堆清掉
            heap.clear();
        } else {
            //下一次不换行
            heap.remove(grid[i][j]);
            heap.remove(grid[i + 1][j]);
            heap.remove(grid[i + 2][j]);

        }
    }


}
