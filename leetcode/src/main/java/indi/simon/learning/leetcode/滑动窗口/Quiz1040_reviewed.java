package indi.simon.learning.leetcode.滑动窗口;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 首先将这些石子儿按照所处位置从小到大排好序，那么他们中间肯定有很多空位，他们目前是发散的，题目的意思是将他们逐步收敛聚拢，算出需要移动的最大步数以及最小步数。
// 最大步数就是最繁琐的移动方式，假设上述排好序的石子儿最左端点是老幺，最右端点是老大，那么最繁琐的方式就是把老幺到老大之间的所有空位都走一遍。例如下面这个排列：
// 2,4,5,8,10,13,15,19，老幺2和老大19之间一共有10个空位，那么每次移动都把端点石子儿移到最外层的空位，那么这9个空位都会轮一遍（除了位置3和18，因为这俩比较特殊，
// 假如我们第一步移动2，3这个位置就作废了，同理，假如第一步移动19，那么16，17，18就作废了），所以最大步骤数就是老幺和老大之间的空位数，然后减去头尾两块空地中小的那一个，
// 留下较大块的空地，以上述排列为例，那么就是留下15到19之间这块空地，因为这段空位多。
// 而最小的移动次数，就是假设石子儿一共有n个，需要计算整个老幺和老大之间，在长度为n的区间内，哪一块有最密集的石子儿，那么其他石子儿都往这最密集的区域聚集即可，那么就是
// 滑动窗口走一遍就完事儿了。
public class Quiz1040_reviewed {

    public static void main(String[] args) {

    }

    public int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        Arrays.sort(stones);
        if (stones[n - 1] - stones[0] + 1 == n) {
            return new int[]{0, 0};
        }
        int ma = Math.max(stones[n - 2] - stones[0] + 1, stones[n - 1] - stones[1] + 1) - (n - 1);
        int mi = n;
        for (int i = 0, j = 0; i < n && j + 1 < n; ++i) {
            while (j + 1 < n && stones[j + 1] - stones[i] + 1 <= n) {
                ++j;
            }
            if (j - i + 1 == n - 1 && stones[j] - stones[i] + 1 == n - 1) {
                mi = Math.min(mi, 2);
            } else {
                mi = Math.min(mi, n - (j - i + 1));
            }
        }
        return new int[]{mi, ma};
    }


}
