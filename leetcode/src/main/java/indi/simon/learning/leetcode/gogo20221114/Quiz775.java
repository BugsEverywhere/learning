package indi.simon.learning.leetcode.gogo20221114;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 第一次提交漏了2,0,3,1这种情况，就是两个局部倒置连起来了，因此新增了maxSoFar变量用来记住前面目前为止最小的数
public class Quiz775 {

    public static void main(String[] args) {
        Quiz775 quiz775 = new Quiz775();
        int[] nums = new int[]{0, 3, 1, 4, 2};
        boolean res = quiz775.isIdealPermutation(nums);
        System.out.println(res);
    }

    public boolean isIdealPermutation(int[] nums) {

        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 1; ) {
            if (nums[i] > nums[i + 1]) {
                //判断其是否是一个局部倒置
                if (nums[i + 1] > maxSoFar //倒置满足前续条件
                        && (i + 1 == nums.length - 1 || nums[i + 2] > nums[i])) {//倒置满足后续条件
                    maxSoFar = Math.max(maxSoFar, Math.max(nums[i], nums[i + 1]));
                    //是一个局部倒置
                    //todo: 第三次提交这里鬼使神差写成了i += i+2
                    i += 2;
                } else {
                    return false;
                }
            } else {
                //todo: 第二次提交是这两行顺序反了，结果maxSoFar赋错了值
                maxSoFar = Math.max(maxSoFar, nums[i]);
                i++;
            }
        }

        return true;
    }

}
