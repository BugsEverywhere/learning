package indi.simon.learning.leetcode.gogo20220425;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 此题注意备忘录的更新必须放在DFS方法之外，不能在DFS方法里面就更新备忘录，因为DFS的结果并不代表对该区块的完整结果，例如考虑两个相同值的相邻区块a和b，假设a->b的探索不可以流向大海，
// 但是b-a的探索可以流向大海，这样其实两个区块都能流向大海，但是我们假如先进行的是a->b的探索，因为有脚印的存在，会先入为主的判断这种方式到不了大海，如果这个时候更新备忘录，那么这个结果就是
// 不全面的
public class Quiz417_reviewed {

    public static void main(String[] args) {
        int[][] heights = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        Quiz417_reviewed quiz417 = new Quiz417_reviewed();
        List<List<Integer>> res = quiz417.pacificAtlantic(heights);
        System.out.println(res);
    }

    Boolean[][] pacificMem;
    Boolean[][] atlanticMem;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights.length == 0) {
            return null;
        }
        pacificMem = new Boolean[heights.length][heights[0].length];
        atlanticMem = new Boolean[heights.length][heights[0].length];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                pacificAtlanticInternal(heights, i, j, res);
            }
        }
        return res;
    }

    private void pacificAtlanticInternal(int[][] heights, int i, int j, List<List<Integer>> res) {
        if (i >= heights.length || j >= heights[0].length) {
            return;
        }

        boolean pacificRes;
        if (pacificMem[i][j] != null) {
            pacificRes = pacificMem[i][j];
        } else {
            pacificRes = findCoast(heights, i, j, true, new boolean[heights.length][heights[0].length]);
            pacificMem[i][j] = pacificRes;
        }

        boolean atlanticRes;
        if (atlanticMem[i][j] != null) {
            atlanticRes = atlanticMem[i][j];
        } else {
            atlanticRes = findCoast(heights, i, j, false, new boolean[heights.length][heights[0].length]);
            atlanticMem[i][j] = atlanticRes;
        }

        //整理结果
        if (pacificRes && atlanticRes) {
            List<Integer> thisCoordinate = new ArrayList<>();
            thisCoordinate.add(i);
            thisCoordinate.add(j);
            res.add(thisCoordinate);
        }

    }

    private boolean findCoast(int[][] heights, int i, int j, boolean pacificWards, boolean[][] footPrint) {

        footPrint[i][j] = true;

        if ((i <= 0 || j <= 0) && pacificWards) {
            return true;
        } else if ((i >= heights.length - 1 || j >= heights[0].length - 1) && !pacificWards) {
            return true;
        }

        if (pacificWards) {
            if (pacificMem[i][j] != null) {
                return pacificMem[i][j];
            }
            boolean pacificRes = false;
            if (heights[i - 1][j] <= heights[i][j] && !footPrint[i - 1][j]) {
                pacificRes = findCoast(heights, i - 1, j, pacificWards, footPrint);
            }
            if (heights[i][j - 1] <= heights[i][j] && !footPrint[i][j - 1]) {
                pacificRes = pacificRes | findCoast(heights, i, j - 1, pacificWards, footPrint);
            }
            if (i + 1 < heights.length && heights[i + 1][j] <= heights[i][j] && !footPrint[i + 1][j]) {
                pacificRes = pacificRes | findCoast(heights, i + 1, j, pacificWards, footPrint);
            }
            if (j + 1 < heights[0].length && heights[i][j + 1] <= heights[i][j] && !footPrint[i][j + 1]) {
                pacificRes = pacificRes | findCoast(heights, i, j + 1, pacificWards, footPrint);
            }
            return pacificRes;
        } else {
            if (atlanticMem[i][j] != null) {
                return atlanticMem[i][j];
            }
            boolean atlanticRes = false;
            if (heights[i][j + 1] <= heights[i][j] && !footPrint[i][j + 1]) {
                atlanticRes = findCoast(heights, i, j + 1, pacificWards, footPrint);
            }
            if (heights[i + 1][j] <= heights[i][j] && !footPrint[i + 1][j]) {
                atlanticRes = atlanticRes | findCoast(heights, i + 1, j, pacificWards, footPrint);
            }
            if (j - 1 >= 0 && heights[i][j - 1] <= heights[i][j] && !footPrint[i][j - 1]) {
                atlanticRes = atlanticRes | findCoast(heights, i, j - 1, pacificWards, footPrint);
            }
            if (i - 1 >= 0 && heights[i - 1][j] <= heights[i][j] && !footPrint[i - 1][j]) {
                atlanticRes = atlanticRes | findCoast(heights, i - 1, j, pacificWards, footPrint);
            }
            return atlanticRes;
        }
    }


}
