package indi.simon.learning.复习.博弈;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 本题是一个自底向上递归的思路，因为存在博弈，那么在每一轮的博弈里面，选手需要做出对自己最有利的选择，每一轮的两个选择里面，最有利的选择是什么？
// 当然是能让自己在整场比赛中比对手获得更多分数的选择，那么哪一个才是让自己在整场比赛获胜更多的选择？不知道，至少本轮不知道，必须递归到底才能知道。
// 其实在游戏一开始谁赢谁输已经注定，输的那一个选手，直到递归到底才发现，自己每一轮虽然也是在选一个获胜更多的结果，但这个结果总是负的，无外乎比另一个负得少一些而已
public class Quiz486_2个选手_数组两头挑数字积分 {

    public static void main(String[] args) {
        Quiz486_2个选手_数组两头挑数字积分 quiz486Notfinish = new Quiz486_2个选手_数组两头挑数字积分();
        int[] nums = new int[]{1, 5, 233, 7};
        boolean res = quiz486Notfinish.PredictTheWinner(nums);
        System.out.println(res);
    }

    //记忆化回溯======================================================================================
    //todo: 典型的自底向上的回溯，每一层的结果都是一个虚的引用，要等递归触底了才能一层一层返回结果
    // 这么设计妙就妙在，将博弈融入到了递归中，递归的每一次调用，都是描述的对方选手的行为，易主了，所以递归方法的返回值就必须是A相较于B
    // （反过来就是B相较于A）的一个比较值才有意义
    private int[][] memo;

    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        memo = new int[len][len];
        int[][] memo = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }
        return scoreOver(nums, 0, len - 1) >= 0;
    }

    /**
     * @param nums
     * @param i
     * @param j
     * @return 当前选手在[i, j]区间可以比对手拿到多出的分数
     */
    private int scoreOver(int[] nums, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (memo[i][j] != Integer.MIN_VALUE) {
            return memo[i][j];
        }
        int chooseLeft = nums[i] - scoreOver(nums, i + 1, j);
        int chooseRight = nums[j] - scoreOver(nums, i, j - 1);
        memo[i][j] = Math.max(chooseLeft, chooseRight);
        return memo[i][j];
    }


}
