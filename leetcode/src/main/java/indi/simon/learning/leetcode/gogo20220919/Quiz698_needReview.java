package indi.simon.learning.leetcode.gogo20220919;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz698_needReview {

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        Quiz698_needReview quiz698NeedReview = new Quiz698_needReview();
        boolean res = quiz698NeedReview.canPartitionKSubsetsStatusCompress(nums, 4);
        System.out.println(res);
    }

    //todo: 利用位运算状态压缩=============================================================================================================
    // 主要需要记住的有两点：
    // 1. 使用整数的位运算来标识集合中元素使用状态，这就是状态压缩。对于状态整数，如果集合数组nums中有n个数，那么就存在2的n次方种状态，从0到Math.pow(2,n)-1，也就是(1<<n)-1，对应从所有数都使用完，到所有数都未使用中间的所有状态
    // 2. 使用dp[]来记录每一个状态的结果，去除重复计算
    // 3. 使用累计长度对单边长取余来记录当前长度累计是否符合要求，无论是记忆化回溯和DP中都是这么使用，这样很省事儿，不管是递归还是状态转移，都自动帮我做了更新边长的事情了
    int[] nums;
    int per, n;
    boolean[] dp;

    public boolean canPartitionKSubsetsStatusCompress(int[] nums, int k) {
        this.nums = nums;
        int all = Arrays.stream(nums).sum();
        if (all % k != 0) {
            return false;
        }
        //边长
        per = all / k;
        //有必要排序吗？
        Arrays.sort(nums);
        n = nums.length;
        //最大的数如果比单边还大，就直接没戏
        if (nums[n - 1] > per) {
            return false;
        }
        //dp[i]表示不同nums可用数字状态下的结果，默认将所有的状态初始化为true，是一个备忘录，所以dp的长度会随nums的长度呈指数级上涨
        dp = new boolean[1 << n];
        Arrays.fill(dp, true);
        //传入初始的可用数字状态，也就是全部可用，usedNumStatus每一个二进制bit位都是1，
        // 如果nums中有n个数，那么就存在2的n次方种状态，从0到 Math.pow(2,n)-1，也就是(1<<n)-1，对应从所有数都使用完，到所有数都未使用中间的所有状态
        // 所以要根据
        return dfs((1 << n) - 1, 0);
    }

    /**
     * @param usedNumStatus 用来记录当前哪些数字已经被使用，1<<i为1则表示nums中的下标为i的数字可以被使用，所以，usedNumStatus的第1位对应nums[0]，usedNumStatus的第二位对应
     * @param sideSoFar 积累到目前为止的长度
     * @return
     */
    public boolean dfs(int usedNumStatus, int sideSoFar) {
        //如果所有数字均用过了，返回true，之所以这里只看usedNumStatus，而不考虑sideSoFar是否也为0，是因为在前面判断了总和能被
        if (usedNumStatus == 0) {
            return true;
        }
        //如果该种状态之前递归过，且不可用，直接返回
        if (!dp[usedNumStatus]) {
            return dp[usedNumStatus];
        }
        //先置该状态下结果为false，后面递归看看是否能成true
        dp[usedNumStatus] = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] + sideSoFar > per) {
                break;
            }
            //确保一下usedNumStatus的第i位bit是1，也就是确保nums中的第i位数是没有被使用过的
            if (((usedNumStatus >> i) & 1) != 0) {
                //nums的第i个数之前没有使用过，用他，usedNumStatus把第i位bit置为0往下传，但是本层不动usedNumStatus，
                // 因为还要循环递归后面的数，(sideSoFar + nums[i]) % per 来算实际达到的边长，记下了
                if (dfs(usedNumStatus ^ (1 << i), (sideSoFar + nums[i]) % per)) {
                    return true;
                }
            }
        }
        return false;
    }

    //todo: 动态规划+状态压缩，这他妈太难了
    // ===============================================================================================================

    public boolean canPartitionKSubsetsDp(int[] nums, int k) {
        int all = Arrays.stream(nums).sum();
        if (all % k != 0) {
            return false;
        }
        int per = all / k;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        //状态总数
        int statusCount = 1 << n;
        //状态转移数组，dp[i]为true代表状态i可达，初始化nums所有数都不选的状态为可达，即true，所以目标是考察最后dp[statusCount-1]是否可达，也就是所有数字都用上的这个状态是否可达
        boolean[] dp = new boolean[statusCount];
        dp[0] = true;
        //todo: 记录每一个状态下的累计长度对单边长取余的结果，这个数组很关键，因为对于任意一个特定的状态i，curSum[i]的值是唯一且固定的，
        // 这在状态转移的时候可以用来作为之前状态的一个记录，所以这种动态规划的状态转移，状态数组相当于有两个，dp和curSum
        // curSum中只有部分状态会有值，这些状态，假设状态数字是t，无一例外，都是curSum[t]<=per。curSum中不会记录
        int[] curSum = new int[statusCount];

        //todo: 遍历所有状态，每一轮遍历，都会基于当前状态，计算后续所有可达状态，将结果更新到dp[]和curSum[]，所以在遍历时只需要遍历所有基于之前状态的可达状态即可，不可达状态看都不用看
        for (int singleStatus = 0; singleStatus < statusCount; singleStatus++) {
            //singleStatus作为下标就代表状态本身，如果该状态基于之前的状态不可达，直接continue下一个状态
            if (!dp[singleStatus]) {
                continue;
            }
            //在单个可达状态下遍历nums的每一个数与当前状态组合的下一个状态是否可达
            for (int j = 0; j < n; j++) {
                //所考察的单边长
                int oops = curSum[singleStatus] + nums[j];
                //如果当前状态加上nums[j]之后比单边长度要大，说明没戏，后面的nums元素不用看了，因为之前排序了，直接break看下一个状态
                if (oops > per) {
                    break;
                }
                //确认一下该状态下nums的第j个数没有被使用过
                if (((singleStatus >> j) & 1) == 0) {
                    //nums[j]没有被使用，那么算出基于当前 singleStatus 状态加上nums[j]之后，也就是使用了nums[j]之后，下一个状态是多少
                    int nextStatusIndex = singleStatus | (1 << j);
                    //如果下一个状态本来就是true（可达），那不用做任何事情。当下一个状态的结果为false时，将其更新为可达，且更新curSum在下一个状态的值
                    if (!dp[nextStatusIndex]) {
                        curSum[nextStatusIndex] = oops % per;
                        dp[nextStatusIndex] = true;
                    }
                }
            }
        }
        return dp[statusCount - 1];
    }


    //todo : 非记忆化回溯，超时=====================================================================================================
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //登记簿
        Map<Integer, Integer> numCountMap = new HashMap<>();
        int sum = 0;
        //算出总和，顺便把所有数都登记一下
        for (int num : nums) {
            sum += num;
            if (numCountMap.containsKey(num)) {
                numCountMap.put(num, numCountMap.get(num) + 1);
            } else {
                numCountMap.put(num, 1);
            }
        }
        //如果总和不能被k整除，返回false
        if (sum % k != 0) {
            return false;
        }

        return canPartitionKSubsetsInternal(0, numCountMap, sum / k);

    }

    private boolean canPartitionKSubsetsInternal(int thisSideSoFar, Map<Integer, Integer> countMap, int singleSideNeeded) {
        if (countMap.size() == 0) {
            //如果最终登记簿没有数了
            if (thisSideSoFar == 0) {
                return true;
            } else {
                return false;
            }
        }

        for (Map.Entry<Integer, Integer> singleEntry : countMap.entrySet()) {
            if (thisSideSoFar + singleEntry.getKey() == singleSideNeeded) {
                //如果本边长加到此处等于所需要的边长，往下从0递归起新的边长
                Map<Integer, Integer> newMap = new HashMap<>(countMap);
                deductOneNum(newMap, singleEntry.getKey());
                boolean res = canPartitionKSubsetsInternal(0, newMap, singleSideNeeded);
                if (res) {
                    return true;
                }
            } else if (thisSideSoFar + singleEntry.getKey() < singleSideNeeded) {
                //如果本边长加上此数仍然小于所需边长，继续往下递归
                Map<Integer, Integer> newMap = new HashMap<>(countMap);
                deductOneNum(newMap, singleEntry.getKey());
                boolean res = canPartitionKSubsetsInternal(thisSideSoFar + singleEntry.getKey(), newMap, singleSideNeeded);
                if (res) {
                    return true;
                }
            }
        }

        return false;
    }

    private void deductOneNum(Map<Integer, Integer> countMap, int num) {
        if (!countMap.containsKey(num)) {
            return;
        }

        if (countMap.get(num) == 1) {
            countMap.remove(num);
        } else {
            countMap.put(num, countMap.get(num) - 1);
        }
    }

}
