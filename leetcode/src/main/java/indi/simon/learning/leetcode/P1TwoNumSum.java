package indi.simon.learning.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class P1TwoNumSum {

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(result);
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}

//todo:别人的示范，不是靠暴力搜索，使用了一个map来保存每次循环到的值，这样只要一个循环就能搞定，复杂度最坏为O(n)，
// 我那样最坏得O(n方)了。
//import java.util.HashMap;
//class Solution {
//    public int[] twoSum(int[] nums, int target) {
//        int[] res = new int[]{-1,-1};
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i=0; i<nums.length; i++){
//            if (map.containsKey(target - nums[i])){
//                res[0] = map.get(target - nums[i]);
//                res[1] = i;
//                break;
//            }
//            map.put(nums[i], i);
//        }
//        return res;
//    }
//}


//todo:启示就是，凡是马上要暴力搜索寻找多个值是否匹配某条件的时候，总是想想能不能用map缓存的方式来减少循环的层级