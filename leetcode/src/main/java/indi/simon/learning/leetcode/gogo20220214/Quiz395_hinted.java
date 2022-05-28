package indi.simon.learning.leetcode.gogo20220214;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz395_hinted {

    public static void main(String[] args) {
        Quiz395_hinted quiz395Hinted = new Quiz395_hinted();
        int res = quiz395Hinted.longestSubstring("zzzzzzzzzzaaaaaaaaabbbbbbbbhbhbhbhbhbhbhicbcbcibcbccccccccccbbbbbbbbaaaaaaaaafffaahhhhhiaahiiiiiiiiifeeeeeeeeee", 10);
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

//todo: 惨烈的提交记录，一开始思路就不对，双指针无法解决子串的复杂判断，官方的滑动窗口解法过于复杂，基本上临场无法想出来。所以只能采取分治+递归的方式，
// 此时要注意分治时候的分段，不能粗心大意，每个子串的结束下标要加入到分段下标list中，也就是第60行
