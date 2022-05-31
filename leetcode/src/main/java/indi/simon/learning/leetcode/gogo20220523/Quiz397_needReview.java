package indi.simon.learning.leetcode.gogo20220523;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 可以作为记忆化回溯的经典案例，integerReplacementV1是带记忆化回溯，与官方的很像。V2是不带记忆化的回溯，可以改造成V1。V3是另一种不带记忆化的回溯，不能改造成V1
// 一点感想，这种走到每一步，后面的迭代逻辑都是确定的情况是可以使用记忆化回溯的，还有另一种求最值回溯无法使用记忆化，因为每一步回溯面临多种情况，你必须穷举才能遍历所有可能
// 从而求得最值，记忆化反而会限制你求最值
public class Quiz397_needReview {

    public static void main(String[] args) {
        Quiz397_needReview quiz397NeedReview = new Quiz397_needReview();
        int res = quiz397NeedReview.integerReplacementV3(1234);
        System.out.println(res);
    }

    //---------------------------自己的带记忆化的回溯

    private Map<Long, Integer> mem;

    public int integerReplacementV1(int n) {
        mem = new HashMap<>();
        int res = findInternalV1(n);
        return res;
    }


    private int findInternalV1(long curr) {
        if (curr == 1) {
            return 0;
        }

        if (mem.get(curr) != null) {
            return mem.get(curr);
        }

        if (curr % 2 == 0) {
            int halfRes = findInternalV1(curr / 2) + 1;
            mem.put(curr, halfRes);
            return mem.get(curr);
        } else {
            int plusRes = findInternalV1(curr + 1) + 1;
            int minusRes = findInternalV1(curr - 1) + 1;
            int minRes = Math.min(minusRes, plusRes);
            mem.put(curr, minRes);
            return mem.get(curr);

        }
    }


    //-------------------不带记忆化

    public int integerReplacementV2(int n) {
        return findInternalV2(n);
    }


    private int findInternalV2(long curr) {

        if (curr == 1) {
            return 0;
        }

        if (curr % 2 == 0) {
            return findInternalV2(curr / 2) + 1;
        } else {
            return Math.min(findInternalV2(curr + 1) + 1, findInternalV2(curr - 1) + 1);

        }
    }

    //--------------------挫逼的不带记忆法

    private int minSteps;

    public int integerReplacementV3(int n) {
        minSteps = Integer.MAX_VALUE;
        findInternalV3(n, 0);
        return minSteps;
    }


    private void findInternalV3(long curr, int path) {

        if (curr == 1) {
            if (path >= 0 && path < minSteps) {
                minSteps = path;
            }
            return;
        }

        if (curr % 2 == 0) {
            findInternalV3(curr / 2, path + 1);
        } else {
            findInternalV3(curr + 1, path + 1);
            findInternalV3(curr - 1, path + 1);
        }
    }


    //--------------------官方记忆化回溯
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int integerReplacementOfficial(int n) {
        if (n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            if (n % 2 == 0) {
                memo.put(n, 1 + integerReplacementOfficial(n / 2));
            } else {
                memo.put(n, 2 + Math.min(integerReplacementOfficial(n / 2), integerReplacementOfficial(n / 2 + 1)));
            }
        }
        return memo.get(n);
    }


}
