package indi.simon.learning.leetcode.gogo20221010;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz940_reviewed {

    public static void main(String[] args) {
        Quiz940_reviewed quiz940TimeExceed = new Quiz940_reviewed();
        int res = quiz940TimeExceed.distinctSubseqII("pcrdhwdxmqdznbenhwjsenjhvulyve");
        System.out.println(res);
    }

    //todo: 官方的dp，这尼玛绝逼写不出，如果不背的话
    // 所以单纯的dp[i]不一定最后会被用到，i一定要是某个字符在s中最后出现的下标，dp[i]才会在最后统计结果的时候被纳入考虑
    public int distinctSubseqIIOfficial(String s) {
        final int MOD = 1000000007;
        //用于记录某个字符最后一次出现的下标的数组，last[i]代表从'a'开始第i个字符最后一次出现在s中的下标值，
        // 比如last[0]代表'a'的最后一个下标，last[1]代表'b'的最后一个下标
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int n = s.length();
        //dp[i]代表以s中下标为i的字符为结尾字符的子串个数
        int[] dp = new int[n];
        //dp[i]需要初始化为1，以他自己结尾的子串s.subStr(0,i]不会在后面的状态转移中被加上
        Arrays.fill(dp, 1);

        //遍历s中每一个字符，同时动态记录该字符在s中最后一次出现所在的下标
        for (int i = 0; i < n; ++i) {
            //遍历以在i之前出现过的所有26个字符中的每一个字符为最后一个字符的情况的子串，将他们累加起来，结合dp[i]本身的1，组成dp[i]的结果
            //这里的逻辑是，以第i位字符结尾的子串个数，是以在他之前出现过的所有字符结尾的子串个数之和，而且为了不重复统计，每一个在他之前出现过的
            //字符都只考虑在他之前最后一次出现的下标即可，即dp[last['a'-'a']]，dp[last['b'-'a']]，dp[last['c'-'a']]....等等
            //之所以可以这样做是因为last数组是在遍历s填充dp时同步遍历更新的，也就是该for循环遍历到dp[i]为止，last中记录的是26个字母在i之前最后出现的下标
            //那么dp[i]只需要看dp中此时出现过的'a'的最终下标（last['a'-'a']），'b'的最终下标（last['b'-'a']），
            //'c'的最终下标（last['c'-'a']）.....的值即可，前提是他们在这之前出现过，如果没有出现过，则last[j]为-1，跳过
            for (int j = 0; j < 26; ++j) {
                //如果last[j]不为-1，代表i之前出现过字符'a'+j，虽然可能在这之后再次出现'a'+j，但那是之后的dp[i]要考虑的事情了
                if (last[j] != -1) {
                    //dp[]中的结果直接取模，就可以省得中间结果过大
                    dp[i] = (dp[i] + dp[last[j]]) % MOD;
                }
            }
            //动态记录本字符，也就是s.charAt(i)最后一次出现的下标，为当前下标
            last[s.charAt(i) - 'a'] = i;
        }
        int ans = 0;
        //最终将所有26个字符在s中的最终下标的dp值累加，也就是累加以每一个字符结尾的子串数，即为结果
        //如果某个字符在s中从来没有出现过，那么就跳过
        for (int i = 0; i < 26; ++i) {
            if (last[i] != -1) {
                ans = (ans + dp[last[i]]) % MOD;
            }
        }
        return ans;
    }

    //todo: 官方dp的优化，你不是最终只考虑dp[last['a'-'a']]，dp[last['b'-'a']]，dp[last['c'-'a']]么，
    // 那我在状态转移时直接把dp[last['x'-'a']]换成一个数组g['x'-'a']不就可以了？
    public int distinctSubseqIIOfficialOptimise(String s) {
        final int MOD = 1000000007;
        //g[i]代表以迄今最后一个字符'a'+i 结尾的子串的个数，那么在状态转移的时候，在遍历s中每一个字符的时候，
        // 只需要将之前出现过的所有最终字符结尾子串个数累加起来即可，之前没出现过某字符，比如没出现过x的话，g['x'-'a']就是0，累加的时候也就加个0而已
        // 哪怕当前s.charAt(i)的字符在之前出现过，打个比方字符b出现过一次，遍历到第二个b的时候，g[1]不为0，那么更新g[1]的时候，也需要累加上之前的g[1]值，
        // 因为xxyyb的子串个数是xxyybuub的一个子集
        int[] g = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int total = 1;
            for (int j = 0; j < 26; ++j) {
                total = (total + g[j]) % MOD;
            }
            g[s.charAt(i) - 'a'] = total;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans = (ans + g[i]) % MOD;
        }
        return ans;
    }


    //todo: 自己的超时做法
    private Set<String> set;
    private Map<String, Integer> mem;

    public int distinctSubseqII(String s) {
        set = new HashSet<>();
        mem = new HashMap<>();
        distinctSubseqIIInternal(s, new StringBuilder(), 0);
        return (int) (set.size() % (Math.pow(10, 9) + 7));
    }

    private void distinctSubseqIIInternal(String s, StringBuilder stringBuilder, int j) {
        if (j >= s.length()) {
            String target = stringBuilder.toString();
            if (!"".equals(target)) {
                set.add(target);
            }
            return;
        }

        if (mem.containsKey(stringBuilder.toString()) && mem.get(stringBuilder.toString()).equals(j)) {
            return;
        }

        //考虑j位字符
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(s.charAt(j));
        distinctSubseqIIInternal(s, stringBuilder1, j + 1);

        //不考虑j位字符
        distinctSubseqIIInternal(s, stringBuilder, j + 1);

        mem.put(stringBuilder.toString(), j);
    }


}
