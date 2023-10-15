package indi.simon.learning.leetcode.gogo20230828;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chen Zhuo on 2023/9/3.
 */
//我这个解法是按照最多往回跳两次来搞的，不符合题意，暂时搁置这题
public class Quiz1654_needReview {

    public static void main(String[] args) {
        Integer i = -7;
        System.out.println(Integer.toBinaryString(i));
        int t = i >> 31;
        System.out.println(Integer.toBinaryString(t));

//        Quiz1654_needReview quiz1654NeedReview = new Quiz1654_needReview();
//        int res = quiz1654NeedReview.minimumJumps(new int[]{162, 118, 178, 152, 167, 100, 40, 74, 199, 186, 26, 73, 200, 127, 30, 124, 193, 84, 184, 36, 103, 149, 153, 9, 54, 154, 133, 95, 45, 198, 79, 157, 64, 122, 59, 71, 48, 177, 82, 35, 14, 176, 16, 108, 111, 6, 168, 31, 134, 164, 136, 72, 98}, 29, 98, 80);
//        System.out.println(res);
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {

        Set<Integer> forbiddenSet = new HashSet<>();
        for (int num : forbidden) {
            forbiddenSet.add(num);
        }

        for (int j = 0; j <= 2; j++) {
            if ((b * j + x) % a == 0) {
                int i = (b * j + x) / a;
                //往前跳i步，往后跳j步，后面校验跳的过程中是否会踩到禁区，需要回溯
                if (valid(i, 0, a, j, 0, b, forbiddenSet)) {
                    return i + j;
                }
            }
        }

        return -1;
    }

    private boolean valid(int i, int m, int a, int j, int n, int b, Set<Integer> forbiddenSet) {
        //中间过程小于零或者踩到禁区了都不能考虑
        if (a * m - b * n < 0 || forbiddenSet.contains(a * m - b * j)) {
            return false;
        }
        if (m == i && n == j) {
            return true;
        }
        if (m > i || n > j) {
            return false;
        }
        //下一步往前跳
        boolean forward = valid(i, m + 1, a, j, n, b, forbiddenSet);
        //下一步往后跳
        boolean backward = valid(i, m, a, j, n + 1, b, forbiddenSet);

        return forward || backward;
    }


}
