package indi.simon.learning.leetcode.脑筋急转弯;

/**
 * @author chenzhuo(zhiyue)
 */
//todo: 除了二分查找还有好几种解法，后头可以顺带学习学习
public class Quiz287_reviewed {

    public static void main(String[] args) {

    }

    //todo: 官方的二分查找，原理是，如果nums中某个数target是重复的数（甭管重复了几次），那么定义cnt(i)为nums重小于等于i的数的总个数，对于所有的i<target，
    // 总有cnt(i)<=i的（有可能是cnt(i)<i，也有可能是cnt(i)==i，取决于target重复了2次还是更多次），对于所有i>=target，总有cnt(i)>i，因此
    // 只需要找到这个target，当x<target时，cnt(x)<=x；当x>=target时，cnt(x)>x。
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            //对于mid，计算cnt(mid)，此处耗时O(n)
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                //说明mid<target
                l = mid + 1;
            } else {
                //说明mid==target或者mid>target，ans顺便赋个值总归不会错
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

}
