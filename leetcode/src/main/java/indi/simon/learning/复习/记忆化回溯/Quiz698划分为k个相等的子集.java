package indi.simon.learning.复习.记忆化回溯;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 
// 技巧：
// 0. 排序（方便后续剪枝） + 从0开始的for循环递归dfs，且由于题目是存在性问题，dfs返回值为boolean。
// 1. 本题可用数组的长度为n，状态压缩时，那么就存在2的n次方种状态，从0到Math.pow(2,n)-1，也就是(1<<n)-1，对应从所有数都使用完，到所有数都未使用中间的所有状态
// 2. 之所以本题可以使用1维mem数组来作为备忘录，是因为，一旦dfs到某个状态，那么所用到的nums中的哪些数肯定是唯一确定的，而这些数的和对边长取余（sideSoFar）也是唯一的，所以用到了这些数的情况下，所有状态都是唯一的，不存在额外的维度
// 3. 使用累计长度对单边长取余来记录当前长度累计是否符合要求，无论是记忆化回溯和DP中都是这么使用，这样很省事儿，不管是递归还是状态转移，都自动帮我做了更新边长的事情了，所有多阶段可行性dfs都可以参考这种做法
public class Quiz698划分为k个相等的子集 {

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        Quiz698划分为k个相等的子集 quiz698NeedReview = new Quiz698划分为k个相等的子集();
        boolean res = quiz698NeedReview.canPartitionKSubsetsStatusCompress(nums, 4);
        System.out.println(res);
    }

    //todo:利用位运算状态压缩
    // =============================================================================================================
    int[] nums;
    int per, n;
    boolean[] mem;

    public boolean canPartitionKSubsetsStatusCompress(int[] nums, int k) {
        this.nums = nums;
        int all = Arrays.stream(nums).sum();
        if (all % k != 0) {
            return false;
        }
        //边长
        per = all / k;
        //排序是有必要的，因为在dfs的时候，对于每一个状态，在遍历该状态下sideSoFar与nums中的各数之和时，一旦出现大于per的情况就可以break，后面的不用考虑了
        Arrays.sort(nums);
        n = nums.length;
        //最大的数如果比单边还大，就直接没戏
        if (nums[n - 1] > per) {
            return false;
        }
        //mem[i]表示不同nums可用数字状态下的结果，默认将所有的状态初始化为true，是一个备忘录
        // mem[i]为true表示此状态可使用，在此题中，一旦某个状态在mem中为false，说明此种状态之前来过，后续无须
        mem = new boolean[1 << n];
        Arrays.fill(mem, true);
        //传入初始的可用数字状态，也就是全部可用，usedNumStatus每一个二进制bit位都是1，
        // 如果nums中有n个数，那么就存在2的n次方种状态，从0到 Math.pow(2,n)-1，也就是(1<<n)-1，对应从所有数都使用完，到所有数都未使用中间的所有状态
        // 所以要根据
        return dfs((1 << n) - 1, 0);
    }

    /**
     * @param usedNumStatus 用来记录当前哪些数字已经被使用，1<<i为1则表示nums中的下标为i的数字可以被使用，即1为未使用状态，所以，usedNumStatus的第1位对应nums[0]，usedNumStatus的第二位对应
     * @param sideSoFar     积累到目前为止的长度
     * @return
     */
    public boolean dfs(int usedNumStatus, int sideSoFar) {
        //如果所有数字均用过了，返回true，之所以这里只看usedNumStatus，而不考虑sideSoFar是否也为0，是因为在前面判断了总和能被
        if (usedNumStatus == 0) {
            return true;
        }
        //如果该种状态之前递归过（说明之前那次递归到该状态肯定是返回的false），都不考虑了，直接返回
        if (!mem[usedNumStatus]) {
            return mem[usedNumStatus];
        }
        //todo: 先置该状态为已经递归过，无论在后面的for循环dfs中返回true还是false，以后都不必重复考虑该状态了
        mem[usedNumStatus] = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] + sideSoFar > per) {
                //因为排序了，所以如果此时nums[i]与sideSoFar之和如果大于边长，则可以直接break，后面的肯定更大
                break;
            }
            //todo: 一定要有这一步校验，确保一下usedNumStatus的第i位bit是1，也就是确保nums中的第i位数是没有被使用过的，
            // 如果先前就使用过则此处可以直接跳过，dfs下一个
            if (((usedNumStatus >> i) & 1) != 0) {
                //nums的第i个数之前没有使用过，用他，usedNumStatus把第i位bit置为0往下传，但是本层不动usedNumStatus，
                // (sideSoFar + nums[i]) % per 一下就囊括了边长不足per，以及边长刚好等于per并且归零两个操作，妙~
                //todo: 技巧：记录状态位，可以使用1左移之后与原状态异或，得到新的状态
                //todo: 技巧：可以使用(sideSoFar + nums[i]) % per来递归，自动完成下一层的边长填充
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
        //排序是必须的，同上
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
        for (int i = 0; i < statusCount; i++) {
            //singleStatus作为下标就代表状态本身，如果该状态基于之前的状态不可达，直接continue下一个状态
            if (!dp[i]) {
                continue;
            }
            //在单个可达状态下遍历nums的每一个数与当前状态组合的下一个状态是否可达
            for (int j = 0; j < n; j++) {
                //所考察的单边长
                int oops = curSum[i] + nums[j];
                //如果当前状态加上nums[j]之后比单边长度要大，说明没戏，后面的nums元素不用看了，因为之前排序了，直接break看下一个状态
                if (oops > per) {
                    break;
                }
                //确认一下该状态下nums的第j个数没有被使用过
                if (((i >> j) & 1) == 0) {
                    //nums[j]没有被使用，那么算出基于当前 i 状态加上nums[j]之后，也就是使用了nums[j]之后，下一个状态是多少
                    int nextStatusIndex = i | (1 << j);
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
    

}
