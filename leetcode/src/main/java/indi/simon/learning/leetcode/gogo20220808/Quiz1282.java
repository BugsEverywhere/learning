package indi.simon.learning.leetcode.gogo20220808;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz1282 {


    public static void main(String[] args) {
        Quiz1282 quiz1282 = new Quiz1282();
        List<List<Integer>> res = quiz1282.groupThePeople(new int[]{2,1,3,3,3,2});
        System.out.println(res);
    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        Map<Integer, List<List<Integer>>> sizeListMap = new HashMap<>();

        for (int i = 0; i < groupSizes.length; i++) {
            List<List<Integer>> listList = sizeListMap.computeIfAbsent(groupSizes[i], k -> new ArrayList<>());
            int j = 0;
            for (; j < listList.size(); j++) {
                if (listList.get(j).size() < groupSizes[i]) {
                    listList.get(j).add(i);
                    break;
                }
            }
            if (j == listList.size()) {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                listList.add(newList);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (List<List<Integer>> listList : sizeListMap.values()) {
            res.addAll(listList);
        }

        return res;
    }


}
