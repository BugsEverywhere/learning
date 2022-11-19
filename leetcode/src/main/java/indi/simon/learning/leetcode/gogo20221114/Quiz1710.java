package indi.simon.learning.leetcode.gogo20221114;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1710 {

    public static void main(String[] args) {
        Quiz1710 quiz1710 = new Quiz1710();
        int res = quiz1710.maximumUnits(new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4);
        System.out.println(res);
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        //将所有箱子按照其所能装载的数量从大到小排序
        Arrays.sort(boxTypes, (o1, o2) -> Integer.compare(o2[1], o1[1]));

        int unit = 0;
        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            //最大限度塞下该种箱子
            if (boxTypes[i][0] <= truckSize) {
                truckSize -= boxTypes[i][0];
                unit += boxTypes[i][1] * boxTypes[i][0];
            } else {
                unit += boxTypes[i][1] * truckSize;
                truckSize = 0;
            }
        }

        return unit;
    }

}
