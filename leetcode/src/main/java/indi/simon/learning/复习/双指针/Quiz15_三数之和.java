package indi.simon.learning.复习.双指针;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class Quiz15_三数之和 {

    public static void main(String[] args) {

//        int[] test = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        int[] test = {-4, 0, 1, 4, 4, 4};
        List<List<Integer>> result = threeSum(test);
        System.out.println(result);

    }

    private static List<List<Integer>> threeSum(int[] nums) {
        //todo: 排序排序！
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        int i;
        for (i = 0; i < nums.length - 2; i++) {
            //todo: 第一个注意的点，i指针要跳过重复的
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = nums.length - 1;
            int j = i + 1;
            while (j < k) {
                //如果最小数都大于0了，那没必要倒腾j和k了
                if (nums[i] > 0) {
                    break;
                }
                //加起来比0小，则右移j
                if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                    continue;
                }
                //todo: 第2个注意的点，j指针要跳过重复的
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    j++;
                    continue;
                }
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> singleList = new ArrayList<>();
                    singleList.add(nums[i]);
                    singleList.add(nums[j]);
                    singleList.add(nums[k]);
                    res.add(singleList);
                    //找一个符合条件的组合后，j前移，这样，只需要对i和j保证不重复即可，k移动时无需判断是否重复
                    j++;
                } else {
                    //todo: 第3个注意的点，k指针左移之后，j原地不动
                    k--;
                }
            }
        }

        return res;
    }
}


