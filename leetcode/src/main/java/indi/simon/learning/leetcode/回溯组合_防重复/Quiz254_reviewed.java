package indi.simon.learning.leetcode.回溯组合_防重复;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/9/17.
 */
//todo:回溯组合防重复的重要手段就是排序，这里其实和之前的排序后递归是一个思路，递归时按照因子从小到大的顺序来递归，
// 就可以防止重复组合被加入到结果集
public class Quiz254_reviewed {

    public static void main(String[] args) {
        Quiz254_reviewed quiz254NeedReview = new Quiz254_reviewed();
        List<List<Integer>> factors = quiz254NeedReview.getFactors(16);
        System.out.println(factors);
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> mid_res = new LinkedList<>();
        backtrace(mid_res, res, n);
        return res;
    }


    public void backtrace(LinkedList<Integer> collector, List<List<Integer>> res, int n) {
        // 退出条件
        if (n == 1) {
            return;
        }

        // 加入当前组合
        if (collector.size() > 0) {
            List<Integer> t = new LinkedList(collector);
            // 要把当前传过来的因数也要加进去
            t.add(n);
            res.add(t);
        }


        // 回溯环节(因数要从最小2开始，然后逐级递增，注意上限)，对当前因子进行回溯
        for (int i = 2; i * i <= n; i++) {
            // 要判断能不能除尽
            if (n % i != 0) continue;
            // 消除重复数据, 只允许因子从小到大进行排列
            //todo: 这一步就是防重复的判断，一定保证collector里面已经加入的因子是从小到大的
            if (collector.isEmpty() || collector.getLast() <= i) {
                // 放入路径
                collector.add(i);
                // 开始回溯
                backtrace(collector, res, n / i);
                // 清理路径
                collector.remove(collector.size() - 1);
            }

        }
    }

}
