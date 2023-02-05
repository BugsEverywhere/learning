package indi.simon.learning.leetcode.贪心;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1798_reviewed {

    public static void main(String[] args) {
        Quiz1798_reviewed quiz1798NeedReview = new Quiz1798_reviewed();
        int res = quiz1798NeedReview.getMaximumConsecutive(new int[]{1, 1, 1, 596, 266, 210, 766, 579, 1, 195, 1, 1, 230, 1, 465, 1, 1, 1, 538, 1, 1, 125, 624, 62, 239, 1, 1, 1, 1, 874, 1, 307, 186, 1, 1, 879, 1, 933, 681, 680, 1, 1, 1, 757, 1, 903, 975, 104, 1, 742, 516, 1, 541, 1, 1, 1, 661, 529, 628, 721, 1, 1, 38, 493, 434, 813, 270, 380, 1, 1, 1, 448, 226, 1, 1, 1, 1, 1, 360, 1, 411, 699, 717, 1, 1, 483, 1, 1, 1, 1, 1, 1, 427, 1, 931, 857, 871, 1, 96, 1, 1, 556, 898, 1, 1, 1, 1, 873, 1, 608, 1, 515, 134, 1, 1, 606, 780, 863, 1, 1, 1, 774, 87, 639, 1, 1, 209, 1, 394, 1, 1, 864, 1, 395, 737, 523, 1, 348, 1, 328, 1, 16, 1, 1, 1, 1, 1, 210, 789, 729, 1, 713, 1, 614, 64, 429, 1, 1, 241, 866, 541, 324});
        System.out.println(res);
    }

    //todo: 官方贪心，这里使用贪心的方法是，找到一个我们能凑出的硬币面额区间[0,x]，然后我们再coins里面从小到大扫过去，来扩充这个区间，
    // 扩中的方法是，如果剩余硬币中最小面额是y，那么将该面额的硬币使用进来之后，我们可以凑出的面额区间肯定能覆盖[y,x+y]，结合之前我们已经能凑出的[0,x]，
    // 只要[0,x]和[y,x+y]能接上，也就是y<=x+1的话，那么我们就能将能凑出的面额区间扩充到[0,x+y]，否则两个区间接不上，到此就结束了，能凑出的最大数也就是x。
    // 如果能凑上，此时区间新的上限就是x+y，再重复以上步骤继续遍历coins即可
    public int getMaximumConsecutiveOfficial(int[] coins) {
        //res代表目前为止我们所能凑出的面额的区间上限+1，初始值是1的话，代表我们任何硬币都不选，就能凑出[0,0]这个面额区间，其上限是0，加一得res，也就是1
        int res = 1;
        //一定要排序，因为需要从小找到大，
        Arrays.sort(coins);
        for (int i : coins) {
            if (i > res) {
                break;
            }
            res += i;
        }
        return res;
    }

    //todo: 自己的答案，超时
    public int getMaximumConsecutive(int[] coins) {

        Map<Integer, Integer> coinCount = new HashMap<>();
        for (int coin : coins) {
            coinCount.merge(coin, 1, Integer::sum);
        }

        //记录了每一个数所消耗的不同面额的硬币个数
        Map<Integer, Map<Integer, Integer>> usedCoinMem = new HashMap<>();
        int i = 0;
        while (true) {
            boolean success = false;
            for (int j = i - 1; j >= 0; j--) {
                //拿出之前j所用到的硬币不同面额的个数
                Map<Integer, Integer> coinMem = usedCoinMem.get(j);
                //赋值一份所有硬币面额的登记簿
                Map<Integer, Integer> totalCoinCount1 = new HashMap<>(coinCount);
                //拿掉之前j用掉的面额，剩下的是凑本数i可以使用的面额
                if (coinMem != null) {
                    for (Map.Entry<Integer, Integer> entry : coinMem.entrySet()) {
                        int totalCount = totalCoinCount1.get(entry.getKey());
                        if (totalCount == entry.getValue()) {
                            totalCoinCount1.remove(entry.getKey());
                        } else {
                            totalCoinCount1.put(entry.getKey(), totalCount - entry.getValue());
                        }
                    }
                }
                //判断剩余的硬币是否还能拼出一个i
                if (totalCoinCount1.containsKey(i - j)) {
                    success = true;
                    Map<Integer, Integer> coinMemI = new HashMap<>();
                    if (coinMem != null) {
                        coinMemI.putAll(coinMem);
                        coinMemI.merge(i - j, 1, Integer::sum);
                    } else {
                        coinMemI.put(i - j, 1);
                    }
                    usedCoinMem.put(i, coinMemI);
                    break;
                }
            }
            if (!success && i != 0) {
                break;
            }
            i++;
        }

        return i;
    }

}
