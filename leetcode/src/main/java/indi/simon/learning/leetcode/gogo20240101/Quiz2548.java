package indi.simon.learning.leetcode.gogo20240101;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Chen Zhuo on 2024/1/7.
 */
public class Quiz2548 {

    public static void main(String[] args) {
        Quiz2548 quiz2548 = new Quiz2548();
        double res = quiz2548.maxPrice(new int[][]{{17, 19}, {15, 5}, {3, 7}, {5, 9}}, 29);
        System.out.println(res);
    }

    public double maxPrice(int[][] items, int capacity) {

        //按照价格对所有物品排序，优先放入单位价格更高物品
        Arrays.sort(items, (o1, o2) -> Double.compare((double) o2[0] / (double) o2[1], (double) o1[0] / (double) o1[1]));

        double remain = capacity;
        double totalCost = 0;
        for (int i = 0; i < items.length; i++) {
            if (remain > items[i][1]) {
                totalCost += items[i][0];
                remain -= items[i][1];
            } else {
                //需要对该物品进行切分
                //todo: 根据剩余所需的remain来切分！！不要搞错了
                double ratio = remain / (double) items[i][1];
                totalCost += (double) items[i][0] * ratio;
                remain = 0;
            }
            if (remain <= 0) {
                break;
            }
        }

        if (remain > 0) {
            return -1;
        }

        return totalCost;
    }


}
