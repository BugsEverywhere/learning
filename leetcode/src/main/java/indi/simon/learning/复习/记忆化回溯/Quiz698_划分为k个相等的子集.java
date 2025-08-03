package indi.simon.learning.复习.记忆化回溯;

import java.util.Arrays;

/**
 * @author chenzhuo(zhiyue)
 *
 * 给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 *
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 * 提示：
 *
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 *
 */
//todo: 
// 技巧：
// 0. 排序（方便后续剪枝） + 从0开始的for循环递归dfs，且由于题目是存在性问题，dfs返回值为boolean。
// 1. 本题可用数组的长度为n，状态压缩时，那么就存在2的n次方种状态，从0到Math.pow(2,n)-1，也就是(1<<n)-1，对应从所有数都使用完，到所有数都未使用中间的所有状态
// 2. 之所以本题可以使用1维mem数组来作为备忘录，是因为，一旦dfs到某个状态，那么所用到的nums中的哪些数肯定是唯一确定的，而这些数的和对边长取余（sideSoFar）也是唯一的，所以用到了这些数的情况下，所有状态都是唯一的，不存在额外的维度
// 3. 使用累计长度对单边长取余来记录当前长度累计是否符合要求，无论是记忆化回溯和DP中都是这么使用，这样很省事儿，不管是递归还是状态转移，都自动帮我做了更新边长的事情了，所有多阶段可行性dfs都可以参考这种做法
public class Quiz698_划分为k个相等的子集 {

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        Quiz698_划分为k个相等的子集 quiz698NeedReview = new Quiz698_划分为k个相等的子集();
        boolean res = quiz698NeedReview.canPartitionKSubsetsStatusCompress(nums, 4);
        System.out.println(res);
    }

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

}
