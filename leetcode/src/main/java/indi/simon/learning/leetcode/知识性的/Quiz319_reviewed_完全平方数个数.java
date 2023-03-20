package indi.simon.learning.leetcode.知识性的;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz319_reviewed_完全平方数个数 {

    public static void main(String[] args) {
        Quiz319_reviewed_完全平方数个数 quiz319Reviewed完全平方数个数 = new Quiz319_reviewed_完全平方数个数();
        int res = quiz319Reviewed完全平方数个数.bulbSwitch(99999);
        System.out.println(res);
    }

    //todo: 官方醍醐灌顶，如果某数是完全平方数，那么他就肯定有奇数个约数，否则他的约数肯定是成对出现的，
    // 所以只需找到1~n以内完全平方数的个数即可，这里记住根号n向下取整就是n以内完全平方数个数这个知识
    public int bulbSwitchOfficial(int n) {
        return (int) Math.sqrt(n + 0.5);
    }


    //todo: 自己的方式，超时，太老实了，真的去统计每个数的所有约数，其实完全没必要，因为只需要知道该数的约数个数是奇数还是偶数
    public int bulbSwitch(int n) {
        if (n == 0) {
            return 0;
        }

        int lightCount = 1;

        Map<Integer, Set<Integer>> factors = new HashMap<>();
        Set<Integer> oneFactor = new HashSet<>();
        oneFactor.add(1);
        factors.put(1, oneFactor);

        for (int i = 2; i <= n; i++) {
            Set<Integer> iFactor = new HashSet<>();
            factors.put(i, iFactor);
            for (int j = i / 2; j >= 1; j--) {
                if (i % j == 0 && !iFactor.contains(j)) {
                    //i可被j整除，说明j是i的约数，factors[i]取factors[j]并且视情况添加自己和除数即可
                    iFactor.add(i);
                    iFactor.addAll(factors.get(j));
                    if (!factors.get(j).contains(i / j)) {
                        iFactor.add(i / j);
                    }
                }
            }
            if (iFactor.size() > 2) {
                //非质数，有多少个约数就会被动几次
                if (iFactor.size() % 2 != 0) {
                    //包含奇数个约数的灯泡，最终是亮的
                    lightCount++;
                }
            }
        }

        return lightCount;
    }

}
