package indi.simon.learning.leetcode.gogo20221010;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz940_timeExceed {

    public static void main(String[] args) {
        Quiz940_timeExceed quiz940TimeExceed = new Quiz940_timeExceed();
        int res = quiz940TimeExceed.distinctSubseqII("pcrdhwdxmqdznbenhwjsenjhvulyve");
        System.out.println(res);
    }

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
