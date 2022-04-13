package indi.simon.learning.leetcode.gogo20220411;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz357_toBeReviewed {

    public static void main(String[] args) {
        Quiz357_toBeReviewed quiz357 = new Quiz357_toBeReviewed();
        int res = quiz357.countNumbersWithUniqueDigits(8);
        System.out.println(res);
    }


    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        return countNumbersWithUniqueDigitsInternal(n, 1, new HashSet<>(), false);
    }

    /**
     * @param n
     * @param i
     * @param alreadyAdded 前面已经添加过的数
     * @param started      标志位，开头已经不是0了，为true，开头仍然是0，就位false，比如0000..，为false，0008，为start
     * @return
     */
    private int countNumbersWithUniqueDigitsInternal(int n, int i, Set<Integer> alreadyAdded, boolean started) {
        if (i > n) {
            return 1;
        }

        //遍历在该位置上放0~9不同数字的情况，往下递归
        int totalCount = 0;
        for (int j = 0; j <= 9; j++) {
            if (alreadyAdded.contains(j)) {
                continue;
            }

            if (j != 0 || started) {
                //避免把以0打头的0算计去
                alreadyAdded.add(j);
            }
            totalCount = totalCount + countNumbersWithUniqueDigitsInternal(n, i + 1, alreadyAdded, j != 0 || started);
            if (j != 0 || started) {
                //避免把以0打头的0算计去
                alreadyAdded.remove(j);
            }
        }

        return totalCount;

    }

    //todo: Arrays.fill就只用来填充一维数组吧，二维数组就不好使了，假如Arrays.fill(twoDimArr, new int[]{-1,-1}),会有问题，相当于二维数组每一行都是填充的同一个一维数组的引用
    //todo: 备忘录一定要覆盖递归方法的所有可变入参，比如上面这个countNumbersWithUniqueDigitsInternal方法里面，如果想做备忘录，那么备忘录要覆盖i, alreadyAdded, started。
    // 显然alreadyAdded不太好搞
}
