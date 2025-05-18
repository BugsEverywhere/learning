package indi.simon.learning.复习.区间;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz763 {

    public static void main(String[] args) {
        Quiz763 quiz763 = new Quiz763();
        List<Integer> res = quiz763.partitionLabels("qiejxqfnqceocmy");
        System.out.println(res);
    }

    public List<Integer> partitionLabels(String s) {

        Map<Character, int[]> intervals = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int[] interval = intervals.get(c);
            if (interval == null) {
                interval = new int[2];
                interval[0] = i;
                interval[1] = i;
            } else {
                interval[1] = i;
            }
            intervals.put(c, interval);
        }

        List<int[]> intervalArr = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (!intervals.containsKey(c)) {
                continue;
            }
            intervalArr.add(intervals.get(c));
            intervals.remove(c);
        }

        //todo: 开始合并区间
        List<Integer> res = new ArrayList<>();
        int i = 0;
        //记录当前合并大区间的右边界
        int right = 0;
        while (i < intervalArr.size()) {
            int[] intervalI = intervalArr.get(i);
            //如果intervalI右边界比当前合并大区间的右边界还有小，说明肯定合并过该区间了，跳过
            if (intervalI[1] < right) {
                i++;
                continue;
            }
            //更新一下合并大区间的右边界
            right = Math.max(right, intervalI[1]);
            int gap = intervalI[1] - intervalI[0] + 1;
            //往前拓展合并大区间
            int j = i + 1;
            while (j < intervalArr.size()) {
                int[] nextInterval = intervalArr.get(j);
                if (nextInterval[0] > right) {
                    break;
                } else {
                    gap = Math.max(gap, nextInterval[1] - intervalI[0] + 1);
                    right = Math.max(right, nextInterval[1]);
                }
                j++;
            }
            res.add(gap);
            i = j;
        }
        return res;
    }
}
