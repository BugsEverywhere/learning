package indi.simon.learning.leetcode.回溯组合_防重复;

/**
 * @author chenzhuo(zhiyue)
 */

//todo: 我这种方法只能算出组合数，不能算出带顺序的排列组合数。看了官方解答，在DP的步骤划分上我除了问题，不能拿入参数组的元素遍历作为步骤，应该将 0 ~ target 的每一个数
public class Quiz377_notfinish {

    public static void main(String[] args) {
        Quiz377_notfinish quiz377Notfinish = new Quiz377_notfinish();
        int[] nums = new int[]{1, 2, 3};
        int res = quiz377Notfinish.combinationSum4(nums, 4);
        System.out.println(res);
    }

    public int combinationSum4(int[] nums, int target) {




        return -1;
    }
}
