package indi.simon.learning.leetcode.gogo20231030;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/10/29.
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
