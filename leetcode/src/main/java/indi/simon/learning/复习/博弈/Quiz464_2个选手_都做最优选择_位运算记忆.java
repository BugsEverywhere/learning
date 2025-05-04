package indi.simon.learning.复习.博弈;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 选手都会做出对自己最有利的选择 == 只要有一种可能赢就会选这种
public class Quiz464_2个选手_都做最优选择_位运算记忆 {

    public static void main(String[] args) {
        Quiz464_2个选手_都做最优选择_位运算记忆 quiz464Notfinish = new Quiz464_2个选手_都做最优选择_位运算记忆();
        boolean res = quiz464Notfinish.canIWin(10, 10);
        System.out.println(res);
    }

    //todo:另外，这题博弈的考点就是，每一轮里面，选手都会做出对自己最有利的选择，那么什么才是最有利的选择，如果有十个选择，那么能让自己赢的选择都是最有利的选择，因此只需要这十个选择
    // 中有一个选择最终都是自己赢（也就是下一轮的结果是对手输），那么当然最有利的选择就是这一个，那么就应该认为他会理所当然选择这一个，并且在这一轮里面获胜
    private Map<Integer, Boolean> mem;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            sum += i;
        }
        if (sum < desiredTotal) {
            return false;
        }
        mem = new HashMap<>();
        return pick(maxChoosableInteger, desiredTotal, 0, 0);
    }

    /**
     * @param maxChoosableInteger
     * @param desiredTotal
     * @param usedNum
     * @param sum
     * @return 当前选手（有可能是甲，也有可能是乙）是否能赢，能则为true，否则为false
     */
    //todo: 位运算的usedNum放入递归入参，不要定义为private，不好驾驭
    // private的仅仅只有备忘录哈希表
    private boolean pick(int maxChoosableInteger, int desiredTotal, int usedNum, int sum) {
        if (mem.containsKey(usedNum)) {
            return mem.get(usedNum);
        }

        boolean res = false;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((usedNum >> i) & 1) == 1) {
                //该数用过，跳过去
                continue;
            }
            if (sum + i >= desiredTotal) {
                //当前选手选了i，然后赢了，直接返回true
                return true;
            } else {
                //当前选手选了i，但是仍然不够，那么继续递归下去，因为下一次是对方的结果，那么如果在本轮所有数往下递归的结果都是对方赢（结果为true），那么他就输了。只要有一种可能是自己赢，那么就算做他能赢
                res = res || !pick(maxChoosableInteger, desiredTotal, usedNum | (1 << i), sum + i);
            }
        }
        mem.put(usedNum, res);
        return res;
    }

}
