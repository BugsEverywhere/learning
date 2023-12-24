package indi.simon.learning.leetcode.gogo20231218;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen Zhuo on 2023/12/24.
 */
public class Quiz1055_needReview {

    public static void main(String[] args) {
        Quiz1055_needReview quiz1055NeedReview = new Quiz1055_needReview();
        int res = quiz1055NeedReview.shortestWay("abdefgbde", "abdefg");
        System.out.println(res);
    }

    //todo: 人家的滑动窗口
    public int shortestWayV2(String source, String target) {
        int n = source.length();
        int j = 0;
        int count = 0;
        while (j < target.length()) {
            int prev = j;
            for (int i = 0; i < n; i++) {
                if (j < target.length() && source.charAt(i) == target.charAt(j)) {
                    j++;
                }
            }
            if (prev == j) {
                //如果j没有移动
                return -1;
            }

            count++;
        }

        return count;
    }


    //todo: 自己的递归，超时
    private int minSteps;

    public int shortestWay(String source, String target) {

        minSteps = Integer.MAX_VALUE;

        //记录source中每个字符出现的下标index
        Map<Character, List<Integer>> cMap = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            List<Integer> list = cMap.getOrDefault(source.charAt(i), new ArrayList<>());
            list.add(i);
            cMap.put(source.charAt(i), list);
        }

        char firstC = target.charAt(0);
        if (!cMap.containsKey(firstC)) {
            return -1;
        } else {
            for (int index : cMap.get(firstC)) {
                shortestWayInternal(target, 0, cMap, index, 1);
            }
        }

        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }

    private void shortestWayInternal(String target, int i, Map<Character, List<Integer>> cMap, int sourceIndex, int round) {
        if (round >= minSteps) {
            return;
        }
        if (i + 1 < target.length()) {
            char next = target.charAt(i + 1);
            if (!cMap.containsKey(next)) {
                return;
            }
            List<Integer> list = cMap.get(next);
            for (int index : list) {
                if (index <= sourceIndex) {
                    //重生
                    shortestWayInternal(target, i + 1, cMap, index, round + 1);
                } else {
                    //继续本轮
                    shortestWayInternal(target, i + 1, cMap, index, round);
                }
            }
        } else {
            minSteps = round;
        }
    }

}
