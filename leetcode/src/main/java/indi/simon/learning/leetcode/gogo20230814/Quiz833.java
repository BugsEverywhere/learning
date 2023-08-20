package indi.simon.learning.leetcode.gogo20230814;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/8/20.
 */
public class Quiz833 {

    public static void main(String[] args) {
        Quiz833 quiz833 = new Quiz833();
        String res = quiz833.findReplaceString("abcde", new int[]{2, 2}, new String[]{"cde", "cdef"}, new String[]{"fe", "f"});
        System.out.println(res);
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {

        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < indices.length; i++) {
            indexMap.putIfAbsent(indices[i], new ArrayList<>());
            indexMap.get(indices[i]).add(i);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            if (indexMap.containsKey(i)) {
                boolean hit = false;
                for (Integer single : indexMap.get(i)) {
                    if (i + sources[single].length() <= s.length() && s.startsWith(sources[single], i)) {
                        builder.append(targets[single]);
                        i = i + sources[single].length();
                        hit = true;
                        break;
                    }
                }
                if (!hit) {
                    builder.append(s.charAt(i));
                    i++;
                }
            } else {
                builder.append(s.charAt(i));
                i++;
            }

        }

        return builder.toString();
    }

}
