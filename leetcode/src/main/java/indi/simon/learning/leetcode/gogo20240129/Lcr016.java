package indi.simon.learning.leetcode.gogo20240129;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2024/2/3.
 */
public class Lcr016 {

    public static void main(String[] args) {
        Lcr016 lcr016 = new Lcr016();
        int res = lcr016.lengthOfLongestSubstring("");
        System.out.println(res);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;
        //窗口内不同字符所在下标
        Map<Character, Integer> window = new HashMap<>();
        window.put(s.charAt(0), 0);

        int res = 0;
        while (j < s.length()) {
            j++;
            if (j < s.length()) {
                if (window.containsKey(s.charAt(j))) {
                    int m = window.get(s.charAt(j));
                    for (int k = i; k <= m; k++) {
                        window.remove(s.charAt(k));
                    }
                    i = m + 1;
                    //todo: 此处注意remove掉了重复元素，还需要put回来，毕竟窗口往后走了一步
                    window.put(s.charAt(j), j);
                } else {
                    res = Math.max(res, j - i + 1);
                    window.put(s.charAt(j), j);
                }
            } else {
                res = Math.max(res, j - i);
            }
        }

        return res;
    }

}
