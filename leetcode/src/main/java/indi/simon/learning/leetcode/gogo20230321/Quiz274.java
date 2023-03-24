package indi.simon.learning.leetcode.gogo20230321;

import java.util.*;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz274 {

    public static void main(String[] args) {
        Quiz274 quiz274 = new Quiz274();
        int res = quiz274.hIndex(new int[]{0, 0});
        System.out.println(res);
    }

    public int hIndex(int[] citations) {

        Arrays.sort(citations);

        //从后往前遍历，找到最大的满足条件的下标即可返回
        for (int i = citations[citations.length - 1]; i >= 0; i--) {
            PriorityQueue<Integer> indexQueue = binarySearchCeiling(i, citations);
            while (indexQueue.size() > 0) {
                int index = indexQueue.poll();
                if (citations.length - index == i) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * @param possibleH
     * @param citations
     * @return 返回的队列中的下标i，排序好的citations中大于等于i的下标都至少被引用了possibleH次，i从大往小排，方便上层遍历
     */
    private PriorityQueue<Integer> binarySearchCeiling(int possibleH, int[] citations) {
        PriorityQueue<Integer> res = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        int left = 0;
        int right = citations.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (citations[mid] == possibleH) {
                //在citations中找到了目标，返回所有被引用了正好possibleH次的下标，以及！！以及ceiling，因为下标大于等于ceiling的也至少被引用了possibleH次，
                // 并且小于ceiling下标的也至多引用了possibleH次
                res.add(mid);
                int i = mid + 1;
                int j = mid - 1;
                while ((i < citations.length && citations[i] == possibleH) || (j >= 0 && citations[j] == possibleH)) {
                    //todo: 在while内部的数组越界判断也不能省
                    if (i < citations.length && citations[i] == possibleH) {
                        res.add(i);
                        i++;
                    }
                    //todo: 在while内部的数组越界判断也不能省
                    if (j >= 0 && citations[j] == possibleH) {
                        res.add(j);
                        j--;
                    }
                }
                //todo: 此种情况也要记得添加ceiling
                res.add(i);
                return res;
            } else if (citations[mid] < possibleH) {
                if (mid + 1 < citations.length && citations[mid + 1] > possibleH) {
                    //完美跳过，返回ceiling
                    res.add(mid + 1);
                    return res;
                } else if (mid + 1 < citations.length) {
                    left = mid + 1;
                } else if (mid + 1 == citations.length) {
                    //目标过大，没有比这更大的引用次数，不会到这儿
                    return res;
                }
            } else if (citations[mid] > possibleH) {
                if (mid - 1 >= 0 && citations[mid - 1] < possibleH) {
                    //完美跳过，返回ceiling
                    res.add(mid);
                    return res;
                } else if (mid - 1 >= 0) {
                    right = mid - 1;
                } else if (mid - 1 < 0) {
                    //目标过小，返回下标0
                    res.add(0);
                    return res;
                }
            }
        }
        //不会到这儿
        return res;
    }

}
