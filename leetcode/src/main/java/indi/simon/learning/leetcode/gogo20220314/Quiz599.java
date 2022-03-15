package indi.simon.learning.leetcode.gogo20220314;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz599 {

    public static void main(String[] args) {
        String[] list1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = new String[]{"KFC", "Shogun", "Burger King"};
        Quiz599 quiz599 = new Quiz599();
        String[] res = quiz599.findRestaurant(list1, list2);
        System.out.print(res);
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> list1Map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            list1Map.put(list1[i], i);
        }
        List<String> resList = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (list1Map.containsKey(list2[i]) && i + list1Map.get(list2[i]) < minSum) {
                resList.clear();
                resList.add(list2[i]);
                minSum = i + list1Map.get(list2[i]);
            } else if (list1Map.containsKey(list2[i]) && i + list1Map.get(list2[i]) == minSum) {
                resList.add(list2[i]);
            }
        }

        String[] resStr = new String[resList.size()];
        for (int j = 0; j < resList.size(); j++) {
            resStr[j] = resList.get(j);
        }
        return resStr;
    }
}
