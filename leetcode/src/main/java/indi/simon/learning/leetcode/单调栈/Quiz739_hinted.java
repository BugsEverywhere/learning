package indi.simon.learning.leetcode.单调栈;

import java.util.Comparator;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by Chen Zhuo on 2024/2/7.
 */
public class Quiz739_hinted {

    public static void main(String[] args) {
        Quiz739_hinted quiz739Hinted = new Quiz739_hinted();
        int[] res = quiz739Hinted.dailyTemperaturesV2(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70});
        System.out.println(res);
    }

    //todo: 优秀的递减栈
    public int[] dailyTemperaturesV2(int[] temperatures) {
        Stack<int[]> stack = new Stack<>();

        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            if (stack.size() == 0) {
                stack.push(new int[]{i, temperatures[i]});
                continue;
            }

            while (stack.size() > 0 && temperatures[i] > stack.peek()[1]) {
                int[] thatDay = stack.pop();
                res[thatDay[0]] = i - thatDay[0];
            }
            stack.push(new int[]{i, temperatures[i]});
        }
        return res;
    }


    //todo: 自己的超时treeSet解法，可以看出，不到万不得已不要用treeSet，一是因为可能面试场景下把握不好comparator定义，
    // 此处就是这样，如果存在自己ceiling自己的场景，comparator一定要定义0 case，否则会死循环233.....
    // 二是临床上还没见过官方解法使用treeSet实现的
    public int[] dailyTemperatures(int[] temperatures) {
        //element[0] represents the index, element[1] represents the val
        TreeSet<int[]> tailSet = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    if (o1[0] > o2[0]) {
                        return 1;
                    } else if (o1[0] < o2[0]) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });

        int[] res = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int[] today = new int[]{i, temperatures[i]};
            int[] ceiling = tailSet.higher(today);
            if (ceiling == null) {
                res[i] = 0;
            } else {
                int leastIndex = Integer.MAX_VALUE;
                while (ceiling != null) {
                    if (ceiling[1] > today[1]) {
                        leastIndex = Math.min(leastIndex, ceiling[0]);
                        res[i] = leastIndex - i;
                    }
                    ceiling = tailSet.higher(ceiling);
                }
            }
            tailSet.add(today);
        }
        return res;
    }

}
