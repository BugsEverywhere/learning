package indi.simon.learning.复习.双指针_滑动窗口;

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
                //todo: 加速trick
                //如果最小数都大于0了，那没必要倒腾j和k了
                if (nums[i] > 0) {
                    break;
                }

                int sum = nums[i] + nums[j] + nums[k];
                //更新答案
                if (sum == 0) {
                    List<Integer> singleList = new ArrayList<>();
                    singleList.add(nums[i]);
                    singleList.add(nums[j]);
                    singleList.add(nums[k]);
                    res.add(singleList);
                }

                //todo: 注意本题可能有多个目标组合，因此这里迭代条件要覆盖sum==0，sum==0时迭代j或者k都行
                if (sum <= 0) {
                    //较小（或相等），右移j
                    //加起来比0小，则右移j
                    // todo: j移动时要跳过重复的
                    int j0 = j + 1;
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                } else {
                    //较大，左移k
                    //todo: k移动时也要跳过重复的
                    int k0 = k - 1;
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                }
            }
        }
        return res;
    }
}


