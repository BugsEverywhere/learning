package indi.simon.learning.leetcode.gogo20221031;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1620 {

    public static void main(String[] args) {
        Quiz1620 quiz1620 = new Quiz1620();
        int[] res = quiz1620.bestCoordinate(new int[][]{{0, 1, 2}, {2, 1, 2}, {1, 0, 2}, {1, 2, 2}}, 1);
        System.out.println(res);
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        //找到最远的一座塔所覆盖的最远的点
        int maxI = 0;
        int maxJ = 0;
        for (int i = 0; i < towers.length; i++) {
            maxI = Math.max(maxI, towers[i][0] + radius);
            maxJ = Math.max(maxJ, towers[i][1] + radius);
        }

        int[] xArr = new int[]{-1, 1, 0, 0};
        int[] yArr = new int[]{0, 0, -1, 1};

        //遍历所有的塔，算出所有的塔覆盖范围内的点的信号强度，单个点的强度累加，并记录最大信号强度
        int maxSignal = 0;
        int[][] points = new int[maxI + 1][maxJ + 1];
        for (int i = 0; i < towers.length; i++) {
            //单个塔只遍历一次
            boolean[][] mem = new boolean[2 * radius + 2][2 * radius + 2];
            //处理单个塔所覆盖的点
            Queue<int[]> towerPoints = new ArrayDeque<>();
            towerPoints.offer(new int[]{towers[i][0], towers[i][1]});
            mem[radius][radius] = true;
            while (towerPoints.size() > 0) {
                int[] currPoint = towerPoints.poll();
                double distance = Math.sqrt(Math.pow(currPoint[0] - towers[i][0], 2) + Math.pow(currPoint[1] - towers[i][1], 2));
                double signal = towers[i][2] / (1 + distance);
                //该点的信号加上该塔的信号强度
                points[currPoint[0]][currPoint[1]] += signal;
                maxSignal = Math.max(maxSignal, points[currPoint[0]][currPoint[1]]);
                //看一下该点的四周的点
                for (int k = 0; k < 4; k++) {
                    int pointX = currPoint[0] + xArr[k];
                    int pointY = currPoint[1] + yArr[k];
                    double distance1 = Math.sqrt(Math.pow(pointX - towers[i][0], 2) + Math.pow(pointY - towers[i][1], 2));
                    if (distance1 <= radius && pointX >= 0 && pointY >= 0 && !mem[pointX - towers[i][0] + radius][pointY - towers[i][1] + radius]) {
                        towerPoints.offer(new int[]{pointX, pointY});
                        mem[pointX - towers[i][0] + radius][pointY - towers[i][1] + radius] = true;
                    }
                }
            }
        }

        //遍历完了，从原点开始找到字典序最小的信号最强点
        for (int i = 0; i < maxI; i++) {
            for (int j = 0; j < maxJ; j++) {
                if (points[i][j] == maxSignal) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

}
