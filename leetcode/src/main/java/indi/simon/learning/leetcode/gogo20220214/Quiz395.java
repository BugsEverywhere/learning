package indi.simon.learning.leetcode.gogo20220214;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz395 {

    public static void main(String[] args) {
        Quiz395 quiz395 = new Quiz395();
        int res = quiz395.longestSubstring("zzzzzzzzzzaaaaaaaaabbbbbbbbhbhbhbhbhbhbhicbcbcibcbccccccccccbbbbbbbbaaaaaaaaafffaahhhhhiaahiiiiiiiiifeeeeeeeeee", 10);
        System.out.println(res);

    }

    public int longestSubstring(String s, int k) {
        return acquireMaxOkLengthOfSubStr(0, s.length(), k, s);
    }

    /**
     * @param i
     * @param j
     * @param k
     * @param s
     * @return
     */
    private int acquireMaxOkLengthOfSubStr(int i, int j, int k, String s) {
        if (j - i < k) {
            return 0;
        }

        Map<Character, List<Integer>> indexMap = new HashMap<>();
        //先遍历一遍统计所有字符出现次数
        for (int o = i; o < j; o++) {
            Character c = s.charAt(o);
            List<Integer> indexList = indexMap.get(c);
            if (indexList == null || indexList.size() == 0) {
                indexList = new ArrayList<>();
            }
            indexList.add(o);
            indexMap.put(c, indexList);
        }
        //把捣乱的字符摘出来
        indexMap.entrySet().removeIf(entry -> entry.getValue().size() >= k);

        //递归终止条件
        if (indexMap.size() == 0) {
            return j - i;
        }

        int maxOkLengthOfThisLayer = 0;

        List<Integer> frontierIndexes = new ArrayList<>();
        for (Map.Entry<Character, List<Integer>> entry : indexMap.entrySet()) {
            frontierIndexes.addAll(entry.getValue());
        }

        frontierIndexes.sort(Comparator.comparingInt(o -> o));
        frontierIndexes.add(j);

        int leftPoint = i;
        int frontierIndexIndex = 0;
        while (leftPoint < j && frontierIndexIndex < frontierIndexes.size()) {
            int frontierIndex = frontierIndexes.get(frontierIndexIndex);
            int maxSubOkLength = acquireMaxOkLengthOfSubStr(leftPoint, frontierIndex, k, s);
            maxOkLengthOfThisLayer = Math.max(maxOkLengthOfThisLayer, maxSubOkLength);
            leftPoint = frontierIndexes.get(frontierIndexIndex) + 1;
            frontierIndexIndex++;
        }

        return maxOkLengthOfThisLayer;
    }

}
