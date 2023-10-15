package indi.simon.learning.leetcode.gogo20230321;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz373_needReview {

    public static void main(String[] args) {
        Quiz373_needReview quiz373NeedReview = new Quiz373_needReview();
        List<List<Integer>> res = quiz373NeedReview.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
        System.out.println(res);
    }

    boolean flag = true;

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        if (n > m && !(flag = false)) return kSmallestPairs(nums2, nums1, k);
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]])));
        for (int i = 0; i < Math.min(n, k); i++) {
            q.add(new int[]{i, 0});
        }
        while (ans.size() < k && !q.isEmpty()) {
            int[] poll = q.poll();
            int a = poll[0], b = poll[1];
            ans.add(new ArrayList() {{
                add(flag ? nums1[a] : nums2[b]);
                add(flag ? nums2[b] : nums1[a]);
            }});
            if (b + 1 < m) {
                q.add(new int[]{a, b + 1});
            }
        }
        return ans;
    }

}
