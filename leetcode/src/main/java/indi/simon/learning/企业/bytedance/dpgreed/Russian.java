package indi.simon.learning.企业.bytedance.dpgreed;

import java.util.*;

public class Russian {

    public static void main(String[] args) {

        int[][] arr = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int res = maxEnvelopes(arr);
        System.out.println(res);

    }

    private static int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        List<Rectangle> recList = new ArrayList<>();
        for (int[] enve : envelopes) {
            recList.add(new Rectangle(enve[0], enve[1]));
        }

        Collections.sort(recList, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                if (o1.width != o2.width) {
                    return o1.width - o2.width;
                } else {
                    return o1.heigth - o2.heigth;
                }
            }
        });

        int[] dp = new int[recList.size()];
        int max = -1;
        Arrays.fill(dp, 1);
        for (int i = 0; i < recList.size(); i++) {
            for (int j = i + 1; j < recList.size(); j++) {
                if (recList.get(i).width < recList.get(j).width && recList.get(i).heigth < recList.get(j).heigth) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

class Rectangle {
    int width;
    int heigth;

    public Rectangle(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
    }
}
