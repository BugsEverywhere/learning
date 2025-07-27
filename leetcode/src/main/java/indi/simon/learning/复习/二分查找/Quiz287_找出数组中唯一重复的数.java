package indi.simon.learning.复习.二分查找;

/**
 * @author chenzhuo(zhiyue)
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * <p>
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3 :
 * <p>
 * 输入：nums = [3,3,3,3,3]
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * 进阶：
 * <p>
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 */
//todo: 除了二分查找还有好几种解法，后头可以顺带学习学习
public class Quiz287_找出数组中唯一重复的数 {

    public static void main(String[] args) {
        Quiz287_找出数组中唯一重复的数 quiz287_找出数组中唯一重复的数 = new Quiz287_找出数组中唯一重复的数();
        int res = quiz287_找出数组中唯一重复的数.findDuplicate(new int[]{1, 2, 3, 3, 4, 5, 6});
        System.out.println(res);
    }


    //todo: 官方的二分查找，原理是，如果nums中某个数target是重复的数（甭管重复了几次），那么定义cnt(num)为nums中小于等于num的数的总个数，对于所有的num<target，
    // 总有cnt(num)<=num的（有可能是小于，也有可能是等于，取决于target重复了2次还是更多次），对于所有num>=target，总有cnt(num)>num，因此
    // 只需要找到这个target，当x<target时，cnt(x)<=x；当x>=target时，cnt(x)>x。
    // 由于二分查找本身时间复杂度为O(logn)，这种方式每一轮需要计算cnt(mid)，耗时为O(n)，因此整体时间复杂度为O(nlogn)
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        //todo: 此处l,r,mid都是数组值，不是下标。之所以知道r就是n是因为题目限定了数组中数的范围是[1,n]，此处n不是题目中的
        int l = 1, r = n, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            boolean checkMid = check(nums, mid);
            boolean checkMidMinus = check(nums, mid - 1);
            if (checkMid) {
                //说明mid<target
                l = mid + 1;
            } else if (!checkMid && checkMidMinus) {
                //找到了该mid
                return mid;
            } else {
                //说明mid>target
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 校验数组中小于等于mid的数的个数是否小于等于mid本身
     *
     * @param nums
     * @param mid
     * @return
     */
    private boolean check(int[] nums, int mid) {
        int cnt = 0;
        //对于mid，计算cnt(mid)，此处耗时O(n)
        for (int num : nums) {
            if (num <= mid) {
                cnt++;
            }
        }
        return cnt <= mid;
    }

}
