package indi.simon.learning.leetcode.gogo20220228;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2100 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 4, 1, 0, 2, 4, 5, 3, 1, 2, 4, 3, 2, 4, 8};
        Quiz2100 quiz2100 = new Quiz2100();
        List<Integer> res = quiz2100.goodDaysToRobBank(arr, 2);
        System.out.println(res);
    }

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int i = 0;
        List<Integer> res = new ArrayList<>();
        if (time == 0) {
            for (int index = 0; index < security.length; index++) {
                res.add(index);
            }
            return res;
        }

        while (i < security.length) {
            //先找到打劫区间的第一天
            int j = i + 1;
            while (j < security.length && j <= i + time && security[j] <= security[j - 1]) {
                j++;
            }
            //判断一下j的大小，来看看窗口期长度是否满足要求
            if (j == i + time + 1) {
                //找到一个time长度左窗口，j先退格
                j--;
                // i移动到j位置，找右窗口
                i = j;
                j = i + 1;
                while (j < security.length && j <= i + time && security[j] >= security[j - 1]) {
                    j++;
                }
                //看一下j的位置
                if (j == i + time + 1) {
                    //找到了！j先退一位，保持窗口长度
                    j--;
                    // 看看一连有多少个满足要求，全add进去
                    while (security[i] <= security[i - 1] && j < security.length && security[j] >= security[j - 1]) {
                        res.add(i);
                        i++;
                        j++;
                    }
                } else if (j == i + 1) {
                    //完了，barbecue了，但是仍然在递减，之前找到的左窗口不能浪费，i移动到j-time
                    i = j - time;
                } else {
                    i = j;
                }
            } else {
                //j中途跳出了，i移动到j，继续下一次找左窗口
                i = j;
            }
        }
        return res;
    }

}
