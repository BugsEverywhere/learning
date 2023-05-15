package indi.simon.learning.leetcode.gogo20220228;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz553 {

    public static void main(String[] args) {
        Quiz553 quiz553 = new Quiz553();
        String res = quiz553.optimalDivision(new int[]{1000, 100, 10});
        System.out.println(res);
    }

    public String optimalDivision(int[] nums) {
        if (nums.length == 1) {
            return Integer.toString(nums[0]);
        }
        if (nums.length == 2) {
            return nums[0] + "/" + nums[1];
        }

        String fenZi = Integer.toString(nums[0]);
        StringBuilder fenMu = new StringBuilder("");
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                fenMu.append("(").append(nums[i]);
            } else if (i == nums.length - 1) {
                fenMu.append("/").append(nums[i]).append(")");
            } else {
                fenMu.append("/").append(nums[i]);
            }
        }

        return fenZi + "/" + fenMu.toString();
    }

}
