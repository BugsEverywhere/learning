package indi.simon.learning.复习.贪心;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * <p>
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * <p>
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * 示例 2：
 * <p>
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 */
public class Quiz763_划分字母区间 {

    public static void main(String[] args) {
        Quiz763_划分字母区间 quiz763划分字母区间 = new Quiz763_划分字母区间();
        List<Integer> res = quiz763划分字母区间.partitionLabels("abab");
        System.out.println(res);
    }

    public List<Integer> partitionLabels(String s) {
        //记录26个字母在字符串中最后一次出现的下标
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<>();
        //todo: start是当前区间起始下标，end为结束下标，动态变，循环每次往前推进时，end会更新为当前字符的最后位置，
        // 因此只有当end与所记录的当前字符最后位置相等时，start和end中间的所有字符的最后位置都在start和end以内。
        int start = 0, end = 0;
        //开始遍历字符串
        for (int i = 0; i < length; i++) {
            //每次循环都要更新end
            end = Math.max(end, last[s.charAt(i) - 'a']);
            //仅当end与i相等时，说明这是一个需要记录的区间
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

}
