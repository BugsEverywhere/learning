package indi.simon.learning.leetcode.gogo20231030;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/10/29.
 *
 * 在一个二维平面空间中，给你 n 个点的坐标。问，是否能找出一条平行于 y 轴的直线，让这些点关于这条直线成镜像排布？
 *
 * 注意：题目数据中可能有重复的点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：points = [[1,1],[-1,1]]
 * 输出：true
 * 解释：可以找出 x = 0 这条线。
 * 示例 2：
 *
 *
 * 输入：points = [[1,1],[-1,-1]]
 * 输出：false
 * 解释：无法找出这样一条线。
 *
 *
 * 提示：
 *
 * n == points.length
 * 1 <= n <= 10^4
 * -10^8 <= points[i][j] <= 10^8
 *
 *
 * 进阶：你能找到比 O(n2) 更优的解法吗?
 */
public class Quiz356_needReview {

    public static void main(String[] args) {
        Quiz356_needReview quiz356NeedReview = new Quiz356_needReview();
        boolean res = quiz356NeedReview.isReflected(new int[][]{{1, 1}, {-1, 1}});
        System.out.println(res);
    }

    public boolean isReflected(int[][] points) {
        Map<Double, Map<Double, List<double[]>>> heightMap = new HashMap<>();
        for (int[] point : points) {
            Map<Double, List<double[]>> map = heightMap.getOrDefault((double) point[1], new HashMap<>());
            List<double[]> sameXPoints = map.getOrDefault((double) point[0], new ArrayList<>());
            sameXPoints.add(new double[]{point[0], point[1]});
            map.put((double) point[0], sameXPoints);
            heightMap.put((double) point[1], map);
        }

        Double yAxis = null;
        for (Map.Entry<Double, Map<Double, List<double[]>>> sameLevelPoints : heightMap.entrySet()) {
            Map<Double, List<double[]>> pointsOnSameLevel = sameLevelPoints.getValue();
            double sum = 0;
            for (Map.Entry<Double, List<double[]>> pointsOfSameLevelSameXAxis : pointsOnSameLevel.entrySet()) {
                List<double[]> list = pointsOfSameLevelSameXAxis.getValue();
                for (double[] point : list) {
                    sum += point[0];
                }
            }
            double yAxisOnThisLevel;
            yAxisOnThisLevel = sum / (double) pointsOnSameLevel.size();
            if (yAxis == null) {
                yAxis = yAxisOnThisLevel;
            } else if (yAxis != yAxisOnThisLevel) {
                return false;
            }
            //double check
            for (Map.Entry<Double, List<double[]>> pointsOfSameLevelSameXAxis : pointsOnSameLevel.entrySet()) {
                List<double[]> list = pointsOfSameLevelSameXAxis.getValue();
                if (pointsOfSameLevelSameXAxis.getKey() >= yAxisOnThisLevel) {
                    double mirror = 2 * yAxisOnThisLevel - pointsOfSameLevelSameXAxis.getKey();
                    if (!pointsOnSameLevel.containsKey(mirror)) {
                        return false;
                    }
                    if (pointsOnSameLevel.get(mirror).size() == list.size()) {
                        continue;
                    } else {
                        return false;
                    }
                } else {
                    double mirror = pointsOfSameLevelSameXAxis.getKey() + 2 * (yAxisOnThisLevel - pointsOfSameLevelSameXAxis.getKey());
                    if (!pointsOnSameLevel.containsKey(mirror)) {
                        return false;
                    }
                    if (pointsOnSameLevel.get(mirror).size() == list.size()) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }


}
