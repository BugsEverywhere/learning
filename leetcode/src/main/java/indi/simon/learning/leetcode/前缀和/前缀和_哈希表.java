package indi.simon.learning.leetcode.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class 前缀和_哈希表 {

    public static void main(String[] args) {
        前缀和_哈希表 前缀和_哈希表 = new 前缀和_哈希表();
        String[] res = 前缀和_哈希表.findLongestSubarray(new String[]{"A", "A"});
        System.out.println(res);
    }

    // todo:认为字母为1，数字为-1，构造前缀和数组，这样，由于s[j]代表nums中从下标0到下标j-1的元素的和，那么如果s[i]=s[j]，且i>j
    //  那么说明区间[0,j-1]与[0,i-1]的元素和是相同的，则说明[j,i-1]这个区间的元素和为0，所谓元素和为0，代表着原数组在这个区间内"没有变化"，
    //  这个套路可以用来处理很多这样的题型，只要将"没有变化"这种状态抽象到位就行了，比如本题的"没有变化"就意味着在这段区间内数字和字母个数相同
    public String[] findLongestSubarray(String[] array) {
        int n = array.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            //是字母则isChar为1，是数字则isChar为0
            int isChar = array[i].charAt(0) >> 6 & 1;
            s[i + 1] = s[i] + (isChar * 2 - 1);
        }

        // 符合要求的子数组 [begin,end)
        int begin = 0, end = 0;
        //todo: 这也是成熟套路，使用哈希表来记录元素和中首次出现的某个和，这样方便后续监控到再次遇到这个和的情况
        Map<Integer, Integer> first = new HashMap<>();
        for (int i = 0; i <= n; ++i) {
            int j = first.getOrDefault(s[i], -1);
            if (j < 0) {
                // 首次遇到 s[i]
                first.put(s[i], i);
            } else if (i - 1 - j > end - begin) {
                // 更长的子数组，子数组结尾是下标i-1，开头是j
                begin = j;
                end = i - 1;
            }
        }

        String[] sub;
        if (end == 0 && begin == 0) {
            sub = new String[0];
        } else {
            sub = new String[end - begin + 1];
        }
        System.arraycopy(array, begin, sub, 0, sub.length);
        return sub;
    }

}
