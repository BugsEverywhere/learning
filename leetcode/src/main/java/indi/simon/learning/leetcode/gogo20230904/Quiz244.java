package indi.simon.learning.leetcode.gogo20230904;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/9/10.
 */
public class Quiz244 {

    public static void main(String[] args) {
        Quiz244 quiz244 = new Quiz244(new String[]{"trag", "milla"});
        int res = quiz244.shortest("trag", "milla");
        System.out.println(res);
    }

    private Map<String, List<Integer>> indexMap;

    public Quiz244(String[] wordsDict) {
        indexMap = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++) {
            indexMap.putIfAbsent(wordsDict[i], new ArrayList<>());
            indexMap.get(wordsDict[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> indexList1 = indexMap.get(word1);
        List<Integer> indexList2 = indexMap.get(word2);
        int minGap = Integer.MAX_VALUE;
        if (indexList1.size() < indexList2.size()) {
            for (Integer word1Index : indexList1) {
                minGap = Math.min(binarySearch(word1Index, indexList2), minGap);
            }
        } else {
            for (Integer word2Index : indexList2) {
                minGap = Math.min(binarySearch(word2Index, indexList1), minGap);
            }
        }
        return minGap;
    }

    private int binarySearch(int index, List<Integer> indexList) {
        int l = 0;
        int r = indexList.size() - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) >> 1;
            int gap = Math.abs(indexList.get(mid) - index);
            if (indexList.get(mid) < index && mid + 1 < indexList.size()) {
                if (indexList.get(mid + 1) > index) {
                    if (gap < Math.abs(indexList.get(mid + 1) - index)) {
                        return gap;
                    } else {
                        return Math.abs(indexList.get(mid + 1) - index);
                    }
                } else if (indexList.get(mid) < index) {
                    l = mid + 1;
                }
            } else if (indexList.get(mid) < index && mid + 1 >= indexList.size()) {
                return gap;
            } else if (indexList.get(mid) > index && mid - 1 >= 0) {
                if (indexList.get(mid - 1) > index) {
                    r = mid - 1;
                } else if (indexList.get(mid - 1) < index) {
                    if (Math.abs(indexList.get(mid - 1) - index) > gap) {
                        return gap;
                    } else {
                        return Math.abs(indexList.get(mid - 1) - index);
                    }
                }
            } else if (indexList.get(mid) > index && mid - 1 < 0) {
                return gap;
            }
        }
        return -1;
    }

}
