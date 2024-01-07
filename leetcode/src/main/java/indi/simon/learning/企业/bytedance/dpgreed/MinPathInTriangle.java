package indi.simon.learning.leetcode.bytedance.dpgreed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinPathInTriangle {

    public static void main(String[] args) {

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<Integer>() {{
            add(2);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(3);
            add(4);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(6);
            add(5);
            add(7);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(4);
            add(1);
            add(8);
            add(3);
        }});

        int res = minimumTotal(triangle);
        System.out.println(res);

    }

    private static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) return 0;
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int minTotal = Integer.MAX_VALUE;
        int[] aux = new int[triangle.size()];//声明一个辅助数组用于存储f[i][j]的数据
        Arrays.fill(aux, Integer.MAX_VALUE);
        aux[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                if (j != 0) {
                    aux[j] = Math.min(aux[j], aux[j - 1]) + triangle.get(i).get(j);
                } else {
                    aux[j] = aux[j] + triangle.get(i).get(j);
                }
            }
        }
        for (int i = 0; i < aux.length; i++) {
            minTotal = Math.min(aux[i], minTotal);
        }
        return minTotal;
    }


}
