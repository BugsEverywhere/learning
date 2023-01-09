package indi.simon.learning.leetcode.gogo20230103;

import java.util.*;

public class Quiz187 {

    public static void main(String[] args) {
        Quiz187 quiz187 = new Quiz187();
        List<String> res = quiz187.findRepeatedDnaSequences("AAAAAAAAAAAAA");
        System.out.println(res);
    }

//    public List<String> findRepeatedDnaSequences(String s) {
//        Map<String, List<int[]>> map = new HashMap<>();
//        List<String> res = new ArrayList<>();
//        for (int i = 0; i < s.length() - 10; i++) {
//            String subStr = s.substring(i, i + 10);
//            if (map.containsKey(subStr) && !res.contains(subStr)) {
//                //subStr以前出现过，并且还没有把它往结果集res里面加
//                List<int[]> pairs = map.get(subStr);
//                for (int[] pair : pairs) {
//                    if (pair[0] >= i + 10 || pair[1] < i) {
//                        res.add(subStr);
//                        break;
//                    }
//                }
//                pairs.add(new int[]{i, i + 10});
//            } else if (!res.contains(subStr)) {
//                //subStr以前没有出现过
//                List<int[]> pairsOfI = new ArrayList<>();
//                pairsOfI.add(new int[]{i, i + 10});
//                map.put(subStr, pairsOfI);
//            }
//        }
//        return res;
//    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String subStr = s.substring(i, i + 10);
            if (set.contains(subStr) && !res.contains(subStr)) {
                res.add(subStr);
            }
            set.add(subStr);
        }
        return res;
    }

}
