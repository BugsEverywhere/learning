package indi.simon.learning.leetcode.gogo20230911;

import java.util.*;

/**
 * Created by Chen Zhuo on 2023/9/17.
 */
public class Quiz254_needReview {

    public static void main(String[] args) {
        Quiz254_needReview quiz254NeedReview = new Quiz254_needReview();
        List<List<Integer>> factors = quiz254NeedReview.getFactors(32);
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

        // 答案放入环节
        if (collector.size() > 0) {
            List<Integer> t = new LinkedList(collector);
            // 要把当前传过来的因数也要加进去
            t.add(n);
            res.add(t);
        }


        // 回溯环节(因数要从最小2开始，然后逐级递增，注意上限)
        for (int i = 2; i * i <= n; i++) {
            // 要判断能不能除尽
            if (n % i != 0) continue;
            // 消除重复数据, 只允许因子从小到大进行排列

            if (collector.isEmpty() || collector.getLast() <= i) {
                // 放入答案
                collector.add(i);
                // 开始回溯
                backtrace(collector, res, n / i);
                // 退出答案
                collector.remove(collector.size() - 1);
            }

        }
    }

}
