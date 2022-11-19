package indi.simon.learning.leetcode.gogo20221107;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz764_needReview {

    public static void main(String[] args) {
        Quiz764_needReview quiz764NeedReview = new Quiz764_needReview();
        int res = quiz764NeedReview.orderOfLargestPlusSign(1, new int[][]{{0, 0}});
        System.out.println(res);
    }


    //todo: 官方DP
    public int orderOfLargestPlusSignOfficialDp(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }
        Set<Integer> banned = new HashSet<Integer>();
        for (int[] vec : mines) {
            banned.add(vec[0] * n + vec[1]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            /* left */
            for (int j = 0; j < n; j++) {
                if (banned.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
            count = 0;
            /* right */
            for (int j = n - 1; j >= 0; j--) {
                if (banned.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }
        for (int i = 0; i < n; i++) {
            int count = 0;
            /* up */
            for (int j = 0; j < n; j++) {
                if (banned.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
            }
            count = 0;
            /* down */
            for (int j = n - 1; j >= 0; j--) {
                if (banned.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }


    //todo: 穷举超时
    public int orderOfLargestPlusSign(int n, int[][] mines) {

        //遍历所有0点，先记录下来
        boolean[][] zeros = new boolean[n][n];
        for (int i = 0; i < mines.length; i++) {
            zeros[mines[i][0]][mines[i][1]] = true;
        }

        int maxK = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //0直接跳过
                if (zeros[i][j]) {
                    continue;
                }
                //开始从该点bfs
                int maxKSub = 1;
                Queue<Point> queue = new ArrayDeque<>();
                queue.add(new Point(i, j, 0));
                while (queue.size() > 0) {
                    Point currPoint = queue.poll();
                    if (currPoint.getDir() == 0) {
                        //中心点
                        if (currPoint.getI() + 1 < n && !zeros[currPoint.getI() + 1][currPoint.getJ()]) {
                            //考察中心点的右向点
                            queue.add(new Point(currPoint.getI() + 1, currPoint.getJ(), 1));
                        } else {
                            break;
                        }

                        if (currPoint.getJ() + 1 < n && !zeros[currPoint.getI()][currPoint.getJ() + 1]) {
                            //考察中心点的下向点
                            queue.add(new Point(currPoint.getI(), currPoint.getJ() + 1, 2));
                        } else {
                            break;
                        }

                        if (currPoint.getI() - 1 >= 0 && !zeros[currPoint.getI() - 1][currPoint.getJ()]) {
                            //考察中心点的左向点
                            queue.add(new Point(currPoint.getI() - 1, currPoint.getJ(), 3));
                        } else {
                            break;
                        }

                        if (currPoint.getJ() - 1 >= 0 && !zeros[currPoint.getI()][currPoint.getJ() - 1]) {
                            //考察中心点的上向点
                            queue.add(new Point(currPoint.getI(), currPoint.getJ() - 1, 4));
                        } else {
                            break;
                        }
                    } else if (currPoint.getDir() == 1) {
                        //因为该方向是每一轮的开始，需要增加轮次
                        maxKSub++;
                        //向右点
                        if (currPoint.getI() + 1 < n && !zeros[currPoint.getI() + 1][currPoint.getJ()]) {
                            //考察该点的右向点
                            queue.add(new Point(currPoint.getI() + 1, currPoint.getJ(), 1));
                        } else {
                            break;
                        }
                    } else if (currPoint.getDir() == 2) {
                        //向下点
                        if (currPoint.getJ() + 1 < n && !zeros[currPoint.getI()][currPoint.getJ() + 1]) {
                            //考察该点的下向点
                            queue.add(new Point(currPoint.getI(), currPoint.getJ() + 1, 2));
                        } else {
                            break;
                        }
                    } else if (currPoint.getDir() == 3) {
                        //向左点
                        if (currPoint.getI() - 1 >= 0 && !zeros[currPoint.getI() - 1][currPoint.getJ()]) {
                            //考察该点的左向点
                            queue.add(new Point(currPoint.getI() - 1, currPoint.getJ(), 3));
                        } else {
                            break;
                        }
                    } else if (currPoint.getDir() == 4) {
                        //向上点
                        if (currPoint.getJ() - 1 >= 0 && !zeros[currPoint.getI()][currPoint.getJ() - 1]) {
                            //考察该点的上向点
                            queue.add(new Point(currPoint.getI(), currPoint.getJ() - 1, 4));
                        } else {
                            break;
                        }
                    }
                }
                maxK = Math.max(maxK, maxKSub);
            }
        }

        return maxK;
    }

    private class Point {

        private int i;

        private int j;
        //1向右，2向下，3向左，4向上
        private int dir;

        public Point(int i, int j, int dir) {
            this.i = i;
            this.j = j;
            this.dir = dir;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        public int getDir() {
            return dir;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }
    }

}
